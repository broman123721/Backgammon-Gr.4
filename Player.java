public class Player
{
    // Private fields to store player's name and score
    private String name;
    private int score;

    // Default constructor for the Player class.
    // This constructor is empty and doesn't initialize the fields.
    public Player()
    {
        // Constructor body intentionally left empty
    }

    // Gets the current score of the player.
    public int getScore() {
        return score;
    }

    // Sets the player's score to the specified value.
    public void setScore(int score) {
        this.score = score;
    }

    // Gets the name of the player.
    public String getName() {
        return name;
    }

    //Sets the name of the player to the specified value.
    public void setName(String name) {
        this.name = name;
    }
}
