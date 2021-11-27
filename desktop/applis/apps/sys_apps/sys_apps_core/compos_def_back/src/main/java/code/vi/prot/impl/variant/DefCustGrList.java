package code.vi.prot.impl.variant;

import code.adv.BoolIntChoiceImpl;
import code.adv.BoolIntChoiceUtil;
import code.gui.*;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.ScrollPane;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;

import javax.swing.*;

public class DefCustGrList<T> extends CustComponent implements AbsGraphicList<T>, AbsGraphicListDef {

    private final DefaultListModel modelDef = new DefaultListModel();
    private final JList listDef = new JList(modelDef);
    private final JScrollPane scrollDef = new JScrollPane(listDef);
    private final ScrollPane custScrollDef = new ScrollPane(scrollDef);
    private final CustList<T> eltsDef = new CustList<T>();
    private final IdMap<ListSelection,DefLocalListSelectionListener> listenersDef = new IdMap<ListSelection,DefLocalListSelectionListener>();

    public DefCustGrList(boolean _simple, CustCellRender<T> _render) {
        setup(BoolIntChoiceUtil.choice(new BoolIntChoiceImpl(_simple),
                ListSelectionModel.SINGLE_SELECTION,
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        DefCustSelList<T> custSelList_ = new DefCustSelList<T>(eltsDef,_render);
        listDef.setCellRenderer(custSelList_);
    }

    protected DefCustGrList(boolean _simple,ListCellRenderer _render) {
        setup(BoolIntChoiceUtil.choice(new BoolIntChoiceImpl(_simple),
                ListSelectionModel.SINGLE_SELECTION,
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
        listDef.setCellRenderer(_render);
    }
    private void setup(int _value) {
        listDef.setSelectionMode(_value);
    }
    public void setListener(ListSelection _listener) {
        FrameUtil.removeListeners(this);
        listenersDef.clear();
        addListener(_listener);
    }

    public CustList<ListSelection> getListeners() {
        return listenersDef.getKeys();
    }

    public void addListener(ListSelection _listener) {
        DefLocalListSelectionListener loc_ = new DefLocalListSelectionListener(_listener);
        listDef.addListSelectionListener(loc_);
        listenersDef.addEntry(_listener,loc_);
    }

    public void removeListener(ListSelection _listener) {
        DefLocalListSelectionListener val_ = listenersDef.getVal(_listener);
        listDef.removeListSelectionListener(val_);
        listenersDef.removeKey(_listener);
    }

    public JList getListView() {
        return listDef;
    }
    public void add(T _elt) {
        add(eltsDef.size(),_elt);
    }

    @Override
    public void add(int _index, AbsPreparedLabel _lab, T _elt) {
        eltsDef.add(_index, _elt);
        modelDef.add(_index, _elt);
    }

    public void add(int _index, T _elt) {
        eltsDef.add(_index, _elt);
        modelDef.add(_index, _elt);
    }

    @Override
    public void set(int _index, T _elt) {
        eltsDef.set(_index, _elt);
        modelDef.set(_index, _elt);
    }

    @Override
    public int set(int _index, AbsPreparedLabel _lab, T _elt) {
        eltsDef.set(_index, _elt);
        modelDef.set(_index, _elt);
        return _index;
    }

    public void clear() {
        eltsDef.clear();
        modelDef.clear();
    }

    public void clearRevalidate() {
        clear();
        custScrollDef.revalidate();
    }
    public void remove(int _index) {
        modelDef.remove(_index);
        eltsDef.remove(_index);
    }

    public int getVisibleRowCount() {
        return listDef.getVisibleRowCount();
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        listDef.setVisibleRowCount(_visibleRowCount);
    }

    public void clearAllRange() {
        listDef.clearSelection();
    }

    public void setSelectedIndice(int _min) {
        listDef.setSelectedIndex(_min);
    }
    public void clearSelection() {
        listDef.clearSelection();
    }

    public Ints getSelectedIndexes() {
        return FrameUtil.toList(listDef.getSelectedIndices());
    }

    public void setSelectedIndexes(Ints _values) {
        listDef.setSelectedIndices(FrameUtil.toList(_values));
    }


    public CustList<T> getList() {
        return eltsDef;
    }

    public int getSelectedIndex() {
        return listDef.getSelectedIndex();
    }

    @Override
    public int getSelectedValuesLsLen() {
        return listDef.getSelectedIndices().length;
    }

    public T get(int _i) {
        return eltsDef.get(_i);
    }

    public boolean isEmpty() {
        return eltsDef.isEmpty();
    }

    public T last() {
        return eltsDef.last();
    }

    public boolean isSelectionEmpty() {
        return listDef.isSelectionEmpty();
    }

    public int size() {
        return eltsDef.size();
    }

    public boolean isEnabled() {
        return listDef.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        listDef.setEnabled(_enabled);
    }

    @Override
    public JComponent getNatComponent() {
        return custScrollDef.getNatComponent();
    }

    public AbsCustComponent self() {
        return this;
    }

    @Override
    public AbsCustComponent scroll() {
        return custScrollDef;
    }

    @Override
    public AbsCustComponent visible() {
        return this;
    }

}
