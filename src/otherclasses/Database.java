package otherclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;



public class Database {	
	
	// GLOBAL VARIABLES
	
	private static Connection conn;
    private static Statement stat;
    public static Vector<String[]> sel = new Vector();
	
	// END OF GLOBAL VARIABLES
	
	
	/**
     * Connect to SQLite database
     * @param Name of file
     */
    public static void connect(String fajl) {
       try {
          conn = DriverManager.getConnection("jdbc:sqlite:" + fajl);
          stat = conn.createStatement();
       } catch (Exception ex) {
          ex.printStackTrace();
       }
    }
    
    /**
     * Disconnect from database
     */
    public static void disconnect() {
       try {
          conn.close();
       } catch (Exception ex) {
          ex.printStackTrace();
       }
    }
    
    /**
     * Execute sql command
     * @param sql command
     */
    public static void exec(String sql) {
       try {
          stat.execute(sql);
       } catch (Exception ex) {
          ex.printStackTrace();          
       }
    }
    
    /**
     * Execure sql select
     * @param sql sql command
     * @return String array generic vector. Vector is for rows and array is for columns.
     */
    public static Vector<String[]> lekerdez(String sql){
       try {
          Vector<String[]> v = new Vector();
          ResultSet st = stat.executeQuery(sql);
          ResultSetMetaData meta = st.getMetaData();
          int n = meta.getColumnCount();
          while(st.next()) {
             String[] t = new String[n];
             for(int i = 1; i <= n; ++i) {
                t[i - 1] = st.getString(i);
             }
             v.add(t);
          }
          return v;
       } catch (Exception ex) {
          ex.printStackTrace();
          return null;
       }
    }
    
}
