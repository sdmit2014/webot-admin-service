package com.wecodee.SpringBootPractice.admin.constant;

public enum TemplateTagName {

	 // Customer Tags
    CUSTOMER_NUMBER("[[CUSTOMER_NUMBER]]"),
    CUSTOMER_NAME("[[CUSTOMER_NAME]]"),
    TITLE("[[TITLE]]"),
    FIRST_NAME("[[FIRST_NAME]]"),
    LAST_NAME("[[LAST_NAME]]"),
    EMAIL_IDENTIFIER_TAG("[[EMAIL_IDENTIFIER_TAG]]"),


    // Account Tags
    ACCOUNT_NUMBER("[[ACCOUNT_NUMBER]]"),
    ACCOUNT_NUMBER_MASKED("[[ACCOUNT_NUMBER_MASKED]]"),
    ACCOUNT_CLASS("[[ACCOUNT_CLASS]]"),
    ACCOUNT_CCY("[[ACCOUNT_CCY]]"),
    ACCOUNT_AVL_BALANCE("[[ACCOUNT_AVL_BALANCE]]"),
    ACCOUNT_MIN_BALANCE("[[ACCOUNT_MIN_BALANCE]]"),
    ACCOUNT_IBAN("[[ACCOUNT_IBAN]]"),
    ACCOUNT_OPENED_DATE("[[ACCOUNT_OPENED_DATE]]"),

    // Loan Tags
    LOAN_NUMBER("[[LOAN_NUMBER]]"),
    LOAN_BRANCH("[[LOAN_BRANCH]]"),
    LOAN_CCY("[[LOAN_CCY]]"),
    LOAN_AMOUNT("[[LOAN_AMOUNT]]"),
    LOAN_START_DATE("[[LOAN_START_DATE]]"),
    LOAN_MATURITY_DATE("[[LOAN_MATURITY_DATE]]"),
    LOAN_INSTALLMENT_AMOUNT("[[LOAN_INSTALLMENT_AMOUNT]]"),
    PRINCIPAL_INCREASE_DATE("[[PRINCIPAL_INCREASE_DATE]]"),
    PRINCIPAL_INCREASE_AMOUNT("[[PRINCIPAL_INCREASE_AMOUNT]]"),

    // Other Tags
    SWIFT("[[SWIFT]]");

    private String message;
    public String getMessage() {
        return message;
    }

    TemplateTagName(String message) {
        this.message = message;
    }
}
