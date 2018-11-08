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
import com.dontdie.game.sprites.Projectile;
import com.dontdie.game.sprites.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {
    private MainCharacter mainCharacter;
    private BossCharacter bossCharacter;
    private List<Projectile> projectileList;
    private List<Item> itemList;
    public Texture backGround;
    private float shootDelay =  2.5f;
    private float itemDelay = 2.5f;
    public int coinCount;
    Controller controller;
    Projectile projectile;
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        mainCharacter = new MainCharacter(25, 0);
        bossCharacter = new BossCharacter(DontDie.WIDTH - 90, 0);
        itemList = new ArrayList<Item>();
        //itemList.add(new Item(DontDie.WIDTH, MathUtils.random(0 , DontDie.HEIGHT - 10), "coin.png"));

        projectileList = new ArrayList<Projectile>();
        //projectileList.add( new Projectile((int) bossCharacter.getPosition().x, (int) bossCharacter.getPosition().y));
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        backGround = new Texture("Background-1.png");
        controller = new Controller();
        coinCount = 0;
        font = new BitmapFont();
        font.setColor(Color.LIME);
        projectile = new Projectile(mainCharacter.getPosition().x,mainCharacter.getPosition().y);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mainCharacter.move();
            System.out.println(mainCharacter.getPosition());
        }/*
        if(controller.isUpPressed()) {
            mainCharacter.move();
        }*/
    }


    @Override
    public void update(float dt) {
        handleInput();
        mainCharacter.update(dt);
        bossCharacter.update(mainCharacter);
        // Generate Projectiles
        for( int i = 0; i < projectileList.size(); ) {
            Projectile projectile = projectileList.get(i);
            if( projectile.update(bossCharacter) ) {
                i++;
            }else {
                projectileList.remove(i);
            }
            if(projectile.collides(mainCharacter.getHitBox())){
                gsm.set(new DeathState(gsm));
            }
        }

        shootDelay -= dt;
        if( shootDelay < 0.0f ) {
            projectileList.add(new   Projectile((int) bossCharacter.getPosition().x, (int) bossCharacter.getPosition().y + bossCharacter.getBossCharacter().getHeight()/2));
            shootDelay = 0.7f;
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
                System.out.println("Current coin amount is:  " + String.valueOf(coinCount));
            }
        }
        itemDelay -= dt;
        if (itemDelay < 0.0f){
            itemList.add(new Item(DontDie.WIDTH, MathUtils.random(10 , DontDie.HEIGHT - 10), "coin.png"));
            itemDelay = 2.0f;
        }/*Tanaka
        //shooting
        if(controller.isUpPressed()) {
            projectileList.add(new Projectile(mainCharacter.getPosition().x+80,mainCharacter.getPosition().y+80));
        }
        //update
        for(Projectile projectile : projectileList){
            projectile.update(bossCharacter);

        }*/
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,cam.position.x - (cam.viewportWidth/2), 0);
        spriteBatch.draw(mainCharacter.getMainCharacter(), mainCharacter.getPosition().x, mainCharacter.getPosition().y);
        spriteBatch.draw(bossCharacter.getBossCharacter(), bossCharacter.getPosition().x, bossCharacter.getPosition().y);
        for( Projectile projectile : projectileList) {
            spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
        }
        for (Item item : itemList){
            spriteBatch.draw(item.getItemTexture(), item.getPosition().x, item.getPosition().y);
        }/* TAnaka
        for( Projectile projectile : projectileList) {
            spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
        }*/
        font.draw(spriteBatch, "Coins: " + String.valueOf(coinCount),DontDie.WIDTH/2-20, DontDie.HEIGHT-10);
        spriteBatch.end();
        //controller.draw();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        mainCharacter.dispose();
        for( Projectile projectile : projectileList) {
            mainCharacter.dispose();
        }
        font.dispose();
        System.out.println("Play State dispose");
    }
}
