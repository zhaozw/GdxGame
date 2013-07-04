package com.uklooneyjr.gdxgame.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.uklooneyjr.gdxgame.Components.SpriteAnimationComponent;
import com.uklooneyjr.gdxgame.Components.SpriteComponent;

public class SpriteAnimationSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<SpriteComponent> sm;
	@Mapper ComponentMapper<SpriteAnimationComponent> sam;
	  
	@SuppressWarnings("unchecked")
	public SpriteAnimationSystem() {
		super(Aspect.getAspectForAll(SpriteComponent.class, SpriteAnimationComponent.class));
	}
	 
	@Override
	protected void process(Entity e) {
	 
		SpriteComponent sprite = sm.get(e);
		SpriteAnimationComponent anim = sam.get(e);
	   
		anim.incrementStateTime(world.getDelta());
	 
		TextureRegion region = anim.getFrame();
		sprite.setRegion(region);
	}
	 
	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
