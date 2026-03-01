/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
import java.io.*;
public interface FineCalculationStrategy extends Serializable{
  int FINE_PER_DAY=5; 
  int FIXED_FINE=30;
  int calculateFine(int num);
}
