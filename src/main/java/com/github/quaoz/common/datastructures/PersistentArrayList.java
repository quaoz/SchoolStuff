package com.github.quaoz.common.datastructures;

import com.github.quaoz.common.filehandling.SequentialFileHandler;
import org.jetbrains.annotations.NotNull;

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
		SequentialFileHandler.writeAll(fileLocation, c);
	}

	@Override
	public boolean add(@NotNull E e) {
		SequentialFileHandler.write(fileLocation, e.toString());
		return super.add(e);
	}

	@Override
	public E set(int index, E element) {
		E oldValue = super.set(index, element);
		SequentialFileHandler.writeAt(fileLocation, element.toString(), index);
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
