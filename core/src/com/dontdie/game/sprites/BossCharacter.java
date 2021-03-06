package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

public class BossCharacter {
    private static final int Y_MOVEMENT = 6;
    private Vector3 position;
    private Vector3 goUp;
    private Vector3 goDown;
    public boolean up;
    public Texture bossCharacter;
    public Rectangle hitBox;
    public String textureName;
    public float fullHealth;

////RETURN BOSS CHARACTER'S POSITION
    public Vector3 getPosition() {
        return position;
    }

////RETURN BOSS CHARACTER'S TEXTURE
    public Texture getBossCharacter() {
        return bossCharacter;
    }

////BOSS CHARACTER CONSTRUCTOR (X-COORDINATE, Y-COORDINATE, BOSS CHARACTER TEXTURE, BOSS HEALTH)
    public BossCharacter(int x, int y, String texture, float health) {
        position = new Vector3(x, y, 0);
        goUp = new Vector3(0, Y_MOVEMENT, 0);
        goDown = new Vector3(0, -Y_MOVEMENT, 0);
        up = false;
        textureName= texture;
        fullHealth = health;
        bossCharacter = new Texture(texture);
        hitBox = new Rectangle(x+10,y,bossCharacter.getWidth()/2,bossCharacter.getHeight());

    }

////BOSS CHARACTER CONSTANTLY MOVES UP AND DOWN BASED ON MAIN CHARACTER'S POSITION
    public void update(MainCharacter mainCharacter) {

        if (position.y < 0) {
            position.y = 0;
        }

        if (position.y > DontDie.HEIGHT - 64-40) {
            position.y = DontDie.HEIGHT - 64-40;
        }

        if (position.y >= mainCharacter.getPosition().y + 40) {
            up = false;
        }

        if (position.y < mainCharacter.getPosition().y - 40) {
            up = true;
        }

        if (up) {
            position.add(goUp);
        } else {
            position.add(goDown);
        }
        hitBox.setPosition(position.x+10,position.y);
    }

////RETURNS COLLISION DETECTION BOX
    public Rectangle getHitBox() {
        return hitBox;
    }

    public void dispose(){
        bossCharacter.dispose();
    }
}