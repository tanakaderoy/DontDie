package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
    private List<BossCharacterProjectile> BossProjectileList;
    private List<MainCharacterProjectile> MainProjectileList;
    private List<BossCharacter> BossList;
    private List<Item> itemList;
    public Texture backGround;
    private Texture statContainer;
    private Texture shootTexture;
    private Texture jumpTexture;
    private Texture pauseTexture;
    public float timeAlive;
    private float aliveSecs = 0.01f;
    private float bossShootDelay =  2.5f;
    private float mainShootDelay = 0f;
    private float coinDelay = 2.5f;
    private float ammoDelay = 2.5f;
    private float jumpDelay = 0f;
    public int coinCount;
    public int ammoCount;
    private float bossHealth = 100;
    private GameButton shootButton;
    private GameButton jumpButton;
    private GameButton pauseButton;
    BossCharacterProjectile bossProjectile;
    MainCharacterProjectile mainProjectile;
    private BitmapFont font;
    private FitViewport viewport;
    private Stage stage;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);

        itemList = new ArrayList<Item>();
        //itemList.add(new Item(DontDie.WIDTH, MathUtils.random(0 , DontDie.HEIGHT - 10), "coin.png"));

        BossProjectileList = new ArrayList<BossCharacterProjectile>();
        //BossProjectileList.add( new Projectile((int) bossCharacter.getPosition().x, (int) bossCharacter.getPosition().y));

        MainProjectileList = new ArrayList<MainCharacterProjectile>();
        BossList = new ArrayList<BossCharacter>();
        backGround = new Texture(DontDie.backGround);
        statContainer = new Texture("box.png");
        shootTexture = new Texture("shootButton.png");
        jumpTexture = new Texture("jump.png");
        pauseTexture = new Texture("retry.png");

        coinCount = 0;
        ammoCount = 0;
        timeAlive = 0;

        font = new BitmapFont();
        font.setColor(Color.LIME);

        mainCharacter = new MainCharacter(jumpTexture.getWidth()+30, 0, "player.png");
        bossCharacter1 = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth()-60, 0, "FlameDemon Evolved.png");
        BossList.add(bossCharacter1);
        bossProjectile = new BossCharacterProjectile(mainCharacter.getPosition().x,mainCharacter.getPosition().y);
        mainProjectile = new MainCharacterProjectile(mainCharacter.getPosition().x, mainCharacter.getPosition().y);

        viewport = new FitViewport(800,480, cam);
        stage = new Stage(viewport, DontDie.batch);
        jumpButton = new GameButton(0,0,jumpTexture,stage);
        shootButton = new GameButton(DontDie.WIDTH - shootTexture.getWidth(), 0, shootTexture, stage);
        pauseButton = new GameButton(DontDie.WIDTH - pauseTexture.getWidth(), DontDie.HEIGHT - 100, pauseTexture, stage);

    }

    @Override
    protected void handleInput() {





    }


    @Override
    public void update(float dt) {
        handleInput();
        //MAIN CHARACTER JUMP
        jumpDelay -= dt;
        if(pauseButton.isUpPressed()){
            gsm.set(new PauseState(gsm));
            }
        if(jumpButton.isUpPressed() && jumpDelay < 0.0f) {
            mainCharacter.move();
            jumpDelay = 0.1f;
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
                    gsm.push(new DeathState(gsm));
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
                    bossHealth -= 10;
                    MainProjectileList.remove(projectile);
                    if (bossHealth == 0) {
                        BossList.remove(0);
                        //BossList.get(0).dispose();
                    }
                }
            }
        }
        mainShootDelay -= dt;
        if(shootButton.isUpPressed() && ammoCount > 0 && mainShootDelay < 0.0f){
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

        /*Tanaka
        //shooting
        if(controller.isUpPressed()) {
            BossProjectileList.add(new Projectile(mainCharacter.getPosition().x+80,mainCharacter.getPosition().y+80));
        }
        //update
        for(Projectile projectile : BossProjectileList){
            projectile.update(bossCharacter);

        }*/
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
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,cam.position.x - (cam.viewportWidth/2), 0);
        spriteBatch.draw(statContainer, cam.position.x - statContainer.getWidth()/2,0,statContainer.getWidth(), 20);
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
        } /*TAnaka
        for( Projectile projectile : BossProjectileList) {
            spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
        }*/
        font.draw(spriteBatch, "Coins: " + String.valueOf(coinCount),10, DontDie.HEIGHT-20);
        font.draw(spriteBatch, "Ammo: " + String.valueOf(ammoCount), 10, DontDie.HEIGHT-7);
        font.draw(spriteBatch, "Time Alive: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, DontDie.HEIGHT-10);
        if (BossList.size() > 0) {
            font.draw(spriteBatch, "Boss Health: " + String.valueOf(bossHealth), 300, DontDie.HEIGHT - 20);
        }

        spriteBatch.end();
        shootButton.draw(stage);
        jumpButton.draw(stage);

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
