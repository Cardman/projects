package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecCustArrOperation;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private final ExecArrContent arrContent;
    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunctionPair readWrite;

    public RendCustArrOperation(boolean _intermediate, ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _content, ExecArrContent _arrContent, ExecInstFctContent _instFctContent) {
        super(_content,_intermediate);
        arrContent = _arrContent;
        instFctContent = _instFctContent;
        readWrite = new ExecTypeFunctionPair(_get,_set);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        if (resultCanBeSet()) {
            Struct array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument(array_);
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _rendStack);
            return;
        }
        processCalling(_nodes, null, _context, _rendStack);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        return processCalling(_nodes, _right, _context, _rendStack);
    }

    @Override
    public Argument calculateCompoundString(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecCatOperation.localSumDiff(left_, _right, _context);
        return processCalling(_nodes, res_, _context, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, Argument _right, byte _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, _cl, _context, _rendStack);
        return processCalling(_nodes, res_, _context, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, StringList _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _cl, _context);
        return processCalling(_nodes, res_, _context, _rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, boolean _post, byte _cast, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        Argument arg_ = processCalling(_nodes, res_, _context, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post,left_,arg_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        processCalling(_nodes, _right, _context, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,_right);
    }

    private Argument processCalling(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall) {
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return _right;
        }
        Argument previous_ = getPreviousArgument(_nodes,this);
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument result_ = ExecCustArrOperation.redirect(_context,_right,_rendStackCall.getStackCall(),previous_,infos_,instFctContent,readWrite);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStackCall);
        setSimpleArgument(argres_, _nodes, _context, _rendStackCall);
        return argres_.getValue();
    }

    public ExecInstFctContent getInstFctContent() {
        return instFctContent;
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
