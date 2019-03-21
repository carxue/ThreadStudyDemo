package com.thread.xue.asyn;

public class Point {
	public float x;
	public float y;

	public Point(float x, float y) {
		setLocation(x, y);
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
