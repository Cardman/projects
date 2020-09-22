package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendDeclareVariable extends RendLeaf implements RendWithEl {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    RendDeclareVariable(OffsetStringInfo _className, OffsetsBlock _offset) {
        super(_offset);
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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        }
        _page.setMerged(true);
        _page.setAcceptCommaInstr(true);
        _page.setFinalVariable(false);
        _page.setCurrentVarSetting(importedClassName);
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _cont.getContext());
        for (String v: getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,importedClassName);
            ip_.putLocalVar(v, lv_);
        }
        processBlock(_cont);
    }

    public String getImportedClassName() {
        return importedClassName;
    }
}
