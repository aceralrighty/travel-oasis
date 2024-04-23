package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

/**
 * I ran out of time and I have a C# project due on the 24th of april, I really tried to get as much as I could get done,
 * but I know it's not enough. I'll understand if I get a bad grade on this one. I really did try my best.
 * I didn't have enough time to make unit tests.
 */
public class TravelOasisProgram {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    static boolean isFileRead = false;

    public static void main(String[] args) {
        deserializeFile();
        while (!isFileRead) {
            OptionSelector();
        }

    }

    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Trip> tripArrayList = new ArrayList<>();


    public static void OptionSelector() {
        System.out.println(GREEN + "1. Add a trip");
        System.out.println(CYAN + "2. Edit any part of an existing trip");
        System.out.println(PURPLE + "3. To see all trips");
        System.out.println(RED + "4. To exit");
        String ChooseOption = scanner.nextLine();
        switch (ChooseOption) {
            case "1":
                AgentInputs();
                break;
            case "2":
                editTrip();
                break;
            case "3":
                ThirdOptionSelected();
                break;
            case "4":
                serializeFile();
                System.out.println(YELLOW + "Serializing file\nFile Saved!");
                System.exit(0);
                break;
        }
    }

    /**
     * to edit my trips
     */
    private static void editTrip() {
        boolean invalid;
        int input = 0;
        do {
            System.out.print("Enter Trip Number To Edit: ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input > 0 && input <= tripArrayList.size()) {
                    invalid = false;
                } else {
                    System.out.println("Invalid Input Not In Range For Trips");
                    invalid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input Is Not A Number");
                invalid = true;
            }
        } while (invalid);
        tripArrayList.remove(input - 1);
        tripArrayList.add(AgentInputs());
        System.out.println("Trip Updated!!\n");

    }

    /**
     * to display my trips
     */
    public static void ThirdOptionSelected() {
        int i = 0;
        tripArrayList.sort(Trip::compareTo);
        for (Trip trip : tripArrayList
        ) {
            System.out.printf("""
                          Trip %d
                    -------------------
                    %s
                    """, i + 1, trip.toString());
            i++;
        }

    }

    /**
     * method to grab user input and validate it. A special kind of scanner
     * @param display
     * @return
     */
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

    public static Trip AgentInputs() {
        Agent agent = new Agent();
        Trip trip = new Trip();
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
        trip.setAgent(agent);
        trip.setGuests(GuestInputs());
        trip.setCost(GrabUserInputDouble("Enter Cost Total Cost of Trip: "));
        GuestInputs();
        return trip;

    }


    public static Guest[] GuestInputs() {
        ArrayList<Guest> guestArrayList = new ArrayList<>();
        do {
            Guest guest = new Guest();
            String gFirstName = GrabUserInputString("Enter the guest's first name: ");
            guest.setFirstName(gFirstName);
            String gLastName = GrabUserInputString("Enter the guest's last name: ");
            guest.setLastName(gLastName);
            String guestNationality = String.valueOf(NationalityValidator("Enter the guest's nationality: "));
            guest.setNationality(Nationality.valueOf(guestNationality));
            if (Arrays.stream(Nationality.values()).toList().contains(guestNationality)) {
                System.out.println(Arrays.toString(Nationality.values()));
            }
            String haveMealPlan = String.valueOf(Boolean.parseBoolean(GrabUserInputString("Does guest have meal plan?\nenter 'yes' or 'no' ")));
            guest.setMealPlan(haveMealPlan.equalsIgnoreCase("yes"));
            String hasPaid = GrabUserInputString("Has the guest paid?\nenter 'yes' or 'no'");
            guest.setPaid(hasPaid.equalsIgnoreCase("yes"));
            String moreGuests = GrabUserInputString("Do you want to enter another guest? Enter 'y' or 'n'");
            guestArrayList.add(guest);
            if (moreGuests.equalsIgnoreCase("n"))
                break;
        } while (true);
        Guest[] guests = new Guest[guestArrayList.size()];
        for (int i = 0; i < guestArrayList.size(); i++) {
            guests[i] = guestArrayList.get(i);
        }
        TripCostAndDates();
        return guests;
    }

    public static void TripCostAndDates() {
        Trip trip = new Trip();
        double tripCost = GrabUserInputDouble("Trip Cost: ");
        trip.setCost(tripCost);
        String tripStart = GrabUserInputString("Trip Start (MM-dd-yyy) format: ");
        trip.setStartDate(LocalDate.parse(tripStart, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        String tripEnd = GrabUserInputString("Trip End (MM-dd-yyy) format: ");
        trip.setEndDate(LocalDate.parse(tripEnd, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        tripArrayList.add(trip);


    }

    /**
     * to validate my nationalities.
     * @param userInput
     * @return
     */
    public static Nationality NationalityValidator(String userInput) {
        while (true) {

            System.out.println(userInput);
            userInput = scanner.nextLine();
            for (Nationality n : Nationality.values()
            ) {
                if (userInput.equalsIgnoreCase(String.valueOf(n)) || userInput.equalsIgnoreCase(n.getNationalityString())) {

                    return n;
                }
            }

        }
    }
    /**
     * method to grab user input and validate it. A special kind of scanner
     * @param display
     * @return
     */
    public static double GrabUserInputDouble(String display) {
        System.out.println(display);
        return Double.parseDouble(scanner.nextLine());
    }

    /**
     * to serialize and deserialize
     */
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
