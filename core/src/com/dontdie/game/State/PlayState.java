package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.MainCharacter;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.sprites.Projectile;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {
    private MainCharacter mainCharacter;
    private BossCharacter bossCharacter;
    private List<Projectile> projectileList;
    private Texture backGround;
    private float delay =  0.5f;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        mainCharacter = new MainCharacter(25, 0);
        bossCharacter = new BossCharacter(DontDie.WIDTH - 90, 0);

        projectileList = new ArrayList<Projectile>();
        projectileList.add( new Projectile((int) bossCharacter.getPosition().x, (int) bossCharacter.getPosition().y));
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        backGround = new Texture("Background-1.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mainCharacter.move();
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
        mainCharacter.update(dt);
        bossCharacter.update();
        for( int i = 0; i < projectileList.size(); ) {
            Projectile projectile = projectileList.get(i);
            if( projectile.update(bossCharacter) )
                    i++;
            else
                    projectileList.remove(i);
        }

        delay -= dt;
        if( delay < 0.0f ) {
            projectileList.add(new   Projectile((int) bossCharacter.getPosition().x, (int) bossCharacter.getPosition().y));
            delay = 0.5f;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,cam.position.x - (cam.viewportWidth/2), 0);
        spriteBatch.draw(mainCharacter.getMainCharacter(), mainCharacter.getPosition().x, mainCharacter.getPosition().y);
        spriteBatch.draw(bossCharacter.getBossCharacter(), bossCharacter.getPosition().x, bossCharacter.getPosition().y);
        for( Projectile projectile : projectileList)
            spriteBatch.draw(projectile.getPew(), projectile.getPosition().x, projectile.getPosition().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
