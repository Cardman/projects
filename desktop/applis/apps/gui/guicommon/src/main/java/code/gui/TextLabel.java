package code.gui;

import javax.swing.*;

public final class TextLabel extends CustComponent {

    private final JLabel label = new JLabel();
    private String text;

    public TextLabel(String _text) {
        label.putClientProperty("html.disable", true);
        text = _text;
//        int w_ = stringWidth(text)+2;
        JLabel lab_ = label;
        lab_.setBackground(getBackground());
        lab_.setForeground(getForeground());
        lab_.setText(_text);

//        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(this, text, w_, getForeground(), getBackground());
//        lab_.setIcon(new ImageIcon(img_));
    }

    public TextLabel(String _titre, int _align) {
        this(_titre);
        label.setHorizontalAlignment(_align);
    }

    @Override
    protected JComponent getNatComponent() {
        return label;
    }

    //    @Override
//    public void paintComponent(AbstractImage _g2) {
//        int h_ = heightFont();
//        int w_ = stringWidth(text);
//        LabelButtonUtil.paintDefaultLabel(_g2, text, w_, getWidth(), h_, getForeground(), getBackground());
//    }

    @Override
    public int getHeight() {
        return heightFont();
    }

    @Override
    public int getWidth() {
        return stringWidth(text)+2;
    }

    public void setText(String _simpleNumberFormat) {
        text = _simpleNumberFormat;
        JLabel lab_ =label;
        lab_.setBackground(getBackground());
        lab_.setForeground(getForeground());
        lab_.setText(_simpleNumberFormat);
//        int w_ = stringWidth(text)+2;
//        JLabel lab_ = getLabel();
//        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(this, text, w_, getForeground(), getBackground());
//        lab_.setIcon(new ImageIcon(img_));
    }

    public String getText() {
        return text;
    }

    public void setHorizontalAlignment(int _val) {
        label.setHorizontalAlignment(_val);
    }
}
