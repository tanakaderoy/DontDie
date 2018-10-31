package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.DontDie;

public class Projectile {
    private Vector3 position;
    private Vector3 shoot_velo;
    private Texture pew;
    private int start;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPew() {
        return pew;
    }

    public Projectile(int x, int y){
        start = x;
        position = new Vector3(x, y, 0);
        shoot_velo = new Vector3(-9, 0, 0);
        pew = new Texture("fireball.png");
    }

    public boolean update(BossCharacter bC){

        if (position.x< -5){
            return false;
        }
        position.add(shoot_velo);
        return true;
    }
}

