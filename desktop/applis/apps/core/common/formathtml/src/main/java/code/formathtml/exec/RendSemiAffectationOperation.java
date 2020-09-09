package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SemiAffectationOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendSemiAffectationOperation extends RendAbstractUnaryOperation implements RendCallable {
    private RendSettableElResult settable;
    private boolean post;
    private String oper;
    private MethodAccessKind kind;
    private String className;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converterFrom;
    private ImplicitMethods converterTo;

    public RendSemiAffectationOperation(SemiAffectationOperation _s, ContextEl _context) {
        super(_s);
        post = _s.isPost();
        oper = _s.getOper();
        kind = ExecOperationNode.getKind(_s.getClassMethodId());
        className = ExecOperationNode.getType(_context,_s.getClassMethodId());
        named = ExecOperationNode.fetchFunction(_context,_s.getRootNumber(),_s.getMemberNumber());
        rootBlock = ExecOperationNode.fetchType(_context,_s.getRootNumber());
        converterFrom = ExecOperationNode.fetchImplicits(_context,_s.getConverterFrom(),_s.getRootNumberFrom(),_s.getMemberNumberFrom());
        converterTo = ExecOperationNode.fetchImplicits(_context,_s.getConverterTo(),_s.getRootNumberTo(),_s.getMemberNumberTo());
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
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),NullStruct.NULL_VALUE,_conf.getContext()));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        if (named != null) {
            CustList<RendDynOperationNode> list_ = getChildrenNodes();
            RendDynOperationNode left_ = list_.first();
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add(left_);
            Argument stored_ = getArgument(_nodes,left_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),_nodes,_conf, null);
            res_ = settable.endCalculate(_nodes,_conf, post, stored_, res_);
            setSimpleArgument(res_, _conf,_nodes);
            return;
        }
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        RendDynOperationNode left_ = list_.first();
        Argument leftStore_ = getArgument(_nodes,left_);
        Argument stored_ = getArgument(_nodes,(RendDynOperationNode) settable);
        Argument before_ = stored_;
        if (converterFrom != null) {
            Argument conv_ = tryConvert(converterFrom.getRootBlock(),converterFrom.get(0),converterFrom.getOwnerClass(), leftStore_, _conf);
            if (conv_ == null) {
                return;
            }
            stored_ = conv_;
        }
        if (converterTo != null) {
            String tres_ = converterTo.get(0).getImportedParametersTypes().get(0);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(tres_);
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(stored_, _conf.getContext(), oper, cl_);
            Argument conv_ = tryConvert(converterTo.getRootBlock(),converterTo.get(0),converterTo.getOwnerClass(), res_, _conf);
            if (conv_ == null) {
                return;
            }
            conv_ = settable.calculateSetting(_nodes,_conf,conv_);
            stored_ =  RendSemiAffectationOperation.getPrePost(post,before_,conv_);
            setSimpleArgument(stored_, _conf,_nodes);
            return;
        }
        Argument arg_ = settable.calculateSemiSetting(_nodes, _conf, oper, post,stored_);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(), rootBlock,named, first_, className, kind);
        return Argument.createVoid();
    }
}
