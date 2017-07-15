package code.serialize.classes;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.annot.RwXml;

@RwXml
public class Containers {

    private StringList list;

    private TreeMap<String, Integer> treemap;

    private StringMap<MyEnum> map;

    private int[] array;

    private int[][] arrayDouble;

    public StringList getList() {
        return list;
    }

    public void setList(StringList _list) {
        list = _list;
    }

    public TreeMap<String, Integer> getTreemap() {
        return treemap;
    }

    public void setTreemap(TreeMap<String, Integer> _treemap) {
        treemap = _treemap;
    }

    public StringMap<MyEnum> getMap() {
        return map;
    }

    public void setMap(StringMap<MyEnum> _map) {
        map = _map;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] _array) {
        array = _array;
    }

    public int[][] getArrayDouble() {
        return arrayDouble;
    }

    public void setArrayDouble(int[][] _arrayDouble) {
        arrayDouble = _arrayDouble;
    }
}
