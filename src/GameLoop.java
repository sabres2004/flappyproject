import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

//!  The main game loop class.
/*!
Responsible for the sequence of game events.
*/
public class GameLoop extends Thread{
    int nP = 6;	//# of pipes
    //! Pipe ArrayList.
    /*! Array containing all of the top pipe objects.*/
    ArrayList<Pipe> topPipe = new ArrayList<Pipe>(nP);
    //! Pipe ArrayList.
    /*! Array containing all of the bottom pipe objects.*/
    ArrayList<Pipe> botPipe = new ArrayList<Pipe>(nP);

    boolean runs = false; /*! Boolean to determine whether the game loop should iterate or not. */

    Timer tick;
    int distance = 0; /*! The distance between pipes. */
    int score; /*! The player's game score. */
    boolean[] hasPassed = new boolean[nP]; /*! Array to keep track of whether the bird has been awarded point for the set of pipes. */

    //	READ AND WRITE PATH
    File pathFile = new File ("data\\flappy_HS.txt");
    String path = pathFile.getAbsolutePath(); //path to write and read from

    int highScore[] = new int[10];/*! Array storing the player's top 10 scores. */

    Clip clip;
    AudioInputStream audioInputStream;

    //! Game initialization
    /*! Initializes all required variables and fields.*/
    public void initialize(){
        distance = (1280+52)/nP;
        topPipe.clear();
        botPipe.clear();
        score = 0;
        for(int i = 0; i<nP;i++){
            double rand = Math.random();
            topPipe.add(new Pipe(1280+i*distance,(int)(100+rand*250),true));
            botPipe.add(new Pipe(1280+i*distance,(int)(100+rand*250),false));
            hasPassed[i] = false;
        }
        try {
            readFromFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for(int i =0;i<highScore.length;i++)
            System.out.println(highScore[i]);
    }

    //! Main game loop
    /*! Manages hit collision, bird movement, and pipe spawning.*/
    public void run(Map map, Bird player, GameLoop mainLoop){

        Timer tick = new Timer(20, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(runs){
                    map.updateUI();
                    for (int i = 0; i<nP;i++){
                        double rand = Math.random();
                        if (!player.isDisabled) {
                            topPipe.get(i).move();
                            botPipe.get(i).move();
                        }

                        if(topPipe.get(i).getX() < Bird.birdXPos){
                            if(!hasPassed[i]){
                                score++;
                                hasPassed[i] = true;
                                playSound("sfx_point.wav");

                            }
                        }

                        if(topPipe.get(i).getX() < -52){
                            int spawnRand = (int)(100+rand*250);
                            topPipe.set(i, new Pipe(1280, spawnRand, true));
                            botPipe.set(i, new Pipe(1280, spawnRand, false));
                            hasPassed[i] = false;
                        }


                        //Check collision
                        if (player.checkCollision(topPipe.get(i), botPipe.get(i)) && !player.isDisabled){
                            player.disable();
                            playSound("sfx_hit.wav");

                            map.updateUI();
                        }

                        if (player.yPos > 720){
                            player.kill();
                            map.updateUI();
                            mainLoop.runs = false;
                            map.play  = false;
                            map.mainMenu = false;
                            map.highScore = false;
                            map.gameOver = true;
                            runs = false;
                            checkHighScore();
                            initialize();
                            playSound("sfx_die.wav");

                            return;
                        }
                    }
                }}});
        tick.start();
    }

    public void playSound(String s) {
    }

    //! High score checker
    /*! Iterates through high scores text file to compare current score to previous score.*/
    private void checkHighScore(){
        for(int i=0;i<highScore.length;i++){
            if(score>highScore[i]){
                //Update high scores by inserting new score and pushing everything below the rest down by 1 index
                int oldScore1 = highScore[i];
                highScore[i] = score;
                int oldScore2 = oldScore1;

                for (int j = i+1;j<highScore.length;j++){
                    oldScore1 = highScore[j];
                    highScore[j] = oldScore2;
                    oldScore2 = oldScore1;
                }
                try {
                    writeToFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            }
        }
    }

    private void writeToFile() throws IOException{

        PrintWriter print_line = new PrintWriter(path, "UTF-8");

        for (int i = 0; i < highScore.length; i++){
            print_line.println(highScore[i]);
            //System.out.println(line[i]);
        }

        print_line.close();

        System.out.println("Done Writing");
    }

    //Reads from text file and fill cellContents char array and sets piece to current turn.
    private void readFromFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        for(int i = 0;i<highScore.length;i++)
            highScore[i] = Integer.parseInt(textReader.readLine());
        textReader.close();
    }}
