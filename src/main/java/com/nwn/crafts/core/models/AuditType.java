package com.nwn.crafts.core.models;

public enum AuditType {

    LOGIN("Login", "Login of the User: %s", null),
    CREATE_CLIENT("Create new Client", "Client '%s' is successfully created", "Failed to create Client '%s'"),
    CREATE_CRAFTSMAN("Create new Craftsman", "Craftsman '%s' is successfully created", "Failed to create Craftsman '%s'"),
    CREATE_USER("Create new User", "User '%s' is successfully created", "Failed to create user '%s'");

    private final String action;
    private final String description;
    private final String descriptionKO;

    AuditType(String action, String description, String descriptionKO) {
        this.action = action;
        this.description = description;
        this.descriptionKO = descriptionKO;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionKO() {
        return descriptionKO;
    }
}
