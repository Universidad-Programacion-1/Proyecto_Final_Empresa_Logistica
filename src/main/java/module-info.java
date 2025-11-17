module co.edu.uniquindio.proyecto_final_empresa_logistica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires co.edu.uniquindio.proyecto_final_empresa_logistica;

    opens co.edu.uniquindio.proyecto_final_empresa_logistica to javafx.fxml;
    exports co.edu.uniquindio.proyecto_final_empresa_logistica;
    exports co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;
    opens co.edu.uniquindio.proyecto_final_empresa_logistica.viewController to javafx.fxml;
}