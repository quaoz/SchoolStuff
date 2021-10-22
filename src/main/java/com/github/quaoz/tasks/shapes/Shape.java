package com.github.quaoz.tasks.shapes;

class Shape {
	float numSides;

	float getInternalAngle() {
		return (numSides - 2) * 180 / numSides;
	}
}
