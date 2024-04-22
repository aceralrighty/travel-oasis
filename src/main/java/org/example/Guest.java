package org.example;

public class Guest extends Person {
    private boolean paid;
    private boolean mealPlan;

    public void setPaid(boolean paid) {
        boolean myFlag = true;
        do {
            if (!paid) {
                System.out.println("If you don't pay, no trip!");
                this.paid = false;
                continue;
            } else {
                this.paid = paid;
            }
            break;
        } while(myFlag);
    }

    public void setMealPlan(boolean mealPlan) {
        if (!mealPlan) {
            System.out.println("that's ok. you'll just starve, muahahah!");
        } else {
            this.mealPlan = mealPlan;
        }
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isMealPlan() {
        return mealPlan;
    }
    @Override
    public String toString() {
        return super.toString() + "\nPayment Status: " + isPaid() +
                "\nMeal Plan Selected: " + isMealPlan();
    }
}
