package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.images.MetaFont;
import code.gui.initialize.*;
import code.util.*;

public final class FindBeanEventContent {

    private FindNextElement finding;

    private final AbsTextField field;

    private final CustList<AbsTextPane> labels = new CustList<AbsTextPane>();
    private final AbstractProgramInfos api;
    private AbsScrollPane scrollPane;
    private IdMap<AbsCustComponent,AbsCustComponent> parents;
    private IdMap<MetaSearchableContent,AbsTextPane> refsSearch;
    private IdMap<AbsTextPane,AbsAttrSet> fonts;

    public FindBeanEventContent(AbsTextField _field, AbstractProgramInfos _a) {
        field = _field;
        api = _a;
    }

    public static void paint(AbstractProgramInfos _api, AbsTextPane _tp, CustList<SegmentPart> _segs, IdMap<AbsTextPane, AbsAttrSet> _fonts) {
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
        if (_segs.isEmpty()) {
            attSeg_.addBackground(_tp.getBackgroundValue());
        }
        attSeg_.addForeground(_tp.getForegroundValue());
        attSeg_.addFontSize(_tp.getMetaFont().getRealSize());
        _tp.setCharacterAttributes(0,text_.length(),attSeg_,false);
        if (_fonts.contains(_tp)) {
            _tp.setCharacterAttributes(0, _tp.getText().length(), _fonts.getVal(_tp), false);
        }
    }

    public void scroll(AbsCustComponent _dual) {
        AbsCustComponent c_ = _dual;
        int x_ = 0;
        int y_ = 0;
        while (c_ != null) {
            AbsCustComponent par_ = parents.getVal(c_);
            if (par_ != null) {
                x_ += c_.getXcoords();
                y_ += c_.getYcoords();
            }
            c_ = par_;
        }
        scrollPane.setHorizontalValue(x_);
        scrollPane.setVerticalValue(y_);
    }

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
            AbsTextPane l_ =refsSearch.getVal(l.getKey());
            paint(api,l_,l.getValue(),fonts);
            labels.add(l_);
        }
        scroll(refsSearch.getVal(lab_));
    }
    private void clear() {
        for (AbsTextPane l: getLabels()) {
            paint(api,l,new CustList<SegmentPart>(),fonts);
        }
        labels.clear();
        for (EntryCust<AbsTextPane, AbsAttrSet> a: fonts.entryList()) {
            a.getKey().setCharacterAttributes(0, a.getKey().getText().length(), a.getValue(), false);
        }
    }

    public void setFinding(FindNextElement _f) {
        this.finding = _f;
    }

    public void setScrollPane(AbsScrollPane _s) {
        this.scrollPane = _s;
    }

    public void setParents(IdMap<AbsCustComponent, AbsCustComponent> _p) {
        this.parents = _p;
    }

    public void setRefsSearch(IdMap<MetaSearchableContent, AbsTextPane> _r) {
        this.refsSearch = _r;
    }

    public void setFonts(IdMap<AbsTextPane, AbsAttrSet> _f) {
        this.fonts = _f;
    }

    public CustList<AbsTextPane> getLabels() {
        return labels;
    }
}
