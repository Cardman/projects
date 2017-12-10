package code.serialize.classes;
import code.sml.FromAndToString;

public class PrimitiveThree {

    private Integer primitive;

    @FromAndToString
    public static PrimitiveThree deserialize(String _input) {
        PrimitiveThree pr_ = new PrimitiveThree();
        pr_.primitive = Integer.parseInt(_input);
        return pr_;
    }

    @FromAndToString
    public String serialize() {
        return primitive.toString();
    }

    public Integer getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Integer _primitive) {
        primitive = _primitive;
    }
}
