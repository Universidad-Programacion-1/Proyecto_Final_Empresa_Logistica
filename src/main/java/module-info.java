module co.edu.uniquindio.proyecto_final_empresa_logistica {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyecto_final_empresa_logistica to javafx.fxml;
    exports co.edu.uniquindio.proyecto_final_empresa_logistica;
}