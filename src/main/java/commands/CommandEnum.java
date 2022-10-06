package commands;

public enum CommandEnum {
    FIND_BY_STATUS("Find by Status"),
    UPLOAD_IMAGE("Upload image"),
    FIND_BY_ID("Find by ID"),
    DELETE_BY_ID("Delete pet by ID"),
    ADD_PET("Add pet"),
    UPDATE_PET("Update pet"),
    ADD_STORE_ORDER("Add store order"),
    FIND_ORDER_BY_ID("Find order by ID"),
    DELETE_ORDER_BY_ID("Delete order by id"),
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