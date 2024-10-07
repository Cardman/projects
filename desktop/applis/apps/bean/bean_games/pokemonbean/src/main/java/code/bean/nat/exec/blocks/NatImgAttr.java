package code.bean.nat.exec.blocks;

import code.images.*;
import code.sml.*;
import code.sml.util.*;
import code.util.core.StringUtil;

public final class NatImgAttr extends AbsImgAnimAttr {
    public NatImgAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        NatImgAttr at_ = new NatImgAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }
    @Override
    public String escape() {
        return StringUtil.join(BaseSixtyFourUtil.getStringsByImage(getAnim(),MessagesTranslations.BASE),'=');
    }
}
