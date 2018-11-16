package com.dontdie.game.State;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;

public class SettingsState extends State{
    public Texture background;
    private Texture soundOnTexture;
    private Texture soundOffTexture;
    private Texture settingsMenu;
    private Texture menuTexture;
    private Texture soundButtonTexture;
    //private GameButton soundOnButton;
    private GameButton soundButton;
    private GameButton menuButton;
    private Stage stage;
    private FitViewport viewport;
    public SettingsState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, DontDie.WIDTH,DontDie.HEIGHT);
        viewport = new FitViewport(DontDie.WIDTH, DontDie.HEIGHT,cam);
        stage = new Stage(viewport,DontDie.batch);
        soundOnTexture = new Texture("soundOn.png");
        soundOffTexture = new Texture("soundOff.png");
        settingsMenu = new Texture("settingsMenu.png");
        menuTexture = new Texture("menu.png");
        background = new Texture(DontDie.backGround);
        soundButtonTexture = soundOffTexture;
        //soundOnButton = new GameButton(0,0,soundOnTexture,stage);
        soundButton = new GameButton(cam.position.x - soundOnTexture.getWidth()/2,cam.position.y - soundOnTexture.getHeight()/2 - 50,soundButtonTexture,stage);
        menuButton = new GameButton(cam.position.x - menuTexture.getWidth()/2, 10, menuTexture, stage);

    }

    @Override
    public void handleInput(){
        if(menuButton.isUpPressed()){
            gsm.set(new MenuState(gsm));
        }else if (soundButton.isUpPressed() && soundButtonTexture == soundOffTexture){
            System.out.println("MUTE SOUND!!!!!!!!!");
            DontDie.music.setVolume(0.0f);
            soundButtonTexture = soundOnTexture;
            soundButton = new GameButton(cam.position.x - soundOnTexture.getWidth()/2,cam.position.y - soundOnTexture.getHeight()/2-50,soundButtonTexture,stage);
        } else if (soundButton.isUpPressed() && soundButtonTexture == soundOnTexture) {
            System.out.println("SOUND ON!!!!!!!!!");
            DontDie.music.setVolume(0.1f);
            soundButtonTexture = soundOffTexture;
            soundButton = new GameButton(cam.position.x - soundOnTexture.getWidth()/2,cam.position.y - soundOnTexture.getHeight()/2-50,soundButtonTexture,stage);

        }
    }

    @Override
    public void update(float dt){
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.draw(settingsMenu,cam.position.x - settingsMenu.getWidth()/2, DontDie.HEIGHT-200);
        spriteBatch.end();
        menuButton.draw(stage);
        soundButton.draw(stage);

    }

    @Override
    public void dispose(){
        background.dispose();
        settingsMenu.dispose();
    }
}
