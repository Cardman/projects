package code.serialize.classes;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class ContainerCompositeTwo {

    private CustList<CompositeTwo> object;

    public CustList<CompositeTwo> getObject() {
        return object;
    }

    public void setObject(CustList<CompositeTwo> _object) {
        object = _object;
    }
}
