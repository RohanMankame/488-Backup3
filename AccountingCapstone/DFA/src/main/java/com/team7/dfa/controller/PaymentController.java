package com.team7.dfa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentController implements Initializable {


    //  @FXML
    //private Button logoutButton;

    //@FXML
    //protected void logoutClicked(ActionEvent event)
    // {
    //     System.exit(0);
    //  }

    @FXML
    private TableColumn<Payment, String> PPAmountOwedCol;

    @FXML
    private TableColumn<Payment, String> PPAmountPayedCol;

    @FXML
    private TableColumn<Payment, String> PPDueDateCol;

    @FXML
    private TableColumn<Payment, String> PPIDcol;

    @FXML
    private TableColumn<Payment, String> PPLastPayedCol;

    @FXML
    private TableColumn<Payment, String> PPNameCol;

    @FXML
    private TableColumn<Payment, String> PPStatusCol;

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
    private TableColumn<Payment, String> PayNameCol;

    @FXML
    private TableColumn<Payment, String> PayNetPayCol;

    @FXML
    private TableColumn<Payment, String> PayPayStatusCol;

    @FXML
    private TableView<Payment> PayStatustable;

    @FXML
    private TableView<Payment> table3;

    @FXML
    private TableView<Payment> Table4;

    @FXML
    private TableColumn<Payment, String> PrevPayDateCol;

    @FXML
    private TableColumn<Payment, String> PrevPayIDCol;

    @FXML
    private TableColumn<Payment, String> PrevPayNameCol;

    @FXML
    private TableColumn<Payment, String> PrevPayStatusCol;

    @FXML
    private TableColumn<Payment, String> SalaeyJobCol;

    @FXML
    private TableColumn<Payment, String> SalaryNameCol;

    @FXML
    private TableColumn<Payment, String> SalarySalaryCol;

    @FXML
    private TableView<Payment> SalaryTable;

    @FXML
    private TextField txtPayName;

    @FXML
    private Button btnPPPay;



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

    @FXML
    private Button logoutButton;

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
    private TextField txtPPAmountTransfer;

    @FXML
    private TextField txtPPID;

    @FXML
    private TextField txtPPName;

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

    @FXML
    void Add(ActionEvent event) {

        String Name,Job,Salary,HoursWorked,Deductions,NetPay;
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";

        float SalFloat = Float.parseFloat(Salary);;
        float calcTax = (SalFloat / 100) * 8;
        Deductions = String.valueOf(calcTax);
        NetPay = String.valueOf(SalFloat - calcTax);


        try
        {
            pst = con.prepareStatement("insert into rohanPayroll(Name,Job,Salary,HoursWorked,Deductions,NetPay)values(?,?,?,?,?,?)");
            //pst = con.prepareStatement("insert into rohanPayroll(ID,Name,Job,Salary,HoursWorked,Deductions,NetPay)values(?,?,?,?,?,?,?)");
            //pst.setString(1, ID);
            pst.setString(1, Name);
            pst.setString(2, Job);
            pst.setString(3, Salary);
            pst.setString(4, HoursWorked);
            pst.setString(5, Deductions);
            pst.setString(6, NetPay);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Registation");

            alert.setHeaderText("Employee Payroll");
            alert.setContentText("Record Addedddd!");
            alert.showAndWait();
            table();
            table3();
            table2();
            table4();

            //txtID.setText("");
            txtName.setText("");
            txtJob.setText("");
            txtSalary.setText("");
            txtHoursWorked.setText("");

            ResultSet rs = pst.executeQuery();

            // Retrieve the last auto-generated ID after insertion
            int employeeID = 0;
            while (rs.next()) {
                employeeID = rs.getInt("ID"); // Assuming the ID is an integer
            }

            FillTable3(employeeID, Name, Job, Salary, NetPay);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FillTable3(int employeeID, String Name,String Job,String Salary,String NetPay){
        System.out.println(Name);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        String ID =Integer.toString(employeeID);
        String Status = "Not payed";
        String Owed = NetPay;
        String DueDate = "18/4/2024";
        String LastPayed = "Never";
        String AmountPayedLast = "0";




        try
        {

            // Calculate DueDate by adding two weeks to the current date
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusWeeks(2);

            // Format the date as "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dueDateString = dueDate.format(formatter);

            pst = con.prepareStatement("INSERT INTO rohanPayStatus (EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast)values(?,?,?,?,?,?,?,?)");
            //pst = con.prepareStatement("insert into rohanPayroll(ID,Name,Job,Salary,HoursWorked,Deductions,NetPay)values(?,?,?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, NetPay);
            pst.setString(4, Status);
            pst.setString(5, Owed);
            pst.setString(6, dueDateString);
            pst.setString(7, LastPayed);
            pst.setString(8, AmountPayedLast);
            pst.executeUpdate();

            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Employee Registation");
            //alert.setHeaderText("Employee Payroll");
            //alert.setContentText("Record Addedddd!");
           //alert.showAndWait();
            table3();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void table()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select ID,Name,Job,Salary,HoursWorked,Deductions,NetPay from rohanPayroll");

            //pst = con.prepareStatement("select EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast from rohanPayStatus");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Payment st = new Payment();
                    st.setId(rs.getString("ID"));
                    st.setName(rs.getString("Name"));
                    st.setJob(rs.getString("Job"));
                    st.setSalary(rs.getString("Salary"));
                    st.setHoursWorked(rs.getString("HoursWorked"));
                    st.setDeductions(rs.getString("Deductions"));
                    st.setNetPay(rs.getString("NetPay"));
                    payments.add(st);
                }
            }
            table.setItems(payments);
            IDcol.setCellValueFactory(f -> f.getValue().idProperty());
            NameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            Jobcol.setCellValueFactory(f -> f.getValue().JobProperty());
            Salarycol.setCellValueFactory(f -> f.getValue().SalaryProperty());
            HoursWorkedcol.setCellValueFactory(f -> f.getValue().HoursWorkedProperty());
            Deductionscol.setCellValueFactory(f -> f.getValue().DeductionsProperty());
            NetPaycol.setCellValueFactory(f -> f.getValue().NetPayProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    //txtID.setText(table.getItems().get(myIndex).getId());
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtJob.setText(table.getItems().get(myIndex).getJob());
                    txtSalary.setText(table.getItems().get(myIndex).getSalary());
                    txtHoursWorked.setText(table.getItems().get(myIndex).getHoursWorked());



                }
            });
            return myRow;
        });


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void table3()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select EmployeeID,Name,NetPay,Status,Owed,DueDate,LastPayed,AmountPayedLast from rohanPayStatus");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Payment st = new Payment();
                    st.setPPEmployeeID(rs.getString("EmployeeID"));
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("Owed"));
                    st.setPPNetPay(rs.getString("NetPay"));
                    st.setPPDueDate(rs.getString("DueDate"));
                    st.setPPLastPayed(rs.getString("LastPayed"));
                    st.setPPAmountPayedLast(rs.getString("AmountPayedLast"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            table3.setItems(payments);
            PPIDcol.setCellValueFactory(f -> f.getValue().PPEmployeeIDProperty());
            PPNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PPAmountOwedCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PPDueDateCol.setCellValueFactory(f -> f.getValue().PPDueDateProperty());
            PPLastPayedCol.setCellValueFactory(f -> f.getValue().PPLastPayedProperty());
            PPAmountPayedCol.setCellValueFactory(f -> f.getValue().PPAmountPayedLastProperty());
            PPStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table3.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    System.out.println("Clicked");
                    myIndex =  table3.getSelectionModel().getSelectedIndex();
                    //id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getId()));

                    txtPPID.setText(table3.getItems().get(myIndex).getPPEmployeeID());
                    System.out.println(table3.getItems().get(myIndex).getPPEmployeeID());
                    //////////////////////////////////////////////////////////////////////////////////
                    txtPPName.setText(table3.getItems().get(myIndex).getPPName());
                    txtPPAmountTransfer.setText(table3.getItems().get(myIndex).getPPOwed());


                }
            });
            return myRow;
        });


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void table2()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Name,Owed, Status from rohanPayStatus where Status = 'Not Payed'");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("Owed"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            PayStatustable.setItems(payments);
            PayNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PayNetPayCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PayPayStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }


        /*
        PayStatustable.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  PayStatustable.getSelectionModel().getSelectedIndex();

                    //id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getId()));
                    //txtPPID.setText(table3.getItems().get(myIndex).getPPEmployeeID());
                    //System.out.println(table3.getItems().get(myIndex).getPPEmployeeID());
                    //////////////////////////////////////////////////////////////////////////////////
                    //txtPPName.setText(table3.getItems().get(myIndex).getPPName());
                    //txtPPAmountTransfer.setText(table3.getItems().get(myIndex).getPPOwed());


                }
            });
            return myRow;
        });
        */



    }





    public void table4()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select EmployeeID,Name,LastPayed, Status from rohanPayStatus where Status = 'Payed'");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setPPEmployeeID(rs.getString("EmployeeID"));
                    st.setPPName(rs.getString("Name"));
                    st.setPPOwed(rs.getString("LastPayed"));
                    st.setPPStatus(rs.getString("Status"));
                    payments.add(st);
                }
            }
            Table4.setItems(payments);
            PrevPayIDCol.setCellValueFactory(f -> f.getValue().PPEmployeeIDProperty());
            PrevPayNameCol.setCellValueFactory(f -> f.getValue().PPNameProperty());
            PrevPayDateCol.setCellValueFactory(f -> f.getValue().PPOwedProperty());
            PrevPayStatusCol.setCellValueFactory(f -> f.getValue().PPStatusProperty());


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        Table4.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  Table4.getSelectionModel().getSelectedIndex();

                    //id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getId()));
                    //txtPPID.setText(table3.getItems().get(myIndex).getPPEmployeeID());
                    //System.out.println(table3.getItems().get(myIndex).getPPEmployeeID());
                    //////////////////////////////////////////////////////////////////////////////////
                    //txtPPName.setText(table3.getItems().get(myIndex).getPPName());
                    //txtPPAmountTransfer.setText(table3.getItems().get(myIndex).getPPOwed());


                }
            });
            return myRow;
        });

        */

    }






    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
    public void table3()
    {
        Connect();
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select Name,Salary,Job from payroll");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Payment st = new Payment();
                    st.setName(rs.getString("Name"));
                    st.setSalary(rs.getString("Salary"));
                    st.setJob(rs.getString("Job"));

                    payments.add(st);
                }
            }
            SalaryTable.setItems(payments);
            SalaryNameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            SalarySalaryCol.setCellValueFactory(f -> f.getValue().SalaryProperty());
            SalaeyJobCol.setCellValueFactory(f -> f.getValue().JobProperty());
            //PayPayStatusCol.setCellValueFactory("payed");


        }

        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PayStatustable.setRowFactory( tv -> {
            TableRow<Payment> myRow = new TableRow<>();

            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    /////////////NOTHING

                }
            });
            return myRow;
        });


    }

