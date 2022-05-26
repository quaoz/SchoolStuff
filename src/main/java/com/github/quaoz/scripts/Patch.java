package com.github.quaoz.scripts;

import com.github.quaoz.common.datastructures.BinarySearchTree;
import com.github.quaoz.common.sorts.DualPivotIntroSort;
import com.github.quaoz.common.timer.Timer;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Patch {
	public static void main(String[] args) throws IOException {
		// Download the list of common passwords
		// URL url = new URL("https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/Common-Credentials/10-million-password-list-top-1000000.txt");
		// ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
		// FileOutputStream fileOutputStream = new FileOutputStream("list.txt");
		// fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

		File source = new File("list.txt");
		File destination = new File("done.txt");

		Timer timer = new Timer();
		timer.startTimerNano();

		BufferedReader br = new BufferedReader(new FileReader(source));
		PrintWriter printWriter = new PrintWriter(new FileWriter(destination, true));
		/*
		BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();

		// Import the lines into a binary search tree
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			if (line.length() >= 8) {
				binarySearchTree.add(line);
			}
		}

		// Write the sorted lines to a file
		binarySearchTree.inOrder().forEach(printWriter::println);
		*/
		ArrayList<String> lines = new ArrayList<>();
		br.lines().forEachOrdered(lines::add);

		DualPivotIntroSort.sort(lines.toArray(new String[0]));

		System.out.println(timer.stopAndGetElapsedTime());
		// 407300100
	}
}
