// Student name: Koichi Nakata (ID: knakata595)

package org.example;

public class Assignment6 {
    static final int NUM_OF_ACCOUNT = 5;
    public static void main(String[] args) {
        CDAccount.setAnnualInterestRate(3.0);
        CDAccount[] CDArray = new CDAccount[NUM_OF_ACCOUNT];

        for (int i = 0; i < NUM_OF_ACCOUNT; i++) {
            int id = i * 1000 + 1000;
            double balance = i * 1000 + 1000.00;
            int duration = i * 3 + 3;
            CDArray[i] = new CDAccount(id, balance, duration);
        }

        for (CDAccount account : CDArray) {
            System.out.println(account);
            account.displayMonthlyInterest();
            account.withdraw(100);
            account.deposit(100);
            System.out.println();
        }
    }
}