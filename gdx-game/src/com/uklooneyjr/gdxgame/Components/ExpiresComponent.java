package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;

public class ExpiresComponent extends Component {
	private float delay;
	
	public ExpiresComponent(float delay) {
		this.delay = delay;
	}
	
	public float getDelay() {
		return delay;
	}
	
	public void decrementDelay(float delta) {
		if (delta < 0) {
			System.out.println("Decrementing delay by value less than 0");
		}
		delay -= delta;
	}

}
