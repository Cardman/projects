package code.util.ints;
import code.util.AbEqList;

public interface TreeFilter<T extends Parent<T>> {

    boolean accept(AbEqList<T> _list);
}
