# 💰 Tax Calculator

A production-grade, extensible Tax Calculator that computes individual tax based on dynamic slabs using the **Chain of Responsibility** design pattern.  
Built with Java and Maven, and tested with JUnit 5 and JaCoCo.

---

## 🧩 Problem Statement

Calculate the total tax to be paid by an individual based on income and the following slabs:

| Income Slab (₹)           | Tax Rate |
|---------------------------|----------|
| 0 - 3,50,000              | 0%       |
| 3,50,001 - 6,25,000       | 9%       |
| 6,25,001 - 12,00,000      | 18%      |
| 12,00,001 and above       | 35%      |

🧮 **Example:** For ₹25,00,000 income
- 0% on first 3.5L = 0
- 9% on next 2.75L = 24,750
- 18% on next 5.75L = 1,03,500
- 35% on remaining 13L = 4,55,000
- **Total Tax = ₹5,83,250**

---

## 🛠 Tech Stack

- Java 17+
- Maven
- JUnit 5
- JaCoCo (Code Coverage)
- Design Pattern: Chain of Responsibility
- Immutability & Precision: `BigDecimal`

---

## 📦 Project Structure

tax-calculator/
├── src/
│ ├── main/
│ │ └── java/com/taxcalculator/
│ │ ├── TaxCalculator.java
│ │ └── TaxSlabHandler.java
│ │ └── App.java
│ └── test/
│ └── java/com/taxcalculator/
│ └── TaxCalculatorTest.java
├── pom.xml
└── README.md

---

## 🚀 Getting Started
```bash
git clone https://github.com/Mani-Kanta-Reddy/tax-calculator
```

### 1. Build the Project
```bash
mvn clean install
```

### 2. ✅ Run Tests
```bash
mvn test
```

### 3. 📊 Generate and View Code Coverage (JaCoCo)
```bash
# Run tests and generate coverage
mvn jacoco:prepare-agent test jacoco:report
```

### 4. View HTML Report
```
target/site/jacoco/index.html
```

