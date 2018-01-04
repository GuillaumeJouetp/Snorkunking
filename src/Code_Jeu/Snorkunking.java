package Code_Jeu;

import com.sun.xml.internal.bind.v2.TODO;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;

import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snorkunking extends BasicGame {
    public static int WIDTH = 900;
    public static int HEIGHT = 900;
    private int step; // To situate code to execute
    private Image fond, diver, title, blood, goldTreasure, money, enter, leftSideDiver, pad, rightIndication, leftIndication;
    private Music music1;
    private Music music2;
    private Sound sound1;

    private int titleY = -4 * HEIGHT / 7;
    private int death = 0;
    private int diverpos = 0;
    private int Pad = 1;
    private Money fastCoin;
    private Money regularCoin;
    private Money slowCoin;
    private Diver step12Diver;
    private TextField choiceNamePlayer;
    private Shark step1Shark;
    public static DivingArea myDivingArea = new DivingArea();
    List<Integer> scorePanel = new ArrayList<>();
    List<Diver> divers = new ArrayList<>();
    public static Oxygene myOxygen = new Oxygene();


    public Snorkunking(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        step = 1;
        step1Shark = new Shark(-4 * WIDTH / 7, HEIGHT / 7, WIDTH / 3, HEIGHT / 7);
        step12Diver = new Diver(WIDTH / 2, HEIGHT - 2 * HEIGHT / 5, WIDTH / 7, HEIGHT / 14, "Step 1&2 diver");
        fastCoin = new Money(4);
        regularCoin = new Money(2);
        slowCoin = new Money(1);
        initMusicMenu();
        initImageMenu();
        divers.add(new Diver(DivingArea.x+DivingArea.WIDTH/3, DivingArea.y, 10*Level.WIDTH/100, Level.HEIGHT, "Player 1"));
        divers.add(new Diver(DivingArea.x+2*DivingArea.WIDTH/3, DivingArea.y, 10*Level.WIDTH/100, Level.HEIGHT, "Player 2"));

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        step1(gameContainer); // Game presentation (title)
        step2(gameContainer); // Menu (chose 1 or 2 players)
        step34(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        fond.draw(0, 0, WIDTH, HEIGHT);
        step1draw(graphics);
        step2draw(graphics);
        instructions(graphics, gameContainer);
        step34draw(graphics);
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
        leftSideDiver = new Image("res/image/leftSideDiver.jpg");
        pad = new Image("res/image/pad.png");
        rightIndication = new Image("res/image/rightIndication.png");
        leftIndication = new Image("res/image/leftIndication.png");
    }

    public void money() {
        fastCoin.setY(fastCoin.getY() + fastCoin.getSpeed());
        regularCoin.setY(regularCoin.getY() + regularCoin.getSpeed());
        slowCoin.setY(slowCoin.getY() + slowCoin.getSpeed());
        if (fastCoin.getY() > HEIGHT + 50) {
            fastCoin.setY(-100);
        }
        if (regularCoin.getY() > HEIGHT + 50) {
            regularCoin.setY(-30);
        }
        if (slowCoin.getY() > HEIGHT + 50) {
            slowCoin.setY(-30);
        }
    }

    //mouvement du diver//
    public void updateDiverStep01(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_DOWN)) {
            step12Diver.setY(step12Diver.getY() + 1);
            Pad = 0;
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            step12Diver.setY(step12Diver.getY() - 1);
            Pad = 0;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            diverpos = 0;
            step12Diver.setX(step12Diver.getX() + 1);
            Pad = 0;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            diverpos = 1;
            step12Diver.setX(step12Diver.getX() - 1);
            Pad = 0;
        }
    }

    public void step1(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (step == 1) {
            //fais descendre le title//
            if (titleY <= 23 * HEIGHT / 70) {
                titleY += 4;
            }
            step1Shark.movementshark();
            money();
            if (death == 0) {
                //mouvement du diver//
                updateDiverStep01(gameContainer);
                if (step1Shark.getX() > step12Diver.getX() - step12Diver.getX() / 4 && step1Shark.getX() < step12Diver.getX() + step12Diver.getX() / 4 && step1Shark.getY() > step12Diver.getY() - step12Diver.getY() / 4 && step1Shark.getY() < step12Diver.getY() + step12Diver.getY() / 4) {
                    sound1.play(1.0f, 0.3f);
                    death = 1;
                }
            }
            if (input.isKeyDown(Input.KEY_ENTER)) {
                step12Diver.setX(345 * WIDTH / 700);
                step12Diver.setY(325 * HEIGHT / 700);
                step = 2;
                Pad = 1;
            }
        }
    }

    public void step1draw(Graphics graphics) throws SlickException {
        if (step == 1) {
            goldTreasure.draw(-2 * WIDTH / 7, 55 * HEIGHT / 70, 5 * WIDTH / 7, 3 * HEIGHT / 7);
            goldTreasure.draw(3 * WIDTH / 7, 55 * HEIGHT / 70, 5 * WIDTH / 7, 3 * HEIGHT / 7);
            money.draw(WIDTH / 70, fastCoin.getY() - 10, 30, 30);
            money.draw(WIDTH / 7, regularCoin.getY() - 100, 30, 30);
            money.draw(17 * WIDTH / 70, slowCoin.getY() - 80, 30, 30);
            money.draw(25 * WIDTH / 70, regularCoin.getY() - 40, 30, 30);
            money.draw(3 * WIDTH / 7, slowCoin.getY() - 100, 30, 30);
            money.draw(4 * WIDTH / 7, fastCoin.getY() - 50, 30, 30);
            money.draw(43 * WIDTH / 70, slowCoin.getY() - 10, 30, 30);
            money.draw(51 * WIDTH / 70, regularCoin.getY() - 60, 30, 30);
            money.draw(6 * WIDTH / 7, fastCoin.getY() - 70, 30, 30);
            money.draw(66 * WIDTH / 70, regularCoin.getY() - 10, 30, 30);
            if (death == 0) {
                if (diverpos == 0) {
                    diver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
                }
                if (diverpos == 1) {
                    leftSideDiver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
                }
            }
            if (death == 1) {
                blood.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            title.draw(3 * WIDTH / 20, titleY, 7 * WIDTH / 10, HEIGHT / 5);
            //shark.draw(step1Shark.getX() - 150,(int)step1Shark.getY() - 60,200,100);
            step1Shark.drawShark();
            if (titleY >= 23 * HEIGHT / 70) {
                graphics.setColor(Color.cyan);
                graphics.drawRoundRect(WIDTH / 2 - 110 * WIDTH / 700, 475 * HEIGHT / 700, 22 * WIDTH / 70, 42 * HEIGHT / 700, 20);
                enter.draw(WIDTH / 2 - 93 * WIDTH / 700, 485 * HEIGHT / 700, 19 * WIDTH / 70, 3 * HEIGHT / 70);
            }
        }
    }

    //choisir options de jeu//
    public void diverChoiceGame() {
        //jouer contre l'ordi//
        if (step12Diver.getX() > 465 * WIDTH / 700 && step12Diver.getX() < 605 * WIDTH / 700 && step12Diver.getY() > 455 * HEIGHT / 700 && step12Diver.getY() < 505 * HEIGHT / 700) {
            try {
                Thread.sleep(500);   }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();  }
            step = 4;
        }
        //jouer contre un autre joueur//
        if (step12Diver.getX() > 75 * WIDTH / 700 && step12Diver.getX() < 215 * WIDTH / 700 && step12Diver.getY() > 455 * HEIGHT / 700 && step12Diver.getY() < 505 * HEIGHT / 700) {
            try {
                Thread.sleep(500);   }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();  }
            step = 3;
        }
        //retourne au menu principal
        if (step12Diver.getX() > 75 * WIDTH / 700 && step12Diver.getX() < 215 * WIDTH / 700 && step12Diver.getY() > 155 * HEIGHT / 700 && step12Diver.getY() < 205 * HEIGHT / 700) {
            step12Diver.setX(WIDTH / 2);
            step12Diver.setY(HEIGHT - 2 * HEIGHT / 5);
            step = 1;
        }
        //lance les instructions
        if (step12Diver.getX() > 465 * WIDTH / 700 && step12Diver.getX() < 605 * WIDTH / 700 && step12Diver.getY() > 155 * HEIGHT / 700 && step12Diver.getY() < 205 * HEIGHT / 700) {
            step = 10;
        }
    }

    public void step2(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (step == 2) {
            updateDiverStep01(gameContainer);
            diverChoiceGame();
        }
    }

    public void step2draw(Graphics graphics) {
        if (step == 2) {
            graphics.setColor(Color.white);
            graphics.drawRect(465 * WIDTH / 700, 455 * HEIGHT / 700, 15 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("1 Player", 500 * WIDTH / 700, 47 * HEIGHT / 70);
            graphics.drawRect(75 * WIDTH / 700, 455 * HEIGHT / 700, 15 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("2 Player", 110 * WIDTH / 700, 47 * HEIGHT / 70);
            graphics.drawRect(465 * WIDTH / 700, 155 * HEIGHT / 700, 15 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("Instructions", 490 * WIDTH / 700, 17 * HEIGHT / 70);
            graphics.drawRect(75 * WIDTH / 700, 155 * HEIGHT / 700, 15 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("Back to menu", 100 * WIDTH / 700, 17 * HEIGHT / 70);
            if (diverpos == 0) {
                diver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            if (diverpos == 1) {
                leftSideDiver.draw(step12Diver.getX() - step12Diver.getWidth() / 2, step12Diver.getY() - step12Diver.getHeight() / 2, step12Diver.getWidth(), step12Diver.getHeight());
            }
            if (Pad == 1) {
                rightIndication.draw(345 * WIDTH / 700, 35 * HEIGHT / 70, 15 * WIDTH / 70, HEIGHT / 7);
                leftIndication.draw(195 * WIDTH / 700, 35 * HEIGHT / 70, 15 * WIDTH / 70, HEIGHT / 7);
                pad.draw(225 * WIDTH / 700, 50 * HEIGHT / 70, 25 * WIDTH / 70, 15 * HEIGHT / 70);
                graphics.drawString("Use arrow keys to move the diver", 2 * WIDTH / 7, 66 * HEIGHT / 70);
            }
        }
    }

    public void instructions(Graphics graphics, GameContainer gameContainer) throws SlickException {
        Input input = gameContainer.getInput();
        if (step == 10) {
            graphics.drawRect(275 * WIDTH / 700, 585 * HEIGHT / 700, 18 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("Press enter to back", 280 * WIDTH / 700, 60 * HEIGHT / 70);
        }
        if (input.isKeyDown(Input.KEY_ENTER)) {
            step12Diver.setX(345 * WIDTH / 700);
            step12Diver.setY(325 * HEIGHT / 700);
            Pad = 1;
            step = 2;
        }
    }

    int turn =0;
    int phase = 1;

    public void step34draw(Graphics graphics) throws SlickException {
        if (step == 3 || step ==4 && phase <=3) {
            myOxygen.drawBottle(graphics);
            myDivingArea.drawLevels(graphics);
            myDivingArea.drawChests();
            for (int i = 0; i <divers.size() ; i++) {
                divers.get(i).drawDiver();
            }
            drawInfos(graphics);

        }
        if(phase >3){
            drawEndGame();
        }
    }

    public void step34(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (step ==3 || step == 4) {
            music1.pause();
            if (phase == 1) {
                checkOxygeneLevel();
                action(gameContainer);
                checkColision();
            } else if (phase == 2) {
                checkOxygeneLevel();
                action(gameContainer);
                checkColision();
            } else if (phase == 3) {
                checkOxygeneLevel();
                action(gameContainer);
                checkColision();
            } else {
                endGame();
            }
        }
    }


    public void checkOxygeneLevel() {
        if (myOxygen.getValue() <= 0) {
            phase++;
            myOxygen = new Oxygene();
            for (int i = 0; i <divers.size() ; i++) {
                divers.get(i).setScore(divers.get(i).getScore() + divers.get(i).getNbTreasures()); // calcule du score
                divers.get(i).setY(DivingArea.y - Level.HEIGHT); // replacement des joueurs
            }
             removeLevels();
            //for(Diver elt : divers){
                //elt.setDiverChests(new ArrayList<>());
            //}
        }
    }



    public void removeLevels(){
        List<Level> toRemove = new ArrayList<>();
        int nbLevelsToRemove = 0;
        for (int j = 0; j < myDivingArea.caves.size(); j++) {
            for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) {
                if (myDivingArea.caves.get(j).getLevels().get(i).getChests().size()==0) {
                toRemove.add(myDivingArea.caves.get(j).getLevels().get(i));
                nbLevelsToRemove ++;
                myDivingArea.caves.get(j).setNbLevels(myDivingArea.caves.get(j).getNbLevels() - nbLevelsToRemove );
                }
            }
            myDivingArea.caves.get(j).getLevels().removeAll(toRemove); // enleve à la liste tous les éléments qu'on en commun la liste toRemove et ma liste
        }
    }

    int iaStop = myOxygen.getValue();
    int choice=0;
    public void action(GameContainer gc) {
        Input input = gc.getInput();
        if (step==3){
            playerAction(gc);
        }
        if (step==4)
        {
            if (turn==0) {
                playerAction(gc);
            }
            if (turn==1){
                iaAction(gc);
            }
        }
    }

    public void playerAction(GameContainer gc){
        Input input = gc.getInput();
        Diver currentPlayer = divers.get(turn);
        if (input.isKeyPressed(Input.KEY_DOWN)) { // diver going down
            currentPlayer.setY(currentPlayer.getY() + Level.HEIGHT);
            for (int i = 0; i < currentPlayer.getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }
            if (turn == 0)
                turn ++;
            else turn--;
        }
        if (input.isKeyPressed(Input.KEY_UP)) { // diver going down
            currentPlayer.setY(currentPlayer.getY() - Level.HEIGHT);
            for (int i = 0; i < currentPlayer.getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }
            if (turn == 0)
                turn ++;
            else turn--;
        }
        if (input.isKeyPressed(Input.KEY_SPACE)) { // diver catching a chest (no care of weight)
            for (int j = 0; j < myDivingArea.caves.size(); j++) {
                for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) {
                    for (int k = 0; k < myDivingArea.caves.get(j).getLevels().get(i).getChests().size(); k++) {
                        if (currentPlayer.getY() == myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k).y) {
                            currentPlayer.diverChests.add(myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k));
                            currentPlayer.setNbTreasures(currentPlayer.getNbTreasures() + myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k).getValue()); // update le score partiel que le joueur detient dans ses coffres
                            myDivingArea.caves.get(j).getLevels().get(i).getChests().remove(k);
                            myOxygen.setValue(myOxygen.getValue() - 1);
                        }
                    }
                }
            }
            if (turn == 0)
                turn++;
            else turn--;
        }
    }

    public void iaAction(GameContainer gc){
        if (myOxygen.getValue() >= iaStop / 2 ){
            if(choice==1) {
                for (int j = 0; j < myDivingArea.caves.size(); j++) {
                    for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) {
                        for (int k = 0; k < myDivingArea.caves.get(j).getLevels().get(i).getChests().size(); k++) {
                            if (divers.get(1).getY() == myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k).y) {
                                divers.get(1).diverChests.add(myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k));
                                divers.get(1).setNbTreasures(divers.get(1).getNbTreasures() + myDivingArea.caves.get(j).getLevels().get(i).getChests().get(k).getValue()); // update le score partiel que le joueur detient dans ses coffres
                                myDivingArea.caves.get(j).getLevels().get(i).getChests().remove(k);
                                myOxygen.setValue(myOxygen.getValue() - 1);

                            }
                        }
                    }
                }
                choice=0;
            }
            else{
                divers.get(1).setY(divers.get(1).getY() + Level.HEIGHT);
                for (int i = 0; i < divers.get(1).getDiverChests().size() + 1; i++) { // diver's weight + movement
                    myOxygen.setValue(myOxygen.getValue() - 1);
                }
                choice=1;
            }
        }
        else {
            divers.get(1).setY(divers.get(1).getY() - Level.HEIGHT);
            for (int i = 0; i < divers.get(1).getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }

        }

        turn--;
    }

    public void checkColision(){
        for (Diver elt : divers){
            if (elt.getY() < DivingArea.y){
                elt.setY(DivingArea.y - Level.HEIGHT);
            }
        }
    }

    public void drawInfos(Graphics graphics)throws SlickException {

        //ScorePanel.drawScorePanel();
        graphics.drawRect(ScorePanel.x, ScorePanel.y, ScorePanel.WIDTH, ScorePanel.HEIGHT);
        graphics.setColor(Color.red);
        graphics.drawString("Manche ",ScorePanel.x + 10*ScorePanel.WIDTH/100,ScorePanel.y + 10*ScorePanel.HEIGHT/100);
        graphics.drawString(Integer.toString(phase)+ " sur 3",ScorePanel.x + 10*ScorePanel.WIDTH/100,ScorePanel.y + 30*ScorePanel.HEIGHT/100);
        graphics.drawString(divers.get(0).getName(),ScorePanel.x + 45*ScorePanel.WIDTH/100,ScorePanel.y + 10*ScorePanel.HEIGHT/100);
        graphics.drawString("Poid : " + Integer.toString(divers.get(0).diverChests.size()) ,ScorePanel.x + 45*ScorePanel.WIDTH/100,ScorePanel.y + 30*ScorePanel.HEIGHT/100);
        graphics.drawString("Score : " + Integer.toString(divers.get(0).getScore()) ,ScorePanel.x + 45*ScorePanel.WIDTH/100,ScorePanel.y + 50*ScorePanel.HEIGHT/100);
        graphics.drawString(divers.get(1).getName(),ScorePanel.x + 75*ScorePanel.WIDTH/100,ScorePanel.y + 10*ScorePanel.HEIGHT/100);
        graphics.drawString("Poid : " + Integer.toString(divers.get(1).diverChests.size()),ScorePanel.x + 75*ScorePanel.WIDTH/100,ScorePanel.y + 30*ScorePanel.HEIGHT/100);
        graphics.drawString("Score : " + Integer.toString(divers.get(1).getScore()) ,ScorePanel.x + 75*ScorePanel.WIDTH/100,ScorePanel.y + 50*ScorePanel.HEIGHT/100);
        myOxygen.drawOxygen(graphics);
    }


    public void endGame(){

    }

    public void drawEndGame()throws SlickException{
        Image shark = new Image("res/image/LOL.png");
        shark.draw(0,0,WIDTH,HEIGHT);
    }

}
