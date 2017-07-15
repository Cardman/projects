package code.gui;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;


public class StringComboBox extends TreeComboBox<String> {

    public StringComboBox() {
        super(new TreeMap<String,String>(new NaturalComparator<String>()));
    }
//    public StringComboBox(String... _numerosPlis) {
//        super(_numerosPlis);
//    }

    public void addItem(String _anObject) {
        getElements().put(_anObject, _anObject);
        super.addItem(_anObject);
    }

}
