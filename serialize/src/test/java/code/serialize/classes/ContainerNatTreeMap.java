package code.serialize.classes;
import code.util.NatTreeMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerNatTreeMap {

    private NatTreeMap<String,Integer> object;

    public NatTreeMap<String,Integer> getObject() {
        return object;
    }

    public void setObject(NatTreeMap<String,Integer> _object) {
        object = _object;
    }
}
