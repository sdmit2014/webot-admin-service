package com.wecodee.SpringBootPractice.admin.constant;

public enum ResponseMessage {
	
	FAIL_TO_DELETE("Only Maker Can Delete The Record"),

	// LOGIN-MESSAGES
	LOGIN_SUCCESS("Login Successfull"),
	LOGIN_FAILED("Login Failed"),
	INVALID_CREDENTIALS("Invalid Credentials"),
	USER_NOT_FOUND("User Id not found"),
	USER_LOCKED("User is Locked"),
	USER_UNAPPROVED("User is not approved. Login is restricted"),
	USER_LOGIN_DISABLED("User Id is disabled. Please contact your system administrator"),
	PASSWORD_UPDATE_SUCCESS("Password Updated Successfully"),
	PASSWORD_UPDATE_FAILED("Failed to Update Password"),
	PASSWORD_MISMATCH("Existing Password is wrong"),
	INVALID_USER("Invalid User Id"),
	RESET_PASSWORD_SUCCESS("Auto Generated Password sent to Registered Email Id"),
	RESET_PASSWORD_FAILED("Failed to Reset Password"),
	USER_EMAIL_MISMATCH("User Id and Registered Email Id does not match"),
	NEW_PASS_AND_CONFRM_PASS("New Password and Confirm Password should be same"),
	BACKDATE_NOT_ALLOWED("Notification Date and Time cannot be backdated"),
	ADMIN_UNLOCKED_USER_SUCCESS("User unlocked successfully"),

	// USER
	USER_CREATED("User Created Successfully"),
	USER_UPDATED("User Updated Successfully"),
	USER_APPROVED("User is Approved Successfully"),
	USER_DELETED("User Deleted Successfully"),
	USER_REJECTED("User is Rejected Successfully"),
	USER_DISABLED("User Disabled Successfully"),
	USER_UNLOCKED_SUCCESS("User Unlocked Successfully by default it will 'password' "),
	USER_UNLOCKED_FAILED("Failed to Unlock the User"),
	USER_APPROVED_RECORDS("User Approved Records"),
	USER_UNAPPROVED_RECORDS("User Unapproved Records"),

	// ROLE
	ROLE_CREATED("Role Created Successfully"),
	ROLE_UPDATED("Role Updated Successfully"),
	ROLE_APPROVED("Role is Approved Successfully"),
	ROLE_DELETED("Role Deleted Successfully"),
	ROLE_REJECTED("Role is Rejected Successfully"),
	ROLE_APPROVED_RECORDS("Role Approved Records"),
	ROLE_UNAPPROVED_RECORDS("Role Unapproved Records"),

	// EMAIL-CONFIGURATION
	EMAIL_CONFIG_UPDATED("Email Configuration is Updated Successfully"),
	EMAIL_CONFIG_APPROVED("Email Configuration Changes Approved Successfully"),
	EMAIL_CONFIG_REJECTED("Email Configuration Changes Rejected Successfully"),
	EMAIL_SENT("Email Sent Successfully"),

	// SMS-CONFIGURATION
	SMS_MODIFIED("SMS Configuration is Modified Successfully"),
	SMSCONFIG_APPROVED("SMS Configuration Changes Approved Successfully"),
	SMSCONFIG_REJECTED("SMS Configuration Changes Rejected Successfully"),
	
	// SECURITY-PARAMETER
	SECURITY_PARAMETER_APPROVED("Security Parameter Approved Successfully"),
	SECURITY_PARAMETER_REJECTED("Security Parameter Rejected Successfully"),
	SECURITY_PARAMETER_UPDATED("Security Parameter Updated Successfully"),
	
	// NOTIFICATION-CONTROL
	NOTIFICATION_CONTROL_CREATED("Notification Control Created Successfully"),
	NOTIFICATION_CONTROL_UPDATED("Notification Control Updated Successfully"),
	NOTIFICATION_CONTROL_APPROVED_RECORDS("Notification Control Approved records"),
	NOTIFICATION_CONTROL_UNAPPROVED_RECORDS("Notification Control Unapproved records"),
	NOTIFICATION_CONTROL_APPROVED("Notification Control Approved Successfully"),
	NOTIFICATION_CONTROL_REJECTED("Notification Control Rejected Successfully"),
	NOTIFICATION_CONTROL_DELETED("Notification Control Deleted Successfully"),
	
