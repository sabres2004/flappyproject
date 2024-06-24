import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

//!  The player/bird class.
/*!
Holds all information for the bird and player.
*/
public class Bird {
    Image birdFlap;
    Image birdIdle;
    Image birdDead;

    int yPos;
    boolean flapping = false;
    boolean isAlive;
    boolean isDisabled;
    Jump jump;

    static final int birdWidth = 47;
    static final int birdHeight = 32;
    static final int birdXPos = 400;

    //! Player constructor
    /*! Creates an instance of the player object.*/
    public Bird() {
        //Initial position (bottom of screen is y=0)
        this.yPos = 100;
        isDisabled = false;
        isAlive = true;
        //Initialize and thread responsible for jumping


        //Set bird graphics
        try{
            this.birdFlap = ImageIO.read(Main.class.getResource("birdFlapping.png"));
            this.birdIdle = ImageIO.read(Main.class.getResource("birdIdle.png"));
            this.birdDead = ImageIO.read(Main.class.getResource("birdIdleDead.png"));
        } catch (IOException e) {e.printStackTrace();}


    }

    //! Get Y position
    /*! Returns the Y coordinate of the bird.*/
    public int getY(){
        return this.yPos;
    }

    //! Disable player
    /*! Prevents the player from moving.*/
    public void disable(){
        this.isDisabled = true;
    }

    //! Kill player
    /*! Game over for the player.*/
    public void kill(){
        this.isAlive = false;
        jump.reset();
    }

    //! Respawn player
    /*! Resets the player fields to default values.*/
    public void respawn(){
        this.isAlive = true;
        this.isDisabled = false;
        this.yPos = 100;
        jump = new Jump();
        jump.run();
    }

    //! Collision Detection
    /*! Checks if the player is colliding with either of the input pipes.*/
    public boolean checkCollision(Pipe topPipe, Pipe botPipe){
        //Assume colliding for simplicity
        boolean isColliding = false;
        //System.out.println("Flappy Bird Ypos: " + this.yPos + " -- " + (720 - botPipe.getLength()+50));// + " " topPipe.xPos + " -- " + topPipe.length + "	Bottom Pipe: " + botPipe.xPos + " -- " + botPipe.length);
        //If player is above the top or below bottom pipes
        if((topPipe.getX() <= birdXPos+birdWidth && topPipe.getX()>= birdXPos) || ((topPipe.getX() + Pipe.pipeWidth) < (birdXPos+birdWidth) && (topPipe.getX()+Pipe.pipeWidth) > birdXPos)){
            if (this.yPos < topPipe.getOffset() || this.yPos >= (Map.pipeGap + botPipe.getOffset() - birdHeight)) {
                isColliding = true;
            }
        }

        return isColliding;
    }

    //! Get Image
    /*! Returns the image used for the bird.*/
    public Image getImg(){
        if (this.isDisabled)
            return birdDead;

        else if (this.flapping)
            return birdFlap;
        else
            return birdIdle;
    }
}
