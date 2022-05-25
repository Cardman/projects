package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ForEachTable extends AbstractForLoop implements ImportForEachTable {

    private String importedClassNameFirst;

    private String importedClassNameSecond;

    private final TableLoopExpressionContent tableLoopExpressionContent;
    private final ResultExpression res = new ResultExpression();
    private AnaResultPartType partOffsetsFirst = new AnaResultPartType();

    private AnaResultPartType partOffsetsSecond = new AnaResultPartType();

    private final StringList nameErrorsFirst = new StringList();
    private final StringList nameErrorsSecond = new StringList();

    private final int sepOffset;
    private final StringList sepErrors = new StringList();
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;
    private final boolean refVariable;

    public ForEachTable(TableLoopExpressionContent _cont, int _offset, int _sepOffset, boolean _refVariable, OffsetStringInfo _label) {
        super(_offset, _label);
        tableLoopExpressionContent = _cont;
        sepOffset = _sepOffset;
        refVariable = _refVariable;
    }

    public int getExpressionOffset() {
        return tableLoopExpressionContent.getExpressionOffset();
    }


    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MethodAccessKind static_ = processVarTypes(_page);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(static_), _page));
//        root = _page.getCurrentRoot();
//        ExecOperationNode l_ = op_.last();
//        argument = l_.getArgument();
        checkMatchs(res.getRoot().getResultClass(), _page);
        processVariables(_page);
