package code.serialize.classes;
import code.sml.FromAndToString;

public class PrimitiveTwo {

    private Integer primitive;

    public PrimitiveTwo() {
    }

    public PrimitiveTwo(String _input) {
        primitive = Integer.parseInt(_input);
    }

    @FromAndToString
    public static PrimitiveTwo newPrimitiveTwo(String _input) {
        return new PrimitiveTwo(_input);
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
