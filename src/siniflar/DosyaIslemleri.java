
package siniflar;

import duyurutakipsistemi.AkademikDuyuruDuzenleController;
import duyurutakipsistemi.AkademisyenHesapController;
import duyurutakipsistemi.OgrenciHesapController;
import duyurutakipsistemi.TeknoparkDuyuruDuzenleController;
import duyurutakipsistemi.TeknoparkHesapController;
import duyurutakipsistemi.ToplulukDuyuruDuzenleController;
import duyurutakipsistemi.ToplulukHesapController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DosyaIslemleri {
    //DOSYA OKUMA
    
    private int akademikDuyuruSayisi;
    private int toplulukDuyuruSayisi;
    private int teknoparkDuyuruSayisi;
    AkademikDuyuruDuzenleController oku = null;

    public DosyaIslemleri() {
        akademikDuyuruSayisi = 0;
        toplulukDuyuruSayisi = 0;
        teknoparkDuyuruSayisi = 0;
    }

    public int akademikDuyuruSayisi() {
        return akademikDuyuruSayisi;
    }

    public int teknoparkDuyuruSayisi() {
        return teknoparkDuyuruSayisi;
    }

    public int toplulukDuyuruSayisi() {
        return toplulukDuyuruSayisi;
    }

    public void dosyadanDuyuruOku() {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@akademik")) {

                    TumDuyurular kayitlar = new AkademisyenDuyuru(tmp[7], tmp[6], tmp[3], tmp[4], tmp[1], tmp[2], tmp[5], tmp[8]);
                    AkademikDuyuruDuzenleController.duyuruListele.add((AkademisyenDuyuru) kayitlar);
                    AkademisyenHesapController.akademisyenDuyuru.add((AkademisyenDuyuru) kayitlar);
                    OgrenciHesapController.akademisyenDuyuru.add((AkademisyenDuyuru) kayitlar);
                    TeknoparkHesapController.akademisyenDuyuru.add((AkademisyenDuyuru) kayitlar);
                    ToplulukHesapController.akademisyenDuyuru.add((AkademisyenDuyuru) kayitlar);
                    akademikDuyuruSayisi++;
                }
                if (tmp[0].equals("@teknopark")) {

                    TumDuyurular kayitlar = new TeknoparkDuyurular(tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7], tmp[8]);
                    TeknoparkDuyuruDuzenleController.duyuruListele.add((TeknoparkDuyurular) kayitlar);
                    AkademisyenHesapController.teknoparkDuyuru.add((TeknoparkDuyurular) kayitlar);
                    TeknoparkHesapController.teknoparkDuyuru.add((TeknoparkDuyurular) kayitlar);
                    ToplulukHesapController.teknoparkDuyuru.add((TeknoparkDuyurular) kayitlar);
                    OgrenciHesapController.teknoparkDuyuru.add((TeknoparkDuyurular) kayitlar);
                    teknoparkDuyuruSayisi++;
                }
                if (tmp[0].equals("@topluluk")) {
                    TumDuyurular kayitlar = new ToplulukDuyurular(tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7]);
                    ToplulukDuyuruDuzenleController.duyuruListele.add((ToplulukDuyurular) kayitlar);
                    AkademisyenHesapController.toplulukDuyuru.add((ToplulukDuyurular) kayitlar);
                    TeknoparkHesapController.toplulukDuyuru.add((ToplulukDuyurular) kayitlar);
                    ToplulukHesapController.toplulukDuyuru.add((ToplulukDuyurular) kayitlar);
                    OgrenciHesapController.toplulukDuyuru.add((ToplulukDuyurular) kayitlar);
                    toplulukDuyuruSayisi++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
        }
    }

    public void kayitSil(TumDuyurular silinecekDuyuru) {
        ArrayList<String> duyuruGuncel = new ArrayList();
        DosyaIslemleri dosyaGuncel = new DosyaIslemleri();

        try (Scanner scanner = new Scanner(new FileReader("duyurular.txt"))) {
            if (silinecekDuyuru instanceof AkademisyenDuyuru || silinecekDuyuru instanceof TeknoparkDuyurular || silinecekDuyuru instanceof ToplulukDuyurular) {

                while (scanner.hasNextLine()) {

                    String duyuru_bilgisi = scanner.nextLine();
                    String[] gecici_dizi = duyuru_bilgisi.split("<>");
                    if (gecici_dizi[0].equals("@akademik")) {
                        if (!silinecekDuyuru.getDuyuran().equals(gecici_dizi[1]) || !silinecekDuyuru.getDuyuru().equals(gecici_dizi[2])
                                || !silinecekDuyuru.getYayinlanmaTarihi().equals(gecici_dizi[3]) || !silinecekDuyuru.getGecerlilikTarihi().equals(gecici_dizi[4])
                                || !silinecekDuyuru.getDuyuruOnem().equals(gecici_dizi[5]) || !((AkademisyenDuyuru) silinecekDuyuru).getBolum().equals(gecici_dizi[6])
                                || !((AkademisyenDuyuru) silinecekDuyuru).getFakulte().equals(gecici_dizi[7]) || !silinecekDuyuru.getDuyuruIsmi().equals(gecici_dizi[8])) {

                            duyuruGuncel.add(duyuru_bilgisi);
                        }
                    }
                    if (gecici_dizi[0].equals("@teknopark")) {

                        if (!silinecekDuyuru.getDuyuran().equals(gecici_dizi[5]) || !silinecekDuyuru.getDuyuru().equals(gecici_dizi[6])
                                || !silinecekDuyuru.getYayinlanmaTarihi().equals(gecici_dizi[3]) || !silinecekDuyuru.getGecerlilikTarihi().equals(gecici_dizi[4])
                                || !silinecekDuyuru.getDuyuruOnem().equals(gecici_dizi[7]) || !((TeknoparkDuyurular) silinecekDuyuru).getSirketIsmi().equals(gecici_dizi[1])
                                || !((TeknoparkDuyurular) silinecekDuyuru).getCalismaAlani().equals(gecici_dizi[2]) || !silinecekDuyuru.getDuyuruIsmi().equals(gecici_dizi[8])) {

                            duyuruGuncel.add(duyuru_bilgisi);
                        }
                    }
                    if (gecici_dizi[0].equals("@topluluk")) {

                        if (!silinecekDuyuru.getDuyuran().equals(gecici_dizi[4]) || !silinecekDuyuru.getDuyuru().equals(gecici_dizi[5])
                                || !silinecekDuyuru.getYayinlanmaTarihi().equals(gecici_dizi[2]) || !silinecekDuyuru.getGecerlilikTarihi().equals(gecici_dizi[3])
                                || !silinecekDuyuru.getDuyuruOnem().equals(gecici_dizi[6]) || !((ToplulukDuyurular) silinecekDuyuru).getToplulukIsmi().equals(gecici_dizi[1]) || !silinecekDuyuru.getDuyuruIsmi().equals(gecici_dizi[7])) {
                            duyuruGuncel.add(duyuru_bilgisi);
                        }
                    }
                }
                File silinecekDosya = new File("C:\\Users\\hp\\Desktop\\DuyuruTakipSistemi\\Duyurular.txt");
                silinecekDosya.delete();
                System.out.println("duyuru guncel  :" + duyuruGuncel);
                dosyaGuncel.silinmisDosyayaYaz(duyuruGuncel);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyaIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean kayitKontrol(TumDuyurular duyuru) {
        try (Scanner scanner = new Scanner(new FileReader("duyurular.txt"))) {
            if (duyuru instanceof AkademisyenDuyuru || duyuru instanceof TeknoparkDuyurular || duyuru instanceof ToplulukDuyurular) {

                while (scanner.hasNextLine()) {

                    String duyuru_bilgisi = scanner.nextLine();
                    String[] gecici_dizi = duyuru_bilgisi.split("<>");
                    if (gecici_dizi[0].equals("@akademik")) {
                        if (duyuru.getDuyuran().equals(gecici_dizi[1]) && duyuru.getDuyuru().equals(gecici_dizi[2])
                                && duyuru.getYayinlanmaTarihi().equals(gecici_dizi[3]) && duyuru.getGecerlilikTarihi().equals(gecici_dizi[4])
                                && duyuru.getDuyuruOnem().equals(gecici_dizi[5]) && ((AkademisyenDuyuru) duyuru).getBolum().equals(gecici_dizi[6])
                                && ((AkademisyenDuyuru) duyuru).getFakulte().equals(gecici_dizi[7]) && duyuru.getDuyuruIsmi().equals(gecici_dizi[8])) {

                            return true;
                        }
                    }
                    if (gecici_dizi[0].equals("@teknopark")) {

                        if (duyuru.getDuyuran().equals(gecici_dizi[5]) && duyuru.getDuyuru().equals(gecici_dizi[6])
                                && duyuru.getYayinlanmaTarihi().equals(gecici_dizi[3]) && duyuru.getGecerlilikTarihi().equals(gecici_dizi[4])
                                && duyuru.getDuyuruOnem().equals(gecici_dizi[7]) && ((TeknoparkDuyurular) duyuru).getSirketIsmi().equals(gecici_dizi[1])
                                && ((TeknoparkDuyurular) duyuru).getCalismaAlani().equals(gecici_dizi[2]) && duyuru.getDuyuruIsmi().equals(gecici_dizi[8])) {

                            return true;
                        }
                    }
                    if (gecici_dizi[0].equals("@topluluk")) {

                        if (duyuru.getDuyuran().equals(gecici_dizi[4]) && duyuru.getDuyuru().equals(gecici_dizi[5])
                                && duyuru.getYayinlanmaTarihi().equals(gecici_dizi[2]) && duyuru.getGecerlilikTarihi().equals(gecici_dizi[3])
                                && duyuru.getDuyuruOnem().equals(gecici_dizi[6]) && ((ToplulukDuyurular) duyuru).getToplulukIsmi().equals(gecici_dizi[1])
                                && duyuru.getDuyuruIsmi().equals(gecici_dizi[7])) {
                            return true;
                        }
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyaIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean akademikDuyuruIsmiKontrol(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@akademik")) {
                    if (tmp[8].equals(duyuruIsmi)) {

                        return true;
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
        }
        return false;

    }

    public boolean teknoparkDuyuruIsmiKontrol(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@teknopark")) {
                    if (tmp[8].equals(duyuruIsmi)) {

                        return true;
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
        }
        return false;

    }

    public boolean toplulukDuyuruIsmiKontrol(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@topluluk")) {
                    if (tmp[7].equals(duyuruIsmi)) {

                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
        }
        return false;
    }

    public void akademikDuyuruOku(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@akademik")) {
                    if (tmp[8].equals(duyuruIsmi)) {
                        AkademisyenDuyuru kayitlar = new AkademisyenDuyuru(tmp[7], tmp[6], tmp[3], tmp[4], tmp[1], tmp[2], tmp[5], tmp[8]);
                        kayitSil(kayitlar);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyaIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void teknoparkDuyuruOku(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@teknopark")) {
                    if (tmp[8].equals(duyuruIsmi)) {
                        TeknoparkDuyurular kayitlar = new TeknoparkDuyurular(tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7], tmp[8]);
                        kayitSil(kayitlar);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyaIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toplulukDuyuruOku(String duyuruIsmi) {
        try (Scanner scanner = new Scanner(new FileReader("Duyurular.txt"))) {
            while (scanner.hasNextLine()) {
                String duyuruBilgisi = scanner.nextLine();
                String[] tmp = duyuruBilgisi.split("<>");
                if (tmp[0].equals("@topluluk")) {
                    if (tmp[7].equals(duyuruIsmi)) {
                        ToplulukDuyurular kayitlar = new ToplulukDuyurular(tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7]);
                        kayitSil(kayitlar);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
        }

    }
    
    
    //DOSYA YAZMA
    
       FileWriter writer = null;

    public void DosyaYaz(TumDuyurular duyuru) {

        try {
            writer = new FileWriter("Duyurular.txt", true);
            if (duyuru instanceof AkademisyenDuyuru) {
                writer.write("@akademik<>" + duyuru.duyuran + "<>" + duyuru.duyuru + "<>" + duyuru.yayinlanmaTarihi + "<>" + duyuru.gecerlilikTarihi + "<>" + duyuru.duyuruOnem + "<>" + ((AkademisyenDuyuru) duyuru).getBolum() + "<>" + ((AkademisyenDuyuru) duyuru).getFakulte() + "<>" + ((AkademisyenDuyuru) duyuru).duyuruIsmi+"\n");
            } else if (duyuru instanceof TeknoparkDuyurular) {
                writer.write("@teknopark<>" + ((TeknoparkDuyurular) duyuru).getSirketIsmi() + "<>" + ((TeknoparkDuyurular) duyuru).getCalismaAlani() + "<>" + duyuru.yayinlanmaTarihi + "<>" + duyuru.gecerlilikTarihi + "<>" + duyuru.duyuran + "<>" + duyuru.duyuru + "<>" + duyuru.duyuruOnem + "<>" +  ((TeknoparkDuyurular) duyuru).duyuruIsmi +"\n");
            } else if (duyuru instanceof ToplulukDuyurular) {
                writer.write("@topluluk<>" + ((ToplulukDuyurular) duyuru).getToplulukIsmi() + "<>" + duyuru.yayinlanmaTarihi + "<>" + duyuru.gecerlilikTarihi + "<>" + duyuru.duyuran + "<>" + duyuru.duyuru + "<>" + duyuru.duyuruOnem + "<>" +  ((ToplulukDuyurular) duyuru).duyuruIsmi + "\n");

            }
        } catch (IOException ex) {
            System.err.println("IO Exception");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.err.println("Dosya kapatılmadi");
                }
            }
        }
    }

    public void silinmisDosyayaYaz(ArrayList<String> arraylist) {

        try {
            writer = new FileWriter("Duyurular.txt");
            for (String a : arraylist) {
                writer.write(a + "\n");
            }
        } catch (IOException ex) {
            System.out.println("IO Exception hatası alıntı");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("IOException Hatası var");
                }
            }
        }
    }

}
