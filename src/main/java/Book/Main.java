package Book;

import javax.crypto.ExemptionMechanism;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        System.out.println("Choose: \n1)Add\n2)GetByNumber\n3)DeleteByNumber\n4)GetCountByNumber\n5)GetByAuthor\n6)GetCountAllBooks");
        switch (scanner.nextInt()) {
            case 1:
                Book book = new Book();
                System.out.println("Add book to bookstore please write book ...");
                try {
                    System.out.println("name:");
                    book.setName(scanner.next());
                    System.out.println("number:");
                    book.setNumber(scanner.nextInt());
                    System.out.println("author:");
                    book.setAuthor(scanner.next());
                    System.out.println("count:");
                    book.setCount(scanner.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Error input info");
                    break;
                }
                printCountRows(bookRepository.addBook(book));
                break;
            case 2:
                System.out.println("Write number:");
                try {
                    System.out.println(bookRepository.getByNumber(scanner.nextInt()));
                } catch (InputMismatchException e) {
                    System.out.println("Error input info");
                    break;
                }
                break;
            case 3:
                System.out.println("Write book number for delete:");
                try {
                    printCountRows(bookRepository.deleteByNumber(scanner.nextInt()));
                } catch (InputMismatchException e) {
                    System.out.println("Error input info");
                    break;
                }
                break;
            case 4:
                System.out.println("Write number:");
                try {
                    System.out.println(bookRepository.getCountByNumber(scanner.nextInt()));
                } catch (InputMismatchException e) {
                    System.out.println("Error input info");
                    break;
                }
                break;
            case 5:
                System.out.println("Write author:");
                try{
                System.out.println(bookRepository.getByAuthor(scanner.next()));
                } catch (InputMismatchException e) {
                    System.out.println("Error input info");
                    break;
                }
                break;
            case 6:
                System.out.println("Cont book in store:" + bookRepository.getCountAllBooks());
                break;
        }
    }

    public static void printCountRows(int rows) {
        System.out.println(rows + " - count row affected");
    }
}
