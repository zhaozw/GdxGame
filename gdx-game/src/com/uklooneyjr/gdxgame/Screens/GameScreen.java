package com.uklooneyjr.gdxgame.Screens;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.uklooneyjr.gdxgame.Systems.CollisionSystem;
import com.uklooneyjr.gdxgame.Systems.EnemySpawningSystem;
import com.uklooneyjr.gdxgame.Systems.ExpirySystem;
import com.uklooneyjr.gdxgame.Systems.InputSystem;
import com.uklooneyjr.gdxgame.Systems.MovementSystem;
import com.uklooneyjr.gdxgame.Systems.PlayerSystem;
import com.uklooneyjr.gdxgame.Systems.SpriteAnimationSystem;
import com.uklooneyjr.gdxgame.Systems.SpriteRenderSystem;
import com.uklooneyjr.gdxgame.Utils.EntityFactory;

public class GameScreen implements Screen {

	@SuppressWarnings("unused")
	private Game game;
	private World world;
	
	private SpriteRenderSystem spriteRenderSystem;
	private InputSystem inputSystem;
	
	private OrthographicCamera camera;
	
	public GameScreen(Game game) {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		
		this.game = game;
		
		world = new World();
		EntityFactory.init(world);
		
		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
		inputSystem = world.setSystem(new InputSystem(), true);
		world.setManager(new GroupManager());
		world.setSystem(new SpriteAnimationSystem());
		world.setSystem(new PlayerSystem(camera));
	    world.setSystem(new MovementSystem());
	    world.setSystem(new ExpirySystem());
	    world.setSystem(new EnemySpawningSystem());
	    world.setSystem(new CollisionSystem());
		
		world.initialize();
		
		EntityFactory.createSaber(-100, -200).addToWorld();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.f, 0.2f, 0.f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.setDelta(delta);
		world.process();
		spriteRenderSystem.process();
		inputSystem.process();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
