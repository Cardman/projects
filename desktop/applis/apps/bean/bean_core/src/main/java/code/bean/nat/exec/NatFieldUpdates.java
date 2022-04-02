package code.bean.nat.exec;

import code.bean.nat.NatCaller;
import code.formathtml.util.FieldUpdates;

public final class NatFieldUpdates extends FieldUpdates {
    private final NatCaller use;

    public NatFieldUpdates(NatCaller _u) {
        this.use = _u;
    }

    public NatCaller getUse() {
        return use;
    }
}
