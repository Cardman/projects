package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private ExecOperatorContent operatorContent;
    private ExecStaticEltContent staticEltContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;

    public RendCompoundAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_content);
        operatorContent = _operatorContent;
        staticEltContent = _staticEltContent;
        named = _named;
        rootBlock = _rootBlock;
        converter = _converter;
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
        settableParent = RendAffectationOperation.tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (settableParent instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context);
                return;
            }
        }
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        RendDynOperationNode left_ = list_.first();
        RendDynOperationNode right_ = list_.last();
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, left_);
        if (argumentPair_.isArgumentTest()){
            Argument arg_ = RendAffectationOperation.calculateChSetting(settable,_nodes, _conf, leftArg_, _advStandards, _context);
            setSimpleArgument(arg_, _conf,_nodes, _context);
            return;
        }
        if (named != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add(left_);
            chidren_.add(right_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),_nodes,_conf, null, _advStandards, _context);
            if (converter != null) {
                Argument conv_ = tryConvert(converter.getRootBlock(),converter.get(0),converter.getOwnerClass(), res_, _context);
                if (conv_ == null) {
                    return;
                }
                res_ = conv_;
            }
            Argument arg_ = endCalculateCh(_nodes, _conf, res_, settable, _advStandards, _context);
            setSimpleArgument(arg_, _conf,_nodes, _context);
            return;
        }
        if (converter != null) {
            String tres_ = converter.get(0).getImportedParametersTypes().get(0);
            StringList argType_ = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, operatorContent.getOper(), false, argType_, cast_, _context);
            Argument conv_ = tryConvert(converter.getRootBlock(),converter.get(0),converter.getOwnerClass(), res_, _context);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument arg_ = endCalculateCh(_nodes, _conf, res_, settable, _advStandards, _context);
            setSimpleArgument(arg_, _conf,_nodes, _context);
            return;
        }
        Argument arg_ = calculateCompoundChSetting(_nodes, _conf, rightArg_, _advStandards, _context);
        setSimpleArgument(arg_, _conf,_nodes, _context);
    }

    private static Argument endCalculateCh(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _res, RendDynOperationNode _settable, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = null;
        if (_settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context);
        }
        return Argument.getNullableValue(arg_);
    }

    private Argument calculateCompoundChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _rightArg, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = null;
        if (settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        return Argument.getNullableValue(arg_);
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(_context.getExiting(),_context, rootBlock,named, first_, staticEltContent.getClassName());
        return Argument.createVoid();
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }
}
