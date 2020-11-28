package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DeclareVariable extends Leaf implements BuildableElMethod {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private boolean finalVariable;

    private int finalVariableOffset;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String errInf = EMPTY_STRING;
    private boolean refVariable;

    public DeclareVariable(OffsetBooleanInfo _finalVar, OffsetStringInfo _className, OffsetsBlock _offset, boolean _refVariable) {
        super(_offset);
        finalVariable = _finalVar.isInfo();
        finalVariableOffset = _finalVar.getOffset();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        refVariable = _refVariable;
    }

    public StringList getVariableNames() {
        return variableNames;
    }
    public int getClassNameOffset() {
        return classNameOffset;
    }

    public String getClassName() {
        return className;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        }
        _page.setMerged(true);
        _page.setRefVariable(refVariable);
        _page.setAcceptCommaInstr(true);
        _page.setFinalVariable(finalVariable);
        _page.setCurrentVarSetting(importedClassName);
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        //        ExecDeclareVariable exec_ = new ExecDeclareVariable(getOffset(), importedClassName,variableNames);
//        _page.setExecDeclareVariable(exec_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    public boolean isRefVariable() {
        return refVariable;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getFinalVariableOffset() {
        return finalVariableOffset;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public String getErrInf() {
        return errInf;
    }

    public void setErrInf(String _errInf) {
        errInf = _errInf;
    }
}
