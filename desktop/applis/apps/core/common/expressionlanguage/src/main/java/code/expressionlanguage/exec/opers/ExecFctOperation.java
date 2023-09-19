package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecFctOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunctionInst inst;

    public ExecFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        inst = new ExecTypeFunctionInst(_instFctContent,_pair);
    }

    public ExecFctOperation(ExecClassArgumentMatching _res,
                            int _child, int _order, ExecArrContent _arrContent, ExecTypeFunction _pair, ExecFormattedRootBlock _formattedType) {
        super(_child,_res,_order,true,_arrContent);
        inst = new ExecTypeFunctionInst(new ExecInstFctContent(_formattedType),_pair);
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        setRelOffsetPossibleLastPage(inst.getInst().getMethodName(), _stack);
        Argument parent_ = new Argument(ExecFieldTemplates.getParent(inst.getInst().getAnc(), previous_.getStruct(), _conf, _stack));
        prep(_conf, _stack, inst, parent_, fetchFormattedArgs(_conf, _stack, parent_.getStruct(), inst, buildInfos(_nodes)));
        setCheckedResult(ArgumentListCall.toStr(NullStruct.NULL_VALUE), _conf, _nodes, _stack);
    }

    public Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last) {
        return ExecOperationNode.instance(this,inst.getInst().getAnc(), _nodes, _last);
    }
    public ArgumentListCall args(ContextEl _cont, Struct _pr, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return args(_cont,inst.getPair().getType(),inst.getInst().getLastType(),inst.getInst().getNaturalVararg(),_pr,_nodes).getArguments();
    }
    public ExecOverrideInfo poly(ContextEl _cont, Struct _pr) {
        ExecFormattedRootBlock formattedType_ = inst.getInst().getFormattedType();
        return polymorphOrSuper(inst.getInst().isStaticChoiceMethod(), _cont, _pr, formattedType_, inst.getPair());
    }
    public static ExecFormattedRootBlock glClass(ContextEl _cont, Struct _pr, ExecFormattedRootBlock _classNameFound) {
        String className_ = _pr.getClassName(_cont);
        String sup_ = ExecInherits.getQuickFullTypeByBases(className_, _classNameFound.getFormatted(), _cont);
        return new ExecFormattedRootBlock(_classNameFound, sup_);
    }

    public static void prep(ContextEl _conf, StackCall _stack, ExecTypeFunctionInst _inst, Argument _parent, ArgumentListCall _args) {
        ExecFormattedRootBlock formattedType_ = _inst.getInst().getFormattedType();
        Struct pr_ = _parent.getStruct();
        ExecOverrideInfo polymorph_ = polymorphOrSuper(_inst.getInst().isStaticChoiceMethod(), _conf, pr_, formattedType_, _inst.getPair());
        ExecTypeFunction pair_ = polymorph_.getPair();
        ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
        new MethodParamChecker(pair_, _args, MethodAccessKind.INSTANCE).checkParams(classNameFound_, _parent, null, _conf, _stack);
    }

    public ExecTypeFunctionInst getInst() {
        return inst;
    }
}
