package code.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;

public class CustCellRender {
    public Component getListCellRendererComponent(GraphicList _list, Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus) {
        JLabel label_ = (JLabel) _list.getListComponents().get(_index);
        if (_isSelected) {
            LabelButtonUtil.setTextDefaultLabel(label_, String.valueOf(_value), Color.WHITE, Color.BLUE);
        } else {
            LabelButtonUtil.setTextDefaultLabel(label_, String.valueOf(_value), Color.BLACK, Color.WHITE);
        }
        return label_;
    }
    public void paintComponent(Component _component) {
    }
}
