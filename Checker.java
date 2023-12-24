import java.util.List;

public class Checker
{
    // Private fields to store checker's color, index, position, pip, and bar index
    private String color_str;
    private int index_int;
    private int position_int;
    private int pip_int;
    private int barindex_int;

    // Constructor for the Checker class.
    // Initializes the checker with the specified color, index, position, and pip values.
    public Checker (String color_str,int index_int,int position_int,int pip_int)
    {
        this.color_str=color_str;
        this.index_int=index_int; // index 0 means position on playing field 1
        this.position_int=position_int; // position 0 means stack number 1
        this.pip_int=pip_int;
    }

    // Sets the color of the checker to the specified value.
    public void setColor_str(String color_str) {
        this.color_str = color_str;
    }
    public String getColor_str() {
        return color_str;
    } // Gets the color of the checker.

    public int getPosition_int() {
        return position_int;
    } // Gets the position of the checker on the board.

    // Sets the position of the checker to the specified value.
    public void setPosition_int(int position_int) {
        this.position_int = position_int;
    }

    // Gets the index of the checker.
    public int getIndex_int() {
        return index_int;
    }

    // Sets the index of the checker to the specified value.
    public void setIndex_int(int index_int) {
        this.index_int = index_int;
    }

    // Gets the pip value of the checker.
    public int getPip_int() {
        return pip_int;
    }

    // Sets the pip value of the checker to the specified value.
    public void setPip_int(int pip_int) {
        this.pip_int = pip_int;
    }

    // Sets the bar index of the checker to the specified value.
    public void setBarindex_int(int barindex_int) {
        this.barindex_int = barindex_int;
    }

    // Gets the bar index of the checker.
    public int getBarindex_int() {
        return barindex_int;
    }
}
