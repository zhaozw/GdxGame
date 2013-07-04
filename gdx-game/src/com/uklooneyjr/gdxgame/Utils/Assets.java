package com.uklooneyjr.gdxgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	private static TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("res/textures/pack.atlas"), 
			Gdx.files.internal("res/textures"));

	public static TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
}
