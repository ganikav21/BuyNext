# BuyNext – Testing Documentation

## 1. Objective

To verify that all major functionalities of the BuyNext Product Recommendation System work correctly.

## 2. Test Cases

| Test ID | Feature | Test Input | Expected Result |
|---|---|---|---|
| TC01 | Product recommendations | Laptop | Related products are displayed |
| TC02 | Customer recommendations | Customer ID 1 | Personalized recommendations are displayed |
| TC03 | Bestsellers | Menu option 3 | Top 3 bestselling products are displayed |
| TC04 | New customer | Customer ID 10 | Bestseller recommendations are displayed |
| TC05 | Co-purchase analytics | Menu option 5 | Top 5 product pairs are displayed |
| TC06 | Invalid menu input | 9 | Error message is displayed |
| TC07 | Invalid product | Tablet | Product not found message |
| TC08 | Invalid customer input | abc | Invalid customer ID message |
| TC09 | Exit | Menu option 6 | Application terminates |

## 3. Compilation and Execution

Compile all Java source files using the Java compiler.

Run the application using the Java runtime.

## 4. Testing Status

Test cases should be executed and their actual results recorded before submission.