package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.BinarySearchTree;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;

public class Patch {
	public static void main(String[] args) throws IOException {
		System.out.println(isCommon("password"));
	}

	private static void generate(@NotNull URL url) {
		File source = new File("list.txt");
		File destination = new File("done.txt");

		try (
				FileOutputStream fileOutputStream = new FileOutputStream("list.txt");
				BufferedReader br = new BufferedReader(new FileReader(source));
				PrintWriter printWriter = new PrintWriter(new FileWriter(destination, true))
		) {
			ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
			fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
			BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();

			// Import the lines into a binary search tree
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if (line.length() >= 8) {
					binarySearchTree.add(line);
				}
			}

			// Write the sorted lines to a file
			binarySearchTree.inOrder().forEach(printWriter::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isCommon(String password) {
		try (BufferedReader br = new BufferedReader(new FileReader("done.txt"))) {
			String line = br.readLine();

			while (line != null && line.compareTo(password) <= 0) {
				if (line.toLowerCase(Locale.ROOT).equals(password.toLowerCase(Locale.ROOT))) {
					System.out.println("common password");
					return true;
				}
				line = br.readLine();
			}
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
