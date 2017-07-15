package code.gui;
import javax.swing.DefaultListModel;

public class CustListModel<T> extends DefaultListModel {


    public static final int SWAP_SORT = 1;

    public static final int NO_SWAP_SORT = -1;

    public static final int EQ_CMP = 0;

    public static final int INDEX_NOT_FOUND_ELT = -1;

    public static final int FIRST_INDEX = 0;

    public static final int SECOND_INDEX = 1;

    public static final int ONE_ELEMENT = 1;

    public static final int SIZE_EMPTY = 0;

    public Object getObj(int _index) {
        return super.get(_index);
    }

    public int indexOfNull(int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            if (super.get(i) == null) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
}