	// NOTIFICATION-TEMPLATE
	NOTIFICATION_TEMPLATE_CREATED("Notification Template Created Successfully"),
	NOTIFICATION_TEMPLATE_UPDATED("Notification Template Updated Successfully"),
	NOTIFICATION_TEMPLATE_APPROVED_RECORDS("Notification Template Approved records"),
	NOTIFICATION_TEMPLATE_UNAPPROVED_RECORDS("Notification Template Unapproved records"),
	NOTIFICATION_TEMPLATE_APPROVED("Notification Template Approved Successfully"),
	NOTIFICATION_TEMPLATE_REJECTED("Notification Template Rejected Successfully"),
	NOTIFICATION_TEMPLATE_DELETED("Notification Template Deleted Successfully"),

	// EMAIL-TEMPLATE
	EMAIL_TEMPLATE_CREATED("Email Template Created Successfully"),
	EMAIL_TEMPLATE_UPDATED("Email Template Updated Successfully"),
	EMAIL_TEMPLATE_APPROVED("Email Template Approved Successfully"),
	EMAIL_TEMPLATE_REJECTED("Email Template Rejected Successfully"),
	EMAIL_TEMPLATE_DISABLED("Email Template Disabled Successfully"),
	EMAIL_TEMPLATE_DELETED("Email Template Deleted Successfully"),
	
	//CUSTOMER-PROFILE
	CUSTOMER_UNAPPROVED_RECORDS("Customer Profile Unapproved Records"),
	CUSTOMER_APPROVED_RECORDS("Customer Profile Approved Records"),
	CUSTOMER_CREATED("Customer Profile Created Successfully"),
	CUSTOMER_REJECTED("Customer Profile Rejected Successfully"),
	CUSTOMER_UPDATED("Customer Profile Updated Successfully"),
	CUSTOMER_APPROVED("Customer Profile Approved Successfully"),
	CUSTOMER_DELETED("Customer Profile Deleted Successfully"),
	
	// EMAIL-THEME
	EMAILTHEME_UNAPPROVED_RECORDS("Email Theme Unapproved Records"),
	EMAILTHEME_APPROVED_RECORDS("Email Theme Approved Records"),
	EMAILTHEME_CREATED("Email Theme Created Successfully"),
	EMAILTHEME_REJECTED("Email Theme Rejected Successfully"),
	EMAILTHEME_UPDATED("Email Theme Updated Successfully"),
	EMAILTHEME_APPROVED("Email Theme Approved Successfully"),
	EMAILTHEME_DELETED("Email Theme Deleted Successfully"),

	// BNCUSTOMER-ALERT
	CUSTOMER_NOTIFICATION_CREATED("Customer Notification Created Successfully"),
	CUSTOMER_NOTIFICATION_UPDATED("Customer Notification Updated Successfully"),
	CUSTOMER_NOTIFICATION_APPROVED("Customer Notification Approved Successfully"),
	CUSTOMER_NOTIFICATION_REJECTED("Customer Notification Rejected Successfully"),
	CUSTOMER_NOTIFICATION_DELETED("Customer Notification Deleted Successfully"),
	CUSTOMER_NOTIFICATION_UNAPPROVED_RECORDS("Customer Notification Unapproved Records"),
	CUSTOMER_NOTIFICATION_APPROVED_RECORDS("Customer Notification Approved Records"),
	CUSTOMER_NOTIFICATION_ALREADY_EXISTS("Customer Notification Already exists"),
	CUSTOMER_NOTIFICATION_CUSTOMER_TYPE_AND_SEGEMENT_ALREADY_EXISTS("Customer Type and Customer Segement Already exists"),
	

	// JOB-MONITOR
	JOB_STARTED("Job Started Successfully"),
	JOB_STOPPED("Job Stopped Successfully"),
	
