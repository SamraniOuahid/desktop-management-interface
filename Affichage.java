package tp_swin_db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Affichage {

    private JFrame frame;
    private JTable table;
    private JTable table_1;
    private DefaultTableModel modelEtudiant;
    private DefaultTableModel modelFiliere;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Affichage window = new Affichage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Affichage() {
        initialize();
        loadDataEtudiant();
        loadDataFiliere();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1245, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Table Etudiant
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 51, 516, 343);
        frame.getContentPane().add(scrollPane);

        modelEtudiant = new DefaultTableModel(new Object[][]{}, new String[]{"CNE", "NOM", "PRENOM", "ADRESSE"});
        table = new JTable(modelEtudiant);
        scrollPane.setViewportView(table);

        // Table Filiere
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(598, 53, 497, 341);
        frame.getContentPane().add(scrollPane_1);

        modelFiliere = new DefaultTableModel(new Object[][]{}, new String[]{"NUMERO", "NOM", "NOMBRE ETUDIANTS", "ANNEE"});
        table_1 = new JTable(modelFiliere);
        scrollPane_1.setViewportView(table_1);

        JLabel lblNewLabel = new JLabel("Etudiant");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(237, 28, 132, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblFiliere = new JLabel("Filiere");
        lblFiliere.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFiliere.setBounds(780, 30, 132, 14);
        frame.getContentPane().add(lblFiliere);

        // Add buttons
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(91, 450, 89, 23);
        frame.getContentPane().add(btnAjouter);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(190, 450, 89, 23);
        frame.getContentPane().add(btnSupprimer);

        JButton btnModifier = new JButton("Modifier");
        btnModifier.setBounds(289, 450, 89, 23);
        frame.getContentPane().add(btnModifier);

        JButton btnImprimer = new JButton("Imprimer");
        btnImprimer.setBounds(388, 450, 89, 23);
        frame.getContentPane().add(btnImprimer);
        
        JButton btnImprimer_1 = new JButton("Imprimer");
        btnImprimer_1.setBounds(949, 450, 89, 23);
        frame.getContentPane().add(btnImprimer_1);
        
        JButton btnModifier_1 = new JButton("Modifier");
        btnModifier_1.setBounds(850, 450, 89, 23);
        frame.getContentPane().add(btnModifier_1);
        
        JButton btnSupprimer_1 = new JButton("Supprimer");
        btnSupprimer_1.setBounds(751, 450, 89, 23);
        frame.getContentPane().add(btnSupprimer_1);
        
        JButton btnAjouter_1 = new JButton("Ajouter");
        btnAjouter_1.setBounds(652, 450, 89, 23);
        frame.getContentPane().add(btnAjouter_1);

        // Add action listeners for buttons
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField cneField = new JTextField();
                JTextField nomField = new JTextField();
                JTextField prenomField = new JTextField();
                JTextField adresseField = new JTextField();

                Object[] message = {
                    "CNE:", cneField,
                    "Nom:", nomField,
                    "Prenom:", prenomField,
                    "Adresse:", adresseField,
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter un étudiant", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String cne = cneField.getText();
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    String adresse = adresseField.getText();

                    try (Connection conn = Connect.getConnection();
                         Statement stmt = conn.createStatement()) {
                        String query = "INSERT INTO etudiant (cne, nom, prenom, adresse) VALUES ('" + cne + "', '" + nom + "', '" + prenom + "', '" + adresse + "')";
                        stmt.executeUpdate(query);
                        modelEtudiant.addRow(new Object[]{cne, nom, prenom, adresse});
                        JOptionPane.showMessageDialog(null, "Étudiant ajouté avec succès !");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout : " + ex.getMessage());
                    }
                }
            }
        });


        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String cne = modelEtudiant.getValueAt(selectedRow, 0).toString();

                    try (Connection conn = Connect.getConnection();
                         Statement stmt = conn.createStatement()) {
                        String query = "DELETE FROM etudiant WHERE cne = '" + cne + "'";
                        stmt.executeUpdate(query);
                        modelEtudiant.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Étudiant supprimé avec succès !");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression : " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer !");
                }
            }
        });


        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String cne = modelEtudiant.getValueAt(selectedRow, 0).toString();
                    String nom = modelEtudiant.getValueAt(selectedRow, 1).toString();
                    String prenom = modelEtudiant.getValueAt(selectedRow, 2).toString();
                    String adresse = modelEtudiant.getValueAt(selectedRow, 3).toString();

                    JTextField nomField = new JTextField(nom);
                    JTextField prenomField = new JTextField(prenom);
                    JTextField adresseField = new JTextField(adresse);

                    Object[] message = {
                        "Nom:", nomField,
                        "Prenom:", prenomField,
                        "Adresse:", adresseField,
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Modifier un étudiant", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String newNom = nomField.getText();
                        String newPrenom = prenomField.getText();
                        String newAdresse = adresseField.getText();

                        try (Connection conn = Connect.getConnection();
                             Statement stmt = conn.createStatement()) {
                            String query = "UPDATE etudiant SET nom = '" + newNom + "', prenom = '" + newPrenom + "', adresse = '" + newAdresse + "' WHERE cne = '" + cne + "'";
                            stmt.executeUpdate(query);

                            // Mettre à jour le tableau
                            modelEtudiant.setValueAt(newNom, selectedRow, 1);
                            modelEtudiant.setValueAt(newPrenom, selectedRow, 2);
                            modelEtudiant.setValueAt(newAdresse, selectedRow, 3);

                            JOptionPane.showMessageDialog(null, "Étudiant modifié avec succès !");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erreur lors de la modification : " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier !");
                }
            }
        });


        btnImprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to print the list of students or filieres
            }
        });
    }

    // Méthode pour charger les données de la table Etudiant
    private void loadDataEtudiant() {
        try (Connection conn = Connect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM etudiant")) {

            while (rs.next()) {
                String cne = rs.getString("cne");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                modelEtudiant.addRow(new Object[]{cne, nom, prenom, adresse});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }
    }

    // Méthode pour charger les données de la table Filiere
    private void loadDataFiliere() {
        try (Connection conn = Connect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM filiere")) {

            // Effacer les anciennes lignes dans le modèle
            modelFiliere.setRowCount(0);

            // Ajouter les données ligne par ligne
            while (rs.next()) {
                int numero = rs.getInt("numero");
                String nom = rs.getString("nom");
                int nombreEtud = rs.getInt("nombreEtud");
                Date annee = rs.getDate("annee");
                modelFiliere.addRow(new Object[]{numero, nom, nombreEtud, annee});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }
    }

}
