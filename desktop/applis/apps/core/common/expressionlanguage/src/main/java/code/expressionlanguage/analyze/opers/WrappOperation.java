package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.maths.litteralcom.StrTypes;

public final class WrappOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {
    private int offset;
    private final int delta;
    public WrappOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, int _delta) {
        super(_index, _indexChild, _m, _op);
        delta = _delta;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation m_ = getParent();
        boolean retRef_ = false;
        AbsBk cur_ = _page.getCurrentBlock();
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String type_ = EMPTY_STRING;
        if (m_ == null && f_ instanceof NamedFunctionBlock && cur_ instanceof ReturnMethod) {
            if (((NamedFunctionBlock)f_).isRetRef()) {
                retRef_ = true;
                type_ = ((NamedFunctionBlock)f_).getImportedReturnType();
            }
        }
        if (m_ == null && f_ instanceof SwitchMethodBlock && cur_ instanceof ReturnMethod) {
            if (((SwitchMethodBlock)f_).isRetRef()) {
                retRef_ = true;
                type_ = ((SwitchMethodBlock)f_).getRetType();
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
        AbsBk cur_ = _page.getCurrentBlock();
        MemberCallingsBlock f_ = _page.getCurrentFct();
        if (m_ == null && f_ instanceof NamedFunctionBlock && cur_ instanceof ReturnMethod) {
            if (((NamedFunctionBlock)f_).isRetRef()) {
                retRef_ = true;
            }
        }
        if (m_ == null && f_ instanceof SwitchMethodBlock && cur_ instanceof ReturnMethod) {
            if (((SwitchMethodBlock)f_).isRetRef()) {
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
        if (!rightAffDecl_&&!retRef_&&isNotChildOfCall(m_)&& !(m_ instanceof NamedArgumentOperation)&&!(m_ instanceof AbstractRefTernaryOperation)&&!(m_ instanceof ArgumentListInstancing)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
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
            varg_.setFile(_page.getCurrentFile());
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
        if (isLeftValueCall(firstChild_)) {
            setResultClass(new AnaClassArgumentMatching(firstChild_.getResultClass().getNames()));
            return;
        }
        if (firstChild_ instanceof VariableOperationUse) {
            VariableOperationUse v_ = (VariableOperationUse)firstChild_;
            processErrorVar(_page, v_);
            setResultClass(new AnaClassArgumentMatching(firstChild_.getResultClass().getNames()));
            return;
        }
        if (!(firstChild_ instanceof SettableAbstractFieldOperation) && !(firstChild_ instanceof DotOperation) && !(firstChild_ instanceof AbstractRefTernaryOperation) && !(firstChild_ instanceof SwitchOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
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
                procField(_page, (SettableAbstractFieldOperation) last_);
                return;
            }
            if (isLeftValueCall(last_)) {
                setResultClass(AnaClassArgumentMatching.copy(last_.getResultClass(),_page.getPrimitiveTypes()));
                return;
            }
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
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
            procField(_page, (SettableAbstractFieldOperation) firstChild_);
            return;
        }
        if (firstChild_ instanceof AbstractRefTernaryOperation) {
            AbstractRefTernaryOperation v_ = (AbstractRefTernaryOperation)firstChild_;
            setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(), _page.getPrimitiveTypes()));
            return;
        }
        SwitchOperation v_ = (SwitchOperation)firstChild_;
        setResultClass(AnaClassArgumentMatching.copy(v_.getResultClass(), _page.getPrimitiveTypes()));
    }

    private void procField(AnalyzedPageEl _page, SettableAbstractFieldOperation _last) {
        AnaSettableOperationContent settableFieldContent_ = _last.getSettableFieldContent();
        if (settableFieldContent_.isFinalField()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
        }
        setResultClass(AnaClassArgumentMatching.copy(_last.getResultClass(), _page.getPrimitiveTypes()));
    }

    public void processErrorVar(AnalyzedPageEl _page, VariableOperationUse _var) {
        if (_var.isFinalVariable()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
        }
    }

    public int getDelta() {
        return delta;
    }
}