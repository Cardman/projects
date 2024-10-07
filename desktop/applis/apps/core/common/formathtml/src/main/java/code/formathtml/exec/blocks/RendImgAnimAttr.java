package code.formathtml.exec.blocks;

import code.images.BaseSixtyFourUtil;
import code.sml.AbsImgAnimAttr;
import code.sml.Attr;
import code.sml.util.MessagesTranslations;
import code.util.core.StringUtil;

public final class RendImgAnimAttr extends AbsImgAnimAttr {
    public RendImgAnimAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        RendImgAnimAttr at_ = new RendImgAnimAttr(getName());
        at_.setAnim(getAnim());
        return at_;
    }
    @Override
    public String escape() {
        return MessagesTranslations.BASE+ "=="+ StringUtil.join(BaseSixtyFourUtil.getStringsByImage(getAnim(),MessagesTranslations.BASE),'=');
    }

}
