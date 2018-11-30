package com.dontdie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;


public class MainCharacter {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture mainCharacter;
    private Texture mainCharacterShield;
    private Rectangle hitBox;
    public Sound click;
    public String textureName = "player.png";

////RETURNS MAIN CHARACTER'S POSITION
    public Vector3 getPosition() {
        return position;
    }

////SETS MAIN CHARACTER'S TEXTURE
    public void setMainCharacterTexture(String setTexture){
        textureName = setTexture;
    }

////RETURNS TEXTURE DICTATED BY THE MAIN CHARACTER HAVING OR NOT HAVING SHIELDS
    public Texture getMainCharacter() {
        if (textureName == "player.png") {
            return mainCharacter;
        }else{
            return mainCharacterShield;
        }
    }

    public MainCharacter(int x, int y){


        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        //textureName = texture;
        mainCharacter = new Texture("player.png");
        mainCharacterShield = new Texture("playerShield.png");
        hitBox = new Rectangle(x+4,y,mainCharacter.getWidth()-8,mainCharacter.getHeight()-4);
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
        if (position.y > DontDie.HEIGHT - 32 - 40){
            position.y = DontDie.HEIGHT - 32 - 40;
        }

        velocity.scl(1/dt);
        hitBox.setPosition(position.x+4,position.y);
    }

////CAUSE THE MAIN CHARACTER TO JUMP
    public void move(){
        velocity.y = 350;
        click.play(0.1f);
    }

////GETS COLLISION DETECTION BOX
    public Rectangle getHitBox() {  // sends the dimentions of the maincharacters hitbox
        return hitBox;
    }

    public void dispose(){
        mainCharacter.dispose();
        click.dispose();
    }
}
