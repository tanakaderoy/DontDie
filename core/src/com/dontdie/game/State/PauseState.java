package com.dontdie.game.State;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;

public class PauseState extends State{
    public Texture background;
    private Texture menuTexture;
    private Texture restartTexture;
    private Texture resumeTexture;
    private Texture gamepause_title;
    private GameButton menuBtn;
    private GameButton restartBtn;
    private GameButton resumeBtn;
    private Stage stage;
    private FitViewport viewport;


    public PauseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH, DontDie.HEIGHT);
        viewport = new FitViewport(800,480, cam);
        stage = new Stage(viewport, DontDie.batch);
        menuTexture = new Texture("play.png");
        restartTexture = new Texture("retry.png");
        resumeTexture = new Texture("settings.png");
        menuBtn = new GameButton(cam.position.x - menuTexture.getWidth()/2,DontDie.HEIGHT/2-50, menuTexture, stage);
        restartBtn = new GameButton(cam.position.x - restartTexture.getWidth()/2,DontDie.HEIGHT/2-150, restartTexture, stage);
        resumeBtn = new GameButton(cam.position.x - resumeTexture.getWidth()/2,DontDie.HEIGHT/2-250, resumeTexture, stage);
        background = new Texture(DontDie.backGround);
        gamepause_title = new Texture("title.png");
    }


    @Override
    public void handleInput() {
        if(menuBtn.isUpPressed()) {
            gsm.set(new MenuState(gsm));
        }
        else if (restartBtn.isUpPressed()){
            gsm.set(new PlayState(gsm));
        }
        else if (resumeBtn.isUpPressed()){
            gsm.push(new PlayState(gsm));
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
        sb.draw(background,cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(background, 0,0);
        sb.draw(gamepause_title, cam.position.x - gamepause_title.getWidth()/2, DontDie.HEIGHT - 200);
        sb.end();
        menuBtn.draw(stage);
        restartBtn.draw(stage);
        resumeBtn.draw(stage);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
