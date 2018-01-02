package code.serialize.classes;
import code.sml.FromAndToString;
import code.util.annot.RwXml;
import code.util.ints.Displayable;

@RwXml
public class PrimitiveFive implements Displayable {

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
    public String display() {
        return primitive.toString();
    }

    public Integer getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Integer _primitive) {
        primitive = _primitive;
    }
}
