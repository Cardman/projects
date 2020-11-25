package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.BooleanList;
import code.util.Ints;
import code.util.StringList;

public abstract class NamedCalledFunctionBlock extends NamedFunctionBlock {
    protected NamedCalledFunctionBlock(OffsetAccessInfo _access, OffsetStringInfo _retType, OffsetStringInfo _fctName, StringList _paramTypes, Ints _paramTypesOffset, StringList _paramNames, Ints _paramNamesOffset, OffsetsBlock _offset, BooleanList _refParams) {
        super(_access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset, _refParams);
    }

    protected NamedCalledFunctionBlock(int _fctName, OffsetsBlock _offset, AnalyzedPageEl _page) {
        super(_fctName, _offset, _page);
    }

    public abstract MethodId getId();
    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return getId().getSignature(_page);
    }
}
