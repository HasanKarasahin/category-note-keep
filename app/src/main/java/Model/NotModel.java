package Model;

public class NotModel {
    private String id;
    private String notIcerik;
    private String kategoriId;
    private String olusturulmaTarihi;
    private String bitisTarihi;
    private String yapildi;

    public NotModel(String id, String notIcerik, String kategoriId, String olusturulmaTarihi, String bitisTarihi, String yapildi) {
        this.id = id;
        this.notIcerik = notIcerik;
        this.kategoriId = kategoriId;
        this.olusturulmaTarihi = olusturulmaTarihi;
        this.bitisTarihi = bitisTarihi;
        this.yapildi = yapildi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotIcerik() {
        return notIcerik;
    }

    public void setNotIcerik(String notIcerik) {
        this.notIcerik = notIcerik;
    }

    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getOlusturulmaTarihi() {
        return olusturulmaTarihi;
    }

    public void setOlusturulmaTarihi(String olusturulmaTarihi) {
        this.olusturulmaTarihi = olusturulmaTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getYapildi() {
        return yapildi;
    }

    public void setYapildi(String yapildi) {
        this.yapildi = yapildi;
    }
}
