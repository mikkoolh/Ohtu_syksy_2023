module com.automaatio {
    requires junit;
    requires jakarta.persistence;
    requires javafx.controls;
    requires javafx.fxml;

    // Open specific packages for reflection (change "package_name" to your actual package names)
    opens com.automaatio.model.database;
    opens com.automaatio.controller;
    opens com.automaatio.tests;

    // Export specific packages for accessibility by other modules (if needed)
    exports com.automaatio;
    exports com.automaatio.controller;
    exports com.automaatio.tests;
}
