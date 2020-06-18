package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecOperatorBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class OperatorBlock extends NamedFunctionBlock implements GeneMethod,ReturnableWithSignature {

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    public OperatorBlock(OffsetStringInfo _retType, OffsetStringInfo _fctName,
                         StringList _paramTypes, Ints _paramTypesOffset,
                         StringList _paramNames, Ints _paramNamesOffset,
                         OffsetsBlock _offset) {
        super(new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodAccessKind.STATIC, name_, pTypes_, isVarargs());
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

    public void buildAnnotationsParams(ExecOperatorBlock _val) {
        _val.getAnnotationsOpsParams().addAllElts(getAnnotationsOpsParams());
    }
}
