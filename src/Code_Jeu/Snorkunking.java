package Code_Jeu;

import com.sun.xml.internal.ws.api.pipe.SyncStartForAsyncFeature;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Font;

public class Snorkunking extends BasicGame {

    public static int WIDTH = 900;
    public static int HEIGHT = 900;

    private boolean replay;

    private Image fond, diver, title, blood, goldTreasure, money, enter, leftSideDiver, pad, rightIndication, leftIndication;
    private Music music1;
    private Music music2;
    private Sound sound1;

    private int titleY;
    private int death;
    private int diverpos;
    private int Pad;

    private Money fastCoin;
    private Money regularCoin;
    private Money slowCoin;

    private Diver step12Diver;
    private Shark step1Shark;

    public static DivingArea myDivingArea = new DivingArea();
    List<Diver> divers = new ArrayList<>();
    public static Oxygene myOxygen = new Oxygene();

    private int step; // To situate code to execute
    private int turn ; // To situate who's player is playing
    private int phase ; // To situate the phase


    public Snorkunking(String title) {
        super(title);
    }

    /*---------------------------------------------------------- GAME ---------------------------------------------------------------------------*/
    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        replay = false;
        titleY = -4 * HEIGHT / 7;
        death = 0;
        diverpos = 0;
        Pad = 1;
        step1Shark = new Shark(-4 * WIDTH / 7, HEIGHT / 7, WIDTH / 3, HEIGHT / 7);
        step12Diver = new Diver(WIDTH / 2, HEIGHT - 2 * HEIGHT / 5, WIDTH / 7, HEIGHT / 14, "Step 1&2 diver");
        fastCoin = new Money(4);
        regularCoin = new Money(2);
        slowCoin = new Money(1);
        initMusicMenu();
        initImageMenu();



        divers.add(new Diver(DivingArea.x+DivingArea.WIDTH/3, DivingArea.y - Level.HEIGHT, 10*Level.WIDTH/100, Level.HEIGHT, "Player 1"));
        divers.add(new Diver(DivingArea.x+2*DivingArea.WIDTH/3, DivingArea.y - Level.HEIGHT, 10*Level.WIDTH/100, Level.HEIGHT, "Player 2"));

