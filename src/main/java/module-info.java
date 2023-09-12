module com.automaatio {
    requires junit;
    requires jakarta.persistence;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.automaatio to javafx.fxml;
    opens com.automaatio.controller to javafx.fxml;
    opens com.automaatio.tests to javafx.fxml;
    exports com.automaatio;
    exports com.automaatio.controller;
    exports com.automaatio.tests;
}

