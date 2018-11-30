package com.dontdie.game.State;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

////CLASS THAT CREATES ALL TOUCHABLE BUTTONS
public class GameButton {
    public boolean pressed=false;
    public Texture buttonTexture;
    public Image buttonImage;

    public GameButton(float x, float y, Texture texture, Stage stage){
        buttonTexture = texture;
        buttonImage = new Image(texture);
        buttonImage.addListener(new InputListener(){

            ////BUTTON IS PRESSED DOWN
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pressed = true;
                return true;
            }

            ////BUTTON IS RELEASED
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pressed = false;
            }
        });

        ////SETS BUTTON TO SPECIFIED POSITION
        buttonImage.setPosition(x,y);
        stage.addActor(buttonImage);

    }
    public void draw(Stage stage){
        stage.draw();
    }

    public boolean isPressed() {return pressed;
    }

    public void unpress() { pressed = false; }
}
