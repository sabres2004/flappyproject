//!  The entry point for the program.
/*!
Initializes necessary game classes and begins the game loop.
*/

public class Main {

   //  static Login login;
    static Bird Bird;
    static Map map;
    static GameLoop mainLoop = new GameLoop();

    public static void main(String[] args){
        initialize();
    }

    //! Initialize game
    /*! Creates a player, map, and gameLoop instance.*/
    public  static void initialize(){
       // login= new Login();
        Bird = new Bird();
        map = new Map();
        mainLoop.initialize();
        mainLoop.run(map, Bird, mainLoop);
    }
;
    //! Reset game
    /*! Respawns the player and starts the game over again.*/
    public static void reset(){
        //mainLoop.initialize();
        Bird.respawn();
        mainLoop.runs = true;
        Bird.flapping=true;
    }
}



















