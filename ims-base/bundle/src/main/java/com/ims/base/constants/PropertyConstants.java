package com.ims.base.constants;

public enum PropertyConstants {
	/** The paths property. */
    PATHS("paths"),
    /** The paths property has title. */
    TITLE("title"),
    /** The paths property has url. */
    URL("url");
    
    /**
     * value of the enum constant.
     */
	private final String value;

    /**
     * Custom Constructor.
     * 
     * @param value
     *            the value
     */
    private PropertyConstants(final String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * 
     * @return the value.
     */
    public String getValue() {
        return value;
    }
}
