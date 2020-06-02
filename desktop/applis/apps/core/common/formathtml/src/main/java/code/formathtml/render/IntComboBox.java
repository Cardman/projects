package code.formathtml.render;

import code.util.Ints;

public interface IntComboBox extends IntInput {
    Ints getSelectedIndexes();
    String getValue(int _index);
}
