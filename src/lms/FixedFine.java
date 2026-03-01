/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
public class FixedFine implements FineCalculationStrategy {
 
    @Override
    public int calculateFine(int num) {
        return  FIXED_FINE;
    }
    
}
