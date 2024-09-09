package code.expressionlanguage;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;
import code.gui.images.MetaFont;
import code.util.CustList;

public abstract class CommonCellPrepareCustomEvents extends AbsPrepareCustomEvents {
    private RowGraphicList<Struct> row;
    private int index;
    private Struct info;
    private Struct font;
    private final GraphicListStruct instance;

    protected CommonCellPrepareCustomEvents(RowGraphicList<Struct> _r, int _i, Struct _f, Struct _o, GraphicListStruct _g) {
        this.row = _r;
        this.index = _i;
        this.info = _f;
        this.font = _o;
        this.instance = _g;
    }

    public RowGraphicList<Struct> getRow() {
        return row;
    }

    public void rowSet(RowGraphicList<Struct> _r, int _i) {
        this.row = _r;
        index = _i;
        info = _r.getInfo();
        MetaFont mf_ = _r.getLabel().getMetaFont();
        font = new FontStruct(mf_);
        info = _r.getInfo();
    }

    @Override
    protected ExecTypeFunction pair(ContextEl _ctx) {
        return new ExecTypeFunction(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getCellRender(),((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getCellRenderGenerate());
    }

    @Override
    protected CustList<ArgumentWrapper> args() {
        CustList<ArgumentWrapper> args_ = new CustList<ArgumentWrapper>();
        args_.add(new ArgumentWrapper(new IntStruct(index)));
        args_.add(new ArgumentWrapper(info));
        if (row != null) {
            args_.add(new ArgumentWrapper(BooleanStruct.of(row.isSelected())));
        } else {
            args_.add(new ArgumentWrapper(BooleanStruct.of(false)));
        }
        if (row != null) {
            args_.add(new ArgumentWrapper(BooleanStruct.of(row.isFocused())));
        } else {
            args_.add(new ArgumentWrapper(BooleanStruct.of(false)));
        }
        if (row != null) {
            args_.add(new ArgumentWrapper(BooleanStruct.of(row.isAnchored())));
        } else {
            args_.add(new ArgumentWrapper(BooleanStruct.of(false)));
        }
        args_.add(new ArgumentWrapper(font));
        args_.add(new ArgumentWrapper(instance));
        return args_;
    }
}
