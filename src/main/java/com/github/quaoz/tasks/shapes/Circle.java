package com.github.quaoz.tasks.shapes;

class Circle extends Shape {
	private final double Pi = Math.PI;
	private double radius;

	Circle(double radius) {
		this.radius = radius;
		this.numSides = 1f;
	}

	double getRadius() {
		return radius;
	}

	void setRadius(double radius) {
		this.radius = radius;
	}

	double getPerimeter() {
		return 2 * Pi * radius;
	}

	@Override
	float getInternalAngle() {
		return 360f;
	}
}
