package code.util;
import code.util.ints.Viewable;

public class ViewAdapter implements Viewable {

    private transient boolean modified;

    @Override
    public final void setModified() {
        modified = true;
    }

    @Override
    public final void setUnmodified() {
        modified = false;
    }

    @Override
    public final boolean isModified() {
        return modified;
    }

}
