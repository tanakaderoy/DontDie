package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dontdie.game.DontDie;

public class GameButton {
    boolean upPressed, downPressed, leftPressed, rightPressed;
    OrthographicCamera cam;
    public Texture buttonTexture;
    public Image upImg;

    public GameButton(float x, float y, Texture texture, Stage stage){
       // cam = new OrthographicCamera();

        Gdx.input.setInputProcessor(stage);
        buttonTexture = texture;
        upImg = new Image(texture);
        //upImg.setSize(50,50);
        upImg.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });

        upImg.setPosition(x,y);
        stage.addActor(upImg);

    }
    public void draw(Stage stage){
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }
    public void resize(int width, int height, FitViewport viewport){
        viewport.update(width,height);
    }
}