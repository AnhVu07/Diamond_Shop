/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.model.Categorys;
import com.anhvu.model.Contact;
import com.anhvu.model.Menus;
import com.anhvu.model.Slides;
import com.anhvu.repository.BillDetailRepository;
import com.anhvu.repository.BillRepository;
import com.anhvu.repository.CategoryRepository;
import com.anhvu.repository.ContactRepository;
import com.anhvu.repository.MenuRepository;
import com.anhvu.repository.ProductRepository;
import com.anhvu.repository.SlideRepository;
import com.anhvu.repository.UserRepository;
import com.anhvu.utils.UserInputSanitizer;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class HomeServiceImp implements IHomeService {

	@Autowired
	SlideRepository slideRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	BillDetailRepository billDetailRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Slides> getListSlides() {
		return slideRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categorys> getListCategorys() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menus> getListMenus() {
		return menuRepository.findAll();
	}

	@Override
	@Async
	public void sendMail(String to, String subject, String content) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);

		javaMailSender.send(mailMessage);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> getListContacts() {
		return contactRepository.findAll();
	}

	@Override
	public void deleteContact(int id) {
		contactRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Contact with id: " + id + " does not exist"));

		contactRepository.deleteById(id);
	}

	@Override
	public Contact addContact(Contact contact) {
		sanitizerContact(contact);

		return contactRepository.save(contact);
	}

	private void sanitizerContact(Contact contact) {
		String subject = UserInputSanitizer.sanitizer(contact.getSubject());
		String email = UserInputSanitizer.sanitizer(contact.getEmail());
		String content = UserInputSanitizer.sanitizer(contact.getContent());
		contact.setSubject(subject);
		contact.setEmail(email);
		contact.setContent(content);
	}

	@Override
	public boolean exitsByIdContac(int id) {
		return contactRepository.existsById(id);
	}

	@Override
	public void addCategory(Categorys categorys) {
		categoryRepository.findCategoryByName(categorys.getName()).ifPresent(c -> {
			throw new IllegalStateException("Category Name already in use!");
		});

		sanitizerCategory(categorys);
		categoryRepository.save(categorys);

	}

	private void sanitizerCategory(Categorys categorys) {
		String name = UserInputSanitizer.sanitizer(categorys.getName());
		String description = UserInputSanitizer.sanitizer(categorys.getDescription());
		categorys.setName(name);
		categorys.setDescription(description);
	}

	@Override
	@Transactional
	public void updateCategorys(Categorys categorys) {
		Categorys existingCategory = getCategoryById(categorys.getIdCategory());
		checkAndUpdate(existingCategory, categorys);
	}

	private void checkAndUpdate(Categorys existingCategory, Categorys categorys) {
		if (StringUtils.isNotBlank(categorys.getName())
				&& !Objects.equals(categorys.getName(), existingCategory.getName())) {
			existingCategory.setName(UserInputSanitizer.sanitizer(categorys.getName()));
			existingCategory.setDescription(UserInputSanitizer.sanitizer(categorys.getDescription()));
		}
	}

	@Override
	public void deleteCategorys(int id) {
		getCategoryById(id);
		categoryRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Categorys getCategoryById(int id) {

		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Category with id: " + id + " does not exist!"));
	}

}
