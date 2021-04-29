package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.util.*;
import code.util.core.StringUtil;

public final class ForEachTable extends AbstractForLoop implements Loop,ImportForEachTable {

    private final String label;
    private final int labelOffset;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private final int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private final int classNameOffsetSecond;

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;

    private final String variableNameFirst;

    private final int variableNameOffsetFirst;

    private final String variableNameSecond;

    private final int variableNameOffsetSecond;

    private final String expression;

    private final int expressionOffset;

    private final ResultExpression res = new ResultExpression();
    private final CustList<PartOffset> partOffsetsFirst = new CustList<PartOffset>();

    private final CustList<PartOffset> partOffsetsSecond = new CustList<PartOffset>();

    private final StringList nameErrorsFirst = new StringList();
    private final StringList nameErrorsSecond = new StringList();

    private final int sepOffset;
    private final StringList sepErrors = new StringList();
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;
    private final boolean refVariable;

    public ForEachTable(OffsetStringInfo _className, OffsetStringInfo _variable,
                        OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                        OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, int _offset, int _sepOffset, AnalyzedPageEl _page, boolean _refVariable) {
        super(_offset);
        classNameFirst = _className.getInfo();
        classNameOffsetFirst = _className.getOffset();
        variableNameFirst = _variable.getInfo();
        variableNameOffsetFirst = _variable.getOffset();
        classNameSecond = _classNameSec.getInfo();
        classNameOffsetSecond = _classNameSec.getOffset();
        variableNameSecond = _variableSec.getInfo();
        variableNameOffsetSecond = _variableSec.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _page.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
        sepOffset = _sepOffset;
        refVariable = _refVariable;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getLabelOffset() {
        return labelOffset;
    }


    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MethodAccessKind static_ = processVarTypes(_page);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, expression, Calculation.staticCalculation(static_), _page));
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
         return _page.getForEachFetch().getCustomTableType(_names, "","").getClassName();
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    private MethodAccessKind processVarTypes(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.zeroOffset();
        MemberCallingsBlock f_ = _page.getCurrentFct();
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_page.getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        TokenErrorMessage resOne_ = ManageTokens.partVar(_page).checkTokenVar(variableNameFirst, _page);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            b_.setBuiltError(resOne_.getMessage());
            _page.addLocError(b_);
            nameErrorsFirst.add(b_.getBuiltError());
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_page).checkTokenVar(variableNameSecond, _page);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            b_.setBuiltError(resTwo_.getMessage());
            _page.addLocError(b_);
            nameErrorsSecond.add(b_.getBuiltError());
            okVarSecond = false;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        _page.setGlobalOffset(classNameOffsetFirst);
        _page.zeroOffset();
        if (!StringUtil.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = ResolvingTypes.resolveCorrectType(classNameFirst, _page);
            partOffsetsFirst.addAllElts(_page.getCurrentParts());
        } else {
            importedClassNameFirst = "";
        }
        _page.setGlobalOffset(classNameOffsetSecond);
        _page.zeroOffset();
        if (!StringUtil.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = ResolvingTypes.resolveCorrectType(classNameSecond, _page);
            partOffsetsSecond.addAllElts(_page.getCurrentParts());
        } else {
            importedClassNameSecond = "";
        }
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        MethodAccessKind static_ = f_.getStaticContext();
//        _page.getCoverage().putBlockOperationsLoops(this);
        return static_;
    }

    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (!refVariable&&_types.onlyOneElt()) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).get(1);
            if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
            } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)){
                paramArg_ = _page.getAliasObject();
            }
            if (StringUtil.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
            mapping_ = new Mapping();
            paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
            } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)){
                paramArg_ = _page.getAliasObject();
            }
            if (StringUtil.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char before expression
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterableTable());
            _page.addLocError(cast_);
            sepErrors.add(cast_.getBuiltError());
        }
    }

    private void processVariables(AnalyzedPageEl _page) {
        if (okVarFirst && okVarSecond) {
            if (StringUtil.quickEq(variableNameFirst, variableNameSecond)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableNameOffsetSecond);
                //second variable name len
                d_.buildError(_page.getAnalysisMessages().getDuplicatedVariableName(),
                        variableNameFirst);
                _page.addLocError(d_);
                nameErrorsSecond.add(d_.getBuiltError());
                return;
            }
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(variableNameOffsetFirst);
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableNameFirst, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setRef(variableNameOffsetFirst);
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameFirst, lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(variableNameOffsetSecond);
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableNameSecond, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setRef(variableNameOffsetSecond);
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameSecond, lInfo_);
        }
    }


    public String getLabel() {
        return label;
    }

    public String getClassNameFirst() {
        return classNameFirst;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public String getClassNameSecond() {
        return classNameSecond;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public int getClassNameOffsetFirst() {
        return classNameOffsetFirst;
    }

    public int getClassNameOffsetSecond() {
        return classNameOffsetSecond;
    }

    public int getVariableNameOffsetFirst() {
        return variableNameOffsetFirst;
    }

    public int getVariableNameOffsetSecond() {
        return variableNameOffsetSecond;
    }

    public String getExpression() {
        return expression;
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
        return importedClassIndexName;
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(variableNameFirst);
        _ip.getLoopsVars().removeKey(variableNameSecond);
        _ip.getInfosVars().removeKey(variableNameFirst);
        _ip.getInfosVars().removeKey(variableNameSecond);
    }

    public ResultExpression getRes() {
        return res;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public CustList<PartOffset> getPartOffsetsFirst() {
        return partOffsetsFirst;
    }

    public CustList<PartOffset> getPartOffsetsSecond() {
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
