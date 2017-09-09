package code.serialize.classes;
import code.util.TreeMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerTreeMap {

    private TreeMap<String,Integer> object;

    public TreeMap<String,Integer> getObject() {
        return object;
    }

    public void setObject(TreeMap<String,Integer> _object) {
        object = _object;
    }
}
