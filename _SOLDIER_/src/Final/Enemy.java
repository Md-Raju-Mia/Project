
package Final;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Enemy {

        int   speedX, centerX, centerY;
        Animation bg = StartingClass.bg1;
        public Rectangle r = new Rectangle(0,0,0,0);
        public int health = 5; //kotota guli khele morbe 
        boolean isdead;
        public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        int movementSpeed;
        public String direction = "left";
        
        public Enemy(int centerX, int centerY) {
		this.centerX=centerX;
		this.centerY=centerY;
	}
        
	public static void update() {
            
            for (Enemy i: enemies) {
                  
                    i.follow();
                    i.centerX += i.speedX;
                    i.speedX = i.bg.speedX * 5 + i.movementSpeed;   //very important satge for enemy
                    i.r.setBounds(i.centerX - 30, i.centerY-10, 85, 60); //30,10,85,60   //0 kore dile enemy gulo gai lagleo morbe na

                    if (i.r.intersects(Robot.yellowRed)) //eta na thakle dragon gai lagleo morbo na
                            i.checkCollision();
                   
            }
	}
        
       public void follow() {
		
                if (centerX - StartingClass.robot.centerX >650){
                    this.movementSpeed = 0;
                }
                else if (StartingClass.robot.centerX - centerX > 650){
                    this.movementSpeed = 0;
                }

		else if (Math.abs(StartingClass.robot.centerX - centerX) < 3) {
			this.movementSpeed = 0;
		}

		else {

			if (StartingClass.robot.centerX >= centerX) {
				this.direction = "right";
                                this.movementSpeed = 2;//enemy ke over kore gele ulto dike tar speed 
			} 
                        else {
                                this.direction = "left";
				this.movementSpeed = -2; //samner dike enemyr speed
			}
		}

	}
        
        void checkCollision() {
		if (r.intersects(Robot.rect) || r.intersects(Robot.rect2)){  //comment e rakhle gai lagleo enemy ,,soldier morbe na
			StartingClass.State = "dead";
			}
              
        }

	
}

