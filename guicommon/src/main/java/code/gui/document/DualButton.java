package code.gui.document;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;

import javax.swing.*;

import code.formathtml.render.MetaButton;
import code.gui.LabelButtonUtil;

public final class DualButton extends DualInput {

    private String value;
    private JLabel label;

    public DualButton(DualContainer _container, MetaButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = new JLabel();
        updateGraphics(label,_component);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new FormEvent(_component.getForm(), this, _page));
        value = _component.getValue();
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public JComponent getGraphic() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void paint() {
        BufferedImage img_ = LabelButtonUtil.paintButton(label, value, true);
        label.setIcon(new ImageIcon(img_));
    }
}
