package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.Ints;
import code.util.StringList;

public final class MethodBlock extends OverridableBlock {

    public MethodBlock(ContextEl _importingPage,
                       OffsetAccessInfo _access,
                       OffsetStringInfo _retType, OffsetStringInfo _fctName,
                       StringList _paramTypes, Ints _paramTypesOffset,
                       StringList _paramNames, Ints _paramNamesOffset,
                       OffsetStringInfo _modifier, OffsetsBlock _offset) {
        super(_importingPage, _access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _modifier,_offset);
    }

}
