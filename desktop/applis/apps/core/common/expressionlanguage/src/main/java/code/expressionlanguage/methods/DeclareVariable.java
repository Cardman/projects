package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecDeclareVariable;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.CustList;
import code.util.StringList;

public final class DeclareVariable extends Leaf implements BuildableElMethod {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private boolean finalVariable;

    private int finalVariableOffset;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private ExecDeclareVariable exec;
    public DeclareVariable(OffsetBooleanInfo _finalVar, OffsetStringInfo _className, OffsetsBlock _offset) {
        super(_offset);
        finalVariable = _finalVar.isInfo();
        finalVariableOffset = _finalVar.getOffset();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariable(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        exec = new ExecDeclareVariable(getOffset(),className,classNameOffset,importedClassName,variableNames,partOffsets);
        page_.getBlockToWrite().appendChild(exec);
        page_.getAnalysisAss().getMappingMembers().put(exec,this);
        _cont.getCoverage().putBlockOperations(_cont, exec,this);
    }

    private void processVariable(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
            partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
        }
        page_.setMerged(true);
        page_.setAcceptCommaInstr(true);
        page_.setFinalVariable(finalVariable);
        page_.setCurrentVarSetting(importedClassName);
        page_.getVariablesNames().clear();
        page_.getVariablesNamesToInfer().clear();
    }

    public ExecDeclareVariable getExec() {
        return exec;
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
}
