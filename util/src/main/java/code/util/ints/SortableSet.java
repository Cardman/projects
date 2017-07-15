package code.util.ints;



public interface SortableSet<E> extends Settable<E>, HasComparator<E> {

    E lower(E _e);

    E floor(E _e);

    E ceiling(E _e);

    E higher(E _e);
}
