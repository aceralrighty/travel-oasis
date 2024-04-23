package org.example;

public enum Nationality {
    ISR("Israeli"),
    TUR("Turkish"),
    GER("German"),
    IRH("Irish"),
    SWD("Swede");
    private String nationalityString;

    Nationality(String nationalityString){
        this.nationalityString = nationalityString;
    }

    public String getNationalityString() {
        return nationalityString;
    }
}
