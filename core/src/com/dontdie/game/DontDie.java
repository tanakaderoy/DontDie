package com.dontdie.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dontdie.game.State.GameStateManager;
import com.dontdie.game.State.MenuState;


////SINGLETON CLASS OF THE APPLICATION
public class DontDie extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "Don't Die";
	public static float bestTime;
	public static float yourTime;
	public static float appVolume = 0.1f;
	public static String backGround;
	private GameStateManager gsm;
	private SpriteBatch batch;
	public static FitViewport viewport;
	public static OrthographicCamera cam;
	public static Music music;


////SET BEST TIME ALIVE
	public static void setBestTime(float besttime){
		bestTime = besttime;
	}

////SET YOUR TIME ALIVE
	public static void setYourTime(float yourtime) {
		yourTime = yourtime;
	}

	private static DontDie instance = null;

	public static SpriteBatch getBatch() {
		return instance.batch;
	}

////CREATES GAME
	@Override
	public void create () {
		instance = this;
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		viewport = new FitViewport(DontDie.WIDTH, DontDie.HEIGHT,cam);

		yourTime = 0;
		bestTime = 0;
		backGround = "Background-1.png";
		music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
		music.setLooping(true);
		music.setVolume(appVolume);
		music.play();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
