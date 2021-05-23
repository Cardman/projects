package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.opers.ExecCustArrOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

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
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Struct argPrev_ = previous_.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), argPrev_, _context, _stackCall));
        Argument result_ = ExecCustArrOperation.redirect(_context,_right,_stackCall,prev_,infos_,instFctContent,readWrite);
//        if (_context.callsOrException(_stackCall)) {
//            result_ = new Argument();
//        } else {
//            result_ = ExecCustArrOperation.redirect(_context,_right,_stackCall,prev_,infos_,instFctContent,readWrite);
//        }
//        int naturalVararg_ = instFctContent.getNaturalVararg();
//        ExecFormattedRootBlock formattedType_ = instFctContent.getFormattedType();
//        if (_context.callsOrException(_stackCall)) {
//            result_ = new Argument();
//        } else {
//            ExecTypeFunction fct_;
//            if (_right != null) {
//                fct_ = readWrite.getWrite();
//            } else {
//                fct_ = readWrite.getRead();
//            }
//            Struct pr_ = prev_.getStruct();
//            String cl_ = pr_.getClassName(_context);
//            String lastType_ = ExecTemplates.formatType(_context, fct_.getType(), instFctContent.getLastType(), cl_);
//            ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorphOrSuper(instFctContent.isStaticChoiceMethod(), _context, pr_, formattedType_, fct_);
//            fct_ = polymorph_.getPair();
//            ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
//            result_ = new MethodParamChecker(fct_, fectchArgs(lastType_, naturalVararg_, _rendStackCall, _right,_context,_stackCall, infos_), MethodAccessKind.INSTANCE).checkParams(classNameFound_, prev_, null, _context, _stackCall);
//        }
        ArgumentListCall list_ = _rendStackCall.getLastPage().getList();
//        list_.getArgumentWrappers().clear();
//        list_.getArgumentWrappers().addAllElts(fetchArgs_.getArgumentWrappers());
        Argument argres_ = RendDynOperationNode.processCall(result_, _context, _stackCall).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stackCall, _rendStackCall);
        return argres_;
    }

    public ExecInstFctContent getInstFctContent() {
        return instFctContent;
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
