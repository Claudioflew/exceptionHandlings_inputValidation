// Student name: Koichi Nakata (ID: knakata595)

package org.example;

public class SavingAccount extends Account{
    private int numberWithdraw;
    private static double minBalance = 300.00;
    private static int maxWithdraw = 3;

    public SavingAccount(int id, double balance) {
        super(id, balance);
        setNumberWithdraw(getMaxWithdraw());
    }

    public static double getMinBalance() { return minBalance; }
    public static int getMaxWithdraw() { return maxWithdraw; }

    public int getNumberWithdraw() { return numberWithdraw; }
    public void setNumberWithdraw(int num) {
        if (num >= 0 && num <= getMaxWithdraw()) this.numberWithdraw = num;
        else System.out.println("Invalid number..");
    }

    @Override
    public void withdraw(double amount) {
        double remaining = getBalance() - amount;
        if (getNumberWithdraw() > 0 && remaining >= getMinBalance()) {
            super.withdraw(amount);
            int numberWithdraw = getNumberWithdraw() - 1;
            setNumberWithdraw(numberWithdraw);
            // "this" calls overridden toString()
            System.out.println(this + "\nWithdraw complete.");
        } else {
            String message = "";
            if (remaining < getMinBalance()) message = String.format("A saving account should maintain a minimum balance: $ %.2f",
                    getMinBalance());
            else message = String.format("A saving account can withdraw maximum %d times in a month",
                    getMaxWithdraw());

            System.out.println(this + "\nUnable to perform withdraw.\n" + message);
        }
    }

    @Override
    public String toString() {
        String output = String.format("Account Number: %d, Account Balance: $ %.2f",
                getId(), getBalance());
        return output;
    }
}
