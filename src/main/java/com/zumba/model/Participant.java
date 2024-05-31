package com.zumba.model;

public class Participant {
    private int id;
    private String name;
    private String email;
    private int batchId;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getBatchId() { return batchId; }
    public void setBatchId(int batchId) { this.batchId = batchId; }
}