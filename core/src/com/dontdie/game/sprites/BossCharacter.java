package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

public class BossCharacter {
    private static final int Y_MOVEMENT = 5;
    private Vector3 position;
    private Vector3 goUp;
    private Vector3 goDown;
    private boolean up;
    private Texture bC;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getbC() {
        return bC;
    }

    public BossCharacter(int x, int y) {
        position = new Vector3(x,y,0);
        goUp = new Vector3(0, Y_MOVEMENT,0);
        goDown = new Vector3(0, -Y_MOVEMENT,0);
        up = true;
        bC = new Texture("FlameDemon Evolved.png");

    }
    public void update() {
        if (position.y < 0){
            position.y = 0;
            up = true;
        }
        if (position.y > DontDie.HEIGHT - 64){
            position.y = DontDie.HEIGHT - 64;
            up = false;
        }
        if (up) {
            position.add(goUp);
        }else{
            position.add(goDown);
        }


    }
}
