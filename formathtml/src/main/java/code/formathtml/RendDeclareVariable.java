package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.StringList;

public final class RendDeclareVariable extends RendLeaf implements RendInitVariable,RendBuildableElMethod {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    RendDeclareVariable(OffsetStringInfo _className, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
    }

    @Override
    public StringList getVariableNames() {
        return variableNames;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = _cont.resolveCorrectType(className);
        }
        _cont.setMerged(true);
        _cont.setFinalVariable(false);
        _cont.setCurrentVarSetting(importedClassName);
        _cont.getVariablesNames().clear();
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont);
        for (String v: getVariableNames()) {
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(importedClassName);
            lv_.setStruct(struct_);
            ip_.putLocalVar(v, lv_);
        }
        processBlock(_cont);
    }
}
