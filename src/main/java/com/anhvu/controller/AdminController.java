/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anhvu.dto.BillsDto;
import com.anhvu.dto.OrderDto;
import com.anhvu.exception.ProductNotFoundException;
import com.anhvu.exception.UserNotFoundException;
import com.anhvu.model.Categorys;
import com.anhvu.model.Contact;
import com.anhvu.model.Products;
import com.anhvu.model.RedirectPath;
import com.anhvu.model.Users;
import com.anhvu.service.IBillService;
import com.anhvu.service.IHomeService;
import com.anhvu.service.IProductService;
import com.anhvu.service.IUserService;
import com.google.gson.Gson;

/**
 *
 * @author Admin
 */
@Controller
@Validated
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	IHomeService homeService;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@Autowired
	IBillService billService;

	@GetMapping(value = { "/manager_products" })
	public ModelAndView manager(@ModelAttribute("product") Products product) {
		ModelAndView view = new ModelAndView("/admin/manager_product");

		view.addObject("categorys", homeService.getListCategorys()).addObject("listP",
				productService.getListProducts());

		return view;
	}

	@GetMapping("/load/{id}")
	public String load(@ModelAttribute("product") Products product, @PathVariable Long id,
			RedirectAttributes redirectAttributes, Model model) {

		if (id == null || id <= 0) {
			redirectAttributes.addAttribute("errorMessage", "ID product không hợp lệ");
			return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
		}

		try {
			model.addAttribute("categorys", homeService.getListCategorys());
			model.addAttribute("detail", productService.getProductById(id));
		} catch (ProductNotFoundException e) {
			redirectAttributes.addAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error loading product", e);
			redirectAttributes.addFlashAttribute("successMessage", "Loading product unsuccessful!");
		}

		return "admin/edit";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam Long productId, RedirectAttributes redirectAttributes) {
		if (productId == null || productId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID product không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
		}

		try {
			productService.deleteProduct(productId);
			redirectAttributes.addFlashAttribute("successMessage", "Delete product successful!");
		} catch (ProductNotFoundException e) {
			redirectAttributes.addAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error deleting product with id: " + productId, e);
			redirectAttributes.addFlashAttribute("successMessage", "Delete product unsuccessful!");
		}

		return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
	}

	@PostMapping(value = { "/addProduct" })
	public String insertProduct(@RequestBody @ModelAttribute("product") @Valid Products product,
			RedirectAttributes redirectAttributes, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
		}

		try {
			productService.addProduct(product);
			redirectAttributes.addFlashAttribute("messageSuccess", "Thêm sản phẩm thành công!");
		} catch (ProductNotFoundException e) {
			redirectAttributes.addAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error adding product: " + product.getName(), e);
			redirectAttributes.addFlashAttribute("errorMessage", "Thêm sản phẩm thất bại. Vui lòng thử lại.");
		}

		return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();

	}

	@PostMapping(value = { "/editProduct" })
	public String updateProduct(@RequestBody @ModelAttribute("product") @Valid Products product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/edit";
		}

		try {
			productService.updateProduct(product);
			redirectAttributes.addAttribute("successMessage", "Update product successful!");

		} catch (ProductNotFoundException e) {
			redirectAttributes.addAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error updating product with id: " + product.getIdProducts(), e);
			redirectAttributes.addAttribute("errorMessage", "Update product unsuccessful!");
		}

		return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
	}

	@GetMapping(value = { "/manager_category" })
	public ModelAndView managerCategory(@ModelAttribute("category") Categorys category) {
		ModelAndView view = new ModelAndView("/admin/manager_category");

		view.addObject("listCategorys", homeService.getListCategorys());

		return view;
	}

	@PostMapping(value = { "/addCategory" })
	public String addCategory(@ModelAttribute("category") @Valid Categorys category,
			RedirectAttributes redirectAttributes, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
		}

		try {
			homeService.addCategory(category);
			redirectAttributes.addFlashAttribute("successMessage", "Danh mục đã được thêm thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error adding category", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm danh mục.");
		}

		return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
	}

	@GetMapping(value = { "/loadCategory/{id}" })
	public String loadCategory(@PathVariable("id") Integer id, @ModelAttribute("category") Categorys category,
			RedirectAttributes redirectAttributes, Model model) {

		if (id == null || id <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID category không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
		}

		try {
			model.addAttribute("categoryById", homeService.getCategoryById(id));
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error loading category", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi tải danh mục");
		}

		return "/admin/editCategory";
	}

	@PostMapping("/editCategory")
	public String editCategory(@ModelAttribute("category") @Valid Categorys category, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/editCategory";
		}

		try {
			homeService.updateCategorys(category);
			redirectAttributes.addFlashAttribute("successMessage", "Danh mục đã được cập nhật thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error updating category", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật danh mục.");
		}

		return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
	}

	@PostMapping(value = { "/deleteCategory" })
	public String deleteCategory(@RequestParam("categoryId") Integer categoryId,
			RedirectAttributes redirectAttributes) {

		if (categoryId == null || categoryId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID category không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
		}

		try {
			homeService.deleteCategorys(categoryId);
			redirectAttributes.addFlashAttribute("successMessage", "Category đã được xóa thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error delete category", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa danh mục");
		}
		return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
	}

	@GetMapping(value = { "/manager_productReview" })
	public ModelAndView managerProductReview() {
		ModelAndView view = new ModelAndView("/admin/manager_productReview");

		view.addObject("listProductReview", productService.getListReview());

		return view;
	}

	@PostMapping("/deletePReview")
	public String deleteProductReview(@RequestParam("reviewId") Integer reviewId,
			RedirectAttributes redirectAttributes) {

		if (reviewId == null || reviewId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID productReview không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_REVIEW.getPath();
		}

		try {
			productService.deleteReview(reviewId);
			redirectAttributes.addFlashAttribute("successMessage", "Nhận xét đã được xóa thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error delete review", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa nhận xét");
		}
		return "redirect:"+RedirectPath.MANAGER_REVIEW.getPath();
	}

	@GetMapping("/manager_contact")
	public ModelAndView managerContact(@ModelAttribute("contact") Contact contact) {
		ModelAndView view = new ModelAndView("/admin/manager_contact");

		view.addObject("listContact", homeService.getListContacts()).addObject("menus", homeService.getListMenus());

		return view;
	}

	@PostMapping(value = "/deleteContact")
	public String deleteContact(@RequestParam("contactId") Integer contactId, RedirectAttributes redirectAttributes) {

		if (contactId == null || contactId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID contact không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_CONTACT.getPath();
		}

		try {
			homeService.deleteContact(contactId);
			redirectAttributes.addFlashAttribute("successMessage", "Liên hệ đã được xóa thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error delete contact", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa liên hệ");
		}
		return "redirect:"+RedirectPath.MANAGER_CONTACT.getPath();

	}

	@PostMapping("/sendFeedback")
	public String sendContact(@ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng kiểm tra lại thông tin liên hệ.");
			return "redirect:"+RedirectPath.MANAGER_CONTACT.getPath();
		}

		try {
			homeService.sendMail(contact.getEmail(), contact.getSubject(), contact.getContent());
			redirectAttributes.addFlashAttribute("successMessage", "Gửi mail thành công!");
		} catch (Exception e) {
			logger.error("Email sending failed", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Gửi mail thất bại. Vui lòng thử lại sau.");
		}

		return "redirect:"+RedirectPath.MANAGER_CONTACT.getPath();
	}

	@GetMapping("/returnAdmin")
	public String returnAdmin() {
		return "redirect:"+RedirectPath.MANAGER_PRODUCTS.getPath();
	}

	@GetMapping("/returnAdminAccount")
	public String returnAdminAccount() {
		return "redirect:"+RedirectPath.MANAGER_ACCOUNTS.getPath();
	}

	@GetMapping("/returnAdminCategory")
	public String returnAdminCategory() {
		return "redirect:"+RedirectPath.MANAGER_CATEGORY.getPath();
	}

	@GetMapping(value = { "/manager_orders" })
	public ModelAndView managerOrders(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/admin/manager_orders");
		List<BillsDto> listOrder = billService.getListOrders();
		List<OrderDto> list = billService.getDataOrders();
		List<Map<String, Object>> dataList = new ArrayList<>();
		for (OrderDto item : list) {
			Map<String, Object> dataItem = new HashMap<>();
			dataItem.put("orderMonth", item.getTotal_orders());
			dataItem.put("revenue", item.getTotal_revenue());
			dataItem.put("ngay", item.getNgay());
			dataList.add(dataItem);
		}
		String data = new Gson().toJson(dataList);
		view.addObject("listOrder", listOrder);
		view.addObject("menus", homeService.getListMenus());
		request.setAttribute("listData", data);
		request.setAttribute("listOrders", dataList);

		return view;
	}

	@PostMapping("/deleteBills")
	public String deleteBills(@RequestParam("billId") Integer billId, RedirectAttributes redirectAttributes) {
		if (billId == null || billId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID hóa đơn không hợp lệ.");
			return "redirect:"+RedirectPath.MANAGER_ORDERS.getPath();
		}
		try {
			billService.deleteBill(billId);
			redirectAttributes.addFlashAttribute("successMessage", "Hóa đơn đã được xóa thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error delete bill", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa hóa đơn.");
		}
		return "redirect:"+RedirectPath.MANAGER_ORDERS.getPath();
	}

	@GetMapping(value = "/manager_accounts")
	public ModelAndView manager_accounts(@ModelAttribute("account") Users account) {
		ModelAndView view = new ModelAndView("/admin/manager_accounts");
		view.addObject("listUser", userService.getListUsers());

		return view;
	}

	@PostMapping(value = "/addAcc")
	public String addAcc(@RequestBody @ModelAttribute("account") @Valid Users account, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:"+ RedirectPath.MANAGER_ACCOUNTS.getPath();
		}

		try {
			userService.addUser(account);
			redirectAttributes.addFlashAttribute("successMessage", "Tài khoản đã được thêm thành công.");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm tài khoản.");
		}

		return "redirect:"+ RedirectPath.MANAGER_ACCOUNTS.getPath();
	}

	@PostMapping("/deleteAcc")
	public String deleteAcc(@RequestParam("userId") Integer userId, RedirectAttributes redirectAttributes) {
		if (userId == null || userId <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID tài khoản không hợp lệ.");
			return "redirect:"+ RedirectPath.MANAGER_ACCOUNTS.getPath();
		}

		try {
			userService.deleteUser(userId);
			redirectAttributes.addFlashAttribute("successMessage", "Delete Acc successful!");
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error delete acc", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Delete Acc unsuccessful!");
		}

		return "redirect:"+ RedirectPath.MANAGER_ACCOUNTS.getPath();

	}

	@GetMapping("/loadAcc/{id}")
	public String loadAcc(@PathVariable Integer id, @ModelAttribute("account") Users account, Model model, RedirectAttributes redirectAttributes) {
		if (id == null || id <= 0) {
			redirectAttributes.addFlashAttribute("errorMessage", "ID tài khoản không hợp lệ.");
			return "redirect:"+ RedirectPath.MANAGER_ACCOUNTS.getPath();
		}

		try {
			Users user = userService.getUsersById(id);
			model.addAttribute("getUsersById", user);
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error loading account", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tải thông tin tài khoản.");
		}

		return "admin/edit_Acc";
	}

	@PostMapping("/editAcc")
	public String updateAccount(@Valid @ModelAttribute("account") Users account, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/edit_Acc";
		}

		try {
			userService.updateUser(account);
			return handleException(null, redirectAttributes,"Tài khoản đã được cập nhật thành công",null,RedirectPath.MANAGER_ACCOUNTS.getPath());
		} catch (Exception e) {
			return handleException(e, redirectAttributes, "", "Có lỗi xảy ra khi cập nhật tài khoản", RedirectPath.MANAGER_ACCOUNTS.getPath());
		}

	}

	@GetMapping("/pdf/{idOrder}")
	public ResponseEntity<?> exportOrderToPdf(@PathVariable("idOrder") Integer idOrder) {
		if (idOrder == null || idOrder <= 0) {
			return ResponseEntity.badRequest().body("Invalid order ID");
		}
		try {

			byte[] pdfBytes = billService.generateOrderPdf(idOrder);
			if (pdfBytes == null || pdfBytes.length == 0) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Order_" + idOrder + ".pdf")
					.body(pdfBytes);

		} catch (Exception e) {
			logger.error("Error generating PDF for order " + idOrder, e);
			return ResponseEntity.internalServerError().body("Error generating PDF");
		}

	}

	private String handleException(Exception e, RedirectAttributes redirectAttributes, String successMessage,
			String defaultErrorMessage, String redirectUrl) {
		if (e instanceof IllegalStateException || e instanceof UserNotFoundException
				|| e instanceof ProductNotFoundException) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		} else {
			logger.error(defaultErrorMessage, e);
			redirectAttributes.addFlashAttribute("errorMessage", defaultErrorMessage);
		}
		if (successMessage != null) {
			redirectAttributes.addFlashAttribute("successMessage", successMessage);
		}
		return "redirect:" + redirectUrl;
	}
}
