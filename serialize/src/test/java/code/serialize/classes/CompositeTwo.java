package code.serialize.classes;
import code.util.Numbers;
import code.util.annot.RwXml;
import code.util.ints.Equallable;

@RwXml
public final class CompositeTwo implements Equallable<CompositeTwo> {

    private Integer primitive;

    public Integer getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Integer _primitive) {
        primitive = _primitive;
    }

    @Override
    public boolean eq(CompositeTwo _obj) {
        CompositeTwo comp_ = _obj;
        if (primitive == null) {
            return comp_.primitive == null;
        }
        if (comp_.primitive == null) {
            return false;
        }
        return Numbers.eq(primitive, comp_.primitive);
    }
}
