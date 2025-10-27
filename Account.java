import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Account class - Represents a bank account
class Account {
    private String accountNumber;
    private String accountHolderName;
    private String accountType; // SAVINGS, CURRENT
    private double balance;
    private ArrayList<Transaction> transactionHistory;
    private static int transactionCounter = 1000;
    private double minimumBalance;
    private boolean isActive;

    public Account(String accountNumber, String accountHolderName,
                   String accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        this.minimumBalance = accountType.equalsIgnoreCase("SAVINGS") ? 500.0 : 1000.0;
        this.isActive = true;

        // Record initial deposit as first transaction
        if (initialDeposit > 0) {
            addTransaction("DEPOSIT", initialDeposit, "Initial deposit - Account opening");
        }
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public ArrayList<Transaction> getTransactionHistory() { return transactionHistory; }
    public double getMinimumBalance() { return minimumBalance; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setAccountHolderName(String name) { this.accountHolderName = name; }

    // Deposit money
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit must be greater than zero.");
            return false;
        }

        if (!isActive) {
            System.out.println("Account is inactive! Cannot perform transaction.");
            return false;
        }

        balance += amount;
        addTransaction("DEPOSIT", amount, "Cash deposit");
        System.out.println("₹" + String.format("%.2f", amount) + " deposited successfully!");
        System.out.println("New Balance: ₹" + String.format("%.2f", balance));
        return true;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal must be greater than zero.");
            return false;
        }

        if (!isActive) {
            System.out.println("Account is inactive! Cannot perform transaction.");
            return false;
        }

        if (balance - amount < minimumBalance) {
            System.out.println("Insufficient balance!");
            System.out.println("Current Balance: ₹" + String.format("%.2f", balance));
            System.out.println("Minimum Balance Required: ₹" + String.format("%.2f", minimumBalance));
            System.out.println("Available for Withdrawal: ₹" +
                    String.format("%.2f", Math.max(0, balance - minimumBalance)));
            return false;
        }

