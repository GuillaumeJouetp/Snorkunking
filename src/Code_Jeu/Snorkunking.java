package Code_Jeu;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Snorkunking extends BasicGame {

    public static int WIDTH = 700;
    public static int HEIGHT = 700 ;

    private int step; // To situate code to execute

    private Image fond, shark, diver, title, blood, tresor, money, enter, divergauche, pad, flechedroite, flechegauche;

    private Music music1;
    private Sound sound1;

    private int sharkX=-400;
    private double sharkY=120;
    private int diverX=300;
    private int diverY=400;
    private int pieceY1=0;
    private int pieceY2=0;
    private int pieceY3=0;
    private int titleY=-400;
    private int mort=0;
    private int diverpos=0;
    private int p=0;
    private int Pad=1;

    List<Cave> caves = new ArrayList<>();
    List<Diver> divers = new ArrayList<>();

    public Snorkunking(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        initMusicMenu();
        initImageMenu();
        step=1;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        step1(gameContainer); // Game presentation (title)

        step2(gameContainer); // Menu (chose 1 or 2 players)

        step3(gameContainer);

        step4(gameContainer);

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
        title = new Image("res/image/titre.png");
        blood = new Image("res/image/blood.png");
        tresor = new Image("res/image/tresor.png");
        money = new Image("res/image/money.png");
        enter = new Image("res/image/enter.png");
        divergauche =  new Image ("res/image/divergauche.jpg");
        pad = new Image("res/image/pad.png");
        flechedroite =  new Image ("res/image/flechedroite.png");
        flechegauche =  new Image ("res/image/flechegauche.png");
    }

    public void movementshark(){
        sharkX+=1;

        if (p==0){
            sharkY+=0.3;
            if(sharkY>=140){
                p=1;
            }
        }
        if (p==1){
            sharkY+=-0.3;
            if (sharkY<=100){
                p=0;
            }
        }
        if (sharkX > WIDTH + 200) {
            sharkX = -300;
        }
    }
    public void money(){
        pieceY1+=4;
        pieceY2+=2;
        pieceY3+=1;

        if (pieceY1 > HEIGHT +50) {
            pieceY1 = -100;
        }
        if (pieceY2 > HEIGHT +50) {
            pieceY2 = -30;
        }
        if (pieceY3 > HEIGHT +50) {
            pieceY3 = -30;
        }
    }

    //mouvement du diver//
    public void updateDiverStep01(GameContainer gameContainer){
        Input input = gameContainer.getInput();

        if (input.isKeyDown(Input.KEY_DOWN)) {
            diverY += 1;
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            diverY += -1;
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            diverpos=0;
            diverX += 1;
            Pad=0;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            diverpos=1;
            diverX += -1;
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
            movementshark();

            money();

            if (mort == 0) {
                //mouvement du diver//
                updateDiverStep01(gameContainer);

                if (sharkX > diverX && sharkX < diverX + 100 && sharkY > diverY && sharkY < diverY + 50) {
                    sound1.play(1.0f, 0.2f);
                    mort = 1;
                }
            }
            if (input.isKeyDown(Input.KEY_ENTER)) {
                diverX = 345;
                diverY = 125;
                step = 2;
            }
        }
    }
    public void step1draw(Graphics graphics){
        if (step==1) {
            tresor.draw(-100,550,500,300);
            tresor.draw(400,550,500,300);
            money.draw(10,pieceY1-10,30,30);
            money.draw(100,pieceY2-30,30,30);
            money.draw(170,pieceY3-80,30,30);
            money.draw(250,pieceY2-40,30,30);
            money.draw(300,pieceY3-100,30,30);
            money.draw(400,pieceY1-50,30,30);
            money.draw(430,pieceY3-10,30,30);
            money.draw(510,pieceY2-60,30,30);
            money.draw(600,pieceY1-70,30,30);
            money.draw(660,pieceY2-10,30,30);


            if (mort==0) {
                if (diverpos==0) {
                    diver.draw(diverX, diverY, 100, 50);
                }
                if (diverpos==1){
                    divergauche.draw(diverX, diverY, 100, 50);
                }
            }
            if (mort==1){
                blood.draw(diverX, diverY, 100, 50);
            }
            title.draw(75,titleY,550,130);
            shark.draw(sharkX - 150,(int)sharkY - 60,200,100);
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
        if(diverX>465 && diverX<595 && diverY>355 && diverY<405){
            step=4;
        }
        //jouer contre un autre joueur//
        if(diverX>75 && diverX<205 && diverY>355 && diverY<405) {
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
                diver.draw(diverX-50, diverY-25, 100, 50);
            }
            if (diverpos==1){
                divergauche.draw(diverX-50, diverY-25, 100, 50);
            }
            if (Pad==1){
                flechedroite.draw(345,150,150,200);
                flechegauche.draw(195,150,150,200);
                pad.draw(225,450,250,150);
                graphics.drawString("Utilisez les flèches pour bouger le diver",160,610);
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
