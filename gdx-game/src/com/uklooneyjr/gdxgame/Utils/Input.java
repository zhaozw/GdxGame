package com.uklooneyjr.gdxgame.Utils;

public class Input {
	private final static int NUMBER_KEYS = 255;
	private final static int NUMBER_BUTTONS = 3;
	
	private static boolean[] key = new boolean[NUMBER_KEYS];
	private static boolean[] keyPressed = new boolean[NUMBER_KEYS];
	private static boolean[] keyReleased = new boolean[NUMBER_KEYS];
	
	private static boolean[] button = new boolean[NUMBER_BUTTONS];
	private static boolean[] buttonPressed = new boolean[NUMBER_BUTTONS];
	private static boolean[] buttonReleased = new boolean[NUMBER_BUTTONS];
	
	private static int mouseX, mouseY;
	
	private Input() {}
	
	/**
	 * Initialises all arrays to false
	 */
	public static void initialize() {
		// initialise keys
		for (int i = 0; i < NUMBER_KEYS; ++i) {
			key[i] = false;
		}
		for (int i = 0; i < NUMBER_KEYS; ++i) {
			keyPressed[i] = false;
		}
		for (int i = 0; i < NUMBER_KEYS; ++i) {
			keyReleased[i] = false;
		}
		// initialise mouse buttons
		for (int i = 0; i < NUMBER_BUTTONS; ++i) {
			button[i] = false;
		}
		for (int i = 0; i < NUMBER_BUTTONS; ++i) {
			buttonPressed[i] = false;
		}
		for (int i = 0; i < NUMBER_BUTTONS; ++i) {
			buttonReleased[i] = false;
		}
		
		mouseX = 0;
		mouseY = 0;
	}
	
	/**
	 * Resets all the key-pressed and key-released arrays to false
	 */
	public static void reset() {
		// reset keys
		for (int i = 0; i < NUMBER_KEYS; ++i) {
			keyPressed[i] = false;
		}
		for (int i = 0; i < NUMBER_KEYS; ++i) {
			keyReleased[i] = false;
		}
		// reset mouse button
		for (int i = 0; i < NUMBER_BUTTONS; ++i) {
			buttonPressed[i] = false;
		}
		for (int i = 0; i < NUMBER_BUTTONS; ++i) {
			buttonReleased[i] = false;
		}
	}
	
	public static void setKeyDown(int keycode) {
		key[keycode] = true;
		keyPressed[keycode] = true;
	}
	
	public static void setKeyUp(int keycode) {
		key[keycode] = false;
		keyReleased[keycode] = true;
	}
	
	public static boolean key(int keycode) {
		return key[keycode];
	}
	
	public static boolean keyPressed(int keycode) {
		return keyPressed[keycode];
	}
	
	public static boolean keyReleased(int keycode) {
		return keyReleased[keycode];
	}
	
	public static void setButtonDown(int buttoncode) {
		button[buttoncode] = true;
		buttonPressed[buttoncode] = true;
	}
	
	public static void setButtonUp(int buttoncode) {
		button[buttoncode] = false;
		buttonReleased[buttoncode] = true;
	}
	
	public static boolean button(int buttoncode) {
		return button[buttoncode];
	}
	
	public static boolean buttonPressed(int buttoncode) {
		return buttonPressed[buttoncode];
	}
	
	public static boolean buttonReleased(int buttoncode) {
		return buttonReleased[buttoncode];
	}
	
	public static void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
	
	public static int mouseX() {
		return mouseX;
	}
	
	public static int mouseY() {
		return mouseY;
	}
}
