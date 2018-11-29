package com.dontdie.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.DontDie;

import com.dontdie.game.sprites.GameTest;
import com.dontdie.game.sprites.MainCharacter;

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
    Sound click;

    @Before
    public void setUp() throws Exception {
        velocity = new Vector3(0,0,0);
        position = new Vector3(0,0,0);
        mainCharacter = new Texture("player.png");
        test = new MainCharacter(25,0,"player.png");
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
    public void getPositionX(){
        Assert.assertEquals(25,test.getPosition().x, .003);
    }

    @Test
    public void getPositionY(){
        Assert.assertEquals(0,test.getPosition().y, .003);
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
    @Test
    public void getSound() {
        assertNull("true",click);
        click = Gdx.audio.newSound(Gdx.files.internal("../android/assets/button-3.mp3"));
        assertNotNull(click);




    }

}