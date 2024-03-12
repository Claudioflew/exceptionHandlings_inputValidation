// Student name: Koichi Nakata (ID: knakata595)

package org.example;

import java.util.ArrayList;

public class Assignment8_Part1 {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(new SavingAccount(1000, 2000));
        accounts.add(new SavingAccount(1001, 800));
        accounts.add(new CheckingAccount(1002, 1000));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < accounts.size(); j++) {
                // withdraw() is overridden so no need to downcast
                accounts.get(j).withdraw(300);
            }
            System.out.println();
        }
    }
}