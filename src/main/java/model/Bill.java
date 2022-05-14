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
    
    // read bills by customerID
    public String readCustomerBills(String customerID) {
        String table = "";
            
        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for reading bills by customer ID.\"}";
            }

            // Prepare the html table to be displayed
			table = "<table>" 
                    + "<tr>"
                        + "<th>Connection ID</th>" + "<th>Customer ID</th>"
                        + "<th>Customer Name</th>" + "<th>Issued Date</th>" + "<th>Due Date</th>"
                        + "<th>Units</th>" + "<th>Amount</th>" + "<th>Payment Status</th>"
                        + "<th>Update Payment</th>" + "<th>Delete Bill</th>"
                    + "</tr>";

            // sql statement to retrieve bills by connection ID
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and U.customerID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(customerID));
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
        		table += "<input name='btnUpdate' type='button' value='Update Payment' class='btnUpdate btn btn-sm btn-secondary' data-billid='" + billBean.getBillID() + "'>";
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
            return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while reading bills by customer ID\"}";
        }
    }

    // issuing a new bill
    public String newBill(String connectionID, String meterReading) {
        BillBean billBean = new BillBean();
        billBean.setConnectionID(Integer.parseInt(connectionID));
        billBean.setUnits(Integer.parseInt(meterReading));

        try {
            Connection connection = DBConnection.connect();
            BillValidations validations = new BillValidations();

            if (connection == null) {
                return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for inserting bill\"}";
            }

            // validating data
            if (validations.insertValidation(billBean.getConnectionID(), billBean.getUnits()) == false){
                return "{\"status\":\"error\", \"code\":412, \"data\":\"Unacceptable Values.\"}";
            }

            // get units difference
            String d_units = GetMonthlyUnitsFromConnectionService(billBean);
            int diff_units = Integer.parseInt(d_units);
            billBean.calculateAmount(diff_units);

            String sql =    " INSERT INTO Bill (`connectionID`,`issueDate`,`dueDate`,`units`,`amount`,`status`)" + 
                            " values (?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), ?, ?, ?)";

            String[] billID = { "billID" };

            PreparedStatement preparedStatement = connection.prepareStatement(sql,billID);
			preparedStatement.setInt(1, billBean.getConnectionID());
			preparedStatement.setInt(2, diff_units);
            preparedStatement.setDouble(3, billBean.getAmount());
            preparedStatement.setString(4, "Pending");
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	        String key="";
					
            if (resultSet.next()) {  
                key=resultSet.getString(1);
            }

            String insertedBill = readBill(key);
            
            return "{\"status\":\"success\", \"code\":200, \"data\":" + insertedBill + "}";

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{\"status\":\"error\", \"code\":500, \"data\":\"" + e.getMessage() + "\"}";
        }
    }

    // updating bill
    public String updateBill(String billId, String connectionID, String paymentID) {

        BillBean billBean = new BillBean();
        billBean.setPaymentID(Integer.parseInt(paymentID));
        billBean.setBillID(Integer.parseInt(billId));

		try {
			Connection connection = DBConnection.connect(); 
            BillValidations validations = new BillValidations();
			
			if (connection == null) {
				return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for updating bill\"}";
			}

            // validating data
            if (validations.updateValidation(billBean.getBillID(), billBean.getPaymentID()) == false){
                return "{\"status\":\"error\", \"code\":412, \"data\":\"Unacceptable Values.\"}";
            }

            String sql = "UPDATE Bill SET paymentID = ?, status = ? WHERE billID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, billBean.getPaymentID());
            preparedStatement.setString(2, "Paid");
            preparedStatement.setInt(3, billBean.getBillID());
            int status = preparedStatement.executeUpdate();

			connection.close();

            //checking if the bill is updated
            if(status > 0) {
                String newBills = readConnectionBills(connectionID);
                
                return "{\"status\":\"success\", \"code\":200, \"data\":" + newBills + "}"; 
            } else {
                return "{\"status\":\"error\", \"code\":404, \"data\":\"No bills found with the corresponding ID.\"}";
            }
			
		} catch (SQLException se) {
            System.err.println(se.getMessage());
            return "{\"status\":\"error\", \"code\":304, \"data\":\"" + se.getMessage() + "\"}";
        } catch (Exception e) {
			System.err.println(e.getMessage());
            return "{\"status\":\"error\", \"code\":500, \"data\":\"" + e.getMessage() + "\"}";
		}
	}

    // deleting bill
    public String deleteBill(String billId) {

        BillBean billBean = new BillBean();
        billBean.setBillID(Integer.parseInt(billId));

        try {
            Connection connection = DBConnection.connect();
            BillValidations validations = new BillValidations();

            if (connection == null) {
                return "{\"status\":\"error\", \"code\":500, \"data\":\"Error while connecting database for deleting bill.\"}";
            }

            // validating data
            if (validations.deleteValidation(billBean.getBillID()) == false){
                return "{\"status\":\"error\", \"code\":412, \"data\":\"Unacceptable Values.\"}";
            }


            // get payment related to bill
            String sql1 = "SELECT paymentID, connectionID FROM Bill WHERE billID = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, billBean.getBillID());
            ResultSet resultSet = preparedStatement1.executeQuery();

            String connectionID ="";

            // call payment delete only if a payment is related to bill
            if(resultSet.next()) {
                billBean.setPaymentID(resultSet.getInt("paymentID"));
                connectionID = resultSet.getString("connectionID");

                if (billBean.getPaymentID() != 0) {
                    
                }
            }

            // delete bill
            String sql2 = "DELETE FROM Bill WHERE billID = ? ";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, billBean.getBillID());
            int status = preparedStatement2.executeUpdate(); 

            connection.close(); 

            //checking if the bill is updated
            if(status > 0) {
            	String newBills = readConnectionBills(connectionID);
                
                return "{\"status\":\"success\", \"code\":200, \"data\":" + newBills + "}";
            } else {
                return "{\"status\":\"error\", \"code\":404, \"data\":\"No bills found with the corresponding ID.\"}";
            }

        } catch (SQLException se) {
            System.err.println(se.getMessage());
            return "{\"status\":\"error\", \"code\":304, \"data\":\"" + se.getMessage() + "\"}";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{\"status\":\"error\", \"code\":500, \"data\":\"" + e.getMessage() + "\"}";
        }
    }

    // this method call update units method in connection service
	public String GetMonthlyUnitsFromConnectionService(BillBean billBean) {
        String output = "";

		try {
			Connection connection =DBConnection.connect();

			if(connection==null){
				return "Error while connecting to database";
			}

			String query = "SELECT units FROM Connection WHERE connectionID=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, billBean.getConnectionID());
            ResultSet resultSet = preparedStmt.executeQuery();

			int oldUnits=0;

			if(resultSet.next()){
				oldUnits = resultSet.getInt("units");
				
			}

			int monthlyUnits = billBean.getUnits() - oldUnits;

			String sql = "UPDATE Connection SET  units = ?  WHERE connectionID=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            preparedStatement.setInt(1, billBean.getUnits());
			preparedStatement.setInt(2, billBean.getConnectionID());
            preparedStatement.executeUpdate();

			connection.close();

			output = Integer.toString(monthlyUnits);
		} catch (Exception e){
			output = "Error while updating the connection.";
			System.err.println(e.getMessage());
		}

		return output;
			
	}
}
