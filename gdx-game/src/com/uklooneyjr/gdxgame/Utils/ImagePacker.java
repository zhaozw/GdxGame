package com.uklooneyjr.gdxgame.Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class ImagePacker {
	
	public static void run() {
		Settings settings = new Settings();
		settings.filterMin = Texture.TextureFilter.Linear;
		settings.filterMag = Texture.TextureFilter.Linear;
		settings.pot = false;
		TexturePacker2.process(settings, "../textures", "res/textures", "pack");
	}
}
