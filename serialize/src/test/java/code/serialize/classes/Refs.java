package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class Refs {

    private RefOne ref;

    public RefOne getRef() {
        return ref;
    }

    public void setRef(RefOne _ref) {
        ref = _ref;
    }
}
