package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class MainCharacter {
    private static final int GRAVITY = -15;  //untis the mC drops

    private Vector3 postions;
    private Vector3 velocity;


    private Texture mC;


    public MainCharacter(int x, int y ){   /// counsturctiung starting positions
        postions = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        mC = new Texture("cat.jpg");

    }


    public void update(float dt){    /// changing coridnates

        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        postions.add(0, velocity.y, 0);

        velocity.scl(1/dt);
    }
    //// simple getters
    public Vector3 getPostions() {
        return postions;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getmC() {
        return mC;
    }

    public void move(){
        velocity.y = 250;  // units that he mC moves up



    }



}
