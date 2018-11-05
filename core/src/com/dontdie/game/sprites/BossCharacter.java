package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

public class BossCharacter {
    private static final int Y_MOVEMENT = 8;
    private Vector3 position;
    private Vector3 goUp;
    private Vector3 goDown;
    public boolean up;
    public Texture bossCharacter;
    public Rectangle hitBox;


    public Vector3 getPosition() {
        return position;
    }

    public Texture getBossCharacter() {
        return bossCharacter;
    }

    public BossCharacter(int x, int y) {
        position = new Vector3(x, y, 0);
        goUp = new Vector3(0, Y_MOVEMENT, 0);
        goDown = new Vector3(0, -Y_MOVEMENT, 0);
        up = false;
        bossCharacter = new Texture("FlameDemon Evolved.png");
        hitBox = new Rectangle(x,y,bossCharacter.getWidth(),bossCharacter.getHeight());

    }


    public void update(MainCharacter mainCharacter) {

        if (position.y < 0) {
            position.y = 0;
        }

        if (position.y > DontDie.HEIGHT - 64) {
            position.y = DontDie.HEIGHT - 64;
        }

        if (position.y >= mainCharacter.getPosition().y + 60) {
            up = false;
        }

        if (position.y < mainCharacter.getPosition().y - 60) {
            up = true;
        }

        if (up) {
            position.add(goUp);
        } else {
            position.add(goDown);
        }
        hitBox.setPosition(position.x,position.y);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}