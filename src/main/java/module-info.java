module br.com.autocare.autocare_java {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.autocare.model to javafx.base;
    opens br.com.autocare to javafx.fxml;
    exports br.com.autocare;
}
