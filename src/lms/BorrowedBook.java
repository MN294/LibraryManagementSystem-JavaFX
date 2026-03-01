/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
import java.io.Serializable;
import java.util.Date;
public class BorrowedBook implements Serializable {
    
    private Book book;
    private Member member;
    private Date borrowDate,dueDate;
   public BorrowedBook (Book book,Member member,Date borrowDate,Date dueDate){
   this.book=book;
   this.member=member;
   this.borrowDate=borrowDate;
   this.dueDate=dueDate;
   }
   
   public Book getBook(){
   return book;
   }
   public Member getMember(){
   return member;
   }
   public Date getBorrowDate(){
    return borrowDate;
   }
    public Date getDueDate(){
    return dueDate;
   }
}
