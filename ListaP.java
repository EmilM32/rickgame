package rickmario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static rickmario.Main.score;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ListaP extends JFrame
{
    public ListaP()
    {
        this.setBounds(800, 300, 400, 500);

        initComponents();  
    }
    
    public static Connection polacz(String baza) 
    {
        Connection polaczenie = null;
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:" + baza + ".db");
            System.out.println("Połączyłem się z bazą " + baza);
        } catch (Exception e) {
            System.err.println("Błąd w połączeniu z bazą: \n" + e.getMessage());
            return null;
        }
        return polaczenie;
    }
    public static void stworzTabele(Connection polaczenie, String tabela) {
        Statement stat = null;
        try {
            stat = polaczenie.createStatement(); 
            String tabelaSQL = "CREATE TABLE " + tabela
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " IMIE        CHAR(50)     NOT NULL, "
                    + " SCORE         INT) ";
           stat.executeUpdate(tabelaSQL); 
 
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println("Nie mogę stworzyć tabeli " + e.getMessage());
        }
    }
       public static void dodajDane(Score score, String baza) {
        Connection polaczenie = null;
        Statement stat = null;
     
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:" + baza + ".db");
 
            stat = polaczenie.createStatement();
            String dodajSQL = "INSERT INTO " + baza + " (ID, IMIE, SCORE) "
                    + "VALUES ("
                    + score.getId() + ","
                    + "'" + score.getName() + "',"
                    + score.getPtk() + " "
                    + "  );";
            stat.executeUpdate(dodajSQL);
            
            ResultSet r = stat.executeQuery("SELECT COUNT(*) AS rowcount FROM score");
            r.next();
            int count = r.getInt("rowcount");
            score.setId(count);
            r.close() ;
            System.out.println(score.getId());
 
            stat.close();
            polaczenie.close();
            System.out.println("Polecenie: \n" + dodajSQL + "\n wykonane.");
        } catch (Exception e) {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
 
    private JButton dodaj = new JButton("Dodaj do listy");
    private JLabel tytul = new JLabel("Punkty");
    private JLabel imie2 = new JLabel("Twoje imie:");
    private JLabel ptk = new JLabel(score + "ptk");
    private JLabel wynik = new JLabel("Lista wyników");
    private JPanel gorny = new JPanel();
    private JPanel srodek = new JPanel();
    private JPanel lista = new JPanel();
    private JTextField imie = new JTextField(10);

    
    public void initComponents()
    { 
        gorny.add(tytul);
        srodek.add(imie2);
        srodek.add(imie);
        srodek.add(ptk);
        srodek.add(dodaj);
        tytul.setFont(new Font("Verdana", Font.BOLD, 25));
        
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                String baza = "score";
              
                Connection polaczenie = polacz(baza);
                stworzTabele(polaczenie, baza);
                Score punkciory = new Score(1, imie.getText(), score);
                punkciory.setPtk(score);
                dodajDane(punkciory, baza);
            }
        });
        
        this.getContentPane().add(gorny, BorderLayout.NORTH);
        this.getContentPane().add(srodek, BorderLayout.CENTER);
        this.getContentPane().add(lista, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(3);
    }

}
