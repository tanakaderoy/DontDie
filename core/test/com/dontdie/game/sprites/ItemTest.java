package com.dontdie.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class ItemTest extends GameTest {
    private Vector3 position;
    private Texture Item;

    @Before
    public void setUp() throws Exception {
        position = new Vector3(5,10,5);
        Item = new Texture("coin.png");
    }

    @Test
    public void getPosition() {
        assertNotNull(position);
    }

    @Test
    public void getItemTexture() {
        assertNotNull("true",Item);

    }

    @Test
    public void update() {
    }
}