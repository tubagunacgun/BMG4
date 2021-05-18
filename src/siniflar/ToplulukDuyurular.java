package siniflar;

public class ToplulukDuyurular extends TumDuyurular {

    private String toplulukIsmi;

    public ToplulukDuyurular(String yayinlanmaTarihi, String gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem, String duyuruIsmi) {
        super(yayinlanmaTarihi, gecerlilikTarihi, duyuran, duyuru, duyuruOnem, duyuruIsmi);
    }

    
    public ToplulukDuyurular(String toplulukIsmi, String yayinlanmaTarihi, String gecerlilikTarihi, String duyuran, String duyuru, String duyuruOnem, String duyuruIsmi) {
        super(yayinlanmaTarihi, gecerlilikTarihi, duyuran, duyuru, duyuruOnem, duyuruIsmi);
        this.toplulukIsmi = toplulukIsmi;
    }
    
    public String getToplulukIsmi() {
        return toplulukIsmi;
    }

    public void setToplulukIsmi(String toplulukIsmi) {
        this.toplulukIsmi = toplulukIsmi;
    }

}
