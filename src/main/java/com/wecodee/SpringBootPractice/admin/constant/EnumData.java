package com.wecodee.SpringBootPractice.admin.constant;

public enum EnumData {
	
	 ACTIVE("A"),
	    INACTIVE("I"),
	    LOCKED("L"),
	    SENT("S"),
	    REJECT("R"),
	    FAILED("F"),
	    DISABLED("D"),
	    NO_OF_ATTEMPTS("0"),
	    NEW("NEW"),
	    NOTIFI_CATEG_CUSTOM("C"),
	    ERROR("E"),
	    SUCCESS("S"),
	    GROUP_TYPE_UPLOAD("upload"),
	    FIXED("fixed"),
	    RULE_BASED("rule"),

	    //PROCESS STATUS
	    INITIATED("I"),
	    WORK_IN_PROGRESS("W"),
	    COMPLETED("C"),
	    PROCESSING("P"),

	    // CHECKING ACTIONS
	    NEW_ACTION("NEW"),
	    UPDATE_ACTION("UPDATE"),
	    APPROVE_ACTION("APPROVE"),
	    REJECT_ACTION("REJECT"),
	    VIEW_ACTION("VIEW"),
	    DELETE_ACTION("DELETE"),
	    ENABLE_ACTION("ENABLE"),
	    DISABLE_ACTION("DISABLE"),
	    LOGIN("LOGIN"),

	    //CHECKING ACCESS
	    CREATE_ACCESS("CREATE"),
	    APPROVE_ACCESS("APPROVE"),
	    VIEW_ACCESS("VIEW"),


	    DISABLED_RECORD("DISABLED"),
	    ENABLED_RECORD("ENABLED"),

	    //email and sms template enum
	    EMAILCONFIG("EMAILCONFIG"),
	    SMSCONFIG("SMSCONFIG"),
	    RESETPASSWORD("RESETPASSWORD"),
	    CUSTOMER("CUSTOMER"),
	    EMAIL_BODY_KEY("{{email_body}}"),
	    EMAIL_CONTENT("[[EMAIL_CONTENT]]"),
	    DEFAULTTEMPLATE("DEFAULTTEMPLATE"),
	    EMAILRETRYCOUNT("EMAIL_RETRY_COUNT"),
	    CUSTOMERJOURNEY("CUSTOMERJOURNEY"),
	    NOTIFICATION_TYPE("NOTIFICATION_TYPE"),

	    //role type
	    ROLE_TYPE_APPROVER("APPROVER"),
	    ROLE_TYPE_REQUESTOR("REQUESTOR"),
	    ROLE_TYPE_ADMINISTRTOR("ADMIN"),

	    //security parameter
	    SECURITY_PARAMETER("SECURITYPARAMETER"),
	    CUSTOMERPROFILE("CUSTOMERPROFILE"),
	    GROUP("GROUP"),

	    //EMAIL
	    EMAIL("EMAIL"),
	    SMS("SMS"),

	    //DIRECTION
	    ASCENDING("ASC");

	    private String name;

	    private EnumData(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

}
