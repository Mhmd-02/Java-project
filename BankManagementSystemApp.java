
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    String name;
    int customerId;

    Customer(String name, int customerId) {
        this.name = name;
        this.customerId = customerId;
    }
}

class Account {
    int accountId;
    double balance;
    Customer customer;

    Account(int accountId, Customer customer) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = 0.0;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    double getBalance() {
        return balance;
    }
}

class BankManagementSystem {
    List<Customer> customers;
    List<Account> accounts;
    int customerIdCounter;
    int accountIdCounter;

    BankManagementSystem() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.customerIdCounter = 1;
        this.accountIdCounter = 1;
    }

    void createCustomer(String name) {
        Customer customer = new Customer(name, customerIdCounter++);
        customers.add(customer);
        System.out.println("Customer created successfully. Customer ID: " + customer.customerId);
    }

    void createAccount(int customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            Account account = new Account(accountIdCounter++, customer);
            accounts.add(account);
            System.out.println("Account created successfully. Account ID: " + account.accountId);
        } else {
            System.out.println("Customer not found. Account creation failed.");
        }
    }

    void deposit(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found. Deposit failed.");
        }
    }

    void withdraw(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found. Withdrawal failed.");
        }
    }

    void checkBalance(int accountId) {
        Account account = findAccountById(accountId);
        if (account != null) {
            System.out.println("Account balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found. Balance check failed.");
        }
    }

    private Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.customerId == customerId) {
                return customer;
            }
        }
        return null;
    }

    private Account findAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.accountId == accountId) {
                return account;
            }
        }
        return null;
    }
}

public class BankManagementSystemApp {
    public static void main(String[] args) {
        BankManagementSystem bankSystem = new BankManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume newline
                    String customerName = scanner.nextLine();
                    bankSystem.createCustomer(customerName);
                    break;

                case 2:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    bankSystem.createAccount(customerId);
                    break;

                case 3:
                    System.out.print("Enter account ID: ");
                    int depositAccountId = scanner.nextInt();
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    bankSystem.deposit(depositAccountId, depositAmount);
                    break;

                case 4:
                    System.out.print("Enter account ID: ");
                    int withdrawAccountId = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    bankSystem.withdraw(withdrawAccountId, withdrawAmount);
                    break;

                case 5:
                    System.out.print("Enter account ID: ");
                    int checkBalanceAccountId = scanner.nextInt();
                    bankSystem.checkBalance(checkBalanceAccountId);
                    break;

                case 0:
                    System.out.println("Exiting the Bank Management System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    scanner.close();
            }
        }
    }
}
