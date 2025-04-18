package com.example;

import java.sql.Date;

public class Schedule {
    private int scheduleId;
    private String tickets;
    private String broadcasting;
    private Date date;
    private String location;

    public Schedule(int scheduleId, String tickets, String broadcasting, Date date, String location) {
        this.scheduleId = scheduleId;
        this.tickets = tickets;
        this.broadcasting = broadcasting;
        this.date = date;
        this.location = location;
    }

    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public String getTickets() { return tickets; }
    public void setTickets(String tickets) { this.tickets = tickets; }

    public String getBroadcasting() { return broadcasting; }
    public void setBroadcasting(String broadcasting) { this.broadcasting = broadcasting; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
