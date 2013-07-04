	package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;

public class VelocityComponent extends Component {
	private float vx, vy;

	public VelocityComponent(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public VelocityComponent() {
		this(0.f, 0.f);
	}
	
	public void set(float vx, float vy) {
		this.vx = vx;
		this.vx = vy;
	}
	
	public void stop() {
		this.vx = 0.f;
		this.vy = 0.f;
	}
	
	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}
	
	public void addVx(float vx) {
		this.vx += vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	
	public void addVy(float vy) {
		this.vy += vy;
	}

}
