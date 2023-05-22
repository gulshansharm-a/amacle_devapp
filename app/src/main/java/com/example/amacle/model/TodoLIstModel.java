package com.example.amacle.model;

public class TodoLIstModel {
    String toDoid , Topic ;
    Boolean iscompleted;

    public String getToDoid() {
        return toDoid;
    }

    public void setToDoid(String toDoid) {
        this.toDoid = toDoid;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public Boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }
}
