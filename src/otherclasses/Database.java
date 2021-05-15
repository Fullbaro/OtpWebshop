package otherclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

public class Database {	
	
	// GLOBAL VARIABLES
	
	private Connection conn;
    private Statement stat;
    private Vector<String[]> sel = new Vector();
	
	// END OF GLOBAL VARIABLES
		
	/**
     * Connect to SQLite database
     * @param Name of file
     */
    public void connect(String fajl) throws Exception{       
    	conn = DriverManager.getConnection("jdbc:sqlite:" + fajl);
        stat = conn.createStatement();       
    }
    
    /**
     * Disconnect from database
     */
    public void disconnect() throws Exception{       
        conn.close();       
    }
    
    /**
     * Execute sql command
     * @param sql command
     */
    public void exec(String sql) throws Exception{       
    	stat.execute(sql);       
    }
    
    /**
     * Execure sql select
     * @param sql sql command
     * @return String array generic vector. Vector is for rows and array is for columns.
     */
    public Vector<String[]> select(String sql) throws Exception{
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
    }
}
