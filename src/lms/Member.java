/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;


import java.io.*;

/**
 *
 * @author mariam_youssef
 */

public abstract class Member implements Serializable {
     
    private String memberID,name,contact;
    private int borrowed;
    Member(String memberID,String name,String contact){
        this.memberID=memberID;
        this.name=name;
        this.contact=contact;
        borrowed=0;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
      public int getBorrowed() {
        return borrowed;
    }

    public void incrementBorrowed() {
        borrowed++;
    }

    public void decrementBorrowed() {
        borrowed--;
    }
     public abstract int getMaxBooksAllowed();
     
     public boolean canBorrow() {
        return borrowed < getMaxBooksAllowed();
    }
    public boolean isActive(String memberID){
    boolean active=false;
     if(borrowed!=0)
       active=true;
    return active;
}
    
    public  int getMaxBorrow(){
    return 0;}
    
    @Override
       public String toString(){
//        if(m==null) Should be written in main
//         return "Member not found";
        
    return "The member ID is:"+memberID+" ,the name: "+name+" ,the contact number:"+contact;
           
    }
       
    
}
