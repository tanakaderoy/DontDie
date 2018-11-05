package com.dontdie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.State.GameStateManager;
import com.dontdie.game.State.MenuState;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DontDieTest extends simpletest {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Don't Die";
    private GameStateManager gsm;
    public static SpriteBatch batch;

    private Music music;
    @Before
    public void setUp() throws Exception {

        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        //music.setLooping(true);
        music.setVolume(0.1f);
        music.play();


    }

    @Test
    public void create() {

        //System.out.println(music.isLooping());
        Assert.assertFalse(music.isLooping());

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
    public void playerPNGFileExists() {
        assertTrue("This test will only pass when the player.png file  has not been deleted.", Gdx.files
                .internal("../android/assets/player.png").exists());
    }
    @Test
    public void bossPNGFileExists() {
        assertTrue("This test will only pass when the FlameDemon Evolved.png file  has not been deleted.", Gdx.files
                .internal("../android/assets/FlameDemon Evolved.png").exists());
    }
    @Test
    public void oneEqualsOne() {
        assertEquals("1",1,1);
    }
}