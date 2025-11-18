module co.edu.uniquindio.proyecto_final_empresa_logistica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.mail;
    requires java.desktop;
    requires kernel;
    requires layout;
   // requires co.edu.uniquindio.proyecto_final_empresa_logistica;
    //requires jdk.javadoc; duda


    opens co.edu.uniquindio.proyecto_final_empresa_logistica to javafx.fxml;
    exports co.edu.uniquindio.proyecto_final_empresa_logistica;
    exports co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;
    opens co.edu.uniquindio.proyecto_final_empresa_logistica.viewController to javafx.fxml;
}