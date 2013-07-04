package com.uklooneyjr.gdxgame.Components;

import java.util.HashMap;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimationComponent extends Component { 
	private HashMap<String, Animation> loadedAnimations;
	private Animation currentAnimation;
	private float stateTime = 0.f;
	
	/**
	 * Creates the HashMap for loaded animations internally, animation that is
	 * passed into the constructor will be set as the current animation.
	 * 
	 * @param name Name of the animation, to be stored as a key in the HashMap
	 * @param animation Reference to the new animation
	 */
	public SpriteAnimationComponent(String name, Animation animation) {
		loadedAnimations = new HashMap<String, Animation>();
		addAnimation(name, animation, true);
	}
	
	/**
	 * Add a new animation to the component.
	 * 
	 * @param name Name of the animation, to be stored as a key in the HashMap
	 * @param animation Reference to the new animation
	 * @param makeCurrent True if the current animation is to be set to the new animation
	 */
	public void addAnimation(String name, Animation animation, boolean makeCurrent) {
		loadedAnimations.put(name, animation);
		if (makeCurrent) {
			currentAnimation = animation;
		}
	}
	
	/**
	 * Sets the current animation to an animation stored in the HashMap
	 * 
	 * @param name The key for the HashMap
	 * @return false if animation does not exist within the HashMap, otherwise true
	 */
	public boolean set(String name) {
		Animation a = loadedAnimations.get(name);
		if (a == null) return false;
		if (a != currentAnimation) {
			currentAnimation = a;
		}
		return true;
	}
	
	public void incrementStateTime(float delta) {
		stateTime += delta;
	}
	
	public TextureRegion getFrame() {
		return currentAnimation.getKeyFrame(stateTime);
	}
}
