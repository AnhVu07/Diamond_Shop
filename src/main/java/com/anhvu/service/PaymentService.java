package com.anhvu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhvu.configuration.VNPayConfig;
import com.anhvu.dto.BillsDto;
import com.anhvu.model.Bills;
import com.anhvu.model.Payment;
import com.anhvu.repository.BillRepository;
import com.anhvu.repository.PaymentRepository;
import com.anhvu.utils.VNPayUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	@Autowired
	IBillService billService;

	@Autowired
	BillRepository billRepository;

	@Autowired
	PaymentRepository paymentRepository;

	private final VNPayConfig vnPayConfig;

	public String createVnPayPayment(HttpServletRequest request) throws ParseException {

		double amount = 0;
		int idOrder = 0;
		Bills bills = billService.getLastestBill();
		List<BillsDto> listOrderById = billRepository.generateOrdersPdf(bills.getId());
		if (!listOrderById.isEmpty()) {
			for (BillsDto billsDto : listOrderById) {
				amount += billsDto.getBill_detail_total();
			}

			idOrder = listOrderById.get(0).getBill_id();
		}

		long total = (long) amount;
		total = total * 100L;
		String bankCode = "NCB";
		Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
		vnpParamsMap.put("vnp_Amount", String.valueOf(total));
		if (bankCode != null && !bankCode.isEmpty()) {
			vnpParamsMap.put("vnp_BankCode", bankCode);
		}
		vnpParamsMap.put("vnp_IpAddr", VNPayUtils.getIpAddress(request));

		Payment payment = new Payment();
		Bills bill = billService.getBillById(idOrder);
		payment.setAmount(total);
		payment.setVnpBankCode(bankCode);
		payment.setOrderId(bill);

		SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = vnpParamsMap.get("vnp_CreateDate");
		Date day = inputFormatter.parse(date);
		String vnpCreateDate = formatter.format(day);
		payment.setVnpTransDate(vnpCreateDate);
		payment.setVnpResponseCode("Success");
		paymentRepository.save(payment);

		vnpParamsMap.put("vnp_TxnRef", String.valueOf(idOrder));
		vnpParamsMap.put("vnp_OrderInfo", String.valueOf(idOrder));
		// build query url
		String queryUrl = VNPayUtils.getPaymentURL(vnpParamsMap, true);
		String hashData = VNPayUtils.getPaymentURL(vnpParamsMap, false);
		String vnpSecureHash = VNPayUtils.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
		queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
		String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
		return paymentUrl;
	}
}
