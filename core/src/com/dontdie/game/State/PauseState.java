package com.dontdie.game.State;


import com.badlogic.gdx.Gdx;
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



    public PauseState(GameStateManager gsm) {
        super(gsm);

        menuTexture = new Texture("menu.png");
        restartTexture = new Texture("restart.png");
        resumeTexture = new Texture("resume.png");
        menuBtn = new GameButton(DontDie.cam.position.x - menuTexture.getWidth()/2,DontDie.HEIGHT/2-50, menuTexture, stage);
        restartBtn = new GameButton(DontDie.cam.position.x - restartTexture.getWidth()/2,DontDie.HEIGHT/2-150, restartTexture, stage);
        resumeBtn = new GameButton(DontDie.cam.position.x - resumeTexture.getWidth()/2,DontDie.HEIGHT/2-250, resumeTexture, stage);
        background = new Texture(DontDie.backGround);
        gamepause_title = new Texture("pauseMenu.png");
    }


    @Override
    public void handleInput() {
        if(menuBtn.isPressed()) {
            gsm.set(new MenuState(gsm));
        }
        else if (restartBtn.isPressed()){
            gsm.set(new PlayState(gsm));
        }
        else if (resumeBtn.isPressed()){
            System.out.println("PauseState popped");
            gsm.pop();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(DontDie.cam.combined);
        sb.begin();
        sb.draw(background,DontDie.cam.position.x - (DontDie.cam.viewportWidth/2), 0);
        sb.draw(background, 0,0);
        sb.draw(gamepause_title, DontDie.cam.position.x - gamepause_title.getWidth()/2, DontDie.HEIGHT - 200);
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
