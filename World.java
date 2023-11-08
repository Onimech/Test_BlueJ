import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;
/**
 * The `World` class represents the environment in which robots operate.
 * It defines the minimum and maximum positions within the world and maintains a list of robots.
 */
public class World
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 100;
    private int MAX_Robot=15;
    private static ArrayList<Robot> robotList = new ArrayList<Robot>();

 /**
* Creates a new World instance.
*/
public World()
    {
        
    }
    
/**
 * Executes a test scenario in the world with a specified number of robots and iterations.
 * This method creates the specified number of robots and then moves them for the specified number of iterations using the `moveAll` method.
 *
 * @param nbRobots The number of robots to create and test in the world.
 * @param nbIteration The number of iterations to move the robots.
 */

public void testWorld(int nbRobots, int nbIteration){
        createLife(nbRobots);
        moveAll(nbIteration);
    }
    
/**
 * Initiates a series of actions to move robots within the world for a specified number of iterations.
 * This method plays suspenseful sounds, delays for a certain duration, plays a "Let's Go" sound, and then
 * moves each robot for the specified number of iterations, followed by playing an end sound.
 *
 * @param nbIteration The number of iterations to move the robots.
 */
public void moveAll (int nbIteration){
        playSuspenseSound();
        try
        {
            Thread.sleep(8000);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
        playLetsGoSound();
        try
        {
            Thread.sleep(2000);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
        for(int i = 0; i < nbIteration; i++){
            for(Robot robot : robotList){
                robot.move();
            }
        }
        playEndSound();
    }
    
/**
 * Creates a specified number of robots with random positions and colors.
 * This method generates and initializes the specified number of robots with random X and Y positions
 * that are within the world boundaries and assigns each robot a random color.
 *
 * @param nbRobots The number of robots to create.
 */
public void createLife(int nbRobots){
        for (int i = 0; i < nbRobots; i++){
            int newXPosition;
            int newYPosition;
            newXPosition = randomPosition();
            newYPosition = randomPosition();
            Colour[] colours = Colour.values();
            Random r = new Random();
            Colour randomColour=colours[r.nextInt(colours.length)];
        String colourStringRandom = randomColour.name();
            while (collision(newXPosition, newYPosition)){
                newXPosition = randomPosition();
                newYPosition = randomPosition();
            }
            new Knight("", colourStringRandom,newXPosition, newYPosition);
        }
    }
    
/**
 * Gets the maximum position allowed within the world.
 *
 * @return The maximum position value for the world.
 */
public static int getMaxPosition(){
        return MAX_POSITION;
    }
    
    /**
 * Gets the minimum position allowed within the world.
 *
 * @return The minimum position value for the world.
 */
public static int getMinPosition(){
        return MIN_POSITION;
    }
    
    /**
 * Generates a random position within the allowed range in the world.
 *
 * @return A randomly generated position value within the world's valid range.
 */
public int randomPosition(){
        int position = (int)(MIN_POSITION + Math.random() * (MAX_POSITION + MIN_POSITION));
        return position;
    }

    
    
    
    
    
    /**
 * Gets the maximum number of robots allowed in the world.
 *
 * @return The maximum number of robots that can exist in the world.
 */
public int getMaxRobot()
    {
        // Insérez votre code ici
        return MAX_Robot;
    }
    
    
    /**
 * Gets the current number of robots in the world.
 *
 * @return The number of robots currently present in the world.
 */
public int getNbRobot()
    {
        // Insérez votre code ici
        return robotList.size();
    }
    
    
    /**
 * Gets the list of robots currently present in the world.
 *
 * @return An ArrayList containing the robots in the world.
 */
public ArrayList<Robot> getRobotList(){
        return robotList;
    }
    
       
    /**
 * Checks if there is a collision between a robot at the specified position and other robots in the world.
 *
 * @param xrobot The X-coordinate position of the robot to check for collision.
 * @param yrobot The Y-coordinate position of the robot to check for collision.
 * @return true if a collision is detected, false otherwise.
 */
public boolean collision(int xrobot, int yrobot){
        int xloc,yloc;
        for (Robot ro : robotList){
            xloc=ro.getXPosition();
            yloc=ro.getYPosition();
            if ((xrobot==xloc) && (yrobot==yloc))  
                return true;
            }
        return false; 
            
    }
    /**
 * Checks if a robot is positioned outside the canvas boundaries.
 *
 * @param xR The X-coordinate position of the robot to check.
 * @param yR The Y-coordinate position of the robot to check.
 * @return true if the robot is outside the canvas boundaries, false otherwise.
 */
    private boolean outOfCanvas(int xR, int yR) {
        if ((xR<MIN_POSITION) || (yR<MIN_POSITION) || (xR>MAX_POSITION) || (yR>MAX_POSITION)) {
            return true;
        }
        return false;
    }
    
    
    /**
 * Checks if a move to the specified position is possible for a robot, considering collisions and canvas boundaries.
 *
 * @param xR The X-coordinate position to check for the move.
 * @param yR The Y-coordinate position to check for the move.
 * @return true if the move is possible, false if it's not due to collisions or being outside canvas boundaries.
 */
boolean movePossible(int xR, int yR){
        if(collision(xR,yR) || outOfCanvas(xR,yR)){
            return false;  
        }
        else{
            return true;
        }
    }

    
    
    

    
    /**
 * Plays the "Let's Go" sound effect.
 */
private void playLetsGoSound() {
    try {
        // Charge le fichier audio (assurez-vous d'avoir un fichier son approprié)
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("okay.wav"));

        // Créez un Clip pour lire le son
        Clip clip = AudioSystem.getClip();

        // Ajoute un écouteur pour gérer la fin de la lecture
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            }
        });

        // Ouvre le clip et démarre la lecture
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   /**
 * Plays some sound effect at the end of the moves of all the robots.
 */
private void playEndSound() {
    try {
        // Charge le fichier audio (assurez-vous d'avoir un fichier son approprié)
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("aww.wav"));

        // Créez un Clip pour lire le son
        Clip clip = AudioSystem.getClip();

        // Ajoute un écouteur pour gérer la fin de la lecture
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            }
        });

        // Ouvre le clip et démarre la lecture
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * Plays some suspensful sound effect.
 */
private void playSuspenseSound() {
    try {
        // Charge le fichier audio (assurez-vous d'avoir un fichier son approprié)
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("suspense.wav"));

        // Créez un Clip pour lire le son
        Clip clip = AudioSystem.getClip();

        // Ajoute un écouteur pour gérer la fin de la lecture
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            }
        });

        // Ouvre le clip et démarre la lecture
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

