package movieapp.model;

// Inheritance: kelas ini menjadi parent untuk model lain.
public abstract class BaseEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
