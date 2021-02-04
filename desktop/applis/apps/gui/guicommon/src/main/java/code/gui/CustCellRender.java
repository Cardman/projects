package code.gui;

import code.util.CustList;

import java.awt.image.BufferedImage;

public abstract class CustCellRender<T> {
    private CustList<T> list = new CustList<T>();
    public abstract void getListCellRendererComponent(PreparedLabel _currentLab,
                                                      int _index, boolean _isSelected, boolean _cellHasFocus);
    public abstract int getHeight();
    public abstract int getWidth();
    public abstract void paintComponent(CustGraphics _g);
    public void paintComponent(PreparedLabel _component) {
        BufferedImage buff_ = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(buff_.getGraphics());
        gr_.setFont(_component.getFont());
        paintComponent(gr_);
        _component.setIcon(buff_);
    }

    public CustList<T> getList() {
        return list;
    }

    public void setList(CustList<T> _list) {
        this.list = new CustList<T>(_list);
    }
}
