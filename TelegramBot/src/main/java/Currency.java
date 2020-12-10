import java.text.SimpleDateFormat;

public class Currency {
    private String StartDate;
    private int TimeSign;
    private int CurrencyCode;
    private String CurrencyCodeL;
    private int Units;
    private double Amount;

    public Currency() {

    }

    public Currency(String StartDate) {
        this.StartDate = StartDate;
    }

    public Currency(String StartDate, int TimeSign, int CurrencyCode, String CurrencyCodeL, int Units, double Amount) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.StartDate = StartDate;
        this.TimeSign = TimeSign;
        this.CurrencyCode = CurrencyCode;
        this.CurrencyCodeL = CurrencyCodeL;
        this.Units = Units;
        this.Amount = Amount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public int getTimeSign() {
        return TimeSign;
    }

    public int getCurrencyCode() {
        return CurrencyCode;
    }

    public String getCurrencyCodeL() {
        return CurrencyCodeL;
    }

    public int getUnits() {
        return Units;
    }

    public double getAmount() {
        return Amount;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setTimeSign(int timeSign) {
        TimeSign = timeSign;
    }

    public void setCurrencyCode(int currencyCode) {
        CurrencyCode = currencyCode;
    }

    public void setCurrencyCodeL(String currencyCodeL) {
        CurrencyCodeL = currencyCodeL;
    }

    public void setUnits(int units) {
        Units = units;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "startDate=" + StartDate +
                ", timeSign=" + TimeSign +
                ", currencyCode=" + CurrencyCode +
                ", currencyCodeL='" + CurrencyCodeL + '\'' +
                ", units=" + Units +
                ", amount=" + Amount +
                '}';
    }
}
