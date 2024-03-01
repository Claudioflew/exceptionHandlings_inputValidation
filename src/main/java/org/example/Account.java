// Student name: Koichi Nakata (ID: knakata595)

package org.example;

import java.util.Date;
import java.util.Scanner;

public class Account {
    private static double annualInterestRate;
    private int id;
    private double balance;
    private final Date dateCreated;

    public Account() {
        id = 0;
        balance = 0;
        dateCreated = new Date();
    }

    public Account(int id, double balance) {
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
                System.out.println("Invalid annual rate.. Please enter again: ");
                Scanner input = new Scanner(System.in);
                annualInterestRate = input.nextDouble();
            }
        }
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id >= 0) this.id = id;
        else {
            System.out.println("Invalid ID.. The id has been set to 0.");
            this.id = 0;
        }
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) {
        if (balance >= 0) this.balance = balance;
        else {
            System.out.println("Invalid balance.. The balance has been set to 0.");
            this.balance = 0.0;
        }
    }

    public Date getDateCreated() { return dateCreated; }

    public double getMonthlyInterestRate() {
        return getAnnualInterestRate() / 12;
    }

    public double getMonthlyInterest() {
        double monthlyInterestRate = getMonthlyInterestRate();
        return (int)(getBalance() * monthlyInterestRate * 100) / 100.0;
    }

    public void withdraw(double amount) {
        if (getBalance() - amount >= 0) {
            setBalance(getBalance() - amount);
            System.out.printf("Withdrawn %.2f - New balance: %.2f\n",
                    amount, getBalance());
        } else {
            System.out.printf("Short of the balance.. Amount to withdraw should be equal or less than %.2f.\n",
                    getBalance());
        }
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

    public void addMonthlyInterest() {
        double monthlyInterest = getMonthlyInterest();
        setBalance(getBalance() + monthlyInterest);
    }

    public void display() {
        System.out.printf("%15d%20.2f%20.2f%15.2f%30s\n",
                getId(), getBalance(), getMonthlyInterest(),
                getBalance() + getMonthlyInterest(),
                getDateCreated());
    }
}

