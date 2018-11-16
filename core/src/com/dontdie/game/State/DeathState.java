package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;


public class DeathState extends State {
    private Texture backGround;
    private Texture deathMessage;
    private Texture statContainer;
    private Texture retryTexture;
    private Texture menuTexture;
    private GameButton retryBtn;
    private GameButton menuBtn;
    private Stage stage;
    private FitViewport viewport;
    private BitmapFont font;

    public DeathState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        backGround = new Texture(DontDie.backGround);
        deathMessage = new Texture("DeathStateMessage.png");
        statContainer = new Texture("box.png");
        retryTexture = new Texture("retry.png");
        menuTexture = new Texture("menu.png");
        font = new BitmapFont();
        font.setColor(Color.LIME);
        viewport = new FitViewport(800,480, cam);
        stage = new Stage(viewport, DontDie.batch);
        retryBtn = new GameButton(100, 100, retryTexture,stage);
        menuBtn = new GameButton(500, 100, menuTexture,stage);

    }
    @Override
    public void handleInput(){
        /*if(Gdx.input.justTouched()){
            gsm.pop();
            gsm.set(new PlayState(gsm));
        }*/
        if (retryBtn.isUpPressed()){
            gsm.pop();
            gsm.set(new PlayState(gsm));
        }else if (menuBtn.isUpPressed()){
            gsm.pop();
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,0, 0);
        spriteBatch.draw(deathMessage, cam.position.x - deathMessage.getWidth()/2, 200);
        spriteBatch.draw(statContainer, 0,0,DontDie.WIDTH, 60);
        font.draw(spriteBatch, "YOUR TIME ALIVE: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, 20);
        font.draw(spriteBatch, "BEST TIME ALIVE: " + String.format("%,.2f",DontDie.bestTime) + " seconds", 300, 45);

        //font.draw(spriteBatch, "Coins: " + String.valueOf(coinCount),100, 20);
       // font.draw(spriteBatch, "Ammo: " + String.valueOf(ammoCount), 10, 20);
        spriteBatch.end();
        retryBtn.draw(stage);
        menuBtn.draw(stage);
    }



    @Override
    public void dispose(){
        backGround.dispose();
        deathMessage.dispose();
    }
}
