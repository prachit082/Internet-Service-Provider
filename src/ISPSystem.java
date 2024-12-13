import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private double outstandingBalance;
    private InternetPlan plan;

    public Customer(int id, String name, String email, String password, double outstandingBalance, InternetPlan plan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.plan = plan;
        this.password = password;
        this.outstandingBalance = outstandingBalance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InternetPlan getPlan() {
        return plan;
    }

    public void setPlan(InternetPlan plan) {
        this.plan = plan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Plan: " + plan.getName() + ", Outstanding Balance: $" + outstandingBalance;
    }
}

class InternetPlan {
    private String name;
    private double speed;
    private double price;

    public InternetPlan(String name, double speed, double price) {
        this.name = name;
        this.speed = speed;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPrice() {
        return price;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Speed: " + speed + " Mbps, Price: $" + price;
    }
}


public class ISPSystem {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<InternetPlan> plans = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- ISP Management System ---");
            System.out.println("1. Admin Panel");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminPanel();
                    break;
                case 2:
                if (customerLogin()) {
                    customerPanel();
                }
                break;
            case 3:
                System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminPanel() {
    while (true) {
        System.out.println("\n--- Admin Panel ---");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.println("5. Add Internet Plan");
        System.out.println("6. View All Plans");
        System.out.println("7. Delete Plan");
        System.out.println("8. Update Plan");
        System.out.println("9. Back to Main Menu");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                viewAllCustomers();
                break;
            case 3:
                updateCustomer();
                break;
            case 4:
                deleteCustomer();
                break;
            case 5:
                addInternetPlan();
                break;
            case 6:
                viewAllPlans();
                break;
            case 7:
                deletePlan();
                break;
            case 8:
                updatePlan();
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}


    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Customer Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Outstanding Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        InternetPlan plan = selectPlan(); // Use the existing selectPlan() method
    if (plan != null) {
        customers.add(new Customer(id, name, email, password, balance, plan));
        System.out.println("Customer added successfully.");
    } else {
        System.out.println("Failed to add customer. Plan selection failed.");
    }
    }

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\n--- Customer List ---");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void updateCustomer() {
    System.out.print("Enter Customer ID to update: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    for (Customer customer : customers) {
        if (customer.getId() == id) {
            System.out.print("Enter new Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) customer.setName(name);

            System.out.print("Enter new Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) customer.setEmail(email);

            System.out.print("Enter new Password (leave blank to keep current): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) customer.setPassword(password);

            System.out.print("Enter new Outstanding Balance (-1 to keep current): ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            if (balance != -1) customer.setOutstandingBalance(balance);

            InternetPlan newPlan = selectOrCreatePlan();
            if (newPlan != null) {
                customer.setPlan(newPlan);
                System.out.println("Plan updated successfully.");
            }

            System.out.println("Customer updated successfully.");
            return;
        }
    }
    System.out.println("Customer not found.");
}

    private static void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                System.out.println("Customer deleted successfully.");
                return;
            }
        }
        System.out.println("Customer not found.");
    }
    
    private static void addInternetPlan() {
        System.out.print("Enter plan name: ");
        String name = scanner.nextLine();
        System.out.print("Enter plan speed (Mbps): ");
        double speed = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter plan price: $");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        plans.add(new InternetPlan(name, speed, price));
        System.out.println("Internet plan added successfully.");
    }

    private static void viewAllPlans() {
        if (plans.isEmpty()) {
            System.out.println("No plans available.");
            return;
        }
        System.out.println("\n--- Available Plans ---");
        for (int i = 0; i < plans.size(); i++) {
            System.out.println((i + 1) + ". " + plans.get(i));
        }
    }

    private static InternetPlan selectPlan() {
        viewAllPlans();
        System.out.print("Enter the plan number to select: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= plans.size()) {
            return plans.get(choice - 1);
        } else {
            System.out.println("Invalid choice. No plan selected.");
            return null;
        }
    }
    
    private static InternetPlan selectOrCreatePlan() {
    System.out.println("Do you want to:");
    System.out.println("1. Select an existing plan");
    System.out.println("2. Create a new plan");
    System.out.print("Enter your choice (1/2): ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1:
            return selectPlan();
        case 2:
            return createNewPlan();
        default:
            System.out.println("Invalid choice. No plan selected.");
            return null;
    }
    }

    private static InternetPlan createNewPlan() {
    System.out.print("Enter new plan name: ");
    String name = scanner.nextLine();
    System.out.print("Enter new plan speed (Mbps): ");
    double speed = scanner.nextDouble();
    scanner.nextLine(); // Consume newline
    System.out.print("Enter new plan price: $");
    double price = scanner.nextDouble();
    scanner.nextLine(); // Consume newline

    InternetPlan newPlan = new InternetPlan(name, speed, price);
    plans.add(newPlan);
    System.out.println("New plan created successfully.");
    return newPlan;
}

