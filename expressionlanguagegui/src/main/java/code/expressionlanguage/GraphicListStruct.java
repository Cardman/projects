package code.expressionlanguage;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.Panel;
import code.util.CustList;
import code.util.Ints;

import java.awt.*;

public class GraphicListStruct extends CustComponentStruct {

    private CustList<Struct> list;
    private CustList<PreparedLabelStruct> listComponents = new CustList<PreparedLabelStruct>();
    private CustList<IndexableListener> indexableMouse = new CustList<IndexableListener>();
    private CustList<IndexableListener> indexableKey = new CustList<IndexableListener>();
    private Ints selectedIndexes = new Ints();

    private Struct render = NullStruct.NULL_VALUE;

    private ListSelection listener;

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
