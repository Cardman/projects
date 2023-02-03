package code.gui.document;



import code.formathtml.render.MetaIndentNbLabel;
import code.formathtml.render.MetaStyle;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.util.core.NumberUtil;

public final class DualIndentNbLabel extends DualLabel {

    private final String pattern;
    private int rate;
    public DualIndentNbLabel(DualContainer _container, MetaIndentNbLabel _component,
            RenderedPage _page, int _width) {
        super(_container, _component, _page);
        getLabel().setOpaque(true);
        StringBuilder noop_ = new StringBuilder();
        for (int i = 0; i < _width; i++) {
            noop_.append("_");
        }
        pattern = noop_.append("  ").toString();
    }

    @Override
    public void paint() {
        MetaStyle style_ = getComponent().getStyle();
        MetaFont copy_ = newFont(style_);
        int h_ = NumberUtil.max(rate,getLabel().heightFont(copy_));
        int w_ = getPage().getCompoFactory().stringWidth(copy_,pattern);
        AbstractImage img_ = getPage().getGene().getImageFactory().newImageRgb(w_, h_);
        img_.setFont(copy_);
        img_.setColor(GuiConstants.newColor(style_.getBgColor()));
        img_.fillRect(0, 0, w_, h_);
        getLabel().setIcon(getPage().getGene().getImageFactory(), img_);
        img_.dispose();
    }

    public void setRate(int _r) {
        this.rate = _r;
    }
}
