package code.gui.document;




import code.formathtml.render.MetaLabel;
import code.formathtml.render.MetaStyle;
import code.formathtml.render.SegmentPart;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.util.CustList;

public abstract class DualLabel extends DualLeaf {

    private final CustList<SegmentPart> segments = new CustList<SegmentPart>();

    private final String text;
    private final AbsPreparedLabel label;

    protected DualLabel(DualContainer _container, MetaLabel _component, RenderedPage _page) {
        super(_container, _component, _page);
        text = _component.getText();
        label = GuiBaseUtil.prep(_page.getGene().getImageFactory(), _page.getGene().getCompoFactory());
        updateGraphics(label,_component);
    }

    public final void clearSegments() {
        segments.clear();
    }

    public final void addSegment(SegmentPart _segment) {
        segments.add(_segment);
    }

    public CustList<SegmentPart> getSegments() {
        return segments;
    }

    void backAndFront() {
        label.setBackground(getContainer().getGraphic().getBackgroundValue());
        label.setForeground(getContainer().getGraphic().getForegroundValue());
    }

    @Override
    public AbsCustComponent getGraphic() {
        return getLabel();
    }

    public AbsPreparedLabel getLabel() {
        return label;
    }

    public void paint() {
        MetaStyle style_ = getComponent().getStyle();
        MetaFont copy_ = newFont(style_);
        int h_ = getPage().getCompoFactory().heightFont(copy_);
        int w_ = adjustedWidth(copy_);
        AbstractImage img_ = getPage().getGene().getImageFactory().newImageRgb(w_, h_);
        img_.setFont(copy_);
        img_.setColor(GuiConstants.newColor(style_.getBgColor()));
        img_.fillRect(0, 0, w_, h_);
        img_.setColor(GuiConstants.ORANGE);
        for (SegmentPart s: segments) {
            int beginIndex_ = s.getBegin();
            int b_ = getPage().getCompoFactory().stringWidth(copy_,text.substring(0, beginIndex_));
            int d_ = getPage().getCompoFactory().stringWidth(copy_,text.substring(beginIndex_, s.getEnd()));
            img_.fillRect(b_, 0, d_, h_);
        }
        img_.setColor(GuiConstants.newColor(style_.getFgColor()));
        img_.drawString(text, 0, h_ - 1);
        label.setIcon(getPage().getGene().getImageFactory(), img_);
        img_.dispose();
    }

    protected int adjustedWidth(MetaFont _copy) {
        int w_ = getPage().getCompoFactory().stringWidth(_copy,text);
        if (w_ == 0) {
            w_ = getPage().getCompoFactory().stringWidth(_copy," ");
        }
        return w_;
    }

    public String getText() {
        return text;
    }
    protected static MetaFont newFont(MetaStyle _style) {
        String fontFamily_ = _style.getFontFamily();
        int realSize_ = _style.getRealSize();
        return font(fontFamily_, _style.getItalic()+_style.getBold(), realSize_);
    }

    private static MetaFont font(String _fontFamily, int _font, int _realSize) {
        return new MetaFont(_fontFamily, _font, _realSize);
    }
}
