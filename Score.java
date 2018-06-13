package rickmario;

public class Score 
{
    private int id;
    private int score;
    private String imie;
    
    public Score(int id, String imie, int score)
    {
        this.id = id;
        this.score = score;
        this.imie = imie;
    }

     public int getId() {
        return id;
    }
 
    public void setId(int nr) {
        this.id = id;
    }
     public int getPtk() {
        return score;
    }
 
    public void setPtk(int nr) {
        this.score = score;
    }
 
    public String getName() {
        return imie;
    }
 
    public void setName(String imie) {
        this.imie = imie;
    }
}
