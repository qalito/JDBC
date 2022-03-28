package Book;

import java.util.List;

public interface IBook {
    int addBook(Book book);
    Book getByNumber(int number);
    int deleteByNumber(int number);
    int getCountByNumber(int number);
    List<Book> getByAuthor(String author);
    int getCountAllBooks();
}
