public class TransactionData {
    private final String period;
    private final String units;
    private final int year;
    private final int month;
    private final double amount;

    public TransactionData(String period, String units, int year, int month, double amount) {
        this.period = period;
        this.units = units;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public String getUnits() {
        return units;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public double getAmount() {
        return amount;
    }
}