//        ExecForEachTable exec_ = new ExecForEachTable(getOffset(),label, importedClassNameFirst,
//                importedClassNameSecond,
//                importedClassIndexName,variableNameFirst,
//                variableNameSecond, expressionOffset,op_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);

    }

    private void checkMatchs(AnaClassArgumentMatching _el, AnalyzedPageEl _page) {
//        if (Argument.isNullValue(argument)) {
//            FoundErrorInterpret static_ = new FoundErrorInterpret();
//            static_.setFileName(_page.getCurrentBlock().getFile().getFileName());
//            static_.setIndexFile(_page.getTraceIndex());
//            //separator char
//            static_.buildError(_page.getAnalysisMessages().getNullValue(),
//                    _page.getStandards().getAliasNullPe());
//            _page.addLocError(static_);
//            sepErrors.add(static_.getBuiltError());
//        } else {
            StringList names_ = _el.getNames();
            StringList out_ = getCustomType(names_, _page);
            checkIterableCandidates(out_, _page);
//        }
    }
    private StringList getCustomType(StringList _names, AnalyzedPageEl _page) {
         return ContextUtil.getCustomTableType(_names,_page).getClassName();
    }

    public String getClassIndexName() {
        return tableLoopExpressionContent.getClassIndexName();
    }

    private MethodAccessKind processVarTypes(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        tableLoopExpressionContent.resolveIndex(this,_page);
        TokenErrorMessage resOne_ = ManageTokens.partVar(_page).checkTokenVar(tableLoopExpressionContent.getVariableNameFirst(), _page);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetFirst());
            //first variable name
            b_.setBuiltError(resOne_.getMessage());
            _page.addLocError(b_);
            nameErrorsFirst.add(b_.getBuiltError());
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_page).checkTokenVar(tableLoopExpressionContent.getVariableNameSecond(), _page);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetSecond());
            //second variable name
            b_.setBuiltError(resTwo_.getMessage());
            _page.addLocError(b_);
            nameErrorsSecond.add(b_.getBuiltError());
            okVarSecond = false;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        _page.setSumOffset(tableLoopExpressionContent.getClassNameOffsetFirst());
        _page.zeroOffset();
        if (!StringUtil.quickEq(tableLoopExpressionContent.getClassNameFirst().trim(), keyWordVar_)) {
            partOffsetsFirst = ResolvingTypes.resolveCorrectType(tableLoopExpressionContent.getClassNameFirst(), _page);
            importedClassNameFirst = partOffsetsFirst.getResult(_page);
        } else {
            importedClassNameFirst = "";
        }
        _page.setSumOffset(tableLoopExpressionContent.getClassNameOffsetSecond());
        _page.zeroOffset();
        if (!StringUtil.quickEq(tableLoopExpressionContent.getClassNameSecond().trim(), keyWordVar_)) {
            partOffsetsSecond = ResolvingTypes.resolveCorrectType(tableLoopExpressionContent.getClassNameSecond(), _page);
            importedClassNameSecond = partOffsetsSecond.getResult(_page);
        } else {
            importedClassNameSecond = "";
        }
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        return f_.getStaticContext();
    }

    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (refVariable || !_types.onlyOneElt()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
            //separator char before expression
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterableTable());
            _page.addLocError(cast_);
            sepErrors.add(cast_.getBuiltError());
            return;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String type_ = _types.first();
        Mapping mapping_ = new Mapping();
        String paramArg_ = ForEachLoop.extType(_page, StringExpUtil.getAllTypes(type_).get(1));
        if (StringUtil.quickEq(tableLoopExpressionContent.getClassNameFirst().trim(), keyWordVar_)) {
            importedClassNameFirst = paramArg_;
        } else {
            mapping_.setArg(paramArg_);
            mapping_.setParam(importedClassNameFirst);
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            mapping_.setMapping(vars_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(getFile());
                cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
                //separator char before expression
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        paramArg_,
                        importedClassNameFirst);
                _page.addLocError(cast_);
                sepErrors.add(cast_.getBuiltError());
            }
        }
        mapping_ = new Mapping();
        paramArg_ = ForEachLoop.extType(_page, StringExpUtil.getAllTypes(type_).last());
        if (StringUtil.quickEq(tableLoopExpressionContent.getClassNameSecond().trim(), keyWordVar_)) {
            importedClassNameSecond = paramArg_;
        } else {
            mapping_.setArg(paramArg_);
            mapping_.setParam(importedClassNameSecond);
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            mapping_.setMapping(vars_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(getFile());
                cast_.setIndexFile(tableLoopExpressionContent.getExpressionOffset());
                //separator char before expression
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        paramArg_,
                        importedClassNameSecond);
                _page.addLocError(cast_);
                sepErrors.add(cast_.getBuiltError());
            }
        }
    }

    private void processVariables(AnalyzedPageEl _page) {
        if (okVarFirst && okVarSecond && StringUtil.quickEq(tableLoopExpressionContent.getVariableNameFirst(), tableLoopExpressionContent.getVariableNameSecond())) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(getFile());
            d_.setIndexFile(tableLoopExpressionContent.getVariableNameOffsetSecond());
            //second variable name len
            d_.buildError(_page.getAnalysisMessages().getDuplicatedVariableName(),
                    tableLoopExpressionContent.getVariableNameFirst());
            _page.addLocError(d_);
            nameErrorsSecond.add(d_.getBuiltError());
            return;
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(tableLoopExpressionContent.getVariableNameOffsetFirst());
            lv_.setIndexClassName(tableLoopExpressionContent.getImportedClassIndexName());
            _page.getLoopsVars().put(tableLoopExpressionContent.getVariableNameFirst(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setRef(tableLoopExpressionContent.getVariableNameOffsetFirst());
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(tableLoopExpressionContent.getVariableNameFirst(), lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(tableLoopExpressionContent.getVariableNameOffsetSecond());
            lv_.setIndexClassName(tableLoopExpressionContent.getImportedClassIndexName());
            _page.getLoopsVars().put(tableLoopExpressionContent.getVariableNameSecond(), lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setRef(tableLoopExpressionContent.getVariableNameOffsetSecond());
            lInfo_.setConstType(ConstType.FIX_VAR);
            lInfo_.setFinalVariable(true);
            _page.getInfosVars().put(tableLoopExpressionContent.getVariableNameSecond(), lInfo_);
        }
    }
    public String getClassNameFirst() {
        return tableLoopExpressionContent.getClassNameFirst();
    }

    public String getVariableNameFirst() {
        return tableLoopExpressionContent.getVariableNameFirst();
    }

    public String getClassNameSecond() {
        return tableLoopExpressionContent.getClassNameSecond();
    }

    public String getVariableNameSecond() {
        return tableLoopExpressionContent.getVariableNameSecond();
    }

    public int getClassNameOffsetFirst() {
        return tableLoopExpressionContent.getClassNameOffsetFirst();
    }

    public int getClassNameOffsetSecond() {
        return tableLoopExpressionContent.getClassNameOffsetSecond();
    }

    public int getVariableNameOffsetFirst() {
        return tableLoopExpressionContent.getVariableNameOffsetFirst();
    }

    public int getVariableNameOffsetSecond() {
        return tableLoopExpressionContent.getVariableNameOffsetSecond();
    }

    public String getExpression() {
        return tableLoopExpressionContent.getExpression();
    }

    @Override
    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    @Override
    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }

    public String getImportedClassIndexName() {
        return tableLoopExpressionContent.getImportedClassIndexName();
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(tableLoopExpressionContent.getVariableNameFirst());
        _ip.getLoopsVars().removeKey(tableLoopExpressionContent.getVariableNameSecond());
        _ip.getInfosVars().removeKey(tableLoopExpressionContent.getVariableNameFirst());
        _ip.getInfosVars().removeKey(tableLoopExpressionContent.getVariableNameSecond());
    }

    public ResultExpression getRes() {
        return res;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public AnaResultPartType getPartOffsetsFirst() {
        return partOffsetsFirst;
    }

    public AnaResultPartType getPartOffsetsSecond() {
        return partOffsetsSecond;
    }

    public StringList getNameErrorsFirst() {
        return nameErrorsFirst;
    }

    public StringList getNameErrorsSecond() {
        return nameErrorsSecond;
    }

    public int getSepOffset() {
        return sepOffset;
    }

    public StringList getSepErrors() {
        return sepErrors;
    }
}
