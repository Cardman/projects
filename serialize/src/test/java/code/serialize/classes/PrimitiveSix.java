package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public abstract class PrimitiveSix {

    private Integer primitive;

    @RwXml
    PrimitiveSix() {
        primitive = 0;
    }

    @RwXml
    PrimitiveSix(String _input) {
        primitive = Integer.parseInt(_input);
    }

    @Override
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
