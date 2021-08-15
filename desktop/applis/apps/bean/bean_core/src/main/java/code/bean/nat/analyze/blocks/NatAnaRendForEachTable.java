package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBuildEl;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;

public final class NatAnaRendForEachTable extends AnaRendParentBlock implements AnaRendBuildEl {

    private final String label;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private final int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private final int classNameOffsetSecond;

    private final String variableNameFirst;

    private final String variableNameSecond;

    private final String expression;

    private final int expressionOffset;

    private NatOperationNode root;

    NatAnaRendForEachTable(OffsetStringInfo _className, OffsetStringInfo _variable,
                           OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                           OffsetStringInfo _expression, OffsetStringInfo _label, int _offset) {
        super(_offset);
        classNameFirst = _className.getInfo();
        classNameOffsetFirst = _className.getOffset();
        variableNameFirst = _variable.getInfo();
        classNameSecond = _classNameSec.getInfo();
        classNameOffsetSecond = _classNameSec.getOffset();
        variableNameSecond = _variableSec.getInfo();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        label = _label.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildEl(_anaDoc, _page);
        putVariable(_page);
    }

    public void buildEl(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffsetFirst);
        _page.zeroOffset();
        importedClassNameFirst = classNameFirst;
        _page.setGlobalOffset(classNameOffsetSecond);
        _page.zeroOffset();
        importedClassNameSecond = classNameSecond;
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        root = NatRenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public void putVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        _page.getLoopsVars().put(variableNameFirst, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        lInfo_.setClassName(importedClassNameFirst);
        lInfo_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableNameFirst, lInfo_);
        lv_ = new AnaLoopVariable();
        _page.getLoopsVars().put(variableNameSecond, lv_);
        lInfo_ = new AnaLocalVariable();
        lInfo_.setClassName(importedClassNameSecond);
        lInfo_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableNameSecond, lInfo_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableNameFirst);
        _ip.getLoopsVars().removeKey(variableNameFirst);
        _ip.getInfosVars().removeKey(variableNameSecond);
        _ip.getLoopsVars().removeKey(variableNameSecond);
    }
    public String getRealLabel() {
        return label;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
