package com.taxcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxSlabHandler
{
    private final BigDecimal lowerLimit;
    private final BigDecimal upperLimit;
    private final BigDecimal rate;
    private final TaxSlabHandler next;

    public TaxSlabHandler(BigDecimal lowerLimit, BigDecimal upperLimit, BigDecimal rate, TaxSlabHandler next)
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.rate = rate;
        this.next = next;
    }

    public BigDecimal handle(BigDecimal income)
    {
        if (income.compareTo(lowerLimit) <= 0)
        {
            return next != null ? next.handle(income) : BigDecimal.ZERO;
        }

        BigDecimal taxable = (upperLimit == null ? income : income.min(upperLimit))
            .subtract(lowerLimit);

        BigDecimal tax = taxable.multiply(rate)
            .setScale(2, RoundingMode.HALF_UP);

        BigDecimal nextTax = next != null ? next.handle(income) : BigDecimal.ZERO;

        return tax.add(nextTax).setScale(2, RoundingMode.HALF_UP);
    }
}
