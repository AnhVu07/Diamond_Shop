/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.service;

import com.anhvu.spring.dto.Pagination;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PaginationService {

    public Pagination getInfoPagination(int dataPage, int currentPage, int limit) {
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        int total = setInfoTotalPage(dataPage, limit);
        pagination.setTotalPage(total);
        int current = checkCurrentPage(total, currentPage);
        pagination.setCurrentPage(current);
        int start = setStart(pagination.getCurrentPage(), limit);
        pagination.setStart(start);
        int end = setEnd(start, limit, dataPage);
        pagination.setEnd(end);
        return pagination;
    }
    
    

    private int setInfoTotalPage(int dataPage, int limit) {
        int totalPagee = 0;
        totalPagee = dataPage / limit;
        totalPagee = totalPagee * limit < dataPage ? totalPagee +1 : totalPagee;
        return totalPagee;
    }

    private int checkCurrentPage(int total, int currentPage) {
        if (currentPage < 1) {
            return 1;
        }

        if (currentPage > total) {
            return total;
        }
        return currentPage;
    }

    private int setStart(int currentPage, int limit) {
        return ((currentPage - 1) * limit) + 1;
    }

    private int setEnd(int start, int limit, int dataPage) {
        return (start + limit < dataPage) ? (start + limit) - 1 : dataPage;
    }
}
