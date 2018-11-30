package Model;

public class KategoriModel {
    private String _id;
    private String kategoriAdi;

    public KategoriModel(String _id, String kategoriAdi) {
        this._id = _id;
        this.kategoriAdi = kategoriAdi;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }
}
