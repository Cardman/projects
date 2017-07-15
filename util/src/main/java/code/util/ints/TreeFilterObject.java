package code.util.ints;
import code.util.AbEqList;

public interface TreeFilterObject<T extends ParentObject<T>> extends TreeFilter<T> {

    boolean accept(AbEqList<T> _list, Object _object);
}
