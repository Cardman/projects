package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
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
        String type_ = getType(m_,_page);
        if (type_.isEmpty()) {
            return;
        }
        setResultClass(voidToObject(new AnaClassArgumentMatching(type_),_page));
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        if (isUnexpected(_page)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode firstChild_ = AffectationOperation.getFirstToBeAnalyzed(this);
        OperationNode toAna_ = getAnalyzed(firstChild_);
        if (isLeftValueCall(toAna_) || toAna_ instanceof AbstractRefTernaryOperation || toAna_ instanceof SwitchOperation) {
            procArr(_page, toAna_);
            setResultClass(new AnaClassArgumentMatching(toAna_.getResultClass().getNames()));
            return;
        }
        if (toAna_ instanceof VariableOperationUse) {
            VariableOperationUse v_ = (VariableOperationUse)toAna_;
            processErrorVar(_page, v_);
            setResultClass(new AnaClassArgumentMatching(toAna_.getResultClass().getNames()));
            return;
        }
        if (toAna_ instanceof SettableAbstractFieldOperation) {
            procField(_page, (SettableAbstractFieldOperation) toAna_);
            setResultClass(new AnaClassArgumentMatching(toAna_.getResultClass().getNames()));
            return;
        }
        //key word len
        FoundErrorInterpret varg_ = new FoundErrorInterpret();
        varg_.setFile(_page.getCurrentFile());
        varg_.setIndexFile(_page);
        //key word len
        varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                _page.getKeyWords().getKeyWordThat());
        _page.getLocalizer().addError(varg_);
        addErr(varg_.getBuiltError());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    static void procArr(AnalyzedPageEl _page, OperationNode _ch) {
        ExplicitOperatorOperation.checkSetter(_ch,_page);
    }

    private boolean isUnexpected(AnalyzedPageEl _page) {
        MethodOperation m_ = getParent();
        String type_ = getType(m_,_page);
        AbsLineDeclarator lineDeclarator_ = _page.getLineDeclarator();
        boolean rightAffDecl_ = lineDeclarator_ != null && lineDeclarator_.isRefVariable() && m_ instanceof AffectationOperation && (m_.getParent() == null || m_.getParent() instanceof DeclaringOperation) && m_.getFirstChild() != this;
        return !rightAffDecl_&&type_.isEmpty()&&isNotChildOfCall(m_)&& !(m_ instanceof NamedArgumentOperation)&&!(m_ instanceof AbstractRefTernaryOperation)&&!(m_ instanceof ArgumentListInstancing) || m_ instanceof NamedArgumentOperation && m_.getParent() instanceof CallDynMethodOperation;
    }

    private static String getType(OperationNode _op,AnalyzedPageEl _page) {
        AbsBk cur_ = _page.getCurrentBlock();
        if (!(_op == null && cur_ instanceof ReturnMethod)) {
            return EMPTY_STRING;
        }
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String type_ = EMPTY_STRING;
        if (f_ instanceof NamedFunctionBlock && ((NamedFunctionBlock) f_).isRetRef()) {
            type_ = ((NamedFunctionBlock) f_).getImportedReturnType();
        }
        if (f_ instanceof SwitchMethodBlock && ((SwitchMethodBlock) f_).isRetRef()) {
            type_ = ((SwitchMethodBlock) f_).getRetType();
        }
        return type_;
    }
    private OperationNode getAnalyzed(OperationNode _node) {
        OperationNode toAna_;
        if (_node instanceof DotOperation) {
            toAna_ = ((DotOperation) _node).getChildrenNodes().last();
        } else {
            toAna_ = _node;
        }
        return toAna_;
    }

    private void procField(AnalyzedPageEl _page, SettableAbstractFieldOperation _last) {
        AnaSettableOperationContent settableFieldContent_ = _last.getSettableFieldContent();
        if (settableFieldContent_.isFinalField()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
        }
    }

    public void processErrorVar(AnalyzedPageEl _page, VariableOperationUse _var) {
        if (_var.isFinalVariable()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
        }
    }

    public int getDelta() {
        return delta;
    }
}