package tasks.bitmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BitMap {
	private final int width;
	private final int height;
	private int colourDepth;
	private ArrayList<Integer>[][] pixels;

	BitMap(int width, int height, int colourDepth) {
		this.width = width;
		this.height = height;
		this.colourDepth = colourDepth;
	}

	@Contract(pure = true)
	BitMap(@NotNull ArrayList<Integer>[] @NotNull [] pixels) {
		this.pixels = pixels;
		this.width = pixels.length;
		this.height = pixels[0].length;
	}

	public int totalPixels() {
		return width * height;
	}

	public int maxColours() {
		return (int) Math.pow(2, colourDepth);
	}

	public ImageSize ImageSize() {
		ImageSize imageSize = new ImageSize();
		imageSize.size = (totalPixels() * colourDepth) / 8;

		while (imageSize.size >= 1000) {
			imageSize.size /= 1000;
			imageSize.scale++;
		}

		return imageSize;
	}
}

class ImageSize {
	int size;
	int scale; // number of times it was divided by 1000
}

