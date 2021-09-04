package code.sys.impl.variant;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.sys.impl.gui.CustComponent;
import code.sys.impl.gui.Panel;
import code.util.CustList;
import code.util.Ints;

import javax.swing.*;

public final class DefCustGrMultList extends CustComponent implements AbsInputGraphicList<String> {

    private final DefCustGrList<String> compo;

    public DefCustGrMultList(boolean _simple, AbstractImageFactory _fact) {
        compo = new DefCustGrList<String>(_simple, new DefaultCellRender(_fact, Panel.newPageBox()));
    }

    public void add(String _elt) {
        compo.add(_elt);
    }

    public void add(int _index, String _elt) {
        compo.add(_index, _elt);
    }

    @Override
    public void add(int _index, AbsPreparedLabel _lab, String _elt) {
        compo.add(_index, _lab, _elt);
    }

    @Override
    public int set(int _index, AbsPreparedLabel _lab, String _elt) {
        compo.set(_index,_lab, _elt);
        return _index;
    }

    @Override
    public void clear() {
        compo.clear();
    }

    @Override
    public void clearSelection() {
        compo.clearSelection();
    }

    @Override
    public void clearRevalidate() {
        compo.clearRevalidate();
    }

    @Override
    public int size() {
        return compo.size();
    }

    @Override
    public void remove(int _index) {
        compo.remove(_index);
    }

    @Override
    public void setListener(ListSelection _list) {
        compo.setListener(_list);
    }

    @Override
    public void set(int _index, String _elt) {
        compo.set(_index, _elt);
    }

    public void clearAllRange() {
        compo.clearSelection();
    }

    @Override
    public void setSelectedIndice(int _index) {
        compo.setSelectedIndice(_index);
    }

    @Override
    public void setVisibleRowCount(int _vis) {
        compo.setVisibleRowCount(_vis);
    }

    @Override
    public int getSelectedIndex() {
        return compo.getSelectedIndex();
    }

    @Override
    public boolean isSelectionEmpty() {
        return compo.isSelectionEmpty();
    }

    @Override
    public boolean isEmpty() {
        return compo.isEmpty();
    }

    @Override
    public AbsCustComponent getGlobal() {
        return compo.scroll();
    }

    public Ints getSelectedIndexes() {
        return compo.getSelectedIndexes();
    }


    public CustList<String> getList() {
        return compo.getList();
    }

    @Override
    public int getSelectedValuesLsLen() {
        return compo.getSelectedValuesLsLen();
    }
    public String get(int _i) {
        return compo.get(_i);
    }

    public String last() {
        return compo.last();
    }

    @Override
    public JComponent getNatComponent() {
        return compo.getNatComponent();
    }

    public AbsCustComponent self() {
        return compo.self();
    }

    @Override
    public AbsCustComponent scroll() {
        return compo.scroll();
    }

    @Override
    public AbsCustComponent visible() {
        return compo.visible();
    }

}
