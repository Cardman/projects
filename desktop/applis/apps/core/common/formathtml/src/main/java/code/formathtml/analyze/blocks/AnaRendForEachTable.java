package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.ImportForEachTable;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
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

public final class AnaRendForEachTable extends AnaRendParentBlock implements AnaRendLoop,ImportForEachTable {

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

    private OperationNode root;
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;

    AnaRendForEachTable(OffsetStringInfo _className, OffsetStringInfo _variable,
                        OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                        OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, int _offset, PrimitiveTypes _primTypes) {
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
            classIndex_ = _primTypes.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
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
            static_.setFileName(_anaDoc.getFileName());
            static_.setIndexFile(expressionOffset);
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            AnalyzingDoc.addError(static_, _anaDoc, _page);
        } else {
            StringList names_ = root.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _page);
            checkIterableCandidates(out_, _anaDoc, _page);
        }
        putVariable(_anaDoc, _page);
    }

    private StringList getCustomType(StringList _names, AnalyzedPageEl _page) {
        return _page.getForEachFetch().getCustomTableType(_names, importedClassNameFirst,importedClassNameSecond).getClassName();
    }

    public void buildEl(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page).getResult(_page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            AnalyzingDoc.addError(cast_, _anaDoc, _page);
        }
        TokenErrorMessage resOne_ = ManageTokens.partVar(_page).checkTokenVar(variableNameFirst, _page);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            b_.setBuiltError(resOne_.getMessage());
            AnalyzingDoc.addError(b_, _anaDoc, _page);
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_page).checkTokenVar(variableNameSecond, _page);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            b_.setBuiltError(resTwo_.getMessage());
            AnalyzingDoc.addError(b_, _anaDoc, _page);
            okVarSecond = false;
        }
        _page.setGlobalOffset(classNameOffsetFirst);
        _page.zeroOffset();
        if (!toInferFirst(_page)) {
            importedClassNameFirst = ResolvingTypes.resolveCorrectType(classNameFirst, _page).getResult(_page);
        } else {
            importedClassNameFirst = EMPTY_STRING;
        }
        _page.setGlobalOffset(classNameOffsetSecond);
        _page.zeroOffset();
        if (!toInferSecond(_page)) {
            importedClassNameSecond = ResolvingTypes.resolveCorrectType(classNameSecond, _page).getResult(_page);
        } else {
            importedClassNameSecond = EMPTY_STRING;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        root = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
    }

    public void checkIterableCandidates(StringList _types, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).get(1);
            if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
            } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)) {
                paramArg_ = _page.getAliasObject();
            }
            if (toInferFirst(_page)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    AnalyzingDoc.addError(cast_, _anaDoc, _page);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringUtil.quickEq(paramArg_, StringExpUtil.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(StringExpUtil.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(StringExpUtil.SUB_TYPE.length());
            } else if (paramArg_.startsWith(StringExpUtil.SUP_TYPE)) {
                paramArg_ = _page.getAliasObject();
            }
            if (toInferSecond(_page)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_anaDoc.getFileName());
                    cast_.setIndexFile(expressionOffset);
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    AnalyzingDoc.addError(cast_, _anaDoc, _page);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(expressionOffset);
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterableTable());
            AnalyzingDoc.addError(cast_, _anaDoc, _page);
        }
    }

    public void putVariable(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (okVarFirst && okVarSecond) {
            if (StringUtil.quickEq(variableNameFirst, variableNameSecond)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableNameOffsetSecond);
                d_.buildError(_page.getAnalysisMessages().getBadVariableName(),
                        variableNameFirst);
                AnalyzingDoc.addError(d_, _anaDoc, _page);
                return;
            }
        }
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableNameFirst, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameFirst, lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableNameSecond, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(_page.getAliasObject());
            }
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableNameSecond, lInfo_);
        }
    }

    private boolean toInferFirst(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(classNameFirst.trim(), keyWordVar_) || classNameFirst.trim().isEmpty();
    }

    private boolean toInferSecond(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        return StringUtil.quickEq(classNameSecond.trim(), keyWordVar_) || classNameSecond.trim().isEmpty();
    }
    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getInfosVars().removeKey(variableNameFirst);
        _ip.getLoopsVars().removeKey(variableNameFirst);
        _ip.getInfosVars().removeKey(variableNameSecond);
        _ip.getLoopsVars().removeKey(variableNameSecond);
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
        return variableNameSecond;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public OperationNode getRoot() {
        return root;
    }
}
