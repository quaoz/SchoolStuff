package com.github.quaoz.common.datastructures;

import com.github.quaoz.common.filehandling.FileHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class PersistentArrayList<E> extends ArrayList<E> {
	final private String fileLocation;

	public PersistentArrayList(String fileLocation) {
		super();
		this.fileLocation = fileLocation;
	}

	public PersistentArrayList(String fileLocation, int initialCapacity) {
		super(initialCapacity);
		this.fileLocation = fileLocation;
	}

	public PersistentArrayList(String fileLocation, Collection<? extends E> c) {
		super(c);
		this.fileLocation = fileLocation;
		FileHandler.writeAll(fileLocation, c);
	}

	@Override
	public boolean add(@NotNull E e) {
		FileHandler.write(fileLocation, e.toString());
		return super.add(e);
	}

	@Override
	public E set(int index, E element) {
		E oldValue = super.set(index, element);
		FileHandler.w

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
