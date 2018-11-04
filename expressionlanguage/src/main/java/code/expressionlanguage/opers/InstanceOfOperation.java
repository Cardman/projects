package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;

public final class InstanceOfOperation extends AbstractUnaryOperation {

    private String className;
    private int offset;
    private boolean correctTemplate = true;
    public InstanceOfOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String sub_ = className.substring(keyWordInstanceof_.length() + className.indexOf(keyWordInstanceof_));
        sub_ = _conf.resolveCorrectType(sub_, false);
        if (!sub_.contains(Templates.TEMPLATE_BEGIN)) {
            if (!sub_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                correctTemplate = Templates.correctNbParameters(sub_, _conf);
            }
        }
        className = sub_;
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument argres_ = getArgument( arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            arg_.setObject(false);
            return arg_;
        }
        String className_ = stds_.getStructClassName(objArg_.getStruct(), _conf.getContextEl());
        PageEl page_ = _conf.getOperationPageEl();
        String str_ = page_.formatVarType(className, _conf);
        if (!correctTemplate) {
            className_ = Templates.getIdFromAllTypes(className_);
            boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(false, str_, className_, _conf);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return arg_;
        }
        boolean res_ = Templates.isCorrectExecute(className_, str_, _conf);
        Argument arg_ = new Argument();
        arg_.setObject(res_);
        return arg_;
    }
}
