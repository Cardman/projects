package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import code.util.CustList;

public class AnimatedLabel extends PaintableLabel {

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
        index++;
        if (index >= list.size()) {
            index = 0;
        }
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (index >= list.size()) {
            return;
        }
        _g.drawImage(list.get(index), 0, 0);
    }
}
