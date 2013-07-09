package com.uklooneyjr.gdxgame.Systems;

import java.util.Random;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.Gdx;
import com.uklooneyjr.gdxgame.Utils.EntityFactory;

public class EnemySpawningSystem extends VoidEntitySystem {
	private Timer gilgaheadTimer;
	
	private Random random = new Random();
	
	public EnemySpawningSystem() {
		gilgaheadTimer = new Timer(1.f, true) {
			@Override
			public void execute() {
				int w = Gdx.graphics.getWidth();
				float n = random.nextFloat();
				
				EntityFactory.createGilgahead(w*n - w/2, 400, 0, -200).addToWorld();
			}
		};
	}

	@Override
	protected void processSystem() {
		gilgaheadTimer.update(world.getDelta());
		
	}
}
