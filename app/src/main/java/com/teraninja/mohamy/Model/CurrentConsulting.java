package com.teraninja.mohamy.Model;

public class CurrentConsulting {
    public int id;
    public int client_id;
    public int consulting_id;
    public String body;
    public int is_replay;
    public String replay;
    public String created_at;
    public Object consulting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getConsulting_id() {
        return consulting_id;
    }

    public void setConsulting_id(int consulting_id) {
        this.consulting_id = consulting_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIs_replay() {
        return is_replay;
    }

    public void setIs_replay(int is_replay) {
        this.is_replay = is_replay;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Object getConsulting() {
        return consulting;
    }

    public void setConsulting(Object consulting) {
        this.consulting = consulting;
    }
}
