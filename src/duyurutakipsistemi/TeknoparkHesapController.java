
package duyurutakipsistemi;

import static duyurutakipsistemi.AkademikDuyuruDuzenleController.duyuruListele;
import static duyurutakipsistemi.OgrenciHesapController.akademisyenDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.teknoparkDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.toplulukDuyuru;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import siniflar.AkademisyenDuyuru;
import siniflar.TeknoparkDuyurular;
import siniflar.ToplulukDuyurular;


public class TeknoparkHesapController extends AbstractController implements Initializable {

    public static List<AkademisyenDuyuru> akademisyenDuyuru = new ArrayList();
    public static List<TeknoparkDuyurular> teknoparkDuyuru = new ArrayList();
    public static List<ToplulukDuyurular> toplulukDuyuru = new ArrayList();
    public static int akademisyenDuyuruSayisi;
    public static int teknoparkDuyuruSayisi;
    public static int toplulukDuyuruSayisi;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<AkademisyenDuyuru> akademisyenTableView;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenDuyuruIsmiColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenDuyuranColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenDuyuruColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenYayinlanmaTarihiColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenGecerlilikTarihColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenDuyuruOnemColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenFakulteColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenBolumColumn;
    
    @FXML
    private TableView<TeknoparkDuyurular> teknoparkTableView;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkDuyuruIsmiColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkDuyuranColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkDuyuruColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkYayinlanmaTarihiColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkGecerlilikTarihColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkDuyuruOnemColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkSirketIsmiColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkCalismaAlaniColumn;

    @FXML
    private TableView<ToplulukDuyurular> toplulukTableView;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukDuyuruIsmiColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukDuyuranColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukDuyuruColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukYayinlanmaTarihiColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukGecerlilikTarihColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukDuyuruOnemColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukIsmiColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        akademisyenDuyuru.removeAll(akademisyenDuyuru);
        teknoparkDuyuru.removeAll(teknoparkDuyuru);
        toplulukDuyuru.removeAll(toplulukDuyuru);

        dosyaIslem.dosyadanDuyuruOku();

        akademisyenTableView.getItems().setAll(akademisyenDuyuru);

        teknoparkTableView.getItems().setAll(teknoparkDuyuru);

        toplulukTableView.getItems().setAll(toplulukDuyuru);

        akademisyenDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuruIsmi"));
        akademisyenDuyuranColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuran"));
        akademisyenDuyuruColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuru"));
        akademisyenYayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("yayinlanmaTarihi"));
        akademisyenGecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("gecerlilikTarihi"));
        akademisyenDuyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuruOnem"));
        akademisyenFakulteColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("fakulte"));
        akademisyenBolumColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("bolum"));

        teknoparkDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuruIsmi"));
        teknoparkDuyuranColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuran"));
        teknoparkDuyuruColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuru"));
        teknoparkYayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("yayinlanmaTarihi"));
        teknoparkGecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("gecerlilikTarihi"));
        teknoparkDuyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuruOnem"));
        teknoparkSirketIsmiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("sirketIsmi"));
        teknoparkCalismaAlaniColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("calismaAlani"));
       
        toplulukDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuruIsmi"));
        toplulukDuyuranColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuran"));
        toplulukDuyuruColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuru"));
        toplulukYayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("yayinlanmaTarihi"));
        toplulukGecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("gecerlilikTarihi"));
        toplulukDuyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuruOnem"));
        toplulukIsmiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("toplulukIsmi"));

    }
    @FXML
    private void duzenle(ActionEvent a) {

        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("TeknoparkDuyuruDuzenle.fxml"));
            Scene tableview = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setScene(tableview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void cikis(ActionEvent a) {
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
