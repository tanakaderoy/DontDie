package com.dontdie.game.State;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;

////SETTINGS MENU
public class SettingsState extends State{
    public Texture background;
    private Texture soundOnTexture;
    private Texture soundOffTexture;
    private Texture settingsMenu;
    private Texture menuTexture;
    private Texture soundButtonTexture;
    private GameButton soundButton;
    private GameButton menuButton;

    private FitViewport viewport;
    public SettingsState(GameStateManager gsm){
        super(gsm);

        soundOnTexture = new Texture("soundOn.png");
        soundOffTexture = new Texture("soundOff.png");
        settingsMenu = new Texture("settingsMenu.png");
        menuTexture = new Texture("menu.png");
        background = new Texture(DontDie.backGround);
        soundButtonTexture = soundOnTexture;
        soundButton = new GameButton(DontDie.cam.position.x - soundOnTexture.getWidth()/2,DontDie.cam.position.y - soundOnTexture.getHeight()/2 - 50,soundButtonTexture,stage);
        menuButton = new GameButton(DontDie.cam.position.x - menuTexture.getWidth()/2, 10, menuTexture, stage);

    }

////TURNS SOUND ON AND OFF BASED ON TOUCH OF THE SOUND BUTTON
    @Override
    public void handleInput(){
        if(menuButton.isPressed()){
            gsm.set(new MenuState(gsm));
        }else if (soundButton.isPressed() && soundButtonTexture == soundOnTexture){
            System.out.println("MUTE SOUND!!!!!!!!!");
            DontDie.music.setVolume(0.0f);
            soundButtonTexture = soundOffTexture;
            soundButton = new GameButton(DontDie.cam.position.x - soundOnTexture.getWidth()/2,DontDie.cam.position.y - soundOnTexture.getHeight()/2-50,soundButtonTexture,stage);
        } else if (soundButton.isPressed() && soundButtonTexture == soundOffTexture) {
            System.out.println("SOUND ON!!!!!!!!!");
            DontDie.music.setVolume(0.1f);
            soundButtonTexture = soundOnTexture;
            soundButton = new GameButton(DontDie.cam.position.x - soundOnTexture.getWidth()/2,DontDie.cam.position.y - soundOnTexture.getHeight()/2-50,soundButtonTexture,stage);

        }
    }

    @Override
    public void update(float dt){
        handleInput();
    }

////DRAW IMAGES
    @Override
    public void render(SpriteBatch spriteBatch){
        spriteBatch.setProjectionMatrix(DontDie.cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.draw(settingsMenu,DontDie.cam.position.x - settingsMenu.getWidth()/2, DontDie.HEIGHT-200);
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
