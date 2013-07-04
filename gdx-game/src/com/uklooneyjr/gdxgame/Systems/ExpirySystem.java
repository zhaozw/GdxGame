package com.uklooneyjr.gdxgame.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.uklooneyjr.gdxgame.Components.ExpiresComponent;

public class ExpirySystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<ExpiresComponent> em;
	
	@SuppressWarnings("unchecked")
	public ExpirySystem() {
		super(Aspect.getAspectForAll(ExpiresComponent.class));
	}

	@Override
	protected void process(Entity e) {
		ExpiresComponent exp = em.get(e);
		exp.decrementDelay(world.getDelta());
		if (exp.getDelay() < 0) {
			e.deleteFromWorld();
		}
	}
}
