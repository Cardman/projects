package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.CustList;

public abstract class SrcFileLocationField extends AbsSrcFileLocation {
    private final ClassField cf;

    protected SrcFileLocationField(ClassField _c) {
        cf = _c;
    }

    static void addField(ClassField _c, RootBlock _d, int _i, CstFieldInfo _s, CustList<SrcFileLocation> _out) {
        SrcFileLocationField f_ = field(_c, _d, _i, _s);
        if (f_ != null) {
            _out.add(f_);
        }
    }
    static SrcFileLocationField field(ClassField _c, RootBlock _d, int _i, CstFieldInfo _s) {
        if (_c.getClassName().isEmpty()) {
            return null;
        }
        return fieldInit(_c, _d, _i, _s);
    }

    static SrcFileLocationField fieldInit(ClassField _c, RootBlock _d, int _i, CstFieldInfo _s) {
        if (_d != null) {
            return new SrcFileLocationFieldCust(_c, _d, _i);
        }
        return new SrcFileLocationFieldStd(_c, _s);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.FIELD,getCf().getClassName()+"."+getCf().getFieldName(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }

    public ClassField getCf() {
        return cf;
    }
}
