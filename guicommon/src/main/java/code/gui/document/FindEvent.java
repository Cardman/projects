package code.gui.document;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import code.formathtml.render.FindNextElement;
import code.formathtml.render.MetaDocument;
import code.formathtml.render.MetaSearchableLabel;
import code.formathtml.render.SegmentPart;
import code.gui.ScrollPane;
import code.gui.TextField;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;

public final class FindEvent extends MouseAdapter {

    private RenderedPage page;

    private FindNextElement finding;

    private TextField field;

    private CustList<DualLabel> labels = new CustList<DualLabel>();

    public FindEvent(TextField _field, RenderedPage _page) {
        field = _field;
        page = _page;
    }
    public void setFinding(MetaDocument _document) {
        finding = new FindNextElement(_document);
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (page.isProcessing()) {
            return;
        }
        String text_ = field.getText();
        if (text_.isEmpty()) {
            for (DualLabel l: labels) {
                l.clearSegments();
                l.paint();
            }
            labels.clear();
            return;
        }
        finding.next(text_);
        MetaSearchableLabel lab_ = finding.getLabel();
        if (lab_ == null) {
            for (DualLabel l: labels) {
                l.clearSegments();
                l.paint();
            }
            labels.clear();
            return;
        }
        for (EntryCust<MetaSearchableLabel, CustList<SegmentPart>> l: finding.getSegments().entryList()) {
            DualLabel l_ = (DualLabel) page.getRefs().getVal(l.getKey());
            for (SegmentPart s: l.getValue()) {
                l_.addSegment(s);
            }
            l_.paint();
            labels.add(l_);
        }
        ScrollPane sc_ = page.getScroll();
        DualComponent r_ = page.getPage();
        int x_ = 0;
        int y_ = 0;
        DualComponent c_ = page.getRefs().getVal(lab_);
        while (c_ != r_) {
            x_ +=  c_.getGraphic().getXcoords();
            y_ +=  c_.getGraphic().getYcoords();
            c_ = c_.getContainer();
        }
        sc_.setHorizontalValue(x_);
        sc_.setVerticalValue(y_);
    }
}
