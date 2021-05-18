
package duyurutakipsistemi;

import static duyurutakipsistemi.AkademisyenHesapController.akademisyenDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.akademisyenDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.teknoparkDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.toplulukDuyuru;
import static duyurutakipsistemi.TeknoparkDuyuruDuzenleController.duyuruListele;
import siniflar.AkademisyenDuyuru;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import javax.swing.SpringLayout;
import siniflar.TeknoparkDuyurular;
import siniflar.TumDuyurular;


public class AkademikDuyuruDuzenleController extends AbstractController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //  TableView<AkademisyenDuyuru> table;
    public static List<AkademisyenDuyuru> duyuruListele = new ArrayList();
    public static int duyuruSayisi;

    @FXML
    public ComboBox<String> duyuruOnem = new ComboBox();
    @FXML
    public DatePicker yayinlanmaTarihi;
    @FXML
    public DatePicker gecerlilikTarihi;
    @FXML
    public TextField duyuru;
    @FXML
    public TextField fakulte;
    @FXML
    public TextField bolum;
    @FXML
    public TextField duyuran;
    @FXML
    public TextField duyuruIsmi;
    @FXML
    public TextField guncellenecek;
    @FXML
    private TableView<AkademisyenDuyuru> tableView;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> akademisyenDuyuruIsmiColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> duyuranColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> duyuruColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> yayinlanmaTarihiColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> gecerlilikTarihColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> duyuruOnemColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> fakulteColumn;
    @FXML
    private TableColumn<AkademisyenDuyuru, String> bolumColumn;

    @FXML
    public void kayitEkle(ActionEvent event) {
        if (duyuruOnem.getValue().equals("Normal Duyuru")) {
            duyuruOnem.setValue("Duyuru->NORMAL");
        } else if (duyuruOnem.getValue().equals("Önemli Duyuru")) {
            duyuruOnem.setValue("Duyuru->ÖNEMLİ");
        }
        String fakulteText = fakulte.getText();
        String bolumText = bolum.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
        AkademisyenDuyuru akademikDuyuruKontrol = new AkademisyenDuyuru(fakulteText, bolumText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
      
        boolean isimKontrol = dosyaIslem.akademikDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.akademikDuyuruIsmiKontrol(akademikDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol = dosyaIslem.kayitKontrol(akademikDuyuruKontrol);

        if (!guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "KAYIT EDERKEN GUNCELLE METNİ GİREMEZSİN !!");
        } else if (kontrol == true) {
            JOptionPane.showMessageDialog(null, "DUYURU İSMİNİ DEĞİŞTİR  !!");

        } else if (isimKontrol == true) {
            JOptionPane.showMessageDialog(null, "ÖZGÜN BİR DUYURU İSMİ GİRİNİZ !!");
        } else if (fakulteText.equals("") || bolumText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if (kayitKontrol == true) {
            JOptionPane.showMessageDialog(null, "BOYLE BİR KAYIT ZATEN VAR !!");

        } else {
            AkademisyenDuyuru akademikDuyuru = new AkademisyenDuyuru(fakulteText, bolumText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            System.out.println("---------Su an dosyaya yazilan kayit---------");
            System.out.println("fakulte İsmi : "+fakulteText);
            System.out.println("Bolum İsmi : "+bolumText);
            System.out.println("yayinlanma Tarihi : "+yayinlanmaTarihiText);
            System.out.println("gecerlilik Tarihi : "+gecerlilikTarihiText);
            System.out.println("duyuran  : "+duyuranText);
            System.out.println("duyuru : "+duyuruText);
            System.out.println("duyuru Onem : "+duyuruOnemText);
            System.out.println("duyuru ismi : "+duyuruIsmiText);
            dosyaIslem.DosyaYaz(akademikDuyuru);
        }
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<AkademisyenDuyuru> selectedRows, allDuyuru;
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
        String fakulteText = fakulte.getText();
        String bolumText = bolum.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
        AkademisyenDuyuru akademisyenDuyuruKontrol = new AkademisyenDuyuru(fakulteText, bolumText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);

        boolean isimKontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(akademisyenDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol = dosyaIslem.kayitKontrol(akademisyenDuyuruKontrol);

        boolean kayitVarMi = dosyaIslem.akademikDuyuruIsmiKontrol(guncellenecekText);

        if (fakulteText.equals("") || bolumText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("") || guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if (kontrol == true) {
            JOptionPane.showMessageDialog(null, "BU İSİMDE BİR DUYURU VAR  !!");

        } else if (kayitVarMi == false) {
            JOptionPane.showMessageDialog(null, "GÜNCELLENECEK DUYURU BULUNAMADI !!");
        } else {
            AkademisyenDuyuru akademikDuyuru = new AkademisyenDuyuru(fakulteText, bolumText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            dosyaIslem.akademikDuyuruOku(guncellenecekText);
                                    System.out.println("-----Guncellenen Duyuru--------");
            System.out.println("fakulte İsmi : "+akademikDuyuru.getFakulte());
            System.out.println("bolum   : "+akademikDuyuru.getBolum());
            System.out.println("yayinlanma Tarihi : "+akademikDuyuru.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : "+akademikDuyuru.getGecerlilikTarihi());
            System.out.println("duyuran  : "+akademikDuyuru.getDuyuran());
            System.out.println("duyuru : "+akademikDuyuru.getDuyuru());
            System.out.println("duyuru Onem : "+akademikDuyuru.getDuyuru());
            System.out.println("duyuru ismi : "+akademikDuyuru.getDuyuruIsmi());
            dosyaIslem.DosyaYaz(akademikDuyuru);

        }
        duyuruListele.removeAll(duyuruListele);
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<AkademisyenDuyuru> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
    }

    @FXML
    public void kayitSil() {

        ObservableList<AkademisyenDuyuru> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
        System.out.println(selectedRows);
        for (AkademisyenDuyuru akademisyenDuyuru : selectedRows) {

            allDuyuru.remove(akademisyenDuyuru);
            System.out.println("-----Silinen Duyuru--------");
            System.out.println("fakulte İsmi : "+akademisyenDuyuru.getFakulte());
            System.out.println("Bolum İsmi : "+akademisyenDuyuru.getBolum());
            System.out.println("yayinlanma Tarihi : "+akademisyenDuyuru.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : "+akademisyenDuyuru.getGecerlilikTarihi());
            System.out.println("duyuran  : "+akademisyenDuyuru.getDuyuran());
            System.out.println("duyuru : "+akademisyenDuyuru.getDuyuru());
            System.out.println("duyuru Onem : "+akademisyenDuyuru.getDuyuru());
            System.out.println("duyuru ismi : "+akademisyenDuyuru.getDuyuruIsmi());
            dosyaIslem.kayitSil(akademisyenDuyuru);
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

        akademisyenDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuruIsmi"));
        duyuranColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuran"));
        duyuruColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuru"));
        yayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("yayinlanmaTarihi"));
        gecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("gecerlilikTarihi"));
        duyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("duyuruOnem"));
        fakulteColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("fakulte"));
        bolumColumn.setCellValueFactory(new PropertyValueFactory<AkademisyenDuyuru, String>("bolum"));

        /**
         * String fakulte, String bolum, String yayinlanmaTarihi, String
         * gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem
         */
    }

    @FXML
    public void duyurular(ActionEvent a) {

        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("AkademisyenHesap.fxml"));
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
