package com.taxcalculator;

import java.math.BigDecimal;
public class App
{
    public static void main(String[] args)
    {
        BigDecimal income = new BigDecimal("2500000");
        BigDecimal tax = TaxCalculator.getInstance().calculateTax(income);
        System.out.println("Total Tax: ₹" + tax);
    }
}
