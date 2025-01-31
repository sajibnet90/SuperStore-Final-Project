# SuperStore Data Management System 🏪

## Overview
Command-line Java application for managing SuperStore sales data and customer information.

## 🚀 Features
* **Customer Search** - Find customers by name
* **Order Management** - Track customer orders
* **Statistics** - Generate sales analytics
* **Reports** - Create detailed business reports

## 💻 Technical Requirements
* Java JDK 8+
* CSV data file

## 🛠️ Installation
```bash
# Clone repository
git clone https://github.com/yourusername/SuperStore.git

# Navigate to project
cd SuperStore/src/main/java
```

## Running the Application
```bash
# Compile
cd src/main/java
javac *.java

# Run
java SuperStore
```
```bash

📁 Project Structure

SuperStore/
├── src/
│   └── main/
│       ├── java/
│       │   ├── SuperStore.java    # Main app
│       │   ├── Customer.java      # Customer model
│       │   ├── Order.java         # Order model
│       │   └── Product.java       # Product model
│       └── resources/
│           └── data/
│               └── SuperStoreOrders.csv
```

```bash

📊 Data Format

OrderID,OrderDate,ShipDate,CustomerID,CustomerName,Sales,Quantity
```

⚠️ Known Issues
- CSV parsing errors with semicolons
- Special character handling

📄 License
MIT License

