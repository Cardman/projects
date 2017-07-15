package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class RefTwo {

    private RefOne refOne;

    public RefOne getRefOne() {
        return refOne;
    }

    public void setRefOne(RefOne _refOne) {
        refOne = _refOne;
    }
}
