package com.example.amacle.model;

public class HomeProjectModel {
    String name, Deadline,Dealy,Expected,progress,budget,extrabudget,nextMeeting,completed,image;

    public HomeProjectModel(String name, String deadline, String dealy, String expected, String progress, String budget, String extrabudget, String nextMeeting,String completed,String image) {
        name = name;
        Deadline = deadline;
        Dealy = dealy;
        Expected = expected;
        this.progress = progress;
        this.budget = budget;
        this.extrabudget = extrabudget;
        this.nextMeeting = nextMeeting;
        this.completed = completed;
        this.image = image;
    }

    public HomeProjectModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getProjectName() {
        return name;
    }

    public void setProjectName(String projectName) {
        name = projectName;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getDealy() {
        return Dealy;
    }

    public void setDealy(String dealy) {
        Dealy = dealy;
    }

    public String getExpected() {
        return Expected;
    }

    public void setExpected(String expected) {
        Expected = expected;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getExtrabudget() {
        return extrabudget;
    }

    public void setExtrabudget(String extrabudget) {
        this.extrabudget = extrabudget;
    }

    public String getNextMeeting() {
        return nextMeeting;
    }

    public void setNextMeeting(String nextMeeting) {
        this.nextMeeting = nextMeeting;
    }
}
