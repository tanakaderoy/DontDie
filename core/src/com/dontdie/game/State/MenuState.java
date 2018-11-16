package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;

public class MenuState extends State {
    public Texture background;
    private Texture playTexture;
    private Texture settingsTexture;
    private Texture game_title;
    private GameButton playBtn;
    private GameButton settingsBtn;
    private Stage stage;
    private FitViewport viewport;
    //private GameButton playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        viewport = new FitViewport(800,480, cam);
        stage = new Stage(viewport, DontDie.batch);
        playTexture = new Texture("play.png");
        settingsTexture = new Texture("settings.png");
        playBtn = new GameButton(cam.position.x - playTexture.getWidth()/2,DontDie.HEIGHT/2-50, playTexture, stage);
        settingsBtn = new GameButton(cam.position.x - settingsTexture.getWidth()/2,DontDie.HEIGHT/2-150, settingsTexture, stage);
        background = new Texture(DontDie.backGround);
        game_title = new Texture("title.png");
    }

    @Override
    public void handleInput() {
       /* if(Gdx.input.justTouched()){
            gsm.set(new InstructionState(gsm));
        }*/
        if(playBtn.isUpPressed()) {
            gsm.set(new InstructionState(gsm));
        }
        else if (settingsBtn.isUpPressed()){
            gsm.set(new SettingsState(gsm));
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
        sb.draw(game_title, cam.position.x - game_title.getWidth()/2, DontDie.HEIGHT - 200);
       // sb.draw(PlayBtn, cam.position.x - playBtn.imgTexture.getWidth()/2, DontDie.HEIGHT/2-50);
       // sb.draw(settingsBtn, cam.position.x - settingsBtn.getWidth()/2, DontDie.HEIGHT/2 - 150);
        sb.end();
        playBtn.draw(stage);
        settingsBtn.draw(stage);
    }

    @Override
    public void dispose() {
        background.dispose();
       // playBtn.dispose();
    }
}
