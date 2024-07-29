package ATM;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean authenticate(String userPin) {
        return this.userPin.equals(userPin);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw: " + amount);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit: " + amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void transfer(Bank bank, String recipientId, double amount) {
        if (amount > 0 && amount <= balance) {
            User recipient = bank.getUser(recipientId);
            if (recipient != null) {
                balance -= amount;
                recipient.deposit(amount);
                transactionHistory.add("Transfer to " + recipientId + ": " + amount);
                System.out.println("Transferred: " + amount + " to " + recipientId);
            } else {
                System.out.println("Recipient not found.");
            }
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

