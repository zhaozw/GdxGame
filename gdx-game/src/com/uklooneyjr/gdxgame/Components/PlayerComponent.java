package com.uklooneyjr.gdxgame.Components;

import com.artemis.Component;
import com.artemis.utils.Timer;
import com.uklooneyjr.gdxgame.Enums.Direction;

public class PlayerComponent extends Component {
	private Direction direction = Direction.RIGHT;
	private boolean jumping = false;
	private boolean running = false;
	
	private Timer attackTimer;
	private boolean attackReady = true;
	
	private Timer energyBallTimer;
	private boolean energyBallReady = true;
	
	public PlayerComponent() {
		attackTimer = new Timer(0.1f) {
			@Override
			public void execute() {
				attackReady = true;
			}
		};
		
		energyBallTimer = new Timer(1.f) {
			@Override
			public void execute() {
				energyBallReady = true;
			}
		};
	}
	
	public void updateTimers(float delta) {
		attackTimer.update(delta);
		energyBallTimer.update(delta);
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isAttackReady() {
		return attackReady;
	}

	public void useAttack() {
		attackReady = false;
		attackTimer.reset();
	}

	public boolean isEnergyBallReady() {
		return energyBallReady;
	}

	public void useEnergyBall() {
		energyBallReady = false;
		energyBallTimer.reset();
	}
}
