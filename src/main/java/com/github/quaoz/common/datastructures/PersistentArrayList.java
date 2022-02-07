package com.github.quaoz.common.datastructures;

import com.github.quaoz.common.filehandling.SequentialFileHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class PersistentArrayList<E> extends ArrayList<E> {
	final private File file;

	public PersistentArrayList(String fileLocation) {
		super();
		this.file = new File(fileLocation);
	}

	public PersistentArrayList(String fileLocation, int initialCapacity) {
		super(initialCapacity);
		this.file = new File(fileLocation);
	}

	public PersistentArrayList(String fileLocation, Collection<? extends E> c) {
		super(c);
		this.file = new File(fileLocation);
		SequentialFileHandler.writeAll(file, c);
	}

	public PersistentArrayList(File file) {
		super();
		this.file = file;
	}

	public PersistentArrayList(File file, int initialCapacity) {
		super(initialCapacity);
		this.file = file;
	}

	public PersistentArrayList(File file, Collection<? extends E> c) {
		super(c);
		this.file = file;
		SequentialFileHandler.writeAll(file, c);
	}

	@Override
	public boolean add(@NotNull E e) {
		SequentialFileHandler.write(file, e.toString());
		return super.add(e);
	}

	@Override
	public E set(int index, E element) {
		E oldValue = super.set(index, element);
		try {
			SequentialFileHandler.writeAt(file, element.toString(), index);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oldValue;
	}

	@Override
	public <T1> T1[] toArray(IntFunction<T1[]> generator) {
		return super.toArray(generator);
	}

	@Override
	public Stream<E> stream() {
		return super.stream();
	}

	@Override
	public Stream<E> parallelStream() {
		return super.parallelStream();
	}
}
