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

//    @Override
//    public boolean addAll(Collection<? extends T> _c) {
//        setModified();
//        return super.addAll(_c);
//    }

//    @Override
//    public boolean addAll(int _index, Collection<? extends T> _c) {
//        setModified();
//        return super.addAll(_index, _c);
//    }

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

//    @Override
//    public boolean removeAll(Collection<?> _c) {
//        setModified();
//        return super.removeAll(_c);
//    }

//    @Override
//    protected void removeRange(int _fromIndex, int _toIndex) {
//        setModified();
//        super.removeRange(_fromIndex, _toIndex);
//    }

//    @Override
//    public boolean remove(Object _o) {
//        setModified();
//        return super.remove(_o);
//    }

//    @Override
//    public boolean retainAll(Collection<?> _c) {
//        setModified();
//        return super.retainAll(_c);
//    }

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
