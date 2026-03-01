/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

import java.io.Serializable;

/**
 *
 * @author mariam_youssef
 */
public class Book implements Serializable {

    private String ISBN, title, author;
    private int ID, availableCopies;

    Book(int ID, String title, String author, int availableCopies, String ISBN) {
        this.ID = ID;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void isAvailable(String name) {
        //   boolean exist = false;
        if (getTitle().equalsIgnoreCase(name)) {
            //exist = true;
            System.out.println("The book is available");
        } else {
            System.out.println("The book is not available");
        }
    }

    public void modifyAvailableCopies(int ch, int n) {
        if (ch == 1) {
            availableCopies += n;
        } else {
            if (n <= availableCopies) {
                availableCopies -= n;
            } else {
                System.out.println("Not enough available copies");
            }
        }

    }

    @Override
    public String toString() {

        return "The book title is:" + title + " ,the author: " + author + " ,the ISBN:" + ISBN + " ,Number of available copies:" + availableCopies;

    }
}

