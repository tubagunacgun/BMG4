package siniflar;


public class TeknoparkDuyurular extends TumDuyurular {

    private String sirketIsmi;
    private String calismaAlani;

    public TeknoparkDuyurular(String yayinlanmaTarihi, String gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem, String duyuruIsmi) {
        super(yayinlanmaTarihi, gecerlilikTarihi, duyuran, duyuru, duyuruOnem, duyuruIsmi);
    }

    public TeknoparkDuyurular(String sirketIsmi, String calismaAlani, String yayinlanmaTarihi, String gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem, String duyuruIsmi) {
        super(yayinlanmaTarihi, gecerlilikTarihi, duyuran, duyuru, duyuruOnem, duyuruIsmi);
        this.sirketIsmi = sirketIsmi;
        this.calismaAlani = calismaAlani;
    }

 
    public String getSirketIsmi() {
        return sirketIsmi;
    }

    public void setSirketIsmi(String sirketIsmi) {
        this.sirketIsmi = sirketIsmi;
    }

    public String getCalismaAlani() {
        return calismaAlani;
    }

    public void setCalismaAlani(String calismaAlani) {
        this.calismaAlani = calismaAlani;
    }

}
