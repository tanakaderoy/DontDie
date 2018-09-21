package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("glacial_mountains.png");
        playBtn = new Texture("gem3@0.25x.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, DontDie.WIDTH, DontDie.HEIGHT);
        sb.draw(playBtn, (DontDie.WIDTH /2 )- (playBtn.getWidth()/2), DontDie.HEIGHT);
        sb.end();
    }

    public void dispose(){
        background.dispose();
        playBtn.dispose();

    }


}
