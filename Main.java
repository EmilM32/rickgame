package rickmario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/*
* https://v-play.net/game-resources/16-sites-featuring-free-game-graphics#_Openclipart.org
*/
public class Main extends JFrame
{
    public Main()
    {
        this.setTitle("Nowa Gra!");
        this.setBounds(350, 100, 1200, 735);
        this.setResizable(false);
        
        panelGry.setBackground(Color.DARK_GRAY);
        
        startAnimation();
        
        this.getContentPane().add(panelGry);
        this.setDefaultCloseOperation(3);
    }
     
    public void startAnimation()
    {
        panelGry.addMorty();
        
    }
    
    
    private ImageIcon rick;
    private ImageIcon pill;
    private ImageIcon enemy;
    static boolean crazyMode = false;
    static int score = 0;
    
    PanelGry panelGry = new PanelGry();

    
    public static void main(String[] args)
    {
        new Main().setVisible(true);   
    }
    
    public class PanelGry extends JPanel implements KeyListener, ActionListener
    {   
        private int[] xPoz = new int[1200];
        private int[] yPoz = new int[750];
        
        public int x;
        public int y;
        
        private boolean left = false;
        private boolean right = false;
        private boolean up = false;
        private boolean down = false;       
        private boolean listap = false;
        private boolean pauza = false;
        private boolean shift = false;
        private boolean info = false;
        private boolean ctrl = false;
       
        
        private int[] xpillP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100}; //21
        private int[] ypillP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600}; //11
       
        private int[] xenemyP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100}; //21
        private int[] yenemyP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600}; //11
 
        private int[] xheartP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100}; //21
        private int[] yheartP = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600}; //11
        
        private Random random = new Random();
    
        private int xPill = random.nextInt(21);
        private int yPill = random.nextInt(11);
        
        private int xenemy = random.nextInt(21);
        private int yenemy = random.nextInt(11);
        
        private int xheart = random.nextInt(21);
        private int yheart = random.nextInt(11);
        
        private int moves = 0;
        private int lives = 3;
        
        private Timer timer;
        
        private int delay = 150;
        
     
        public void addMorty()
        {
            lista.add(new Morty());
            watek = new Thread(grupaWatkow, new MortyRunnable((Morty)lista.get(lista.size()-1)));
            watek.start(); //uruchomienie Runnable

            
            grupaWatkow.list();;
        }
        
        
        Image background = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "background.png");   
        Image ground = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "ground.png");   
        Image border = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "border.png");   
        Image border2 = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "border2.png");   
        Image box1 = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "box1.png");   
        Image pauzat = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "pauza.png");   
        Image pauzat2 = Toolkit.getDefaultToolkit().createImage("images" + File.separator + "pauza2.png");   
 
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
             if(moves == 0)
            {
                xPoz[0] = 1000;
                yPoz[0] = 600;
             }

            if(!shift)
            {
                for(int gY = 50; gY <= 650; gY += 50)
                {
                    for(int gX = 50; gX <= 1100; gX += 50)
                    {
                        g.drawImage(ground, gX, gY, this);
                    }
                }

                for(int x = 0; x < 1200; x+= 50)
                {
                    g.drawImage(border, x, 0, this);
                }
                for(int x = 0; x < 1200; x+= 50)
                {
                   g.drawImage(border, x, 650, this);
                }
                
                for(int y = 0; y < 750; y+= 50)
                {
                    g.drawImage(border, 0, y, this);
                }
                for(int y = 0; y < 750; y+= 50)
                {
                    g.drawImage(border, 1150, y, this);
                }
            }
            if(shift)
            {
                background.getScaledInstance(1200, 750, 0);
                g.drawImage(background, 0, 0, this);
                for(int x = 0; x < 1200; x+= 50)
                {
                    border2.getScaledInstance(50, 50,0);
                    g.drawImage(border2, x, 0, this);
                }
                for(int y = 0; y < 750; y+= 50)
                {
                    border2.getScaledInstance(50, 50,0);
                    g.drawImage(border2, 0, y, this);
                }
                for(int x = 0; x < 1200; x+= 50)
                {
                    border2.getScaledInstance(50, 50,0);
                    g.drawImage(border2, x, 650, this);
                }
                for(int y = 0; y < 750; y+= 50)
                {
                    border2.getScaledInstance(50, 50,0);
                    g.drawImage(border2, 1150, y, this);
                }
            }

            g.setColor(Color.RED);
            g.drawImage(box1, 1072, 10, this);
            g.drawString("Punkty: " + score, 1080, 25);
            g.drawString("Życia: " + lives, 1080, 40);
            
            if(info)
            {
                g.setColor(Color.WHITE);
                g.drawString("Ruchy: " + moves, 1080, 70);
                g.drawString("Rx: " + xPoz[x], 1080, 90);
                g.drawString("Ry: " + yPoz[y], 1080, 110);
                g.drawString("Opóźnienie: " + delay, 1080, 170);
                g.drawString("crazyMode: " + crazyMode, 1080, 190);
            }
            g.setColor(Color.WHITE);
            g.drawString("P = Pauza", 80, 30); 
            


            pill = new ImageIcon("images" + File.separator + "pill.png");

            if(xpillP[xPill] == xPoz[x] && ypillP[yPill] == yPoz[y])
            {
                score++;
                xPill = random.nextInt(21);
                yPill = random.nextInt(11);
            }

            pill.paintIcon(this, g, xpillP[xPill], ypillP[yPill]);
            
            enemy = new ImageIcon("images" + File.separator + "enemy.gif");

            if(xenemyP[xenemy] == xPoz[x] && yenemyP[yenemy] == yPoz[y])
            {
                lives--;
                xenemy = random.nextInt(21);
                yenemy = random.nextInt(11);
            }

            enemy.paintIcon(this, g, xenemyP[xenemy], yenemyP[yenemy]);   
           
            if(crazyMode)
                rick = new ImageIcon("images" + File.separator + "rick2.png");
            else
                rick = new ImageIcon("images" + File.separator + "rick.png");
            
            rick.paintIcon(this, g, xPoz[0], yPoz[0]);
            for(int i = 0; i <lista.size(); i++)
            {
                g.drawImage(Morty.getImg(), ((Morty)lista.get(i)).x, ((Morty)lista.get(i)).y, null);
                
                if(info)
                {                            
                    g.drawString("Mx: " + ((Morty)lista.get(i)).x, 1080, 130);
                    g.drawString("My: " + ((Morty)lista.get(i)).y, 1080, 150);
                }
                
                if(xPoz[x] == ((Morty)lista.get(i)).x && yPoz[y] == ((Morty)lista.get(i)).y)
                {
                    if(lives > 0)
                        lives--;
                    else if(lives == 0)
                        lives = 0;
                }
            }
            
            if(lives == 0)
            {    

                if(listap)
                {
                    ListaP okno = new ListaP();
                    okno.setTitle("Lista punktów");
                    okno.setVisible(true);
                    okno.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                    listap = false;
                }
                
                g.setColor(Color.WHITE);
                g.setFont(new Font("Verdana", Font.PLAIN, 40));
                g.drawString("Zjebałeś!", 520, 300);
                
                g.setFont(new Font("Verdana", Font.PLAIN, 30));
                g.drawString("Naciśnij spację aby zacząć od nowa", 350, 350);
                g.drawString("Naciśnij enter aby zapisać wynik", 370, 390);
                 
                timer.stop();
            }
            
            if(ctrl && score >= 10)
            {
                score = score - 10;
                lives++;
                ctrl = false;
            }
            
            if(crazyMode)
            {

               if(score%5 == 0)
                {
                    if(shift)
                        shift = false;
                    else if(!shift)
                        shift = true;
                    
                    score++;
                }
            }
            
            if(pauza)
            {
                g.drawImage(pauzat, 370, 190, this);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Verdana", Font.BOLD, 30));
                g.drawString("R = WZNÓW GRĘ", 460, 250);

                g.setFont(new Font("Verdana", Font.PLAIN, 20));
                g.drawString("W = Zwolnij ruch Mortiego", 470, 290);
                g.drawString("S = Przyśpiesz ruch Mortiego", 470, 320);
                g.drawString("Shift = Włącz tryb pijany Rick", 470, 350);
                g.drawString("I = Pokaż/ukryj dodatkowe informację", 440, 380);
                g.drawString("Ctrl = Zamień 10ptk na 1 życie", 460, 410);
                
                g.drawImage(pauzat2, 370, 450, this);
            }

            
            g.dispose();
        }
        
        
        ArrayList lista = new ArrayList();
        JPanel ten = this;
        Thread watek;
        ThreadGroup grupaWatkow = new ThreadGroup("Grupa Mortyiego");
        
        
        public class MortyRunnable implements Runnable
        {
            public MortyRunnable(Morty morty)
            {
                this.morty = morty;
            }
            @Override
            public void run() 
            {
                try 
                {
                    while(!Thread.currentThread().isInterrupted())
                    {
                       this.morty.ruch(ten);
                        repaint();
                            
                        if(!pauza)
                            Thread.sleep(delay);
                        else if(pauza)
                            Thread.sleep(1000);

                        } 

                    }
                catch (InterruptedException ex) 
                {
                    System.out.println(ex.getMessage());
                    lista.clear();
                    repaint();
                }
            }
            Morty morty;
            
        }
        
        public PanelGry() 
        {   
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(70, this);
            timer.start();
            
        }


        @Override
        public void keyTyped(KeyEvent ke) 
        {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) 
        {
            if(ke.getKeyCode() == KeyEvent.VK_5)
            {
                if(crazyMode)
                    crazyMode = false;
                else
                    crazyMode = true;
            }
            if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }
            if(ke.getKeyCode() == KeyEvent.VK_CONTROL)
            {
                ctrl = true;
            }
            if(ke.getKeyCode() == KeyEvent.VK_I)
            {
                if(!info)
                    info = true;
                else if(info)
                    info = false;
            }
            if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
            {
                if(!shift)
                    shift = true;
                else if(shift)
                    shift = false;
            }
            if(ke.getKeyCode() == KeyEvent.VK_P)
            {
                timer.stop();
                pauza = true;
                repaint();
            }
            if(ke.getKeyCode() == KeyEvent.VK_R)
            {
                timer.start();
                pauza = false;
                repaint();
            }
            if(ke.getKeyCode() == KeyEvent.VK_ENTER && lives == 0)
            {
                listap = true;
                
            }
            if(ke.getKeyCode() == KeyEvent.VK_S)
            {
                if(delay > 10)
                    delay -= 10;
                else
                    delay = 10;
            }
            if(ke.getKeyCode() == KeyEvent.VK_W)
            {
                if(delay < 150)
                    delay += 10;
                else
                    delay = 150;
            }
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
            {
                if(lives == 0)
                {
                    lives = 3;
                    score = 0;
                    
                    moves = 0;
                    shift = false;
                    crazyMode = false;
                    delay = 150;
                    timer.start();
                }
            }
            
            if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                moves++;
                if(!shift)
                    {
                    right = true;
                    left = false;
                }
                else if(shift)
                {
                    right = false;
                    left = true;                    
                }
                
                up = false;
                down = false;
            }
            if(ke.getKeyCode() == KeyEvent.VK_LEFT)
            {
                moves++;
                if(!shift)
                {
                    right = false;
                    left = true;
                }
                else if(shift)
                {
                    right = true;
                    left = false;
                }       
                
                up = false;
                down = false;
            }
            if(ke.getKeyCode() == KeyEvent.VK_UP)
            {
                moves++;
                right = false;
                left = false;
                
                if(!shift)
                {
                    up = true;
                    down = false;
                }
                else if(shift)
                {
                    up = false;
                    down = true;
                }
            }
            if(ke.getKeyCode() == KeyEvent.VK_DOWN)
            {
                moves++;
                right = false;
                left = false;
                
                if(!shift)
                {
                    up = false;
                    down = true;
                }
                else if(shift)
                {
                    up = true;
                    down = false;
                }
            }            
        }

        @Override
        public void keyReleased(KeyEvent ke) 
        {
            right = false;
            left = false;
            up = false;
            down = false;    
            listap = false;
            ctrl = false;
        }

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(right)
            {
                xPoz[x] += 50;
                
                if(xPoz[x] >= 1100)
                    xPoz[x] = 1100;
                
                repaint();
            }
            if(left)
            {
                xPoz[x] -= 50;
                
                if(xPoz[x] <= 50)
                    xPoz[x] = 50;
                
                repaint();
            }
            if(up)
            {
                yPoz[y] -= 50;
                
                if(yPoz[y] <= 50)
                    yPoz[y] = 50;                
                
                repaint();
            }
            if(down)
            {
                yPoz[y] += 50;
                
                if(yPoz[y] >= 600)
                    yPoz[y] = 600;                
                
                repaint();
            }

        }

        
    }
    
}