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
 /* to be continued
  * 
    public static int insert(String title, String author, int year, int pages) {
        int rowsAffected = 0;
        Connection conn = null;
        Statement statement = null;

    }

    public static int delete(String title) {
        Connection conn = null;
        Statement statement = null;
       
    }
 */    
 
}