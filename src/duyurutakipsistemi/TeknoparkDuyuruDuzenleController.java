
package duyurutakipsistemi;

import static duyurutakipsistemi.AkademikDuyuruDuzenleController.duyuruListele;
import static duyurutakipsistemi.TeknoparkHesapController.teknoparkDuyuru;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import siniflar.AkademisyenDuyuru;
import siniflar.TeknoparkDuyurular;
import siniflar.ToplulukDuyurular;
import siniflar.TumDuyurular;


public class TeknoparkDuyuruDuzenleController extends AbstractController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //  TableView<AkademisyenDuyuru> table;
    public static List<TeknoparkDuyurular> duyuruListele = new ArrayList();
    public static int duyuruSayisi;

    @FXML
    public ComboBox<String> duyuruOnem = new ComboBox();
    @FXML
    public DatePicker yayinlanmaTarihi;
    @FXML
    public DatePicker gecerlilikTarihi;
    @FXML
    public TextField duyuruIsmi;
    @FXML
    public TextField duyuru;
    @FXML
    public TextField sirketIsmi;
    @FXML
    public TextField calismaAlani;
    @FXML
    public TextField duyuran;
    @FXML
    public TextField guncellenecek;

    @FXML
    private TableView<TeknoparkDuyurular> tableView;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> teknoparkDuyuruIsmiColumn;

    @FXML
    private TableColumn<TeknoparkDuyurular, String> duyuranColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> duyuruColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> yayinlanmaTarihiColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> gecerlilikTarihColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> duyuruOnemColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> sirketIsmiColumn;
    @FXML
    private TableColumn<TeknoparkDuyurular, String> calismaAlaniColumn;

    @FXML
    public void kayitEkle(ActionEvent event) {
        if (duyuruOnem.getValue().equals("Normal Duyuru")) {
            duyuruOnem.setValue("Duyuru->NORMAL");
        } else if (duyuruOnem.getValue().equals("Önemli Duyuru")) {
            duyuruOnem.setValue("Duyuru->ÖNEMLİ");
        }
        String sirketIsmiText = sirketIsmi.getText();
        String calismaAlaniText = calismaAlani.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
        TeknoparkDuyurular teknoparkDuyuruKontrol = new TeknoparkDuyurular(sirketIsmiText, calismaAlaniText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);

        boolean isimKontrol = dosyaIslem.teknoparkDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.teknoparkDuyuruIsmiKontrol(teknoparkDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol = dosyaIslem.kayitKontrol(teknoparkDuyuruKontrol);
        if (!guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "KAYIT EDERKEN GUNCELLE METNİ GİREMEZSİN !!");
        }else if(kontrol==true){
            JOptionPane.showMessageDialog(null, "DUYURU İSMİNİ DEĞİŞTİR  !!");

        }  
        else if (isimKontrol == true) {
            JOptionPane.showMessageDialog(null, "ÖZGÜN BİR DUYURU İSMİ GİRİNİZ !!");

        } else if (sirketIsmiText.equals("") || calismaAlaniText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if (kayitKontrol == true) {
            JOptionPane.showMessageDialog(null, "BOYLE BİR KAYIT ZATEN VAR !!");

        } else {
            TeknoparkDuyurular teknoparkDuyuru = new TeknoparkDuyurular(sirketIsmiText, calismaAlaniText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            System.out.println("---------Su an dosyaya yazilan kayit---------");
            System.out.println("Sirket İsmi : "+sirketIsmiText);
            System.out.println("calisma Alani  : "+calismaAlaniText);
            System.out.println("yayinlanma Tarihi : "+yayinlanmaTarihiText);
            System.out.println("gecerlilik Tarihi : "+gecerlilikTarihiText);
            System.out.println("duyuran  : "+duyuranText);
            System.out.println("duyuru : "+duyuruText);
            System.out.println("duyuru Onem : "+duyuruOnemText);
            System.out.println("duyuru ismi : "+duyuruIsmiText);
            
            dosyaIslem.DosyaYaz(teknoparkDuyuru);
        }
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<TeknoparkDuyurular> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

    }

    @FXML
    public void kayitGuncelle() {

        if (duyuruOnem.getValue().equals("Normal Duyuru")) {
            duyuruOnem.setValue("Duyuru->NORMAL");
        } else if (duyuruOnem.getValue().equals("Önemli Duyuru")) {
            duyuruOnem.setValue("Duyuru->ÖNEMLİ");
        }
        String sirketIsmiText = sirketIsmi.getText();
        String calismaAlaniText = calismaAlani.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
    TeknoparkDuyurular teknoparkDuyuruKontrol = new TeknoparkDuyurular(sirketIsmiText,calismaAlaniText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
       
        boolean isimKontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(teknoparkDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol=dosyaIslem.kayitKontrol(teknoparkDuyuruKontrol);
            
        boolean kayitVarMi = dosyaIslem.teknoparkDuyuruIsmiKontrol(guncellenecekText);  
          if (sirketIsmiText.equals("") || calismaAlaniText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("") || guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if(kontrol==true){
            JOptionPane.showMessageDialog(null, "BU İSİMDE BİR DUYURU VAR  !!");

        } 
          else if (kayitVarMi == false) {
            JOptionPane.showMessageDialog(null, "GÜNCELLENECEK DUYURU BULUNAMADI !!");
        } else {
            TeknoparkDuyurular teknoparkDuyuru = new TeknoparkDuyurular(sirketIsmiText, calismaAlaniText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            dosyaIslem.teknoparkDuyuruOku(guncellenecekText);
                                    System.out.println("-----Guncellenen Duyuru--------");
            System.out.println("sirket İsmi : "+teknoparkDuyuru.getSirketIsmi());
            System.out.println("calisma Alani   : "+teknoparkDuyuru.getCalismaAlani());
            System.out.println("yayinlanma Tarihi : "+teknoparkDuyuru.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : "+teknoparkDuyuru.getGecerlilikTarihi());
            System.out.println("duyuran  : "+teknoparkDuyuru.getDuyuran());
            System.out.println("duyuru : "+teknoparkDuyuru.getDuyuru());
            System.out.println("duyuru Onem : "+teknoparkDuyuru.getDuyuru());
            System.out.println("duyuru ismi : "+teknoparkDuyuru.getDuyuruIsmi());
        
            dosyaIslem.DosyaYaz(teknoparkDuyuru);

        }
        duyuruListele.removeAll(duyuruListele);
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<TeknoparkDuyurular> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
    }

    @FXML
    public void kayitSil() {

        ObservableList<TeknoparkDuyurular> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (TeknoparkDuyurular teknoparkDuyurular : selectedRows) {

            allDuyuru.remove(teknoparkDuyurular);
                        System.out.println("-----Silinen Duyuru--------");
            System.out.println("sirket İsmi : "+teknoparkDuyurular.getSirketIsmi());
            System.out.println("calisma alani : "+teknoparkDuyurular.getCalismaAlani());
            System.out.println("yayinlanma Tarihi : "+teknoparkDuyurular.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : "+teknoparkDuyurular.getGecerlilikTarihi());
            System.out.println("duyuran  : "+teknoparkDuyurular.getDuyuran());
            System.out.println("duyuru : "+teknoparkDuyurular.getDuyuru());
            System.out.println("duyuru Onem : "+teknoparkDuyurular.getDuyuru());
            System.out.println("duyuru ismi : "+teknoparkDuyurular.getDuyuruIsmi());
            dosyaIslem.kayitSil(teknoparkDuyurular);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        duyuruOnem.getItems().add("Normal Duyuru");
        duyuruOnem.getItems().add("Önemli Duyuru");

        duyuruListele.removeAll(duyuruListele);
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);

        teknoparkDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuruIsmi"));
        duyuranColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuran"));
        duyuruColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuru"));
        yayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("yayinlanmaTarihi"));
        gecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("gecerlilikTarihi"));
        duyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("duyuruOnem"));
        sirketIsmiColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("sirketIsmi"));
        calismaAlaniColumn.setCellValueFactory(new PropertyValueFactory<TeknoparkDuyurular, String>("calismaAlani"));
        /**
         * String fakulte, String bolum, String yayinlanmaTarihi, String
         * gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem
         */
    }

    @FXML
    public void duyurular(ActionEvent a) {

        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("TeknoparkHesap.fxml"));
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
