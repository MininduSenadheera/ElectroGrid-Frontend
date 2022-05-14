package bean;

// for JSON
import com.google.gson.*;

public class BillBean {
    private int billID;
    private int connectionID;
    private int customerID;
    private String customerName;
    private int paymentID;
    private String issuedDate;
    private String dueDate;
    private String status;
    private int units;
    private double amount;

    public int getBillID() {
        return billID;
    }
    public void setBillID(int billID) {
        this.billID = billID;
    }
    public int getConnectionID() {
        return connectionID;
    }
    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public String getIssuedDate() {
        return issuedDate;
    }
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    //calculate amount according to no.of units and adding tax rate
    public void calculateAmount(int units) {
        double total = 0;

        if (units <= 60) {
            total = units * 7.85;
        } else if (units <= 90) {
            total = (60 * 7.85) + ((units-60) * 10) + 90.00;
        } else if (units <= 120) {
            total = (60 * 7.85) + (30 * 10.00) + ((units-90) * 27.75) + 480.00;
        } else if (units <= 180) {
            total = (60 * 7.85) + (30 * 10.00) + (30 * 27.75) + ((units-120) * 32.00) + 480.00;
        } else {
            total = (60 * 7.85) + (30 * 10.00) + (30 * 27.75) + (60 * 32.00) + ((units-180) * 45.00) + 540.00;
        }

        setAmount(total);
    }

    //convert bill object to JSON object
   public String convertObjectToJsonString(BillBean billBean) {
       Gson gson = new Gson();
       String jsonString = gson.toJson(billBean);
       return jsonString;
   }
}