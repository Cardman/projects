package code.gui;

import code.util.CustList;
import code.util.Ints;

import javax.swing.*;

public final class CustGrMultList extends CustComponent implements AbsInputGraphicList<String> {

    private final CustGrList<String> compo;

    public CustGrMultList(boolean _simple) {
        compo = new CustGrList<>(_simple);
    }
    public ListSelection getListener() {
        return compo.getListener();
    }

    public void setListener(ListSelection _listener) {
        compo.setListener(_listener);
    }

    public CustCellRender<String> getRender() {return compo.getRender();}
    public void setRender(CustCellRender<String> _render) {
        compo.setRender(_render);
    }

    public void add(String _elt) {
        compo.add(_elt);
    }
    public void add(int _index, String _elt) {
        compo.add(_index, _elt);
    }

    @Override
    public void addLab(int _index, PreparedLabel _lab) {
        //
    }

    @Override
    public void set(int _index, PreparedLabel _lab, String _elt) {
        compo.set(_index,_lab, _elt);
    }

    @Override
    public void set(int _index, String _elt) {
        compo.set(_index, _elt);
    }

    @Override
    public boolean selectOneAmongIntervalPaint(boolean _sel, int _index) {
        return compo.selectOneAmongIntervalPaint(_sel, _index);
    }

    @Override
    public Interval selectIntervalKeyPaint(boolean _sel, int _index) {
        return null;
    }

    @Override
    public Interval selectIntervalPaint(boolean _sel, int _index) {
        return null;
    }

    @Override
    public Interval selectIntervalPaintBase(boolean _sel, int _index) {
        return null;
    }

    @Override
    public void selectOneAmongIntervalPaintBase(boolean _sel, int _index) {
        //
    }

    public void clear() {
        compo.clear();
    }

    @Override
    public void simpleAddLab(int _index, PreparedLabel _lab) {
        //
    }

    @Override
    public void addListeners(int _index, PreparedLabel _lab) {
        //
    }

    @Override
    public void updateGraphics() {
        //
    }

    public void clearRevalidate() {
        compo.clearRevalidate();
    }
    public void remove(int _index) {
        compo.remove(_index);
    }

    public int getVisibleRowCount() {
        return compo.getVisibleRowCount();
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        compo.setVisibleRowCount(_visibleRowCount);
    }

    public void clearAllRange() {
        compo.clearSelection();
    }

    public void setSelectedIndice(int _min) {
        compo.setSelectedIndice(_min);
    }
    public void clearSelection() {
        compo.clearSelection();
    }

    @Override
    public CustComponent getGlobal() {
        return compo.scroll();
    }

    public Ints getSelectedIndexes() {
		return compo.getSelectedIndexes();
    }

    @Override
    public CustList<PreparedLabel> getListComponents() {
        return compo.getListComponents();
    }

    @Override
    public void setSelectedIndexes(Ints _values) {
        compo.setSelectedIndexes(_values);
    }


    public CustList<String> getList() {
        return compo.getList();
    }

    public int getSelectedIndex() {
        return compo.getSelectedIndex();
    }

    public String getSelectedValue() {
        return compo.getSelectedValue();
    }

    public CustList<String> getSelectedValuesLs() {
        return compo.getSelectedValuesLs();
    }
    public String get(int _i) {
        return compo.get(_i);
    }

    public boolean isEmpty() {
        return compo.isEmpty();
    }

    public String last() {
        return compo.last();
    }

    public boolean isSelectionEmpty() {
        return compo.isSelectionEmpty();
    }

    public int size() {
        return compo.size();
    }

    @Override
    public boolean isEnabled() {
        return compo.isEnabled();
    }

    @Override
    public void setEnabled(boolean _enabled) {
        compo.setEnabled(_enabled);
    }

    @Override
    protected JComponent getComponent() {
        return compo.getComponent();
    }

    public CustComponent self() {
        return compo.self();
    }

    @Override
    public CustComponent scroll() {
        return compo.scroll();
    }

    @Override
    public CustComponent visible() {
        return compo.visible();
    }

}