import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Transaction class - Represents a single transaction
class Transaction {
    private String transactionId;
    private String type; // DEPOSIT, WITHDRAW, TRANSFER
    private double amount;
    private double balanceAfter;
    private String timestamp;
    private String description;

    public Transaction(String transactionId, String type, double amount,
                       double balanceAfter, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"));
        this.description = description;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    public String getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("%-12s | %-10s | ₹%-10.2f | Balance: ₹%-10.2f | %s",
                transactionId, type, amount, balanceAfter, timestamp);
    }

    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n--- Transaction Details ---\n");
        info.append("Transaction ID : ").append(transactionId).append("\n");
        info.append("Type           : ").append(type).append("\n");
        info.append("Amount         : ₹").append(String.format("%.2f", amount)).append("\n");
        info.append("Balance After  : ₹").append(String.format("%.2f", balanceAfter)).append("\n");
        info.append("Date & Time    : ").append(timestamp).append("\n");
        info.append("Description    : ").append(description).append("\n");
        return info.toString();
    }
}