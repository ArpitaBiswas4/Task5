# Bank Account Simulation System

A comprehensive CLI-based bank account simulation demonstrating Object-Oriented Programming (OOP) principles with account management, transactions, and complete banking operations.

## Features

### Account Management
- Create new accounts (Savings/Current)
- Login/Logout functionality
- View account details
- Account activation/deactivation
- Minimum balance enforcement

### Banking Operations
- **Deposit**: Add money to account
- **Withdraw**: Remove money with balance checks
- **Transfer**: Send money to other accounts
- **Balance Check**: View current balance
- **Transaction History**: Complete audit trail

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt or IDE (VS Code, IntelliJ)

### Compilation & Execution

**Using Terminal:**
```bash
# Compile
javac BankAccountSimulation.java

# Run
java BankAccountSimulation
```

**Using VS Code:**
1. Install Java Extension Pack
2. Open project folder
3. Right-click on `BankAccountSimulation.java`
4. Select "Run Java"

**Using IntelliJ IDEA:**
1. Create new Java project
2. Add file to src folder
3. Right-click → Run 'BankAccountSimulation.main()'

## How to Use

### Startup Screen

```
        BANK ACCOUNT SIMULATION SYSTEM         
           Welcome to Digital Bank             

ℹ 3 sample accounts created for testing.

MAIN MENU
Press 1 for Create New Account
Press 2 for Login to Account
Press 3 for View Bank Statistics
Press 0 for Exit
```

### Sample Accounts (Pre-loaded for Testing)

| Account Number | Name | Type | Balance |
|----------------|------|------|---------|
| ACC100001 | Riya Roy | SAVINGS | ₹5,000.00 |
| ACC100002 | Diya Bose | CURRENT | ₹10,000.00 |
| ACC100003 | Abir Sen | SAVINGS | ₹7,500.00 |

## Getting Started

### Step 1: Create New Account

```
Enter your choice: 1

--- CREATE NEW ACCOUNT ---
Enter account holder name: Arpita Biswas
Select Account Type:
Press 1 for Savings Account (Min. Balance: ₹500)
Press 2 for Current Account (Min. Balance: ₹1000)
Enter choice: 1
Enter initial deposit (Min. ₹500.00): 3000

Account created successfully!

============================================================
              ACCOUNT INFORMATION
============================================================
Account Number    : ACC100004
Account Holder    : Arpita Biswas
Account Type      : SAVINGS
Current Balance   : ₹3000.00
Minimum Balance   : ₹500.00
Available Funds   : ₹2500.00
Account Status    : ACTIVE
Total Transactions: 1
============================================================
```

### Step 2: Login to Account

```
Enter your choice: 2

--- LOGIN ---
Enter account number: ACC100001

Login successful!
Welcome, Riya Roy!
```

### Step 3: User Menu (After Login)

```
USER MENU - Riya Roy
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. View Transaction History
6. Generate Account Statement
7. View Account Details
8. Logout
0. Exit
```

## Banking Operations

### 1. Check Balance

```
Enter your choice: 1

--- Account Balance ---
Account Number : ACC100001
Account Holder : Riya Roy
Account Type   : SAVINGS
Current Balance: ₹5000.00
Minimum Balance: ₹500.00
Available Funds: ₹4500.00
```

**Available Funds** = Current Balance - Minimum Balance

### 2. Deposit Money

```
Enter your choice: 2

--- DEPOSIT MONEY ---
Enter amount to deposit: 2000
₹2000.00 deposited successfully!
New Balance: ₹7000.00
```

### 3. Withdraw Money

```
Enter your choice: 3

--- WITHDRAW MONEY ---
Account Number : ACC100001
Account Holder : Riya Roy
Account Type   : SAVINGS
Current Balance: ₹7000.00
Minimum Balance: ₹500.00
Available Funds: ₹6500.00

Enter amount to withdraw: 1500
₹1500.00 withdrawn successfully!
New Balance: ₹5500.00
```

### 4. Transfer Money

```
Enter your choice: 4

--- TRANSFER MONEY ---
Enter target account number: ACC100002
Target Account: Diya Bose (ACC100002)
Enter amount to transfer: 1000

₹1000.00 transferred successfully!
To: Diya Bose (ACC100002)
Your New Balance: ₹4500.00
```

### 5. View Transaction History

```
Enter your choice: 5

1. View Last 10 Transactions
2. View All Transactions

Enter choice: 1

--- Transaction History ---
Account: ACC100001 (John Doe)
----------------------------------------------------------------------------------------------------
TXN1003      | TRANSFER-OUT | ₹1000.00   | Balance: ₹4500.00  | 27-Oct-2025 15:30:45
TXN1002      | WITHDRAW   | ₹1500.00   | Balance: ₹5500.00  | 27-Oct-2025 15:25:10
TXN1001      | DEPOSIT    | ₹2000.00   | Balance: ₹7000.00  | 27-Oct-2025 15:20:30
TXN1000      | DEPOSIT    | ₹5000.00   | Balance: ₹5000.00  | 27-Oct-2025 14:00:00
----------------------------------------------------------------------------------------------------
Showing 4 of 4 transactions
```

### 6. Generate Account Statement

```
Enter your choice: 6

====================================================================================================
                           BANK ACCOUNT STATEMENT
====================================================================================================
Account Number  : ACC100001
Account Holder  : Riya Roy
Account Type    : SAVINGS
Current Balance : ₹4500.00
Statement Date  : 27-Oct-2025 15:35
====================================================================================================

Transaction Details:
----------------------------------------------------------------------------------------------------
TXN1000      | DEPOSIT    | ₹5000.00   | Balance: ₹5000.00  | 27-Oct-2025 14:00:00
TXN1001      | DEPOSIT    | ₹2000.00   | Balance: ₹7000.00  | 27-Oct-2025 15:20:30
TXN1002      | WITHDRAW   | ₹1500.00   | Balance: ₹5500.00  | 27-Oct-2025 15:25:10
TXN1003      | TRANSFER-OUT | ₹1000.00 | Balance: ₹4500.00  | 27-Oct-2025 15:30:45
----------------------------------------------------------------------------------------------------
Total Transactions: 4

Summary:
Total Deposits    : ₹7000.00
Total Withdrawals : ₹2500.00
Net Change        : ₹4500.00
====================================================================================================
```

### 7. View Account Details

```
Enter your choice: 7

============================================================
              ACCOUNT INFORMATION
============================================================
Account Number    : ACC100001
Account Holder    : Riya Roy
Account Type      : SAVINGS
Current Balance   : ₹4500.00
Minimum Balance   : ₹500.00
Available Funds   : ₹4000.00
Account Status    : ACTIVE
Total Transactions: 4
============================================================
```

### 8. Logout

```
Enter your choice: 8

Logged out successfully!
Goodbye, Riya Roy!

[Returns to Main Menu]
```
