package co.edu.uniquindio.proyecto_final_empresa_logistica;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.viewController.LoginViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import co.edu.uniquindio.proyecto_final_empresa_logistica.viewController.UsuarioViewController;
import java.io.IOException;

public class Application extends javafx.application.Application {

    private Stage primaryStage;
    ModelFactory modelFactory = ModelFactory.getInstance();
    public static EmpresaLogistica empresaLogistica = new EmpresaLogistica("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de Empresa Logistica");
        openViewLogin();
    }

    public void openViewLogin() {
        inicializarData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("login.fxml"));
            javafx.scene.layout.VBox rootLayout = (javafx.scene.layout.VBox) loader.load();
            LoginViewController loginViewController = loader.getController();
            loginViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void openMenuAdministrador() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MenuAdministrador.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 1000);
        primaryStage.setTitle("Menu Administrador!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void openMenuUsuario() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MenuUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        primaryStage.setTitle("Menu Usuario!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void openMenuRepartidor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MenuRepartidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        primaryStage.setTitle("Menu Repartidor!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void inicializarData(){
        modelFactory.inicializarDatos();
    }
}