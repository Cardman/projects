package code.sml;

import code.util.CustList;

public abstract class AbsImgAnimAttr extends Attr {
    private CustList<int[][]> anim;
    protected AbsImgAnimAttr(String _name) {
        super(_name);
    }

    public CustList<int[][]> getAnim() {
        return anim;
    }

    public void setAnim(CustList<int[][]> _i) {
        this.anim = _i;
    }
}
