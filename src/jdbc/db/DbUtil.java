package jdbc.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import data.Book;

 
public class DbUtil {
	
	public static List<Book> getBooks(){
		List<Book> booksList = null;
		final String query = "SELECT * FROM books";
		try (
			Connection conn = ConnectionProvider.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
		) {
			String title = null;
			String author = null;
			int year = 0;
			int pages = 0;
			booksList = new ArrayList<>();
			while(resultSet.next()) {
				title = resultSet.getString("Title");
				author = resultSet.getString("Author");
				year = resultSet.getInt("Year");
				pages = resultSet.getInt("Pages");
				Book book = new Book(title, author, year, pages);
				booksList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}

    public static int insert(String title, String author, int year, int pages) {
    	int rowsAffected = 0;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = ConnectionProvider.getConnection();
            statement = conn.createStatement();
            String query = "INSERT INTO books (Title,Author,Year,Pages) VALUES ('"+title+"','"+author+"',"+year+","+pages+")";
                   
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(statement, null, conn);
        }
        return rowsAffected;

    }
    
    public static int update(String title, String author, int year, int pages) {
    	int rowsAffected = 0;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectionProvider.getConnection();
            statement = conn.createStatement();
            String selectQuery = "SELECT ID FROM books WHERE " + ("Title = " + "\""+title+"\"");
            resultSet = statement.executeQuery(selectQuery);
            if(resultSet.next()) {
            	String updateQuery = "UPDATE books SET Author='"+author+"',Year="+year+",Pages="+pages+" WHERE ID="+resultSet.getInt(1);
                rowsAffected = statement.executeUpdate(updateQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(statement, null, conn);
        }
        return rowsAffected;

    }

    public static int delete(String title) {
    	int rowsAffected = 0;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectionProvider.getConnection();
            statement = conn.createStatement();
            String selectQuery = "SELECT ID FROM books WHERE " + ("Title = " + "\""+title+"\"");
            resultSet = statement.executeQuery(selectQuery);
            if(resultSet.next()) {
                String deleteQuery = "DELETE FROM books WHERE ID="+resultSet.getInt(1);
                rowsAffected = statement.executeUpdate(deleteQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseResources(statement, resultSet, conn);
        }
        return rowsAffected;
       
    }
    
    private static void releaseResources(Statement st, ResultSet rst, Connection conn) {
        try {
            if(st != null && !st.isClosed())
                st.close();
            if(rst != null && !rst.isClosed())
                rst.close();
            if(conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
 
}