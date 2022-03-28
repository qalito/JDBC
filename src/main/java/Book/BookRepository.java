package Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class BookRepository implements IBook {
    private Connection connection = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/basetest";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1111";


    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        }
        return connection;
    }

    private List<Book> executeQuery(String sql) {
        List<Book> bookList = new ArrayList<Book>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setNumber(resultSet.getInt("NUMBER"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setCount(resultSet.getInt("COUNT"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    private int executeQueryCount(String sql) {
        int count = 0;

        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private int executeUpdate(String sql) {
        int rows = 0;
        try (Statement statement = getConnection().createStatement()) {
            rows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int addBook(Book book) {
        int rows = 0;
        Book currentBook = getByNumber(book.getNumber());
        if (currentBook == null) {
            rows = executeUpdate(generateInsert(book));
        } else {
            currentBook.setCount(currentBook.getCount() + book.getCount());
            rows = executeUpdate(generateUpdateCountByNumber(currentBook));
        }
        return rows;
    }

    private String generateInsert(Book book) {
        return "INSERT INTO BOOKSTORE (NAME, NUMBER, AUTHOR, COUNT)" +
                " VALUES ('" + book.getName() + "', " + book.getNumber() + ", '" + book.getAuthor() + "', " + book.getCount() + " )";
    }

    private String generateUpdateCountByNumber(Book book) {
        return "UPDATE BOOKSTORE SET COUNT = " + book.getCount() +
                " WHERE NUMBER = " + book.getNumber() + " ";
    }

    @Override
    public Book getByNumber(int number) {
        List<Book> books = executeQuery("SELECT * FROM BOOKSTORE WHERE NUMBER = " + number + "");
        return books.size() > 0 ? books.get(0) : null;
    }

    @Override
    public int deleteByNumber(int number) {
        int rows = 0;
        rows = executeUpdate("DELETE FROM BOOKSTORE WHERE NUMBER = " + number);
        return rows;
    }

    @Override
    public int getCountByNumber(int number) {
        return executeQueryCount("SELECT COUNT FROM BOOKSTORE WHERE NUMBER = " + number);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        List<Book> books = executeQuery("SELECT * FROM BOOKSTORE WHERE AUTHOR = '" + author + "'");
        return books;
    }

    @Override
    public int getCountAllBooks() {
        return executeQueryCount("SELECT SUM(COUNT) FROM BOOKSTORE");
    }
}
