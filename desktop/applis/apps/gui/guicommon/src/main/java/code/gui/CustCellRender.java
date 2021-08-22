package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.CustList;

public abstract class CustCellRender<T> {
    private CustList<T> list = new CustList<T>();
    protected abstract AbstractImageFactory getImageFactory();
    public abstract void getListCellRendererComponent(PreparedLabel _currentLab,
                                                      int _index, boolean _isSelected, boolean _cellHasFocus);
    public abstract int getHeight();
    public abstract int getWidth();
    public abstract void paintComponent(AbstractImage _g);
    public void paintComponent(PreparedLabel _component) {
        AbstractImage buff_ = getImageFactory().newImageRgb(getWidth(),getHeight());
        buff_.setFont(_component.getFont());
        paintComponent(buff_);
        _component.setIcon(getImageFactory(),buff_);
    }

    public CustList<T> getList() {
        return list;
    }

    public void setList(CustList<T> _list) {
        this.list = new CustList<T>(_list);
    }
}
