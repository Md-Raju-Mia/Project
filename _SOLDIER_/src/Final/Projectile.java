
package Final;

import java.awt.Rectangle;

public class Projectile {

	int x, y, speedX;
	boolean visible;
        Rectangle r;
        
	
	public Projectile(int startX, int startY, boolean right){
		x = startX;
		y = startY;
                if (right)
                    speedX =30; //gulir beg
                else
                    speedX = -30; // pisoner dike gulir beg
		visible = true;
                r = new Rectangle(0, 0, 0, 0);
	}
	
	public void update(){
		x += speedX;
                r.setBounds(x, y, 10, 5);  //10, 5 enemy ke ei rectangle er vitor guli shoot kore gaieb hoe jabe
		if (x > 1366 || x<0){       //1366  gulii screen er koto dur obdhi jabe tar man
			visible = false;
                       r = null;
		}
                else
                    checkCollision();
                        
		
	}
        
        void checkCollision() { ///enemy death function
           
            for (Enemy i: Enemy.enemies){
                
                if(r.intersects(i.r)){
                            visible = false;
                            if (i.health > 0) {
                                    i.health -= 1;
                            }
                            if (i.health == 0) {
                                i.isdead=true;
                            }

                    }
            }
	}

}
