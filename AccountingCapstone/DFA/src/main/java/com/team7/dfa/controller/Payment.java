package com.team7.dfa.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Payment {
    private final StringProperty ID;
    private final StringProperty Name;
    private final StringProperty Job;
    private final StringProperty Salary;
    private final StringProperty HoursWorked;
    private final StringProperty Deductions;
    private final StringProperty NetPay;
    //private final StringProperty mobile;
    //private final StringProperty  course;
/////////////////////////////////////////////////////////////////////
    private final StringProperty PPEmployeeID;
    private final StringProperty PPName;
    private final StringProperty PPNetPay;
    private final StringProperty PPStatus;
    private final StringProperty PPOwed;
    private final StringProperty PPDueDate;
    private final StringProperty PPLastPayed;
    private final StringProperty PPAmountPayedLast;


    public Payment()
    {
        ID = new SimpleStringProperty(this, "ID");
        Name = new SimpleStringProperty(this, "Name");
        Job = new SimpleStringProperty(this, "Job");
        Salary = new SimpleStringProperty(this, "Salary");
        HoursWorked = new SimpleStringProperty(this, "HoursWorked");
        Deductions = new SimpleStringProperty(this, "Deductions");
        NetPay = new SimpleStringProperty(this, "NetPay");
        //////////////////////////////////////////////////////////////////////
        PPEmployeeID = new SimpleStringProperty(this, "PPEmployeeID");
        PPName = new SimpleStringProperty(this, "PPName");
        PPNetPay = new SimpleStringProperty(this, "PPNetPay");
        PPStatus = new SimpleStringProperty(this, "PPStatus");
        PPOwed = new SimpleStringProperty(this, "PPOwed");
        PPDueDate = new SimpleStringProperty(this, "PPDueDate");
        PPLastPayed = new SimpleStringProperty(this, "PPLastPayed");
        PPAmountPayedLast = new SimpleStringProperty(this, "PPAmountPayedLast");


    }
    public StringProperty PPAmountPayedLastProperty() { return PPAmountPayedLast; }
    public String getPPAmountPayedLast() { return PPAmountPayedLast.get(); }
    public void setPPAmountPayedLast(String newId) { PPAmountPayedLast.set(newId); }

    public StringProperty PPLastPayedProperty() { return PPLastPayed; }
    public String getPPLastPayed() { return PPLastPayed.get(); }
    public void setPPLastPayed(String newId) { PPLastPayed.set(newId); }


    public StringProperty PPDueDateProperty() { return PPDueDate; }
    public String getPPDueDate() { return PPDueDate.get(); }
    public void setPPDueDate(String newId) { PPDueDate.set(newId); }

    public StringProperty PPOwedProperty() { return PPOwed; }
    public String getPPOwed() { return PPOwed.get(); }
    public void setPPOwed(String newId) { PPOwed.set(newId); }

    public StringProperty PPStatusProperty() { return PPStatus; }
    public String getPPStatus() { return PPStatus.get(); }
    public void setPPStatus(String newId) { PPStatus.set(newId); }

    public StringProperty PPNetPayProperty() { return PPNetPay; }
    public String getPPNetPay() { return PPNetPay.get(); }
    public void setPPNetPay(String newId) { PPNetPay.set(newId); }

    public StringProperty PPNameProperty() { return PPName; }
    public String getPPName() { return PPName.get(); }
    public void setPPName(String newId) { PPName.set(newId); }


    public StringProperty PPEmployeeIDProperty() { return PPEmployeeID; }
    public String getPPEmployeeID() { return PPEmployeeID.get(); }
    public void setPPEmployeeID(String newId) { PPEmployeeID.set(newId); }

    public StringProperty idProperty() { return ID; }
    public String getId() { return ID.get(); }
    public void setId(String newId) { ID.set(newId); }


    public StringProperty nameProperty() { return Name; }
    public String getName() { return Name.get(); }
    public void setName(String newName) { Name.set(newName); }


    public StringProperty JobProperty() { return Job; }
    public String getJob() { return Job.get(); }
    public void setJob(String newJob) { Job.set(newJob); }

    public StringProperty SalaryProperty() { return Salary; }
    public String getSalary() { return Salary.get(); }
    public void setSalary(String newSalary) { Salary.set(newSalary); }

    public StringProperty HoursWorkedProperty() { return HoursWorked; }
    public String getHoursWorked() { return HoursWorked.get(); }
    public void setHoursWorked(String newHoursWorked) { HoursWorked.set(newHoursWorked); }

    public StringProperty DeductionsProperty() { return Deductions; }
    public String getDeductions() { return Deductions.get(); }
    public void setDeductions(String newDeductions) { Deductions.set(newDeductions); }

    public StringProperty NetPayProperty() { return NetPay; }
    public String getNetPay() { return NetPay.get(); }
    public void setNetPay(String newNetPay) { NetPay.set(newNetPay); }

}
