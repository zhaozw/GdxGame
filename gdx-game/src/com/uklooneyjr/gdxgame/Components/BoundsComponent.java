package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;

/**
 * AABB bounds relative to the player's position
 */
public class BoundsComponent extends Component {
	private float top, bottom, left, right;
	/** true if the entity will collide with static objects */
	
	public BoundsComponent(float top, float bottom, float left, float right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
	
	public void set(float top, float bottom, float left, float right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public float getTop() {
		return top;
	}

	public float getBottom() {
		return bottom;
	}

	public float getLeft() {
		return left;
	}

	public float getRight() {
		return right;
	}

	public BoundsComponent() {
		this(0.f, 0.f, 0.f, 0.f);
	}
}
