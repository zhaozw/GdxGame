package com.uklooneyjr.gdxgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	
	private Assets() {}
	
	private static TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("res/textures/pack.atlas"), 
			Gdx.files.internal("res/textures"));

	public static TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public static class Animations {
		static Animation saberStand = null;
		static Animation saberRunning = null;
		static Animation saberHit = null;
		
		static void init() {
			saberStand = new Animation(0.1f, 
					Assets.getTextureAtlas().findRegions("saberStand"), Animation.NORMAL);
			
			saberRunning = new Animation(0.1f,
						Assets.getTextureAtlas().findRegions("saberRunning"), Animation.LOOP);
			
			saberHit = new Animation(0.1f,
					Assets.getTextureAtlas().findRegions("saberHit"), Animation.NORMAL);
			
		}
	}
}
