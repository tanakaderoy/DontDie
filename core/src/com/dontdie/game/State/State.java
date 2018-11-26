package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dontdie.game.DontDie;

public abstract class State {


    protected GameStateManager gsm;
    protected Stage stage;


    protected State(GameStateManager gsm){
        this.gsm = gsm;
        this.stage = stage;
        stage = new Stage(DontDie.viewport, DontDie.getBatch());


    }


    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    public void setInput() {
        Gdx.input.setInputProcessor(stage);
    }
}
