package code.formathtml.analyze.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendCaseCondition extends AnaRendSwitchPartCondition {

    private final String value;
    private OperationNode root;

    private final int classNameOffset;
    private final String className;
    private final int variableOffset;
    private final int valueOffset;
    private boolean builtEnum;
    private boolean nullCaseEnum;
    private Argument argument;
    AnaRendCaseCondition(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, int _offset) {
        super(_offset);
        classNameOffset = _className.getOffset();
        className = _className.getInfo();
        variableOffset = _variable.getOffset();
        setVariableName(_variable.getInfo());
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        AnaRendParentBlock par_ = getParent();
        if (!(par_ instanceof AnaRendSwitchBlock)) {
            _page.setGlobalOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    value,
                    _page.getKeyWords().getKeyWordSwitch());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
            root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page);
            return;
        }
        AnaRendSwitchBlock sw_ = (AnaRendSwitchBlock) par_;
        if (sw_.isInstance()) {
            if (StringUtil.quickEq(value, _page.getKeyWords().getKeyWordNull())) {
                argument = Argument.createVoid();
                setImportedClassName("");
                return;
            }
            _page.setGlobalOffset(classNameOffset);
            setImportedClassName(ResolvingTypes.resolveCorrectType(className, _page));
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(getVariableName(), _page);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                AnalyzingDoc.addError(d_, _anaDoc, _page);
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(getImportedClassName());
            lv_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(getVariableName(), lv_);
            return;
        }
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
        String type_ = resSwitch_.getSingleNameOrEmpty();
        if (!type_.isEmpty()) {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType g_ = _page.getAnaClassBody(id_);
            if (g_ instanceof EnumBlock) {
                for (InnerTypeOrElement f: ((EnumBlock) g_).getEnumBlocks()) {
                    if (!StringUtil.contains(f.getFieldName(), value.trim())) {
                        continue;
                    }
                    builtEnum = true;
                    _page.setLookLocalClass(id_);
                    _page.setAccessStaticContext(MethodAccessKind.STATIC);
                    Delimiters d_ = ElResolver.checkSyntax(value, IndexConstants.FIRST_INDEX, _page);
                    OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, value, d_, _page);
                    OperationNode op_ = OperationNode.createOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _page);
                    ElUtil.analyzeInfer(op_,_page);
                    _page.setLookLocalClass(EMPTY_STRING);
                    op_.setOrder(0);
                    root = op_;
                    checkDuplicateEnumCase(_anaDoc, _page);
                    return;
                }
                builtEnum = true;
                nullCaseEnum = true;
                root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page);
                Argument a_ = root.getArgument();
                argument = a_;
                if (Argument.isNullValue(a_)) {
                    checkDuplicateCase(a_, _anaDoc, _page);
                    return;
                }
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        value);
                AnalyzingDoc.addError(un_, _anaDoc, _page);
                return;
            }
        }
        root = RenderAnalysis.getRootAnalyzedOperations(value, 0, _anaDoc, _page);
        AnaClassArgumentMatching resCase_ = root.getResultClass();
        Argument arg_ = root.getArgument();
        argument = arg_;
        if (arg_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(valueOffset);
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                    _page.getKeyWords().getKeyWordCase(),
                    value);
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        } else {
            checkDuplicateCase(arg_, _anaDoc, _page);
            Mapping m_ = new Mapping();
            m_.setArg(resCase_);
            m_.setParam(resSwitch_);
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(valueOffset);
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                        _page.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(arg_, _page),
                        StringUtil.join(resSwitch_.getNames(),AND_ERR));
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            }
        }
    }

    private void checkDuplicateCase(Argument _arg, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendParentBlock par_ = getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof AnaRendCaseCondition) {
                AnaRendCaseCondition c_ = (AnaRendCaseCondition) first_;
                Argument a_ = c_.root.getArgument();
                if (a_ != null) {
                    if (_arg.getStruct().sameReference(a_.getStruct())) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg, _page),
                                _page.getKeyWords().getKeyWordSwitch());
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendParentBlock par_ = getParent();
        AnaRendBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof AnaRendCaseCondition) {
                AnaRendCaseCondition c_ = (AnaRendCaseCondition) first_;
                String v_ = c_.value.trim();
                if (StringUtil.quickEq(v_, value.trim())) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                            _page.getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _page.getKeyWords().getKeyWordSwitch());
                    AnalyzingDoc.addError(un_, _anaDoc, _page);
                    break;
                }

            }
            first_ = first_.getNextSibling();
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public boolean isNullCaseEnum() {
        return nullCaseEnum;
    }

    public boolean isBuiltEnum() {
        return builtEnum;
    }

    public String getValue() {
        return value;
    }

    public Argument getArgument() {
        return argument;
    }
}
