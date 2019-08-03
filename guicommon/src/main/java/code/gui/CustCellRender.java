package code.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class CustCellRender {
    public abstract PreparedLabel getListCellRendererComponent(GraphicListable _list, Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus);
    public abstract int getHeight();
    public abstract int getWidth();
    public void paintComponent(Graphics _g) {
    }
    public void paintComponent(PreparedLabel _component) {
        BufferedImage buff_ = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        paintComponent(buff_.getGraphics());
        _component.setIcon(new ImageIcon(buff_));
    }
}
