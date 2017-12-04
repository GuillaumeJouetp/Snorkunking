package Code_Jeu;

import com.sun.xml.internal.bind.v2.TODO;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snorkunking extends BasicGame {

    public static int WIDTH = 900;
    public static int HEIGHT = 900;

    private int step; // To situate code to execute

    private Image fond, diver, title, blood, goldTreasure, money, enter, leftSideDiver, pad, rightIndication, leftIndication;

    private Music music1;
    private Sound sound1;

    private int titleY=-4*HEIGHT/7;
    private int death=0;
    private int diverpos=0;
    private int Pad=1;

    private  Money fastCoin;
    private  Money regularCoin ;
    private  Money slowCoin;

    private Diver step12Diver;

    private TextField choiceNamePlayer;

    private Shark step1Shark;

    public static DivingArea myDivingArea = new DivingArea();
    List<Integer> scorePanel = new ArrayList<>();
    List<Diver> divers = new ArrayList<>();
    public static Oxygene myOxygen = new Oxygene();
    List<OxygeneBar> oxygenBars;

    int phase = 1;

    public Snorkunking(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        step=1;
        step1Shark = new Shark(-4*WIDTH/7,HEIGHT/7,WIDTH/3,HEIGHT/7);
        step12Diver = new Diver(WIDTH/2,HEIGHT-2*HEIGHT/5,WIDTH/7,HEIGHT/14,"Step 1&2 diver");

        fastCoin = new Money(4);
        regularCoin = new Money(2);
        slowCoin = new Money(1);

        initMusicMenu();
        initImageMenu();

        divers.add(new Diver(0,0,0,0,"Player 1"));
        divers.add(new Diver(0,0,0,0,"Player 2"));

        oxygenBars = new ArrayList<>();
        fillOxygen();

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        step1(gameContainer); // Game presentation (title)

        step2(gameContainer); // Menu (chose 1 or 2 players)

        step3(gameContainer); // Game with 2 player

        step4(gameContainer); // Game with 1 player

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        fond.draw(0, 0, WIDTH, HEIGHT);

        step1draw(graphics);

        step2draw(graphics);

        step3draw(graphics);

        step4draw(graphics);
/*
        score.disp;
        oxygene.disp();
        caves.disp();
        water.disp();
        divers.disp();
*/
        //Diver myDiver = new Diver("Diver 1");
        //myDiver.dispDiver();

    }

    public void initMusicMenu() throws SlickException {
        music1 = new Music("res/sound/opening.ogg");
        music1.setVolume(0.2f);
        music1.loop();
        sound1 = new Sound("res/sound/cri.wav");
    }

    public void initImageMenu() throws SlickException {
        fond = new Image("res/image/ocean.jpg");

        diver = new Image("res/image/diver.png");
        title = new Image("res/image/title.png");
        blood = new Image("res/image/blood.png");
        goldTreasure = new Image("res/image/goldTreasure.png");
        money = new Image("res/image/money.png");
        enter = new Image("res/image/enter.png");
        leftSideDiver =  new Image ("res/image/leftSideDiver.jpg");
        pad = new Image("res/image/pad.png");
        rightIndication =  new Image ("res/image/rightIndication.png");
        leftIndication =  new Image ("res/image/leftIndication.png");
    }


    public void money(){
        fastCoin.setY(fastCoin.getY()+fastCoin.getSpeed());
        regularCoin.setY(regularCoin.getY()+regularCoin.getSpeed());
        slowCoin.setY(slowCoin.getY()+slowCoin.getSpeed());


        if (fastCoin.getY() > HEIGHT +50) {
            fastCoin.setY(-100);
        }
        if (regularCoin.getY() > HEIGHT +50) {
            regularCoin.setY(-30);
        }
        if (slowCoin.getY() > HEIGHT +50) {
            slowCoin.setY(-30);
        }
    }

    //mouvement du diver//
    public void updateDiverStep01(GameContainer gameContainer){
        Input input = gameContainer.getInput();

        if (input.isKeyDown(Input.KEY_DOWN)) {
            step12Diver.setY(step12Diver.getY()+1);
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            step12Diver.setY(step12Diver.getY()-1);
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            diverpos=0;
            step12Diver.setX(step12Diver.getX()+1);
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            diverpos=1;
            step12Diver.setX(step12Diver.getX()-1);
            Pad=0;
        }
    }

    public void step1(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        if(step==1) {
            //fais descendre le title//
            if (titleY <= 23*HEIGHT/70) {
                titleY += 4;
            }
            step1Shark.movementshark();

            money();

            if (death == 0) {
                //mouvement du diver//
                updateDiverStep01(gameContainer);

                if (step1Shark.getX() > step12Diver.getX()-step12Diver.getX()/4 && step1Shark.getX() < step12Diver.getX()+step12Diver.getX()/4  && step1Shark.getY() > step12Diver.getY()-step12Diver.getY()/4 && step1Shark.getY() < step12Diver.getY()+step12Diver.getY()/4) {
                    sound1.play(1.0f, 0.3f);
                    death = 1;
                }
            }
            if (input.isKeyDown(Input.KEY_ENTER)) {
                step12Diver.setX(345*WIDTH/700);
                step12Diver.setY(125*HEIGHT/700);
                step = 2;
                Pad=1;
            }
        }
    }
    public void step1draw(Graphics graphics) throws SlickException {
        if (step==1) {
            goldTreasure.draw(-2*WIDTH/7,55*HEIGHT/70,5*WIDTH/7,3*HEIGHT/7);
            goldTreasure.draw(3*WIDTH/7,55*HEIGHT/70,5*WIDTH/7,3*HEIGHT/7);
            money.draw(10,fastCoin.getY()-10,30,30);
            money.draw(100,regularCoin.getY()-30,30,30);
            money.draw(170,slowCoin.getY()-80,30,30);
            money.draw(250,regularCoin.getY()-40,30,30);
            money.draw(300,slowCoin.getY()-100,30,30);
            money.draw(400,fastCoin.getY()-50,30,30);
            money.draw(430,slowCoin.getY()-10,30,30);
            money.draw(510,regularCoin.getY()-60,30,30);
            money.draw(600,fastCoin.getY()-70,30,30);
            money.draw(660,regularCoin.getY()-10,30,30);


            if (death==0) {
                if (diverpos==0) {
                    diver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());

                }
                if (diverpos==1) {
                    leftSideDiver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
                }
            }
            if (death==1){
                blood.draw(step12Diver.getX()-step12Diver.getWidth()/2, step12Diver.getY()-step12Diver.getHeight()/2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            title.draw(3*WIDTH/20,titleY,7*WIDTH/10,HEIGHT/5);
            //shark.draw(step1Shark.getX() - 150,(int)step1Shark.getY() - 60,200,100);
            step1Shark.drawShark();

            if (titleY>=23*HEIGHT/70) {
                graphics.setColor(Color.cyan);
                graphics.drawRoundRect(WIDTH / 2 - 110*WIDTH/700, 475*HEIGHT/700, 22*WIDTH/70, 42*HEIGHT/700,20);
                enter.draw(WIDTH / 2 - 93*WIDTH/700,485*HEIGHT/700,19*WIDTH/70,3*HEIGHT/70);

            }

        }
    }

    //choisir options de jeu//
    public void diverChoiceGame(){
        //jouer contre l'ordi//
        if(step12Diver.getX()>465*WIDTH/700 && step12Diver.getX()<595*WIDTH/700 && step12Diver.getY()>355*HEIGHT/700 && step12Diver.getY()<405*HEIGHT/700){
            step=3;
        }
        //jouer contre un autre joueur//
        if(step12Diver.getX()>75*WIDTH/700 && step12Diver.getX()<205*WIDTH/700 && step12Diver.getY()>355*HEIGHT/700 && step12Diver.getY()<405*HEIGHT/700) {
            step=3;
        }
    }

    public void step2(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        if (step==2){

            updateDiverStep01(gameContainer);

            diverChoiceGame();
        }
    }
    public void step2draw(Graphics graphics){
        if (step==2){
            graphics.setColor(Color.white);
            graphics.drawRect(465*WIDTH/700, 355*HEIGHT/700, 15*WIDTH/70, 5*HEIGHT/70);
            graphics.drawString("2 Player", 500*WIDTH/700, 37*HEIGHT/70);
            graphics.drawRect(75*WIDTH/700, 355*HEIGHT/700, 15*WIDTH/70, 5*HEIGHT/70);
            graphics.drawString("1 Player", 110*WIDTH/700, 37*HEIGHT/70);

            if (diverpos==0) {
                diver.draw(step12Diver.getX()-step12Diver.getWidth()/2, step12Diver.getY()-step12Diver.getHeight()/2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            if (diverpos==1){
                leftSideDiver.draw(step12Diver.getX()-step12Diver.getWidth()/2, step12Diver.getY()-step12Diver.getHeight()/2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            if (Pad==1){
                rightIndication.draw(345*WIDTH/700,15*HEIGHT/70,15*WIDTH/70,2*HEIGHT/7);
                leftIndication.draw(195*WIDTH/700,15*HEIGHT/70,15*WIDTH/70,2*HEIGHT/7);
                pad.draw(225*WIDTH/700,45*HEIGHT/70,25*WIDTH/70,15*HEIGHT/70);
                graphics.drawString("Use arrow keys to move the diver",2*WIDTH/7,61*HEIGHT/70);
            }
        }
    }

    public void step3(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        if (step==3) {
            music1.pause();

            if(phase == 1){
                checkOxygeneLevel();

            }
            else if(phase == 2){
                checkOxygeneLevel();

            }
            else if (phase == 3){
                checkOxygeneLevel();
            }
            else{
                endGame();

            }
        }
    }

    public void step3draw(Graphics graphics){
        if (step==3){
            //myDivingArea.drawDivingArea(graphics);
            graphics.setColor(Color.blue);
            graphics.drawRect(2*WIDTH/100,2*WIDTH/100,96*WIDTH/100,14*HEIGHT/100);
            myDivingArea.drawLevels(graphics);
        }
    }
    public void step4(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        if (step==4) {
            music1.pause();
        }
    }
    public void step4draw(Graphics graphics){
        if (step==4){
            graphics.setColor(Color.orange);
            graphics.drawRect(WIDTH/70,15*HEIGHT/70,68*WIDTH/70,54*HEIGHT/70);

        }
    }

    public void checkOxygeneLevel(){
        if (oxygenBars.size()<=0){
            phase++;
            myOxygen = new Oxygene();
            divers.get(0).setScore(divers.get(0).getScore() + divers.get(0).getNbTreasures());
            divers.get(1).setScore(divers.get(1).getScore() + divers.get(1).getNbTreasures());

        }

    }

    public void fillOxygen(){
        for (int i = 0; i <myOxygen.getOxygeneLevel() ; i++) {
            oxygenBars.add(new OxygeneBar(i,7));
        }
    }

    /*
    public void action(GameContainer gc){
        Input input = gc.getInput();
        //List<OxygeneBar> toRemove = new ArrayList<>();

        if (input.isKeyPressed(Input.KEY_DOWN)) { // diver going down
            y += Level.HEIGHT;
            for (int i = 0; i < diverChests.size() + 1; i++) { // diver's weight + movement
                Snorkunking.myOxygen.oxygenBars.remove(Snorkunking.myOxygen.oxygenBars.size() - 1);
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) { // diver going up
            y-=Level.HEIGHT;
            for (int i = 0; i < diverChests.size() + 1; i++) { // diver's weight + movement
                Snorkunking.myOxygen.oxygenBars.remove(Snorkunking.myOxygen.oxygenBars.size() - 1);
            }
        }
        //for (Chest elt :  ) {
        if (input.isKeyPressed(Input.KEY_SPACE)) { // diver catching a chest (no care of weight)
            Snorkunking.myOxygen.oxygenBars.remove(Snorkunking.myOxygen.oxygenBars.size() - 1);
            //diverChests.add(elt);
        }
        //}
    }
    */

    public String whosTurnIsIt(){
        if (divers.get(0).getY()>divers.get(1).getY()){
            return ("Player one's turn !");
        }
        else
            return ("Players two's turn !");
    }

    public void endGame(){


    }

}
