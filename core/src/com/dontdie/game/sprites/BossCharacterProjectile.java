package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.DontDie;

public class BossCharacterProjectile {
    private Vector3 position;
    private Vector3 shoot_velo;
    private Texture pew;
    private float start;
    private Rectangle hitArea;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPew() {
        return pew;
    }

    public BossCharacterProjectile(float x, float y){
        start = x;
        position = new Vector3(x, y, 0);
        shoot_velo = new Vector3(-9, 0, 0);
        pew = new Texture("fireball.png");
        hitArea = new Rectangle(x,y, pew.getWidth(), pew.getHeight());
    }

    public boolean update(BossCharacter bossCharacter){

        if (position.x< -pew.getWidth()){
            return false;
        }
        position.add(shoot_velo);
        hitArea.setPosition(position.x, position.y);
        return true;
    }
    public boolean collides(Rectangle player){
        return player.overlaps(hitArea);
    }

    public void dispose(){
        pew.dispose();
    }
}

