package com.uklooneyjr.gdxgame.Systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.uklooneyjr.gdxgame.Components.BoundsComponent;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Components.SpriteComponent;
import com.uklooneyjr.gdxgame.Utils.Assets;

public class SpriteRenderSystem extends EntitySystem {
	
	@Mapper ComponentMapper<PositionComponent> pm;
	@Mapper ComponentMapper<SpriteComponent> sm;
	@Mapper ComponentMapper<BoundsComponent> bm;
	 
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private TextureAtlas atlas;
	private List<Entity> sortedEntities;
	
	private static final boolean RENDER_BOUNDS = false;
	
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(PositionComponent.class, SpriteComponent.class));
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		atlas = Assets.getTextureAtlas();
		
		sortedEntities = new ArrayList<Entity>();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
	@Override
	protected void inserted(Entity e) {
		SpriteComponent sprite = sm.get(e);
		sortedEntities.add(e);
		
		TextureRegion reg = atlas.findRegion(sprite.getName());
		
		sprite.setRegion(reg);
		
		Collections.sort(sortedEntities, new Comparator<Entity>() {
			@Override
			public int compare(Entity e1, Entity e2) {
				SpriteComponent s1 = sm.get(e1);
				SpriteComponent s2 = sm.get(e2);
				return s1.getLayer().compareTo(s2.getLayer());
			}
		});
	}
	
	@Override
	protected void removed(Entity e) {
		sortedEntities.remove(e);
	}
	
	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (Entity e : sortedEntities) {
			process(e);
		}

	}
	
	protected void process(Entity e) {
		if (pm.has(e)) {
			PositionComponent pos = pm.getSafe(e);
			SpriteComponent sprite = sm.get(e);

			batch.setColor(sprite.getR(), sprite.getG(), sprite.getB(), sprite.getAlpha());
			// The alignment point of the sprite is at the centre of the sprite
			float posX = pos.getX() - (sprite.getW() / 2 * sprite.getScaleX());
			float posY = pos.getY() - (sprite.getH() / 2 * sprite.getScaleY());
			   
			batch.draw(sprite.getRegion(), posX, posY, 0, 0, 
					sprite.getW(), sprite.getH(), 
					sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
			
			if (RENDER_BOUNDS) {
				if (bm.has(e)) {
					BoundsComponent bounds = bm.get(e);
					shapeRenderer.rect(pos.getX() - bounds.getLeft(), pos.getY() - bounds.getBottom(), 
							bounds.getLeft() + bounds.getRight(), bounds.getBottom() + bounds.getTop());
				}
			}
		}
	}
	
	@Override
	protected void end() {
		batch.end();
		shapeRenderer.end();
	}

}
