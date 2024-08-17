package com.anhvu.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.dto.BillsDto;
import com.anhvu.dto.CartDto;
import com.anhvu.dto.OrderDto;
import com.anhvu.model.Bills;
import com.anhvu.model.Billsdetail;
import com.anhvu.repository.BillDetailRepository;
import com.anhvu.repository.BillRepository;
import com.anhvu.security.UsersDetail;
import com.anhvu.security.oauth.CustomerOAuth2User;
import com.anhvu.utils.UserInputSanitizer;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class BillServiceImp implements IBillService {

	private static final String FONT_PATH = "src/main/resources/static/assets/user/font/DejaVuSans.ttf";

	@Autowired
	BillRepository billRepository;

	@Autowired
	BillDetailRepository billDetailRepository;

	@Override
	public Bills getLastestBill() {

		return billRepository.getLastestBill();
	}

	@Override
	public Bills addBill(Bills bills) {
		sanitizerBill(bills);

		return billRepository.save(bills);
	}

	private void sanitizerBill(Bills bills) {
		java.sql.Date created_at = new java.sql.Date(System.currentTimeMillis());
		bills.setCreated_at(created_at);
		String address = UserInputSanitizer.sanitizer(bills.getAddress());
		String displayName = UserInputSanitizer.sanitizer(bills.getDisplayName());
		String note = UserInputSanitizer.sanitizer(bills.getNote());
		String phone = UserInputSanitizer.sanitizer(bills.getPhone());
		bills.setAddress(address);
		bills.setDisplayName(displayName);
		bills.setNote(note);
		bills.setPhone(phone);
	}

	@Override
	@Transactional
	public void deleteBill(int id) {
		billRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("bill with id: " + id + " does not exist"));

		billRepository.deleteById(id);

	}

	@Override
	public Billsdetail addBillDetail(Billsdetail bills) {

		return billDetailRepository.save(bills);
	}

	@Override
	public List<BillsDto> getListOrders() {

		return billRepository.getListOrders();
	}

	@Override
	public List<OrderDto> getDataOrders() {

		return billRepository.getDataOrders();
	}

	@Override
	public void addBillDetail(HashMap<Long, CartDto> cart) {
		Billsdetail bd = setBillDetail(cart);
		billDetailRepository.save(bd);

	}
	
	private Billsdetail setBillDetail(HashMap<Long, CartDto> cart) {
		Bills bill = billRepository.getLastestBill();
		Billsdetail bd = new Billsdetail();
		for (Map.Entry<Long, CartDto> itemCart : cart.entrySet()) {
			bd.setBill(bill);
			bd.setProduct(itemCart.getValue().getProduct());
			bd.setQuatity(itemCart.getValue().getTotalQuatity());
			bd.setTotal(itemCart.getValue().getTotalPrice());
		}
		return bd;
	}

	@Override
	public boolean ExitsById(int id) {
		return billRepository.existsById(id);
	}

	@Override
	public Bills getBillById(int id) {
		return billRepository.getById(id);
	}

	@Override
	public List<BillsDto> getListOrdersHistory() {
	    String email = getAuthenticatedUserEmail();
	    return email != null ? billRepository.getListOrdersHistory(email) : Collections.emptyList();
	}

	private String getAuthenticatedUserEmail() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        return null;
	    }

	    Object principal = authentication.getPrincipal();
	    if (principal instanceof OAuth2User) {
	        return new CustomerOAuth2User((OAuth2User) principal).getEmail();
	    } else if (principal instanceof UsersDetail) {
	        return ((UsersDetail) principal).getUsername();
	    }

	    return null;
	}

	@Override
	public byte[] generateOrderPdf(int id) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (PdfWriter pdfWriter = new PdfWriter(outputStream); PdfDocument pdfDocument = new PdfDocument(pdfWriter)) {
			Document document = new Document(pdfDocument);
			PdfFont font = PdfFontFactory.createFont(FONT_PATH, PdfEncodings.IDENTITY_H, true);
			document.setFont(font);
			int count = 0;
			List<BillsDto> billsDto = billRepository.generateOrdersPdf(id);
			for (BillsDto orderPdf : billsDto) {

				document.add(new Paragraph("Order ID: " + id));
				document.add(new Paragraph("Order Peson: " + orderPdf.getDisplay_name()));
				document.add(new Paragraph("Order Address: " + orderPdf.getAddress()));
				document.add(new Paragraph("Order PhoneNumber: " + orderPdf.getPhone()));
				document.add(new Paragraph("Order Note: " + orderPdf.getNote()));
				document.add(new Paragraph("Order Email: " + orderPdf.getUsers()));
				document.add(new Paragraph("Order NameProduct: " + orderPdf.getProduct_name()));
				document.add(new Paragraph("Order Quantity: " + orderPdf.getQuantity()));
				document.add(new Paragraph("Order Total: " + orderPdf.getBill_detail_total()));
				document.add(new Paragraph("Order ProductPrice: " + orderPdf.getProduct_price()));
				document.add(new Paragraph("Order ProductDetail: " + orderPdf.getProduct_detail()));

				count++;
				if (billsDto.size() > count) {
					document.add(new Paragraph("===================================================="));
				}
			}
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}

}
