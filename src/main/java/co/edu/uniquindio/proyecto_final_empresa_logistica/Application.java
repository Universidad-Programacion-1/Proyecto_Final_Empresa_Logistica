package co.edu.uniquindio.proyecto_final_empresa_logistica;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.viewController.LoginViewController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.viewController.OlvidoContrasenaViewController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.viewController.RegistroUsuarioViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private Stage primaryStage;
    ModelFactory modelFactory = ModelFactory.getInstance();
    public static EmpresaLogistica empresaLogistica = new EmpresaLogistica("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de Empresa Logistica");
        inicializarData();
        openViewLogin();
    }

    public void openViewLogin() {
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

    public void newVista(int tipo) throws IOException {
        String nombrefxml = "";
        String title = "";
        if (tipo == 1) {
            nombrefxml = "MenuAdministrador.fxml";
            title = "Gestion de Empresa Logistica Administrador";
        } else if (tipo == 2) {
            nombrefxml = "MenuUsuario.fxml";
            title = "Gestion de Empresa Logistica Usuario";
        } else if (tipo == 3) {
            nombrefxml = "MenuRepartidor.fxml";
            title = "Gestion de Empresa Logistica Repartidor";
        }

        Stage newStage = new Stage();
        newStage.setTitle(title);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(nombrefxml));
        Scene scene = new Scene((Parent)fxmlLoader.load(), (double)500.0F, (double)500.0F);
        newStage.setScene(scene);
        newStage.show();
    }

    public void openRegistroUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Application.class.getResource("RegistroUsuario.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        RegistroUsuarioViewController registroUsuarioViewController = loader.getController();
        registroUsuarioViewController.setApp(this);

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void openOlvidoContrasena() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Application.class.getResource("OlvidoContrasena.fxml"));
        javafx.scene.layout.VBox rootLayout = (javafx.scene.layout.VBox) loader.load();
        OlvidoContrasenaViewController olvideContrasenaViewController = loader.getController();
        olvideContrasenaViewController.setApp(this);

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void inicializarData(){
        modelFactory.inicializarDatos();
    }
}