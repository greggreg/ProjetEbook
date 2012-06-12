package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BD {
	private Connection conn;
	private final static String pdfOnly="b.mime_type == 'application/pdf'";

	public BD(String path) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isPdfFile(int id){
		boolean b=false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM books b WHERE _id=" + id + " and " + pdfOnly );

			b= resultSet.next();

		} catch (SQLException e) {

			e.printStackTrace();
		} 
		finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		return b;
	}
	/**
	 * retourne la totalit� des livre dans la base
	 * 
	 * @return ArrayList<Book>
	 *//*
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> res = new ArrayList<Book>();
		 Statement statement = null;
		 ResultSet resultSet = null;
		
		try {
			statement = conn.createStatement();
			 resultSet = statement.executeQuery(("SELECT  *  FROM books"));
			
			while (resultSet.next()) {
				
				
				res.add(new Book(resultSet.getInt("_id"), resultSet.getString("Title"),resultSet.getString("author"), resultSet.getString("file_path"), resultSet.getString("file_name"),this.getBookNotes(resultSet.getInt("_id"))));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		return res;
	}

	/**
	 * return a book from the data base following its id
	 * 
	 * @param id
	 * @return book
	 */
	public Book getBook(int id) {
		Book res = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT *  FROM books WHERE _id=" + id);

			while (resultSet.next()) {

				res = new Book(id, resultSet.getString("Title"),
						resultSet.getString("author"),
						resultSet.getString("file_path"),
						resultSet.getString("file_name"));
						
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return res;

	}

	/**
	 * retourne l'annotation correspondant � l'id
	 * 
	 * @param id
	 * @return annotation
	 *//*
	public  getAnnotation(int id) {
		Note res = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT  * FROM annotation a where _id=" + id);
			while (resultSet.next()) {
				res = new Note(resultSet.getInt("_id"),
						resultSet.getInt("content_id"),
						resultSet.getInt("markup_type"),
						resultSet.getString("added_date"),
						resultSet.getString("modified_date"),
						resultSet.getString("name"),
						resultSet.getString("mark"),
						resultSet.getString("mark_end"),
						resultSet.getInt("page"),
						resultSet.getInt("total_page"),
						resultSet.getString("mime_type"),
						resultSet.getString("file_path"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return res;
	}

	/**
	 * retourne la liste des annotations pour un livre
	 * 
	 * @param id
	 * @return
	 *//*
	public ArrayList<Note> getBookNotes(int id) {
		ArrayList<Note> res = new ArrayList<Note>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT distinct a._id,a.content_id,a.markup_type,a.added_date,a.modified_date,a.name,a.marked_text,a.mark,a.mark_end,a.page,a.total_page,a.mime_type,a.file_path FROM books b,annotation a where a.content_id="
							+ id);
			
			while (resultSet.next()) {
				
				res.add(new Note(resultSet.getInt("_id"), resultSet
						.getInt("content_id"), resultSet.getInt("markup_type"),
						resultSet.getString("added_date"), resultSet
								.getString("modified_date"), resultSet
								.getString("name"),
						resultSet.getString("mark"), resultSet
								.getString("mark_end"), resultSet
								.getInt("page"),
						resultSet.getInt("total_page"), resultSet
								.getString("mime_type"), resultSet
								.getString("file_path")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
		return res;
	}

	/**
	 * retourne la liste des livres annot�s (freehand ou note)
	 */
	/**
	 * retourne la totalit� des livre dans la base
	 * 
	 * @return ArrayList<Book>
	 */
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> res = new ArrayList<Book>();
		 Statement statement = null;
		 ResultSet resultSet = null;
		
		try {
			statement = conn.createStatement();
			 resultSet = statement.executeQuery(("SELECT  *  FROM books"));
			
			while (resultSet.next()) {
				
				
				res.add(new Book(resultSet.getInt("_id"), resultSet.getString("Title"),resultSet.getString("author"), resultSet.getString("file_path"), resultSet.getString("file_name")));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		return res;
	}

	public ArrayList<Book> getAnnotedBooks() {
		ArrayList<Book> res = new ArrayList<Book>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			String T1 ="SELECT distinct b._id ,b.title,b.author,b.file_path,b.file_name FROM books b,annotation a where a.content_id=b._id";  
			String T2="SELECT distinct b._id ,b.title,b.author,b.file_path,b.file_name FROM books b,freehand f where f.content_id=b._id" ; 
			String T3="SELECT b._id ,b.title,b.author,b.file_path,b.file_name from books b where b.mime_type='application/pdf'";
			resultSet = statement
					.executeQuery(T1+" UNION " + T2 + " INTERSECT " + T3) ;
			
			while (resultSet.next()) {
				res.add(new Book(resultSet.getInt("b._id"),resultSet.getString("b.title"),resultSet.getString("b.author"),resultSet.getString("b.file_path"),resultSet.getString("b.file_name")));
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return res;

	}
/**
	public void getAnnotation() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT distinct a.mark, a.mark_end FROM books b,annotation a where a.content_id=b._id and b._id=227");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("mark") +" |end: " +resultSet.getString("mark_end" ));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		// return res;
	}*/
	public ArrayList<Freehand> getBookFreehands(int id){
		
		ArrayList<Freehand> res = new ArrayList<Freehand>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT distinct a._id,a.name, a.content_id,a.added_date,a.modified_date,a.mark,a.mark_end,a.page,a.total_page,a.svg_file FROM books b,freehand a where a.content_id="
							+ id + " and " + pdfOnly);
			
			while (resultSet.next()) {
				
				res.add(new Freehand(resultSet.getInt("_id"),resultSet.getString("name"), resultSet
						.getInt("content_id"),resultSet.getString("added_date"),resultSet.getString("modified_date"),new PdfLoc( resultSet.getString("mark")),new PdfLoc( resultSet
								.getString("mark_end")), resultSet.getInt("page"),resultSet.getInt("total_page"), resultSet
								.getString("svg_file")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
		return res;
	}
	/**
	 * retourne une liste de Notes pour un livre donn�
	 * @param id
	 * @return
	 */
public ArrayList<Note> getBookNotes(int id){
		
		ArrayList<Note> res = new ArrayList<Note>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		if(isPdfFile(id)){
		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT distinct a._id,a.name, a.content_id,a.added_date,a.modified_date,a.mark,a.mark_end,a.page,a.total_page,a.file_path,a.markup_type,a.marked_text FROM books b,annotation a where a.content_id="
							+ id );
			
			while (resultSet.next()) {
				
				res.add(new Note(resultSet.getInt("_id"),resultSet.getString("name"), resultSet
						.getInt("content_id"),resultSet.getString("added_date"),resultSet.getString("modified_date"),new PdfLoc( resultSet.getString("mark")),new PdfLoc( resultSet
								.getString("mark_end")), resultSet.getInt("page"),resultSet.getInt("total_page"), resultSet
								.getString("file_path"), resultSet.getInt("markup_type"),resultSet.getString("marked_text")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
		return res;}
		return res;
	}
	
	
	public void closeBd(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		BD bd = new BD("Sony_Reader\\database\\books.db");
		System.out.println("connected");
		ArrayList<Note> list= new ArrayList<Note> (bd.getBookNotes(27));
		ArrayList<Book> list2= new ArrayList<Book> (bd.getAnnotedBooks());
		
		
		for(Book b: list2)
		System.out.println(b.getId()+ "" +b.getTitle());

		bd.closeBd();
	
	}
	

	
}
