package com.uklooneyjr.gdxgame.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Components.VelocityComponent;

public class MovementSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<PositionComponent> pm;
	@Mapper ComponentMapper<VelocityComponent> vm;
	
	
	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(PositionComponent.class, VelocityComponent.class));
	}

	@Override
	protected void process(Entity e) {
		PositionComponent position = pm.get(e);
		VelocityComponent velocity = vm.get(e);
		
		position.addX(velocity.getVx()*world.getDelta());
		position.addY(velocity.getVy()*world.getDelta());
	}
}
