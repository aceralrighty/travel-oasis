package org.example;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class Trip implements Comparable<Trip>, Serializable {
    private Agent agent = new Agent();
    private ArrayList<Integer> guests;
    private LocalDate endDate;
    private LocalDate startDate;
    private double cost;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ArrayList<Integer> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Integer> guests) {
        if (guests.isEmpty()) {
            System.out.println("You need to have at least 1 guest to be booked for a trip");
            guests.equals(1);
        } else {
            this.guests = guests;

        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(getStartDate())) {
            System.out.println("You cannot make a trip where your end date is before your start date. That doesn't make any sense!");
        } else {
            this.endDate = endDate;

        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        if (startDate.isEqual(today)) {
            System.out.println("You can't make a reservation the same day, that's not very cash money of you");
            this.startDate = today.minusDays(-1);
        } else {
            this.startDate = startDate;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 1) {
            System.out.println("We don't give out freebies!");
            this.cost = 1400;
        } else {
            this.cost = cost;
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$####.##");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return "\nTrip Cost: " + df.format(getCost()) +
                "\n Trip Start: " + dateTimeFormatter.format(getStartDate()) +
                "\n Trip End: " + dateTimeFormatter.format(getEndDate());
    }

    @Override
    public int compareTo(Trip trip) {
        if (trip.startDate.isAfter(getEndDate())){
            return -1;
        } else if(trip.startDate.isEqual(getEndDate())){
            return 0;
        } else {
            return 1;
        }
    }
}
