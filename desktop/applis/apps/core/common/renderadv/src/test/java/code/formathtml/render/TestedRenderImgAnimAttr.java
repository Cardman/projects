package code.formathtml.render;

import code.sml.AbsImgAnimAttr;
import code.sml.Attr;

public final class TestedRenderImgAnimAttr extends AbsImgAnimAttr {
    public TestedRenderImgAnimAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        TestedRenderImgAnimAttr at_ = new TestedRenderImgAnimAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }
    @Override
    public String escape() {
        return "";
    }

}
