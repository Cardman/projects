package code.sml;

public class IndexesFormInput {

    private long anchor;

    public static void incr(IndexesFormInput _indexes) {
        long currentAnchor_ = _indexes.getAnchor();
        currentAnchor_++;
        _indexes.setAnchor(currentAnchor_);
    }

    public long getAnchor() {
        return anchor;
    }

    public void setAnchor(long _anchor) {
        anchor = _anchor;
    }
}
