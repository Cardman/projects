package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;

public final class FindBeanEvent implements AbsActionListener {

//    private BeanBuilderHelper page;
//
//    private FindNextElement finding;
//
//    private final AbsTextField field;
//
//    private final CustList<AbsTextPane> labels = new CustList<AbsTextPane>();
//    private final AbstractProgramInfos api;
    private final FindBeanEventContent content;

    public FindBeanEvent(AbsTextField _field, AbstractProgramInfos _a) {
//        field = _field;
//        api = _a;
        content = new FindBeanEventContent(_field, _a);
    }

    public void scroll(AbsCustComponent _dual) {
//        AbsCustComponent c_ = _dual;
//        AbsScrollPane sc_ = _rend.getScrollPane();
//        int x_ = 0;
//        int y_ = 0;
//        while (c_ != null) {
//            AbsCustComponent par_ = _rend.getParents().getVal(c_);
//            if (par_ != null) {
//                x_ += c_.getXcoords();
//                y_ += c_.getYcoords();
//            }
//            c_ = par_;
//        }
//        sc_.setHorizontalValue(x_);
//        sc_.setVerticalValue(y_);
        content.scroll(_dual);
    }

    public void setFinding(BeanBuilderHelper _document) {
//        page = _document;
//        finding = new FindNextElement(_document.getMetaSearchableContents());
        content.setRefsSearch(_document.getRefsSearch());
        content.setFonts(_document.getFonts());
        content.setScrollPane(_document.getScrollPane());
        content.setParents(_document.getParents());
        content.setFinding(new FindNextElement(_document.getMetaSearchableContents()));
    }
    @Override
    public void action() {
        content.action();
//        String text_ = field.getText();
//        if (text_.isEmpty()) {
//            clear();
//            return;
//        }
//        finding.next(text_);
//        MetaSearchableContent lab_ = finding.getLabel();
//        if (lab_ == null) {
//            clear();
//            return;
//        }
//        for (EntryCust<MetaSearchableContent, CustList<SegmentPart>> l: finding.getSegments().entryList()) {
//            AbsTextPane l_ = page.getRefsSearch().getVal(l.getKey());
//            WindowPage.paint(api,l_,l.getValue(),page.getFonts());
//            labels.add(l_);
//        }
//        scroll(page, page.getRefsSearch().getVal(lab_));
    }

//    private void clear() {
//        for (AbsTextPane l: getLabels()) {
//            WindowPage.paint(api,l,new CustList<SegmentPart>(),page.getFonts());
//        }
//        labels.clear();
//        for (EntryCust<AbsTextPane, AbsAttrSet> a: page.getFonts().entryList()) {
//            a.getKey().setCharacterAttributes(0, a.getKey().getText().length(), a.getValue(), false);
//        }
//    }

//    public CustList<AbsTextPane> getLabels() {
//        return content.getLabels();
//    }
}
