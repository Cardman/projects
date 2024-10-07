package code.gui.document;

import code.sml.AbsImgAnimAttr;
import code.sml.Attr;

public final class TestedGuiDocImgAnimAttr extends AbsImgAnimAttr {
    public TestedGuiDocImgAnimAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        TestedGuiDocImgAnimAttr at_ = new TestedGuiDocImgAnimAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }
    @Override
    public String escape() {
        return "";
    }

}
