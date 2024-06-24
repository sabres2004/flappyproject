import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

//!  The pipe object class.
/*!
Holds all relevant information for the pipe objects that the player must navigate through.
*/
public class Pipe {
    private Image pipeImage;

    private int xPos, mapOffset;
    private boolean isTop;

    static final int pipeWidth = 52;
    static final int pipeHeight = 425;

    //! Constructor
    /*! Creates an instance of the pipe object.*/
    public Pipe(int xPos, int length, boolean isTop){
        this.xPos = xPos;
        this.mapOffset = length;
        this.isTop = isTop;

        try {
            if (this.isTop)
                pipeImage = ImageIO.read(Main.class.getResource("topPipe.png"));
            else
                pipeImage = ImageIO.read(Main.class.getResource("botPipe.png"));

        } catch (IOException e) {e.printStackTrace();}
    }

    //! Get X position
    /*! Returns the X coordinate of the pipe.*/
    public int getX(){
        return this.xPos;
    }

    //! Set X position
    /*! Sets the X coordinate of the pipe.*/
    public void setX(int x){
        this.xPos = x;
    }

    //! Get Off Set
    /*! Returns the portion of the pipe shown on the screen.*/
    public int getOffset(){
        return this.mapOffset;
    }

    //! Set Off Set
    /*! Sets the portion of the pipe shown on the screen.*/
    public void setOffset(int y){
        this.mapOffset = y;
    }

    //! Get Image
    /*! Returns image used for the pipe.*/
    public Image getImg(){
        return pipeImage;
    }

    //! Move pipe
    /*! Moves the pipe 3 pixels to the left.*/
    public void move(){
        xPos-=3;
    }

}
