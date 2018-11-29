package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.MainCharacter;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.sprites.BossCharacterProjectile;
import com.dontdie.game.sprites.Item;
import com.dontdie.game.sprites.MainCharacterProjectile;

import java.util.ArrayList;
import java.util.List;



public class PlayState extends State {
    private MainCharacter mainCharacter;
    private BossCharacter bossCharacter1;
    private BossCharacter bossCharacter2;
    private BossCharacter bossCharacter3;
    private BossCharacter bossCharacter4;
    private BossCharacter bossCharacter5;
    private BossCharacter bossCharacter6;

    private List<BossCharacterProjectile> BossProjectileList;
    private List<MainCharacterProjectile> MainProjectileList;
    private List<BossCharacter> BossList;
    private List<Item> itemList;
    public Texture backGround;
    private Texture statContainer;
    private Texture healthBar;
    private Texture healthText;
    private Texture shootTexture;
    private Texture jumpTexture;
    private Texture pauseTexture;
    public float timeAlive;
    private float aliveSecs = 0.01f;
    private float bossShootDelay =  2.8f;
    private float mainShootDelay = 0f;
    private float coinDelay = 2.5f;
    private float ammoDelay = 2.5f;
    private float jumpDelay = 0f;
    public int coinCount;
    public int ammoCount;
    private float curBossHealth;
    private float fullBossHealth;
    private GameButton shootButton;
    private GameButton jumpButton;
    public static GameButton pauseButton;
    BossCharacterProjectile bossProjectile;
    MainCharacterProjectile mainProjectile;
    private BitmapFont font;
    private boolean hasShield = false;
    private int shieldCount;


    public PlayState(GameStateManager gsm) {
        super(gsm);

        itemList = new ArrayList<Item>();

        BossProjectileList = new ArrayList<BossCharacterProjectile>();

        MainProjectileList = new ArrayList<MainCharacterProjectile>();
        BossList = new ArrayList<BossCharacter>();
        backGround = new Texture(DontDie.backGround);
        statContainer = new Texture("box.png");
        healthBar = new Texture("healthbar.png");
        healthText = new Texture("bosshealth.png");
        shootTexture = new Texture("shootButton.png");
        jumpTexture = new Texture("jump.png");
        pauseTexture = new Texture("pause.png");

        coinCount = 0;
        ammoCount = 0;
        timeAlive = 0;
        shieldCount = 0;

        font = new BitmapFont();
        font.setColor(Color.LIME);


        mainCharacter = new MainCharacter(jumpTexture.getWidth()+30, 0);
        bossCharacter1 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved.png", 100);
        bossCharacter2 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved4.png", 120);
        bossCharacter3 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved3.png", 140);
        bossCharacter4 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved5.png", 160);
        bossCharacter5 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved1.png", 180);
        bossCharacter6 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth() - 60, DontDie.HEIGHT+100, "FlameDemon Evolved2.png", 200);

        BossList.add(bossCharacter1);
        BossList.add(bossCharacter2);
        BossList.add(bossCharacter3);
        BossList.add(bossCharacter4);
        BossList.add(bossCharacter5);
        BossList.add(bossCharacter6);


        bossProjectile = new BossCharacterProjectile(mainCharacter.getPosition().x,mainCharacter.getPosition().y);
        mainProjectile = new MainCharacterProjectile(mainCharacter.getPosition().x, mainCharacter.getPosition().y);

        //BossHealth

        if (BossList.size() > 0) {
            fullBossHealth = BossList.get(0).fullHealth;
            curBossHealth = fullBossHealth;
        }


