package org.example;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String firstName;
    protected String lastName;
    public Nationality nationality;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }
    Trip trip;
    @Override
    public String toString(){
        return "Guests Information:\nName: " + getFirstName() + " "+ getLastName()+
                "\nNationality: " + getNationality().getNationalityString();
    }
}