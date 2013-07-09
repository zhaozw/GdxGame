package com.uklooneyjr.gdxgame.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.uklooneyjr.gdxgame.Components.BoundsComponent;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Utils.CollisionUtils;
import com.uklooneyjr.gdxgame.Utils.Constants;

public class CollisionSystem extends EntitySystem {
	@Mapper ComponentMapper<PositionComponent> pm;
	@Mapper ComponentMapper<BoundsComponent> bm;
	
	private Bag<CollisionPair> collisionPairs;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem() {
		super(Aspect.getAspectForAll(PositionComponent.class, BoundsComponent.class));
	}
	
	@Override
	public void initialize() {
		collisionPairs = new Bag<CollisionPair>();
		
		collisionPairs.add(new CollisionPair(Constants.Groups.PLAYER_ATTACK_SINGLE_HIT_GROUP, 
				Constants.Groups.ENEMY_GROUP, new CollisionHandler() {
			@Override
			public void handleCollision(Entity playerAttack, Entity enemy) {
				playerAttack.deleteFromWorld();
				enemy.deleteFromWorld();
				System.out.println("collision handled");
			}
		}));
		
		collisionPairs.add(new CollisionPair(Constants.Groups.PLAYER_ATTACK_MULTI_HIT_GROUP, 
				Constants.Groups.ENEMY_GROUP, new CollisionHandler() {
			@Override
			public void handleCollision(Entity playerAttack, Entity enemy) {
				enemy.deleteFromWorld();
			}
		}));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> arg0) {
		for(int i = 0; collisionPairs.size() > i; i++) {
			collisionPairs.get(i).checkForCollisions();
		}
		
	}
	
	private class CollisionPair {
		private ImmutableBag<Entity> groupEntitiesA;
		private ImmutableBag<Entity> groupEntitiesB;
		private CollisionHandler handler;
		
		public CollisionPair(String group1, String group2, CollisionHandler handler) {
			groupEntitiesA = world.getManager(GroupManager.class).getEntities(group1);
			groupEntitiesB = world.getManager(GroupManager.class).getEntities(group2);
			this.handler = handler;
		}
		
		public void checkForCollisions() {
			if (groupEntitiesA.size() == 0) {
				return;
			}
			if (groupEntitiesB.size() == 0) {
				return;
			}
			
			for(int a = 0; groupEntitiesA.size() > a; a++) {
				for(int b = 0; groupEntitiesB.size() > b; b++) {
					Entity entityA = groupEntitiesA.get(a);
					Entity entityB = groupEntitiesB.get(b);
					System.out.println("Checking Collisions");
					if (collisionExists(entityA, entityB)) {
						handler.handleCollision(entityA, entityB);
					}
				}
			}
		}
		
		private boolean collisionExists(Entity a, Entity b) {			
			PositionComponent p1 = pm.get(a);
			PositionComponent p2 = pm.get(b);
			
			BoundsComponent b1 = bm.get(a);
			BoundsComponent b2 = bm.get(b);
			
			return CollisionUtils.AABB(p1.getX() - b1.getLeft(), p1.getY() - b1.getBottom(), 
					b1.getLeft() + b1.getRight(), b1.getBottom() + b1.getTop(), 
					p2.getX() - b2.getLeft(), p2.getY() - b2.getBottom(), 
					b2.getLeft() + b2.getRight(), b2.getBottom() + b2.getTop());
		}
	}
	
	private interface CollisionHandler {
		void handleCollision(Entity a, Entity b);
	}


}
