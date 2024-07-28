package com.anhvu.model;

public enum RedirectPath {
    MANAGER_PRODUCTS("/admin/manager_products"),
    MANAGER_ACCOUNTS("/admin/manager_accounts"),
    MANAGER_CATEGORY("/admin/manager_category"),
    MANAGER_REVIEW("/admin/manager_productReview"),
    MANAGER_CONTACT("/admin/manager_contact"),
    MANAGER_ORDERS("/admin/manager_orders");

    private final String path;

    RedirectPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
