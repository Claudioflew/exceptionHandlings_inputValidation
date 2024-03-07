// Student name: Koichi Nakata (ID: knakata595)

package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Assignment7 {
    static final int NUM_OF_ACCOUNTS = 100;
    static final String title = "Assignment7: Overridden equals() demonstration";
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        CDAccount.setAnnualInterestRate(3.0);

        createAccounts();
        Collections.shuffle(accounts);

        int id = 0;
        while (true) {
            id = askId();

            int response = displayAndAsk(id);
            if (response != JOptionPane.YES_OPTION) break;
        }

    }

    private static void createAccounts() {
        for (int i = 0; i < NUM_OF_ACCOUNTS; i++) {
            int random = (int)(Math.random() * 100);
            int id = i + 1001;
            double balance = random * 100 * 100 / 100.00;

            if (random % 2 == 0) {
                accounts.add(new Account(id, balance));
            } else {
                int duration = random / 30;
                accounts.add(new CDAccount(id, balance, duration));
            }
        }
    }

    private static int askId() {
        int id;
        String message = "Please enter an account number to check the balance:";
        String errorMessage = "Invalid entry.. Please enter an positive integer: ";


        while (true) {
            String inputStr = JOptionPane.showInputDialog(null, message,
                    title, JOptionPane.INFORMATION_MESSAGE);

            if (inputStr == null) System.exit(0);

            try {
                id = Integer.parseInt(inputStr);
                if (id > 0) break;

                JOptionPane.showMessageDialog(null, errorMessage,
                        title, JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, errorMessage,
                        title, JOptionPane.ERROR_MESSAGE);
            }
        }
        return id;
    }

    private static int displayAndAsk(int id) {
        // indexOf() method compares elements using equals() method
        // Object other = new Account(id, 0);
        // equals() method is public, so CDAccount also can use this 
        int index = accounts.indexOf(new Account(id, 0));

        String output = "";
        if (index >= 0) {
            // This order matters! CDAccount first, otherwise all instances are instanceof Account
            // Therefore, everything will be handled as Account object.
            if (accounts.get(index) instanceof CDAccount) {
                CDAccount target = (CDAccount)accounts.get(index);
                output = String.format("Account #: %d\nAccount Type: CD\nAccount Mature Balance: %.2f",
                        target.getId(), target.getMatureBalance());
            } else  {
                Account target = accounts.get(index);
                output = String.format("Account #: %d\nAccount Type: Regular\nAccount Balance: %.2f",
                        target.getId(), target.getBalance());
            }
        } else output = String.format("Account # %d doesn't exist..", id);


        output += "\n\nDo you want to look for another account?";
        int response = JOptionPane.showConfirmDialog(null, output,
                title, JOptionPane.INFORMATION_MESSAGE);

        return response;
    }
}