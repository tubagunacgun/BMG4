package siniflar;


public class Giris {

   
    private String ogrenciKullanici;
    private String ogrenciSifre;
    
    private String akademisyenKullanici;
    private String akademisyenSifre;
    
    private String teknoparkKullanici;
    private String teknoparkSifre;
    
    private String toplulukKullanici;
    private String toplulukSifre;
    
    

    public Giris() {
        this.ogrenciKullanici="ogrenci";
        this.ogrenciSifre="123";
        
        this.akademisyenKullanici="akademisyen";
        this.akademisyenSifre="123";
        
        this.teknoparkKullanici="teknopark";
        this.teknoparkSifre="123";
        
        this.toplulukKullanici="topluluk";
        this.toplulukSifre="123";
        
    }
    public boolean ogrenciGiris(String kullaniciAdi,String sifre){
        if(this.ogrenciKullanici.equals(kullaniciAdi) && this.ogrenciSifre.equals(sifre)){
            return true;
        }
        else
          return false;
    }
    public boolean akademisyenGiris(String kullaniciAdi,String sifre){
        if(this.akademisyenKullanici.equals(kullaniciAdi) && this.akademisyenSifre.equals(sifre)){
            return true;
        }
        else 
            return false;
            
    }public boolean teknoparkGiris(String kullaniciAdi,String sifre){
        if(this.teknoparkKullanici.equals(kullaniciAdi) && this.teknoparkSifre.equals(sifre)){
            return true;
        }
        else 
            return false;
    }public boolean toplulukGiris(String kullaniciAdi,String sifre){
         if(this.toplulukKullanici.equals(kullaniciAdi) && this.toplulukSifre.equals(sifre)){
            return true;
        }
        else 
            return false;
    }

    public String getOgrenciKullanici() {
        return ogrenciKullanici;
    }

    public void setOgrenciKullanici(String ogrenciKullanici) {
        this.ogrenciKullanici = ogrenciKullanici;
    }

    public String getOgrenciSifre() {
        return ogrenciSifre;
    }

    public void setOgrenciSifre(String ogrenciSifre) {
        this.ogrenciSifre = ogrenciSifre;
    }

    public String getAkademisyenKullanici() {
        return akademisyenKullanici;
    }

    public void setAkademisyenKullanici(String akademisyenKullanici) {
        this.akademisyenKullanici = akademisyenKullanici;
    }

    public String getAkademisyenSifre() {
        return akademisyenSifre;
    }

    public void setAkademisyenSifre(String akademisyenSifre) {
        this.akademisyenSifre = akademisyenSifre;
    }

    public String getTeknoparkKullanici() {
        return teknoparkKullanici;
    }

    public void setTeknoparkKullanici(String teknoparkKullanici) {
        this.teknoparkKullanici = teknoparkKullanici;
    }

    public String getTeknoparkSifre() {
        return teknoparkSifre;
    }

    public void setTeknoparkSifre(String teknoparkSifre) {
        this.teknoparkSifre = teknoparkSifre;
    }

    public String getToplulukKullanici() {
        return toplulukKullanici;
    }

    public void setToplulukKullanici(String toplulukKullanici) {
        this.toplulukKullanici = toplulukKullanici;
    }

    public String getToplulukSifre() {
        return toplulukSifre;
    }

    public void setToplulukSifre(String toplulukSifre) {
        this.toplulukSifre = toplulukSifre;
    }

}
