package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringMap;

public final class NatAnaRendForEachTable extends NatAnaRendParentBlock implements NatRendBuildEl {

    private final String classNameFirst;

    private String importedClassNameFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private final String variableNameFirst;

    private final String variableNameSecond;

    private final String expression;

    private NatOperationNode root;

    NatAnaRendForEachTable(String _className, String _variable,
                           String _classNameSec, String _variableSec,
                           String _expression) {
        super();
        classNameFirst = _className;
        variableNameFirst = _variable;
        classNameSecond = _classNameSec;
        variableNameSecond = _variableSec;
        expression = _expression;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
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

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
