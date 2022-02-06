package com.revature.models;

public class Reimbursement {
    Integer id;
    double amount;
    ReimbursementType reimbursementType;
    ReimbursementStatus reimbursementStatus;
    User author;
    User resolver;
    String description;

    public Reimbursement(double amount, ReimbursementType reimbursementType, User author) {
        this.amount = amount;
        this.reimbursementType = reimbursementType;
        this.author = author;
    }

    public Reimbursement() {
    }

    public Reimbursement(int id, double amount, ReimbursementType reimbursementType, ReimbursementStatus reimbursementStatus, User author, User resolver, String description) {
        this.id = id;
        this.amount = amount;
        this.reimbursementType = reimbursementType;
        this.reimbursementStatus = reimbursementStatus;
        this.author = author;
        this.resolver = resolver;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ReimbursementType getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(ReimbursementType reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", reimbursementType=" + reimbursementType +
                ", reimbursementStatus=" + reimbursementStatus +
                ", author=" + author +
                ", resolver=" + resolver +
                ", description='" + description + '\'' +
                '}';
    }
}