*/





    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void Delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            String tempID = txtID.getText();
            pst = con.prepareStatement("delete from rohanPayroll where id = ?" );
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("employee payroll delete");

            alert.setHeaderText("employee registration");
            alert.setContentText("Deletedd!");
            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }


        id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getPPEmployeeID()));
        try
        {
            //System.out.println("!!!!!!!!!!"+txtID.getText());
            //String tempID = txtID.getText();
            pst = con.prepareStatement("delete from rohanPayStatus where EmployeeID = ?" );
            pst.setInt(1, id);
            pst.executeUpdate();

            table3();
            table2();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    void Update(ActionEvent event) {

        String Name,Job,Salary,HoursWorked,Deductions,NetPay;

        myIndex = table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        //ID = txtID.getText();
        Name = txtName.getText();
        Job = txtJob.getText();
        Salary = txtSalary.getText();
        HoursWorked = txtHoursWorked.getText();
        Deductions  = "0";
        NetPay = "9000";
        try
        {
            pst = con.prepareStatement("update rohanPayroll set Name = ? ,Job = ?, Salary = ? where id = ? ");
            pst.setString(1, Name);
            pst.setString(2, Job);
            pst.setString(3, Salary);
            pst.setInt(4, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");

            alert.setHeaderText("Student Registation");
            alert.setContentText("Updateddd!");
            alert.showAndWait();
            table();
            table3();
            table2();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void PayAmount(ActionEvent event) {
        String Name, Owe, Status, AmountTransfer,LastPayDate;

        myIndex = table3.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(table3.getItems().get(myIndex).getPPEmployeeID()));
        Owe = String.valueOf(table3.getItems().get(myIndex).getPPOwed());

        //Name = txtPPName.getText();
        Status = "Not Payed";
        AmountTransfer = txtPPAmountTransfer.getText();
        Float NewOwe = Float.parseFloat(Owe) - Float.parseFloat(AmountTransfer);
        String NNewOwe = Float.toString(NewOwe);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        LastPayDate = formattedDate;

        if(NewOwe <= 0 ){
            Status = "Payed";
            NewOwe = 0.00F;
            NNewOwe= "0";
        }

        try
        {
            pst = con.prepareStatement("update rohanPayStatus set Owed = ? ,LastPayed = ?, AmountPayedLast = ? , Status = ? where EmployeeID = ? ");
            pst.setString(1, NNewOwe);
            pst.setString(2, LastPayDate);
            pst.setString(3, AmountTransfer);
            pst.setString(4, Status);
            pst.setInt(5, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Payroll");

            alert.setHeaderText("Employee Payed");
            alert.setContentText("Money Transfered!");
            alert.showAndWait();
            table3();
            table4();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TemplateTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void logoutClicked(ActionEvent event) {

    }

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    /*
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/payroll","root","rohan");
        }catch (ClassNotFoundException ex){

        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
    */
    public void Connect(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            String connString = "jdbc:sqlserver://dfaserver.database.windows.net:1433;database=DFADatabase;user=capstoneAdmin@dfaserver;password=Group7@Capstone;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
            con = DriverManager.getConnection(connString);
        } catch(SQLException ex){
            ex.printStackTrace();

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Connect();
        table();
        table3();
        table2();
        table4();


    }


}
