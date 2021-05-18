/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyurutakipsistemi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import siniflar.DosyaIslemleri;


/**
 *
 * @author hp
 */
public abstract class AbstractController {

    public DosyaIslemleri dosyaIslem = new DosyaIslemleri();
 

    @FXML
    private void cikis(ActionEvent a) {

        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
