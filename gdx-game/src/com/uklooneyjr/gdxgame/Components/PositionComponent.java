package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;

public class PositionComponent extends Component {
	private float x, y;
	
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void addX(float x) {
		this.x += x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void addY(float y) {
		this.y += y;
	}
}
