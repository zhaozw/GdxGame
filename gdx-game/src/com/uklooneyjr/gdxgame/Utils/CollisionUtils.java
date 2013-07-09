package com.uklooneyjr.gdxgame.Utils;

public class CollisionUtils {
	public static boolean AABB(float aX, float aY, float aW, float aH,
			float bX, float bY, float bW, float bH) {
		if(aX > bX + bW) {
			return false;
		}
		if(bX > aX + aW) {
			return false;
		}
		if(aY > bY + bH) {
			return false;
		}
		if(bY > aY + aH) {
			return false;
		}
		
		return true;
	}
}
