package code.formathtml.render;

import code.util.Ints;

public final class MetaTable extends MetaContainer {

    private final Ints remainders = new Ints();
    public MetaTable(MetaContainer _parent) {
        super(_parent, MetaLayout.BAG);
    }
    public void addRemainder(boolean _previous) {
        if (_previous) {
            remainders.add(getChildren().size() - 1);
        } else {
            remainders.add(getChildren().size());
        }
    }

    public int getDiff() {
        if (getFirstChild() instanceof MetaCaption) {
            int diff_ = 1;
            int r_ = 0;
            for (int i:remainders) {
                int l_ = i - r_+1;
                if (l_ > diff_) {
                    diff_ = l_;
                }
                r_ = i;
            }
            return diff_;
        }
        int diff_ = 1;
        int r_ = 0;
        for (int i:remainders) {
            int j_ = i + 1;
            int l_ = j_ - r_+1;
            if (l_ > diff_) {
                diff_ = l_;
            }
            r_ = j_;
        }
        return diff_;
    }
    public Ints getRemainders() {
        return remainders;
    }
}
