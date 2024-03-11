package com.GrowSkill.exception;
public class ResourceNotFoundException extends Exception {

    private String resourceType;
    private String resourceId;

    public ResourceNotFoundException(String resourceType, String resourceId) {
        super("Resource not found: " + resourceType + " with ID " + resourceId);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    // Getters and setters for resourceType and resourceId
}
