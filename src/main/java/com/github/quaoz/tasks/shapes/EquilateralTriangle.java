package com.github.quaoz.tasks.shapes;

public class EquilateralTriangle extends Shape {
	private double side;

	EquilateralTriangle(double side) {
		this.side = side;
		this.numSides = 3f;
	}

	double getSide() {
		return side;
	}

	void setSide(double side) {
		this.side = side;
	}

	double getArea() {
		return (Math.sqrt(3) / 4) * Math.pow(side, 2);
	}

	double getPerimeter() {
		return side * 3;
	}
}
