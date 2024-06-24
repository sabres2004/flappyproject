import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//!  The Bird movement class.
/*!
Responsible for the physics involved in the bird movement and gravity.
*/
public class Jump extends Thread{
    private int upTime=1, downTime=1, boostTime;
    Timer timer;

    //! Run Physics
    /*! Main loop for bird movement.*/
    public void run(){
        timer = new Timer(20, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(Main.Bird.isAlive){
                    if(Main.Bird.flapping && Main.Bird.yPos>=0 && !Main.Bird.isDisabled){
                        downTime=1;
                        if(boostTime!=0){
                            Main.Bird.yPos= Main.Bird.yPos-2*boostTime;
                            boostTime--;
                        }
                        Main.Bird.yPos=(int)(Main.Bird.yPos-0.04*(upTime*upTime));
                        upTime++;
                        if(Main.Bird.yPos<=0 && !Main.Bird.isDisabled){
                            Main.Bird.yPos=-4;
                        }
                    }
                    else if (!Main.Bird.flapping && Main.Bird.yPos<=720){
                        upTime=1;
                        boostTime=6;
                        Main.Bird.yPos=(int)(Main.Bird.yPos+downTime/2);
                        downTime++;
                        if(Main.Bird.yPos>=1275) {
                            Main.Bird.yPos=1280;
                        }
                    }
                }

            }});
        timer.start();
    }

    //! Reset timer
    /*! Stops the current timer and starts a new one.*/
    public void reset(){

        timer.stop();
    }
}
