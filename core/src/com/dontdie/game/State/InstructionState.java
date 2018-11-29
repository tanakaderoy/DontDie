package com.dontdie.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.DontDie;
import com.dontdie.game.sprites.BossCharacter;
import com.dontdie.game.sprites.MainCharacter;



public class InstructionState extends State {
        private Texture backGround;
        private Texture instructions;
        private MainCharacter mainCharacter;

        private BossCharacter bossCharacter;

        private Texture statContainer;
        private Texture shootTexture;
        private Texture jumpTexture;
        public float timeAlive;

        public int coinCount;
        public int ammoCount;

        private GameButton shootButton;
        private GameButton jumpButton;

        private BitmapFont font;



    public InstructionState(GameStateManager gsm){
        super(gsm);

        backGround = new Texture(DontDie.backGround);
        instructions = new Texture("instructions.png");

        statContainer = new Texture("box.png");
        shootTexture = new Texture("shootButton.png");
        jumpTexture = new Texture("jump.png");

        coinCount = 0;
        ammoCount = 0;
        timeAlive = 0;

        font = new BitmapFont();
        font.setColor(Color.LIME);

        mainCharacter = new MainCharacter(jumpTexture.getWidth()+30, 0);
        bossCharacter = new BossCharacter(DontDie.WIDTH - shootTexture.getWidth()-60, 0, "FlameDemon Evolved.png",100);



        jumpButton = new GameButton(0,0,jumpTexture,stage);
        shootButton = new GameButton(DontDie.WIDTH - shootTexture.getWidth(), 0, shootTexture, stage);
    }

    public void handleInput(){
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    public void update(float dt){
        handleInput();
    }

    public void render(SpriteBatch spriteBatch){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(DontDie.cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround,DontDie.cam.position.x - (DontDie.cam.viewportWidth/2), 0);
        spriteBatch.draw(statContainer, DontDie.cam.position.x - statContainer.getWidth()/2,0,statContainer.getWidth(), 20);
        spriteBatch.draw(statContainer, 0 ,DontDie.HEIGHT-40, DontDie.WIDTH,40);
        spriteBatch.draw(mainCharacter.getMainCharacter(), mainCharacter.getPosition().x, mainCharacter.getPosition().y);
        spriteBatch.draw(bossCharacter.getBossCharacter(), bossCharacter.getPosition().x, bossCharacter.getPosition().y);

        font.draw(spriteBatch, "Coins: " + String.valueOf(coinCount),10, DontDie.HEIGHT-20);
        font.draw(spriteBatch, "Ammo: " + String.valueOf(ammoCount), 10, DontDie.HEIGHT-7);
        font.draw(spriteBatch, "Time Alive: " + String.format("%,.2f",DontDie.yourTime) + " seconds", 300, DontDie.HEIGHT-10);

        spriteBatch.draw(instructions,DontDie.cam.position.x - instructions.getWidth()/2,  0, instructions.getWidth(), DontDie.HEIGHT);
        spriteBatch.end();
        shootButton.draw(stage);
        jumpButton.draw(stage);
        }

    public void dispose(){
        backGround.dispose();
        mainCharacter.dispose();
        instructions.dispose();
        bossCharacter.dispose();
    }
}
