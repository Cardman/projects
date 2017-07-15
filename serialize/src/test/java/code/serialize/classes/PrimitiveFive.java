package code.serialize.classes;
import code.util.annot.RwXml;
import code.xml.FromAndToString;

@RwXml
public class PrimitiveFive {

    private Integer primitive;

    @RwXml
    PrimitiveFive(String _input) {
        primitive = Integer.parseInt(_input);
    }

    @FromAndToString
    public static PrimitiveFive newPrimitiveFive(String _input) {
        return new PrimitiveFive(_input);
    }

    @Override
    @FromAndToString
    public String toString() {
        return primitive.toString();
    }

    public Integer getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Integer _primitive) {
        primitive = _primitive;
    }
}
