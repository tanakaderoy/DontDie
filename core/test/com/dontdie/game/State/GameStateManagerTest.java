package com.dontdie.game.State;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateManagerTest extends StateTester{
    GameStateManager gsm;

    State state;

    @Before
    public void initialize(){
        gsm = new GameStateManager();

    }

    @Test
    public void push() {
        Assert.assertNotNull(gsm);
    }

}