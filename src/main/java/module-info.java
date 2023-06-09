module ucr.lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.lab to javafx.fxml;
    exports ucr.lab;
    opens controller;
    exports controller to javafx.controls;
}