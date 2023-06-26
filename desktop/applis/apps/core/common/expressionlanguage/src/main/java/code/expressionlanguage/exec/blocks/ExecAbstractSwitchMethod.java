package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public abstract class ExecAbstractSwitchMethod extends ExecMemberCallingsBlock implements ExecReturnableWithSignature,ExecAnnotableParamBlock,WithCache {

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();

    private final String idFull;
    private final String name;

    private final String importedParamType;

    private final boolean retRef;

    private final MethodAccessKind kind;
    private final CustList<CustList<ExecAnnotContent>> annotationsOpsParams = new CustList<CustList<ExecAnnotContent>>();
    private final String retType;
    private final ExecAnonFctContent anonFctContent;
    private final CustList<ExecAnnotContent> execAnnotContentsSupp = new CustList<ExecAnnotContent>();

    protected ExecAbstractSwitchMethod(String _i,boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, String _retType, ExecAnonFctContent _anonFctContent) {
        idFull = _i;
        name = _name;
        retRef = _retRef;
        importedParamType = _importedParamType;
        kind = _modifier;
        retType = _retType;
        anonFctContent = _anonFctContent;
    }

    @Override
    public String id() {
        return idFull;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = new StringList(importedParamType);
        return new MethodId(isRetRef(), kind, name_, pTypes_,new CustList<BoolVal>(BoolVal.FALSE), false);
    }

    public String getImportedParamType() {
        return importedParamType;
    }

    public String getRetType() {
        return retType;
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    public MethodModifier getModifier() {
        if (kind == MethodAccessKind.STATIC) {
            return MethodModifier.STATIC;
        }
        if (kind == MethodAccessKind.STATIC_CALL) {
            return MethodModifier.STATIC_CALL;
        }
        return MethodModifier.FINAL;
    }
    public String getName() {
        return name;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public ExecBlock processCase(Argument _arg, StackCall _stack) {
        boolean atMost_ = this instanceof ExecSwitchInstanceMethod;
        SwitchBlockStack sw_ = new SwitchBlockStack(this, atMost_);
        sw_.setValue(ArgumentListCall.toStr(_arg));
        sw_.setInstanceTest(getImportedParamType());
        sw_.setLabel("");
        return cover(sw_, _stack);
    }

    public ExecBlock cover(SwitchBlockStack _if, StackCall _stack) {
        AbstractPageEl page_ = _stack.getLastPage();
        page_.clearCurrentEls();
        return ExecAbstractSwitchBlock.visit(_if,page_, this);
    }

    public CacheInfo getCacheInfo() {
        return anonFctContent.getCacheInfo();
    }
    @Override
    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOpsSupp() {
        return execAnnotContentsSupp;
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }
}
