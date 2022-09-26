
package Final;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {

    
    
    final int JUMPSPEED = -22;
    final int MOVESPEED = 10;

    int centerX = 500;
    int centerY = 560;
    boolean jumped = false;
    boolean movingLeft = false;
    boolean movingRight = false;
    boolean ducked = false;
    public  String direction = "right";

    int speedX = 0;
    int speedY = 0;
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);
    public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
    public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
    
    public static Rectangle footleft = new Rectangle(0,0,0,0);
    public static Rectangle footright = new Rectangle(0,0,0,0);
    
    
    Animation bg1 = StartingClass.bg1;
    
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public void update() {
        if (speedX == 0){
            bg1.speedX=0;
           
        }
        
        if (centerX <= 800 && speedX > 0) {
            centerX += speedX;
        }
        if (centerX >=400 && speedX<0){
            centerX += speedX;
        }
        
        if (speedX > 0 && centerX > 800) {
            bg1.speedX= -MOVESPEED / 5;
           
        }
         if (speedX < 0 && centerX < 400){
            bg1.speedX=MOVESPEED / 5;
           
        }
        

        // Updates Y Position
        centerY += speedY;

        // Handles Jumping

          speedY += 1;

        if (speedY > 3){
            jumped = true;
        }

        rect.setRect(centerX -55, centerY - 63	, 35, 70);
        rect2.setRect(rect.getX(), rect.getY() + 70, 35, 70);//70,35/70
        yellowRed.setRect(centerX - 92, centerY - 80, 110, 180);

        footleft.setRect(centerX -60, centerY - 33, 5, 95);
        footright.setRect(centerX -20, centerY - 33, 5, 95);//20,33,5,95


    }

    public void moveRight() {
        direction = "right";
        if (ducked == false) {
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        direction = "left";
        if (ducked == false) {
            speedX = -MOVESPEED;
        }
    }

    public void stopRight() {
        movingRight=false;
        stop();
    }

    public void stopLeft() {
        movingLeft = false;
       stop();
    }

    private void stop() {
        if (movingRight == false && movingLeft == false) {
            speedX = 0;
        }

        if (movingRight == false && movingLeft == true) {
            moveLeft();
        }

        if (movingRight == true && movingLeft == false) {
            moveRight();
        }

    }

    
    //jump function
    public void jump() {
        if (jumped == false) {
            speedY = JUMPSPEED;
            jumped = true;
        }

    }

  public void shoot() {
        Projectile p;
        if (direction == "right"){
           p = new Projectile(centerX-5, centerY-5,true);
        }
        else
            p = new Projectile(centerX-5, centerY-5,false);
	projectiles.add(p);
    }


}