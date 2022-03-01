package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private final ExecArrContent arrContent;

    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunctionPair readWrite;

    public ExecCustArrOperation(ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrContent _arrContent, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation);
        arrContent = _arrContent;
        instFctContent = _instFctContent;
        readWrite = new ExecTypeFunctionPair(_get,_set);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        if (resultCanBeSet()) {
            Struct array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument(array_);
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stack);
            return;
        }
        Argument res_ = getArgument(_conf,_nodes,null,_stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        return getArgument(_conf, _nodes, _right, _stack);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    private Argument getArgument(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall) {
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        Argument previous_ = getPreviousArg(this, _nodes, _stackCall);
        setRelativeOffsetPossibleLastPage(_stackCall);
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        return redirect(_conf, _right, _stackCall, previous_, infos_, instFctContent, readWrite);
    }

    public static Argument redirect(ContextEl _conf, Argument _right, StackCall _stackCall, Argument _previous, CustList<ExecOperationInfo> _infos, ExecInstFctContent _instFctContent, ExecTypeFunctionPair _readWrite) {
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(_instFctContent.getAnc(), argPrev_, _conf, _stackCall));
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        ExecTypeFunction fct_;
        if (_right != null) {
            fct_ = _readWrite.getWrite();
        } else {
            fct_ = _readWrite.getRead();
        }
        ExecRootBlock type_ = fct_.getType();
        Struct pr_ = prev_.getStruct();
        ExecOverrideInfo polymorph_ = polymorphOrSuper(_instFctContent.isStaticChoiceMethod(), _conf,pr_, _instFctContent.getFormattedType(),fct_);
        fct_ = polymorph_.getPair();
        ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
        return new MethodParamChecker(fct_, fetchFormattedArgs(_conf, _stackCall, pr_, type_, _instFctContent, _right, _infos), MethodAccessKind.INSTANCE).checkParams(classNameFound_, prev_, null, _conf, _stackCall);
    }

    public ExecInstFctContent getInstFctContent() {
        return instFctContent;
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
