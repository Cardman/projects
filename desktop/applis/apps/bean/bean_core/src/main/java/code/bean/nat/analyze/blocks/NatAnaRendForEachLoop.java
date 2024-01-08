package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatAnaRendForEachLoop extends NatAnaRendParentBlock implements NatRendBuildEl {

    private final String className;

    private String importedClassName;

    private final String variableName;

    private final String expression;

    private NatOperationNode root;

    private final BeanNatCommonLgNames caller;

    NatAnaRendForEachLoop(String _className, String _variable,
                          String _expression, BeanNatCommonLgNames _caller) {
        className = _className;
        variableName = _variable;
        expression = _expression;
        caller = _caller;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        boolean toInfer_ = toInfer();
        if (!toInfer_) {
            importedClassName = className;
        } else {
            importedClassName = AnaRendBlockHelp.EMPTY_STRING;
        }
        root = NatRenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        NatOperationNode root_ = root;
        String names_ = root_.getNames();
        String last_ = getInferredIt(names_);
        if (toInfer_) {
            importedClassName = last_;
        }
        _page.getLoopsVars().put(variableName, "");
        String lInfo_;
        if (!importedClassName.isEmpty()) {
            lInfo_=importedClassName;
        } else {
            lInfo_=BeanNatCommonLgNames.OBJECT;
        }
        _page.getInfosVars().put(variableName, lInfo_);
    }

    private String getInferredIt(String _names) {
        String it_ = caller.getIterables().getVal(_names);
        it_ = StringUtil.nullToEmpty(it_);
        if (StringUtil.quickEq(it_, BeanNatCommonLgNames.OBJECT)) {
            it_ = importedClassName;
        }
        return it_;
//        StringList out_ = new StringList();
//        for (String f: names_) {
//            String it_ = caller.getIterables().getVal(f);
//            it_ = StringUtil.nullToEmpty(it_);
//            if (StringUtil.quickEq(it_, caller.getAliasObject())) {
//                it_ = importedClassName;
//            }
//            String type_ = StringUtil.concat(caller.getContent().getPredefTypes().getAliasIterable(), "<", it_, ">");
//            out_.add(type_);
//        }
//        return StringExpUtil.getAllTypes(out_.first()).last();
    }

    private boolean toInfer() {
        return className.trim().isEmpty();
    }

    public void removeVars(StringMap<String> _infosVars, StringMap<String> _loopsVars) {
        _infosVars.removeKey(variableName);
        _loopsVars.removeKey(variableName);
    }

    public String getVariableName() {
        return variableName;
    }

    public NatOperationNode getRoot() {
        return root;
    }

}
