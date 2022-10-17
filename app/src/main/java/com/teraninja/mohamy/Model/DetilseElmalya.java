package com.teraninja.mohamy.Model;

import java.util.ArrayList;

public class DetilseElmalya {
    public TrialDetails trial_details;
    public ArrayList<FinancialDetail> financial_details;

    public TrialDetails getTrial_details() {
        return trial_details;
    }

    public void setTrial_details(TrialDetails trial_details) {
        this.trial_details = trial_details;
    }

    public ArrayList<FinancialDetail> getFinancial_details() {
        return financial_details;
    }

    public void setFinancial_details(ArrayList<FinancialDetail> financial_details) {
        this.financial_details = financial_details;
    }
}
