package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.MainCharacter;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.sprites.Projectile;

public class PlayState extends State {
    private MainCharacter mC;
    private BossCharacter bC;
    private Projectile p;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        mC = new MainCharacter(25, 0);
        bC = new BossCharacter(DontDie.WIDTH - 90, 0);
        p = new Projectile((int) bC.getPosition().x, (int) bC.getPosition().y);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
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
        bC.update();
        p.update(bC);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(mC.getmC(), mC.getPosition().x, mC.getPosition().y);
        sb.draw(bC.getbC(), bC.getPosition().x, bC.getPosition().y);
        sb.draw(p.getPew(), p.getPosition().x, p.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
