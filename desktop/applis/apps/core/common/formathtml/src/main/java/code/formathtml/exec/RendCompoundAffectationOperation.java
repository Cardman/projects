package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CompoundAffectationOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

    private RendSettableElResult settable;
    private String oper;
    private MethodAccessKind kind;
    private String className;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;

    public RendCompoundAffectationOperation(CompoundAffectationOperation _c, AnalyzedPageEl _page) {
        super(_c);
        oper = _c.getOper();
        kind = ExecOperationNode.getKind(_c.getClassMethodId());
        className = ExecOperationNode.getType(_c.getClassMethodId());
        named = ExecOperationNode.fetchFunctionOp(_c.getRootNumber(),_c.getMemberNumber(), _page);
        rootBlock = ExecOperationNode.fetchType(_c.getRootNumber(), _page);
        converter = ExecOperationNode.fetchImplicits(_c.getConverter(),_c.getRootNumberConv(),_c.getMemberNumberConv(), _page);
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (((RendDynOperationNode) settable).getParent() instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = ((RendDynOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(_conf.getPageEl(), NullStruct.NULL_VALUE,_conf.getContext(), getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
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
            Argument arg_ = settable.calculateSetting(_nodes, _conf, leftArg_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        if (named != null) {
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add(left_);
            chidren_.add(right_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),_nodes,_conf, null);
            if (converter != null) {
                Argument conv_ = tryConvert(converter.getRootBlock(),converter.get(0),converter.getOwnerClass(), res_, _conf);
                if (conv_ == null) {
                    return;
                }
                res_ = conv_;
            }
            Argument arg_ = settable.endCalculate(_nodes, _conf, res_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        if (converter != null) {
            String tres_ = converter.get(0).getImportedParametersTypes().get(0);
            Argument res_;
            StringList arg = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _conf.getStandards());
            res_ = RendNumericOperation.calculateAffect(leftArg_, _conf, rightArg_, oper, false, arg, cast_);
            Argument conv_ = tryConvert(converter.getRootBlock(),converter.get(0),converter.getOwnerClass(), res_, _conf);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument arg_ = settable.endCalculate(_nodes, _conf, res_);
            setSimpleArgument(arg_, _conf,_nodes);
            return;
        }
        Argument arg_ = settable.calculateCompoundSetting(_nodes, _conf, oper, rightArg_, getResultClass(), getResultClass().getUnwrapObjectNb());
        setSimpleArgument(arg_, _conf,_nodes);
    }

    public String getOper() {
        return oper;
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(), rootBlock,named, first_, className, kind);
        return Argument.createVoid();
    }

    public RendSettableElResult getSettable() {
        return settable;
    }
}
