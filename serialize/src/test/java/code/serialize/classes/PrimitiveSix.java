package code.serialize.classes;
import code.util.annot.RwXml;
import code.util.ints.Displayable;

@RwXml
public abstract class PrimitiveSix implements Displayable {

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
