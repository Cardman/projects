package code.expressionlanguage;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.util.CustList;
import code.util.Ints;

public class GraphicListStruct extends CustComponentStruct {

    private CustList<Struct> list;
    private CustList<PreparedLabelStruct> listComponents = new CustList<PreparedLabelStruct>();
    private CustList<IndexableListener> indexableMouse = new CustList<IndexableListener>();
    private CustList<IndexableListener> indexableKey = new CustList<IndexableListener>();
    private Ints selectedIndexes = new Ints();

    private Struct render = NullStruct.NULL_VALUE;

    private Struct listener = NullStruct.NULL_VALUE;

    private PanelStruct panel;
    private ScrollPaneStruct scroll;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private boolean simple;

    private int visibleRowCount = 8;
    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple) {
        this(_ctx,_className,_simple,new ArrayStruct(new Struct[0],PrimitiveTypeUtil.getPrettyArrayType(_ctx.getStandards().getAliasPrimInteger())), new ArrayStruct(new Struct[0],PrimitiveTypeUtil.getPrettyArrayType(_ctx.getStandards().getAliasObject())));
    }

    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple, Struct _selectedIndexes, Struct _objects) {
        super(_className);
        selectedIndexes = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).getInstance()) {
                selectedIndexes.add(((NumberStruct) s).intStruct());
            }
        }
        if (_objects instanceof ArrayStruct) {
            list = new CustList<Struct>(((ArrayStruct)_objects).getInstance());
        } else {
            list = new CustList<Struct>();
        }
        simple = _simple;
        LgNamesGui stds_ = (LgNamesGui) _ctx.getStandards();
        panel = PanelStruct.newPageBox(stds_.getAliasPanel());
        panel.setAutoscrolls(new BooleanStruct(true));
        scroll = ScrollPaneStruct.newScroll(panel,stds_.getAliasScrollPane());
    }
    public void add(ContextEl _ctx,int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        PreparedLabelStruct img_ = (PreparedLabelStruct) _img;
        panel.add(img_,_index);
        list.add(_index, _elt);
        listComponents.add(_index, img_);
        if (simple) {
            SimpleSelectEltListStruct i_ = new SimpleSelectEltListStruct(_ctx, this, _index);
            img_.addMouseListener(i_);
            indexableMouse.add(i_);
            reindex(indexableMouse);
        } else {
            MultSelectEltListStruct j_ = new MultSelectEltListStruct(_ctx, this, _index);
            img_.addMouseListener(j_);
            indexableMouse.add(j_);
            reindex(indexableMouse);
        }
//            MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index);
//            lab_.addKeyListener(i_);
//            indexableKey.add(i_);
//            MultSelectEltList j_ = new MultSelectEltList(this, _index);
//            lab_.addMouseListener(j_);
//            indexableMouse.add(j_);
//            reindex(indexableMouse);
//            reindex(indexableKey);
    }
    public void set(ContextEl _ctx,int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        if (!list.isValidIndex(_index)) {
            return;
        }
        PreparedLabelStruct img_ = (PreparedLabelStruct) _img;
        Struct c_ = panel.remove(_index);
        if (!(c_ instanceof CustComponentStruct)) {
            return;
        }
        panel.add(img_,_index);
        list.set(_index, _elt);
        listComponents.set(_index, img_);
        if (simple) {
            SimpleSelectEltListStruct i_ = new SimpleSelectEltListStruct(_ctx, this, _index);
            img_.addMouseListener(i_);
            indexableMouse.set(_index,i_);
        } else {
            MultSelectEltListStruct j_ = new MultSelectEltListStruct(_ctx, this, _index);
            img_.addMouseListener(j_);
            indexableMouse.add(j_);
        }
    }
    public ArrayStruct getListView(ContextEl _ctx) {
        int len_ = list.size();
        String obj_ = PrimitiveTypeUtil.getPrettyArrayType(_ctx.getStandards().getAliasObject());
        ArrayStruct arr_ = new ArrayStruct(new Struct[len_], obj_);
        for (int i = 0; i < len_; i++) {
            arr_.getInstance()[i] = list.get(i);
        }
        return arr_;
    }

    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    public void setSelectedIndexes(Struct _selectedIndexes) {
        Ints selectedIndexes_ = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).getInstance()) {
                selectedIndexes_.add(((NumberStruct) s).intStruct());
            }
            selectedIndexes = selectedIndexes_;
        }
    }

    public void clearSelection() {
        selectedIndexes.clear();
    }

    public ArrayStruct getSelectedIndexes(ContextEl _ctx) {
        int len_ = selectedIndexes.size();
        String obj_ = PrimitiveTypeUtil.getPrettyArrayType(_ctx.getStandards().getAliasPrimInteger());
        ArrayStruct arr_ = new ArrayStruct(new Struct[len_], obj_);
        for (int i = 0; i < len_; i++) {
            arr_.getInstance()[i] = new IntStruct(selectedIndexes.get(i));
        }
        return arr_;
    }
    private static void reindex(CustList<IndexableListener> _list) {
        int index_ = 0;
        for (IndexableListener c: _list) {
            c.setIndex(index_);
            index_++;
        }
    }
    void updateGraphics() {
        panel.revalidate();
        scroll.revalidate();
        panel.refresh();
    }

    public void setListener(Struct _listener) {
        listener = _listener;
    }

    public Struct getListener() {
        return listener;
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

    public void addRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.add(i);
        }
    }
    public void clearAllRange() {
        selectedIndexes.clear();
    }

    public void clearRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.removeObj(i);
        }
    }
    public PanelStruct getPanel() {
        return panel;
    }

    public Struct getRender() {
        return render;
    }

    public void setRender(Struct render) {
        this.render = render;
    }

    @Override
    protected CustComponent getComponent() {
        return scroll.getComponent();
    }
}
