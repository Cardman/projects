package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.*;
import code.util.core.IndexConstants;

public final class OperatorBlock extends NamedFunctionBlock implements AccessedBlock,ReturnableWithSignature {
    private final StringList allReservedInners = new StringList();
    private final CustList<RootBlock> localTypes = new CustList<RootBlock>();
    private final CustList<AnonymousTypeBlock> anonymousTypes = new CustList<AnonymousTypeBlock>();
    private int countsAnonFct;
    private final StringList imports = new StringList();

    private final Ints importsOffset = new Ints();

    public OperatorBlock(boolean _retRef, OffsetStringInfo _retType, OffsetStringInfo _fctName,
                         StringList _paramTypes, Ints _paramTypesOffset,
                         StringList _paramNames, Ints _paramNamesOffset,
                         OffsetsBlock _offset, BooleanList _refParams) {
        super(_retRef, new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset, _refParams);
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return getId().getSignature(_page);
    }
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isRetRef(), MethodAccessKind.STATIC, name_, pTypes_,getParametersRef(), isVarargs());
    }

    public boolean isStaticMethod() {
        return true;
    }

    public boolean isFinalMethod() {
        return false;
    }

    public boolean isAbstractMethod() {
        return false;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public CustList<RootBlock> getLocalTypes() {
        return localTypes;
    }

    public CustList<AnonymousTypeBlock> getAnonymousTypes() {
        return anonymousTypes;
    }

    public int getCountsAnonFct() {
        return countsAnonFct;
    }

    public void setCountsAnonFct(int _countsAnonFct) {
        this.countsAnonFct = _countsAnonFct;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }

    public StringList getImports() {
        return imports;
    }

    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

}
