package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.ImportForEachLoop;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendForEachLoop extends AnaRendParentBlock implements AnaRendLoop,ImportForEachLoop {

    private final String label;
    private final int labelOffset;

    private final String className;

    private String importedClassName;

    private final int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;

    private final String variableName;

    private final int variableNameOffset;

    private final String expression;

    private final int expressionOffset;

    private OperationNode root;
    private boolean okVar = true;
    private final boolean refVariable;
    private boolean refVar;
    AnaRendForEachLoop(OffsetBooleanInfo _refVar, OffsetStringInfo _className, OffsetStringInfo _variable,
                       OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, int _offset, PrimitiveTypes _primTypes) {
        super(_offset);
        refVariable = _refVar.isInfo();
        refVar = _refVar.isInfo();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _primTypes.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        OperationNode root_ = buildEl(_anaDoc, _page);
        Argument arg_ = root_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_anaDoc.getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            AnalyzingDoc.addError(static_, _anaDoc, _page);
        } else if (root_.getResultClass().isArray()) {
            inferArrayClass(_anaDoc, root_, _page);
        } else {
            StringList names_ = root_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _page);
            checkIterableCandidates(out_, _anaDoc, _page);
        }
        if (!okVar) {
            return;
        }
        putVariable(_page);
    }
    public OperationNode buildEl(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            AnalyzingDoc.addError(cast_, _anaDoc, _page);
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            okVar = false;
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            AnalyzingDoc.addError(b_, _anaDoc, _page);
        }
        _page.setGlobalOffset(classNameOffset);
        _page.zeroOffset();
        if (!toInfer(_page)) {
            importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
        } else {
            importedClassName = EMPTY_STRING;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrList());
        root = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        return root;
    }
    public void inferArrayClass(AnalyzingDoc _anaDoc, OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching compo_ = AnaTypeUtil.getQuickComponentType(_root.getResultClass());
        if (toInfer(_page) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            if (importedClassName.isEmpty()) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_anaDoc.getFileName());
                cast_.setIndexFile(expressionOffset);
                cast_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        className.trim());
                AnalyzingDoc.addError(cast_, _anaDoc, _page);
            } else {
                if (refVariable) {
                    if (!compo_.matchClass(importedClassName)) {
                        refVar = false;
                    }
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    AnalyzingDoc.addError(cast_, _anaDoc, _page);
                }
            }
        }
    }
    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = _page.getForEachFetch().getCustomType(_types,importedClassName);
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (refVariable) {
            refVar = false;
        }
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
            } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)){
                paramArg_ = _page.getAliasObject();
            }
            if (toInfer(_page)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    AnalyzingDoc.addError(cast_, _anaDoc, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterable());
            AnalyzingDoc.addError(cast_, _anaDoc, _page);
        }
    }
    private boolean toInfer(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(className.trim(), keyWordVar_) || className.trim().isEmpty();
    }
    public void putVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        _page.getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getAliasObject());
        }
        if (refVar) {
            lInfo_.setConstType(ConstType.REF_LOC_VAR);
        } else {
            lInfo_.setConstType(ConstType.FIX_VAR);
        }
        _page.getInfosVars().put(variableName, lInfo_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableName);
        _ip.getLoopsVars().removeKey(variableName);
    }
    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public OperationNode getRoot() {
        return root;
    }

    public boolean isRefVar() {
        return refVar;
    }
}
