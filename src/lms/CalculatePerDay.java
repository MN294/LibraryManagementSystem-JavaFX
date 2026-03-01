/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
public class CalculatePerDay implements FineCalculationStrategy {

    @Override
    public int calculateFine(int numDays) {
        int fine=FINE_PER_DAY*numDays;
     return fine;
    }
    
}
