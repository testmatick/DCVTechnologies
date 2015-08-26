package com.autotests.testdata;


public class SearchData {

    private String organizationName;
    private String organizationType;
    private boolean excludeOrganizationType;

    public SearchData() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public boolean isExcludeOrganizationType() {
        return excludeOrganizationType;
    }

    public void setExcludeOrganizationType(boolean excludeOrganizationType) {
        this.excludeOrganizationType = excludeOrganizationType;
    }
}
