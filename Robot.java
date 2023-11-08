
/**
 * The `Robot` class represents a robot in the world.
 * This is an abstract class that serves as the base class for all robot types.
 * It defines common attributes and behaviors shared by all robots in the system.
 */

public abstract class Robot
{
    
    private int xPosition;
    private int yPosition;
    private boolean isVisible = true;
    private String colorBody;
    private String name;
    private int direction; // 1:N 2:E 3:S 4:O
    private final static int MIN_NAME_LENGTH=3;
    private static int numberOfUnnamedRobots=0;
    private static World world =new World();
    private CanvasRobot canvasRobot = new CanvasRobot(colorBody);

/**
*
* Creates a new Robot with the specified name, color, and initial position.
* If the world has available space for a new robot, it sets the name, position, and color as specified.
* If the provided name is too short, it generates a default name with a prefix and a counter.
* The robot's initial position is set within the world's boundaries. If the position is out of bounds,
* it is set to (0, 0) by default.
*
* @param newName The name of the Robot.
* @param color The color of the Robot.
* @param newPosX The initial X-coordinate position.
* @param newPosY The initial Y-coordinate position.
*/
     
public Robot(String newName, String color, int newPosX, int newPosY)
    {
        if (world.getNbRobot()!=world.getMaxRobot()){
            if (newName.trim().length() >= MIN_NAME_LENGTH)
            {
                this.name = newName.trim();
            }
            else
            {
                numberOfUnnamedRobots += 1;
                name = "iRobot" + numberOfUnnamedRobots;
            }
            if (newPosX <= World.getMaxPosition() && newPosX >= World.getMinPosition())
        {
            xPosition = newPosX;
        }
        else
        {
            xPosition = 0;
        }
        if (newPosY <= World.getMaxPosition() && newPosY >= World.getMinPosition())
        {
            yPosition = newPosY;
        }
        else
        {
            yPosition = 0;
        }
            this.colorBody = color;
            canvasRobot.drawRobot(xPosition,yPosition);
            canvasRobot.setColourBody(colorBody);
            world.getRobotList().add(this);
            }
    }   
/**
* Moves the robot. Subclasses must provide their own implementation to define
* the specific logic for moving the robot.
*/
public abstract void move();
    
    
/**
* Moves the robot down by decrementing its Y-coordinate position by 1 and updates the robot's
* position on the canvas.
*/
public void moveUp()
    {
        this.yPosition-=1;
        this.canvasRobot.drawRobot(this.xPosition,this.yPosition);
    }
    
/**
* Moves the robot down by incrementing its Y-coordinate position by 1 and updates the robot's
* position on the canvas.
*/
public void moveDown()
    {
        this.yPosition+=1;
        this.canvasRobot.drawRobot(this.xPosition,this.yPosition);
    }
    
/**
 * Moves the robot to the left by decrementing its X-coordinate position by 1 and updates the robot's
 * position on the canvas.
 */
public void moveLeft()
    {
        this.xPosition-=1;
        this.canvasRobot.drawRobot(this.xPosition,this.yPosition);
    }
    
/**
 * Moves the robot to the right by incrementing its X-coordinate position by 1 and updates the robot's
 * position on the canvas.
 */
public void moveRight()
    {
        this.xPosition+=1;
        this.canvasRobot.drawRobot(this.xPosition,this.yPosition);
    }
/**
 * Gets the current X-coordinate position of the robot.
 *
 * @return The X-coordinate position of the robot.
 */
public int getXPosition(){
        return xPosition;
    }
    
    
/**
* Sets the X-coordinate position of the robot to the specified value.
*
* @param xPosition The new X-coordinate position to set for the robot.
*/
public void setXPosition(int xPosition){
        this.xPosition = xPosition;
    }
/**
* Gets the current Y-coordinate position of the robot.
*
* @return The Y-coordinate position of the robot.
*/
public int getYPosition(){
        return yPosition;
    }

/**
* Gets the world in which the robot exists.
*
* @return The World object associated with the robot.
*/
public World getWorld(){
        return world;
    }
/**
* Sets the Y-coordinate position of the robot to the specified value.
*
* @param yPosition The new Y-coordinate position to set for the robot.
*/
public void setYPosition(int yPosition){
        this.yPosition = yPosition;
    }
    
    
/**
 * Gets the current direction of the robot.
 *
 * @return The direction of the robot.
 */
public int getDirection(){
        return direction;
    }
    
    
/**
 * Sets the direction of the robot to the specified value.
 *
 * @param direction The new direction to set for the robot.
 */
public void setDirection(int direction){
        this.direction = direction;
    }
    
/**
 * Gets the name of the robot.
 *
 * @return The name of the robot.
 */
public String getName(){
        return name;
    }
    
/**
 * Gets the color of the robot's body.
 *
 * @return The color of the robot's body.
 */
public String getColorBody(){
        return colorBody;
    }
/**
 * Gets the CanvasRobot object associated with the robot.
 *
 * @return The CanvasRobot object used for rendering the robot on the canvas.
 */
public CanvasRobot getCanvasRobot(){
        return canvasRobot;
    }
    
    
/**
 * Changes the direction of the robot by rotating it 90 degrees clockwise.
 * The robot's direction values range from 0 to 3, representing north, east, south, and west.
 * This method increments the direction value, wrapping around to 0 if it reaches 4.
 */
public void turn(){
        if (direction == 3) {
            direction = 1;
        } else {
            direction++;
        }
        
    }
    
 /**
 * Sets the color of the robot's body to the specified color.
 * The provided color is case-insensitive and should match one of the valid color options.
 * If the color is not recognized, the default color "BLUE" is used.
 *
 * @param newColor The new color for the robot's body.
 */
public void setColorBody(String newColor) {
    newColor = newColor.toUpperCase();
    if (newColor.equals("BLUE") || newColor.equals("GREEN") || newColor.equals("YELLOW") ||
        newColor.equals("ORANGE") || newColor.equals("BLACK") || newColor.equals("RED") ||
        newColor.equals("WHITE") || newColor.equals("MAGENTA") || newColor.equals("PURPLE"))
        {
        this.colorBody = newColor;
        }
    else {
        this.colorBody = "BLUE";
        }
        updateColor();
    }
    
   /**
 * Updates the color of the robot's body on the canvas.
 * This method sets the color of the robot's body on the canvas to the current colorBody value.
 */

private void updateColor(){
        canvasRobot.setColourBody(this.colorBody);
    }
    
/**
 * Checks if the robot is currently visible.
 *
 * @return true if the robot is visible, false if it is not.
 */
public boolean isVisible(){
        return isVisible;
    }
}


