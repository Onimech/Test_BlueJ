import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;

/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Robot
{
    static ArrayList<Knight> knights = new ArrayList<Knight>();
    int x;

    
    // instance variables - replace the example below with your own
 

  /**
 * Creates a new Knight object with the specified name, color, and initial position.
 * The newly created Knight is added to a list of knights.
 *
 * @param newName The name of the Knight.
 * @param color The color of the Knight.
 * @param newPosX The initial X-coordinate position.
 * @param newPosY The initial Y-coordinate position.
 */
    public Knight(String newName, String color, int newPosX, int newPosY)
    {
       super(newName, color, newPosX, newPosY);
       knights.add(this);
       
    }
 
    /**
     * 
 * Moves the object according to a randomly generated L-shaped movement pattern.
 * The movement pattern is generated using a random number generator, and the object
 * can move in eight different directions, forming an L-shaped pattern.
 * Here are the 8 possibilities :
 * - 0 => 2 cases right, 1 case up.
 * - 1 => 2 cases right, 1 case down.
 * - 2 => 2 cases down, 1 case right.
 * - 3 => 2 cases down, 1 case left.
 * - 4 => 2 cases left, 1 case down.
 * - 5 => 2 cases left, 1 case up.
 * - 6 => 2 cases up, 1 case left.
 * - 7 => 2 cases up, 1 case right.
 */
     
@Override
public void move()
    {
        Random r = new Random();
                x=r.nextInt(8);
        switch(x){
            case 0:
                this.moveLshape(2,-1);
                
            break;
            case 1:
                this.moveLshape(2,1);
                
            break;
            case 2:
                this.moveLshape(1,2);
                
            break;
            case 3:
                this.moveLshape(-1,2);
                
            break;
            case 4:
                this.moveLshape(-2, 1);
                
            break;
            case 5:
                this.moveLshape(-2, -1);
                
            break;
            case 6:
                this.moveLshape(-1, -2);
                
            break;
            case 7:
                this.moveLshape(1, -2);
                
            break;
        }
       
    }
    
/**
 * Moves the object in an L-shaped pattern by the specified number of X and Y cases.
 * If the new position after the movement is not a collision and is within the canvas, the object is moved.
 * If there is a collision or the new position is out of the canvas boundaries, the movement is canceled, and the object's color is changed to a random color, and a collision sound is played.
 *
 * @param xCases The number of X cases to move.
 * @param yCases The number of Y cases to move.
 */
private void moveLshape(int xCases, int yCases){      
        int x;
        int y;
       
        
        // if !xPosition+Xcases || y position cases = collision or out of canvas 
        x = getXPosition() + xCases;
        y = getYPosition() + yCases;
        
        Colour[] colours = Colour.values();
        Random r = new Random();
        Colour randomColour=colours[r.nextInt(colours.length)];
        String colourStringRandom = randomColour.name();
       if (getWorld().movePossible(x,y)){
        setXPosition(x);
        setYPosition(y);}
     
       
    
        else{
    System.out.println("Collision évitée, mouvement annulé!");
    setColorBody(colourStringRandom);
    playCollisionSound();
 
}
getCanvasRobot().drawRobot(getXPosition(),getYPosition());
}

/**
 * Plays a collision sound when a collision occurs. The sound is loaded from an audio file called "bonk.wav.
 */
private void playCollisionSound() {
    try {
        // Charge le fichier audio (assurez-vous d'avoir un fichier son approprié)
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bonk.wav"));

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

    
