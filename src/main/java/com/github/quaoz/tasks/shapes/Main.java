package com.github.quaoz.tasks.shapes;

public class Main {
	public static void main(String[] args) {
		final Square square = new Square(6);
		System.out.println(square.getSide());

		Square secondSquare = new Square(9);
		System.out.println(secondSquare.getPerimeter());

		secondSquare.setSide(3);
		System.out.println(secondSquare.getArea());
		System.out.println(secondSquare.getInternalAngle());

		final Circle circle = new Circle(3.2);
		System.out.println(circle.getPerimeter());
		System.out.println(circle.getInternalAngle());

		final Rectangle rectangle = new Rectangle(2, 3);
		System.out.println(rectangle.getArea());

		final RightAngleTriangle rightAngleTriangle = new RightAngleTriangle(5, 7);
		System.out.println(rightAngleTriangle.getArea());

		final EquilateralTriangle equilateralTriangle = new EquilateralTriangle(6);
		System.out.println(equilateralTriangle.getArea());
		System.out.println(equilateralTriangle.getInternalAngle());
	}
}