        balance -= amount;
        addTransaction("WITHDRAW", amount, "Cash withdrawal");
        System.out.println("₹" + String.format("%.2f", amount) + " withdrawn successfully!");
        System.out.println("New Balance: ₹" + String.format("%.2f", balance));
        return true;
    }

    // Transfer money to another account
    public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Transfer must be greater than zero.");
            return false;
        }

        if (!isActive) {
            System.out.println("Your account is inactive! Cannot perform transaction.");
            return false;
        }

        if (!targetAccount.isActive()) {
            System.out.println("Target account is inactive! Cannot perform transaction.");
            return false;
        }

        if (balance - amount < minimumBalance) {
            System.out.println("Insufficient balance for transfer!");
            System.out.println("Available for Transfer: ₹" +
                    String.format("%.2f", Math.max(0, balance - minimumBalance)));
            return false;
        }

        // Deduct from sender
        balance -= amount;
        addTransaction("TRANSFER-OUT", amount,
                "Transfer to A/C: " + targetAccount.getAccountNumber());

        // Add to receiver
        targetAccount.balance += amount;
        targetAccount.addTransaction("TRANSFER-IN", amount,
                "Transfer from A/C: " + this.accountNumber);

        System.out.println("₹" + String.format("%.2f", amount) + " transferred successfully!");
        System.out.println("To: " + targetAccount.getAccountHolderName() +
                " (" + targetAccount.getAccountNumber() + ")");
        System.out.println("Your New Balance: ₹" + String.format("%.2f", balance));
        return true;
    }

    // Check balance
    public void checkBalance() {
        System.out.println("\n--- Account Balance ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Current Balance: ₹" + String.format("%.2f", balance));
        System.out.println("Minimum Balance: ₹" + String.format("%.2f", minimumBalance));
        System.out.println("Available Funds: ₹" +
                String.format("%.2f", Math.max(0, balance - minimumBalance)));
    }

    // View transaction history
    public void viewTransactionHistory(int limit) {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        System.out.println("Account: " + accountNumber + " (" + accountHolderName + ")");
        System.out.println("-".repeat(100));

        int start = Math.max(0, transactionHistory.size() - limit);
        for (int i = transactionHistory.size() - 1; i >= start; i--) {
            System.out.println(transactionHistory.get(i));
        }

        System.out.println("-".repeat(100));
        System.out.println("Showing " + Math.min(limit, transactionHistory.size()) +
                " of " + transactionHistory.size() + " transactions");
    }

    // View all transactions
    public void viewAllTransactions() {
        viewTransactionHistory(transactionHistory.size());
    }

    // Get account statement
    public void generateStatement() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                           BANK ACCOUNT STATEMENT");
        System.out.println("=".repeat(100));
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Account Holder  : " + accountHolderName);
        System.out.println("Account Type    : " + accountType);
        System.out.println("Current Balance : ₹" + String.format("%.2f", balance));
        System.out.println("Statement Date  : " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        System.out.println("=".repeat(100));

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            System.out.println("\nTransaction Details:");
            System.out.println("-".repeat(100));
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
            System.out.println("-".repeat(100));
            System.out.println("Total Transactions: " + transactionHistory.size());
        }

        // Calculate statistics
        double totalDeposits = 0;
        double totalWithdrawals = 0;
        for (Transaction t : transactionHistory) {
            if (t.getType().contains("DEPOSIT") || t.getType().equals("TRANSFER-IN")) {
                totalDeposits += t.getAmount();
            } else if (t.getType().contains("WITHDRAW") || t.getType().equals("TRANSFER-OUT")) {
                totalWithdrawals += t.getAmount();
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total Deposits    : ₹" + String.format("%.2f", totalDeposits));
        System.out.println("Total Withdrawals : ₹" + String.format("%.2f", totalWithdrawals));
        System.out.println("Net Change        : ₹" +
                String.format("%.2f", totalDeposits - totalWithdrawals));
        System.out.println("=".repeat(100));
    }

    // Deactivate account
    public void deactivateAccount() {
        this.isActive = false;
        System.out.println("Account deactivated successfully!");
    }

    // Reactivate account
    public void reactivateAccount() {
        this.isActive = true;
        System.out.println("Account reactivated successfully!");
    }

    // Helper method to add transaction
    private void addTransaction(String type, double amount, String description) {
        String transactionId = "TXN" + (transactionCounter++);
        Transaction transaction = new Transaction(transactionId, type, amount,
                balance, description);
        transactionHistory.add(transaction);
    }

    @Override
    public String toString() {
        String status = isActive ? "ACTIVE" : "INACTIVE";
        return String.format("A/C: %-12s | %-25s | Type: %-10s | Balance: ₹%-10.2f | Status: %s",
                accountNumber, accountHolderName, accountType, balance, status);
    }

    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n" + "=".repeat(60)).append("\n");
        info.append("              ACCOUNT INFORMATION\n");
        info.append("=".repeat(60)).append("\n");
        info.append("Account Number    : ").append(accountNumber).append("\n");
        info.append("Account Holder    : ").append(accountHolderName).append("\n");
        info.append("Account Type      : ").append(accountType).append("\n");
        info.append("Current Balance   : ₹").append(String.format("%.2f", balance)).append("\n");
        info.append("Minimum Balance   : ₹").append(String.format("%.2f", minimumBalance)).append("\n");
        info.append("Available Funds   : ₹").append(String.format("%.2f",
                Math.max(0, balance - minimumBalance))).append("\n");
        info.append("Account Status    : ").append(isActive ? "ACTIVE" : "INACTIVE").append("\n");
        info.append("Total Transactions: ").append(transactionHistory.size()).append("\n");
        info.append("=".repeat(60)).append("\n");
        return info.toString();
    }
}
