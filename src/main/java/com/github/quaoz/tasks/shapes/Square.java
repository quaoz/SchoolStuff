package com.github.quaoz.tasks.shapes;

public class Square extends Shape {
	private double side;

	Square(double side) {
		this.side = side;
		this.numSides = 4f;
	}

	double getSide() {
		return side;
	}

	void setSide(double side) {
		this.side = side;
	}

	double getArea() {
		return Math.pow(this.side, 2);
	}

	double getPerimeter() {
		return side * numSides;
	}
}
