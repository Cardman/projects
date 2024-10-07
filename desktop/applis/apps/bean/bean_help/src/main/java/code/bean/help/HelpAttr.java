package code.bean.help;

import code.sml.*;

public final class HelpAttr extends AbsImgAnimAttr {
    public HelpAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        HelpAttr at_ = new HelpAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }

    @Override
    public String escape() {
        return "";
    }
}
