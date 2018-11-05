package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

public class DeathState extends State {
    private Texture backGround;

    public DeathState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        backGround = new Texture("Background-1.png");

    }
    @Override
    public void handleInput(){
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
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
        spriteBatch.draw(backGround,cam.position.x - (cam.viewportWidth/2), 0);
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        backGround.dispose();
    }
}
