// Student name: Koichi Nakata (ID: knakata595) March 22, 2024

package org.example;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment9 {

    public static void main(String[] args) throws FileNotFoundException {
        Account.setAnnualInterestRate(3.0);

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a file name: ");
        String fileName = input.next();
        input.nextLine();
        PrintWriter outputFile = new PrintWriter(fileName);

        ArrayList<Account> accounts = new ArrayList<>();

        while (true) {
            Account account = createAccount();
            accounts.add(account);
            writeAccount(outputFile, account);
            System.out.print("Enter 1 to open another Account: ");
            try {
                int res = input.nextInt();
                input.nextLine();
                if (res != 1) break;
            } catch (InputMismatchException e) {
                break;
            }
        }

        outputFile.close();

        System.out.println("\n" +
                "Account #  Initial Balance ($)  Annual Interest (%)  Account Type\n" +
                "=================================================================");
        for (Account account : accounts) System.out.println(account);
    }

    private static Account createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n***Please provide the following information to open an Account***");

        String prompt = "Enter an initial deposit amount ($): ";
        double initialDeposit = getDouble(input, prompt);

        prompt = "Enter an account duration in months. Input 0 to open a Saving or Checking Account: ";
        int duration = getNonNegativeInt(input, prompt);

        int id = generateId();

        if (duration == 0) {
            prompt = "Please enter 1 for a Saving Account, 2 for a Checking Account: ";
            int choice = getChoice(input, prompt);
            if (choice == 1) return new SavingAccount(id, initialDeposit);
            else return new CheckingAccount(id, initialDeposit);
        } else {
            return new CDAccount(id, initialDeposit, duration);
        }
    }

    private static double getDouble(Scanner input, String prompt) {
        double res = 0;
        while (true) {
            try {
                System.out.print(prompt);
                res = input.nextDouble();
                input.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid value.. Please input a floating number.");
                input.nextLine();
            }
        }
        return res;
    }

    private static int getNonNegativeInt(Scanner input, String prompt) {
        int res = 0;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                res = input.nextInt();
                input.nextLine();
                if (res >= 0) break;
            } else {
                System.out.println("Invalid value.. Please input a non-negative integer.");
                input.nextLine();
            }
        }
        return res;
    }

    private static int getChoice(Scanner input, String prompt) {
        int res = 0;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                res = input.nextInt();
                input.nextLine();
                if (res == 1 || res == 2) break;
            } else {
                System.out.println("Invalid value.. Please input 1 or 2.");
                input.nextLine();
            }
        }
        return res;
    }

    private static int generateId() {
        int id = (int)(Math.random() * 1000);
        return id;
    }

    private static void writeAccount(PrintWriter output, Account account) {
        double interestRate = 0.0;
        String accountType = "";
        if (account instanceof SavingAccount) {
            interestRate = account.getAnnualInterestRate();
            accountType = "SavingAccount";
        } else if (account instanceof CheckingAccount) {
            interestRate = ((CheckingAccount)account).getMonthlyInterestRate();
            accountType = "CheckingAccount";
        } else {
            interestRate = ((CDAccount)account).getCDAnnualInterestRate();
            accountType = "CDAccount";
        }

        output.printf("%d\t%.2f\t%.3f\t%s\n", account.getId(), account.getBalance(),
                interestRate, accountType);
    }
}