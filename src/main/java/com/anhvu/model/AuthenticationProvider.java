package com.anhvu.model;

public enum AuthenticationProvider {
	LOCAL("LOCAL"),GOOGLE("GOOGLE");
	
	private final String value;

	AuthenticationProvider(String value) {
        this.value = value;
    }

    public AuthenticationProvider getValue() {
        return AuthenticationProvider.valueOf(this.value);
    }
}
