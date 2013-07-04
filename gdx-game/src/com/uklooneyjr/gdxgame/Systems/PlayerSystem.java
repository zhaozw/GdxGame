package com.uklooneyjr.gdxgame.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.uklooneyjr.gdxgame.Components.BoundsComponent;
import com.uklooneyjr.gdxgame.Components.PlayerComponent;
import com.uklooneyjr.gdxgame.Components.PositionComponent;
import com.uklooneyjr.gdxgame.Components.SpriteAnimationComponent;
import com.uklooneyjr.gdxgame.Components.SpriteComponent;
import com.uklooneyjr.gdxgame.Components.VelocityComponent;
import com.uklooneyjr.gdxgame.Enums.Direction;
import com.uklooneyjr.gdxgame.Utils.EntityFactory;
import com.uklooneyjr.gdxgame.Utils.Input;
import com.uklooneyjr.gdxgame.Utils.MathUtils;

public class PlayerSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<PlayerComponent> playerm;
	@Mapper ComponentMapper<PositionComponent> pm;
	@Mapper ComponentMapper<VelocityComponent> vm;
	@Mapper ComponentMapper<BoundsComponent> bm;
	@Mapper ComponentMapper<SpriteComponent> sm;
	@Mapper ComponentMapper<SpriteAnimationComponent> sam;
	
	private PlayerComponent player;
	private PositionComponent pos;
	private VelocityComponent velocity;
	private BoundsComponent bounds;
	private SpriteComponent sprite;
	private SpriteAnimationComponent animation;
	
	private OrthographicCamera camera;
	private Vector3 mouseVector;
	
	private static final float PLAYER_SPEED = 400.f;
	private static final float JUMP_HEIGHT = 1200.f;
	private static final float GRAVITY = 20.f;
	private static final float ENERGY_BALL_SPEED = 800.f;
	private static final int   DISTANCE_FROM_SWORD_SLASH = 80;

	@SuppressWarnings("unchecked")
	public PlayerSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(PlayerComponent.class));
		this.camera = camera;
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void begin() {
		mouseVector = new Vector3(Input.mouseX(), Input.mouseY(), 0);
		camera.unproject(mouseVector);
	}
	
	@Override
	protected void process(Entity e) {	
		player = playerm.get(e);
		pos = pm.get(e);
		velocity = vm.get(e);
		bounds = bm.get(e);
		sprite = sm.get(e);
		animation = sam.get(e);
		
		move();
		attack();
		
		setAnimation();
	}
	
	private void move() {		
		moveX();
		moveY();
	}
	
	private void moveX() {
		if (Input.key(Keys.D)) {
			// move right
			player.setDirection(Direction.RIGHT);
			player.setRunning(true);
			
			float rightBounds = pos.getX() + bounds.getRight();
			if (rightBounds > Gdx.graphics.getWidth() / 2) {
				velocity.setVx(0.f);
			} else {
				velocity.setVx(PLAYER_SPEED);
			}
		} else if (Input.key(Keys.A)) {
			// move left		
			player.setDirection(Direction.LEFT);
			player.setRunning(true);
			
			float leftBounds = pos.getX() - bounds.getLeft();
			if (leftBounds < -Gdx.graphics.getWidth() / 2) {
				velocity.setVx(0.f);
			} else {
				velocity.setVx(-PLAYER_SPEED);
			}
		} else {
			player.setRunning(false);
			
			velocity.setVx(0.f);	
		}
	}
	
	private void moveY() {
		if (onPlatform()) {
			velocity.setVy(0.f);
			player.setJumping(false);
			if (Input.keyPressed(Keys.W) ||
				Input.keyPressed(Keys.SPACE)) {
				// move right
				player.setJumping(true);
				
				velocity.setVy(JUMP_HEIGHT);
			}
		} else {
			velocity.addVy(-GRAVITY);
		}
	}
	
	private boolean onPlatform() {
		float bottomBounds = pos.getY() - bounds.getBottom();
		if (bottomBounds <= -Gdx.graphics.getHeight() / 2) {
			// anchor to platform
			pos.setY(-Gdx.graphics.getHeight() / 2 + bounds.getBottom());
			return true;
		}
		return false;
	}
	
	private void attack() {		
		player.updateTimers(world.getDelta());
		
		if (Input.buttonPressed(Buttons.LEFT) &&
				player.isAttackReady()) {
			
			swordAttack();
		}
		
		if (Input.buttonPressed(Buttons.RIGHT) && 
				player.isEnergyBallReady() &&
				player.isAttackReady()) {
			energyBallAttack();
		}
	}
	
	private void swordAttack() {
		player.useAttack();
		
		// if the player is moving, the sword will be slightly in front
		float movingX = velocity.getVx()*world.getDelta()*10.f;
		
		if (player.getDirection() == Direction.RIGHT) {
			EntityFactory.createSwordSlash(pos.getX() + movingX + DISTANCE_FROM_SWORD_SLASH, 
					pos.getY(), true ).addToWorld();
		} else {
			EntityFactory.createSwordSlash(pos.getX() + movingX - DISTANCE_FROM_SWORD_SLASH, 
					pos.getY(), false).addToWorld();
		}
	}
	
	private void energyBallAttack() {
		player.useAttack();
		player.useEnergyBall();
		
		float x, y, vx, vy;
		
		double angle = MathUtils.getAngle(pos.getX(), pos.getY(), mouseVector.x, mouseVector.y);
		
		vx = (float) (ENERGY_BALL_SPEED * Math.sin(angle));
		vy = (float) (ENERGY_BALL_SPEED * Math.cos(angle));
		x = pos.getX() + vx * world.getDelta();
		y = pos.getY() + vy * world.getDelta();
		
		EntityFactory.createEnergyBall(x, y, vx, vy).addToWorld();
	}
	
	private void setAnimation() {
		if (player.getDirection() == Direction.RIGHT) {
			sprite.setScaleX(1);
		} else {
			sprite.setScaleX(-1);
		}
		
		if (player.isJumping()) {
			animation.set("saberHit");
		} else if (player.isRunning()) {
			animation.set("saberRunning");
		} else {
			animation.set("saberStand");
		}
	}
}
