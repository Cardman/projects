package code.bean.nat.exec;

import code.bean.nat.NatCaller;
import code.formathtml.util.FieldUpdates;
import code.formathtml.util.NodeContainer;

public final class NatNodeContainer extends NodeContainer {
    private NatCaller use;
    @Override
    public boolean match(FieldUpdates _f) {
        return use == ((NatFieldUpdates)_f).getUse();
    }

    @Override
    public void setIdClass(FieldUpdates _idClass) {
        use = ((NatFieldUpdates)_idClass).getUse();
    }
}
