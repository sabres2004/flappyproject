import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

//!  The map class. 
/*!
Responsible for the drawing of all game elements and user inputs
*/
public class Map extends JPanel{
    JFrame frame;
    Image mapBackground;

    static final int pipeGap = 180;
    int score;

    //Whether game is in the gameState
    boolean play = false;
    boolean mainMenu = true;
    boolean highScore = false;
    boolean gameOver = false;
    String buttonSelected = "play";
    BufferedImage playButton;
    BufferedImage rankButton;
    BufferedImage playTrans;
    BufferedImage rankTrans;
    BufferedImage title;
    BufferedImage highScores;
    BufferedImage scoreBoard;
    BufferedImage gameOverImg;
    BufferedImage numberImg[] = new BufferedImage[10];
    BufferedImage medals[] = new BufferedImage[4];

    //! Map constructor
    /*! Creates an instance of the map object and loads all required images.*/
    public Map(){
        try {
            mapBackground=ImageIO.read(Main.class.getResource("background.png"));
            playButton = ImageIO.read(Main.class.getResource("playbutton.png"));
            rankButton = ImageIO.read(Main.class.getResource("rankbutton.png"));
            playTrans = ImageIO.read(Main.class.getResource("playtrans.png"));
            rankTrans = ImageIO.read(Main.class.getResource("ranktrans.png"));
            title = ImageIO.read(Main.class.getResource("title.png"));
            scoreBoard = ImageIO.read(Main.class.getResource("scoreBoard.png"));
            highScores = ImageIO.read(Main.class.getResource("highScores.png"));
            gameOverImg = ImageIO.read(Main.class.getResource("gameOver.png"));
            medals[0] = ImageIO.read(Main.class.getResource("bronze.png"));
            medals[1] = ImageIO.read(Main.class.getResource("silver.png"));
            medals[2] = ImageIO.read(Main.class.getResource("gold.png"));
            medals[3] = ImageIO.read(Main.class.getResource("platinum.png"));

            for (int i = 0; i < 10; i++)
                numberImg[i] = ImageIO.read(Main.class.getResource(i + ".png"));


        } catch (IOException e1) {}
        frame=new JFrame("Flappy Bird ");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add(this);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if (Main.Bird.isDisabled && play){}

                    else if(play){
                        //Flap once
                        if (!Main.Bird.flapping)
                            Main.mainLoop.playSound("sfx_wing.wav");
                        Main.Bird.flapping=true;

                    }

                    else if (highScore){
                        mainMenu = true;
                        highScore = false;
                        updateUI();
                    }

                    else if (gameOver){
                        mainMenu = true;
                        gameOver = false;
                        updateUI();
                    }

                    else if (buttonSelected == "play"){
                        //Reset game
                        Main.reset();
                        play=true;
                        mainMenu=false;
                        highScore = false;
                        gameOver = false;
                    }

                    else if (buttonSelected == "rank"){
                        play = false;
                        mainMenu = false;
                        highScore = true;
                        gameOver = false;
                        updateUI();
                    }
                }

                else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    if (mainMenu){
                        if(buttonSelected == "rank") {buttonSelected = "play";}
                        updateUI();
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (mainMenu){
                        if(buttonSelected == "play") {buttonSelected = "rank";}
                        updateUI();
                    }
                }

                else if (e.getKeyCode() == KeyEvent.VK_1){
                    if (play)
                        Main.mainLoop.score += 10;
                }

                else if (e.getKeyCode() == KeyEvent.VK_1){
                    if (play)
                        Main.mainLoop.score += 10;
                }

                else if (e.getKeyCode() == KeyEvent.VK_2){
                    if (play)
                        Main.mainLoop.score += 25;
                }

                else if (e.getKeyCode() == KeyEvent.VK_3){
                    if (play)
                        Main.mainLoop.score += 50;
                }

                else if (e.getKeyCode() == KeyEvent.VK_4){
                    if (play)
                        Main.mainLoop.score += 100;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(play){
                        Main.Bird.flapping=false;
                    }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }

        });


        updateUI();
    }

    //! Number drawing
    /*! Draws the inputted number as an image at the specified location*/
    public void drawNumber(int number, int x, int y, Graphics g){

        String strNumber = Integer.toString(number);

        for (int i = 0; i < strNumber.length(); i++) {
            g.drawImage(numberImg[Character.getNumericValue(strNumber.toCharArray()[i])], x + i*55 ,y,null);
        }
    }

    //! Main drawing method
    /*! Draws all game elements in the correct order and based on the game state.*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph =(Graphics2D)g;

        graph.drawImage(mapBackground,0,0,null);

        //Game state
        if(play){
            for (int i =0;i<Main.mainLoop.topPipe.size();i++){
                graph.drawImage(Main.mainLoop.topPipe.get(i).getImg(), Main.mainLoop.topPipe.get(i).getX(), (Main.mainLoop.topPipe.get(i).getOffset()-Pipe.pipeHeight), null);
                graph.drawImage(Main.mainLoop.botPipe.get(i).getImg(), Main.mainLoop.botPipe.get(i).getX(), (pipeGap + (Main.mainLoop.botPipe.get(i).getOffset())), null);
                drawNumber(score, 1280/2, 50, g);
                graph.drawImage(Main.Bird.getImg(), Bird.birdXPos,Main.Bird.getY(),null);
                score = Main.mainLoop.score;

            }
        }

        else if(mainMenu){
            if (buttonSelected == "play"){
                graph.drawImage(playButton,150,300,null);
                graph.drawImage(rankTrans,750,300,null);

            }
            else{
                graph.drawImage(playTrans,150,300,null);
                graph.drawImage(rankButton,750,300,null);
            }

            graph.drawImage(title,450,100,null);
            //drawNumber(2325, 0, 0, g);
        }

        else if (highScore){
            graph.drawImage(highScores, 350, 20, null);
            for (int i = 0; i < Main.mainLoop.highScore.length; i++){
                drawNumber(Main.mainLoop.highScore[i], 400 + (int)Math.round(i*0.1)*400, 130 + (60+i*100) - (int)Math.round(i*0.1)*500, g);

            }
        }

        else if (gameOver){
            graph.drawImage(gameOverImg, 420, 70, null);
            graph.drawImage(scoreBoard, 350, 250, null);
            drawNumber(score, 1280/2 + 90, 320, g);
            drawNumber(Main.mainLoop.highScore[0], 1280/2 + 90, 436, g);

            if (score >= 100)
                graph.drawImage(medals[3], 412, 350, null);
            else if (score >= 50)
                graph.drawImage(medals[2], 412, 350, null);
            else if (score >= 25)
                graph.drawImage(medals[1], 412, 350, null);
            else if (score >= 10)
                graph.drawImage(medals[0], 412, 350, null);

        }

    }
}
