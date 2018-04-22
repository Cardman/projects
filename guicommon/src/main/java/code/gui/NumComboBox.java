package code.gui;
import code.util.Numbers;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;


public class NumComboBox extends TreeComboBox<Integer> {

    public NumComboBox() {
        super(new TreeMap<Integer, String>(new NaturalComparator<Integer>()));
    }
    public NumComboBox(Integer... _numerosPlis) {
        super(getTree(_numerosPlis));
    }

//    public void setModel(Integer... _numerosPlis) {
//        super.setModel(new DefaultComboBoxModel(_numerosPlis));
//        refresh(getTree(_numerosPlis));
//    }

    public void addItem(Integer _item) {
        getElements().put(_item, _item.toString());
        super.addItem(_item.toString());
    }

    private static TreeMap<Integer, String> getTree(Integer... _ints) {
        TreeMap<Integer, String> tr_;
        tr_ = new TreeMap<Integer, String>(new NaturalComparator<Integer>());
        for (Integer i: _ints) {
            tr_.put(i, i.toString());
        }
        return tr_;
    }
    public void setSelectedItem(int _i) {
        int index_ = 0;
        for (Integer k: getElements().getKeys()) {
            if (Numbers.eq(k.intValue(), _i)) {
                selectItem(index_);
                break;
            }
            index_++;
        }
    }
}
