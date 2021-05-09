package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private final ExecArrContent arrContent;
    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunction get;
    private final ExecTypeFunction set;

    public RendCustArrOperation(boolean _intermediate, ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _content, ExecArrContent _arrContent, ExecInstFctContent _instFctContent) {
        super(_content,_intermediate);
        arrContent = _arrContent;
        instFctContent = _instFctContent;
        get = _get;
        set = _set;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        if (resultCanBeSet()) {
            Struct array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument(array_);
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _stack);
            return;
        }
        processCalling(_nodes, null, _context, _stack, _rendStack);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return processCalling(_nodes, _right, _context, _stack, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _context, _stack);
        return processCalling(_nodes, res_, _context, _stack, _rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument res_ = ExecNumericOperation.calculateIncrDecr(_stored, _op, _cast);
        Argument arg_ = processCalling(_nodes, res_, _context, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,arg_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return processCalling(_nodes, _right, _context, _stack, _rendStack);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processCalling(_nodes, _right, _context, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,_right);
    }

    private Argument processCalling(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        if (_context.callsOrException(_stackCall)) {
            return _right;
        }
        Argument previous_ = getPreviousArgument(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _rendStackCall);
        String lastType_ = instFctContent.getLastType();
        int naturalVararg_ = instFctContent.getNaturalVararg();
        String classNameFound_ = instFctContent.getClassName();
        Struct argPrev_ = previous_.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, argPrev_, _context, _stackCall));
        Argument result_;
        if (_context.callsOrException(_stackCall)) {
            result_ = new Argument();
        } else {
            String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
            ExecTypeFunction fct_;
            if (_right != null) {
                fct_ = set;
            } else {
                fct_ = get;
            }
            Struct pr_ = prev_.getStruct();
            String cl_ = pr_.getClassName(_context);
            String clGen_ = ExecInherits.getSuperGeneric(cl_, base_, _context);
            lastType_ = ExecInherits.quickFormat(fct_.getType(), clGen_, lastType_);
            ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorphOrSuper(instFctContent.isStaticChoiceMethod(), _context, pr_, classNameFound_, fct_);
            fct_ = polymorph_.getPair();
            classNameFound_ = polymorph_.getClassName();
            result_ = new DefaultParamChecker(fct_, fectchArgs(_nodes, lastType_, naturalVararg_, _rendStackCall, _right), CallPrepareState.METHOD, null).checkParams(classNameFound_, prev_, null, _context, MethodAccessKind.INSTANCE, _stackCall);
        }
        Argument argres_ = RendDynOperationNode.processCall(result_, _context, _stackCall).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stackCall, _rendStackCall);
        return argres_;
    }

}
