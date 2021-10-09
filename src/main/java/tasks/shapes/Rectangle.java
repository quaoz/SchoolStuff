package tasks.shapes;

public class Rectangle extends Shape {
	private double width;
	private double height;

	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;

		this.numSides = 4f;
	}

	double getWidth() {
		return width;
	}

	void setWidth(double width) {
		this.width = width;
	}

	double getHeight() {
		return height;
	}

	void setHeight(double height) {
		this.height = height;
	}

	double getArea() {
		return this.width * this.height;
	}

	double getPerimeter() {
		return (width + height) * 2;
	}
}
