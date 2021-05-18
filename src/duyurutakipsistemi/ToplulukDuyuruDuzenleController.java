
package duyurutakipsistemi;

import static duyurutakipsistemi.AkademikDuyuruDuzenleController.duyuruListele;
import static duyurutakipsistemi.OgrenciHesapController.akademisyenDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.teknoparkDuyuru;
import static duyurutakipsistemi.OgrenciHesapController.toplulukDuyuru;
import static duyurutakipsistemi.TeknoparkDuyuruDuzenleController.duyuruListele;
import static duyurutakipsistemi.ToplulukHesapController.teknoparkDuyuru;
import static duyurutakipsistemi.ToplulukHesapController.toplulukDuyuru;
import siniflar.ToplulukDuyurular;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import siniflar.AkademisyenDuyuru;
import siniflar.TeknoparkDuyurular;
import siniflar.TumDuyurular;


public class ToplulukDuyuruDuzenleController extends AbstractController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static List<ToplulukDuyurular> duyuruListele = new ArrayList();
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
    public TextField toplulukIsmi;
    @FXML
    public TextField duyuran;
    @FXML
    public TextField duyuruIsmi;
    @FXML
    public TextField guncellenecek;
    @FXML
    private TableView<ToplulukDuyurular> tableView;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukDuyuruIsmiColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> duyuranColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> duyuruColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> yayinlanmaTarihiColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> gecerlilikTarihColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> duyuruOnemColumn;
    @FXML
    private TableColumn<ToplulukDuyurular, String> toplulukIsmiColumn;

    @FXML
    public void kayitEkle(ActionEvent event) {
        if (duyuruOnem.getValue().equals("Normal Duyuru")) {
            duyuruOnem.setValue("Duyuru->NORMAL");
        } else if (duyuruOnem.getValue().equals("Önemli Duyuru")) {
            duyuruOnem.setValue("Duyuru->ÖNEMLİ");
        }
        String toplulukIsmiText = toplulukIsmi.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
        ToplulukDuyurular toplulukDuyuruKontrol = new ToplulukDuyurular(toplulukIsmiText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);

        boolean isimKontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(toplulukDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol = dosyaIslem.kayitKontrol(toplulukDuyuruKontrol);
        if (!guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "KAYIT EDERKEN GUNCELLE METNİ GİREMEZSİN !!");
        } else if (kontrol == true) {
            JOptionPane.showMessageDialog(null, "DUYURU İSMİNİ DEĞİŞTİR  !!");

        } else if (isimKontrol == true) {
            JOptionPane.showMessageDialog(null, "ÖZGÜN BİR DUYURU İSMİ GİRİNİZ !!");

        } else if (toplulukIsmiText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if (kayitKontrol == true) {
            JOptionPane.showMessageDialog(null, "BOYLE BİR KAYIT ZATEN VAR !!");

        } else {
            ToplulukDuyurular toplulukDuyuru = new ToplulukDuyurular(toplulukIsmiText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            System.out.println("---------Su an dosyaya yazilan kayit---------");
            System.out.println("Topluluk İsmi : " + toplulukIsmiText);
            System.out.println("yayinlanma Tarihi : " + yayinlanmaTarihiText);
            System.out.println("gecerlilik Tarihi : " + gecerlilikTarihiText);
            System.out.println("duyuran  : " + duyuranText);
            System.out.println("duyuru : " + duyuruText);
            System.out.println("duyuru Onem : " + duyuruOnemText);
            System.out.println("duyuru ismi : " + duyuruIsmiText);

            dosyaIslem.DosyaYaz(toplulukDuyuru);
        }
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<ToplulukDuyurular> selectedRows, allDuyuru;
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
        String toplulukIsmiText = toplulukIsmi.getText();
        String yayinlanmaTarihiText = String.valueOf(yayinlanmaTarihi.getValue());
        String gecerlilikTarihiText = String.valueOf(gecerlilikTarihi.getValue());
        String duyuranText = duyuran.getText();
        String duyuruText = duyuru.getText();
        String duyuruOnemText = duyuruOnem.getValue();
        String duyuruIsmiText = duyuruIsmi.getText();
        String guncellenecekText = guncellenecek.getText();
        ToplulukDuyurular toplulukDuyuruKontrol = new ToplulukDuyurular(toplulukIsmiText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);

        boolean isimKontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(duyuruIsmiText);// Böyle bir akademik duyuru var mı?
        boolean kontrol = dosyaIslem.toplulukDuyuruIsmiKontrol(toplulukDuyuruKontrol.getDuyuruIsmi());//Böyle bir duyuru var mı?
        boolean kayitKontrol = dosyaIslem.kayitKontrol(toplulukDuyuruKontrol);

        boolean kayitVarMi = dosyaIslem.toplulukDuyuruIsmiKontrol(guncellenecekText);
        if (toplulukIsmiText.equals("") || (yayinlanmaTarihiText.equals("null")) || (gecerlilikTarihiText.equals("null"))
                && duyuranText.equals("") || duyuruText.equals("") || duyuruIsmiText.equals("") || guncellenecekText.equals("")) {
            JOptionPane.showMessageDialog(null, "BOS ALAN BIRAKMAYINIZ !!");
        } else if (kontrol == true) {
            JOptionPane.showMessageDialog(null, "BU İSİMDE BİR DUYURU VAR  !!");

        } else if (kayitVarMi == false) {
            JOptionPane.showMessageDialog(null, "GÜNCELLENECEK DUYURU BULUNAMADI !!");
        } else {
            ToplulukDuyurular toplulukDuyuru = new ToplulukDuyurular(toplulukIsmiText, yayinlanmaTarihiText, gecerlilikTarihiText, duyuranText, duyuruText, duyuruOnemText, duyuruIsmiText);
            dosyaIslem.toplulukDuyuruOku(guncellenecekText);
                         System.out.println("-----Guncellenen Duyuru--------");
            System.out.println("topluluk İsmi : "+toplulukDuyuru.getToplulukIsmi());
            System.out.println("yayinlanma Tarihi : "+toplulukDuyuru.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : "+toplulukDuyuru.getGecerlilikTarihi());
            System.out.println("duyuran  : "+toplulukDuyuru.getDuyuran());
            System.out.println("duyuru : "+toplulukDuyuru.getDuyuru());
            System.out.println("duyuru Onem : "+toplulukDuyuru.getDuyuru());
            System.out.println("duyuru ismi : "+toplulukDuyuru.getDuyuruIsmi());
            dosyaIslem.DosyaYaz(toplulukDuyuru);

        }
        duyuruListele.removeAll(duyuruListele);
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<ToplulukDuyurular> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
    }

    @FXML
    public void kayitSil() {

        ObservableList<ToplulukDuyurular> selectedRows, allDuyuru;
        allDuyuru = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (ToplulukDuyurular toplulukDuyuru : selectedRows) {

            allDuyuru.remove(toplulukDuyuru);
            System.out.println("-----Silinen Duyuru--------");
            System.out.println("topluluk İsmi : " + toplulukDuyuru.getToplulukIsmi());
            System.out.println("yayinlanma Tarihi : " + toplulukDuyuru.getYayinlanmaTarihi());
            System.out.println("gecerlilik Tarihi : " + toplulukDuyuru.getGecerlilikTarihi());
            System.out.println("duyuran  : " + toplulukDuyuru.getDuyuran());
            System.out.println("duyuru : " + toplulukDuyuru.getDuyuru());
            System.out.println("duyuru Onem : " + toplulukDuyuru.getDuyuru());
            System.out.println("duyuru ismi : " + toplulukDuyuru.getDuyuruIsmi());
            dosyaIslem.kayitSil(toplulukDuyuru);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        duyuruListele.removeAll(duyuruListele);
        dosyaIslem.dosyadanDuyuruOku();
        tableView.getItems().setAll(duyuruListele);
        duyuruListele.removeAll(duyuruListele);

        duyuruOnem.getItems().add("Normal Duyuru");
        duyuruOnem.getItems().add("Önemli Duyuru");

        toplulukDuyuruIsmiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuruIsmi"));
        duyuranColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuran"));
        duyuruColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuru"));
        yayinlanmaTarihiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("yayinlanmaTarihi"));
        gecerlilikTarihColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("gecerlilikTarihi"));
        duyuruOnemColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("duyuruOnem"));
        toplulukIsmiColumn.setCellValueFactory(new PropertyValueFactory<ToplulukDuyurular, String>("toplulukIsmi"));
    }

    @FXML
    public void duyurular(ActionEvent a) {
        //
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToplulukHesap.fxml"));
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
