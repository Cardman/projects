package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import code.util.CustList;

public class AnimatedLabel extends JLabel {

    private CustList<BufferedImage> list = new CustList<BufferedImage>();

    private int index;


    public void setList(CustList<BufferedImage> _list) {
        list = _list;
        if (!list.isEmpty()) {
            setPreferredSize(new Dimension(list.first().getWidth(), list.first().getHeight()));
        }
    }

    public void increment() {
        repaint();
        index ++;
        if (index >= list.size()) {
            index = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (index >= list.size()) {
            return;
        }
        _g.drawImage(list.get(index), 0, 0, null);
    }
}
