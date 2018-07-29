package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class AssocationOperation extends AbstractUnaryOperation {

    private String fieldName;

    public AssocationOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op, String _fieldName) {
        super(_index, _indexChild, _m, _op);
        fieldName = _fieldName;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }

    @Override
    public void analyze(Analyzable _conf) {
        if (!(getParent() instanceof AnnotationInstanceOperation)) {
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(un_);
        }
        if (!StringList.isWord(fieldName.trim())) {
            BadFieldName err_ = new BadFieldName();
            err_.setName(fieldName.trim());
            err_.setRc(_conf.getCurrentLocation());
            err_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(err_);
        }
        fieldName = fieldName.trim();
        setResultClass(getFirstChild().getResultClass());
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgumentAna(arg_, _conf);
    }
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        return _nodes.getVal(this).getArgument();
    }

}
