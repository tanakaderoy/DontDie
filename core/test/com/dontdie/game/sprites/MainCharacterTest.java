package com.dontdie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainCharacterTest {
    private Vector3 velocity;
    private Vector3 position;

    @Before
    public void setUp() throws Exception {
        velocity = new Vector3(0,0,0);
        position = new Vector3(0,0,0);
    }

    @Test
    public void getPosition() {
        assertNotNull(position);
    }

    @Test
    public void getMainCharacter() {

    }

    @Test
    public void update() {
    }

    @Test
    public void move() {
        assertEquals("x starts at 0",0,velocity.x,.005);
        assertEquals("y starts at 0",0,velocity.y,.005);
        assertEquals("z starts at 0",0,velocity.z,.005);

        velocity.y = 350;
        assertEquals("Wrong y velocity",350,velocity.y,.003);

    }

    @Test
    public void getHitBox() {
    }

}