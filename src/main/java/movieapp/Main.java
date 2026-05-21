package movieapp;

import movieapp.view.MovieView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieView().start());
    }
}
