
package duyurutakipsistemi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import siniflar.Giris;


public class GirisController implements Initializable {
    @FXML
    public TextField kullaniciAdi;
    @FXML
    public TextField kullaniciSifre;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    @FXML
    public void giris(ActionEvent a) throws IOException {
        Giris giris = new Giris();

        if (giris.ogrenciGiris(kullaniciAdi.getText(), kullaniciSifre.getText())) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("OgrenciHesap.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

        } else if (giris.akademisyenGiris(kullaniciAdi.getText(), kullaniciSifre.getText())) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AkademisyenHesap.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } else if (giris.teknoparkGiris(kullaniciAdi.getText(), kullaniciSifre.getText())) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("TeknoparkHesap.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } else if (giris.toplulukGiris(kullaniciAdi.getText(), kullaniciSifre.getText())) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToplulukHesap.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } else {
            JOptionPane.showMessageDialog(null, "Hatali sifre veya kullanici adi !"
                    + " YENİDEN DENEYİNİZ!!");
        }
    }

}
