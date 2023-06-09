package com.wecodee.SpringBootPractice.admin.constant;

public enum EmailTemplateIdConst {
	
	CAAPPL_APPROVED_BRANCH_MAKER("CAAPPL_APPROVED_BRANCH_MAKER"),
    CAAPPL_APPROVED_BRANCH_APPROVER("CAAPPL_APPROVED_BRANCH_APPROVER"),
    CAAPPL_APPROVED_HO_APPROVER("CAAPPL_APPROVED_HO_APPROVER"),
    CAAPPL_CREATED_BRANCH_MAKER("CAAPPL_CREATED_BRANCH_MAKER"),
    CAAPPL_CREATED_BRANCH_APPROVER("CAAPPL_CREATED_BRANCH_APPROVER"),
    CAAPPL_MOD_REQ_IN_BR_OFFICE_EMAIL_BR_MAKER("CAAPPL_MOD_REQ_IN_BR_OFFICE_EMAIL_BR_MAKER"),
    CAAPPL_MOD_REQ_IN_BR_OFFICE_EMAIL_BR_APPROVER("CAAPPL_MOD_REQ_IN_BR_OFFICE_EMAIL_BR_APPROVER"),
    CAAPPL_REJECTED_IN_BR_OFFICE_EMAIL_BR_MAKER("CAAPPL_REJECTED_IN_BR_OFFICE_EMAIL_BR_MAKER"),
    CAAPPL_REJECTED_IN_BR_OFFICE_EMAIL_BR_APPROVER("CAAPPL_REJECTED_IN_BR_OFFICE_EMAIL_BR_APPROVER"),
    CAAPPL_APPROVED_IN_HO_EMAIL_BRANCH_MAKER("CAAPPL_APPROVED_IN_HO_EMAIL_BRANCH_MAKER"),
    CAAPPL_MOD_REQ_IN_HO_OFFICE_EMAIL_BR_MAKER("CAAPPL_MOD_REQ_IN_HO_OFFICE_EMAIL_BR_MAKER"),
    CAAPPL_REJECTED_IN_HO_EMAIL_BRANCH_MAKER("CAAPPL_REJECTED_IN_HO_EMAIL_BRANCH_MAKER"),
    CAAPPL_REASSIGN_BRANCH_APPROVER("CAAPPL_REASSIGN_BRANCH_APPROVER"),
    CAAPPL_REASSIGN_HEAD_APPROVER("CAAPPL_REASSIGN_HEAD_APPROVER"),
    CAAPPL_NEW_CUSTOMER_BR_MAKER("CAAPPL_NEW_CUSTOMER_BR_MAKER"),
    CAAPPL_NEW_CUSTOMER_APPROVED_BR_MAKER("CAAPPL_NEW_CUSTOMER_APPROVED_BR_MAKER")
    
    ;
	
	private String name;

    EmailTemplateIdConst(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
