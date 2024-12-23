package tp_swin_db;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;

public class Etudiant {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Etudiant window = new Etudiant();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Etudiant() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 414, 50);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // Add buttons
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(10, 10, 89, 23);
        panel.add(btnAjouter);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(109, 10, 89, 23);
        panel.add(btnSupprimer);

        JButton btnModifier = new JButton("Modifier");
        btnModifier.setBounds(208, 10, 89, 23);
        panel.add(btnModifier);

        JButton btnImprimer = new JButton("Imprimer");
        btnImprimer.setBounds(307, 10, 89, 23);
        panel.add(btnImprimer);

        // Add action listeners for buttons
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to add a student
            }
        });

        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to delete a student
            }
        });

        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to modify a student
            }
        });

        btnImprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to print the list of students
            }
        });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(10, 70, 414, 180);
        frame.getContentPane().add(layeredPane);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(113, 29, 101, 23);
        layeredPane.add(textPane);

        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBounds(113, 83, 101, 23);
        layeredPane.add(textPane_1);

        JTextPane textPane_2 = new JTextPane();
        textPane_2.setBounds(113, 130, 101, 23);
        layeredPane.add(textPane_2);

        JTextPane textPane_3 = new JTextPane();
        textPane_3.setBounds(113, 184, 101, 23);
        layeredPane.add(textPane_3);

        JLabel lblNewLabel = new JLabel("CNE");
        lblNewLabel.setBounds(28, 38, 49, 14);
        layeredPane.add(lblNewLabel);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(28, 92, 49, 14);
        layeredPane.add(lblNom);

        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(28, 139, 49, 14);
        layeredPane.add(lblPrenom);

        JLabel lblAdresse = new JLabel("Adresse");
        lblAdresse.setBounds(28, 193, 49, 14);
        layeredPane.add(lblAdresse);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(365, 30, 110, 22);
        layeredPane.add(comboBox);

        JLabel lblListeEutudient = new JLabel("Liste etudiant");
        lblListeEutudient.setBounds(250, 38, 111, 14);
        layeredPane.add(lblListeEutudient);
    }
}
