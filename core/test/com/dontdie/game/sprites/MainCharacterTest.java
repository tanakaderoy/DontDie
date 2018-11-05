package com.dontdie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.GameTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainCharacterTest extends GameTest {
    private Vector3 velocity;
    private Vector3 position;
    private Texture mainCharacter;
    private Rectangle hitBox;
    private MainCharacter test;

    @Before
    public void setUp() throws Exception {
        velocity = new Vector3(0,0,0);
        position = new Vector3(0,0,0);
        mainCharacter = new Texture("../android/assets/player.png");
        test = new MainCharacter(25,0);
    }

    @Test
    public void getPosition() {
        assertNotNull(position);
    }

    @Test
    public void getMainCharacter() {
        assertNotNull("true",mainCharacter);

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
        Rectangle expected = new Rectangle(25,0,mainCharacter.getWidth(),mainCharacter.getHeight());
        Rectangle rec = test.getHitBox();
        Assert.assertEquals(expected,rec);

    }

}