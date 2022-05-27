package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.*;
import code.util.core.StringUtil;

public final class ForEachLoop extends AbstractForLoop implements ImportForEachLoop,ForEachLoopAbs {

    private String importedClassName;

    private final int sepOffset;

    private final ListLoopExpressionContent listLoopExpressionContent;
    private final ResultExpression res = new ResultExpression();

    private AnaResultPartType partOffsets = new AnaResultPartType();

    private final StringList nameErrors = new StringList();
    private final StringList sepErrors = new StringList();
    private boolean okVar = true;
    private final boolean refVariable;

    public ForEachLoop(ListLoopExpressionContent _content, int _offset, int _sepOffset, boolean _refVariable, OffsetStringInfo _label) {
        super(_offset, _label);
        listLoopExpressionContent = _content;
        sepOffset = _sepOffset;
        refVariable = _refVariable;
    }

    public int getClassNameOffset() {
        return listLoopExpressionContent.getClassNameOffset();
    }

    public int getClassIndexNameOffset() {
        return listLoopExpressionContent.getClassIndexNameOffset();
    }

    public int getVariableNameOffset() {
        return listLoopExpressionContent.getVariableNameOffset();
    }

    public int getExpressionOffset() {
        return listLoopExpressionContent.getExpressionOffset();
    }

    public String getClassIndexName() {
        return listLoopExpressionContent.getClassIndexName();
    }

    public String getClassName() {
        return listLoopExpressionContent.getClassName();
    }

    public String getVariableName() {
        return listLoopExpressionContent.getVariableName();
    }

    public String getExpression() {
        return listLoopExpressionContent.getExpression();
    }


    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    private MethodAccessKind processVarTypes(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        listLoopExpressionContent.resolveIndex(this,_page);
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(listLoopExpressionContent.getVariableName(), _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(listLoopExpressionContent.getVariableNameOffset());
            //variable name len
            b_.setBuiltError(res_.getMessage());
            _page.addLocError(b_);
            nameErrors.add(b_.getBuiltError());
            okVar = false;
        }
        _page.setSumOffset(listLoopExpressionContent.getClassNameOffset());
        _page.zeroOffset();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringUtil.quickEq(listLoopExpressionContent.getClassName().trim(), keyWordVar_)) {
            partOffsets = ResolvingTypes.resolveCorrectType(listLoopExpressionContent.getClassName(), _page);
            importedClassName = partOffsets.getResult(_page);
        } else {
            importedClassName = "";
        }
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        return f_.getStaticContext();
    }

    public void inferArrayClass(AnaClassArgumentMatching _elt, AnalyzedPageEl _page) {
        AnaClassArgumentMatching compo_ = AnaTypeUtil.getQuickComponentType(_elt);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(listLoopExpressionContent.getClassName().trim(), keyWordVar_) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
            return;
        }
        Mapping mapping_ = new Mapping();
        if (importedClassName.isEmpty()) {
            mapping_.setArg("");
            mapping_.setParam("");
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
            //separator char
            cast_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    listLoopExpressionContent.getClassName().trim());
            _page.addLocError(cast_);
            sepErrors.add(cast_.getBuiltError());
        } else {
            if (refVariable) {
                if (!compo_.matchClass(importedClassName)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(getFile());
                    cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                    //separator char
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(compo_.getNames(), ExportCst.JOIN_TYPES),
                            importedClassName);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            } else {
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(getFile());
                    cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                    //separator char
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(compo_.getNames(),ExportCst.JOIN_TYPES),
                            importedClassName);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MethodAccessKind static_ = processVarTypes(_page);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(static_), _page));
//        root = _page.getCurrentRoot();
//        ExecOperationNode l_ = op_.last();
//        argument = l_.getArgument();
        checkMatchs(res.getRoot().getResultClass(), _page);
        if (okVar) {
            processVariable(_page);
        }
//        ExecForEachLoop exec_ = new ExecForEachLoop(getOffset(),label, importedClassName,
//                importedClassIndexName,variableName,variableNameOffset, expressionOffset,op_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkMatchs(AnaClassArgumentMatching _elt, AnalyzedPageEl _page) {
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
            if (_elt.isArray()) {
                inferArrayClass(_elt, _page);
            } else {
                StringList names_ = _elt.getNames();
                StringList out_ = getInferredIterable(names_, _page);
                checkIterableCandidates(out_, _page);
            }
//        }
    }

    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(_types,_page);
        return it_.getClassName();
    }

    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (!refVariable&&_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = extType(_page, StringExpUtil.getAllTypes(type_).last());
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(listLoopExpressionContent.getClassName().trim(), keyWordVar_)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(getFile());
                    cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
                    //separator char
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(listLoopExpressionContent.getExpressionOffset());
            //separator char
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterable());
            _page.addLocError(cast_);
            sepErrors.add(cast_.getBuiltError());
        }
    }

    public static String extType(AnalyzedPageEl _page, String _ty) {
        String paramArg_ = _ty;
        if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
            paramArg_ = _page.getAliasObject();
        } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
            paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
        } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)) {
            paramArg_ = _page.getAliasObject();
        }
        return paramArg_;
    }

    private void processVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setRef(listLoopExpressionContent.getVariableNameOffset());
        lv_.setIndexClassName(listLoopExpressionContent.getImportedClassIndexName());
        _page.getLoopsVars().put(listLoopExpressionContent.getVariableName(), lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getAliasObject());
        }
        lInfo_.setRef(listLoopExpressionContent.getVariableNameOffset());
        if (refVariable) {
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
        _ip.getLoopsVars().removeKey(listLoopExpressionContent.getVariableName());
        _ip.getInfosVars().removeKey(listLoopExpressionContent.getVariableName());
    }

    public ResultExpression getRes() {
        return res;
    }

    public String getImportedClassIndexName() {
        return listLoopExpressionContent.getImportedClassIndexName();
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public StringList getNameErrors() {
        return nameErrors;
    }

    public int getSepOffset() {
        return sepOffset;
    }

    public StringList getSepErrors() {
        return sepErrors;
    }

    public boolean isRefVariable() {
        return refVariable;
    }
}
