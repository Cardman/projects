package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.CustList;

public abstract class CustCellRender<T> implements AbsCustCellRender {
    private CustList<T> list = new CustList<T>();
    private AbsGraphicList<T> listGr;
    protected abstract AbstractImageFactory getImageFactory();
    public void paintComponent(AbsPreparedLabel _component) {
        AbstractImage buff_ = getImageFactory().newImageRgb(getWidth(),getHeight());
        buff_.setFont(_component.getFont());
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

    public AbsGraphicList<T> getListGr() {
        return listGr;
    }

    public void setList(CustList<T> _list) {
        this.list = new CustList<T>(_list);
    }

    public void setCurrent(AbsGraphicList<T> _graphicList) {
        listGr = _graphicList;
    }
}
