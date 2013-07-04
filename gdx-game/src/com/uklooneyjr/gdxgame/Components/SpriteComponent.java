package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component {
	private String name;
	private TextureRegion region;
	private Layer layer;
	
	// colour and alpha values, range between 0 and 1
	private float r = 1;
	private float g = 1;
	private float b = 1;
	private float a = 1;
	// transform values
	private float scaleX = 1;
	private float scaleY = 1;
	private float rotation;
	// width and height of the texture region
	private int w, h;

	public enum Layer {
		DEFAULT,
		BACKGROUND,
		ACTORS_1,
		ACTORS_2,
		ACTORS_3,
		PARTICLES;
	   
		public int getLayerId() {
			return ordinal();
		}
	}
	  
	public SpriteComponent(String name, Layer layer) {
		this.setName(name);
		this.setLayer(layer);
	}
	  
	public SpriteComponent(String name) {
		this(name, Layer.DEFAULT);
	}
	
	public SpriteComponent() {
		this("default", Layer.DEFAULT);
	}
	
	public void setRegion(TextureRegion region) {
		this.region = region;
		this.w = region.getRegionWidth();
		this.h = region.getRegionHeight();
	}
	
	public TextureRegion getRegion() {
		return region;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Layer getLayer() {
		return layer;
	}

	public void setLayer(Layer layer) {
		this.layer = layer;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getR() {
		return r;
	}

	public float getG() {
		return g;
	}

	public float getB() {
		return b;
	}

	public float getAlpha() {
		return a;
	}
	
	public void setColour(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void setColour(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void setAlpha(float a) {
		this.a = a;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	  
}
