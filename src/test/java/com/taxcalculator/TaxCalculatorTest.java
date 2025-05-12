package com.taxcalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculatorTest {

    private static TaxCalculator taxCalculator;

    @BeforeAll
    static void setup() {
        taxCalculator = TaxCalculator.getInstance();
    }

    @Test
    void testZeroIncome() {
        assertEquals(new BigDecimal("0.00"), taxCalculator.calculateTax(new BigDecimal("0")));
    }

    @Test
    void testIncomeJustBelowFirstSlab() {
        assertEquals(new BigDecimal("0.00"), taxCalculator.calculateTax(new BigDecimal("349999")));
    }

    @Test
    void testIncomeExactlyAtFirstSlab() {
        assertEquals(new BigDecimal("0.00"), taxCalculator.calculateTax(new BigDecimal("350000")));
    }

    @Test
    void testIncomeWithinSecondSlab() {
        // 350001 - 625000: taxed at 9% on 275000
        BigDecimal expected = new BigDecimal("24750.00");
        assertEquals(expected, taxCalculator.calculateTax(new BigDecimal("625000")));
    }

    @Test
    void testIncomeExactlyAtSecondSlabUpperLimit() {
        // Income: 625000
        // 0% for first 3.5L, 9% of 275000 = 24750
        assertEquals(new BigDecimal("24750.00"), taxCalculator.calculateTax(new BigDecimal("625000")));
    }

    @Test
    void testIncomeWithinThirdSlab() {
        // Income = 900000
        // 0% of 350000 = 0
        // 9% of 275000 = 24750
        // 18% of 275000 = 49500
        // Total = 74250
        assertEquals(new BigDecimal("74250.00"), taxCalculator.calculateTax(new BigDecimal("900000")));
    }

    @Test
    void testIncomeExactlyAtThirdSlabUpperLimit() {
        // Income = 1200000
        // 0% on 3.5L = 0
        // 9% on 275000 = 24750
        // 18% on 575000 = 103500
        assertEquals(new BigDecimal("128250.00"), taxCalculator.calculateTax(new BigDecimal("1200000")));
    }

    @Test
    void testIncomeInHighestSlab() {
        // Income = 2500000
        // 0% on 3.5L = 0
        // 9% on 275000 = 24750
        // 18% on 575000 = 103500
        // 35% on remaining 1300000 = 455000
        // Total = 583250
        assertEquals(new BigDecimal("583250.00"), taxCalculator.calculateTax(new BigDecimal("2500000")));
    }

    @Test
    void testIncomeAtUpperEnd() {
        // Income = 10000000 (1 crore)
        // 0% on 3.5L
        // 9% on 2.75L = 24750
        // 18% on 5.75L = 103500
        // 35% on 88.0L = 3080000
        assertEquals(new BigDecimal("3208250.00"), taxCalculator.calculateTax(new BigDecimal("10000000")));
    }

    @Test
    void testNegativeIncomeShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
            () -> taxCalculator.calculateTax(new BigDecimal("-1000")));
    }

    @Test
    void testNullIncomeShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
            () -> taxCalculator.calculateTax(null));
    }
}
