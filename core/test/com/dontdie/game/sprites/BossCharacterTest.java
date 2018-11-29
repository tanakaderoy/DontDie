package com.dontdie.game.sprites;

import com.dontdie.game.DontDie;

import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.sprites.GameTest;
import com.dontdie.game.sprites.MainCharacter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BossCharacterTest extends GameTest {
    BossCharacter bossCharacter;
    MainCharacter mainCharacter;
    private int width;

    @Before
    public void initialize(){
        width = DontDie.WIDTH;
        bossCharacter = new BossCharacter(width-90, 0,"FlameDemon Evolved.png");
        mainCharacter = new MainCharacter(25, 0,"player.png");
    }

    @Test
    public void getPositionX(){
        Assert.assertEquals(DontDie.WIDTH-90,bossCharacter.getPosition().x, .003);
    }

    @Test
    public void getPositionY(){
        Assert.assertEquals(0,bossCharacter.getPosition().y, .003);
    }

    @Test
    public void getBossCharacter(){
        Assert.assertEquals(bossCharacter.bossCharacter ,bossCharacter.getBossCharacter());
    }

    @Test
    public void up(){
        Assert.assertEquals(false, bossCharacter.up);
    }

    @Test
    public void getHitBox(){
        Assert.assertEquals(bossCharacter.hitBox, bossCharacter.getHitBox());
    }

}