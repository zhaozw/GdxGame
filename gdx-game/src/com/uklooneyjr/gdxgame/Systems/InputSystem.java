package com.uklooneyjr.gdxgame.Systems;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.uklooneyjr.gdxgame.Utils.Input;

public class InputSystem extends VoidEntitySystem implements InputProcessor {

	@Override
	protected void initialize() {
		Gdx.input.setInputProcessor(this);
		Input.initialize();
	}
	
	@Override
	protected void processSystem() {
		// TODO Auto-generated method stub
		Input.reset();
	}

	@Override
	public boolean keyDown(int keycode) {
		Input.setKeyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Input.setKeyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Input.setButtonDown(button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Input.setButtonUp(button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Input.setMousePosition(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
