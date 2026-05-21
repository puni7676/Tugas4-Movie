package movieapp.model;

// Movie mewarisi BaseEntity sebagai penerapan inheritance.
public class Movie extends BaseEntity {
    // Encapsulation: semua atribut dibuat private dan diakses melalui getter/setter.
    private String judul;
    private double alur;
    private double penokohan;
    private double akting;
    private double nilai;

    public Movie() {
    }

    public Movie(int id, String judul, double alur, double penokohan, double akting) {
        setId(id);
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        hitungNilai();
    }

    public Movie(String judul, double alur, double penokohan, double akting) {
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        hitungNilai();
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public double getAlur() {
        return alur;
    }

    public void setAlur(double alur) {
        this.alur = alur;
        hitungNilai();
    }

    public double getPenokohan() {
        return penokohan;
    }

    public void setPenokohan(double penokohan) {
        this.penokohan = penokohan;
        hitungNilai();
    }

    public double getAkting() {
        return akting;
    }

    public void setAkting(double akting) {
        this.akting = akting;
        hitungNilai();
    }

    public double getNilai() {
        return nilai;
    }

    // Nilai rating otomatis dihitung dari rata-rata alur, penokohan, dan akting.
    public final void hitungNilai() {
        this.nilai = (alur + penokohan + akting) / 3;
    }
}
