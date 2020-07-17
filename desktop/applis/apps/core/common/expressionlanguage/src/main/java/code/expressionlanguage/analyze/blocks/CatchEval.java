package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
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

    private final StringList nameErrors = new StringList();

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
        ExecCatchEval exec_ = new ExecCatchEval(getOffset(),variableName, importedClassName);
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
        TokenErrorMessage res_ = ManageTokens.partVar(_cont).checkStdTokenVar(_cont,variableName);
        if (res_.isError()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name
            d_.setBuiltError(res_.getMessage());
            _cont.addError(d_);
            nameErrors.add(d_.getBuiltError());
            return;
        }
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(importedClassName);
        lv_.setRef(variableNameOffset);
        lv_.setConstType(ConstType.FIX_VAR);
        _cont.getAnalyzing().getInfosVars().put(variableName, lv_);
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_an,className);
        partOffsets.addAllElts(_an.getAnalyzing().getCurrentParts());
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

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableName);
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public StringList getNameErrors() {
        return nameErrors;
    }
}
