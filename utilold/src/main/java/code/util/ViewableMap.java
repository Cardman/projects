package code.util;
import code.util.ints.Viewable;

public abstract class ViewableMap<K, V> extends AbsMap<K, V> implements Viewable {

    private boolean modified;

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
