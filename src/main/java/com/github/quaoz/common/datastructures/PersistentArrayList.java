package com.github.quaoz.common.datastructures;

import java.util.ArrayList;
import java.util.Collection;
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
	}

	@Override
	public boolean add(E e) {

		return super.add(e);
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
