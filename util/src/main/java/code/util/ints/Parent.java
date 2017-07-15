package code.util.ints;
import code.util.AbEqList;

public interface Parent<T> {

    AbEqList<T> getChildren(AbEqList<T> _visited,AbEqList<T> _all);

}
