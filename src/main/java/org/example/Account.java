// Student name: Koichi Nakata (ID: knakata595)

package org.example;

import java.util.Date;

public abstract class Account {
    private static double annualInterestRate; // % scale
    private int id;
    private double balance;
    private final Date dateCreated;

    protected Account() { this(1000, 0.0); }
    protected Account(int id, double balance) {
        setId(id);
        setBalance(balance);
        dateCreated = new Date();
    }

    public static double getAnnualInterestRate() { return annualInterestRate; }
    public static void setAnnualInterestRate(double annualInterestRate) {
        while (true) {
            if (annualInterestRate >= 0) {
                Account.annualInterestRate = annualInterestRate;
                break;
            } else {
                System.out.println("Invalid annual rate.. The rate has been set to 3.00 %.");
                Account.annualInterestRate = 3.0;
            }
        }
    }

    public int getId() { return id; }
    private void setId(int id) {
        if (id >= 0) this.id = id;
        else {
            System.out.println("Invalid ID.. The id has been set to 1000.");
            this.id = 1000;
        }
    }

    public double getBalance() { return balance; }
    protected void setBalance(double balance) {
        if (balance >= 0) this.balance = balance;
        else {
            System.out.println("Invalid balance.. The balance has been set to 0.");
            this.balance = 0.0;
        }
    }

    public Date getDateCreated() { return dateCreated; }

    // Returns % scale
    public double getMonthlyInterestRate() {
        return getAnnualInterestRate() / 12;
    }

    public double getMonthlyInterest() {
        double monthlyInterestRate = getMonthlyInterestRate() / 100;
        return (int)(getBalance() * monthlyInterestRate * 100) / 100.0;
    }

    public void withdraw(double amount) {
        double remaining = getBalance() - amount;
        if (remaining >= 0) setBalance(remaining);
        else System.out.println("No sufficient balance to perform the withdraw.");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            System.out.printf("Deposited %.2f - New balance: %.2f\n",
                    amount, getBalance());
        } else {
            System.out.println("Invalid amount.. Amount to deposit needs to be positive.");
        }
    }
}

