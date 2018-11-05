package com.dontdie.game.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dontdie.game.GameTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest extends GameTest {
    Projectile test;

    @Before
    public void setUp() throws Exception {
        test = new Projectile(25,0);

    }



    @Test
    public void getPosition() {
        Vector3 expected = new Vector3(25,0,0);
        Assert.assertEquals(expected, test.getPosition());
    }

    @Test
    public void getPew() {
        Assert.assertNotNull(test.getPew());

    }

    @Test
    public void collides() {
        Rectangle check = new Rectangle(25,0,32,32);
        Assert.assertTrue(test.collides(check));
    }

}