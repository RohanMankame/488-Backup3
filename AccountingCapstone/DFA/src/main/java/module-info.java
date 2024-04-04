module com.team7.dfa {

    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    opens com.team7.dfa to javafx.fxml;
    exports com.team7.dfa;
    exports com.team7.dfa.controller;
    opens com.team7.dfa.controller to javafx.fxml;
}