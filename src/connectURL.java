


import java.sql.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;  

/**
 * @author rumi 2018
 * this snippet of code allows to test jdb connection easily without dropping any special tools else than java
 * this is very useful when deploying application in production where security concerns are key. You may not want
 * to drop an application like sql workbench just to check the connection
 */
@SuppressWarnings("deprecation")
public class connectURL {
	static void printResultSet(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(" | ");
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
	}
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) throws ParseException {
		Options options = new Options();

		Option argconnecturl = OptionBuilder.withArgName( "connecturl" )
				.hasArg()
				.withDescription(  "connection URL : jdbc:sqlserver://<hostname>:<port>;databaseName=<db name>;user=<user>;password=<password>" )
				.create( "url" );
		Option argsql = OptionBuilder.withArgName( "sql" )
				.hasArg()
				.withDescription(  "<sql command to execute>" )
				.create( "sql" );

		Option help = new Option( "help", "print this message" );
		options.addOption(argconnecturl);
		options.addOption(argsql);
		options.addOption(help);
		HelpFormatter formatter = new HelpFormatter();



		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse( options, args);

		String connectionUrl = null;


		String SQL = "SELECT TOP 10 * FROM BPASessionLog_NonUnicode";  

		if(cmd.hasOption("url")) {
			connectionUrl =	cmd.getOptionValue("url");
			System.out.println("Connection String :" + connectionUrl);
		}
		else {
			formatter.printHelp( "connectUrl", options );
			return;
		}
		if(cmd.hasOption("sql")) {
			SQL =	cmd.getOptionValue("sql");
			System.out.println("SQL Command : " + SQL);

		}
		else {

			System.out.println("Default SQL Command : " + SQL);

		}

		// Create a variable for the connection string.  
		

		// Declare the JDBC objects.  
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;  

		try {  
			// Establish the connection.  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			con = DriverManager.getConnection(connectionUrl);  

			// Create and execute an SQL statement that returns some data.  
			stmt = con.createStatement();  
			rs = stmt.executeQuery(SQL);  

			// Iterate through the data in the result set and display it.  
				printResultSet(rs); 
		
		}  

		// Handle any errors that may have occurred.  
		catch (Exception e) {  
			e.printStackTrace();  
		}  
		finally {  
			if (rs != null) try { rs.close(); } catch(Exception e) {}  
			if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
			if (con != null) try { con.close(); } catch(Exception e) {}  
		}  

	}

}
