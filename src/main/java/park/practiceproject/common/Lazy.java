package park.practiceproject.common;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {
	private Supplier<? extends T> supplier;
	private T value;

	private Lazy(Supplier<? extends T> supplier) {
		this.supplier = supplier;
	}

	public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
		return new Lazy<>(supplier);
	}

	@Override
	public T get() {
		T localReference = value;

		if (localReference == null) {
			synchronized (this) {
				localReference = value;

				if (localReference == null) {
					value = localReference = supplier.get();
					supplier = null;
				}
			}
		}

		return localReference;
	}
}