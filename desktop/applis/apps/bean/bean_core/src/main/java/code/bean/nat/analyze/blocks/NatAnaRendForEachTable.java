package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.util.StringMap;

public final class NatAnaRendForEachTable extends AnaRendParentBlock implements NatRendBuildEl {

    private final String label;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

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
        variableNameFirst = _variable.getInfo();
        classNameSecond = _classNameSec.getInfo();
        variableNameSecond = _variableSec.getInfo();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        label = _label.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildEl(_anaDoc, _page);
        putVariable(_page);
    }

    public void buildEl(AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        importedClassNameFirst = classNameFirst;
        importedClassNameSecond = classNameSecond;
        root = NatRenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public void putVariable(NatAnalyzedCode _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        _page.getLoopsVars().put(variableNameFirst, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        lInfo_.setClassName(importedClassNameFirst);
        lInfo_.setConstType(ConstType.FIX_VAR);
        lInfo_.setFinalVariable(true);
        _page.getInfosVars().put(variableNameFirst, lInfo_);
        lv_ = new AnaLoopVariable();
        _page.getLoopsVars().put(variableNameSecond, lv_);
        lInfo_ = new AnaLocalVariable();
        lInfo_.setClassName(importedClassNameSecond);
        lInfo_.setConstType(ConstType.FIX_VAR);
        lInfo_.setFinalVariable(true);
        _page.getInfosVars().put(variableNameSecond, lInfo_);
    }

    public void removeVars(StringMap<AnaLocalVariable> _infosVars, StringMap<AnaLoopVariable> _loopsVars) {
        _infosVars.removeKey(variableNameFirst);
        _loopsVars.removeKey(variableNameFirst);
        _infosVars.removeKey(variableNameSecond);
        _loopsVars.removeKey(variableNameSecond);
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
