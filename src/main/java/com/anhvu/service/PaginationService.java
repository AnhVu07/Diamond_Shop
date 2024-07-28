/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.service;

import com.anhvu.dto.Pagination;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PaginationService {

    public Pagination getInfoPagination(int totalItems, int currentPage, int itemsPerPage) {
        Pagination pagination = new Pagination();
        pagination.setLimit(itemsPerPage);
        
        int totalPages = calculateTotalPages(totalItems, itemsPerPage);
        pagination.setTotalPage(totalPages);
        
        int validatedCurrentPage = validateCurrentPage(totalPages, currentPage);
        pagination.setCurrentPage(validatedCurrentPage);
        
        int start = calculateStart(validatedCurrentPage, itemsPerPage);
        pagination.setStart(start);
        
        int end = calculateEnd(start, itemsPerPage, totalItems);
        pagination.setEnd(end);
        
        return pagination;
    }

    private int calculateTotalPages(int totalItems, int itemsPerPage) {
        return (totalItems + itemsPerPage - 1) / itemsPerPage;
    }

    private int validateCurrentPage(int totalPages, int currentPage) {
        return Math.max(1, Math.min(currentPage, totalPages));
    }

    private int calculateStart(int currentPage, int itemsPerPage) {
        return ((currentPage - 1) * itemsPerPage) + 1;
    }

    private int calculateEnd(int start, int itemsPerPage, int totalItems) {
        return Math.min(start + itemsPerPage - 1, totalItems);
    }
}
