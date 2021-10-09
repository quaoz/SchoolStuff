package tasks.shapes;

public class Shape {
	float numSides;

	float getInternalAngle() {
		return (numSides - 2) * 180 / numSides;
	}
}
