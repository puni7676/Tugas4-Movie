package movieapp.dao;

import movieapp.database.DatabaseConnection;
import movieapp.model.Movie;
import movieapp.service.CrudInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements CrudInterface<Movie> {

    @Override
    public void create(Movie movie) throws Exception {
        String sql = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getJudul());
            ps.setDouble(2, movie.getAlur());
            ps.setDouble(3, movie.getPenokohan());
            ps.setDouble(4, movie.getAkting());
            ps.setDouble(5, movie.getNilai());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Movie> read() throws Exception {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM movie ORDER BY id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getDouble("alur"),
                        rs.getDouble("penokohan"),
                        rs.getDouble("akting")
                );
                list.add(movie);
            }
        }
        return list;
    }

    @Override
    public void update(Movie movie) throws Exception {
        String sql = "UPDATE movie SET judul = ?, alur = ?, penokohan = ?, akting = ?, nilai = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getJudul());
            ps.setDouble(2, movie.getAlur());
            ps.setDouble(3, movie.getPenokohan());
            ps.setDouble(4, movie.getAkting());
            ps.setDouble(5, movie.getNilai());
            ps.setInt(6, movie.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM movie WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
