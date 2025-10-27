import java.util.ArrayList;

// Bank class - Manages multiple accounts
class Bank {
    private String bankName;
    private ArrayList<Account> accounts;
    private static int accountCounter = 100001;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }

    // Create new account
    public Account createAccount(String accountHolderName, String accountType,
                                 double initialDeposit) {
        String accountNumber = "ACC" + (accountCounter++);
        Account account = new Account(accountNumber, accountHolderName,
                accountType, initialDeposit);
        accounts.add(account);
        return account;
    }

    // Find account by account number
    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // View all accounts
    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the bank.");
            return;
        }

        System.out.println("\n--- ALL BANK ACCOUNTS ---");
        System.out.println("-".repeat(100));
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("-".repeat(100));
        System.out.println("Total Accounts: " + accounts.size());
    }

    // Get bank statistics
    public void getBankStatistics() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the bank.");
            return;
        }

        double totalBalance = 0;
        int activeAccounts = 0;
        int savingsAccounts = 0;
        int currentAccounts = 0;

        for (Account account : accounts) {
            totalBalance += account.getBalance();
            if (account.isActive()) activeAccounts++;
            if (account.getAccountType().equalsIgnoreCase("SAVINGS")) {
                savingsAccounts++;
            } else {
                currentAccounts++;
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("           " + bankName.toUpperCase() + " - STATISTICS");
        System.out.println("=".repeat(60));
        System.out.println("Total Accounts        : " + accounts.size());
        System.out.println("Active Accounts       : " + activeAccounts);
        System.out.println("Inactive Accounts     : " + (accounts.size() - activeAccounts));
        System.out.println("Savings Accounts      : " + savingsAccounts);
        System.out.println("Current Accounts      : " + currentAccounts);
        System.out.println("Total Bank Balance    : ₹" + String.format("%.2f", totalBalance));
        System.out.println("Average Account Balance: ₹" +
                String.format("%.2f", totalBalance / accounts.size()));
        System.out.println("=".repeat(60));
    }

    public String getBankName() {
        return bankName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}