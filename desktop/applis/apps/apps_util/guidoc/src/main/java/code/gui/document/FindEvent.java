package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.EntryCust;

public final class FindEvent implements AbsActionListener {

    private final RenderedPage page;

    private FindNextElement finding;

    private final AbsTextField field;

    private final CustList<DualLabel> labels = new CustList<DualLabel>();

    public FindEvent(AbsTextField _field, RenderedPage _page) {
        field = _field;
        page = _page;
    }
    public void setFinding(MetaDocument _document) {
        finding = new FindNextElement(_document.getTxtParts());
    }
    @Override
    public void action() {
        if (page.isProcessing()) {
            return;
        }
        String text_ = field.getText();
        if (text_.isEmpty()) {
            for (DualLabel l: labels) {
                l.clearSegments();
                DualComponent.paintLabel(l);
            }
            labels.clear();
            return;
        }
        finding.next(text_);
        MetaSearchableContent lab_ = finding.getLabel();
        if (lab_ == null) {
            for (DualLabel l: labels) {
                l.clearSegments();
                DualComponent.paintLabel(l);
            }
            labels.clear();
            return;
        }
        for (EntryCust<MetaSearchableContent, CustList<SegmentPart>> l: finding.getSegments().entryList()) {
            DualLabel l_ = (DualLabel) page.getRefsSearch().getVal(l.getKey());
            for (SegmentPart s: l.getValue()) {
                l_.addSegment(s);
            }
            DualComponent.paintLabel(l_);
            labels.add(l_);
        }
        RenderedPage.scroll(page, page.getRefsSearch().getVal(lab_));
    }

    public CustList<DualLabel> getLabels() {
        return labels;
    }
}
