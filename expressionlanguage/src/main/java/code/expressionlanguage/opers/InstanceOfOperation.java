package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        String method_ = prefixFunction(INSTANCEOF);
        String sub_ = className.substring(method_.length() + className.indexOf(method_));
        sub_ = StringList.removeAllSpaces(sub_);
        if (sub_.contains(Templates.TEMPLATE_BEGIN)) {
            checkCorrect(_conf, sub_, true, offset+1);
        } else {
            if (!checkExistBase(_conf, !isStaticBlock(), sub_, true, offset+1)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                return;
            }
            if (!sub_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                correctTemplate = Templates.correctNbParameters(sub_, _conf);
            }
        }
        className = sub_;
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(true));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(true));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void calculate(ContextEl _conf) {
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

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            arg_.setObject(false);
            return arg_;
        }
        String className_ = stds_.getStructClassName(objArg_.getStruct(), _conf);
        if (!correctTemplate) {
            className_ = StringList.getAllTypes(className_).first();
            boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(className, className_, _conf);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return arg_;
        }
        Mapping mapping_ = new Mapping();
        mapping_.setArg(className_);
        PageEl page_ = _conf.getLastPage();
        String str_ = page_.formatVarType(className, _conf);
        mapping_.setParam(str_);
        boolean res_ = Templates.isCorrect(mapping_, _conf);
        Argument arg_ = new Argument();
        arg_.setObject(res_);
        return arg_;
    }
}