    private static void deletePlan() {
    viewAllPlans();
    System.out.print("Enter the plan number to delete: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    InternetPlan planToDelete = null; // Declare planToDelete here

    if (choice > 0 && choice <= plans.size()) {
        planToDelete = plans.remove(choice - 1); // Assign to planToDelete
        System.out.println("Plan '" + planToDelete.getName() + "' deleted successfully.");
    } else {
        System.out.println("Invalid choice. Plan deletion failed.");
        return; // Return early if deletion fails
    }

    // Now planToDelete is accessible here
    // Update customers who were assigned the deleted plan
    for (Customer customer : customers) {
        if (customer.getPlan() != null && customer.getPlan().getName().equals(planToDelete.getName())) {
            customer.setPlan(null);
            System.out.println("Customer " + customer.getName() + "'s plan has been removed.");
        }
    }
}

    private static void updatePlan() {
    viewAllPlans();
    System.out.print("Enter the plan number to update: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (choice > 0 && choice <= plans.size()) {
        InternetPlan planToUpdate = plans.get(choice - 1);
        
        System.out.print("Enter new plan name (press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) planToUpdate.setName(newName);

        System.out.print("Enter new plan speed (Mbps) (press Enter to keep current): ");
        String speedInput = scanner.nextLine();
        if (!speedInput.isEmpty()) {
            try {
                planToUpdate.setSpeed(Double.parseDouble(speedInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid speed input. Keeping current speed.");
            }
        }

        System.out.print("Enter new plan price ($ press Enter to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                planToUpdate.setPrice(Double.parseDouble(priceInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input. Keeping current price.");
            }
        }

        System.out.println("Plan updated successfully.");
    } else {
        System.out.println("Invalid choice. Plan update failed.");
    }
}


    private static void customerPanel() {
        System.out.print("Enter your Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                while (true) {
                    System.out.println("\n--- Customer Panel ---");
                    System.out.println("1. View Details");
                    System.out.println("2. Update Details");
                    System.out.println("3. Change Password");
                    System.out.println("4. View Billing Information");
                    System.out.println("5. Request Support");
                    System.out.println("6. Logout");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.println(customer);
                            break;
                        case 2:
                            updateCustomerDetails(customer);
                            break;
                        case 3:
                            changePassword(customer);
                            break;
                        case 4:
                            viewBillingInfo(customer);
                            break;
                        case 5:
                            requestSupport(customer);
                            break;
                        case 6:
                            logout();
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
        System.out.println("Customer not found.");
    }
    
    private static Customer loggedInCustomer = null;

    private static boolean customerLogin() {
    System.out.print("Enter your email: ");
    String email = scanner.nextLine();
    System.out.print("Enter your password: ");
    String password = scanner.nextLine();

    for (Customer customer : customers) {
        if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
            loggedInCustomer = customer;
            System.out.println("Login successful. Welcome, " + customer.getName());
            return true;
        }
    }
    System.out.println("Invalid email or password. Please try again.");
    return false;
}

    private static void updateCustomerDetails(Customer customer) {
    System.out.print("Enter new Name (leave blank to keep current): ");
    String name = scanner.nextLine();
    if (!name.isEmpty()) customer.setName(name);

    System.out.print("Enter new Email (leave blank to keep current): ");
    String email = scanner.nextLine();
    if (!email.isEmpty()) customer.setEmail(email);

    System.out.print("Enter new Password (leave blank to keep current): ");
    String password = scanner.nextLine();
    if (!password.isEmpty()) customer.setPassword(password);

    InternetPlan newPlan = selectOrCreatePlan();
    if (newPlan != null) {
        customer.setPlan(newPlan);
        System.out.println("Plan updated successfully.");
    }

    System.out.println("Details updated successfully.");
}

    private static void changePassword(Customer customer) {
        System.out.print("Enter new Password: ");
        String password = scanner.nextLine();
        customer.setPassword(password);
        System.out.println("Password updated successfully.");
    }

    private static void viewBillingInfo(Customer customer) {
        System.out.println("Outstanding Balance: $" + customer.getOutstandingBalance());
    }

    private static void requestSupport(Customer customer) {
        System.out.print("Enter your support request: ");
        String request = scanner.nextLine();
        System.out.println("Support request submitted: " + request);
        System.out.println("Our team will contact you soon.");
    }
    
    private static void logout() {
    loggedInCustomer = null;
    System.out.println("Logged out successfully.");
}
}