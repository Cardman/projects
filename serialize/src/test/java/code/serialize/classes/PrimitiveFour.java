package code.serialize.classes;
import code.sml.FromAndToString;
import code.util.annot.RwXml;

@RwXml
public class PrimitiveFour {

    private static int _nbInstances_;

    private Integer primitive;

    public PrimitiveFour() {
    }

    PrimitiveFour(String _input) {
        primitive = Integer.parseInt(_input);
    }

    @FromAndToString
    public static PrimitiveFour newPrimitiveFour(String _input) {
        return new PrimitiveFour(_input);
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

    public static int getNbInstances() {
        return _nbInstances_;
    }

    public static void setNbInstances(int _nbInstances) {
        _nbInstances_ = _nbInstances;
    }
}
