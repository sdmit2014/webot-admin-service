package com.wecodee.SpringBootPractice.admin.constant;

public enum Gender {
	
	ALBANIAN_MALE_GEN("Z"),
    ALBANIAN_FEMALE_GEN("Znj"),
    ENGLISH_MALE_GEN("Mr"),
    ENGLISH_FEMALE_GEN("Mrs")
    ;

    private String message;
    public String getMessage() {
        return message;
    }

    Gender(String message) {
        this.message = message;
    }

}