        step = 1;
        turn = new Random().nextInt(2); // au début les joueurs sont au meme niveau donc celui qui commence est choisi aléatoirement
        phase = 1;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        intro(gameContainer); // Game presentation (title)
        menu(gameContainer); // Menu (chose 1 or 2 players)
        game(gameContainer);  // Game
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        fond.draw(0, 0, WIDTH, HEIGHT);
        drawIntro(graphics); // Drawing of the game presentation (title)
        drawMenu(graphics); // Drawing of the Menu (chose 1 or 2 players)
        instructions(graphics, gameContainer); // Drawing of the instructions
        drawGame(graphics); // Drawing of the game


    }
    /*---------------------------------------------------------- GAME/ ---------------------------------------------------------------------------*/

    /*---------------------------------------------------------- DEMANDE PAR LE CAHIER DES CHARGES ---------------------------------------------------------------------------*/

    public void menu(GameContainer gameContainer) {
        if (step == 2) {
            updateDiverMenu(gameContainer);
            choseMenu();
        }
    }

    public void choseMenu() { //choisir options de jeu
/*
        Image instructionsBuble = new Image("res/image/instructionsBuble.png");
        instructionsBuble.draw(435 * WIDTH / 700, 435 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
        Image backToTitleBuble = new Image("res/image/backToTitleBuble.png");
        backToTitleBuble.draw(55 * WIDTH / 700, 435 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
        Image versusIABuble = new Image("res/image/versusIABuble.png");
        versusIABuble.draw(435 * WIDTH / 700, 125 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
        Image versusPlayerBuble = new Image("res/image/versusPlayerBuble.png");
        versusPlayerBuble.draw(45 * WIDTH / 700, 125 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
        */
        //lance les instructions
        if (step12Diver.getX() > 435 * WIDTH / 700 && step12Diver.getX() < 635 * WIDTH / 700 && step12Diver.getY() > 435 * HEIGHT / 700 && step12Diver.getY() < 535 * HEIGHT / 700) {
            /*
            try {
                Thread.sleep(500);
            }catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            */
            step = 10;

        }
        //retourne a l'écran titre
        if (step12Diver.getX() > 55 * WIDTH / 700 && step12Diver.getX() < 245 * WIDTH / 700 && step12Diver.getY() > 495 * HEIGHT / 700 && step12Diver.getY() < 535 * HEIGHT / 700) {
            /*
            try {
                Thread.sleep(500);
            }catch(InterruptedException ex) {
                Thread.currentThread().interrupt();  }
            */
            step = 1;
        }
        //jouer contre un autre joueur
        if (step12Diver.getX() > 45 * WIDTH / 700 && step12Diver.getX() < 245 * WIDTH / 700 && step12Diver.getY() > 155 * HEIGHT / 730 && step12Diver.getY() < 235 * HEIGHT / 700) {
            step12Diver.setX(WIDTH / 2);
            step12Diver.setY(HEIGHT - 2 * HEIGHT / 5);
            step = 3;
        }
        //jouer contre l'IA
        if (step12Diver.getX() > 445 * WIDTH / 700 && step12Diver.getX() < 635 * WIDTH / 700 && step12Diver.getY() > 185 * HEIGHT / 700 && step12Diver.getY() < 235 * HEIGHT / 700) {
            step = 4;
        }
    }

    public void game (GameContainer gameContainer) {
        if (step ==3 || step == 4 || step == 5) { // the game begin
            music1.pause();
            if (phase == 1 || phase == 2 || phase == 3) {
                action(gameContainer);
                checkSurface();
                checkOxygeneLevel();
            } else {
                endGame(gameContainer);
            }
        }
    }

    public void checkOxygeneLevel() {
        if (myOxygen.getValue() <= 0) {
            phase++;
            myOxygen = new Oxygene();
            for (int i = 0; i <divers.size() ; i++) {
                chestsFall(i); // Les coffres des plongeurs qui ne sont pas remontés à la surfaces tombent au dernier niveau
                divers.get(i).setY(DivingArea.y - Level.HEIGHT); // replacement des joueurs à la surface
            }
            removeLevels();
        }
    }

    public void chestsFall(int i){
        Diver currentDiver = divers.get(i);
        int lastCave = myDivingArea.caves.size() -1;
        int lastLevel = myDivingArea.caves.get(lastCave).getLevels().size() -1;
        for (Chest elt : currentDiver.diverChests){
            myDivingArea.caves.get(lastCave).getLevels().get(lastLevel).getChests().add(elt); // Les coffres non remontés se retrouvent au fond de la derniere cave
        }
        currentDiver.setDiverChests(new ArrayList<>()); // Les plongeurs perdent leur coffre
        currentDiver.setNbTreasures(0); // ainsi que leur trésors

    }


    public void action(GameContainer gc) {
        if (step==3){
            playerAction(gc);
        }
        if (step==4)
        {
            if (turn==0) {
                playerAction(gc);
            }
            if (turn==1){
                iaAction();
            }
        }
    }

    public boolean checkBottomColision(Diver currentDiver){
        boolean isBottom = false;
        int lastCave = myDivingArea.caves.size() -1;
        int lastLevel = myDivingArea.caves.get(lastCave).getLevels().size() -1;
        int fond = myDivingArea.caves.get(lastCave).getLevels().get(lastLevel).y;

        if(currentDiver.getY() == fond){
            isBottom = true;
        }

        return isBottom;
    }

    public void checkSurface() {

        int surface = DivingArea.y - Level.HEIGHT;
        for (Diver elt : divers) {
            if (elt.getY() == surface) {
                elt.setScore(elt.getScore() + elt.getNbTreasures()); // le plongeur depose les coffres a la surface et son score augmente
                elt.setNbTreasures(0); // il n'a donc plus de tresors sur lui
                elt.setDiverChests(new ArrayList<>()); // et plus de coffres (reset son poid à 0 car poid = DiverChest.size)
            }
        }
    }

    public boolean isAtTheSurface (Diver currentDiver){
        int surface = DivingArea.y - Level.HEIGHT;
        boolean isSurface = false;
        if (currentDiver.getY() == surface){
            isSurface = true;
        }
        return isSurface;

    }

    public void playerAction(GameContainer gc){
        Input input = gc.getInput();
        Diver currentPlayer = divers.get(turn);
        if (input.isKeyPressed(Input.KEY_DOWN) && !checkBottomColision(currentPlayer)) { // diver going down only if he is not already at the bottom
            currentPlayer.setY(currentPlayer.getY() + Level.HEIGHT);
            for (int i = 0; i < currentPlayer.getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }
            setTurn();
        }
        if (input.isKeyPressed(Input.KEY_UP) && !isAtTheSurface(currentPlayer) ) { // diver going up only if he is not already at the surface
            currentPlayer.setY(currentPlayer.getY() - Level.HEIGHT);
            for (int i = 0; i < currentPlayer.getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }
            setTurn();
        }
        if (input.isKeyPressed(Input.KEY_SPACE)) { // diver catching a chest (no care of weight)
            for (int j = 0; j < myDivingArea.caves.size(); j++) {
                for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) {
                    if( myDivingArea.caves.get(j).getLevels().get(i).getChests().size() > 0 ){
                        if (currentPlayer.getY() == myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0).y ) {
                            currentPlayer.diverChests.add(myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0));
                            currentPlayer.setNbTreasures(currentPlayer.getNbTreasures() + myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0).getValue()); // update le score partiel que le joueur detient dans ses coffres
                            myDivingArea.caves.get(j).getLevels().get(i).getChests().remove(0);
                            myOxygen.setValue(myOxygen.getValue() - 1);
                        }
                    }
                }
            }
            setTurn();
        }
    }

    public void setTurn(){
        if (turn == 0)
            turn ++;
        else turn--;
    }

    public void drawTurn()throws SlickException{
        Image arrow = new Image("res/image/LOL.png");
        if (turn == 0){
            arrow.draw(ScorePanel.x + 35*ScorePanel.WIDTH/100,ScorePanel.y + 10*ScorePanel.HEIGHT/100,WIDTH/10,HEIGHT/20);
        }
        if (turn == 1){
            arrow.draw(ScorePanel.x + 66*ScorePanel.WIDTH/100,ScorePanel.y + 10*ScorePanel.HEIGHT/100,WIDTH/10,HEIGHT/20);
        }

    }

    int iaStop = 2*myOxygen.getValue()/3;
    int choice=0;
    int a=1;

    public void iaAction(){
        if (myOxygen.getValue() >= iaStop ){
            if (choice==0) {
                for (int j = 0; j < myDivingArea.caves.size(); j++) {
                    for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) {
                        if (myDivingArea.caves.get(j).getLevels().get(i).getChests().size() > 0) {
                            if (divers.get(1).getY() == myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0).y) {
                                divers.get(1).diverChests.add(myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0));
                                divers.get(1).setNbTreasures(divers.get(1).getNbTreasures() + myDivingArea.caves.get(j).getLevels().get(i).getChests().get(0).getValue()); // update le score partiel que le joueur detient dans ses coffres
                                myDivingArea.caves.get(j).getLevels().get(i).getChests().remove(0);
                                myOxygen.setValue(myOxygen.getValue() - 1);
                                a=0;
                            }
                        }
                    }
                }
                if (a==1){
                    choice=1;
                }
                a=1;
            }
            if (choice==1) {
                divers.get(1).setY(divers.get(1).getY() + Level.HEIGHT);
                for (int i = 0; i < divers.get(1).getDiverChests().size() + 1; i++) { // diver's weight + movement
                    myOxygen.setValue(myOxygen.getValue() - 1);
                }
            }
            choice=0;

        }

        else if(!isAtTheSurface(divers.get(1))) {
            divers.get(1).setY(divers.get(1).getY() - Level.HEIGHT);
            for (int i = 0; i < divers.get(1).getDiverChests().size() + 1; i++) { // diver's weight + movement
                myOxygen.setValue(myOxygen.getValue() - 1);
            }

        }
        setTurn();

    }


    public void removeLevels(){
        List<Level> toRemove = new ArrayList<>();
        int nbLevelsToRemove = 0;
        for (int j = 0; j < myDivingArea.caves.size(); j++) { // parcours des 3 caves

            for (int i = 0; i < myDivingArea.caves.get(j).getNbLevels(); i++) { // parcours des niveaux de la cave j
                if (myDivingArea.caves.get(j).getLevels().get(i).getChests().size()==0) {
                    toRemove.add(myDivingArea.caves.get(j).getLevels().get(i));
                    nbLevelsToRemove ++;
                }
            }
            int newNbLevel = myDivingArea.caves.get(j).getNbLevels() - nbLevelsToRemove;
            myDivingArea.caves.get(j).setNbLevels(newNbLevel );
            System.out.println("nouveau nombre de niveaux cave " + j + " : " + newNbLevel );
            System.out.println("---------");

            for(Level elt : toRemove){
                System.out.println("y des toRemove : " + elt.y);
            }
            myDivingArea.caves.get(j).getLevels().removeAll(toRemove);// enleve à la liste tous les éléments qu'on en commun la liste toRemove et ma liste
            for(Level elt : myDivingArea.caves.get(j).getLevels()){
                System.out.println("y des niveaux restants : " + "cave " + " " + j +" : "+ elt.y);
            }
            System.out.println("taille de la liste de niveau restant cave "+ j +" : " + myDivingArea.caves.get(j).getLevels().size());
            System.out.println("---------");

            toRemove = new ArrayList<>();
            nbLevelsToRemove = 0;
        }
    }


    public void drawMenu(Graphics graphics) throws SlickException {
        if (step == 2) {
            Image instructionsBuble = new Image("res/image/instructionsBuble.png");
            instructionsBuble.draw(435 * WIDTH / 700, 435 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
            Image backToTitleBuble = new Image("res/image/backToTitleBuble.png");
            backToTitleBuble.draw(55 * WIDTH / 700, 435 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
            Image versusIABuble = new Image("res/image/versusIABuble.png");
            versusIABuble.draw(435 * WIDTH / 700, 125 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);
            Image versusPlayerBuble = new Image("res/image/versusPlayerBuble.png");
            versusPlayerBuble.draw(45 * WIDTH / 700, 125 * HEIGHT / 700, 20 * WIDTH / 70, 10 * HEIGHT / 70);

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
                graphics.drawString("Use arrow keys to move the diver",  33* WIDTH / 100, 66 * HEIGHT / 70);
            }
        }
    }


    public void drawGame (Graphics graphics) throws SlickException {
        if (step == 3 || step ==4 && phase <=3) {
            myOxygen.drawBottle(graphics);
            myDivingArea.drawLevels(graphics);
            myDivingArea.drawChests();
            drawTurn();
            for (int i = 0; i <divers.size() ; i++) {
                divers.get(i).drawDiver();
            }
            drawInfos(graphics);

        }
        if(phase >3){
            step = 5;
            drawEndGame(graphics);
        }
    }

    public void drawInfos(Graphics graphics)throws SlickException {

        //ScorePanel.drawScorePanel();
        //Fonts.setFont1(graphics);
        graphics.setColor(new Color(223, 109, 20)); // Orange
        graphics.drawRect(ScorePanel.x, ScorePanel.y, ScorePanel.WIDTH, ScorePanel.HEIGHT);
        graphics.setColor(Color.black);
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

    /*---------------------------------------------------------- DEMANDE PAR LE CAHIER DES CHARGES/ ---------------------------------------------------------------------------*/

    /*---------------------------------------------------------- LES PLUS ---------------------------------------------------------------------------*/

    public void intro(GameContainer gameContainer) {
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
                updateDiverMenu(gameContainer);
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

    public void drawIntro(Graphics graphics) throws SlickException {
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

    //mouvement du diver
    public void updateDiverMenu(GameContainer gameContainer) {
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

    public void instructions(Graphics graphics, GameContainer gameContainer) throws SlickException {
        Input input = gameContainer.getInput();

        if (step == 10) {
            Image instructions = new Image("res/image/Instructions.png");
            instructions.draw(0,0,WIDTH,HEIGHT);
            graphics.setColor(Color.white);
            graphics.drawRect(75* WIDTH / 100, 93 * HEIGHT / 100, 18 * WIDTH / 70, 5 * HEIGHT / 70);
            graphics.drawString("Entrée pour revenir",  78* WIDTH / 100, 96 * HEIGHT / 100);
        }
        if (input.isKeyDown(Input.KEY_ENTER)) {
            step12Diver.setX(345 * WIDTH / 700);
            step12Diver.setY(325 * HEIGHT / 700);
            Pad = 1;
            step = 2;
        }
    }

    public void endGame(GameContainer gc){
        if (gc.getInput().isKeyDown(Input.KEY_ENTER)){
            System.out.println("TEST");

            // replay = true;
        }

    }

    public void drawEndGame(Graphics graphics)throws SlickException{
        Font myFont = new Font("verdana",Font.BOLD,20);
        TrueTypeFont myTypo = new TrueTypeFont(myFont,true);
        Image winner;
        graphics.setColor(Color.black);

        if (divers.get(0).getScore() == divers.get(1).getScore()){ //egalité
            winner = new Image("res/image/egalite.png");
            String finalScores = Integer.toString(divers.get(0).getScore()) + " - " + Integer.toString(divers.get(1).getScore());
            winner.draw(-5*WIDTH/100,0,WIDTH + 10*WIDTH/100,HEIGHT);
            myTypo.drawString(48*WIDTH/100,35*HEIGHT/100,finalScores,Color.black);
        }
        else if (divers.get(0).getScore() > divers.get(1).getScore()){ // le joueur 1 a gagné
           winner = new Image("res/image/player1won.png");
           String finalScores = Integer.toString(divers.get(0).getScore()) + " - " + Integer.toString(divers.get(1).getScore());
           winner.draw(-5*WIDTH/100,0,WIDTH + 10*WIDTH/100,HEIGHT);
           myTypo.drawString(48*WIDTH/100,35*HEIGHT/100,finalScores,Color.black);
        }
        else { // le joueur 2 a gagné
            winner = new Image("res/image/player2won.png");
            String finalScores = Integer.toString(divers.get(1).getScore()) + " - " + Integer.toString(divers.get(0).getScore());
            winner.draw(-5*WIDTH/100,0,WIDTH + 10*WIDTH/100,HEIGHT);
            myTypo.drawString(48*WIDTH/100,35*HEIGHT/100,finalScores,Color.black);
        }

        graphics.setColor(Color.red);
        graphics.drawString("Appuyez sur entrée pour rejouer !",3*WIDTH/100,95*HEIGHT/100);


    }

    /*---------------------------------------------------------- LES PLUS/ ---------------------------------------------------------------------------*/

}
