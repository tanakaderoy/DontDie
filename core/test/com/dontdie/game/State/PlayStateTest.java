package com.dontdie.game.State;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayStateTest extends StateTester{
    PlayState pState;
    GameStateManager gsm;

    @Before
    public void initialize(){
        gsm = new GameStateManager();
        pState = new PlayState(gsm);
    }


    @Test
    public void render() {
        Assert.assertNotNull(pState.backGround);

    }

}