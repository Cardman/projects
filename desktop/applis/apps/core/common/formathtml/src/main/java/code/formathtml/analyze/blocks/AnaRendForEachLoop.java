package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.ForEachLoop;
import code.expressionlanguage.analyze.blocks.ImportForEachLoop;
import code.expressionlanguage.analyze.blocks.ListLoopExpressionContent;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendForEachLoop extends AnaRendParentBlock implements AnaRendLocBreakableBlock,ImportForEachLoop {

    private final String label;
    private final int labelOffset;

    private String importedClassName;

    private final ListLoopExpressionContent listLoopExpressionContent;
    private final ResultExpression resultExpression = new ResultExpression();

    private OperationNode root;
    private boolean okVar = true;
    private final boolean refVariable;
    private boolean refVar;

    AnaRendForEachLoop(OffsetBooleanInfo _refVar, ListLoopExpressionContent _content, OffsetStringInfo _classIndex, int _offset) {
        super(_offset);
        listLoopExpressionContent = _content;
        refVariable = _refVar.isInfo();
        refVar = _refVar.isInfo();
        label = _classIndex.getInfo();
        labelOffset = _classIndex.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        OperationNode root_ = buildEl(_anaDoc, _page);
        Argument arg_ = root_.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            AnalyzingDoc.addError(static_, _page);
        } else if (root_.getResultClass().isArray()) {
            inferArrayClass(root_, _page);
        } else {
            StringList names_ = root_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _page);
            checkIterableCandidates(out_, _page);
        }
        if (!okVar) {
            return;
        }
        putVariable(_page);
    }
    public OperationNode buildEl(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        listLoopExpressionContent.setImportedClassIndexName(ResolvingTypes.resolveCorrectType(listLoopExpressionContent.getClassIndexName(), _page).getResult(_page));
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(listLoopExpressionContent.getImportedClassIndexName()), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(listLoopExpressionContent.getClassIndexNameOffset());
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    listLoopExpressionContent.getImportedClassIndexName());
            AnalyzingDoc.addError(cast_, _page);
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(listLoopExpressionContent.getVariableName(), _page);
        if (res_.isError()) {
            okVar = false;
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(listLoopExpressionContent.getVariableNameOffset());
            b_.setBuiltError(res_.getMessage());
            AnalyzingDoc.addError(b_, _page);
        }
        _page.setSumOffset(listLoopExpressionContent.getClassNameOffset());
        _page.zeroOffset();
        if (!toInfer(_page)) {
            importedClassName = ResolvingTypes.resolveCorrectType(listLoopExpressionContent.getClassName(), _page).getResult(_page);
        } else {
            importedClassName = EMPTY_STRING;
        }
        _page.setSumOffset(resultExpression.getSumOffset());
        _page.zeroOffset();
        root = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpression);
        return root;
    }
    public void inferArrayClass(OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching compo_ = AnaTypeUtil.getQuickComponentType(_root.getResultClass());
        if (toInfer(_page) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            if (importedClassName.isEmpty()) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                cast_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        listLoopExpressionContent.getClassName().trim());
                AnalyzingDoc.addError(cast_, _page);
            } else {
                if (refVariable && !compo_.matchClass(importedClassName)) {
                    refVar = false;
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(compo_.getNames(),AND_ERR),
                            importedClassName);
                    AnalyzingDoc.addError(cast_, _page);
                }
            }
        }
    }
    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(_types,_page);
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (refVariable) {
            refVar = false;
        }
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = ForEachLoop.extType(_page, StringExpUtil.getAllTypes(type_).last());
            if (toInfer(_page)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    AnalyzingDoc.addError(cast_, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterable());
            AnalyzingDoc.addError(cast_, _page);
        }
    }

    private boolean toInfer(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(listLoopExpressionContent.getClassName().trim(), keyWordVar_) || listLoopExpressionContent.getClassName().trim().isEmpty();
    }
    public void putVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setIndexClassName(listLoopExpressionContent.getImportedClassIndexName());
        _page.getLoopsVars().put(listLoopExpressionContent.getVariableName(), lv_);
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
            lInfo_.setFinalVariable(true);
        }
        _page.getInfosVars().put(listLoopExpressionContent.getVariableName(), lInfo_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(listLoopExpressionContent.getVariableName());
        _ip.getLoopsVars().removeKey(listLoopExpressionContent.getVariableName());
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
        return listLoopExpressionContent.getImportedClassIndexName();
    }

    public String getVariableName() {
        return listLoopExpressionContent.getVariableName();
    }

    public String getExpression() {
        return listLoopExpressionContent.getExpression();
    }

    public ResultExpression getRes() {
        return resultExpression;
    }

    public int getExpressionOffset() {
        return listLoopExpressionContent.getExpressionOffset();
    }

    public OperationNode getRoot() {
        return root;
    }

    public boolean isRefVar() {
        return refVar;
    }
}
