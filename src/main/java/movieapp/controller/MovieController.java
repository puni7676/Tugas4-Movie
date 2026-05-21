package movieapp.controller;

import movieapp.dao.MovieDAO;
import movieapp.model.Movie;
import movieapp.view.MovieView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

// Controller menjadi penghubung antara View dan Model/DAO pada konsep MVC.
public class MovieController {
    private final MovieView view;
    private final MovieDAO dao;
    private List<Movie> movieList;

    public MovieController(MovieView view) {
        this.view = view;
        this.dao = new MovieDAO();
        tampilData();
    }

    public void tampilData() {
        try {
            movieList = dao.read();
            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);

            for (Movie movie : movieList) {
                model.addRow(new Object[]{
                        movie.getJudul(),
                        movie.getAlur(),
                        movie.getPenokohan(),
                        movie.getAkting(),
                        movie.getNilai()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    public void tambahData() {
        try {
            Movie movie = ambilDataDariForm(0);
            dao.create(movie);
            tampilData();
            view.clearForm();
            JOptionPane.showMessageDialog(view, "Data Movie Berhasil Ditambahkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void updateData() {
        int row = view.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan diupdate terlebih dahulu");
            return;
        }

        try {
            int id = movieList.get(row).getId();
            Movie movie = ambilDataDariForm(id);
            dao.update(movie);
            tampilData();
            view.clearForm();
            JOptionPane.showMessageDialog(view, "Data Movie Berhasil Diupdate");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void deleteData() {
        int row = view.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Apakah yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = movieList.get(row).getId();
                dao.delete(id);
                tampilData();
                view.clearForm();
                JOptionPane.showMessageDialog(view, "Data Movie Berhasil Dihapus");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        }
    }

    public void pilihData() {
        int row = view.getSelectedRow();
        if (row != -1 && movieList != null) {
            Movie movie = movieList.get(row);
            view.setForm(
                    movie.getJudul(),
                    String.valueOf(movie.getAlur()),
                    String.valueOf(movie.getPenokohan()),
                    String.valueOf(movie.getAkting())
            );
        }
    }

    private Movie ambilDataDariForm(int id) throws Exception {
        String judul = view.getJudul();
        double alur = parseNilai(view.getAlur(), "Alur");
        double penokohan = parseNilai(view.getPenokohan(), "Penokohan");
        double akting = parseNilai(view.getAkting(), "Akting");

        if (judul.trim().isEmpty()) {
            throw new Exception("Judul tidak boleh kosong");
        }

        Movie movie = new Movie(judul, alur, penokohan, akting);
        movie.setId(id);
        return movie;
    }

    private double parseNilai(String text, String namaField) throws Exception {
        if (text.trim().isEmpty()) {
            throw new Exception("Nilai " + namaField + " tidak boleh kosong");
        }

        double nilai;
        try {
            nilai = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new Exception("Nilai " + namaField + " harus berupa angka");
        }

        if (nilai < 0 || nilai > 5) {
            throw new Exception("Nilai " + namaField + " harus antara 0 sampai 5");
        }
        return nilai;
    }
}
