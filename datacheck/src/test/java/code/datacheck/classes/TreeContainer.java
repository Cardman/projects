package code.datacheck.classes;
import code.util.TreeMap;
import code.util.annot.RwXml;

@RwXml
public class TreeContainer {

    private StrangeComparator<String> cmp;

    private TreeMap<String, Integer> tree;

    private TreeMap<Integer, Integer> treeInt;

    public StrangeComparator<String> getCmp() {
        return cmp;
    }

    public void setCmp(StrangeComparator<String> _cmp) {
        cmp = _cmp;
    }

    public TreeMap<String, Integer> getTree() {
        return tree;
    }

    public void setTree(TreeMap<String, Integer> _tree) {
        tree = _tree;
    }

    public TreeMap<Integer, Integer> getTreeInt() {
        return treeInt;
    }

    public void setTreeInt(TreeMap<Integer, Integer> _treeInt) {
        treeInt = _treeInt;
    }
}
