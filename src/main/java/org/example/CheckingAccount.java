// Student name: Koichi Nakata (ID: knakata595)

package org.example;

public class CheckingAccount extends Account {
    private static double minBalance = 0.0;

    public CheckingAccount(int id, double balance) { super(id, balance); }

    public static double getMinBalance() { return minBalance; }

    @Override
    public double getMonthlyInterestRate() { return 0.0; }

    @Override
    public void withdraw(double amount) {
        double remaining = getBalance() - amount;
        if (remaining >= 0) {
            super.withdraw(amount);
            System.out.println(this + "\nWithdraw complete.");
            // "this" calls overridden toString() method
        } else {
            System.out.println(this + "\nNo sufficient balance to perform the withdraw.");
        }
    }

    @Override
    public String toString() {
        String output = String.format("Account Number: %d, Account Balance: $ %.2f",
                getId(), getBalance());
        return output;
    }
}
