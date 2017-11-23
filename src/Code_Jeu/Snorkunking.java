package Code_Jeu;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Snorkunking extends BasicGame {

    public static int WIDTH = 700;
    public static int HEIGHT = 700 ;

    private int step; // To situate code to execute

    private Image fond, shark, diver, title, blood, goldTreasure, money, enter, leftSideDiver, pad, rightIndication, leftIndication;

    private Music music1;
    private Sound sound1;

    private int titleY=-400;
    private int death=0;
    private int diverpos=0;
    private int Pad=1;

    private  Money fastCoin;
    private  Money regularCoin ;
    private  Money slowCoin;

    private Diver step12Diver;

    private Shark step1Shark;

    List<Cave> caves = new ArrayList<>();
    List<Diver> divers = new ArrayList<>();

    public Snorkunking(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        step=1;
        step1Shark = new Shark(-400,120);
        step12Diver = new Diver(300,400,"Step 1&2 diver");

        fastCoin = new Money(4);
        regularCoin = new Money(2);
        slowCoin = new Money(1);

        initMusicMenu();
        initImageMenu();

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        step1(gameContainer); // Game presentation (title)

        step2(gameContainer); // Menu (chose 1 or 2 players)

        step3(gameContainer); // Game

        step4(gameContainer); // End game and Score pannel

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
        shark = new Image("res/image/shark.png");
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
            if (titleY <= 230) {
                titleY += 4;
            }
            step1Shark.movementshark();

            money();

            if (death == 0) {
                //mouvement du diver//
                updateDiverStep01(gameContainer);

                if (step1Shark.getX() > step12Diver.getX() && step1Shark.getX() < step12Diver.getX() + 100 && step1Shark.getY() > step12Diver.getY() && step1Shark.getY() < step12Diver.getY() + 50) {
                    sound1.play(1.0f, 0.2f);
                    death = 1;
                }
            }
            if (input.isKeyDown(Input.KEY_ENTER)) {
                step12Diver.setX(345);
                step12Diver.setY(125);
                step = 2;
            }
        }
    }
    public void step1draw(Graphics graphics){
        if (step==1) {
            goldTreasure.draw(-100,550,500,300);
            goldTreasure.draw(400,550,500,300);
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
                    diver.draw(step12Diver.getX(), step12Diver.getY(), 100, 50);
                }
                if (diverpos==1){
                    leftSideDiver.draw(step12Diver.getX(), step12Diver.getY(), 100, 50);
                }
            }
            if (death==1){
                blood.draw(step12Diver.getX(), step12Diver.getY(), 100, 50);
            }
            title.draw(75,titleY,550,130);
            shark.draw(step1Shark.getX() - 150,(int)step1Shark.getY() - 60,200,100);
            if (titleY>=230) {
                graphics.setColor(Color.cyan);
                graphics.drawRect(WIDTH / 2 - 110, 475, 220, 42);
                enter.draw(WIDTH / 2 - 93,485,190,30);
            }
        }
    }

    //choisir options de jeu//
    public void diverChoiceGame(){
        //jouer contre l'ordi//
        if(step12Diver.getX()>465 && step12Diver.getX()<595 && step12Diver.getY()>355 && step12Diver.getY()<405){
            step=4;
        }
        //jouer contre un autre joueur//
        if(step12Diver.getX()>75 && step12Diver.getX()<205 && step12Diver.getY()>355 && step12Diver.getY()<405) {
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
            graphics.drawRect(465, 355, 150, 50);
            graphics.drawString("1 Player", 530, 370);
            graphics.drawRect(75, 355, 150, 50);
            graphics.drawString("2 Player", 120, 370);

            if (diverpos==0) {
                diver.draw(step12Diver.getX()-50, step12Diver.getY()-25, 100, 50);
            }
            if (diverpos==1){
                leftSideDiver.draw(step12Diver.getX()-50, step12Diver.getY()-25, 100, 50);
            }
            if (Pad==1){
                rightIndication.draw(345,150,150,200);
                leftIndication.draw(195,150,150,200);
                pad.draw(225,450,250,150);
                graphics.drawString("Use arrow keys to move the diver",200,610);
            }
        }
    }

    public void step3(GameContainer gameContainer){
        Input input = gameContainer.getInput();
        if (step==3) {
            music1.pause();
        }
    }
    public void step3draw(Graphics graphics){
        if (step==3){
            graphics.setColor(Color.green);
            graphics.drawRect(10,150,680,540);
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
            graphics.drawRect(10,150,680,540);
        }
    }
}
