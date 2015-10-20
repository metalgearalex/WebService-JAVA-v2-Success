package testinglocalquery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
 

//@WebServlet(name="test",urlPatterns={"/test"})
public class TestWebServiceLayer {

private static String connectionAnswer; //seems like this should be elsewhere or is just a duplicate in general? static String connectionAnswer= "empty";  	    
    
    public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;
    
    try { 
        Class.forName("org.postgresql.Driver"); //Before you can connect to a database, you need to load the driver. This is one of the 2 standard ways
        connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-227-253-238.compute-1.amazonaws.com:5432/d20hohh2uq3tib", "lqwgfavqzszazx", "dYfO-uBInG-WGgkqGFpz4GHAjc"); //username and password //used to get a Connection instance from JDBC; global JDBC method
        statement = connection.createStatement(); //Any time you want to issue SQL statements to the database, you require a Statement or PreparedStatement instance.
        String querySQL = "SELECT price FROM public.apartmentinventory"; //"SELECT attribute FROM tableName WHERE condition", Example: “SELECT price FROM ApartmentListDatabaseTable WHERE price < 4000”
        result = statement.executeQuery(querySQL); //result is an instance of ResultSet. executeQuery returns a ResultSet instance which contains the entire result
        //note that by default the Driver collects all the results for the query at once. You can make it fetch only a few rows however using cursors 
        connectionAnswer = "";
        while (result.next()) { //before reading any values you must call next() This returns true if there is a result, but more importantly, it prepares the row for processing.
        	//System.out.println(result.getString("price")); ////working code to get console log
        	//the code below also gets the result to show up in the console log
        	int price = result.getInt("price");
            connectionAnswer = connectionAnswer + price + " | " +  "\n";
        		}
    }//end of try
    catch (Exception exc){
    	exc.printStackTrace();
    		}
}

           
}//end of ConnectingToPostgreSQL class
