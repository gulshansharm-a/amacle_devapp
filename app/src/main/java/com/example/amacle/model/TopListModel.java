package com.example.amacle.model;

public class TopListModel {
   String bonusEarn,clientReview,projectName,rating,levelOfDev;

    public String getLevelOfDev() {
        return levelOfDev;
    }

    public TopListModel(String bonusEarn, String clientReview, String projectName, String rating, String levelOfDev) {
        this.bonusEarn = bonusEarn;
        this.clientReview = clientReview;
        this.projectName = projectName;
        this.rating = rating;
        this.levelOfDev = levelOfDev;
    }

    public void setLevelOfDev(String levelOfDev) {
        this.levelOfDev = levelOfDev;
    }

    public String getBonusEarn() {
        return bonusEarn;
    }

    public void setBonusEarn(String bonusEarn) {
        this.bonusEarn = bonusEarn;
    }

    public String getClientReview() {
        return clientReview;
    }

    public void setClientReview(String clientReview) {
        this.clientReview = clientReview;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
