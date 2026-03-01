/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
import java.io.*;
import java.util.*;

public class Library implements Serializable {

    private String libName;
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<BorrowedBook> borrowedbooks;
    Calendar c = Calendar.getInstance();

    Library(String libName) {
        this.libName = libName;
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowedbooks = new ArrayList<>(1);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<BorrowedBook> getBorrowedbooks() {
        return borrowedbooks;
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public void addMember(Member m) {
        members.add(m);
    }

    public Book searchBooks(String name) {
        Book b = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(name)) {
                b = books.get(i);
                break;
            }
        }
        return b;
    }

    public ArrayList<Book> searchBooksByAuthor(String author) {
        ArrayList<Book> booksByAuthor = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().equalsIgnoreCase(author)) {
                booksByAuthor.add(books.get(i));
            }
        }
        return booksByAuthor;
    }

    public Book searchbyISBN(String isbn) {
        Book b = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(isbn)) {
                b = books.get(i);
                break;
            }
        }
        return b;
    }

    public Member searchbyID(String id) {
        Member m = null;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberID().equals(id)) {
                m = members.get(i);
                break;
            }
        }
        return m;
    }

    public void Borrow(String isbn, String mID) {
        Book b = searchbyISBN(isbn);
        Member m = searchbyID(mID);
        if (b != null && m != null && b.getAvailableCopies() > 0 && m.canBorrow()) {
            //c.set(2025, Calendar.JANUARY, 1);
            Date d1 = c.getTime();
            System.out.println(d1);
            c.add(Calendar.DAY_OF_YEAR, 7);
            Date d2 = c.getTime();
            System.out.println(d2);
            b.modifyAvailableCopies(2, 1);
            m.incrementBorrowed();
            borrowedbooks.add(new BorrowedBook(b, m, d1, d2));
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book borrowing is unavailable.");
        }
    }

    public void Return(String isbn, String mID) {
        Book b = searchbyISBN(isbn);
        Member m = searchbyID(mID);
        //c.add(Calendar.DAY_OF_YEAR, 8);
        Date returnDate = c.getTime();
        System.out.println(returnDate);
        for (int i = 0; i < borrowedbooks.size(); i++) {
            if (borrowedbooks.get(i).getBook().equals(b)) {

                if (borrowedbooks.get(i).getDueDate().before(returnDate)) {
                    if (m.getMaxBooksAllowed() == 10) {
                        Date d = borrowedbooks.get(i).getDueDate();
                        long diffms = returnDate.getTime() - d.getTime();
                        long diffInDays = diffms / (1000 * 60 * 60 * 24);
                        CalculatePerDay c = new CalculatePerDay();
                        System.out.println("The fine is:" + c.calculateFine((int) diffInDays));
                    } else {
                        FixedFine fi = new FixedFine();
                        System.out.println("The fine is:" + fi.calculateFine(1));

                    }

                } else {
                    System.out.println("Book returned early.No fines.");
                }
                borrowedbooks.remove(i);
                System.out.println("Book " + b.getISBN() + " returned successfully.");
                b.modifyAvailableCopies(1, 1);
                m.decrementBorrowed();
            } else {
                System.out.println("Book can't be returned as it wasn't borrowed.");
            }
        }
    }

    public void displayBooks() {
        System.out.println("Available Books are.");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null) {
                System.out.println(books.get(i).toString());
            }

        }
    }

    public void displayMembers() {
        System.out.println("Available Members are.");
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i) != null) {
                System.out.println(members.get(i).toString());
            }

        }
    }

}
