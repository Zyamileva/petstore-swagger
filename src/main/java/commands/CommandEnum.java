package commands;

public enum CommandEnum {

    PET_TABLE_QUERIES("Pet table queries"),
    ORDER_TABLE_QUERIES("Order table queries"),
    USER_TABLE_QUERIES("User table queries"),
    FIND_BY_STATUS("Find by Status"),
    UPLOAD_IMAGE("Upload image"),
    FIND_BY_ID("Find by ID"),
    DELETE_BY_ID("Delete pet by ID"),
    ADD_PET("Add pet"),
    UPDATE_PET("Update pet"),
    UPDATE_PET_WITH_FORM_DATA("Update pet with form data"),
    ADD_STORE_ORDER("Add store order"),
    FIND_ORDER_BY_ID("Find order by ID"),
    DELETE_ORDER_BY_ID("Delete order by id"),
    INVENTORY("Inventory"),
    CREATE_WITH_ARRAY("Create with array"),
    CREATE_WITH_LIST("Create with list"),
    FIND_USER_BY_USER_NAME("Find User by user`s name"),
    UPDATE_USER_BY_USER_NAME("Update User by user`s name"),
    ADD_USER("Add User"),
    DELETE_USER("Delete User"),
    LOGIN_USER("Login User"),
    LOGOUT_USER("Logout User"),
    MAIN_COMMANDS("Show main commands"),

    EXIT("Exit");

    private final String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}