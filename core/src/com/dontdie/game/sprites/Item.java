package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

public class Item {
    private static final int X_MOVEMENT = -1;
    private Vector3 position;
    private Vector3 itemMovement;
    private Texture itemTexture;
    private Rectangle hitArea;
    public String textureName;

    public Vector3 getPosition(){
        return position;
    }

    public Texture getItemTexture(){
        return itemTexture;
    }
    public Item(int x, int y, String texture){
        position = new Vector3(x, y,0);
        itemMovement = new Vector3(X_MOVEMENT,0 ,0);
        textureName = texture;
        itemTexture = new Texture(texture);
        hitArea = new Rectangle(x,y,itemTexture.getWidth(),itemTexture.getHeight());
    }

    public boolean update(){
        if (position.x < -5){
            return false;
        }
        position.add(itemMovement);
        hitArea.setPosition(position.x,position.y);
        return true;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(hitArea);
    }
}
