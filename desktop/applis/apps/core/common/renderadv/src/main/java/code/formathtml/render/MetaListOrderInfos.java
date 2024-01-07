package code.formathtml.render;

import code.util.core.BoolVal;

public final class MetaListOrderInfos {
    private String typeLi;
    private int li;
    private BoolVal order;
    private MetaOrderedList container;

    public BoolVal getOrder() {
        return order;
    }

    public void setOrder(BoolVal _o) {
        this.order = _o;
    }

    public int getLi() {
        return li;
    }

    public void setLi(int _l) {
        this.li = _l;
    }

    public String getTypeLi() {
        return typeLi;
    }

    public void setTypeLi(String _t) {
        this.typeLi = _t;
    }

    public MetaOrderedList getContainer() {
        return container;
    }

    public void setContainer(MetaOrderedList _c) {
        this.container = _c;
    }
}