	//ATM MSG-CONFIGURATION
	ATM_ALREADY_EXISTS("ATM Code already exists"),
	ATM_CREATED("ATM Created Successfully"),
	ATM_DELETED("ATM Deleted Successfully"),
	ATM_UPDATED("ATM Updated Successfully"),
	ATM_APPROVED("ATM is Approved Successfully"),
	ATM_REJECTED("ATM is Rejected Successfully"),
	ATM_UNAPPROVED_RECORDS("ATM Unapproved Records"),
	ATM_APPROVED_RECORDS("ATM Approved Records"),
	ATM_ENABLED_SUCCESS("ATM Enabled Successfully"),
	ATM_DISABLED_SUCCESS("ATM Disabled Successfully"),
	
	//BRANCH MSG-CONFIGURATION
	BRANCH_ALREADY_EXISTS("Branch Code already exists"),
	BRANCH_CREATED("Branch Created Successfully"),
	BRANCH_DELETED("Branch Deleted Successfully"),
	BRANCH_UPDATED("Branch Updated Successfully"),
	BRANCH_DISABLED_SUCCESS("Branch Disabled Successfully"),
	BRANCH_ENABLED_SUCCESS("Branch Enabled Successfully"),
	BRANCH_APPROVED("Branch is Approved Successfully"),
	BRANCH_REJECTED("Branch is Rejected Successfully"),
	BRANCH_UNAPPROVED_RECORDS("Branch Unapproved Records"),
	BRANCH_APPROVED_RECORDS("Branch Approved Records"),



     //PROCESS FLOW MSG-CONFIGURATION
	 PROCESS_FLOW_EXISTS("Process flow already exists"),
     PROCESS_FLOW_CREATED("Process flow Created Successfully"),
     PROCESS_FLOW_APPROVED("Process flow is Approved Successfully"),
     PROCESS_FLOW_REJECTED("Process flow is Rejected Successfully"),
     PROCESS_FLOW_UPDATED("Process flow Updated Successfully"),
     PROCESS_FLOW_DELETED("Process flow Deleted Successfully"),
     PROCESS_FLOW_APPROVED_RECORDS("Process flow Approved Records"),
     PROCESS_FLOW_UNAPPROVED_RECORDS("Process flow Unapproved Records"),

	// COMMON-MESSAGES
	OPERATION_SUCCESS("Operation Success"),
	OPERATION_FAILED("Operation Failed"),
	USER_ALREADY_EXISTS("Already exists"),
	ROLE_ALREADY_EXISTS("Role already exists"),
	CUSTOMER_ALREADY_EXISTS("Customer already exists"),
	CUSTOMER_JOURNEY_EXISTS("Customer journey already exists"),
	CUSTOMER_JOURNEY_ALREADY_MAINTAINED("Customer Type And Customer Segment journey already maintained"),
	PREFERRED_TIME_BACK_DATED("Preferred time should not be back dated"),
	GROUP_ALREADY_EXISTS("Group already exists"),
	EMAIL_TEMPLATE_ALREADY_EXISTS("Email template already exists"),
	NOTIFICATION_CONTROL_ALREADY_EXISTS("Notification control already exists"),
	SPECIAL_CHARECTERS_NOT_ALLOWED("Only Characters are allowed for notification type"),
	NOTIFICATION_CONTROL_BASE_NOT_ALLOWED("Base Notification Category cannot be created"),
	SPECIAL_CHARACTERS_NOT_ALLOWED("Space and special characters not allowed in Notification Type"),
	NOTIFICATION_TEMPLATE_ALREADY_EXISTS("Notification template already exists"),
	NOTIFICATION_TYPE_ALREADY_MAINTAINED("The Given Notification Type Already Maintained The Template"),
	INVALID_TAGS("Email body is having invalid tags"),
    MARKETING_INVALID_TAGS("Customer related tags cannot be used since customer data is not available"),
	SMS_TEMPLATE_ALREADY_EXISTS("SMS template already exists"),
	EMAIL_THEME_ALREADY_EXISTS("Email theme already exists"),
	NO_MATCHING_TAG("Email Template body is having invalid tags and tag should be [[EMAIL_CONTENT]]"),
    EXTRA_TAGS("Email Template body should not contain extra tag apart from [[EMAIL_CONTENT]] "),
	BULK_NOTIFICATION_ALREADY_EXISTS("Bulk notification already exists"),

