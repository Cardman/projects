package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecAbstractSwitchMethod extends ExecMemberCallingsBlock implements ExecReturnableWithSignature,ExecAnnotableParamBlock,WithCache {

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();

    private final String name;

    private final String importedParamType;

    private final boolean retRef;

    private final MethodAccessKind kind;
    private final CustList<CustList<ExecAnnotContent>> annotationsOpsParams = new CustList<CustList<ExecAnnotContent>>();
    private final String retType;
    private final ExecAnonFctContent anonFctContent;
    protected ExecAbstractSwitchMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, String _retType, ExecAnonFctContent _anonFctContent) {
        name = _name;
        retRef = _retRef;
        importedParamType = _importedParamType;
        kind = _modifier;
        retType = _retType;
        anonFctContent = _anonFctContent;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = new StringList(importedParamType);
        return new MethodId(isRetRef(), kind, name_, pTypes_,new CustList<Boolean>(false), false);
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

    public ExecBlock processCase(ContextEl _cont, Argument _arg, StackCall _stack) {
        ExecResultCase res_ = ExecAbstractSwitchBlock.innerProcess(getImportedParamType(), _cont, _stack, this, _arg, 0);
        ExecListLastBkSw infos_ = new ExecListLastBkSw(this);
        ExecBracedBlock last_ = lastVisMeth(infos_, res_);
        SwitchBlockStack sw_ = new SwitchBlockStack(this,last_);
        sw_.setLabel("");
        return cover(_cont, sw_, _arg, _stack, res_);
    }
    protected abstract ExecBracedBlock lastVisMeth(ExecListLastBkSw _if, ExecResultCase _res);
    public ExecBlock cover(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, ExecResultCase _found) {
        if (_cont.callsOrException(_stack)) {
            return this;
        }
        AbstractPageEl page_ = _stack.getLastPage();
        _cont.getCoverage().passSwitchMethod(_found, _arg, _stack);
        page_.clearCurrentEls();
        ExecBracedBlock foundBk_ = ExecResultCase.block(_found);
        ExecAbstractSwitchBlock.visit(_if,_found,page_, foundBk_);
        return foundBk_;
    }

    public CacheInfo getCacheInfo() {
        return anonFctContent.getCacheInfo();
    }
    @Override
    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }
}
