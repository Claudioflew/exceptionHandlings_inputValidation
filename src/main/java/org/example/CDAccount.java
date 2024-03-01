// Student name: Koichi Nakata (ID: knakata595)

package org.example;

public class CDAccount extends Account {
    private int duration;
    private double CDAnnualInterestRate;

    public CDAccount() { this(0, 0.0, 3); }
    public CDAccount(int id, double balance, int duration) {
        super(id, balance);
        setDuration(duration);
        setCDAnnualInterestRate();
    }

    public void setDuration(int duration) { this.duration = duration; }
    public int getDuration() { return duration; }

    public double getMatureBalance() {
        double matureBalance = getBalance() * Math.pow((1 + getMonthlyInterestRate()/100), getDuration());
        return matureBalance;
    }

    private void setCDAnnualInterestRate() {
        int mon = getDuration() / 3;
        double CDAnnualInterestRate = super.getAnnualInterestRate() + 0.5 * mon;
        this.CDAnnualInterestRate = CDAnnualInterestRate;
    }

    public double getCDAnnualInterestRate() { return CDAnnualInterestRate; }

    @Override
    public double getMonthlyInterestRate() {
        return getCDAnnualInterestRate() / 12;
    }

    @Override
    public double getMonthlyInterest() {
        double monthlyInterestRate = getMonthlyInterestRate();
        return getBalance() * monthlyInterestRate / 100;
    }

    @Override
    public final void withdraw(double amount) {
        System.out.println("A CD Account can't withdraw any cash. You need to close this CD Account.");
    }

    @Override
    public void deposit(double amount) {
        System.out.println("A CD Account can't make any additional deposit. You may open another CD account.");
    }

    @Override
    public String toString() {
        String output = String.format("%15s%20s%20s%15s%25s\n",
                "Account Number", "Initial Balance", "Mature Balance", "Rate(%)", "Date Created");
        output += "=====================================================================================================\n";
        output += String.format("%15d%20.2f%20.2f%15.2f%30s\n",
                getId(), getBalance(), getMatureBalance(),
                getCDAnnualInterestRate(), getDateCreated());

        return output;
    }

    public void displayMonthlyInterest() {
        int duration = getDuration();
        for (int i = 1; i <= duration; i++) {
            double newBalance = getBalance() * Math.pow((1 + getMonthlyInterestRate()/100), i);
            System.out.printf("Month %-2d%14.2f\n", i, newBalance);
        }
    }
}
