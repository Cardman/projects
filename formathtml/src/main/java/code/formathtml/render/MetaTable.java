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
    public boolean isRemainder(int _index) {
        return remainders.containsObj(_index);
    }
    public Ints getRemainders() {
        return remainders;
    }
}
