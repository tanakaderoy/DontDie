package com.dontdie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

import javax.sound.midi.Soundbank;

public class MainCharacter {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture mainCharacter;
    private Rectangle hitBox;
    private Sound click;

    public Vector3 getPosition() {
        return position;
    }


    public Texture getMainCharacter() {
        return mainCharacter;
    }

    public MainCharacter(int x, int y){


        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        mainCharacter = new Texture("../android/assets/player.png");
        hitBox = new Rectangle(x,y,mainCharacter.getWidth(),mainCharacter.getHeight());
        click = Gdx.audio.newSound(Gdx.files.internal("button-3.mp3"));
    }

    public void update(float dt){
        if(position.y >0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(0,velocity.y,0);

        if(position.y<0){
            position.y=0;
        }
        if (position.y > DontDie.HEIGHT - 32){
            position.y = DontDie.HEIGHT - 32;
        }

        velocity.scl(1/dt);
        hitBox.setPosition(position.x,position.y);
    }

    public void move(){
        velocity.y = 350;
        click.play();
    }

    public Rectangle getHitBox() {  // sends the dimentions of the maincharacters hitbox
        return hitBox;
    }

    public void dispose(){
        mainCharacter.dispose();
        click.dispose();
    }
}
