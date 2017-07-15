package code.util.ints;
import code.util.AbEqList;

public interface ParentObject<T> extends Parent<T> {

    AbEqList<T> getChildren(AbEqList<T> _list, Object _object);
}
