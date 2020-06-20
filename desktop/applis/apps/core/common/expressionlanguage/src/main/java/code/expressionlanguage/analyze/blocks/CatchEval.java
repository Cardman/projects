package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.exec.blocks.ExecCatchEval;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class CatchEval extends AbstractCatchEval {

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final String variableName;

    private int variableNameOffset;

    public CatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }
    public int getVariableNameOffset() {
        return variableNameOffset;
    }
    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariable(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecCatchEval exec_ = new ExecCatchEval(getOffset(),variableName,variableNameOffset,importedClassName,partOffsets);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private void processVariable(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        _cont.getCoverage().putCatches(_cont,this);
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsCatchVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
            return;
        }
        if (!ContextUtil.isValidSingleToken(_cont,variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
        }
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(importedClassName);
        _cont.getAnalyzing().putCatchVar(variableName, lv_);
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_an,className);
        partOffsets.addAllElts(_an.getCoverage().getCurrentParts());
        StringList classes_ = new StringList();
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ instanceof CatchEval) {
                classes_.add(((CatchEval)p_).importedClassName);
            }
            if (p_ == null) {
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        _anEl.setArgMapping(importedClassName);
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_an)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
