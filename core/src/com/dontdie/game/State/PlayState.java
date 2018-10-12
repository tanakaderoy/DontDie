package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.MainCharacter;

public class PlayState extends State {
    private MainCharacter mC;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        mC = new MainCharacter(50, 100);
        cam.setToOrtho(false, DontDie.WIDTH/2, DontDie.HEIGHT/2);
        bg = new Texture("Background-1.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mC.move();
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
        mC.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(mC.getmC(), mC.getPosition().x, mC.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
