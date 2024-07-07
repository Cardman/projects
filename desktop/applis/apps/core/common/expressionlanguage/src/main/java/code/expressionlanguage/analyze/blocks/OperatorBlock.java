package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.*;
import code.util.core.IndexConstants;

public final class OperatorBlock extends NamedFunctionBlock implements AccessedBlockMembers,ReturnableWithSignature {
    private final StringList allReservedInners = new StringList();
    private final String labelNumber;
    private final StringList imports = new StringList();

    private final Ints importsOffset = new Ints();
    private int operatorNumber;
    private final AccessMemNbContent accessMemNb = new AccessMemNbContent();
    private final AccessNbContent accessNb = new AccessNbContent();

    public OperatorBlock(ParsedFctHeader _header, OffsetStringInfo _retType, OffsetStringInfo _fctName,
                         int _offset) {
        super(_header, new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName, _offset);
        labelNumber = _header.getInfo().trim();
    }

    @Override
    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        super.buildImportedReturnTypes(_page);
        retRef(_page, MethodKind.OPERATOR);
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

    @Override
    public AccessNbContent contentNb() {
        return accessNb;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }

    public StringList getImports() {
        return imports;
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

    @Override
    public AccessMemNbContent contentMemNb() {
        return accessMemNb;
    }

    public int getOperatorNumber() {
        return operatorNumber;
    }

    public void setOperatorNumber(int _operatorNumber) {
        this.operatorNumber = _operatorNumber;
    }

    public String getLabelNumber() {
        return labelNumber;
    }

}
