import javax.swing.JOptionPane;

import java.sql.*;
import java.lang.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
public class Lab4b
{   

    public static String getResponse(String question){
	String value;
	value = JOptionPane.showInputDialog(question);

	return value;
    }

    public static void showoutput(String value){
	JOptionPane.showMessageDialog(null, value);

    }

    public static String semester(){
	String value = getResponse("What semester would you like to enroll for?");
	if (value.toUpperCase().equals("FA15") || value.toUpperCase().equals("SP16")){
		return value;
	}
	else{
	showoutput("Please enter a correct semester next time");
	return " ";
	}

    }



    public static void main (String[] args)
    {
                    Connection con = null;

                        try {
                        Statement stmt;
                        ResultSet rs20;

                        // Register the JDBC driver for MySQL.
                        Class.forName("com.mysql.jdbc.Driver");

                        // Define URL of database server for
                        // database named 'user' on the faure.
                        String url ="jdbc:mysql://faure/prayfran";

                        // Get a connection to the database for a
                        // user named 'user' with the password
                        // 123456789.
                        con = DriverManager.getConnection(
            url,"prayfran", "829638867");

                        // Display URL and connection information
                        System.out.println("URL: " + url);
                        System.out.println("Connection: " + con);

                        // Get a Statement object
                        stmt = con.createStatement();

                        try{
                                rs20 = stmt.executeQuery("SELECT * FROM Classes");

                                }catch(Exception e){
                                System.out.print(e);
                                System.out.println(
                  "No Class table to query");
                        }//end catch

                        
               
	

	String value;
	int flag = 1;
	while(flag == 1){
		value = getResponse("Please input a student Identification Number");
		ResultSet rs;
        	rs = stmt.executeQuery("SELECT * FROM Student WHERE StudentID = " + value);
		rs.last();
		if(rs.getRow() > 0){
			String sem = semester();
				if (sem == " "){
					continue;

				}else{
					ArrayList<String> classestotake = new ArrayList<String>();
					String classtostart = getResponse("Enter the Class ID of the class you wish to add");
					classestotake.add(classtostart);
					
				String add = getResponse("Would you like to add another class? type yes to confirm");
				if(add.toLowerCase().equals("yes")){		
						while(add.toLowerCase().equals("yes")){
						       	String classtostart2 = getResponse("Enter the Class ID of the class you wish to add");
		                                        ResultSet rs10 =stmt.executeQuery("Select preq From c_pre Where c_ID ='" + classtostart2.toUpperCase() +"';");
                		                        ResultSetMetaData rsmd2 = rs10.getMetaData();
                                		        int cN2 = rsmd2.getColumnCount();
                                      			  while (rs10.next()) {
                                          			for (int i = 1; i <= cN2; i++) {
                                                 		ResultSet rs11 = stmt.executeQuery("Select * From Enrolled Where s_ID ='" + value + "' and c_ID='"+ classtostart2 +"';");
                                                 		rs11.last();
                                                 		if (rs11.getRow() <= 0){
                                                		showoutput("The student doesnt meet the prereqs for " + classtostart2);
                                                		continue;
                                          		      	}else{
														classestotake.add(classtostart2.toUpperCase());
														}

													}	
												}
								add = getResponse("Would you like to add another class? type yes to confirm");
	
						}
						
						}else{
							String listString = "";

						for (String s : classestotake)
						{
						listString = listString + " " + s;
						}
						String check = getResponse("Are these the classes you want to add? (enter yes to confirm) " +listString);
						if(check.toLowerCase().equals("yes")){
							int credits = 0;
							for(int j=0; j<classestotake.size();j++){
							Calendar cal = Calendar.getInstance();
							cal.clear(Calendar.HOUR_OF_DAY);
							cal.clear(Calendar.AM_PM);
							cal.clear(Calendar.MINUTE);
							cal.clear(Calendar.SECOND);
							cal.clear(Calendar.MILLISECOND);
							int year       = cal.get(Calendar.YEAR);
							int month      = cal.get(Calendar.MONTH); // Jan = 0, dec = 11
							int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
							String date = year+"/"+month+"/"+dayOfMonth;
							System.out.println(Integer.valueOf(value));
							int s_id = Integer.valueOf(value);
							int one = 1;
							Boolean rs1000=stmt.execute("INSERT INTO Enrolled VALUES('" + s_id + "','" + classestotake.get(j) + "','" + one + "','" + sem + "','" + "U" + "','" + date+ "');");
							
							ResultSet rs10001=stmt.executeQuery("Select credits From Classes Where c_ID ='"+ classestotake.get(j) + "';");
							credits = credits + rs10001.getInt(1);
								if (credits > 18){
								break;
								}	 
							}
					    }else{continue;}
						}
					}
						
							
					
					

				
				}

		
		else{
		String response = getResponse("Would you like to add this student to the database? (type in yes if so and if not the system will reset)");
		if(response.equals("yes")){
			System.out.println("hi");
			String value2 = getResponse("Please enter the Identification number of the student you wish to add to the database");
			String value3 = getResponse("Please enter the first name of the student");
			String value4 = getResponse("Please enter the last name of the student");
			String value5 = getResponse("Please enter the Date of Birth of the student Example (1993/09/26)");
            String value6 = getResponse("Please enter the Major  of the student");
			
            Boolean rs2 = stmt.execute("INSERT INTO Student VALUES('" +value2 +"','" + value3 +"','"+ value4 + "','" + value5 +"','"+ value6+ "');" );
			String sem2 = semester();
				if(sem2 == " "){
					continue;	
				}else{
                                        ArrayList<String> classes= new ArrayList<String>();
                                        String classtostart3 = getResponse("Enter the Class ID of the class you wish to add");
                                        ResultSet rs12 =stmt.executeQuery("Select preq From c_pre Where c_ID ='" + classtostart3 +"';'");
                                        ResultSetMetaData rsmd3 = rs12.getMetaData();
                                        int cN3 = rsmd3.getColumnCount();
                                        while (rs12.next()) {
                                          for (int i = 1; i <= cN3; i++) {
                                                 ResultSet rs13 = stmt.executeQuery("Select * From Enrolled Where s_ID ='" + value + "'and c_ID='"+ rs.getString(i) +"';");
                                                 rs13.last();
                                                 if (rs13.getRow() <= 0){
                                                showoutput("The student doesnt meet the prereqs for " + classtostart3);
                                                continue;
                                                }else{
                                                        classes.add(classtostart3);
                                                }

                                           }
                                        }
                                        String add = getResponse("Would you like to add another class? type yes to confirm");
                                        if(add.toLowerCase().equals("yes")){

                                                while(add.equals("yes")){
                                                        String classtostart4 = getResponse("Enter the Class ID of the class you wish to add");
                                                        ResultSet rs14 =stmt.executeQuery("Select preq From c_pre Where c_ID =" + classtostart4 +";");
                                                        ResultSetMetaData rsmd4 = rs14.getMetaData();
                                                        int cN4 = rsmd4.getColumnCount();
                                                          while (rs14.next()) {
                                                                for (int i = 1; i <= cN4; i++) {
                                                                ResultSet rs15 = stmt.executeQuery("Select * From Enrolled Where s_ID =" + value + "'and c_ID='"+ classtostart4 +"';");
                                                                rs15.last();
                                                                if (rs15.getRow() <= 0){
                                                                showoutput("The student doesnt meet the prereqs for " + classtostart4);
                                                                continue;
                                                                }else{
                                                                 classes.add(classtostart4);
                                                                     } 
															    }

                                                        }
                                                }
                                        }else{
                                        String check = getResponse("Are these the classes you want to add? (enter yes to confirm)");
                                        if(check.toLowerCase().equals("yes")){
                                                int credits = 0;
                                                for(int j=0; j<classes.size();j++){
												Calendar cal2 = Calendar.getInstance();
												cal2.clear(Calendar.HOUR_OF_DAY);
												cal2.clear(Calendar.AM_PM);
												cal2.clear(Calendar.MINUTE);
												cal2.clear(Calendar.SECOND);
												cal2.clear(Calendar.MILLISECOND);
												int year2       = cal2.get(Calendar.YEAR);
												int month2      = cal2.get(Calendar.MONTH); // Jan = 0, dec = 11
												int dayOfMonth2 = cal2.get(Calendar.DAY_OF_MONTH);
												String date2 = year2+"/"+month2+"/"+dayOfMonth2;
												System.out.println(Integer.valueOf(value));
												int s_id = Integer.valueOf(value);
												int one = 1;
												Boolean rs17=stmt.execute("INSERT INTO Enrolled VALUES('" + Integer.valueOf(value) + "','" + classes.get(j) + "','" + one + "','" + sem2 + "','" + "U" + "','" + date2+ "');");
                                                ResultSet rs18=stmt.executeQuery("Select credits From Classes Where c_ID ='"+ classes.get(j)+ "';");
                                                credits = credits + rs18.getInt(1);
                                                        if (credits > 18){
                                                        break;
                                                        }
                                                }
                                        }}

					


				}
		}else{
		continue;
		}
                



		}

	}
	}                        catch( Exception e ) {
                                e.printStackTrace();

                        }
	}
}
	 							    
