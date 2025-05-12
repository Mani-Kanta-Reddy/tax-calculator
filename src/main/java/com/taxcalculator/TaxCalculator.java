package com.taxcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator
{

    private final TaxSlabHandler taxSlabChain;

    private TaxCalculator()
    {
        taxSlabChain = buildTaxSlabChain();
    }

    public static TaxCalculator getInstance()
    {
        return Holder.INSTANCE;
    }

    public BigDecimal calculateTax(BigDecimal income)
    {
        if (income == null || income.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("Income must be non-null and non-negative");
        }

        return taxSlabChain.handle(income).setScale(2, RoundingMode.HALF_UP);
    }

    private TaxSlabHandler buildTaxSlabChain()
    {
        TaxSlabHandler slab4 = new TaxSlabHandler(
            new BigDecimal("1200000"),
            null,
            new BigDecimal("0.35"),
            null
        );

        TaxSlabHandler slab3 = new TaxSlabHandler(
            new BigDecimal("625000"),
            new BigDecimal("1200000"),
            new BigDecimal("0.18"),
            slab4
        );

        TaxSlabHandler slab2 = new TaxSlabHandler(
            new BigDecimal("350000"),
            new BigDecimal("625000"),
            new BigDecimal("0.09"),
            slab3
        );

        TaxSlabHandler slab1 = new TaxSlabHandler(
            BigDecimal.ZERO,
            new BigDecimal("350000"),
            BigDecimal.ZERO,
            slab2
        );

        return slab1;
    }

    private static class Holder
    {
        private static final TaxCalculator INSTANCE = new TaxCalculator();
    }
}
