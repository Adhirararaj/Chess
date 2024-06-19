package Players;

public abstract class Player {
    protected boolean WhiteSide;
    protected boolean HumanPlayer;

    public boolean isWhiteSide(){
        return this.WhiteSide;
    }

    public boolean isHumanPlayer(){
        return this.HumanPlayer;
    }
}
