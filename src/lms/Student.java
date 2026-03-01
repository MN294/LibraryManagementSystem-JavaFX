/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
public class Student extends Member {
     private static final int MAX_BOOKS = 5; // Students can borrow up to 5 books

    public Student(String memberID, String name, String contact) {
        super(memberID, name, contact);
        
    }
     @Override
    public int getMaxBooksAllowed(){
    return MAX_BOOKS;
    }

    
}