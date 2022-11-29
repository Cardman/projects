package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.CustList;

public abstract class CustCellRender<T> implements AbsCustCellRender {
    private CustList<T> list = new CustList<T>();
    private AbsGraphicList<T> listGr;
    public abstract AbstractImageFactory getImageFactory();
    public void paintComponent(AbsPreparedLabel _component) {
        AbstractImage buff_ = getImageFactory().newImageRgb(getWidth(),getHeight(),_component);
        paintComponent(buff_);
        getImageFactory().setIcon(_component,buff_);
    }

    public T get(int _index) {
        return list.get(_index);
    }

    public void setList(CustList<T> _list) {
        this.list = new CustList<T>(_list);
    }

    public AbsGraphicList<T> getListGr() {
        return listGr;
    }

    public void setCurrent(AbsGraphicList<T> _graphicList) {
        listGr = _graphicList;
    }
}
