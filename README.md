# BuyNext – Product Recommendation System

## Project Overview

BuyNext is a Java-based product recommendation system that analyzes customer purchase history to identify frequently purchased product combinations and generate product recommendations.

The system uses co-purchase frequency to suggest products that customers may be interested in purchasing together.

## Features

1. **Product-Based Recommendations** – Suggests products frequently purchased alongside a selected product.
2. **Customer-Specific Recommendations** – Generates recommendations using a customer's previous purchase history.
3. **Bestseller Recommendations** – Identifies the most frequently purchased products.
4. **New Customer Recommendations** – Uses bestseller information when a customer has no purchase history.
5. **Co-Purchase Analytics** – Displays the most frequently purchased product combinations.
6. **Interactive Menu** – Allows users to access different recommendation features through a console interface.
7. **Input Validation** – Handles invalid menu choices and customer inputs.

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections Framework
- HashMap
- ArrayList
- HashSet
- Sorting and Comparator

## Project Structure

```text
BuyNext/
│
├── main.java
├── Product.java
├── Order.java
├── ProductPair.java
├── CoPurchaseCounter.java
├── RecommendationService.java
├── BuyNextTest.java
├── README.md
└── TESTING.md
```

## How It Works

1. Product information is stored in a product catalogue.
2. Customer orders are created with their purchased products.
3. The system identifies unique products within each order.
4. Co-purchase frequencies are calculated.
5. Recommendation services process purchase information.
6. Products are ranked and displayed according to their purchase frequency.

## How to Run

Make sure Java JDK is installed.

Compile the project:

```bash
javac Main.java Product.java Order.java ProductPair.java CoPurchaseCounter.java RecommendationService.java
```

Run the application:

```bash
java Main
```

## Current Implementation

The current version uses sample product and customer order data stored in the Java application.

## Future Enhancements

- Database integration.
- Dynamic product and customer management.
- Graphical user interface.
- Machine learning-based recommendations.
- Real-time recommendation generation.
- Performance evaluation using larger datasets.