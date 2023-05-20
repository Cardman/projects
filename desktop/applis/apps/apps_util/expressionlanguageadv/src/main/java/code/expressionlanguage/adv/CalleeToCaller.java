package code.expressionlanguage.adv;

public class CalleeToCaller {
    private final MetaCaller call;
    private final CalleeToCaller parent;

    public CalleeToCaller(MetaCaller _e) {
        this(_e,null);
    }

    public CalleeToCaller(MetaCaller _e, CalleeToCaller _r) {
        this.call = _e;
        this.parent = _r;
    }

    public CalleeToCaller getParent() {
        return parent;
    }

    public MetaCaller getCall() {
        return call;
    }
}
