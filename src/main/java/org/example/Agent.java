package org.example;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent extends Person {

    public String phoneNumber;
    public double salary;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        do {
            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("The phone number needs to be in xxx-xxx-xxxx format");
            } else {
                this.phoneNumber = phoneNumber;
                break;
            }
        } while (true);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\d{3}-\\d{3}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 10_000) {
            System.out.println("You're salary is not enough! Why you broke?");
            this.salary = 0;
        } else {
            this.salary = salary;
        }
    }

    public double yearlyBonus(int tripsBooked) {
        return tripsBooked * .002;
    }
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("$####.##");
        return "Agent Information:\nName: " + super.firstName + " " + super.lastName +
                "\nNationality: " + super.nationality +
                "\nAgent Phone Number: " + isValidPhoneNumber(getPhoneNumber()) +
                "\nAgent Salary: " + df.format(getSalary());
    }
}
