package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.util.StringList;


public final class GraphicComboStruct extends InputStruct {
    private GraphicCombo graphicCombo;

    GraphicComboStruct(String _className) {
        super(_className);
    }

    GraphicComboStruct(String _className,Struct _list) {
        this(_className,0,_list);
    }

    GraphicComboStruct(String _className, int _selectedIndex,Struct _list) {
        this(_className,new GraphicStringList(newList(_list)),_selectedIndex);
    }

    private GraphicComboStruct(String _className, GraphicStringList _grList, int _selectedIndex) {
        super(_className);
        graphicCombo = new GraphicCombo(_grList,_selectedIndex);
    }

    private static StringList newList(Struct _s) {
        if (!(_s instanceof ArrayStruct)) {
            return new StringList();
        }
        StringList l_ = new StringList();
        for (Struct s: ((ArrayStruct)_s).getInstance()) {
            if (!(s instanceof StringStruct)) {
                continue;
            }
            l_.add(((StringStruct)s).getInstance());
        }
        return l_;
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(graphicCombo.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        graphicCombo.setEnabled(((BooleanStruct)_enabled).getInstance());
    }

    public Struct getSelectedItem() {
        String s_ = graphicCombo.getSelectedItem();
        if (s_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(s_);
    }

    public void addItem(Struct _object) {
        if (!(_object instanceof StringStruct)) {
            return;
        }
        graphicCombo.addItem(((StringStruct)_object).getInstance());
    }

    public IntStruct getItemCount() {
        return new IntStruct(graphicCombo.getItemCount());
    }

    public void selectItem(RunnableContextEl _run, NumberStruct _index) {
        int index_ = _index.intStruct();
        graphicCombo.setSelectedIndex(index_);
        graphicCombo.getGrList().setFirstIndex(index_);
        graphicCombo.getGrList().setLastIndex(index_);
        graphicCombo.getGrList().addRange();
        invokeRunnable(_run,new SelectionComboEventStruct(index_, index_, this));
    }
    public void setListener(Struct _arg) {
        if (!(_arg instanceof ListSelection)) {
            return;
        }
        graphicCombo.setListener((ListSelection) _arg);
    }
    public Struct getListener() {
        ListSelection l_ = graphicCombo.getListener();
        if (!(l_ instanceof Struct)) {
            return NullStruct.NULL_VALUE;
        }
        return (Struct) l_;
    }
    ListSelection getSelection() {
        return graphicCombo.getListener();
    }
    public void update() {
        graphicCombo.update();
    }
    public ArrayStruct getSelectedIndexes(ContextEl _cont) {
        int selectedIndex_ = graphicCombo.getSelectedIndex();
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(_cont.getStandards().getAliasPrimInteger());
        if (selectedIndex_ == -1) {
            return new ArrayStruct(new Struct[0], arrInt_);
        }
        Struct[] arr_ = new Struct[1];
        arr_[0] = new IntStruct(selectedIndex_);
        return new ArrayStruct(arr_, arrInt_);
    }
    public IntStruct getSelectedIndex() {
        int selectedIndex_ = graphicCombo.getSelectedIndex();
        return new IntStruct(selectedIndex_);
    }
    public void removeAllItems() {
        graphicCombo.getGrList().clearRevalidate();
        graphicCombo.setSelectedIndex(-1);
        graphicCombo.setNoSelected();
    }
    public void removeItem(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (graphicCombo.getGrList().getList().isValidIndex(index_)) {
            graphicCombo.removeItem(index_);
        }
    }

    @Override
    protected CustComponent getVisibleComponent() {
        return graphicCombo.getCurrentSelected();
    }

    @Override
    protected CustComponent getComponent() {
        return graphicCombo.getPanel();
    }
}
