package org.example;

public enum Nationality {
    ;
    private String nationalityString;
    ISR("Israeli");
    TUR("Turkish");
    GER("German");
    IRH("Irish");
    SWD("Swede");

    public void setNationalityString(String nationalityString) {
        this.nationalityString = nationalityString;
    }

    public String getNationalityString() {
        return nationalityString;
    }
}
