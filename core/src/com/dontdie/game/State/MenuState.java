package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dontdie.game.DontDie;

////MAIN MENU FOR THE GAME
public class MenuState extends State {
    public Texture background;
    private Texture playTexture;
    private Texture settingsTexture;
    private Texture exitTexture;
    private Texture game_title;
    private GameButton playBtn;
    private GameButton settingsBtn;
    private GameButton exitBtn;



    public MenuState(GameStateManager gsm) {
        super(gsm);

        playTexture = new Texture("play.png");
        settingsTexture = new Texture("settings.png");
        exitTexture = new Texture("exit.png");
        playBtn = new GameButton(DontDie.cam.position.x - playTexture.getWidth()/2,DontDie.HEIGHT/2-50, playTexture, stage);
        settingsBtn = new GameButton(DontDie.cam.position.x - settingsTexture.getWidth()/2,DontDie.HEIGHT/2-130, settingsTexture, stage);
        exitBtn = new GameButton(DontDie.cam.position.x - exitTexture.getWidth()/2,DontDie.HEIGHT/2-210, exitTexture, stage);
        background = new Texture(DontDie.backGround);
        game_title = new Texture("title.png");
    }

    @Override
    public void handleInput() {
        ////GO TO INSTRUCTION STATE ON PRESS OF PLAY BUTTON
        if(playBtn.isPressed()) {
            gsm.set(new InstructionState(gsm));
        }

        ////GO TO SETTINGS STATE ON PRESS OF SETTINGS BUTTON
        else if (settingsBtn.isPressed()){
            gsm.set(new SettingsState(gsm));
        }

        ////EXIT APP ON PRESS OF EXIT BUTTON
        else if (exitBtn.isPressed()){
            System.exit(0);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

////DRAW IMAGES
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(DontDie.cam.combined);

        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(game_title, DontDie.cam.position.x - game_title.getWidth()/2, DontDie.HEIGHT - 200);
        sb.end();
        playBtn.draw(stage);
        settingsBtn.draw(stage);
        exitBtn.draw(stage);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
