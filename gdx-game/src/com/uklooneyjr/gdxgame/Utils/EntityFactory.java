package com.uklooneyjr.gdxgame.Utils;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.uklooneyjr.gdxgame.Components.BoundsComponent;
import com.uklooneyjr.gdxgame.Components.ExpiresComponent;
import com.uklooneyjr.gdxgame.Components.PlayerComponent;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Components.SpriteAnimationComponent;
import com.uklooneyjr.gdxgame.Components.SpriteComponent;
import com.uklooneyjr.gdxgame.Components.VelocityComponent;

public class EntityFactory {
	
	private static World world;
	
	public static void init(World world) {
		EntityFactory.world = world;
		Animations.init();
	}
	
	public static Entity createSaber(float x, float y) {
		Entity e = world.createEntity();
		
		PositionComponent position = new PositionComponent(x, y);
		e.addComponent(position);
		
		BoundsComponent bounds = new BoundsComponent(65, 75, 30, 30);
		e.addComponent(bounds);
		
		SpriteComponent sprite = new SpriteComponent("saberStand");
		e.addComponent(sprite);
		
		SpriteAnimationComponent animation = new SpriteAnimationComponent("saberStand", Animations.saberStand);
		animation.addAnimation("saberRunning", Animations.saberRunning, false);
		animation.addAnimation("saberHit", Animations.saberHit, false);
		e.addComponent(animation);
		
		VelocityComponent velocity = new VelocityComponent(0, 0);
		e.addComponent(velocity);
		
		e.addComponent(new PlayerComponent());
		
		return e;		
	}
	
	public static Entity createEnergyBall(float x, float y, float vx, float vy) {
		Entity e = world.createEntity();
		
		PositionComponent position = new PositionComponent(x, y);
		e.addComponent(position);
		
		VelocityComponent velocity = new VelocityComponent(vx, vy);
		e.addComponent(velocity);
		
		SpriteComponent sprite = new SpriteComponent("energyBall");
		e.addComponent(sprite);
		
		ExpiresComponent expires = new ExpiresComponent(1.f);
		e.addComponent(expires);
		
		BoundsComponent bounds = new BoundsComponent(20, 20, 20, 20);
		e.addComponent(bounds);
		
		return e;
	}
	
	/**
	 * @param direction true for right, false for left
	 */
	public static Entity createSwordSlash(float x, float y, boolean direction) {
		Entity e = world.createEntity();
		
		PositionComponent position = new PositionComponent(x, y);
		e.addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent("swordSlash");
		sprite.setScaleX(direction ? 0.5f : -0.5f);
		sprite.setScaleY(0.5f);
		e.addComponent(sprite);
		
		ExpiresComponent expires = new ExpiresComponent(0.05f);
		e.addComponent(expires);
		
		BoundsComponent bounds = new BoundsComponent(75, 75, 75, 75);
		e.addComponent(bounds);
		
		return e;
	}
	
	private static class Animations {
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
