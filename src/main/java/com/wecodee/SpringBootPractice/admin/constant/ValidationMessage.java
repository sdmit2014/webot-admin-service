package com.wecodee.SpringBootPractice.admin.constant;

public enum ValidationMessage {
	
	// NOTIFICATION-TEMPLATE
    ACCESS_NOT_AVAILABLE_FOR_CREATE_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to create a new notification template"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to update a notification template"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to approve a notification template"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to reject a notification template"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to disable a notification template"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to delete a notification template"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_NOTIFICATION_TEMPLATE("Sorry! you do not have an access to view a notification template"),

    // CUSTOMER-PROFILE
    ACCESS_NOT_AVAILABLE_FOR_CREATE_CUSTOMER_PROFILE("Sorry! you do not have an access to create a new customer profile "),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_CUSTOMER_PROFILE("Sorry! you do not have an access to approve a customer profile"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_CUSTOMER_PROFILE("Sorry! you do not have an access to update a customer profile"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_CUSTOMER_PROFILE("Sorry! you do not have an access to disable the customer profile"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_CUSTOMER_PROFILE("Sorry! you do not have an access to reject the customer profile"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_CUSTOMER_PROFILE("Sorry! you do not have an access to delete the customer profile"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_CUSTOMER_PROFILE("Sorry! you do not have an access to view the customer profile"),

    // NOTIFY-GROUP
    ACCESS_NOT_AVAILABLE_FOR_CREATE_NOTIFY_GROUP("Sorry! you do not have an access to create a new notify group"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_NOTIFY_GROUP("Sorry! you do not have an access to approve a notify group"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_NOTIFY_GROUP("Sorry! you do not have an access to update a notify group"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_NOTIFY_GROUP("Sorry! you do not have an access to disable the notify group"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_NOTIFY_GROUP("Sorry! you do not have an access to reject the notify group"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_NOTIFY_GROUP("Sorry! you do not have an access to delete the notify group"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_NOTIFY_GROUP("Sorry! you do not have an access to view the notify group"),


    // USER
    ACCESS_NOT_AVAILABLE_FOR_CREATE_USER("Sorry! you do not have an access to create a new user"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_USER("Sorry! you do not have an access to approve"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_USER("Sorry! you do not have an access to update"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_USER("Sorry! you do not have an access to disable the user"),
    ACCESS_NOT_AVAIALABLE_FOR_ENABLE_USER("Sorry! you do not have an access to enable the user"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_USER("Sorry! you do not have an access to reject the user"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_USER("Sorry! you do not have an access to delete the user"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_USER("Sorry! you do not have an access to view the user"),

    // ROLE
    ACCESS_NOT_AVAILABLE_FOR_CREATE_ROLE("Sorry! you do not have an access to create a new role"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_ROLE("Sorry! you do not have an access to approve a role"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_ROLE("Sorry! you do not have an access to update a role"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_ROLE("Sorry! you do not have an access to disable the role"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_ROLE("Sorry! you do not have an access to reject the role"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_ROLE("Sorry! you do not have an access to delete the role"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_ROLE("Sorry! you do not have an access to view the role"),

    //ATM MSG-CONFIG
    ACCESS_NOT_AVAILABLE_FOR_DELETE_ATM("Sorry! you do not have an access to delete a new ATM"),
    ACCESS_NOT_AVAILABLE_FOR_CREATE_ATM("Sorry! you do not have an access to create a new ATM"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_ATM("Sorry! you do not have an access to view the ATM"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_ATM("Sorry! you do not have an access to update a ATM"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_ATM("Sorry! you do not have an access to disable the ATM"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_ATM("Sorry! you do not have an access to approve"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_ATM("Sorry! you do not have an access to reject the ATM"),
    ACCESS_NOT_AVAIALABLE_FOR_ENABLE_ATM("Sorry! you do not have an access to enable the ATM"),
    
    //BRANCH
    ACCESS_NOT_AVAILABLE_FOR_CREATE_BRANCH("Sorry! you do not have an access to create a new BRANCH"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_BRANCH("Sorry! you do not have an access to delete a new BRANCH"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_BRANCH("Sorry! you do not have an access to view the BRANCH"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_BRANCH("Sorry! you do not have an access to update a BRANCH"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_BRANCH("Sorry! you do not have an access to disable the BRANCH"),
    ACCESS_NOT_AVAIALABLE_FOR_ENABLE_BRANCH("Sorry! you do not have an access to enable the BRANCH"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_BRANCH("Sorry! you do not have an access to approve"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_BRANCH("Sorry! you do not have an access to reject the Branch"),


    //PROCESS FLOW

    ACCESS_NOT_AVAILABLE_FOR_CREATE_PROCESS_FLOW("Sorry! you do not have an access to create a new Process flow"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_PROCESS_FLOW("Sorry! you do not have an access to update a Process flow"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_PROCESS_FLOW("Sorry! you do not have an access to delete a new Process flow"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_PROCESS_FLOW("Sorry! you do not have an access to approve"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_PROCESS_FLOW("Sorry! you do not have an access to reject the Process flow"),
    
    // EMAIL-TEMPLATE
    ACCESS_NOT_AVAILABLE_FOR_CREATE_EMAILTEMPLATE("Sorry! you do not have an access to create a new email template"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_EMAILTEMPLATE("Sorry! you do not have an access to update a email template"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_EMAILTEMPLATE("Sorry! you do not have an access to approve a email template"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_EMAILTEMPLATE("Sorry! you do not have an access to reject a email template"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_EMAILTEMPLATE("Sorry! you do not have an access to disable a email template"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_EMAILTEMPLATE("Sorry! you do not have an access to delete a email template"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_EMAILTEMPLATE("Sorry! you do not have an access to view a email template"),
    
    // EMAIL-THEME
    ACCESS_NOT_AVAILABLE_FOR_CREATE_EMAILTHEME("Sorry! you do not have an access to create a new email theme"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_EMAILTHEME("Sorry! you do not have an access to update a new email theme"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_EMAILTHEME("Sorry! you do not have an access to approve a new email theme"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_EMAILTHEME("Sorry! you do not have an access to reject a new email theme"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_EMAILTHEME("Sorry! you do not have an access to delete a new email theme"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_EMAILTHEME("Sorry! you do not have an access to view a new email theme"),

    // SMS-TEMPLATE
    ACCESS_NOT_AVAIALABLE_FOR_CREATE_SMSTEMPLATE("Sorry! you do not have an access to create a new sms template"),
    ACCESS_NOT_AVAIALABLE_FOR_UPDATE_SMSTEMPLATE("Sorry! you do not have an access to update a sms template"),
    ACCESS_NOT_AVAIALABLE_FOR_APPROVE_SMSTEMPLATE("Sorry! you do not have an access to approve a sms template"),
    ACCESS_NOT_AVAIALABLE_FOR_REJECT_SMSTEMPLATE("Sorry! you do not have an access to reject a sms template"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_SMSTEMPLATE("Sorry! you do not have an access to disable a sms template"),
    ACCESS_NOT_AVAIALABLE_FOR_DELETE_SMSTEMPLATE("Sorry! you do not have an access to delete a sms template"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_SMSTEMPLATE("Sorry! you do not have an access to view a sms template"),

    // NOTIFICATION-CONTROL
    ACCESS_NOT_AVAILABLE_FOR_CREATE_NOTIFICATION_CONTROL("Sorry! you do not have an access to create a new notification control"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_NOTIFICATION_CONTROL("Sorry! you do not have an access to update a notification control"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_NOTIFICATION_CONTROL("Sorry! you do not have an access to approve a notification control"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_NOTIFICATION_CONTROL("Sorry! you do not have an access to reject a notification control"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_NOTIFICATION_CONTROL("Sorry! you do not have an access to disable a notification control"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_NOTIFICATION_CONTROL("Sorry! you do not have an access to delete a notification control"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_NOTIFICATION_CONTROL("Sorry! you do not have an access to view a notification control"),


    // EMAIL-CONFIG
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_EMAIL_CONFIG("Sorry! you do not have an access to update a email configuration"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_EMAIL_CONFIG("Sorry! you do not have an access to approve a email configuration"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_EMAIL_CONFIG("Sorry! you do not have an access to reject a email configuration"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_EMAIL_CONFIG("Sorry! you do not have an access to view a email configuration"),

    // SMS-CONFIG
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_SMS_CONFIG("Sorry! you do not have an access to update a sms configuration"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_SMS_CONFIG("Sorry! you do not have an access to approve a sms configuration"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_SMS_CONFIG("Sorry! you do not have an access to reject a sms configuration"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_SMS_CONFIG("Sorry! you do not have an access to view a sms configuration"),

    // SECURITY-PARAMETERS
    ACCESS_NOT_AVAIALABLE_FOR_CREATE_SECURITY_PARAMETER("Sorry! you do not have an access to create a new security parameter"),
    ACCESS_NOT_AVAIALABLE_FOR_UPDATE_SECURITY_PARAMETER("Sorry! you do not have an access to update a security parameter"),
    ACCESS_NOT_AVAIALABLE_FOR_APPROVE_SECURITY_PARAMETER("Sorry! you do not have an access to approve a security parameter"),
    ACCESS_NOT_AVAIALABLE_FOR_REJECT_SECURITY_PARAMETER("Sorry! you do not have an access to reject a security parameter"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_SECURITY_PARAMETER("Sorry! you do not have an access to disable a security parameter"),
    ACCESS_NOT_AVAIALABLE_FOR_DELETE_SECURITY_PARAMETER("Sorry! you do not have an access to delete a security parameter"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_SECURITY_PARAMETER("Sorry! you do not have an access to view a security parameter"),

    // LOOKUP
    ACCESS_NOT_AVAILABLE_FOR_VIEW_LOOKUP("Sorry! you do not have an access to view the user"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_LOOKUP("Sorry! you do not have an access to approve"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_LOOKUP("Sorry! you do not have an access to update"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_LOOKUP("Sorry! you do not have an access to reject the lookup"),

    // CUSTOMER-JOURNEY
    ACCESS_NOT_AVAILABLE_FOR_CREATE_CUSTOMER_JOURNEY("Sorry! you do not have an access to create a new customer journey "),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_CUSTOMER_JOURNEY("Sorry! you do not have an access to approve a customer journey"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_CUSTOMER_JOURNEY("Sorry! you do not have an access to update a customer journey"),
    ACCESS_NOT_AVAIALABLE_FOR_DISABLE_CUSTOMER_JOURNEY("Sorry! you do not have an access to disable the customer journey"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_CUSTOMER_JOURNEY("Sorry! you do not have an access to reject the customer journey"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_CUSTOMER_JOURNEY("Sorry! you do not have an access to delete the customer journey"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_CUSTOMER_JOURNEY("Sorry! you do not have an access to view the customer journey"),

	// BULK NOTIFICATION
    ACCESS_NOT_AVAILABLE_FOR_REJECT_BULK_NOTIFICATION("Sorry! you do not have an access to reject the bulk notification"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_BULK_NOTIFICATION("Sorry! you do not have an access to delete a bulk notification"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_BULK_NOTIFICATION("Sorry! you do not have an access to approve a bulk notification"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_BULK_NOTIFICATION("Sorry! you do not have an access to view the bulk notification"),
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_BULK_NOTIFICATION("Sorry! you do not have an access to update a new bulk notification"),
    ACCESS_NOT_AVAILABLE_FOR_CREATE_BULK_NOTIFICATION("Sorry! you do not have an access to create a new bulk notification"),
    CANNOT_UPDATE_PROCESS_STATE_BULK_NOTIFICATION("Bulk Notification cannot be updated when process status is completed or processing"),

    // CUSTOMER NOTIFICATION
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to update a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_CREATE_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to create a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to approve a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to reject a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to disable a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to delete a customer notification"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_CUSTOMER_NOTIFICATION("Sorry! you do not have an access to view a customer notification"),

    // SPECIAL DAY
    ACCESS_NOT_AVAILABLE_FOR_UPDATE_SPECIAL_DAY("Sorry! you do not have an access to update a special day"),
    ACCESS_NOT_AVAILABLE_FOR_CREATE_SPECIAL_DAY("Sorry! you do not have an access to create a special day"),
    ACCESS_NOT_AVAILABLE_FOR_APPROVE_SPECIAL_DAY("Sorry! you do not have an access to approve a special day"),
    ACCESS_NOT_AVAILABLE_FOR_REJECT_SPECIAL_DAY("Sorry! you do not have an access to reject a special day"),
    ACCESS_NOT_AVAILABLE_FOR_DISABLE_SPECIAL_DAY("Sorry! you do not have an access to disable a special day"),
    ACCESS_NOT_AVAILABLE_FOR_DELETE_SPECIAL_DAY("Sorry! you do not have an access to delete a special day"),
    ACCESS_NOT_AVAILABLE_FOR_VIEW_SPECIAL_DAY("Sorry! you do not have an access to view a special day");

    private String message;
    public String getMessage() {
        return message;
    }

    ValidationMessage(String message) {
        this.message = message;
    }

}
