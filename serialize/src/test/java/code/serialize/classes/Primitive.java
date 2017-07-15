package code.serialize.classes;
import code.xml.FromAndToString;

public class Primitive {

    private Integer primitive;

    @FromAndToString
    public static Primitive deserialize(String _input) {
        Primitive pr_ = new Primitive();
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
