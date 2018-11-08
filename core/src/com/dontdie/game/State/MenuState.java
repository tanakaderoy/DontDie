package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

public class MenuState extends State {
    public Texture background;
    private Texture playBtn;
    private Texture game_title;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        background = new Texture("Background-1.png");
        playBtn = new Texture("play.png");
        game_title = new Texture("title.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(game_title, cam.position.x - game_title.getWidth()/2, DontDie.HEIGHT - 250);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, DontDie.HEIGHT/2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();;
        playBtn.dispose();
    }
}
