package Pieces;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Board.Board;
import Board.Tile;

public abstract class Piece{
    private boolean Alive;
    private boolean White;
    protected BufferedImage ImageSheet;
    protected int ImageWidth;
    public ImageIcon image;

    public Piece(boolean White){
        this.Alive = true;
        this.White = White;
        this.image = null;
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