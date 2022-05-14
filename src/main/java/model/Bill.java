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

    // read bills by connection ID
    public String readConnectionBills(String connectionID) {
        String table = "";
         
        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for reading bills by connection ID.\"}";
            }

            // Prepare the html table to be displayed
			table = "<table>" 
                        + "<tr>"
                            + "<th>Connection ID</th>" + "<th>Customer ID</th>"
                            + "<th>Customer Name</th>" + "<th>Issued Date</th>" + "<th>Due Date</th>"
                            + "<th>Units</th>" + "<th>Amount</th>" + "<th>Payment Status</th>"
                            + "<th>Update Bill</th>" + "<th>Delete Bill</th>"
                        + "</tr>";

            // sql statement to retrieve bills by connection ID
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and C.connectionID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(connectionID));
            ResultSet resultSet = preparedStatement.executeQuery();

            BillBean billBean = new BillBean();

            // looping through the rows
            while (resultSet.next()) {
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

                // add the data to the html table
                table += "<tr><td>" + billBean.getConnectionID() + "</td>";
                table += "<td>" + billBean.getCustomerID() + "</td>";
                table += "<td>" + billBean.getCustomerName() + "</td>";
                table += "<td>" + billBean.getIssuedDate() + "</td>";
                table += "<td>" + billBean.getDueDate() + "</td>";
                table += "<td>" + billBean.getUnits() + "</td>";
                table += "<td>" + billBean.getAmount() + "</td>";
                table += "<td>" + billBean.getStatus() + "</td>";
                
                //buttons
        		table += "<td>";
        		table += "<input name='btnUpdate' type='button' value='Update Bill' class='btnUpdate btn btn-sm btn-secondary' data-billid='" + billBean.getBillID() + "'>";
        		table += "</td>"; 
        		table += "<td>";
        		table += "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-billid='" + billBean.getBillID() + "'>";
        		table += "</td></tr>";
            }

            table += "</table>";
            
            connection.close();

            return "{\"status\":\"success\", \"code\":200, \"data\":\"" + table + "\"}";
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while reading bills by connection ID\"}";
        }
    }
}
