package com.example.TodoService.Domain;

import org.springframework.data.annotation.Id;

import  java.util.Date;
public class Todo {
    @Id
    private String taskid;
    private  String taskName;
    private  String taskDesc;
    private  String imgSrc;
    private  String category;
    private Date createdDate;
    private Date dueDate;
    private String priority;
    private Date currentDate;
    private String Status;

    @Override
    public String toString() {
        return "Todo{" +
                "taskid='" + taskid + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                ", currentDate=" + currentDate +
                ", Status='" + Status + '\'' +
                '}';
    }

    public Todo() {
    }

    public Todo(String taskid, String taskName, String taskDesc, String imgSrc, String category, Date createdDate, Date dueDate, String priority, Date currentDate, String status) {
        this.taskid = taskid;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.imgSrc = imgSrc;
        this.category = category;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.priority = priority;
        this.currentDate = currentDate;
        Status = status;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
