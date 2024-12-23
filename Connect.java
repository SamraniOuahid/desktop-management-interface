package tp_swin_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/swing2";  // URL de la base de données
    private static final String USER = "root";  // Utilisateur de la base de données
    private static final String PASSWORD = "";  // Mot de passe de l'utilisateur
    private static Connection conn;  // Objet Connection pour la gestion de la connexion

    // Méthode pour obtenir la connexion
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {  // Recrée la connexion si elle est fermée
                conn = DriverManager.getConnection(URL, USER, PASSWORD);  // Connexion à la base de données
                System.out.println("Connexion réussie !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return conn;  // Retourne l'objet Connection
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (conn != null) {  // Si la connexion est ouverte
            try {
                conn.close();  // Fermeture de la connexion
                conn = null;  // Réinitialisation de la connexion pour éviter des connexions fermées non gérées
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
