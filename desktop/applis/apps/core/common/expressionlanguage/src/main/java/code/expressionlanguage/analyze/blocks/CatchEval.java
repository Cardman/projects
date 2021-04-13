package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;
import code.util.StringList;

public final class CatchEval extends AbstractCatchEval {

    private final String className;

    private String importedClassName;

    private final int classNameOffset;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final String variableName;

    private final int variableNameOffset;

    private final StringList nameErrors = new StringList();

    public CatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, int _offset) {
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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffset);
        _page.zeroOffset();
        importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        processVariable(_page);
//        ExecCatchEval exec_ = new ExecCatchEval(getOffset(),variableName, importedClassName);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void processVariable(AnalyzedPageEl _page) {
//        _page.getCoverage().putCatches(this);
        _page.setGlobalOffset(variableNameOffset);
        _page.zeroOffset();
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name
            d_.setBuiltError(res_.getMessage());
            _page.addLocError(d_);
            nameErrors.add(d_.getBuiltError());
            return;
        }
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(importedClassName);
        lv_.setRef(variableNameOffset);
        lv_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableName, lv_);
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

    public String getImportedClassName() {
        return importedClassName;
    }
}
