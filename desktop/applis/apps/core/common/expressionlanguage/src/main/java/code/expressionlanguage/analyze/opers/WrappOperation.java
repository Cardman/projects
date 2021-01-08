package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.util.IntTreeMap;

public final class WrappOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {
    private int offset;
    private final int delta;
    public WrappOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, int _delta) {
        super(_index, _indexChild, _m, _op);
        delta = _delta;
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation m_ = getParent();
        boolean retRef_ = false;
        Block cur_ = _page.getCurrentBlock();
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String type_ = EMPTY_STRING;
        if (m_ == null && f_ instanceof NamedFunctionBlock && cur_ instanceof ReturnMethod) {
            if (((NamedFunctionBlock)f_).isRetRef()) {
                retRef_ = true;
                type_ = ((NamedFunctionBlock)f_).getImportedReturnType();
            }
        }
        if (!retRef_) {
            return;
        }
        setResultClass(voidToObject(new AnaClassArgumentMatching(type_),_page));
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        MethodOperation m_ = getParent();
        boolean retRef_ = false;
        Block cur_ = _page.getCurrentBlock();
        MemberCallingsBlock f_ = _page.getCurrentFct();
        if (m_ == null && f_ instanceof NamedFunctionBlock && cur_ instanceof ReturnMethod) {
            if (((NamedFunctionBlock)f_).isRetRef()) {
                retRef_ = true;
            }
        }
        boolean rightAffDecl_ = false;
        if (_page.isRefVariable() && m_ instanceof AffectationOperation) {
            if (m_.getParent() == null || m_.getParent() instanceof DeclaringOperation) {
                if (m_.getFirstChild() != this) {
                    rightAffDecl_ = true;
                }
            }
        }
        if (!rightAffDecl_&&!retRef_&&isNotChildOfCall(m_)&& !(m_ instanceof NamedArgumentOperation)&&!(m_ instanceof AbstractRefTernaryOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (m_ instanceof NamedArgumentOperation && m_.getParent() instanceof CallDynMethodOperation) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode firstChild_ = AffectationOperation.getFirstToBeAnalyzed(this);
        if (firstChild_ instanceof RefParamOperation || isLeftValueCall(firstChild_)) {
            setResultClass(new AnaClassArgumentMatching(firstChild_.getResultClass().getNames()));
            return;
        }
        if (!(firstChild_ instanceof RefVariableOperation)&&!(firstChild_ instanceof VariableOperation)&&!(firstChild_ instanceof MutableLoopVariableOperation)&&!(firstChild_ instanceof SettableAbstractFieldOperation)&&!(firstChild_ instanceof DotOperation)&&!(firstChild_ instanceof AbstractRefTernaryOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (firstChild_ instanceof DotOperation) {
            OperationNode last_ = ((DotOperation) firstChild_).getChildrenNodes().last();
            if (last_ instanceof SettableAbstractFieldOperation) {
                SettableAbstractFieldOperation v_ = (SettableAbstractFieldOperation) last_;
                AnaSettableOperationContent settableFieldContent_ = v_.getSettableFieldContent();
                if (settableFieldContent_.isFinalField()) {
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_page.getLocalizer().getCurrentFileName());
                    varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                            _page.getKeyWords().getKeyWordThat());
                    _page.getLocalizer().addError(varg_);
                    addErr(varg_.getBuiltError());
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(),_page.getPrimitiveTypes()));
                return;
            }
            if (last_ instanceof ArrOperation) {
                ArrOperation v_ = (ArrOperation) last_;
                if (!v_.getPreviousResultClass().isArray()) {
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFileName(_page.getLocalizer().getCurrentFileName());
                    varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                            _page.getKeyWords().getKeyWordThat());
                    _page.getLocalizer().addError(varg_);
                    addErr(varg_.getBuiltError());
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(),_page.getPrimitiveTypes()));
                return;
            }
            if (isLeftValueCall(last_)) {
                setResultClass(AnaClassArgumentMatching.copy(last_.getResultClass(),_page.getPrimitiveTypes()));
                return;
            }
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation v_ = (SettableAbstractFieldOperation)firstChild_;
            AnaSettableOperationContent settableFieldContent_ = v_.getSettableFieldContent();
            if (settableFieldContent_.isFinalField()) {
                FoundErrorInterpret varg_ = new FoundErrorInterpret();
                varg_.setFileName(_page.getLocalizer().getCurrentFileName());
                varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                        _page.getKeyWords().getKeyWordThat());
                _page.getLocalizer().addError(varg_);
                addErr(varg_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(),_page.getPrimitiveTypes()));
            return;
        }
        if (firstChild_ instanceof VariableOperation) {
            VariableOperation v_ = (VariableOperation)firstChild_;
            AnaLocalVariable var_ = _page.getInfosVars().getVal(v_.getVariableName());
            processErrorVar(_page, var_);
            return;
        }
        if (firstChild_ instanceof RefVariableOperation) {
            RefVariableOperation v_ = (RefVariableOperation)firstChild_;
            setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(), _page.getPrimitiveTypes()));
            return;
        }
        if (firstChild_ instanceof AbstractRefTernaryOperation) {
            AbstractRefTernaryOperation v_ = (AbstractRefTernaryOperation)firstChild_;
            setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(), _page.getPrimitiveTypes()));
            return;
        }
        MutableLoopVariableOperation v_ = (MutableLoopVariableOperation)firstChild_;
        AnaLocalVariable var_ = _page.getInfosVars().getVal(v_.getVariableName());
        if (var_ == null || var_.isFinalVariable()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(var_.getClassName()));
    }

    public void processErrorVar(AnalyzedPageEl _page, AnaLocalVariable _var) {
        if (_var == null || _var.isFinalVariable()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(_var.getClassName()));
        return;
    }

    public int getDelta() {
        return delta;
    }
}