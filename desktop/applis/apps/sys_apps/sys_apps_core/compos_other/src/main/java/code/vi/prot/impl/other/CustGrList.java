package code.vi.prot.impl.other;
import code.gui.*;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.ScrollPane;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import javax.swing.*;

public class CustGrList<T> extends CustComponent implements AbsGraphicList<T>,AbsGraphicListDef {

	private final DefaultListModel<T> model = new DefaultListModel<>();
	private final JList<T> list = new JList<>(model);
	private final JScrollPane scroll = new JScrollPane(list);
	private final ScrollPane custScroll = new ScrollPane(scroll);
	private final CustList<T> elts = new CustList<>();
	private final IdMap<ListSelection,LocalListSelectionListener> listeners = new IdMap<>();

    public CustGrList(int _simple,CustCellRender<T> _render) {
        setup(_simple);
        CustSelList<T> custSelList_ = new CustSelList<>(elts,_render);
        list.setCellRenderer(custSelList_);
    }

    protected CustGrList(int _simple,ListCellRenderer<T> _render) {
        setup(_simple);
        list.setCellRenderer(_render);
    }
    private void setup(int _value) {
        list.setSelectionMode(_value);
    }
    public void setListener(ListSelection _listener) {
        FrameUtil.removeListeners(this);
        listeners.clear();
        addListener(_listener);
    }

    public CustList<ListSelection> getListeners() {
        return listeners.getKeys();
    }

    public void addListener(ListSelection _listener) {
        LocalListSelectionListener loc_ = new LocalListSelectionListener(_listener);
        list.addListSelectionListener(loc_);
        listeners.addEntry(_listener,loc_);
    }

    public void removeListener(ListSelection _listener) {
        LocalListSelectionListener val_ = listeners.getVal(_listener);
        list.removeListSelectionListener(val_);
        listeners.removeKey(_listener);
    }

    public JList<T> getListView() {
        return list;
    }
    public void add(T _elt) {
        add(elts.size(),_elt);
    }

    @Override
    public void add(int _index, AbsPreparedLabel _lab, T _elt) {
        elts.add(_index, _elt);
        model.add(_index, _elt);
    }

    public void add(int _index, T _elt) {
        elts.add(_index, _elt);
		model.add(_index, _elt);
    }

    @Override
    public void set(int _index, T _elt) {
        elts.set(_index, _elt);
        model.set(_index, _elt);
    }

    @Override
    public int set(int _index, AbsPreparedLabel _lab, T _elt) {
        elts.set(_index, _elt);
        model.set(_index, _elt);
        return _index;
    }

    public void clear() {
        elts.clear();
        model.clear();
    }

    public void clearRevalidate() {
        clear();
        custScroll.revalidate();
    }
    public void remove(int _index) {
        model.remove(_index);
        elts.remove(_index);
    }

    public int getVisibleRowCount() {
        return list.getVisibleRowCount();
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        list.setVisibleRowCount(_visibleRowCount);
    }

    public void clearAllRange() {
        list.clearSelection();
    }

    public void setSelectedIndice(int _min) {
        list.setSelectedIndex(_min);
    }
    public void clearSelection() {
		list.clearSelection();
    }

    public Ints getSelectedIndexes() {
		return FrameUtil.toList(list.getSelectedIndices());
    }

    public void setSelectedIndexes(Ints _values) {
        list.setSelectedIndices(FrameUtil.toList(_values));
    }


    public CustList<T> getList() {
        return elts;
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    @Override
    public int getSelectedValuesLsLen() {
        return list.getSelectedIndices().length;
    }

    public T get(int _i) {
        return elts.get(_i);
    }

    public boolean isEmpty() {
        return elts.isEmpty();
    }

    public T last() {
        return elts.last();
    }

    public boolean isSelectionEmpty() {
        return list.isSelectionEmpty();
    }

    public int size() {
        return elts.size();
    }

    public boolean isEnabled() {
        return list.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        list.setEnabled(_enabled);
    }

    @Override
    public AbsCustComponent getGlobal() {
        return scroll();
    }

    @Override
    public JComponent getNatComponent() {
        return custScroll.getNatComponent();
    }

    public AbsCustComponent self() {
        return this;
    }

    @Override
    public AbsCustComponent scroll() {
        return custScroll;
    }

    @Override
    public AbsCustComponent visible() {
        return this;
    }

}