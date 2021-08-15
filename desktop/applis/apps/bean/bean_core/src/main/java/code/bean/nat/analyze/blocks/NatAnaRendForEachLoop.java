package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatLgNames;
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
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendForEachLoop extends AnaRendParentBlock implements AnaRendBuildEl {

    private final String label;

    private final String className;

    private String importedClassName;

    private final int classNameOffset;

    private final String variableName;

    private final String expression;

    private final int expressionOffset;

    private NatOperationNode root;

    private final BeanNatLgNames caller;

    NatAnaRendForEachLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                          OffsetStringInfo _expression, OffsetStringInfo _label, int _offset, BeanNatLgNames _caller) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        label = _label.getInfo();
        caller = _caller;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classNameOffset);
        _page.zeroOffset();
        boolean toInfer_ = toInfer();
        if (!toInfer_) {
            importedClassName = className;
        } else {
            importedClassName = AnaRendBlockHelp.EMPTY_STRING;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrList());
        root = NatRenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        NatOperationNode root_ = root;
        String names_ = root_.getNames();
        String last_ = getInferredIt(names_);
        if (toInfer_) {
            importedClassName = last_;
        }
        AnaLoopVariable lv_ = new AnaLoopVariable();
        _page.getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getAliasObject());
        }
        lInfo_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableName, lInfo_);
    }

    private String getInferredIt(String _names) {
        String it_ = caller.getIterables().getVal(_names);
        it_ = StringUtil.nullToEmpty(it_);
        if (StringUtil.quickEq(it_, caller.getAliasObject())) {
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

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableName);
        _ip.getLoopsVars().removeKey(variableName);
    }
    public String getRealLabel() {
        return label;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public NatOperationNode getRoot() {
        return root;
    }

}
