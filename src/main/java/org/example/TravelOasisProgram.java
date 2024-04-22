package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TravelOasisProgram {
    public static void main(String[] args) {


    }

    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Trip> tripArrayList = new ArrayList<>();

    public void OptionSelector() {
        System.out.println("1. Add a trip");
        System.out.println("2. Edit any part of an existing trip");
        System.out.println("3. To see all trips");
        System.out.println("4. To exit");
        String ChooseOption = scanner.nextLine();
        switch (ChooseOption) {
            case "1":
                AgentInputs();
                break;
            case "2":
                break;
            case "3":
                ThirdOptionSelected();
                break;
            case "4":
                serializeFile();
                System.out.println("Serializing file\nFile Saved!");
                System.exit(0);
                break;
        }
    }

    public static void ThirdOptionSelected() {
        ArrayList<Trip> arrayListCopy = (ArrayList<Trip>) tripArrayList.clone();
        Collections.sort(arrayListCopy);
        for (Trip trip : arrayListCopy
        ) {
            System.out.println(trip);
        }

    }

    public static String GrabUserInputString(String display) {
        do {
            System.out.println(display);
            if (display == null || display.isBlank()) {
                System.out.println("You must enter a something in this field");
                continue;
            }
            break;
        } while (true);
        return scanner.nextLine();
    }

    public void AgentInputs() {
        while (true) {
            Agent agent = new Agent();
            String aFirstName = GrabUserInputString("Enter the agent's first name: ");
            agent.setFirstName(aFirstName);
            String aLastName = GrabUserInputString("Enter the agent's last name: ");
            agent.setLastName(aLastName);
            String guestNationality = String.valueOf(NationalityValidator("Enter the guest's nationality: "));
            agent.setNationality(Nationality.valueOf(guestNationality));
            String phoneNumber = GrabUserInputString("Enter the agent's phone number in xxx-xxx-xxxx format: ");
            agent.setPhoneNumber(phoneNumber);
            double aSalary = GrabUserInputDouble("Enter the agents salary: ");
            agent.setSalary(aSalary);
            GuestInputs();
        }
    }

    public void GuestInputs() {
        do {
            Guest guest = new Guest();
            String gFirstName = GrabUserInputString("Enter the guest's first name: ");
            guest.setFirstName(gFirstName);
            String gLastName = GrabUserInputString("Enter the guest's last name: ");
            guest.setLastName(gLastName);
            String guestNationality = String.valueOf(NationalityValidator("Enter the guest's nationality: "));
            guest.setNationality(Nationality.valueOf(guestNationality));
            String haveMealPlan = String.valueOf(Boolean.parseBoolean(GrabUserInputString("Does guest have meal plan?\nenter 'yes' or 'no' ")));
            guest.setMealPlan(haveMealPlan.equalsIgnoreCase("yes"));
            String hasPaid = GrabUserInputString("Has the guest paid?\nenter 'yes' or 'no'");
            guest.setPaid(hasPaid.equalsIgnoreCase("yes"));
            String moreGuests = GrabUserInputString("Do you want to enter another guest? Enter 'y' or 'n'");
            if (moreGuests.equalsIgnoreCase("n"))
                break;
        } while (true);
        TripCostAndDates();
    }

    public void TripCostAndDates() {
        while (true) {
            Trip trip = new Trip();
            double tripCost = GrabUserInputDouble("Trip Cost: ");
            trip.setCost(tripCost);
            String tripStart = GrabUserInputString("Trip Start (MM-dd-yyy) format: ");
            trip.setStartDate(LocalDate.parse(tripStart));
            String tripEnd = GrabUserInputString("Trip End (MM-dd-yyy) format: ");
            trip.setEndDate(LocalDate.parse(tripEnd));
            tripArrayList.add(trip);
        }

    }

    public static Nationality NationalityValidator(String userInput) {
        while (true) {
            System.out.println(userInput);
            for (Nationality n : Nationality.values()
            ) {
                if (userInput.equalsIgnoreCase(String.valueOf(n)) || userInput.equalsIgnoreCase(n.getNationalityString())) {
                    return n;
                }
            }
        }
    }

    public static double GrabUserInputDouble(String display) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(display);
        return Double.parseDouble(scanner.nextLine());
    }

    public static void serializeFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("trips.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tripArrayList);
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void deserializeFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("trips.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tripArrayList = (ArrayList<Trip>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("File is not found " + e);
        }
    }
}
