package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class RefOne {

    private RefTwo refTwo;

    public RefTwo getRefTwo() {
        return refTwo;
    }

    public void setRefTwo(RefTwo _refTwo) {
        refTwo = _refTwo;
    }
}
