package Final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartingClass extends JPanel implements Runnable, KeyListener {

    static StartingClass starter = new StartingClass();

    public static Robot robot;
    public BufferedImage currentSprite, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11,
            X0, X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, X11, crouch_r0, crouch_r1, crouch_r2, crouch_r3,
            crouch_l0, crouch_l1, crouch_l2, crouch_l3, bulletr, bulletl,
            characterJumped_r, characterJumped_l, background1, background2,
            el, e2l, er, e2r, start,
            deathimg,win;

    public boolean ctrl_press;
    int bullet = 0;
    ArrayList projectiles;
    public static Animation bg1;
    public Animation Rajuanim_r, Rajuanim_l, Rajuanim, anim_still, anim_still_r, anim_still_l,
            crouchdown_r, crouchdown_r1, crouchdown_l;
    public static Animation Rhanim_l, Rhanim_r;

    public static BufferedImage tiledirt, grasstop, tilestone, tiletower, tilerock, nationalflag;
    public static ArrayList<Tile> tilearray = new ArrayList<Tile>();

    static String State = "start";

    public static void restart() throws IOException {

        tilearray.clear();
        Enemy.enemies.clear();

        bg1 = new Animation(0, 0); //background
        robot = new Robot();

        starter.loadMap("data/map1.txt");

    }

    public void init() throws Exception {

        setFocusable(true); //for button press and start game
        addKeyListener(this);  // for button press and start game

        characterJumped_r = ImageIO.read(new File("Data/JumpedR.png"));

        characterJumped_l = ImageIO.read(new File("Data/JumpedL.png"));

        background1 = ImageIO.read(new File("Data/Bg1.png"));
        background2 = ImageIO.read(new File("Data/Bg2.png"));

        tiledirt = ImageIO.read(new File("Data/TileDirt.png"));
        grasstop = ImageIO.read(new File("Data/GrassTop.png"));
        tilestone = ImageIO.read(new File("Data/TileStone.png"));
        tiletower = ImageIO.read(new File("Data/TileTower.png"));
        tilerock = ImageIO.read(new File("Data/TileRock.png"));
        nationalflag = ImageIO.read(new File("Data/NationalFlag.png"));

        bulletr = ImageIO.read(new File("Data/Bulletr.png"));
        bulletl = ImageIO.read(new File("Data/Bulletl.png"));

        A0 = ImageIO.read(new File("Data/Run/walk-b (1).png"));
        A1 = ImageIO.read(new File("Data/Run/walk-b (2).png"));
        A2 = ImageIO.read(new File("Data/Run/walk-b (3).png"));
        A3 = ImageIO.read(new File("Data/Run/walk-b (4).png"));
        A4 = ImageIO.read(new File("Data/Run/walk-b (5).png"));
        A5 = ImageIO.read(new File("Data/Run/walk-b (6).png"));
        A6 = ImageIO.read(new File("Data/Run/walk-b (7).png"));
        A7 = ImageIO.read(new File("Data/Run/walk-b (8).png"));
        A8 = ImageIO.read(new File("Data/Run/walk-b (9).png"));
        A9 = ImageIO.read(new File("Data/Run/walk-b (10).png"));
        A10 = ImageIO.read(new File("Data/Run/walk-b (11).png"));
        A11 = ImageIO.read(new File("Data/Run/walk-b (12).png"));

        X0 = ImageIO.read(new File("Data/Run/walk-a (1).png"));
        X1 = ImageIO.read(new File("Data/Run/walk-a (2).png"));
        X2 = ImageIO.read(new File("Data/Run/walk-a (3).png"));
        X3 = ImageIO.read(new File("Data/Run/walk-a (4).png"));
        X4 = ImageIO.read(new File("Data/Run/walk-a (5).png"));
        X5 = ImageIO.read(new File("Data/Run/walk-a (6).png"));
        X6 = ImageIO.read(new File("Data/Run/walk-a (7).png"));
        X7 = ImageIO.read(new File("Data/Run/walk-a (8).png"));
        X8 = ImageIO.read(new File("Data/Run/walk-a (9).png"));
        X9 = ImageIO.read(new File("Data/Run/walk-a (10).png"));
        X10 = ImageIO.read(new File("Data/Run/walk-a (11).png"));
        X11 = ImageIO.read(new File("Data/Run/walk-a (12).png"));

        crouch_r0 = ImageIO.read(new File("Data/Bow_down/r1.png"));
        crouch_r1 = ImageIO.read(new File("Data/Bow_down/r2.png"));
        crouch_r2 = ImageIO.read(new File("data/Bow_down/r3.png"));
        crouch_r3 = ImageIO.read(new File("data/Bow_down/r4.png"));

        crouch_l0 = ImageIO.read(new File("Data/Bow_down/l1.png"));
        crouch_l1 = ImageIO.read(new File("Data/Bow_down/l2.png"));
        crouch_l2 = ImageIO.read(new File("Data/Bow_down/l3.png"));
        crouch_l3 = ImageIO.read(new File("Data/Bow_down/l4.png"));

        start = ImageIO.read(new File("Data/intro1.png"));

        deathimg = ImageIO.read(new File("Data/deathimg.png"));
        win = ImageIO.read(new File("Data/win.png"));

        el = ImageIO.read(new File("Data/Dragon2.png"));
        e2l = ImageIO.read(new File("Data/Dragon.png"));

        er = ImageIO.read(new File("Data/Dragon2l.png"));
        e2r = ImageIO.read(new File("Data/Dragonl.png"));

        Rhanim_l = new Animation(false);
        Rhanim_l.addFrame(el, 500);
        Rhanim_l.addFrame(e2l, 500);

        Rhanim_r = new Animation(false);
        Rhanim_r.addFrame(er, 500);
        Rhanim_r.addFrame(e2r, 500);

        crouchdown_r = new Animation(true);
        crouchdown_r.addFrame(crouch_r0, 20);
        crouchdown_r.addFrame(crouch_r1, 20);
        crouchdown_r.addFrame(crouch_r2, 20);
        crouchdown_r.addFrame(crouch_r3, 20);

        crouchdown_l = new Animation(true);
        crouchdown_l.addFrame(crouch_l0, 20);
        crouchdown_l.addFrame(crouch_l1, 20);
        crouchdown_l.addFrame(crouch_l2, 20);
        crouchdown_l.addFrame(crouch_l3, 20);

        Rajuanim_r = new Animation(false);
        Rajuanim_r.addFrame(A0, 50);
        Rajuanim_r.addFrame(A1, 50);
        Rajuanim_r.addFrame(A2, 50);
        Rajuanim_r.addFrame(A3, 50);
        Rajuanim_r.addFrame(A4, 50);
        Rajuanim_r.addFrame(A5, 50);
        Rajuanim_r.addFrame(A6, 50);
        Rajuanim_r.addFrame(A7, 50);
        Rajuanim_r.addFrame(A8, 50);
        Rajuanim_r.addFrame(A9, 50);
        Rajuanim_r.addFrame(A10, 50);
        Rajuanim_r.addFrame(A11, 50);

        Rajuanim_l = new Animation(false);
        Rajuanim_l.addFrame(X0, 50);
        Rajuanim_l.addFrame(X1, 50);
        Rajuanim_l.addFrame(X2, 50);
        Rajuanim_l.addFrame(X3, 50);
        Rajuanim_l.addFrame(X4, 50);
        Rajuanim_l.addFrame(X5, 50);
        Rajuanim_l.addFrame(X6, 50);
        Rajuanim_l.addFrame(X7, 50);
        Rajuanim_l.addFrame(X8, 50);
        Rajuanim_l.addFrame(X9, 50);
        Rajuanim_l.addFrame(X10, 50);
        Rajuanim_l.addFrame(X11, 50);

        Rajuanim = Rajuanim_r;

        currentSprite = A0;
    }

    public void start() throws IOException {

        bg1 = new Animation(0, 0);

        robot = new Robot();

        loadMap("data/map1.txt");

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {

            switch (State) {

                case "game":
                    gameUpdate();
                    break;

            }
            repaint();

            try {
                Thread.sleep(17);
            } catch (Exception E) {

                System.out.println("FindExceptionProblem");
            }

        }
    }

    public void gameUpdate() {

        robot.update();
        Enemy.update();
        updateTiles();

        Rhanim_l.update(50);
        Rhanim_r.update(50);

        ArrayList projectiles = robot.projectiles;
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            if (p.visible == true) {
                p.update();
            } else {
                projectiles.remove(i);
            }
        }

        for (int i = 0; i < Enemy.enemies.size(); i++) {
            if (Enemy.enemies.get(i).isdead) {
                Enemy.enemies.remove(i);
            }
        }

        if (robot.jumped && robot.direction == "right") {
            currentSprite = characterJumped_r;
        } else if (robot.jumped && robot.direction == "left") {
            currentSprite = characterJumped_l;
        } else if ((robot.direction == "right") && (robot.speedX == 0) && robot.ducked == false) {
            currentSprite = A0;
        } else if ((robot.direction == "left") && (robot.speedX == 0) && robot.ducked == false) {
            currentSprite = X0;
        } else {
            if (robot.speedX < 0) {
                Rajuanim = Rajuanim_l;
            } else if (robot.speedX > 0) {
                Rajuanim = Rajuanim_r;
            } else if (robot.ducked && robot.direction == "right") {
                Rajuanim = crouchdown_r;
            } else if (robot.ducked && robot.direction == "left") {
                Rajuanim = crouchdown_l;
            }
            currentSprite = Rajuanim.getImage();
            Rajuanim.update(10);
        }

        if (robot.centerY > 1400) {
            State = "dead";
        }
    }

    @Override
    public void paint(Graphics g) {

        switch (State) {

            case "start":
                g.drawImage(start, 0, 0, this);
                break;

            case "dead":
                g.drawImage(deathimg, 0, 0, this);
                break;
                
            case "win":
                g.drawImage(win,0,0, this);
                break;

            case "game":

                g.drawImage(background1, bg1.bgX, bg1.bgY, this);

                paintTiles(g);
                paintProjectiles(g);//bullet show
                paintEnemies(g);  //enemy show
                g.drawImage(currentSprite, robot.centerX - 61, robot.centerY - 63, this);//61,     63 value increase korle floor theke upore uthe jabe;

                break;

        }
    }

     void updateTiles() {

        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }
    }

     void paintEnemies(Graphics g) {
        for (Enemy i : Enemy.enemies) {
            if (i.centerX > -50 && i.centerX < 1366) {
                if (i.direction == "right") {
                    g.drawImage(Rhanim_r.getImage(), i.centerX - 48, i.centerY - 20, this);//48,20 x okkho o y okkho theke enemy er uccota
                } else if (i.direction == "left") {
                    g.drawImage(Rhanim_l.getImage(), i.centerX - 48, i.centerY - 20, this);//48,20
                }
            }
        }
    }

     void paintProjectiles(Graphics g) {
        projectiles = robot.projectiles;
        for (int i = 0; i < projectiles.size(); i++) {

            Projectile p = (Projectile) projectiles.get(i);

            if (robot.direction == "right") {
                g.drawImage(bulletr, p.x, p.y, this);
            } else if (robot.direction == "left") {
                g.drawImage(bulletl, p.x, p.y, this);
            }
        }
    }

    void paintTiles(Graphics g) {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            g.drawImage(t.tileImage, t.tileX, t.tileY, this);
        }
    }

    void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();

            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
            
        }
        height = lines.size();

        for (int j = 0; j < 20; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    if (ch == '*') {
                       Enemy.enemies.add(new Enemy(i * 40, j * 40 - 80));//ei line ta bad dile enemy thakbe na//40,40,80
                    }
                    else {
                        Tile t = new Tile(i, j, Character.getNumericValue(ch));
                        tilearray.add(t);
                    }
                    
                }
                 
            }
        }
    }

    
    
    
    
    
    @Override
    public void keyPressed(KeyEvent e) {

        if (State == "start") {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                StartingClass.State = "game";
            }
        }

        if (State == "game") {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_ESCAPE:
                    State = "menu";
                    break;

                case KeyEvent.VK_UP:
                    System.out.println("Move up");
                    break;

                case KeyEvent.VK_DOWN:
                    robot.ducked = true;
                    break;

                case KeyEvent.VK_LEFT:
                    robot.moveLeft();
                    break;

                case KeyEvent.VK_RIGHT:
                    robot.moveRight();
                    break;

                case KeyEvent.VK_SPACE:
                    robot.jump();
                    break;

                case KeyEvent.VK_CONTROL:
                    ctrl_press = true;

            }
        }

        if (State == "dead") {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    bullet = 0;
                    StartingClass.restart();
                    StartingClass.State = "game";
                } catch (IOException ex) {

                    System.out.println("FindExceptionProblem");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                break;

            case KeyEvent.VK_DOWN:
                if (State == "game") {
                    crouchdown_r.currentFrame = 0;
                    crouchdown_l.currentFrame = 0;
                    robot.ducked = false;
                }
                break;

            case KeyEvent.VK_LEFT:
                if (State == "game") {
                    robot.stopLeft();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (State == "game") {
                    robot.stopRight();
                }
                break;

            case KeyEvent.VK_SPACE:
                if (State == "game") {
                    robot.movingLeft = false;
                    robot.movingRight = false;
                }
                break;

            case KeyEvent.VK_CONTROL:

                if (State == "game") {
                    if (ctrl_press) {
                        if (bullet <= 30) {
                            robot.shoot();
                        }
                        bullet++;
                    }
                }
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Main Function
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Soldier");
        frame.setBounds(300, 150, 1366, 768);
        frame.add(starter);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        starter.init();
        starter.start();

    }

}
