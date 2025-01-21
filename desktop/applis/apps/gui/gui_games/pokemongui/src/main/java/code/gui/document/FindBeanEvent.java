package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class FindBeanEvent implements AbsActionListener {

    private final AbsBeanRender page;

    private FindNextElement finding;

    private final AbsTextField field;

    private final CustList<AbsTextPane> labels = new CustList<AbsTextPane>();
    private final AbstractProgramInfos api;

    public FindBeanEvent(AbsTextField _field, AbsBeanRender _page, AbstractProgramInfos _a) {
        field = _field;
        page = _page;
        api = _a;
    }
    public void setFinding(AbsBeanRender _document) {
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
            AbsBeanRender.paint(api,l_,l.getValue());
            labels.add(l_);
        }
        AbsBeanRender.scroll(page, page.getRefsSearch().getVal(lab_));
    }

    private void clear() {
        for (AbsTextPane l: getLabels()) {
            AbsBeanRender.paint(api,l,new CustList<SegmentPart>());
        }
        labels.clear();
    }

    public CustList<AbsTextPane> getLabels() {
        return labels;
    }
}
