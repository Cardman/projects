package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.ImportForEachLoop;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;
import code.util.StringMap;

public final class AnaRendForEachLoop extends AnaRendParentBlock implements AnaRendLoop,ImportForEachLoop {

    private String label;
    private int labelOffset;

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableName;

    private int variableNameOffset;

    private final String expression;

    private int expressionOffset;

    private OperationNode root;
    private boolean okVar = true;
    AnaRendForEachLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                       OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, LgNames _stds) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _stds.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        OperationNode root_ = buildEl(_cont, _doc, _anaDoc, _page);
        Argument arg_ = root_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_anaDoc.getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getStandards().getAliasNullPe());
            Configuration.addError(static_, _anaDoc, _page);
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
    public OperationNode buildEl(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            Configuration.addError(cast_, _anaDoc, _page);
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            okVar = false;
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            Configuration.addError(b_, _anaDoc, _page);
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        if (!toInfer(_page)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        } else {
            importedClassName = EMPTY_STRING;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrList());
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
                Configuration.addError(cast_, _anaDoc, _page);
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
        }
    }
    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = _page.getStandards().getCustomType(_types,importedClassName, _page);
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _page.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _page.getStandards().getAliasObject();
            }
            if (toInfer(_page)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    Configuration.addError(cast_, _anaDoc, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getStandards().getAliasObject(),
                    _page.getStandards().getAliasIterable());
            Configuration.addError(cast_, _anaDoc, _page);
        }
    }
    private boolean toInfer(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringList.quickEq(className.trim(), keyWordVar_) || className.trim().isEmpty();
    }
    public void putVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        _page.getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getStandards().getAliasObject());
        }
        lInfo_.setConstType(ConstType.FIX_VAR);
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
}
