import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineBankingSystem {
    private List<Account> accounts;
    private Scanner scanner;

    public OnlineBankingSystem() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        scanner.nextLine(); // Consume the newline character
        double balance;
        while (true) {
            System.out.print("Enter initial balance: ");
            if (scanner.hasNextDouble()) {
                balance = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
        int accountNumber = accounts.size() + 1; // Generate a unique account number
        Account account = new Account(name, balance, accountNumber); // Pass the account number to the Account constructor
        accounts.add(account);
        System.out.println("Account created successfully! Your account number is: " + accountNumber);
    }

    public void deposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        OnlineBankingSystem system = new OnlineBankingSystem();
        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = system.scanner.nextInt();
            switch (choice) {
                case 1:
                    system.createAccount();
                    break;
                case 2:
                    system.deposit();
                    break;
                case 3:
                    system.withdraw();
                    break;
                case 4:
                    system.checkBalance();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

class Account {
    private int accountNumber;
    @SuppressWarnings("unused")
    private String accountHolderName;
    private double balance;

    public Account(String accountHolderName, double balance, int accountNumber) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}