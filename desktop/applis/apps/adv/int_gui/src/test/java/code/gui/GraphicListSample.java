package code.gui;

import code.util.CustList;
import code.util.Ints;

public class GraphicListSample implements AbsGraphicList<String>{
    @Override
    public void add(String _elt) {

    }

    @Override
    public void add(int _index, AbsPreparedLabel _lab, String _elt) {

    }

    @Override
    public void add(int _index, String _elt) {

    }

    @Override
    public void set(int _index, String _elt) {

    }

    @Override
    public int set(int _index, AbsPreparedLabel _lab, String _elt) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearSelection() {

    }

    @Override
    public void clearRevalidate() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void remove(int _index) {

    }

    @Override
    public void setListener(ListSelection _list) {

    }

    @Override
    public void clearAllRange() {

    }

    @Override
    public void setSelectedIndice(int _index) {

    }

    @Override
    public void setVisibleRowCount(int _vis) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public boolean isSelectionEmpty() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public CustList<String> getList() {
        return new CustList<String>("");
    }

    @Override
    public int getSelectedValuesLsLen() {
        return 0;
    }

    @Override
    public String get(int _i) {
        return null;
    }

    @Override
    public String last() {
        return null;
    }

    @Override
    public AbsCustComponent self() {
        return null;
    }

    @Override
    public AbsCustComponent scroll() {
        return null;
    }

    @Override
    public AbsCustComponent visible() {
        return null;
    }

    @Override
    public Ints getSelectedIndexes() {
        return null;
    }
}
