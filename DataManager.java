import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DataManager {
	
	private Connection connection;
	
	public DataManager() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			}
		
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ideas_saver?useSSL=false","root","Cesar");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void insert(Idea idea) {
		
		PreparedStatement statement = null;
	   	 try {
				 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ideas_saver?useSSL=false","root","Cesar");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	   	 
	   	 String sql = "INSERT INTO ideas (title, date, other) VALUES(?,?,?)";
	   	 try {
			 statement = (PreparedStatement) connection.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		 try {
			statement.setObject(1,idea.getTitle(), Types.VARCHAR);
			statement.setObject(2,idea.getDate(), Types.VARCHAR);
			statement.setObject(3,idea.getOther(), Types.VARCHAR);			
			statement.executeUpdate();
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void remove(Idea idea) {
			
			PreparedStatement statement = null;
	     	 try {
	  			 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ideas_saver?useSSL=false","root","Cesar");
	  		} catch (SQLException e) {
	  			
	  			e.printStackTrace();
	  		}
	     	 
	     	 String sql = "delete from ideas  where title=?";
	     	 try {
	  		 statement = (PreparedStatement) connection.prepareStatement(sql);
	  	} catch (SQLException e) {
	  		
	  		e.printStackTrace();
	  	} 
	  	 try {
	  		statement.setObject(1,idea.getTitle(), Types.VARCHAR);
	  		statement.executeUpdate();
	  		} catch (SQLException e) {
	  		
	  		e.printStackTrace();}
	}

	public Vector<Idea> getIdeas(){
		
			Vector<Idea> resultVector=new Vector<Idea>();
	    	Statement statement = null;
	    	ResultSet result=null;
	    	try {
				 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ideas_saver?useSSL=false","root","Cesar");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    	try {
				 statement = (Statement) connection.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   	try {
	   			result = (ResultSet) statement.executeQuery( "SELECT *  FROM ideas;" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   	try {
			while(result.next())
			{
				Idea i =new Idea(result.getString("title"), result.getString("date"), result.getString("other"));
				resultVector.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   	return resultVector;
	}
}