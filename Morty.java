package rickmario;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Morty 
{
    public static Image getImg()
    {
        return Morty.morty;
    }
    public void ruch(JPanel pojemnik)
    {
        Rectangle granicePojemnika = pojemnik.getBounds();
        
        x += dx;
        y += dy;
        
        if(y + yMorty >= granicePojemnika.getMaxY())
        {
            y = (int)(granicePojemnika.getMaxY()-yMorty);
            dy = -dy;
        }
        if(x + xMorty >= granicePojemnika.getMaxX())
        {
            x = (int)(granicePojemnika.getMaxX()-xMorty);
            dx = -dx;
        }
        if(y < granicePojemnika.getMinY())
        {
            y = (int)granicePojemnika.getMinY();
            dy = -dy;
        }
        if(x < granicePojemnika.getMinX())
        {
            x = (int)granicePojemnika.getMinX();
            dx = -dx;
        }

    }
  
    public static Image morty = new ImageIcon("images" + File.separator + "morty.png").getImage();
    
    int x = 0;
    int y = 0;
    int dx = 50;
    int dy = 50;
    int xMorty = morty.getWidth(null);
    int yMorty = morty.getHeight(null);
}
