
package Final;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
    
    int tileX, tileY, speedX, type;
    Image tileImage;
    Robot robot = StartingClass.robot;
    Animation bg = StartingClass.bg1;
    Rectangle r;

    
    public Tile(int x, int y, int typeint){
        tileX = x*40;
        tileY = y*40;
        type = typeint;
        r = new Rectangle();    
        if (type == 2)
            tileImage = StartingClass.grasstop; 
        else if (type == 3)
            tileImage = StartingClass.tiledirt;
        else if (type == 4)
            tileImage = StartingClass.tilestone;
        else if (type == 5)
            tileImage = StartingClass.tiletower;
        else if (type == 6)
            tileImage = StartingClass.tilerock;
        else if(type == 7)
        {
            tileImage = StartingClass.nationalflag;
        }
        
        else {
            type = 0;
        }
    }
    public void update(){
        speedX = bg.speedX * 5;
        tileX += speedX;  
        r.setBounds(tileX, tileY, 40, 40);
        
        if (r.intersects(Robot.yellowRed) && type != 0) {
                                
				checkVerticalCollision(Robot.rect, Robot.rect2);
				checkSideCollision(Robot.footleft, Robot.footright); // for gravity
			}
        
        
        for (Enemy i: Enemy.enemies){
                if(r.intersects(i.r)  && ( type == 2 || type == 3 || type == 6 )){
                        if (i.centerX<tileX)
                            i.centerX=tileX-55;//55
                        else if (i.centerX>tileX)
                            i.centerX=tileX+56;//56
                    }
               
            }
    }
  
    
    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
        if ( type == 2 || type == 3 || type == 6 ){
            if (rtop.intersects(r)) {
                robot.centerY=(tileY+100);//100
                robot.speedY=-robot.JUMPSPEED;
            }

            if (rbot.intersects(r)) {
                robot.jumped=false;
                robot.speedY=0;
                robot.centerY=(tileY - 75); //75
            }
        }
        
        
    }
     
     public void checkSideCollision(Rectangle leftfoot, Rectangle rightfoot) {  //this method is for gravity
        if (type == 2 || type == 3 || type == 6 ){
            
           if (leftfoot.intersects(r)) {
                robot.centerX=(tileX + 100); //100
                robot.speedX=0;
                
                
            }
            
            else if (rightfoot.intersects(r)) {
                robot.centerX=(tileX + 15);
                robot.speedX=0;
               
            }
           
        }
     
    }

}