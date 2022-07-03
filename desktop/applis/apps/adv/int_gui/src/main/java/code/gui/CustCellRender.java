package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.CustList;

public abstract class CustCellRender<T> implements AbsCustCellRender {
    private CustList<T> list = new CustList<T>();
    private AbsGraphicList<T> listGr;
    public abstract AbstractImageFactory getImageFactory();
    public void paintComponent(AbsPreparedLabel _component) {
        AbstractImage buff_ = getImageFactory().newImageRgb(getWidth(),getHeight());
        buff_.setFont(_component);
        paintComponent(buff_);
        _component.setIcon(getImageFactory(),buff_);
    }

    public T get(int _index) {
        return list.get(_index);
    }

    @Override
    public void fwd() {
        setList(listGr.getList());
    }

    public void setList(CustList<T> _list) {
        this.list = new CustList<T>(_list);
    }

    public void setCurrent(AbsGraphicList<T> _graphicList) {
        listGr = _graphicList;
    }
}
