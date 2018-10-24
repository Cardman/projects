package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadFieldName;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class AssocationOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

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
    public void preAnalyze(Analyzable _conf) {
        if (!StringList.isWord(fieldName.trim())) {
            BadFieldName err_ = new BadFieldName();
            err_.setName(fieldName.trim());
            err_.setRc(_conf.getCurrentLocation());
            err_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(err_);
        }
        fieldName = fieldName.trim();
        MethodOperation mOp_ = getParent();
        if (!(mOp_ instanceof AnnotationInstanceOperation)) {
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(un_);
        } else {
            AnnotationInstanceOperation par_ = (AnnotationInstanceOperation) mOp_;
            if (par_.isArray()) {
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(un_);
            } else {
                String annotationClass_ = par_.getClassName();
                if (!StringList.quickEq(_conf.getStandards().getAliasObject(), annotationClass_)) {
                    Block ann_ = (Block) _conf.getClassBody(annotationClass_);
                    boolean ok_ = false;
                    for (Block b: Classes.getDirectChildren(ann_)) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        if (StringList.quickEq(a_.getName(), fieldName)) {
                            ok_ = true;
                            break;
                        }
                    }
                    if (!ok_) {
                        UndefinedFieldError cast_ = new UndefinedFieldError();
                        cast_.setId(fieldName);
                        cast_.setClassName(annotationClass_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(cast_);
                    }
                }
            }
        }
    }
    @Override
    public void analyzeUnary(Analyzable _conf) {
        MethodOperation mOp_ = getParent();
        LgNames std_ = _conf.getStandards();
        String objCl_ = std_.getAliasObject();
        if (!(mOp_ instanceof AnnotationInstanceOperation)) {
            setResultClass(new ClassArgumentMatching(objCl_));
            return;
        }
        if (!StringList.isWord(fieldName)) {
            setResultClass(new ClassArgumentMatching(objCl_));
            return;
        }
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
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = _nodes.getVal(getFirstChild()).getArgument();
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
