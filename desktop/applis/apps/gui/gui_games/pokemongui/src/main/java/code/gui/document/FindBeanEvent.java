package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.MetaFont;
import code.gui.initialize.*;
import code.util.*;

public final class FindBeanEvent implements AbsActionListener {

    private BeanBuilderHelper page;

    private FindNextElement finding;

    private final AbsTextField field;

    private final CustList<AbsTextPane> labels = new CustList<AbsTextPane>();
    private final AbstractProgramInfos api;

    public FindBeanEvent(AbsTextField _field, AbstractProgramInfos _a) {
        field = _field;
        api = _a;
    }

    public static void paint(AbstractProgramInfos _api, AbsTextPane _tp, CustList<SegmentPart> _segs) {
        String text_ = _tp.getText();
        _tp.setCharacterAttributes(0,text_.length(),_api.getCompoFactory().newAttrSet(), true);
        MetaFont copy_ = _tp.getMetaFont();
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addFontFamily(copy_.getFontFamily());
        att_.addFontSize(_tp.getMetaFont().getRealSize());
        att_.addBackground(_tp.getBackgroundValue());
        att_.addForeground(_tp.getForegroundValue());
        _tp.setCharacterAttributes(0,text_.length(),att_,false);
        for (SegmentPart s: _segs) {
            AbsAttrSet attSeg_ = _api.getCompoFactory().newAttrSet();
            attSeg_.addFontFamily(copy_.getFontFamily());
            attSeg_.addBackground(GuiConstants.ORANGE);
            attSeg_.addForeground(_tp.getForegroundValue());
            attSeg_.addFontSize(_tp.getMetaFont().getRealSize());
            _tp.setCharacterAttributes(s.getBegin(),s.getEnd()-s.getBegin(),attSeg_,false);
        }
        AbsAttrSet attSeg_ = _api.getCompoFactory().newAttrSet();
        attSeg_.addFontFamily(copy_.getFontFamily());
        attSeg_.addForeground(_tp.getForegroundValue());
        attSeg_.addFontSize(_tp.getMetaFont().getRealSize());
        _tp.setCharacterAttributes(0,text_.length(),attSeg_,false);
    }

    public static void scroll(BeanBuilderHelper _rend, AbsCustComponent _dual) {
        AbsCustComponent c_ = _dual;
        AbsScrollPane sc_ = _rend.getScrollPane();
        int x_ = 0;
        int y_ = 0;
        while (c_ != null) {
            AbsCustComponent par_ = _rend.getParents().getVal(c_);
            if (par_ != null) {
                x_ += c_.getXcoords();
                y_ += c_.getYcoords();
            }
            c_ = par_;
        }
        sc_.setHorizontalValue(x_);
        sc_.setVerticalValue(y_);
    }

    public void setFinding(BeanBuilderHelper _document) {
        page = _document;
        finding = new FindNextElement(_document.getMetaSearchableContents());
    }
    @Override
    public void action() {
        String text_ = field.getText();
        if (text_.isEmpty()) {
            clear();
            return;
        }
        finding.next(text_);
        MetaSearchableContent lab_ = finding.getLabel();
        if (lab_ == null) {
            clear();
            return;
        }
        for (EntryCust<MetaSearchableContent, CustList<SegmentPart>> l: finding.getSegments().entryList()) {
            AbsTextPane l_ = page.getRefsSearch().getVal(l.getKey());
            paint(api,l_,l.getValue());
            labels.add(l_);
        }
        scroll(page, page.getRefsSearch().getVal(lab_));
    }

    private void clear() {
        for (AbsTextPane l: getLabels()) {
            paint(api,l,new CustList<SegmentPart>());
        }
        labels.clear();
    }

    public CustList<AbsTextPane> getLabels() {
        return labels;
    }
}
