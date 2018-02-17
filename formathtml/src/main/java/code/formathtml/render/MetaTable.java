package code.formathtml.render;

import code.util.Numbers;

public final class MetaTable extends MetaContainer {

    private final Numbers<Integer> remainders = new Numbers<Integer>();
    public MetaTable(MetaContainer _parent) {
        super(_parent, MetaLayout.BAG);
    }
    public void addRemainder() {
        remainders.add(getChildren().size());
    }
    public boolean isRemainder(int _index) {
        return remainders.containsObj(_index);
    }
    public Numbers<Integer> getRemainders() {
        return remainders;
    }
}
