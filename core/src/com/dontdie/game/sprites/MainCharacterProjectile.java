package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.DontDie;

public class MainCharacterProjectile {
    private static Vector3 position;
    private static  Vector3 shoot_velo;
    private Texture pew;
    private float start;
    private static Rectangle hitArea;
    public boolean shotsFired;

////RETURNS MAIN CHARACTER'S PROJECTILE POSITION
    public Vector3 getPosition() {
        return position;
    }

////RETURNS MAIN CHARACTER'S PROJECTILE TEXTURE
    public Texture getPew() {
        return pew;
    }

    public MainCharacterProjectile(float x, float y){
        start = x;
        position = new Vector3(x, y, 0);
        shoot_velo = new Vector3(12, 0, 0);
        pew = new Texture("redfireball.png");
        hitArea = new Rectangle(x,y, pew.getWidth(), pew.getHeight());
        shotsFired = false;
    }

////IF PROJECTILE IS STILL ON SCREEN, MOVE WITH THE SET SHOOTING VELOCITY
    public boolean update(MainCharacter mainCharacter){

        if (position.x > DontDie.WIDTH){
            return false;
        }
        position.add(shoot_velo);
        hitArea.setPosition(position.x, position.y);
        return true;
    }

////COLLISION DETECTION
    public boolean collides(Rectangle player){
        return player.overlaps(hitArea);
    }
}
