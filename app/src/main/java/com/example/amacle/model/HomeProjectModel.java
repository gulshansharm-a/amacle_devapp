package com.example.amacle.model;

public class HomeProjectModel {
    String ProjectName, Deadline,Dealy,Expected,progress,budget,extrabudget,nextMeeting;

    public HomeProjectModel(String projectName, String deadline, String dealy, String expected, String progress, String budget, String extrabudget, String nextMeeting) {
        ProjectName = projectName;
        Deadline = deadline;
        Dealy = dealy;
        Expected = expected;
        this.progress = progress;
        this.budget = budget;
        this.extrabudget = extrabudget;
        this.nextMeeting = nextMeeting;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
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
