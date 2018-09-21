package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.MainCharacter;


public class PlayState extends State {   // play state is when we are actually playing the game


    private Texture backGround;
    private MainCharacter mC;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        mC = new MainCharacter(50,100);
        cam.setToOrtho(false, DontDie.WIDTH / 2, DontDie.HEIGHT /2);
        backGround = new Texture("glacial_mountains.png");   // setting background
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())  //// makes the Main Character Jump
            mC.move();
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void render(SpriteBatch sb) {    // rendering all starting locations
        sb.setProjectionMatrix(cam.combined);


        sb.begin();
        sb.draw(backGround, cam.position.x - (cam.viewportWidth/2), 0 );
        sb.draw(mC.getmC(), mC.getPostions().x, mC.getPostions().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
