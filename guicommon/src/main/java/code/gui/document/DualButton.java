package code.gui.document;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaButton;
import code.gui.LabelButtonUtil;
import code.util.CustList;

public final class DualButton extends DualInput {

    private String value;

    public DualButton(DualContainer _container, MetaButton _component,
            RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        super(_container, _component, new JLabel(), _page);
        JLabel label_ = getGraphic();
        label_.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label_.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label_.addMouseListener(new FormEvent(_component.getForm(), this, _page, _anims));
        value = _component.getValue();
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public JLabel getGraphic() {
        return (JLabel) super.getGraphic();
    }

    @Override
    public String getValue() {
        return value;
    }

    public void paint() {
        JLabel lab_ = getGraphic();
        BufferedImage img_ = LabelButtonUtil.paintButton(lab_, value, true);
        lab_.setIcon(new ImageIcon(img_));
    }
}
