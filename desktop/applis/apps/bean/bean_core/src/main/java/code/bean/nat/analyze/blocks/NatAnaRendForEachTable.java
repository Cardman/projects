package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
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
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        buildEl(_anaDoc, _page);
        putVariable(_page);
    }

    public void buildEl(NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        importedClassNameFirst = classNameFirst;
        importedClassNameSecond = classNameSecond;
        root = NatRenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public void putVariable(NatAnalyzedCode _page) {
        _page.getLoopsVars().put(variableNameFirst, "");
        _page.getInfosVars().put(variableNameFirst, importedClassNameFirst);
        _page.getLoopsVars().put(variableNameSecond, "");
        _page.getInfosVars().put(variableNameSecond, importedClassNameSecond);
    }

    public void removeVars(StringMap<String> _infosVars, StringMap<String> _loopsVars) {
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
