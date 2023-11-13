import java.util.List;

public class Checker
{
    private String color_str;
    private int index_int;
    private int position_int;
    private int pip_int;
    private int barindex_int;
    public Checker (String color_str,int index_int,int position_int,int pip_int)
    {
        this.color_str=color_str;
        this.index_int=index_int; // index 0 means position on playing field 1
        this.position_int=position_int; // position 0 means stack number 1
        this.pip_int=pip_int;
    }

    public void setColor_str(String color_str) {
        this.color_str = color_str;
    }
    public String getColor_str() {
        return color_str;
    }

    public int getPosition_int() {
        return position_int;
    }

    public void setPosition_int(int position_int) {
        this.position_int = position_int;
    }

    public int getIndex_int() {
        return index_int;
    }

    public void setIndex_int(int index_int) {
        this.index_int = index_int;
    }

    public int getPip_int() {
        return pip_int;
    }
    public void setPip_int(int pip_int) {
        this.pip_int = pip_int;
    }

    public void setBarindex_int(int barindex_int) {
        this.barindex_int = barindex_int;
    }

    public int getBarindex_int() {
        return barindex_int;
    }
}
