package com.uklooneyjr.gdxgame.Utils;

public class MathUtils {
	
	private MathUtils() {}
	
	/**
	 * @param ax x coordinate of the first point
	 * @param ay y coordinate of the first point
	 * @param bx x coordinate of the second point
	 * @param by y coordinate of the second point
	 * @return angle between two points in radians
	 */
	public static float getAngle(float ax, float ay, float bx, float by) {
		float angle = (float) Math.atan2(bx - ax, by - ay);

	    return angle;
	}
}
