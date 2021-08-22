package code.gui.document;

import java.awt.Color;
import java.awt.Cursor;

import code.formathtml.render.MetaButton;
import code.gui.CustComponent;
import code.gui.PlainButton;

public final class DualButton extends DualInput {

    private final String value;
    private final PlainButton label;

    public DualButton(DualContainer _container, MetaButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = new PlainButton();
        updateGraphics(label,_component);
        label.setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new FormEvent(this, _page));
        value = _component.getValue();
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public CustComponent getGraphic() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void paint() {
//        BufferedImage img_ = LabelButtonUtil.paintButton(label, value, true);
//        label.setIcon(img_);
        label.setText(value);
    }
}
