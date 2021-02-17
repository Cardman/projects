package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaNamedContent;
import code.maths.litteral.StrTypes;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.core.StringUtil;

public final class NamedArgumentOperation extends AbstractUnaryOperation {

    private int offsetTr;
    private final AnaNamedContent namedContent;
    private String name;
    private RootBlock field;
    private int ref=-1;
    private final CustList<NamedFunctionBlock> customMethod = new CustList<NamedFunctionBlock>();
    public NamedArgumentOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        namedContent = new AnaNamedContent();
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        String first_ = vs_.firstValue();
        name = first_.trim();
        offsetTr = StringUtil.getFirstPrintableCharIndex(first_);
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        namedContent.setOffset(offsetTr);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ offsetTr, _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCall(m_)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_page.getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                    name);
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(AnaClassArgumentMatching.copy(child_.getResultClass(), _page.getPrimitiveTypes()));
    }

    public int getIndex() {
        return namedContent.getIndex();
    }

    public void setIndex(int _index) {
        this.namedContent.setIndex(_index);
    }

    public AnaNamedContent getNamedContent() {
        return namedContent;
    }

    public CustList<NamedFunctionBlock> getCustomMethod() {
        return customMethod;
    }

    public String getName() {
        return name;
    }

    public int getOffsetTr() {
        return offsetTr;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int _ref) {
        this.ref = _ref;
    }

    public RootBlock getField() {
        return field;
    }

    public void setField(RootBlock _field) {
        this.field = _field;
    }
}
