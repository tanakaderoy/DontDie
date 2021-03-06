package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

////STATE THAT COMES UP WHEN MAIN CHARACTER DIES
public class DeathState extends State {
    private Texture backGround;
    private Texture deathMessage;
    private Texture statContainer;
    private Texture retryTexture;
    private Texture menuTexture;
    private GameButton retryBtn;
    private GameButton menuBtn;
    private BitmapFont font;

    public DeathState(GameStateManager gsm){
        super(gsm);

        backGround = new Texture(DontDie.backGround);
        deathMessage = new Texture("DeathStateMessage.png");
        statContainer = new Texture("box.png");
        retryTexture = new Texture("retry.png");
        menuTexture = new Texture("menu.png");
        font = new BitmapFont();
        font.setColor(Color.LIME);

        retryBtn = new GameButton(100, 100, retryTexture,stage);
        menuBtn = new GameButton(500, 100, menuTexture,stage);

    }
    @Override
    public void handleInput(){

////RESTART GAME ON PRESS OF RETRY BUTTON
        if (retryBtn.isPressed()){
            gsm.pop();
            gsm.set(new PlayState(gsm));
        }

////GO BACK TO MAIN MENU ON PRESS OF MENU BUTTON
        else if (menuBtn.isPressed()){
            gsm.pop();
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

////DRAW IMAGES
    @Override
    public void render(SpriteBatch spriteBatch){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(DontDie.cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,0, 0);
        spriteBatch.draw(deathMessage, DontDie.cam.position.x - deathMessage.getWidth()/2, 200);
        spriteBatch.draw(statContainer, 0,0,DontDie.WIDTH, 60);
        font.draw(spriteBatch, "YOUR TIME ALIVE: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, 20);
        font.draw(spriteBatch, "BEST TIME ALIVE: " + String.format("%,.2f",DontDie.bestTime) + " seconds", 300, 45);
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