	FETCH_SUCCESS("Records Fetched Successfully"),
	NO_RECORDS_FOUND("No records found"),
	FETCH_FAILED("Failed to fetch records"),

	DELETE_SUCCESS("Record deleted successfully"),
	DELETE_FAILED("Record cannot be deleted"),
	REJECTED("Rejected"),

	CREATE_FAILED("Failed to Save the record"),
	UPDATE_FAILED("Failed to Update the record"),
	UPDATE_RECORD_NOT_FOUND("Record not found to update"),

	//JOB-CONFIGURATION
	JOBCONFIG_MODIFIED("Job Configuration Modified successfully"),

    //DISABLE MESSAGES
    DISABLED_FAILED("Sorry!!! we are unable to disabled the selected user"),
    USER_DISABLED_SUCCESS("User Disabled Successfully"),
    MAKER_CANNOT_APPROVE("Maker cannot approve the record"),
	MAKER_CANNOT_REJECT("Maker cannot reject the record"),
    USER_ENABLED_SUCCESS("User Enabled Successfully"),


	// NOTIFICATION-GROUP
	NOTIFICATION_GROUP_CREATED("Notification Group Created Successfully"),
	NOTIFICATION_GROUP_UPDATED("Notification Group Updated Successfully"),
	NOTIFICATION_GROUP_APPROVED_RECORDS("Notification Group Approved records"),
	NOTIFICATION_GROUP_UNAPPROVED_RECORDS("Notification Group Unapproved records"),
	NOTIFICATION_GROUP_APPROVED("Notification Group Approved Successfully"),
	NOTIFICATION_GROUP_REJECTED("Notification Group Rejected Successfully"),
	NOTIFICATION_GROUP_DELETED("Notification Group Deleted Successfully"),

	//CUSTOMER-PROFILE
	CUSTOMER_JOURNEY_UNAPPROVED_RECORDS("Customer Journey Unapproved Records"),
	CUSTOMER_JOURNEY_APPROVED_RECORDS("Customer Journey Approved Records"),
	CUSTOMER_JOURNEY_CREATED("Customer Journey Created Successfully"),
	CUSTOMER_JOURNEY_REJECTED("Customer Journey Rejected Successfully"),
	CUSTOMER_JOURNEY_UPDATED("Customer Journey Updated Successfully"),
	CUSTOMER_JOURNEY_APPROVED("Customer Journey Approved Successfully"),
	CUSTOMER_JOURNEY_DELETED("Customer Journey Deleted Successfully"),

	// BULK-NOTIFICATION
	BULK_NOTIFICATION_CREATED("Bulk Notification Created Successfully"),
	BULK_NOTIFICATION_APPROVED("Bulk Notification is Approved Successfully"),
	BULK_NOTIFICATION_REJECTED("Bulk Notification is Rejected Successfully"),
	BULK_NOTIFICATION_UPDATED("Bulk Notification Updated Successfully"),
	BULK_NOTIFICATION_DELETED("Bulk Notification Deleted Successfully"),
	BULK_NOTIFICATION__APPROVED_RECORDS("Bulk Notification Approved Records"),
	BULK_NOTIFICATION_UNAPPROVED_RECORDS("Bulk Notification Unapproved Records"),

	SPECIALDAY_CREATED("Special Day Created Successfully"),
	SPECIALDAY_UPDATED("Special Day Updated Successfully"),
	SPECIALDAY_APPROVED("Special Day Approved Successfully"),
	SPECIALDAY_REJECTED("Special Day Rejected Successfully"),
	SPECIALDAY_DELETED("Special Day Deleted Successfully"),
	SPECIALDAY_UNAPPROVED_RECORDS("Special Day Unapproved Records"),
	SPECIALDAY_APPROVED_RECORDS("Special Day Approved Records"),
	SPECIALDAY_NAME_AND_DATE_RECORDS("Special Day : Name and Date already maintained in the record"),
	SPECIALDAY_YEAR_ALREDY_EXISTS("Special day calendar already maintained for year"),
	SPECIALDAY_YEAR_VALIDATE("Special day calender is maintained only for");

	private String message;
	public String getMessage() {
		return message;
	}
	private ResponseMessage(String message) {
		this.message = message;
	}


}
