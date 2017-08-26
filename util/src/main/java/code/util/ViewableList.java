package code.util;
import code.util.ints.Viewable;

public final class ViewableList<T> extends CustList<T> implements Viewable {

    private transient boolean modified;

//    public boolean removeAll(List<T> _list) {
//        setModified();
//        return super.removeAll(_list);
//    }
//    public boolean retainAll(List<T> _c) {
//        setModified();
//        return super.retainAll(_c);
//    }

    @Override
    public void add(int _index, T _element) {
        setModified();
        super.add(_index, _element);
    }

    @Override
    public void add(T _e) {
        setModified();
        super.add(_e);
    }

    @Override
    public void clear() {
        setModified();
        super.clear();
    }

    @Override
    public void remove(int _index) {
        setModified();
        super.remove(_index);
    }

    @Override
    public void set(int _index, T _element) {
        setModified();
        super.set(_index, _element);
    }

    @Override
    public void setModified() {
        modified = true;
    }

    @Override
    public void setUnmodified() {
        modified = false;
    }

    @Override
    public boolean isModified() {
        return modified;
    }
}
