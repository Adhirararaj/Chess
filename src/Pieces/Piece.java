package Pieces;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Board.Board;
import Board.Tile;

public abstract class Piece{
    private boolean Alive = true;
    private boolean White = true;
    protected BufferedImage ImageSheet;
    protected int ImageWidth;
    public ImageIcon image = null;

    public Piece(boolean White){
        this.White = White;
        loadImages();
    }

    private void loadImages() {
        try {
            ImageSheet = ImageIO.read(new File("res/pieces.png"));
            ImageWidth = ImageSheet.getWidth() / 6;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isWhite(){
        return this.White;
    }

    public boolean isAlive(){
        return this.Alive;
    }

    public void setAlive(boolean Alive){
        this.Alive = Alive;
    }

    public abstract boolean isValidMove(Board board, Tile start, Tile end);
}