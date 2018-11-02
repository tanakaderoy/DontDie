package com.dontdie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;


import org.junit.Test;

import static org.junit.Assert.*;

public class DontDieTest {

    @Test
    public void create() {
    }

    @Test
    public void render() {
    }

    @Test
    public void dispose() {
    }
    @Test
    public void badlogicLogoFileExists() {
        assertTrue("This test will only pass when the badlogic.jpg file coming with a new project setup has not been deleted.", Gdx.files
                .internal("../android/assets/badlogic.jpg").exists());
    }
    @Test
    public void oneEqualsOne() {
        assertEquals("1",1,1);
    }
}