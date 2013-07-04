package com.me.gdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.uklooneyjr.gdxgame.GdxGame;
import com.uklooneyjr.gdxgame.Utils.ImagePacker;

public class Main {
	public static void main(String[] args) {
		final int FRAME_WIDTH = 1280;
		final int FRAME_HEIGHT = 900;
		
		ImagePacker.run();
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "gdx-game";
		cfg.useGL20 = true;
		cfg.width = FRAME_WIDTH;
		cfg.height = FRAME_HEIGHT;
		
		new LwjglApplication(new GdxGame(), cfg);
	}
}
