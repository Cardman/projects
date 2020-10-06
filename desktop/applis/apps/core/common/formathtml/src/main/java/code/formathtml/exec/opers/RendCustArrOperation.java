package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult,RendCallable {

    private ExecArrContent arrContent;
    private ExecInstFctContent instFctContent;

    private ExecNamedFunctionBlock get;
    private ExecNamedFunctionBlock set;
    private ExecRootBlock rootBlock;

    public RendCustArrOperation(boolean _intermediate, ExecNamedFunctionBlock _get, ExecNamedFunctionBlock _set, ExecRootBlock _rootBlock, ExecOperationContent _content, ExecArrContent _arrContent, ExecInstFctContent _instFctContent) {
        super(_content,_intermediate);
        arrContent = _arrContent;
        instFctContent = _instFctContent;
        get = _get;
        set = _set;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (resultCanBeSet()) {
            Struct array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument(array_);
            setQuickNoConvertSimpleArgument(a_, _nodes, _context);
            return;
        }
        processCalling(_nodes,_conf, null, _advStandards, _context);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return processCalling(_nodes,_conf, _right, _advStandards, _context);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _context);
        if (_context.callsOrException()) {
            return res_;
        }
        return processCalling(_nodes,_conf,res_, _advStandards, _context);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(_stored, _op, _cast);
        Argument arg_ = processCalling(_nodes, _conf, res_, _advStandards, _context);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,arg_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return processCalling(_nodes, _conf, _right, _advStandards, _context);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        processCalling(_nodes, _conf, _right, _advStandards, _context);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,_right);
    }

    private Argument processCalling(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArgument(_nodes,this);
        Argument argres_ = processCall(this, this, previous_,_nodes,  _conf, _right, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
        return argres_;
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = instFctContent.getLastType();
        int naturalVararg_ = instFctContent.getNaturalVararg();
        String classNameFound_;
        classNameFound_ = instFctContent.getClassName();
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, argPrev_, _context));
        if (_context.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        ExecNamedFunctionBlock fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        Struct pr_ = prev_.getStruct();
        String cl_ = pr_.getClassName(_context);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, _context);
        lastType_ = ExecTemplates.quickFormat(rootBlock, clGen_, lastType_);
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        ExecOverrideInfo polymorph_ =  ExecInvokingOperation.polymorphOrSuper(instFctContent.isStaticChoiceMethod(), _context,pr_,classNameFound_,rootBlock,fct_);
        fct_ = polymorph_.getOverridableBlock();
        ExecRootBlock dest_ = polymorph_.getRootBlock();
        classNameFound_ = polymorph_.getClassName();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_,dest_, prev_, firstArgs_, _right,fct_, MethodAccessKind.INSTANCE, "");
    }

}
