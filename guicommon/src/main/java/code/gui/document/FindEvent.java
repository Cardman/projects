package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import code.formathtml.render.FindNextElement;
import code.formathtml.render.MetaDocument;
import code.formathtml.render.MetaSearchableLabel;

public final class FindEvent extends MouseAdapter {

    private RenderedPage page;

    private FindNextElement finding;

    private JTextField field;

    public FindEvent(MetaDocument _document, JTextField _field, RenderedPage _page) {
        finding = new FindNextElement(_document);
        field = _field;
        page = _page;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        finding.next(field.getText());
        MetaSearchableLabel lab_ = finding.getLabel();
        if (lab_ == null) {
            return;
        }
        page.getRefs().getVal(lab_);
    }
}
