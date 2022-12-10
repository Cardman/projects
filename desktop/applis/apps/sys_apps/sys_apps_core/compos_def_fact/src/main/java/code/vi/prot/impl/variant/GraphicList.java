package code.vi.prot.impl.variant;



import javax.swing.*;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.util.core.NumberUtil;
import code.vi.prot.impl.gui.CustComponent;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.gui.ScrollPane;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public class GraphicList<T> extends CustComponent implements AbsGraphicListCommon, AbsGraphicList<T> {

    private final AbsGraphicListPainter graphicListPainter;
    private CustList<T> list;
    private final CustList<AbsPreparedLabel> listComponents = new CustList<AbsPreparedLabel>();
    private final CustList<IndexableListener> indexableMouse = new CustList<IndexableListener>();
    private final CustList<IndexableListener> indexableKey = new CustList<IndexableListener>();
    private final Ints selectedIndexes;

    private final CustCellRender<T> render;

    private ListSelection listener;

    private final AbsPanel panel;
    private final ScrollPane scroll;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private final boolean simple;

    private int visibleRowCount = 8;

    private boolean enabled = true;

    public GraphicList(boolean _simple, AbsGraphicListPainter _graphicListPainter, CustCellRender<T> _render) {
        selectedIndexes = new Ints();
        list = new CustList<T>();
        simple = _simple;
        panel = Panel.newPageBox();
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
        graphicListPainter = _graphicListPainter;
        render = init(_render);
        rebuild();
    }

    protected GraphicList(boolean _simple, Ints _selectedIndexes, StringList _ls, CustList<T> _objects, AbsGraphicListPainter _graphicListPainter, CustCellRender<T> _render) {
        selectedIndexes = new Ints(_selectedIndexes);
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = ((DefaultCellRender)_render).getPanel();
        ((DefaultCellRender)_render).setMaxWidth(FrameUtil.maxWidth(panel,_ls,((DefaultCellRender)_render).getCompo()));
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
        graphicListPainter = _graphicListPainter;
        render = init(_render);
    }

    protected GraphicList(AbstractImageFactory _fact, boolean _simple, Ints _selectedIndexes,StringList _ls,  CustList<T> _objects, int _visible, CustCellRender<T> _render) {
        selectedIndexes = new Ints(_selectedIndexes);
        visibleRowCount = _visible;
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = ((DefaultCellRender)_render).getPanel();
        ((DefaultCellRender)_render).setMaxWidth(FrameUtil.maxWidth(panel,_ls,((DefaultCellRender)_render).getCompo()));
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
        graphicListPainter = new DefaultGraphicListPainter(_fact);
        render = init(_render);
    }

    private CustCellRender<T> init(CustCellRender<T> _render) {
        _render.setCurrent(this);
        return _render;
    }

    protected void setList(CustList<T> _list) {
        list = _list;
    }
    public void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction) {
        ListSelection listener_ = getListener();
        FrameUtil.selectEvent(_firstIndex, _lastIndex, _methodAction, listener_);
    }

    public void add(T _elt) {
        add(list.size(),_elt);
    }
    public void add(int _index, T _elt) {
        list.add(_index, _elt);
        AbsPreparedLabel lab_ = GuiBaseUtil.prep(graphicListPainter.getFact());
        addLab(_index, lab_);
    }

    @Override
    public void add(int _index, AbsPreparedLabel _lab, T _elt) {
        list.add(_index, _elt);
        simpleAddLab(_index, _lab);
        addListeners(_index, _lab);
        updateGraphics();
    }

    public void addLab(int _index, AbsPreparedLabel _lab) {
        simpleAddLab(_index, _lab);
        repaintAdded(_index);
        resetDimensions();
        addListeners(_index, _lab);
    }

    public void addListeners(int _index, AbsPreparedLabel _lab) {
        FrameUtil.addLists(this,_index, _lab);
    }

    public void addMultSel(int _index, AbsPreparedLabel _lab) {
        MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index, graphicListPainter);
        i_.setSelection(listener);
        _lab.addKeyListener(i_);
        indexableKey.add(i_);
        MultSelectEltList j_ = new MultSelectEltList(this, _index, graphicListPainter);
        j_.setSelection(listener);
        _lab.addMouseListener(j_);
        indexableMouse.add(j_);
        FrameUtil.reindex(indexableMouse);
        FrameUtil.reindex(indexableKey);
    }

    public void addSingleSel(int _index, AbsPreparedLabel _lab) {
        IndexableListener i_ = buildSingleSelect(_lab, _index);
        indexableMouse.add(i_);
        FrameUtil.reindex(indexableMouse);
    }

    public void simpleAddLab(int _index, AbsPreparedLabel _lab) {
        AbsPanel panel_ = getPanel();
        listComponents.add(_index, _lab);
        panel_.add(_lab, _index);
    }
    public void set(int _index, T _elt) {
        AbsPreparedLabel lab_ = GuiBaseUtil.prep(graphicListPainter.getFact());
        set(_index,lab_,_elt);
    }
    public int set(int _index, AbsPreparedLabel _lab, T _elt) {
        try {
            list.set(_index, _elt);
            panel.remove(_index);
            panel.add(_lab,_index);
            listComponents.set(_index, _lab);
            FrameUtil.singleMultSel(this,_index, _lab);
            return _index;
        } catch (Exception e) {
            return -1;
        }
    }

    public void multSelSet(int _index, AbsPreparedLabel _lab) {
        MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index, graphicListPainter);
        i_.setSelection(listener);
        _lab.addKeyListener(i_);
        indexableKey.set(_index,i_);
        MultSelectEltList j_ = new MultSelectEltList(this, _index, graphicListPainter);
        j_.setSelection(listener);
        _lab.addMouseListener(j_);
        indexableMouse.set(_index,j_);
        FrameUtil.reindex(indexableMouse);
        FrameUtil.reindex(indexableKey);
    }

    public void singleSelSet(int _index, AbsPreparedLabel _lab) {
        IndexableListener i_ = buildSingleSelect(_lab, _index);
        indexableMouse.set(_index,i_);
        FrameUtil.reindex(indexableMouse);
    }

    protected void repaintAdded(int _index) {
        FrameUtil.repAdd(_index, render, listComponents);
    }

    protected IndexableListener buildSingleSelect(AbsPreparedLabel _lab,int _index) {
        SimpleSelectEltList i_ = new SimpleSelectEltList(this, _index, graphicListPainter);
        i_.setSelection(listener);
        _lab.addMouseListener(i_);
        return i_;
    }
    public void clear() {
        list.clear();
        AbsPanel panel_ = getPanel();
        listComponents.clear();
        panel_.removeAll();
        selectedIndexes.clear();
        indexableKey.clear();
        indexableMouse.clear();
    }
    public void clearRevalidate() {
        clear();
        scroll.revalidate();
    }
    public void remove(int _index) {
        list.remove(_index);
        AbsPanel panel_ = getPanel();
        listComponents.remove(_index);
        panel_.remove(_index);
        selectedIndexes.removeObj(_index);
        resetDimensions();
        FrameUtil.remSingleMult(_index,this);
        updateGraphics();
    }

    public final void rebuild() {
        FrameUtil.reb(this);
    }

    @Override
    public AbsCustCellRender getSimpleRender() {
        return render;
    }

    public void rebuildAct() {
        AbsPanel panel_ = getPanel();
        panel_.removeAll();
        getIndexableMouse().clear();
        getIndexableKey().clear();
        repaintAll();
        FrameUtil.selectSingleOrMult(this);
        resetDimensions();
    }

    public boolean isSimple() {
        return simple;
    }

    public void singleSelect(int _index, AbsPreparedLabel _c) {
        IndexableListener i_ = buildSingleSelect(_c,_index);
        getIndexableMouse().add(i_);
    }

    public void multSelect(int _index, AbsPreparedLabel _c) {
        MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index, graphicListPainter);
        i_.setSelection(listener);
        getIndexableKey().add(i_);
        _c.addKeyListener(i_);
        MultSelectEltList j_ = new MultSelectEltList(this, _index, graphicListPainter);
        j_.setSelection(listener);
        getIndexableMouse().add(j_);
        _c.addMouseListener(j_);
    }

    protected void repaintAll() {
        AbsCustCellRender r_ = setted();
        int len_ = list.size();
        FrameUtil.all(r_, len_, this, graphicListPainter);
    }

    public int getVisibleRowCount() {
        return visibleRowCount;
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        visibleRowCount = NumberUtil.max(1, _visibleRowCount);
        resetDimensions();
        updateGraphics();
    }

    public void addRange() {
        int min_ = NumberUtil.min(firstIndex, lastIndex);
        int max_ = NumberUtil.max(firstIndex, lastIndex);
        FrameUtil.addIndexes(min_, max_, selectedIndexes);
        selectedIndexes.removeDuplicates();
    }

    @Override
    public void updateGraphics() {
        MetaDimension dimension_ = FrameUtil.updateDim(this);
        scroll.setPreferredSize(dimension_);
        scroll.revalidate();
    }

    public void clearAllRange() {
        selectedIndexes.clear();
    }

    public void clearRange() {
        int min_ = NumberUtil.min(firstIndex, lastIndex);
        int max_ = NumberUtil.max(firstIndex, lastIndex);
        FrameUtil.removeIndexes(min_, max_, selectedIndexes);
    }

    public void setSelectedIndice(int _min) {
        selectedIndexes.clear();
        FrameUtil.addSelect(_min, selectedIndexes);
        selectedIndexes.removeDuplicates();
        AbsCustCellRender r_ = setted();
        int len_ = list.size();
        FrameUtil.selectedIndex(r_, len_, listComponents, selectedIndexes);
    }

    @Override
    public void setSelectedIndexes(Ints _values) {
        selectedIndexes.clear();
        selectedIndexes.addAllElts(_values);
        selectedIndexes.removeDuplicates();
        AbsCustCellRender r_ = setted();
        int len_ = list.size();
        FrameUtil.selectedIndex(r_, len_, getListComponents(), getSelectedIndexes());
    }

    @Override
    public boolean selectOneAmongIntervalPaint(boolean _sel, int _index) {
        return FrameUtil.intervalPaint(_sel, _index,this);
    }

    @Override
    public Interval selectIntervalKeyPaint(boolean _sel, int _index) {
        return FrameUtil.interval(_sel,this);
    }

    @Override
    public Interval selectIntervalPaint(boolean _sel, int _index) {
        setLastIndex(_index);
        int min_ = NumberUtil.min(getFirstIndex(), getLastIndex());
        int max_ = NumberUtil.max(getFirstIndex(), getLastIndex());
        AbsCustCellRender r_ = setted();
        FrameUtil.paintSelected(_sel, min_, max_, r_, this);
        return new Interval(min_,max_);
    }

    @Override
    public Interval selectIntervalPaintBase(boolean _sel, int _index) {
        int firstIndex_ = getFirstIndex();
        int min_ = NumberUtil.min(firstIndex_, _index);
        int max_ = NumberUtil.max(firstIndex_, _index);
        FrameUtil.updatedSelectedBis(_sel, min_, max_,this);
        return new Interval(min_,max_);
    }

    @Override
    public void selectOneAmongIntervalPaintBase(boolean _sel, int _index) {
        setFirstIndex(_index);
        FrameUtil.updatedSelected(_sel, _index, this);
    }

    public AbsCustCellRender setted() {
        return FrameUtil.fwd(render);
    }

    public void clearSelection() {
        AbsCustCellRender r_ = setted();
        CustList<T> copy_ = new CustList<T>(list);
        int len_ = list.size();
        FrameUtil.paintList(r_, len_, this);
        setFirstIndex(0);
        setLastIndex(copy_.size());
        clearRange();
        setFirstIndex(-1);
        setLastIndex(-1);
    }

    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    public int getMaxWidth() {
        return FrameUtil.getBasicMaxWidth(0, this);
    }

    protected void resetDimensions(){
        MetaDimension dimension_ = FrameUtil.dimension(this);
        scroll.setPreferredSize(dimension_);
        scroll.revalidate();
    }

    public ListSelection getListener() {
        return listener;
    }

    public void setListener(ListSelection _listener) {
        FrameUtil.selection(_listener, this);
        simpleSetListener(_listener);
    }

    public CustList<IndexableListener> getIndexableKey() {
        return indexableKey;
    }

    public CustList<IndexableListener> getIndexableMouse() {
        return indexableMouse;
    }

    @Override
    public CustList<ListSelection> getListeners() {
        return FrameUtil.listeners(listener);
    }

    @Override
    public void addListener(ListSelection _listener) {
        FrameUtil.addList(_listener,this);
    }

    @Override
    public void removeListener(ListSelection _listener) {
        FrameUtil.removeList(_listener,this);
    }

    public void simpleSetListener(ListSelection _listener) {
        listener = _listener;
    }

    public CustList<T> getList() {
        return list;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int _firstIndex) {
        firstIndex = _firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int _lastIndex) {
        lastIndex = _lastIndex;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public AbsCustComponent getGlobal() {
        return scroll();
    }

    public ScrollPane getScroll() {
        return scroll;
    }

    public CustList<AbsPreparedLabel> getListComponents() {
        return listComponents;
    }

    public int getSelectedIndex() {
        return FrameUtil.firstOrNeg(selectedIndexes);
    }

    @Override
    public int getSelectedValuesLsLen() {
        return getSelectedIndexes().size();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    public T get(int _i) {
        return list.get(_i);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T last() {
        return list.last();
    }

    public boolean isSelectionEmpty() {
        return selectedIndexes.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public JComponent getNatComponent() {
        return scroll.getNatComponent();
    }

    @Override
    public AbsCustComponent self() {
        return this;
    }

    @Override
    public AbsCustComponent scroll() {
        return scroll;
    }

    @Override
    public AbsCustComponent visible() {
        return panel;
    }

    public AbsGraphicListPainter getGraphicListPainter() {
        return graphicListPainter;
    }
}
