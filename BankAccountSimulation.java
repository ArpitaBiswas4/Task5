import java.util.Scanner;

// Main application class
public class BankAccountSimulation {
    private static Bank bank;
    private static Scanner scanner = new Scanner(System.in);
    private static Account currentAccount = null; // For logged-in user

    public static void main(String[] args) {
        System.out.println("        BANK ACCOUNT SIMULATION SYSTEM         ");
        System.out.println("           Welcome to Digital Bank             ");

        bank = new Bank("Digital Bank");
        initializeSampleAccounts();

        int choice = 0;
        do {
            if (currentAccount == null) {
                displayGuestMenu();
            } else {
                displayUserMenu();
            }

            choice = getIntInput("\nEnter your choice: ");

            if (currentAccount == null) {
                handleGuestMenu(choice);
            } else {
                handleUserMenu(choice);
            }
        } while (choice != 0);

        scanner.close();
    }

    // Display menu for guests (not logged in)
    private static void displayGuestMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("Press 1 for Create New Account");
        System.out.println("Press 2 for Login to Account");
        System.out.println("Press 3 for View Bank Statistics");
        System.out.println("Press 0 for Exit");
        System.out.println("=".repeat(60));
    }

    // Display menu for logged-in users
    private static void displayUserMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           USER MENU - " + currentAccount.getAccountHolderName());
        System.out.println("=".repeat(60));
        System.out.println("Press 1 for Check Balance");
        System.out.println("Press 2 for Deposit Money");
        System.out.println("Press 3 for Withdraw Money");
        System.out.println("Press 4 for Transfer Money");
        System.out.println("Press 5 for View Transaction History");
        System.out.println("Press 6 for Generate Account Statement");
        System.out.println("Press 7 for View Account Details");
        System.out.println("Press 8 for Logout");
        System.out.println("Press 0 for Exit");
        System.out.println("=".repeat(60));
    }

    // Handle guest menu
    private static void handleGuestMenu(int choice) {
        switch (choice) {
            case 1:
                createNewAccount();
                break;
            case 2:
                loginToAccount();
                break;
            case 3:
                bank.getBankStatistics();
                break;
            case 0:
                System.out.println("\nThank you for using " + bank.getBankName() + "!");
                break;
            default:
                System.out.println("\nInvalid choice! Please try again.");
        }
    }

    // Handle user menu
    private static void handleUserMenu(int choice) {
        switch (choice) {
            case 1:
                currentAccount.checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                transferMoney();
                break;
            case 5:
                viewTransactionHistory();
                break;
            case 6:
                currentAccount.generateStatement();
                break;
            case 7:
                System.out.println(currentAccount.getDetailedInfo());
                break;
            case 8:
                logout();
                break;
            case 0:
                System.out.println("\nThank you for using " + bank.getBankName() + "!");
                break;
            default:
                System.out.println("\nInvalid choice! Please try again.");
        }
    }

    // Create new account
    private static void createNewAccount() {
        System.out.println("\n--- CREATE NEW ACCOUNT ---");

        String name = getStringInput("Enter account holder name: ");
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.println("\nSelect Account Type:");
        System.out.println("Press 1 for Savings Account (Min. Balance: ₹500)");
        System.out.println("Press 2 for Current Account (Min. Balance: ₹1000)");
        int typeChoice = getIntInput("Enter choice: ");

        String accountType = typeChoice == 1 ? "SAVINGS" : "CURRENT";
        double minDeposit = typeChoice == 1 ? 500.0 : 1000.0;

        double initialDeposit = getDoubleInput("Enter initial deposit (Min. ₹" +
                String.format("%.2f", minDeposit) + "): ");

        if (initialDeposit < minDeposit) {
            System.out.println("✗ Initial deposit must be at least ₹" +
                    String.format("%.2f", minDeposit));
            return;
        }

        Account account = bank.createAccount(name, accountType, initialDeposit);
        System.out.println("\n✓ Account created successfully!");
        System.out.println(account.getDetailedInfo());
    }

    // Login to account
    private static void loginToAccount() {
        System.out.println("\n--- LOGIN ---");

        String accountNumber = getStringInput("Enter account number: ");
        Account account = bank.findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        if (!account.isActive()) {
            System.out.println("Account is inactive! Please contact bank.");
            return;
        }

        currentAccount = account;
        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + currentAccount.getAccountHolderName() + "!");
    }

    // Logout
    private static void logout() {
        System.out.println("\nLogged out successfully!");
        System.out.println("Goodbye, " + currentAccount.getAccountHolderName() + "!");
        currentAccount = null;
    }

    // Deposit money
    private static void depositMoney() {
        System.out.println("\n--- DEPOSIT MONEY ---");
        double amount = getDoubleInput("Enter amount to deposit: ");
        currentAccount.deposit(amount);
    }

    // Withdraw money
    private static void withdrawMoney() {
        System.out.println("\n--- WITHDRAW MONEY ---");
        currentAccount.checkBalance();
        double amount = getDoubleInput("\nEnter amount to withdraw: ");
        currentAccount.withdraw(amount);
    }

    // Transfer money
    private static void transferMoney() {
        System.out.println("\n--- TRANSFER MONEY ---");

        String targetAccountNumber = getStringInput("Enter target account number: ");
        Account targetAccount = bank.findAccount(targetAccountNumber);

        if (targetAccount == null) {
            System.out.println("Target account not found!");
            return;
        }

        if (targetAccount == currentAccount) {
            System.out.println("Cannot transfer to the same account!");
            return;
        }

        System.out.println("Target Account: " + targetAccount.getAccountHolderName() +
                " (" + targetAccount.getAccountNumber() + ")");

        double amount = getDoubleInput("Enter amount to transfer: ");
        currentAccount.transfer(targetAccount, amount);
    }

    // View transaction history
    private static void viewTransactionHistory() {
        System.out.println("\nPress 1 for View Last 10 Transactions");
        System.out.println("Press 2 for View All Transactions");

        int choice = getIntInput("\nEnter choice: ");

        if (choice == 1) {
            currentAccount.viewTransactionHistory(10);
        } else if (choice == 2) {
            currentAccount.viewAllTransactions();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Initialize sample accounts
    private static void initializeSampleAccounts() {
        bank.createAccount("Riya Roy", "SAVINGS", 5000.0);
        bank.createAccount("Diya Bose", "CURRENT", 10000.0);
        bank.createAccount("Abir Sen", "SAVINGS", 7500.0);
        System.out.println("ℹ 3 sample accounts created for testing.");
    }

    // Helper methods
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid amount.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}