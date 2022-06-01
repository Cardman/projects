package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.ForEachLoop;
import code.expressionlanguage.analyze.blocks.ImportForEachTable;
import code.expressionlanguage.analyze.blocks.TableLoopExpressionContent;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
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

public final class AnaRendForEachTable extends AnaRendParentBlock implements AnaRendLocBreakableBlock,ImportForEachTable {

    private String importedClassNameFirst;

    private String importedClassNameSecond;

    private final String label;
    private final int labelOffset;
    private final TableLoopExpressionContent tableLoopExpressionContent;
    private final ResultExpression resultExpression = new ResultExpression();

    private OperationNode root;
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;

    AnaRendForEachTable(TableLoopExpressionContent _content, OffsetStringInfo _classIndex, int _offset) {
        super(_offset);
        label = _classIndex.getInfo();
        labelOffset = _classIndex.getOffset();
        tableLoopExpressionContent = _content;
    }

    @Override
    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    @Override
    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildEl(_anaDoc, _page);
        Argument arg_ = root.getArgument();
        if (Argument.isNullValue(arg_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            AnalyzingDoc.addError(static_, _page);
        } else {
            StringList names_ = root.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _page);
            checkIterableCandidates(out_, _page);
        }
        putVariable(_page);
    }

    private StringList getCustomType(StringList _names, AnalyzedPageEl _page) {
        return ContextUtil.getCustomTableType(_names, _page).getClassName();
    }

    public void buildEl(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        tableLoopExpressionContent.setImportedClassIndexName(ResolvingTypes.resolveCorrectType(tableLoopExpressionContent.getClassIndexName(), _page).getResult(_page));
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(tableLoopExpressionContent.getImportedClassIndexName()), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(tableLoopExpressionContent.getClassIndexNameOffset());
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    tableLoopExpressionContent.getImportedClassIndexName());
            AnalyzingDoc.addError(cast_, _page);
        }
        TokenErrorMessage resOne_ = ManageTokens.partVar(_page).checkTokenVar(tableLoopExpressionContent.getVariableNameFirst(), _page);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetFirst());
            b_.setBuiltError(resOne_.getMessage());
            AnalyzingDoc.addError(b_, _page);
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_page).checkTokenVar(tableLoopExpressionContent.getVariableNameSecond(), _page);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetSecond());
            b_.setBuiltError(resTwo_.getMessage());
            AnalyzingDoc.addError(b_, _page);
            okVarSecond = false;
        }
        _page.setSumOffset(tableLoopExpressionContent.getClassNameOffsetFirst());
        _page.zeroOffset();
        if (!toInferFirst(_page)) {
            importedClassNameFirst = ResolvingTypes.resolveCorrectType(tableLoopExpressionContent.getClassNameFirst(), _page).getResult(_page);
        } else {
            importedClassNameFirst = EMPTY_STRING;
        }
        _page.setSumOffset(tableLoopExpressionContent.getClassNameOffsetSecond());
        _page.zeroOffset();
        if (!toInferSecond(_page)) {
            importedClassNameSecond = ResolvingTypes.resolveCorrectType(tableLoopExpressionContent.getClassNameSecond(), _page).getResult(_page);
        } else {
            importedClassNameSecond = EMPTY_STRING;
        }
        _page.setSumOffset(resultExpression.getSumOffset());
        _page.zeroOffset();
        root = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpression);
    }

    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (!_types.onlyOneElt()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterableTable());
            AnalyzingDoc.addError(cast_, _page);
            return;
        }
        String type_ = _types.first();
        Mapping mapping_ = new Mapping();
        String paramArg_ = ForEachLoop.extType(_page, StringExpUtil.getAllTypes(type_).get(1));
        if (toInferFirst(_page)) {
            importedClassNameFirst = paramArg_;
        } else {
            mapping_.setArg(paramArg_);
            mapping_.setParam(importedClassNameFirst);
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            mapping_.setMapping(vars_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        paramArg_,
                        importedClassNameFirst);
                AnalyzingDoc.addError(cast_, _page);
            }
        }
        mapping_ = new Mapping();
        paramArg_ = ForEachLoop.extType(_page, StringExpUtil.getAllTypes(type_).last());
        if (toInferSecond(_page)) {
            importedClassNameSecond = paramArg_;
        } else {
            mapping_.setArg(paramArg_);
            mapping_.setParam(importedClassNameSecond);
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            mapping_.setMapping(vars_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        paramArg_,
                        importedClassNameSecond);
                AnalyzingDoc.addError(cast_, _page);
            }
        }
    }

    public void putVariable(AnalyzedPageEl _page) {
        if (okVarFirst && okVarSecond && StringUtil.quickEq(tableLoopExpressionContent.getVariableNameFirst(), tableLoopExpressionContent.getVariableNameSecond())) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(_page.getCurrentFile());
            d_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetSecond());
            d_.buildError(_page.getAnalysisMessages().getBadVariableName(),
                    tableLoopExpressionContent.getVariableNameFirst());
            AnalyzingDoc.addError(d_, _page);
            return;
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(tableLoopExpressionContent.getImportedClassIndexName());
            _page.getLoopsVars().put(tableLoopExpressionContent.getVariableNameFirst(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(tableLoopExpressionContent.getVariableNameFirst(), lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(tableLoopExpressionContent.getImportedClassIndexName());
            _page.getLoopsVars().put(tableLoopExpressionContent.getVariableNameSecond(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(tableLoopExpressionContent.getVariableNameSecond(), lInfo_);
        }
    }

    private boolean toInferFirst(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(tableLoopExpressionContent.getClassNameFirst().trim(), keyWordVar_) || tableLoopExpressionContent.getClassNameFirst().trim().isEmpty();
    }

    private boolean toInferSecond(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(tableLoopExpressionContent.getClassNameSecond().trim(), keyWordVar_) || tableLoopExpressionContent.getClassNameSecond().trim().isEmpty();
    }
    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(tableLoopExpressionContent.getVariableNameFirst());
        _ip.getLoopsVars().removeKey(tableLoopExpressionContent.getVariableNameFirst());
        _ip.getInfosVars().removeKey(tableLoopExpressionContent.getVariableNameSecond());
        _ip.getLoopsVars().removeKey(tableLoopExpressionContent.getVariableNameSecond());
    }
    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    public String getVariableNameSecond() {
        return tableLoopExpressionContent.getVariableNameSecond();
    }

    public String getVariableNameFirst() {
        return tableLoopExpressionContent.getVariableNameFirst();
    }

    public String getExpression() {
        return tableLoopExpressionContent.getExpression();
    }

    public ResultExpression getRes() {
        return resultExpression;
    }
    public int getExpressionOffset() {
        return tableLoopExpressionContent.getExpressionOffset();
    }

    public String getImportedClassIndexName() {
        return tableLoopExpressionContent.getImportedClassIndexName();
    }

    public OperationNode getRoot() {
        return root;
    }
}
