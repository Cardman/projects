package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.ResultTernary;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class NullSafeOperation extends MethodOperation {

    public NullSafeOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        OperationNode opTwo_ = chidren_.first();
        OperationNode opThree_ = chidren_.last();
        ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        Argument firstArg_ = opTwo_.getArgument();
        Argument secondArg_ = opThree_.getArgument();
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        StringMap<StringList> vars_ = _conf.getCurrentConstraints();
        String void_ = stds_.getAliasVoid();
        if (StringList.contains(one_, void_)) {
            setRelativeOffsetPossibleAnalyzable(opTwo_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //first part separator
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getVoidType(),
                    void_);
            _conf.addError(un_);
        }
        if (StringList.contains(two_, void_)) {
            setRelativeOffsetPossibleAnalyzable(opThree_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //second part separator
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getVoidType(),
                    void_);
            _conf.addError(un_);
        }
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                if (m_ instanceof CastOperation) {
                    setResultClass(new ClassArgumentMatching(((CastOperation) m_).getClassName()));
                    return;
                }
                break;
            }
            if (m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        Block cur_ = _conf.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            FunctionBlock f_ = _conf.getAnalyzing().getCurrentFct();
            if (f_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                String ret_ = n_.getImportedReturnType();
                if (!StringList.quickEq(ret_, void_)) {
                    type_ = ret_;
                }
            }
        } else if (m_ instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) m_;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                type_ = c_.getSingleNameOrEmpty();
            }
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, firstArg_, two_, secondArg_, vars_, _conf);
        if (!type_.isEmpty() && !StringList.quickEq(type_, keyWordVar_)) {
            setResultClass(new ClassArgumentMatching(type_));
            return;
        }
        setResultClass(new ClassArgumentMatching(res_.getTypes()));
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        tryGetResult(_conf, this);
    }

    public static void tryGetResult(Analyzable _conf, ParentOperable _to) {
        CustList<Operable> children_ = _to.getChildrenOperable();
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (v_ != NullStruct.NULL_VALUE) {
            _to.setSimpleArgumentAna(f_, _conf);
            return;
        }
        if (s_ == null) {
            return;
        }
        v_ = s_.getStruct();
        if (v_ != NullStruct.NULL_VALUE) {
            _to.setSimpleArgumentAna(s_, _conf);
        }
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
}
