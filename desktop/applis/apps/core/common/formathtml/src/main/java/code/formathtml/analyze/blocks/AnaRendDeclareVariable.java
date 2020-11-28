package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendDeclareVariable extends AnaRendLeaf {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;
    private boolean refVariable;
    AnaRendDeclareVariable(OffsetBooleanInfo _refVar, OffsetStringInfo _className, OffsetsBlock _offset) {
        super(_offset);
        refVariable = _refVar.isInfo();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
        }
        _page.setMerged(true);
        _page.setRefVariable(refVariable);
        _page.setAcceptCommaInstr(true);
        _page.setFinalVariable(false);
        _page.setCurrentVarSetting(importedClassName);
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
    }

    public boolean isRefVariable() {
        return refVariable;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

}
