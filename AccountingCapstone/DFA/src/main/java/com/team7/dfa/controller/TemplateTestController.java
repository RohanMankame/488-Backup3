package com.team7.dfa.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TemplateTestController  {

    @FXML
    private Button logoutButton;

    @FXML
    protected void logoutClicked(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private TableColumn<Payment, String> Deductionscol;

    @FXML
    private TableColumn<Payment, String> HoursWorkedcol;

    @FXML
    private TableColumn<Payment, String> IDcol;

    @FXML
    private TableColumn<Payment, String> Jobcol;

    @FXML
    private TableColumn<Payment, String> NameCol;

    @FXML
    private TableColumn<Payment, String> NetPaycol;

    @FXML
    private TableColumn<Payment, String> Salarycol;

    @FXML
    private Button accountingButton;

    @FXML
    private Button bthDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label companyName;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button invoiceButton;

    //@FXML
    //private Button logoutButton;

    @FXML
    private Button payrollButton;

    @FXML
    private VBox sidebar;

    @FXML
    private TableView<Payment> table;

    @FXML
    private AnchorPane titleBar;

    @FXML
    private Button treasuryButton;

    @FXML
    private TextField txtHoursWorked;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtJob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;



}