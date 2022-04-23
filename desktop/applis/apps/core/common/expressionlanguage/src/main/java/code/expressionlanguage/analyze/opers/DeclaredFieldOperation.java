package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ClassField;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DeclaredFieldOperation extends
        SettableAbstractFieldOperation {

    private final InfoBlock infoBlock;
    private final StringList errsField;

    public DeclaredFieldOperation(int _indexInEl, int _indexChild,
                                  MethodOperation _m, OperationsSequence _op, InfoBlock _info) {
        super(_indexInEl, _indexChild, _m, _op);
        infoBlock = _info;
        errsField = _op.getErrors();
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        setStaticAccess(_page.getStaticContext());
        String originalStr_ = getValue();
        String fieldName_ = originalStr_.trim();
        setFieldNameLength(fieldName_.length());
        int valOffset_ = AnaTypeUtil.getIndex(infoBlock, fieldName_);
        getSettableFieldContent().setAnc(0);
        if (valOffset_ < 0) {
            FoundErrorInterpret access_ = new FoundErrorInterpret();
            access_.setFile(_page.getCurrentFile());
            access_.setIndexFile(_page);
            //_name len
            access_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                    fieldName_,
                    _page.getGlobalClass());
            _page.getLocalizer().addError(access_);
            addErr(access_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setFieldType(infoBlock.getDeclaringType());
        setValueOffset(valOffset_);
        getSettableFieldContent().setFinalField(infoBlock.isFinalField());
        getSettableFieldContent().setStaticField(infoBlock.isStaticField());
        getSettableFieldContent().setClassField(new ClassField(infoBlock.getDeclaringType().getFullName(),fieldName_));
        getSettableFieldContent().setRealType(infoBlock.getImportedClassName());
        String c_ = infoBlock.getImportedClassName();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getPrimitiveTypes()));
    }

    @Override
    public int getDelta() {
        String originalStr_ = getValue();
        return StringUtil.getFirstPrintableCharIndex(originalStr_);
    }

    public StringList getErrsField() {
        return errsField;
    }
}
