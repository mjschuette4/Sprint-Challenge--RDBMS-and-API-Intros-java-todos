package com.sprintchallenge.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    private String description;
    private Date datestarted;

    @Transient
    public boolean completedSwitch; //Publicly available switch
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Todo(){ }

    public Todo(String description, Date datestarted, User user) {
        this.description = description;
        this.datestarted = datestarted;
        this.completed = false;
        this.user = user;
    }

    public Todo(String description, Date datestarted, Boolean completed, User user) {
        this.description = description;
        this.datestarted = datestarted;
        this.completed = completed;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(Date datestarted) {
        this.datestarted = datestarted;
    }

    public boolean isCompletedSwitch() {
        return completedSwitch;
    }

    public void setCompletedSwitch(boolean completedSwitch) {
        this.completedSwitch = completedSwitch;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

