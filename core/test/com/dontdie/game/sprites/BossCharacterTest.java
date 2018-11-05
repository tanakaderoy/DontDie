package com.dontdie.game.sprites;

import com.dontdie.game.DontDie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BossCharacterTest {
    BossCharacter bossCharacter;
    MainCharacter mainCharacter;
    @Before
    public void initialize(){
        bossCharacter = new BossCharacter(DontDie.WIDTH-90, 0);
        mainCharacter = new MainCharacter(25, 100);
    }

    @Test
    public void getPositionX(){
        Assert.assertEquals(DontDie.WIDTH-90,bossCharacter.getPosition().x);
    }

    @Test
    public void getPositionY(){
        Assert.assertEquals(0,bossCharacter.getPosition().y);
    }

    @Test
    public void getBossCharacter(){
        Assert.assertEquals(bossCharacter ,bossCharacter.getBossCharacter());
    }

    @Test
    public void up(){
        Assert.assertEquals(true, bossCharacter.up);
    }

    @Test
    public void getHitBox(){
        Assert.assertEquals(bossCharacter.hitBox, bossCharacter.getHitBox());
    }

}