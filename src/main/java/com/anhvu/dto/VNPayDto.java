package com.anhvu.dto;

import lombok.Builder;

public class VNPayDto {

	@Builder
	public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
