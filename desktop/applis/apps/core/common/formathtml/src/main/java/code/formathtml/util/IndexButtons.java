package code.formathtml.util;

import code.util.CustList;

public final class IndexButtons {
    private final CustList<IndexButton> buttons = new CustList<IndexButton>();

    public int addOrIncr(FormInputCoords _input) {
        for (IndexButton i: buttons) {
            if (_input.eq(i.getInputCoords())) {
                i.incr();
                return i.getCount();
            }
        }
        buttons.add(new IndexButton(_input));
        return 0;
    }
}
