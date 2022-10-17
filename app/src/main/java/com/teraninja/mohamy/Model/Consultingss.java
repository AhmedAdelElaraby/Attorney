package com.teraninja.mohamy.Model;

import java.util.ArrayList;

public class Consultingss {
    public ArrayList<lastConsulting> last_consultings;
    public ArrayList<CurrentConsulting> current_consultings;

    public ArrayList<lastConsulting> getLast_consultings() {
        return last_consultings;
    }

    public void setLast_consultings(ArrayList<lastConsulting> last_consultings) {
        this.last_consultings = last_consultings;
    }

    public ArrayList<CurrentConsulting> getCurrent_consultings() {
        return current_consultings;
    }

    public void setCurrent_consultings(ArrayList<CurrentConsulting> current_consultings) {
        this.current_consultings = current_consultings;
    }
}
