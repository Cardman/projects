package code.gui;

import java.awt.Color;

import javax.swing.JLabel;

public class CustCellRender {
    public JLabel getListCellRendererComponent(GraphicListable _list, Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus) {
        JLabel label_ = (JLabel) _list.getListComponents().get(_index);
        int w_ = _list.getMaxWidth();
        if (_isSelected) {
            LabelButtonUtil.setTextDefaultLabel(label_, String.valueOf(_value), w_, Color.WHITE, Color.BLUE);
        } else {
            LabelButtonUtil.setTextDefaultLabel(label_, String.valueOf(_value), w_, Color.BLACK, Color.WHITE);
        }
        return label_;
    }
    public void paintComponent(JLabel _component) {
    }
}