        jumpButton = new GameButton(0,0,jumpTexture,stage);
        shootButton = new GameButton(DontDie.WIDTH - shootTexture.getWidth(), 0, shootTexture, stage);
        pauseButton = new GameButton(DontDie.WIDTH - pauseTexture.getWidth()-8, DontDie.HEIGHT - 35, pauseTexture, stage);

    }

    @Override
    protected void handleInput() {


    }


    @Override
    public void update(float dt) {
        handleInput();




        //MAIN CHARACTER JUMP
        jumpDelay -= dt;
        if(pauseButton.isPressed() ){

            gsm.push(new PauseState(gsm));
            System.out.println("PauseState pushed");
            pauseButton.unpress();
            return;

        }
        if(jumpButton.isPressed() && jumpDelay < 0.0f) {
            mainCharacter.move();
            jumpDelay = 0.1f;
            jumpButton.unpress();
        }
        mainCharacter.update(dt);
        if (BossList.size() > 0) {
            BossList.get(0).update(mainCharacter);
        }

        // Generate Projectiles
        if (BossList.size() > 0) {
            for (int i = 0; i < BossProjectileList.size(); ) {
                BossCharacterProjectile projectile = BossProjectileList.get(i);
                if (projectile.update(BossList.get(0))) {
                    i++;
                } else {
                    BossProjectileList.remove(i);
                }
                if (projectile.collides(mainCharacter.getHitBox())) {
                    if (hasShield){
                        BossProjectileList.remove(projectile);
                        shieldCount -= 1;

                    }else if (!hasShield){
                        gsm.push(new DeathState(gsm));
                    }
                }
            }
        }
        ////////MAIN CHARACTER SHOOTING
        for( int i = 0; i < MainProjectileList.size(); ) {
            MainCharacterProjectile projectile = MainProjectileList.get(i);
            if( projectile.update(mainCharacter) ) {
                i++;
            }else {
                MainProjectileList.remove(i);
            }
            if (BossList.size() > 0) {
                if (projectile.collides(BossList.get(0).getHitBox())) {
                    curBossHealth -= 10;
                    MainProjectileList.remove(projectile);
                    if (curBossHealth == 0) {
                        BossList.remove(0);
                        bossShootDelay = 2.8f;
                        if (BossList.size() > 0) {
                            fullBossHealth = BossList.get(0).fullHealth;
                            curBossHealth = BossList.get(0).fullHealth;
                        }
                    }
                }
            }
        }
        mainShootDelay -= dt;
        if(shootButton.isPressed() && ammoCount > 0 && mainShootDelay < 0.0f){
            MainProjectileList.add(new MainCharacterProjectile((int) mainCharacter.getPosition().x, (int) mainCharacter.getPosition().y + mainCharacter.getMainCharacter().getHeight() / 2));
            ammoCount -= 1;
            mainShootDelay = 1.0f;
        }

        if (BossList.size() > 0) {
            bossShootDelay -= dt;
            if (bossShootDelay < 0.0f) {
                BossProjectileList.add(new BossCharacterProjectile((int) BossList.get(0).getPosition().x, (int) BossList.get(0).getPosition().y + BossList.get(0).getBossCharacter().getHeight() / 2));
                bossShootDelay = 40.7f;
            }
        }
        // Generate Items
        for(int i = 0; i < itemList.size(); ){
            Item item = itemList.get(i);
            if (item.update()){
                i++;
            }else{
                itemList.remove(i);
            }
            if (item.textureName == "coin.png" && item.collides(mainCharacter.getHitBox())){
                itemList.remove(item);
                coinCount += 1;
                if (coinCount % 50 == 0){
                    shieldCount += 1;
                    hasShield = true;
                }
            }
            if (item.textureName == "ammo.png" && item.collides(mainCharacter.getHitBox())){
                itemList.remove(item);
                ammoCount += 10;

            }
        }



        //COINS
        coinDelay -= dt;
        if (coinDelay < 0.0f){
            itemList.add(new Item(DontDie.WIDTH, MathUtils.random(10 , DontDie.HEIGHT - 10-40), "coin.png"));
            coinDelay = 2.0f;
        }
        //AMMO
        ammoDelay -= dt;
        if (ammoDelay <0.0f){
            itemList.add(new Item(DontDie.WIDTH, MathUtils.random(10, DontDie.HEIGHT  - 10-40), "ammo.png"));
            ammoDelay = 10.0f;
        }


        //TIME ALIVE
        aliveSecs -= dt;
        if (aliveSecs < 0.0f){
            timeAlive += 0.01;
            DontDie.setYourTime(timeAlive);
            if (DontDie.yourTime >= DontDie.bestTime){
                DontDie.setBestTime(timeAlive);
            }
            aliveSecs = 0.01f;
        }

        if (shieldCount == 0){
            hasShield = false;
        }
        if (hasShield){
            mainCharacter.setMainCharacterTexture("playerShield.png");
        }else{
            mainCharacter.setMainCharacterTexture("player.png");
        }


        ///////WIN STATE PUSHED WHEN ALL BOSS CHARACTERS ARE DEFEATED!
        /*if (BossList.isEmpty()){
            gsm.set(new WinState(gsm));
        }*/




        System.out.println(hasShield);
        System.out.println(mainCharacter.textureName);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(DontDie.cam.combined);
        spriteBatch.begin();

        spriteBatch.draw(backGround,DontDie.cam.position.x - (DontDie.cam.viewportWidth/2), 0);
        spriteBatch.draw(statContainer, DontDie.cam.position.x - statContainer.getWidth()/2,0,statContainer.getWidth(), 20);
        spriteBatch.draw(healthBar, DontDie.cam.position.x - healthBar.getWidth()/2,0,healthBar.getWidth() * (curBossHealth/fullBossHealth), 20);
        spriteBatch.draw(healthText, DontDie.cam.position.x - healthText.getWidth()/2+25,-16,healthText.getWidth()-50, healthText.getHeight()-2);

        spriteBatch.draw(statContainer, 0 ,DontDie.HEIGHT-40, DontDie.WIDTH,40);
        spriteBatch.draw(mainCharacter.getMainCharacter(), mainCharacter.getPosition().x, mainCharacter.getPosition().y);
        if (BossList.size() > 0) {
            spriteBatch.draw(BossList.get(0).getBossCharacter(), BossList.get(0).getPosition().x, BossList.get(0).getPosition().y);
        }
        if (BossList.size() > 0) {
            for (BossCharacterProjectile projectile : BossProjectileList) {
                spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
            }
        }
        for (MainCharacterProjectile projectile : MainProjectileList){
            spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
        }
        for (Item item : itemList){
            spriteBatch.draw(item.getItemTexture(), item.getPosition().x, item.getPosition().y);
        }
        font.draw(spriteBatch, "Coins: " + String.valueOf(coinCount),10, DontDie.HEIGHT-20);
        font.draw(spriteBatch, "Ammo: " + String.valueOf(ammoCount), 10, DontDie.HEIGHT-7);
        font.draw(spriteBatch, "Time Alive: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, DontDie.HEIGHT-20);
        font.draw(spriteBatch, "Shield Count: " + String.valueOf(shieldCount), 100, DontDie.HEIGHT-7);
        /*if (BossList.size() > 0) {
            font.draw(spriteBatch, "Boss Health: " + String.valueOf(curBossHealth), 300, DontDie.HEIGHT - 20);
        }*/

        spriteBatch.end();
        shootButton.draw(stage);
        jumpButton.draw(stage);
        pauseButton.draw(stage);

    }

    @Override
    public void dispose() {
        backGround.dispose();
        mainCharacter.dispose();
        for( BossCharacterProjectile projectile : BossProjectileList) {
            projectile.dispose();
        }
        font.dispose();
        System.out.println("Play State dispose");
    }
}
