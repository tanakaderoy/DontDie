package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;


public class DeathState extends State {
    private Texture backGround;
    private Texture deathMessage;

    public DeathState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        backGround = new Texture("Background-1.png");
        deathMessage = new Texture("DeathStateMessage.png");

    }
    @Override
    public void handleInput(){
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,0, 0);
        spriteBatch.draw(deathMessage, cam.position.x - deathMessage.getWidth()/2, 200);
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        backGround.dispose();
    }
}
