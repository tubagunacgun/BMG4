package siniflar;

public class TumDuyurular {

    protected String yayinlanmaTarihi;
    protected String gecerlilikTarihi;
    protected String duyuran;
    protected String duyuru;
    protected String duyuruOnem;
    protected String duyuruIsmi;

    public TumDuyurular(String yayinlanmaTarihi, String gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem, String duyuruIsmi) {
        this.yayinlanmaTarihi = yayinlanmaTarihi;
        this.gecerlilikTarihi = gecerlilikTarihi;
        this.duyuran = duyuran;
        this.duyuru = duyuru;
        this.duyuruOnem = duyuruOnem;
        this.duyuruIsmi = duyuruIsmi;
    }

    public String getYayinlanmaTarihi() {
        return yayinlanmaTarihi;
    }

    public void setYayinlanmaTarihi(String yayinlanmaTarihi) {
        this.yayinlanmaTarihi = yayinlanmaTarihi;
    }

    public String getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(String gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }

    public String getDuyuruIsmi() {
        return duyuruIsmi;
    }

    public void setDuyuruIsmi(String duyuruIsmi) {
        this.duyuruIsmi = duyuruIsmi;
    }

    public String getDuyuran() {
        return duyuran;
    }

    public void setDuyuran(String duyuran) {
        this.duyuran = duyuran;
    }

    public String getDuyuru() {
        return duyuru;
    }

    public void setDuyuru(String duyuru) {
        this.duyuru = duyuru;
    }

    public String getDuyuruOnem() {
        return duyuruOnem;
    }

    public void setDuyuruOnem(String duyuruOnem) {
        this.duyuruOnem = duyuruOnem;
    }

}
