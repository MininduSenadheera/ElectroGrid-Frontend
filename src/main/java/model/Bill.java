package model;

import java.sql.*;

import bean.BillBean;
import util.DBConnection;
import util.BillValidations;

public class Bill {

    // read bills by bill ID
    public String readBill(String billID) {
        BillBean billBean = new BillBean();
        billBean.setBillID(Integer.parseInt(billID));

        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for reading bill\"}";
            }

            // sql statement to retrieve bill
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and B.billID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, billBean.getBillID());
            ResultSet resultSet = preparedStatement.executeQuery();

            //checking if data is available
            if (resultSet.next()) {
                billBean.setBillID(resultSet.getInt("billID"));
                billBean.setConnectionID(resultSet.getInt("connectionID"));
                billBean.setCustomerID(resultSet.getInt("customerID"));
                billBean.setCustomerName(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
                billBean.setPaymentID(resultSet.getInt("paymentID"));
                billBean.setIssuedDate(resultSet.getString("issueDate"));
                billBean.setDueDate(resultSet.getString("dueDate"));
                billBean.setStatus(resultSet.getString("status"));
                billBean.setUnits(resultSet.getInt("units"));
                billBean.setAmount(resultSet.getDouble("amount"));
            } else {
                return "{\"status\":\"error\", \"code\":404, \"data\":\"No bills found with the corresponding ID.\"}";
            }
            
            connection.close();
            
            String jsonBill = billBean.convertObjectToJsonString(billBean);

            return "{\"status\":\"success\", \"code\":200, \"data\":"+ billBean.convertObjectToJsonString(billBean) +"}";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{\"status\":\"error\", \"code\":500, \"data\":\"" + e.getMessage() + "\"}";
        }
    }

}
