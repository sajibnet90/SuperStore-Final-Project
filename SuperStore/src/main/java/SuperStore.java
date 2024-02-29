import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SuperStore {
    private static List<Customer> customers;// static is used to make it accessible to all methods
    private Map<String, Order> orders;
    private Map<String, Product> products;

    //constructor to initialize the lists and maps
    public SuperStore() {
        customers = new ArrayList<>();
        orders = new HashMap<>();
        products = new HashMap<>();
    }
    //method to load data from the file

    public void loadData(String filePath) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine();//allows to skip the first line 'Title'.
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");

            String rowId = values[0];
            String customerId =values[5];
            String customerName = values[6];
            String segment = values[7];
            String country = values[8];
            String city = values[9];
            String state = values[10];
            String postalCode = values[11];
            String region = values[12];
            //create a new customer object
            Customer customer = new Customer(rowId, customerId, customerName, segment, country, city, state, postalCode, region);
  
            String orderId = values[1];
            String orderDate = values[2];
            String shipDate = values[3];
            String shipMode = values[4];
            String productId = values[13];
            String category = values[14];
            String subCategory = values[15];
            String productName = values[16];
            String salesString = values[17].replace(",", ".");//to convert the string to double we need to replace the comma with a dot
            Double sales;
            //errror handling for additional ';' in product name
            try {
                 sales = Double.parseDouble(salesString);
            } catch (Exception e) {
                System.err.println("Error parsing sales value because of extra ';' on line: " + line);
                continue; 
            }
            
            int quantity = Integer.parseInt(values[18]);
            double discount = Double.parseDouble(values[19].replace(",", "."));
            double profit = Double.parseDouble(values[20].replace(",", "."));
            //create a new order object
            Order order = new Order(rowId, orderId, orderDate, shipDate, shipMode, customerId, productId, category, subCategory, productName, sales, discount, profit);
            //create a new product object
            Product product = new Product(rowId,productId, category, subCategory, productName,quantity);

            String order_key = rowId + orderId;//to get all orders with same order id//
            String product_key = rowId + productId;//to get all products with same product id
            
            order.addProduct(product);//add the product to the order   
            customer.addOrder(order);//add the order to the customer
            orders.put(order_key, order);//add all the order to the orders map//output:
            customers.add(customer);//add all the customer to the customers list
            products.put(product_key, product);//add all the product to the products map

        }
        br.close();
    }
    //getter for customers list
    public static List<Customer> getCustomers() {
        return customers;
    }
    //getter for orders map
    public Map<String, Order> getOrders() {
        return orders;
    }
    //getter for products map
    public Map<String, Product> getProducts() {
        return products;
    }
    //----------method to search for a customer by name-----------------------
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> matchingCustomers = new ArrayList<>();
        List<Customer> customers = SuperStore.getCustomers();//
        for (Customer customer : customers) {
            if (customer.getCustomerName().toLowerCase().contains(name.toLowerCase())) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }
  //--------------------------------MAIN METHOD---------------------------------
        public static void main(String[] args) {
            SuperStore app = new SuperStore();//create a new SuperStore object
            try {
                app.loadData("/Users/data_course/Desktop/Programming2/Final-Assgn/2023-wk14-final-exercise-sajibnet90/data/SuperStoreOrders.csv");
                Scanner scanner = new Scanner(System.in);

                Boolean quit= false;
                while (!quit) {
                    System.out.println("------------------------\n");
                    System.out.println("Welcome to SuperStore");
                    System.out.println("1. Search by Customer Name");
                    System.out.println("2. Quit");
                    System.out.println("3. Show Statistics ");
                    System.out.println("4. Generate report ");

                    System.out.println("Enter your choice: ");
                    String input = scanner.nextLine();
    
                    switch (input) {
                        case "1":
                            searchCustomers(app);
                            break;
                        case "2":
                            quit = true;
                            break;
                        case "3":
                        showStatistic(app);
                            getCustomersPerSegment(app);
                            break;
                        case "4":
                        salesReport(app);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }

    //-----search customer method --------------
        private static void searchCustomers(SuperStore app) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter customer name:");
            String name = sc.nextLine();
    
            List<Customer> matches = app.searchCustomersByName(name);
            if (matches.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                System.out.println("Found " + matches.size() + " customers:");
                System.out.println("-------------------------------------");
                //System.out.printf("| %-12s | %-18s |\n", "Customer Id", "Customer Name");
                for (Customer customer : matches) {
                    System.out.printf("| %-12s | %-18s |\n", customer.getCustomerId(), customer.getCustomerName());
                }
                System.out.println("-------------------------------------");
                

                System.out.println("Select a customer ID to view orders:");
                String customerId = sc.nextLine();

                // ---------------------select the customer by customer id----------------
                List<Customer> matchingCustomers = SuperStore.getCustomers();
                Customer selectedCustomer = null;// to store the customer object with the given customer id
                for (Customer customer : matchingCustomers) {
                    if (customer.getCustomerId().equals(customerId)) {
                        selectedCustomer = customer;
                        break;
                    }
                }
                
                    // ------------get order details by customer id that is selected ----------------------------
                    List<Order> ordersByCustomer = new ArrayList<>();

                    for (Order order : app.orders.values()) {//loop through all the order objects from orders hashmap
                        if (order.getCustomerId().equals(customerId)) {//checking if the customer id of the order object is equal to the customer id entered by the user
                            ordersByCustomer.add(order);//if yes, add the order object to the ordersByCustomer list
                        }
                    }
                    System.out.println(">All Orders made by customer " + selectedCustomer.getCustomerName() + ":");
                    System.out.println("------------------------------------------------------------");
                    System.out.println("| id (select)| order id \t| order date \t | shipping date |  sales    |");
                    System.out.println("|------------|------------------|----------------|---------------|-----------|");

                    for (Order order : ordersByCustomer) {
                        System.out.printf("| %10s | %10s\t| %10s\t | %10s\t |  %5.2f    |\n",
                                order.getRowId(), order.getOrderId(), order.getOrderDate(), order.getShipDate(), order.getSales());
                    }
                    System.out.println("|------------|------------------|----------------|---------------|-----------|");


                    // -----------getting total sales for the selected customer-----------------
                    //mapToDouble() is called on the Stream object to convert the stream of Order objects to a stream of double values. 
                    //The Order::getSales method reference is used to extract the sales value of each order.
                    double totalSales = ordersByCustomer.stream()
                                        .mapToDouble(Order::getSales).sum();
                    System.out.println("Total sales for customer " + selectedCustomer.getCustomerName() + ": " + totalSales + "EUR");
                    System.out.println("------------------------------------------------------------");

                    
                    // ------------gettting product details by order id for a selected customer------------

                    List<Order> allOrders = new ArrayList<>();
                    for (Customer customer : customers) {
                        allOrders.addAll(customer.getOrders());//get all the orders made by the selected customer
                    }

                    System.out.println("Select an order ID to view products:");
                    String orderId = sc.nextLine();

                    List<Order> selectedOrders = new ArrayList<>();
                    // loop through all orders of the selected customer and add the order to the selectedOrders list
                    //if the order id matches the order id entered by the user
                    for (Order order : allOrders) {
                        if (order.getOrderId().equals(orderId)) {
                            selectedOrders.add(order);
                        }
                    }

                    if (selectedOrders.isEmpty()) {
                        System.out.println("No orders found with the given order ID.");
                    } else {
                        System.out.println("> Products in order " + orderId + ":");
                        System.out.println("--------------------------------------");
                        System.out.printf("| %-12s | %-18s | %-30s | %-8s |\n", "Customer Id", "Product ID", "Product Name", "Quantity");
                        System.out.println("|--------------|--------------------|--------------------------------|----------|");
                        for (Order order : selectedOrders) {                               
                            for (Product product : order.getProducts()) {
                            System.out.printf("| %-12s | %-18s | %-30.30s | %-8d |\n", order.getCustomerId(), product.getProductId(), product.getProductName(), product.getQuantity());
                            }           
                        }
                        System.out.println("|--------------|--------------------|--------------------------------|----------|");

                    }                 
            }
        }


        // ----Statistics method---- for choice 3------------------   
        private static void showStatistic(SuperStore app) {
            //--getting avarage sales amount for the all the orders from orders hashmap--
            System.out.println("|----------------|--------STATISTICS--------|----------------|");
            System.out.println("|----------------|-----------------------|-------------------|");
            double averageSales = app.orders.values().stream()
                            .mapToDouble(Order::getSales).average().getAsDouble();
            System.out.println("Average sales amount for all orders: " + averageSales + " EUR");
            System.out.println("|----------------|-----------------------|-------------------|");

            //--getting amount of customers per state--
            Map<String, Long> customersPerState = app.customers.stream()
            .collect(Collectors.groupingBy(Customer::getState, Collectors.counting()));
            System.out.println("Customers per state: \n"+"--------------------\n" + customersPerState );
            System.out.println("|----------------|-----------------------|------------------------------|");
        }
            
            // How many Corporate, Consumer and Home Office customers are there in SuperStore?
            private static void  getCustomersPerSegment(SuperStore app) {
                Map<String, Long> customersPerSegment = app.customers.stream()
                .collect(Collectors.groupingBy(Customer::getSegment, Collectors.counting()));
                System.out.println("Customers per segment: " + customersPerSegment);
                System.out.println("|----------------|-----------------------|------------------------------|");
                
            }
           
        
        //--A sales report save as  sales-report.txt--
        //total sales, average sales, customers per segment, customers per state
        private static void salesReport(SuperStore app) {
            try {
                FileWriter writer = new FileWriter("sales-report.txt");
                writer.write("----------------------------\n");
                writer.write("Sales report for SuperStore\n");
                writer.write("----------------------------\n");
                writer.write("Total sales: " + app.orders.values().stream()
                .mapToDouble(Order::getSales).sum() + " EUR "+ "\n\n");
                writer.write("----------------------------\n");
                writer.write("Average sales: " + app.orders.values().stream()
                .mapToDouble(Order::getSales).average().getAsDouble() + " EUR "+"\n\n");
                writer.write("----------------------------\n");
                writer.write("Customers per segment: " + app.customers.stream()
                .collect(Collectors.groupingBy(Customer::getSegment, Collectors.counting())) + "\n\n");
                writer.write("----------------------------\n");
                writer.write("Customers per state: " + app.customers.stream()
                .collect(Collectors.groupingBy(Customer::getState, Collectors.counting())) + "\n\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }       

    }


    