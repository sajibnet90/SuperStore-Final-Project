[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/K1BvqJPI)
The code is for a simple console-based application for managing products, orders and customers for a SuperStore.

To run the program, you need to install any IDE ( like VS code, Intellije etc). You also need java JDK installed in the system. You can complie all .java files and then run SuperStore.java file which have the main method as entry point.
You must have the SuperStoreOrders.csv file which contains the dataset.

BUGS: There are some error in the data set in some rows under 'product name' field which contains extra 
semi-colon ';' . So i had to implement error handling for those data point and program skips those data points.

The program has 4 classes Customer, Order,Product and SuperStore to represent the data from the SuperStoreOrders.csv file. The SuperStore class contains methods for loading the data, searching for customers by name.

The main method of the application provides a menu for the user to select different options. The available options are:

1. Search customers by name
2. Quit the application
3. Show statistics
4. Generate report

1. The 'searchCustomers' method allows the user to search for a customer by name (First name or Last name or Full name ) and select a customer to view their orders. The method first prompts the user to enter a customer name and then searches for all customers with matching names using the 'searchCustomersByName' method of the SuperStore class. If there are no matches, a message is printed to the console. Otherwise, the matching customers  and their customer ID are printed to the console, and the user is prompted to select a customer ID to view their orders.

If a customer is selected, the ordersByCustomer list is populated with all orders for the selected customer. These orders are then printed to the console along with the total sales for all orders of the Customer. It also represent id(rowID), order id, order date, shipping date and sales amount for each order.

Next, the user is prompted to select an order ID to view the products in the order. If the order ID entered by the user matches other order ID in the selected customer's orders, then all the products in that order are printed to the console. This table shows Customer ID, Product id, product name and Quantity for the selected Order ID.

2. Select to quit the application

3. The 'showStatistic' method prints some statistics about the data in the SuperStore. It shows Average sales for all orders in the DataSet. Customers per state and Customer per Segment(Consumer,Corporate,Home Office)

4. The salesReport() method is responsible for generating a sales report for SuperStore, which includes the total sales, average sales, number of customers per segment, and number of customers per state. This report is saved as a text file named "sales-report.txt".

