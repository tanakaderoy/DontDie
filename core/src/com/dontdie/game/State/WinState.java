package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

////STATE THAT APPEARS AFTER ALL BOSSES ARE DEFEATED
public class WinState extends State {
    private Texture backGround;
    private Texture winMessage;
    private Texture statContainer;
    private Texture menuTexture;
    private GameButton menuBtn;
    private BitmapFont font;

    public WinState(GameStateManager gsm){
        super(gsm);

        backGround = new Texture(DontDie.backGround);
        winMessage = new Texture("winner.png");
        statContainer = new Texture("box.png");
        menuTexture = new Texture("menu.png");
        font = new BitmapFont();
        font.setColor(Color.LIME);

        menuBtn = new GameButton(DontDie.WIDTH - menuTexture.getWidth(), 0, menuTexture,stage);

    }
    @Override
    public void handleInput(){

        ////GO BACK TO MAIN MENU ON TOUCH OF MENU BUTTON
        if (menuBtn.isPressed()){
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
        spriteBatch.draw(winMessage, DontDie.cam.position.x - winMessage.getWidth()/2, 60, winMessage.getWidth(), DontDie.HEIGHT-60 );
        spriteBatch.draw(statContainer, 0,0,DontDie.WIDTH, 60);
        font.draw(spriteBatch, "YOUR TIME ALIVE: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, 40);
        spriteBatch.end();
        menuBtn.draw(stage);
    }



    @Override
    public void dispose(){
        backGround.dispose();
        winMessage.dispose();
    }
}
