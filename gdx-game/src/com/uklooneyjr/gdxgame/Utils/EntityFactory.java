package com.uklooneyjr.gdxgame.Utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.uklooneyjr.gdxgame.Components.BoundsComponent;
import com.uklooneyjr.gdxgame.Components.ExpiresComponent;
import com.uklooneyjr.gdxgame.Components.PlayerComponent;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Components.SpriteAnimationComponent;
import com.uklooneyjr.gdxgame.Components.SpriteComponent;
import com.uklooneyjr.gdxgame.Components.VelocityComponent;
import com.uklooneyjr.gdxgame.Enums.Direction;

public class EntityFactory {
	
	private static World world;
	
	private EntityFactory() {}
	
	public static void init(World world) {
		EntityFactory.world = world;
		Assets.Animations.init();
	}
	
	public static Entity createSaber(float x, float y) {
		Entity e = world.createEntity();
		
		PositionComponent position = new PositionComponent(x, y);
		e.addComponent(position);
		
		BoundsComponent bounds = new BoundsComponent(65, 75, 30, 30);
		e.addComponent(bounds);
		
		SpriteComponent sprite = new SpriteComponent("saberStand");
		e.addComponent(sprite);
		
		SpriteAnimationComponent animation = new SpriteAnimationComponent("saberStand", Assets.Animations.saberStand);
		animation.addAnimation("saberRunning", Assets.Animations.saberRunning, false);
		animation.addAnimation("saberHit", Assets.Animations.saberHit, false);
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
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_ATTACK_SINGLE_HIT_GROUP);
		
		return e;
	}
	
	/**
	 * @param direction true for right, false for left
	 */
	public static Entity createSwordSlash(float x, float y, Direction direction) {
		Entity e = world.createEntity();
		
		PositionComponent position = new PositionComponent(x, y);
		e.addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent("swordSlash");
		sprite.setScaleX(0.5f);
		sprite.setScaleY(0.5f);
		
		if (direction == Direction.LEFT) {
			sprite.setScaleX(-0.5f);
		} else if (direction == Direction.UP) {
			sprite.setRotation(90);
		}
		sprite.setScaleY(0.5f);
		e.addComponent(sprite);
		
		ExpiresComponent expires = new ExpiresComponent(0.05f);
		e.addComponent(expires);
		
		BoundsComponent bounds = new BoundsComponent(75, 75, 75, 75);
		e.addComponent(bounds);
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_ATTACK_MULTI_HIT_GROUP);
		
		return e;
	}
	
	public static Entity createGilgahead(float x, float y, float vx, float vy) {
		Entity e = world.createEntity();
		
		PositionComponent position =  new PositionComponent(x, y);
		e.addComponent(position);
		
		VelocityComponent velocity = new VelocityComponent(vx, vy);
		e.addComponent(velocity);
				
		SpriteComponent sprite = new SpriteComponent("gilgameshHead");
		e.addComponent(sprite);
		
		ExpiresComponent expires = new ExpiresComponent(5.f);
		e.addComponent(expires);

		BoundsComponent bounds = new BoundsComponent(40, 40, 40, 40);
		e.addComponent(bounds);
		
		world.getManager(GroupManager.class).add(e, Constants.Groups.ENEMY_GROUP);
		
		return e;
	}
	
	
}
