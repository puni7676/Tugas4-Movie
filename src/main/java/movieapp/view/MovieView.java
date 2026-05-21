package movieapp.view;

import movieapp.controller.MovieController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class MovieView extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField txtJudul;
    private final JTextField txtAlur;
    private final JTextField txtPenokohan;
    private final JTextField txtAkting;
    private final JButton btnTambah;
    private final JButton btnUpdate;
    private final JButton btnDelete;
    private final JButton btnClear;
    private MovieController controller;

    public MovieView() {
        setTitle("Movie App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(new Object[]{"Judul", "Alur", "Penokohan", "Akting", "Nilai"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(430, 400));
        add(scrollPane, BorderLayout.CENTER);

        txtJudul = new JTextField(15);
        txtAlur = new JTextField(15);
        txtPenokohan = new JTextField(15);
        txtAkting = new JTextField(15);
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        add(formPanel(), BorderLayout.EAST);
        actionButton();
    }

    private java.awt.Component formPanel() {
        java.awt.Panel panel = new java.awt.Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.gridy = 0;
        panel.add(new JLabel("Judul"), gbc);
        gbc.gridy = 1;
        panel.add(txtJudul, gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Alur"), gbc);
        gbc.gridy = 3;
        panel.add(txtAlur, gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Penokohan"), gbc);
        gbc.gridy = 5;
        panel.add(txtPenokohan, gbc);

        gbc.gridy = 6;
        panel.add(new JLabel("Akting"), gbc);
        gbc.gridy = 7;
        panel.add(txtAkting, gbc);

        gbc.gridy = 8;
        panel.add(btnTambah, gbc);
        gbc.gridy = 9;
        panel.add(btnUpdate, gbc);
        gbc.gridy = 10;
        panel.add(btnDelete, gbc);
        gbc.gridy = 11;
        panel.add(btnClear, gbc);

        return panel;
    }

    private void actionButton() {
        btnTambah.addActionListener(e -> controller.tambahData());
        btnUpdate.addActionListener(e -> controller.updateData());
        btnDelete.addActionListener(e -> controller.deleteData());
        btnClear.addActionListener(e -> clearForm());
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                controller.pilihData();
            }
        });
    }

    public void start() {
        controller = new MovieController(this);
        setVisible(true);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public String getJudul() {
        return txtJudul.getText();
    }

    public String getAlur() {
        return txtAlur.getText();
    }

    public String getPenokohan() {
        return txtPenokohan.getText();
    }

    public String getAkting() {
        return txtAkting.getText();
    }

    public void setForm(String judul, String alur, String penokohan, String akting) {
        txtJudul.setText(judul);
        txtAlur.setText(alur);
        txtPenokohan.setText(penokohan);
        txtAkting.setText(akting);
    }

    public void clearForm() {
        txtJudul.setText("");
        txtAlur.setText("");
        txtPenokohan.setText("");
        txtAkting.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieView().start());
    }
}
