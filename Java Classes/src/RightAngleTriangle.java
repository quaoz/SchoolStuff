public class RightAngleTriangle extends Shape {
	private double base;
	private double height;

	RightAngleTriangle(double base, double height) {
		this.base = base;
		this.height = height;
		this.numSides = 3f;
	}

	double getBase() {
		return base;
	}

	void setBase(double base) {
		this.base = base;
	}

	double getHeight() {
		return height;
	}

	void setHeight(double height) {
		this.height = height;
	}

	double getArea() {
		return base * height / 2;
	}

	double getPerimeter() {
		double hypotenuse = Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));
		return hypotenuse + base + height;
	}
}
