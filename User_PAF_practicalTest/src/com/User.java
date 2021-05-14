package com;

import java.sql.*;

public class User 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ
			public String readUser()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							// Prepare the HTML table to be displayed
							output = "<table  class='table table-dark table-striped'><tr><th>User FirstName</th>"
									+ "<th>User LastName</th>"
									+ "<th>User Password</th>"
									+ "<th>User Email</th>"
									+ "<th>Edit</th><th>Delete</th></tr>";

							String query = "select * from users";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String userID = Integer.toString(rs.getInt("userID"));
									String userfirstName = rs.getString("userfirstName");
									String userlastName = rs.getString("userlastName");
									String userpassword = rs.getString("userpassword");
									String userEmail = rs.getString("userEmail");
								

									// Add a row into the HTML table
									output += "<tr><td>" + userfirstName + "</td>";
									output += "<td>" + userlastName + "</td>";
									output += "<td>" + userpassword + "</td>"; 
									output += "<td>" + userEmail + "</td>";
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-userid='" + userID + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-userid='" + userID + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the users.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT
			public String insertUser(String Fname, String Lname, String pwd, String email)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into users (`userID`,`userfirstName`,`userlastName`,`userpassword`,`userEmail`) values (?, ?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, Fname);
							preparedStmt.setString(3, Lname);
							preparedStmt.setString(4, pwd);
							preparedStmt.setString(5, email);

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateUser(String id,String Fname, String Lname, String pwd, String email)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE users SET userfirstName=?, userlastName=?, userpassword=?, userEmail=? WHERE userID=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, Fname);
							preparedStmt.setString(2, Lname);
							preparedStmt.setString(3, pwd);
							preparedStmt.setString(4, email);
							preparedStmt.setInt(5, Integer.parseInt(id));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteUser(String uID)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from users where userID=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(uID));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
			}

	
}
