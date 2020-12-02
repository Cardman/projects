package code.formathtml.fwd;

import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.*;
import code.formathtml.analyze.InternGlobalOperation;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.blocks.*;
import code.formathtml.exec.blocks.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.exec.opers.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.InputInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendForwardInfos {
    private RendForwardInfos() {
    }
    private static RendDocumentBlock build(AnaRendDocumentBlock _ana, Forwards _forwards, AnalyzingDoc _anaDoc) {
        AbstractInputBuilder inputBuilder_ = _anaDoc.getInputBuilder();
        RendDocumentBlock rendDoc_ = new RendDocumentBlock(_ana.getOffset().getOffsetTrim(), _ana.getElt(), _ana.getFile(), _ana.getFileName(), _ana.getBeanName());
        RendParentBlock curPar_ = rendDoc_;
        AnaRendBlock en_ = _ana;
        while (en_ != null) {
            RendBlock loc_ = newRendBlock(en_, _forwards, inputBuilder_);
            if (loc_ != null) {
                if (loc_ instanceof RendStdElement) {
                    if (StringUtil.quickEq(((RendStdElement) loc_).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                        rendDoc_.getBodies().add(loc_);
                    }
                }
                loc_.setEscapedChars(en_.getEscapedChars());
                curPar_.appendChild(loc_);
                if (loc_ instanceof RendParentBlock) {
                    curPar_ = (RendParentBlock) loc_;
                }
            }
            AnaRendBlock n_ = en_.getFirstChild();
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            while (en_ != null) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                AnaRendParentBlock parent_ = en_.getParent();
                curPar_ = curPar_.getParent();
                if (curPar_ == null) {
                    en_ = null;
                    continue;
                }
                en_ = parent_;
            }
        }
        return rendDoc_;
    }
    private static RendBlock newRendBlock(AnaRendBlock _current, Forwards _forwards, AbstractInputBuilder _inputBuilder) {
        if (_current instanceof AnaRendEmptyText){
            return new RendEmptyText(_current.getOffset().getOffsetTrim(),((AnaRendEmptyText)_current).getExpression(),((AnaRendEmptyText)_current).isAdd());
        }
        if (_current instanceof AnaRendText){
            AnaRendText t_ = (AnaRendText) _current;
            ExecTextPart part_ = toExecPartExt(t_.getRoots(),t_.getTexts(), _forwards);
            return new RendText(_current.getOffset().getOffsetTrim(),part_,t_.getExpressionOffset());
        }
        if (_current instanceof AnaRendForEachLoop){
            AnaRendForEachLoop f_ = (AnaRendForEachLoop) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            if (f_.getRoot().getResultClass().isArray()) {
                return new RendForEachArray(f_.getImportedClassName(),f_.getVariableName(),
                        f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(),f_.getOffset().getOffsetTrim(),op_);
            }
            return new RendForEachIterable(f_.getImportedClassName(),f_.getVariableName(),
                    f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(),f_.getOffset().getOffsetTrim(),op_);
        }
        if (_current instanceof AnaRendForEachTable){
            AnaRendForEachTable f_ = (AnaRendForEachTable) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendForEachTable(f_.getImportedClassNameFirst(),f_.getVariableNameFirst(),
                    f_.getImportedClassNameSecond(),f_.getVariableNameSecond(),
                    f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(),f_.getOffset().getOffsetTrim(),op_);
        }
        if (_current instanceof AnaRendForIterativeLoop){
            AnaRendForIterativeLoop f_ = (AnaRendForIterativeLoop) _current;
            CustList<RendDynOperationNode> opInit_ = getExecutableNodes(f_.getRootInit(), _forwards);
            CustList<RendDynOperationNode> opExp_ = getExecutableNodes(f_.getRootExp(), _forwards);
            CustList<RendDynOperationNode> opStep_ = getExecutableNodes(f_.getRootStep(), _forwards);
            return new RendForIterativeLoop(f_.getImportedClassName(),f_.getVariableName(),
                    f_.getInitOffset(),f_.getExpressionOffset(),f_.isEq(),f_.getStepOffset(),
                    f_.getImportedClassIndexName(),f_.getRealLabel(),f_.getOffset().getOffsetTrim(),
                    opInit_,opExp_,opStep_);
        }
        if (_current instanceof AnaRendForMutableIterativeLoop){
            AnaRendForMutableIterativeLoop f_ = (AnaRendForMutableIterativeLoop) _current;
            CustList<RendDynOperationNode> opInit_ = getExecutableNodes(f_.getRootInit(), _forwards);
            CustList<RendDynOperationNode> opExp_ = getExecutableNodes(f_.getRootExp(), _forwards);
            CustList<RendDynOperationNode> opStep_ = getExecutableNodes(f_.getRootStep(), _forwards);
            return new RendForMutableIterativeLoop(f_.getImportedClassName(),
                    f_.getInitOffset(),f_.getExpressionOffset(),f_.getStepOffset(),
                    f_.getVariableNames(),
                    f_.getImportedClassIndexName(),f_.getRealLabel(),f_.getOffset().getOffsetTrim(),
                    opInit_,opExp_,opStep_);
        }
        if (_current instanceof AnaRendWhileCondition){
            AnaRendWhileCondition f_ = (AnaRendWhileCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendWhileCondition(_current.getOffset().getOffsetTrim(),op_,f_.getConditionOffset(),f_.getRealLabel());
        }
        if (_current instanceof AnaRendDoBlock){
            AnaRendDoBlock f_ = (AnaRendDoBlock) _current;
            return new RendDoBlock(_current.getOffset().getOffsetTrim(),f_.getRealLabel());
        }
        if (_current instanceof AnaRendDoWhileCondition){
            AnaRendDoWhileCondition f_ = (AnaRendDoWhileCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendDoWhileCondition(_current.getOffset().getOffsetTrim(),op_,f_.getConditionOffset());
        }
        if (_current instanceof AnaRendReturnMehod){
            return new RendReturnMehod(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendBreakBlock){
            AnaRendBreakBlock f_ = (AnaRendBreakBlock) _current;
            return new RendBreakBlock(_current.getOffset().getOffsetTrim(),f_.getLabel());
        }
        if (_current instanceof AnaRendContinueBlock){
            AnaRendContinueBlock f_ = (AnaRendContinueBlock) _current;
            return new RendContinueBlock(_current.getOffset().getOffsetTrim(),f_.getLabel());
        }
        if (_current instanceof AnaRendThrowing){
            AnaRendThrowing f_ = (AnaRendThrowing) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendThrowing(_current.getOffset().getOffsetTrim(),op_,f_.getExpressionOffset());
        }
        if (_current instanceof AnaRendDeclareVariable){
            AnaRendDeclareVariable f_ = (AnaRendDeclareVariable) _current;
            if (f_.isRefVariable()) {
                return new RendRefDeclareVariable(_current.getOffset().getOffsetTrim(),f_.getVariableNames());
            }
            return new RendDeclareVariable(_current.getOffset().getOffsetTrim(),f_.getImportedClassName(),f_.getClassNameOffset(),f_.getVariableNames());
        }
        if (_current instanceof AnaRendLine){
            AnaRendLine f_ = (AnaRendLine) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendLine(_current.getOffset().getOffsetTrim(),op_,f_.getExpressionOffset());
        }
        if (_current instanceof AnaRendIfCondition){
            AnaRendIfCondition f_ = (AnaRendIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendIfCondition(_current.getOffset().getOffsetTrim(),op_,f_.getConditionOffset(),f_.getRealLabel());
        }
        if (_current instanceof AnaRendElseIfCondition){
            AnaRendElseIfCondition f_ = (AnaRendElseIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendElseIfCondition(_current.getOffset().getOffsetTrim(),op_,f_.getConditionOffset());
        }
        if (_current instanceof AnaRendElseCondition){
            return new RendElseCondition(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendTryEval){
            AnaRendTryEval f_ = (AnaRendTryEval) _current;
            return new RendTryEval(f_.getRealLabel(), _current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendCatchEval){
            AnaRendCatchEval f_ = (AnaRendCatchEval) _current;
            return new RendCatchEval(f_.getImportedClassName(),f_.getVariableName(),_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendNullCatchEval){
            return new RendNullCatchEval(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendFinallyEval){
            return new RendFinallyEval(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendSwitchBlock){
            AnaRendSwitchBlock f_ = (AnaRendSwitchBlock) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendSwitchBlock(_current.getOffset().getOffsetTrim(),f_.getRealLabel(),f_.getValueOffset(),op_,f_.isEnumTest(),f_.getInstanceTest());
        }
        if (_current instanceof AnaRendCaseCondition){
            AnaRendCaseCondition f_ = (AnaRendCaseCondition) _current;
            return new RendCaseCondition(_current.getOffset().getOffsetTrim(),f_.getImportedClassName(),f_.getVariableName(),f_.getValue(),f_.getArgument());
        }
        if (_current instanceof AnaRendDefaultCondition){
            AnaRendDefaultCondition f_ = (AnaRendDefaultCondition) _current;
            return new RendDefaultCondition(_current.getOffset().getOffsetTrim(),f_.getImportedClassName(),f_.getVariableName());
        }
        if (_current instanceof AnaRendImport){
            AnaRendImport f_ = (AnaRendImport) _current;
            ExecTextPart part_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            return new RendImport(_current.getOffset().getOffsetTrim(),f_.getElt(),part_,f_.getPageOffset());
        }
        if (_current instanceof AnaRendSubmit){
            AnaRendSubmit f_ = (AnaRendSubmit) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            StringMap<ExecTextPart> partSub_ = toExecPartExt(f_.getOpExp(), _forwards);
            return new RendSubmit(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,partSub_,f_.getPreformatted());
        }
        if (_current instanceof AnaRendAnchor){
            AnaRendAnchor f_ = (AnaRendAnchor) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendAnchor(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,op_, f_.getVarNames(),partSub_);
        }
        if (_current instanceof AnaRendImg){
            AnaRendImg f_ = (AnaRendImg) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            return new RendImg(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_, partSub_);
        }
        if (_current instanceof AnaRendLink){
            AnaRendLink f_ = (AnaRendLink) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            StringMap<ExecTextPart> partSub_ = toExecPartExt(f_.getOpExpTitle(), _forwards);
            return new RendLink(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,f_.getContent(),partSub_);
        }
        if (_current instanceof AnaRendStyle){
            AnaRendStyle f_ = (AnaRendStyle) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStyle(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_);
        }
        if (_current instanceof AnaRendEscImg){
            AnaRendEscImg f_ = (AnaRendEscImg) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendEscImg(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_);
        }
        if (_current instanceof AnaRendPackage){
            return new RendPackage(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendForm){
            AnaRendForm f_ = (AnaRendForm) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> opForm_ = getExecutableNodes(f_.getRoot(), _forwards);
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            return new RendForm(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,opForm_,f_.getVarNames(),partSub_);
        }
        if (_current instanceof AnaRendImportForm){
            AnaRendImportForm f_ = (AnaRendImportForm) _current;
            return new RendImportForm(_current.getOffset().getOffsetTrim(),f_.getName());
        }
        if (_current instanceof AnaRendClass){
            AnaRendClass f_ = (AnaRendClass) _current;
            return new RendClass(_current.getOffset().getOffsetTrim(),f_.getFullName());
        }
        if (_current instanceof AnaRendField){
            AnaRendField f_ = (AnaRendField) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendField(_current.getOffset().getOffsetTrim(),op_,f_.getPrepareOffset());
        }
        if (_current instanceof AnaRendMessage){
            AnaRendMessage f_ = (AnaRendMessage) _current;
            CustList<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getRoots(), _forwards);
            StringMap<CustList<CustList<RendDynOperationNode>>> map_ = toExecPartMapExt(f_.getCallsRoots(), _forwards);
            return new RendMessage(_current.getOffset().getOffsetTrim(),f_.getElt(),partSub_,
                    f_.getPreformatted(),f_.getQuoted(),f_.getEscaped(),map_,
                    f_.getArgs(),f_.getLocDoc(),
                    f_.getVarNames());
        }
        if (_current instanceof AnaRendSelect){
            AnaRendSelect f_ = (AnaRendSelect) _current;
            ResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = _inputBuilder.buildWritePart(resultInput_, _forwards);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opConverterFieldValue_ = getExecutableNodes(f_.getRootConverterFieldValue(), _forwards);
            CustList<RendDynOperationNode> opDefault_ = getExecutableNodes(f_.getRootDefault(), _forwards);
            CustList<RendDynOperationNode> opMap_ = getExecutableNodes(f_.getRootMap(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendSelect(_current.getOffset().getOffsetTrim(),opRead_,opValue_,opsWrite_,opMap_,opDefault_,opConverter_,opConverterField_,opConverterFieldValue_
            ,partText_,part_,f_.getVarName(),f_.getId(),f_.getIdClass(),f_.getIdName(),f_.getElt(),f_.isMultiple(),f_.getVarNameConverter(),f_.getVarNameConverterField(),
                    f_.getVarNameConverterFieldValue(),f_.getClassName(),f_.isArrayConverter(), f_.getVarNames());
        }
        if (_current instanceof AnaRendRadio){
            AnaRendRadio f_ = (AnaRendRadio) _current;
            ResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = _inputBuilder.buildWritePart(resultInput_, _forwards);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opConverterFieldValue_ = getExecutableNodes(f_.getRootConverterFieldValue(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendRadio(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,opConverter_,opConverterField_
                    ,f_.getVarName(),f_.getVarNameConverter(),f_.getVarNameConverterField(),f_.getId(),f_.getIdClass(),f_.getIdName(),f_.getClassName(),opConverterFieldValue_,
                    f_.getVarNameConverterFieldValue(),f_.getVarNames());
        }
        if (_current instanceof AnaRendStdInput){
            AnaRendStdInput f_ = (AnaRendStdInput) _current;
            ResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = _inputBuilder.buildWritePart(resultInput_, _forwards);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdInput(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,opConverter_,opConverterField_
                    ,f_.getVarName(),f_.getVarNameConverter(),f_.getVarNameConverterField(),f_.getId(),f_.getIdClass(),f_.getIdName(),f_.getClassName(),f_.getVarNames());
        }
        if (_current instanceof AnaRendTextArea){
            AnaRendTextArea f_ = (AnaRendTextArea) _current;
            ResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = _inputBuilder.buildWritePart(resultInput_, _forwards);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendTextArea(_current.getOffset().getOffsetTrim(),opRead_,opValue_,opsWrite_,opConverter_,opConverterField_
                    ,partText_,part_,f_.getVarNameConverter(),f_.getVarNameConverterField(),f_.getVarName(),f_.getId(),f_.getIdClass(),f_.getIdName(),f_.getClassName(),f_.getElt(),f_.getVarNames());
        }
        if (_current instanceof AnaRendSpan){
            AnaRendSpan f_ = (AnaRendSpan) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            return new RendSpan(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,partSub_,f_.getFormatted());
        }
        if (_current instanceof AnaRendTitledAnchor){
            AnaRendTitledAnchor f_ = (AnaRendTitledAnchor) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            StringMap<ExecTextPart> title_ = toExecPartExt(f_.getOpExpTitle(), _forwards);
            CustList<RendDynOperationNode> opAnc_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendTitledAnchor(_current.getOffset().getOffsetTrim(),f_.getRead(),part_,partText_,opAnc_,f_.getVarNames(),title_,f_.getPreformatted(),partSub_);
        }
        if (_current instanceof AnaRendEmptyInstruction){
            return new RendEmptyInstruction(_current.getOffset().getOffsetTrim());
        }
        if (_current instanceof AnaRendStdElement) {
            AnaRendStdElement f_ = (AnaRendStdElement) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdElement(_current.getOffset().getOffsetTrim(), f_.getRead(), part_, partText_);
        }
        return null;
    }

    static CustList<RendDynOperationNode> buildWritePart(ResultInput _resultInput, Forwards _forwards) {
        OperationNode settable_ = _resultInput.getSettable();
        CustList<RendDynOperationNode> l_ = new CustList<RendDynOperationNode>();
        if (settable_ instanceof SettableAbstractFieldOperation) {
            l_ = buildWritePartField(_resultInput, (SettableAbstractFieldOperation) settable_, _forwards);
        }
        if (settable_ instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) settable_;
            if (a_.getCallFctContent().getClassMethodId() != null) {
                l_ = buildWritePartCustArr(_resultInput, a_, _forwards);
            } else {
                l_ = buildWritePartArr(_resultInput, a_);
            }
        }
        return l_;
    }

    private static StringMap<ExecTextPart> toExecPartExt(StringMap<ResultText> _texts, Forwards _forwards) {
        StringMap<ExecTextPart> m_ = new StringMap<ExecTextPart>();
        for (EntryCust<String,ResultText> e: _texts.entryList()) {
            ResultText value_ = e.getValue();
            m_.addEntry(e.getKey(),toExecPartExt(value_.getOpExpRoot(),value_.getTexts(), _forwards));
        }
        return m_;
    }
    private static ExecTextPart toExecPartExt(CustList<OperationNode> _roots, StringList _texts, Forwards _forwards) {
        CustList<CustList<RendDynOperationNode>> parts_ = toExecPartExt(_roots, _forwards);
        ExecTextPart part_ = new ExecTextPart();
        part_.getTexts().addAllElts(_texts);
        part_.setOpExp(parts_);
        return part_;
    }
    private static StringMap<CustList<CustList<RendDynOperationNode>>> toExecPartMapExt(StringMap<CustList<OperationNode>> _roots, Forwards _forwards) {
        StringMap<CustList<CustList<RendDynOperationNode>>> m_ = new StringMap<CustList<CustList<RendDynOperationNode>>>();
        for (EntryCust<String, CustList<OperationNode>> e:_roots.entryList()) {
            CustList<CustList<RendDynOperationNode>> parts_ = toExecPartExt(e.getValue(), _forwards);
            m_.addEntry(e.getKey(),parts_);
        }

        return m_;
    }
    private static CustList<CustList<RendDynOperationNode>> toExecPartExt(CustList<OperationNode> _roots, Forwards _forwards) {
        CustList<CustList<RendDynOperationNode>> parts_;
        parts_ = new CustList<CustList<RendDynOperationNode>>();
        for (OperationNode r: _roots) {
            parts_.add(getExecutableNodes(r, _forwards));
        }
        return parts_;
    }

    public static CustList<RendDynOperationNode> getExecutableNodes(OperationNode _root, Forwards _forwards) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        if (_root == null){
            return out_;
        }
        OperationNode current_ = _root;
        RendDynOperationNode exp_ = createExecOperationNode(current_, _forwards);
        setImplicits(exp_, current_, _forwards);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof RendMethodOperation &&op_ != null) {
                RendDynOperationNode loc_ = createExecOperationNode(op_, _forwards);
                setImplicits(loc_, op_, _forwards);
                ((RendMethodOperation) exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                if (exp_ instanceof RendAffectationOperation) {
                    ((RendAffectationOperation) exp_).setup();
                }
                if (exp_ instanceof RendSemiAffectationOperation) {
                    ((RendSemiAffectationOperation) exp_).setup();
                }
                if (exp_ instanceof RendCompoundAffectationOperation) {
                    ((RendCompoundAffectationOperation) exp_).setup();
                }
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    RendDynOperationNode loc_ = createExecOperationNode(op_, _forwards);
                    setImplicits(loc_, op_, _forwards);
                    RendMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof AbstractDotOperation && loc_ instanceof RendPossibleIntermediateDotted) {
                        exp_.setSiblingSet((RendPossibleIntermediateDotted) loc_);
                    }
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                RendMethodOperation par_ = exp_.getParent();
                if (op_ == _root) {
                    if (par_ instanceof RendAffectationOperation) {
                        ((RendAffectationOperation) par_).setup();
                    }
                    if (par_ instanceof RendSemiAffectationOperation) {
                        ((RendSemiAffectationOperation) par_).setup();
                    }
                    if (par_ instanceof RendCompoundAffectationOperation) {
                        ((RendCompoundAffectationOperation) par_).setup();
                    }
                    out_.add(par_);
                    current_ = null;
                    break;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
    }

    private static RendDynOperationNode createExecOperationNode(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof InternGlobalOperation) {
            InternGlobalOperation m_ = (InternGlobalOperation) _anaNode;
            return new RendInternGlobalOperation(new ExecOperationContent(m_.getContent()), m_.getOff());
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation c_ = (ForwardOperation) _anaNode;
            return new RendForwardOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediate());
        }
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(c_.getContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isClonedMethod()) {
                return new RendCloneOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), f_.getCallFctContent().getMethodName());
            }
        }
        if (_anaNode instanceof InvokingOperation && _anaNode instanceof AbstractCallFctOperation) {
            InvokingOperation i_ = (InvokingOperation) _anaNode;
            AbstractCallFctOperation a_ = (AbstractCallFctOperation) _anaNode;
            AnaTypeFct classMethodId_ = a_.getFunction();
            if (classMethodId_ != null) {
                if (a_.getStandardMethod() != null) {
                    return new RendStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(a_.getCallFctContent(), a_.isStaticMethod()));
                }
                ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(a_.getMemberId(), _forwards);
                ExecTypeFunction pair_ = FetchMemberUtil.fetchTypeFunction(a_.getMemberId(), _forwards);
                pair_ = FetchMemberUtil.defPair(rootBlock_,pair_);
                if (a_.isTrueFalse()) {
                    return new RendExplicitOperation(pair_,
                            new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent()));
                }
                if (a_.isStaticMethod()) {
                    return new RendStaticFctOperation(pair_,
                            new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctContent(a_.getFunction(),a_.getCallFctContent()), new ExecArrContent(a_.getArrContent()));
                }
                if (rootBlock_ instanceof ExecAnnotationBlock) {
                    return new RendAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
                }
            }
        }
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new RendCallDynMethodOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), c_.getFctName());
        }
        if (_anaNode instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _anaNode;
            return new RendArrayElementOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(i_.getArrayInstancingContent()));
        }
        if (_anaNode instanceof ElementArrayInstancing) {
            ElementArrayInstancing e_ = (ElementArrayInstancing) _anaNode;
            return new RendArrayElementOperation(new ExecOperationContent(e_.getContent()), e_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(e_.getArrayInstancingContent()));
        }
        if (_anaNode instanceof DimensionArrayInstancing) {
            DimensionArrayInstancing d_ = (DimensionArrayInstancing) _anaNode;
            return new RendDimensionArrayInstancing(new ExecOperationContent(d_.getContent()), d_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(d_.getArrayInstancingContent()), d_.getCountArrayDims());
        }
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards);
            if (typeCtor_ == null) {
                return new RendDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()));
            }
            return new RendStandardInstancingOperation(typeCtor_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCommonContent(s_.getInstancingCommonContent()), new ExecInstancingStdContent(s_.getInstancingStdContent()));
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor s_ = (InterfaceFctConstructor) _anaNode;
            return new RendInterfaceFctConstructor(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(s_.getInvokingConstructorContent()), s_.getClassName(), FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecTypeFunction get_ = FetchMemberUtil.fetchTypeFunction(a_.getMemberIdGet(), _forwards);
            ExecTypeFunction set_ = FetchMemberUtil.fetchTypeFunction(a_.getMemberIdSet(), _forwards);
            boolean cust_ = true;
            if (get_ == null) {
                cust_ = false;
            }
            if (set_ == null) {
                cust_ = false;
            }
            if (cust_) {
                return new RendCustArrOperation(a_.isIntermediateDottedOperation(), get_,set_, new ExecOperationContent(a_.getContent()), new ExecArrContent(a_.getArrContent()), new ExecInstFctContent(a_.getCallFctContent(), a_.getAnc(), a_.isStaticChoiceMethod()));
            }
            return new RendArrOperation(a_.isIntermediateDottedOperation(), new ExecOperationContent(a_.getContent()), new ExecArrContent(a_.getArrContent()));
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new RendIdOperation(new ExecOperationContent(d_.getContent()));
            }
            return new RendMultIdOperation(new ExecOperationContent(d_.getContent()));
        }
        if (_anaNode instanceof EnumValueOfOperation) {
            EnumValueOfOperation d_ = (EnumValueOfOperation) _anaNode;
            return new RendEnumValueOfOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _forwards));
        }
        if (_anaNode instanceof ValuesOperation) {
            ValuesOperation d_ = (ValuesOperation) _anaNode;
            return new RendValuesOperation(new ExecOperationContent(d_.getContent()), new ExecValuesContent(d_.getValuesContent(), _forwards));
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new RendTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal());
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecTypeFunction ex_ = FetchMemberUtil.fetchTypeFunction(c_.getMemberId(), _forwards);
            if (ex_ != null) {
                return new RendChoiceFctOperation(ex_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true), new ExecArrContent(c_.getArrContent()));
            }
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecTypeFunction ex_ = FetchMemberUtil.fetchTypeFunction(s_.getMemberId(), _forwards);
            if (ex_ != null) {
                return new RendSuperFctOperation(ex_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true), new ExecArrContent(s_.getArrContent()));
            }
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchTypeFunction(f_.getMemberId(), _forwards);
            if (p_ != null) {
                return new RendFctOperation(p_, new ExecOperationContent(f_.getContent()), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod()), f_.isIntermediateDottedOperation(), new ExecArrContent(f_.getArrContent()));
            }
        }
        if (_anaNode instanceof NamedArgumentOperation) {
            NamedArgumentOperation f_ = (NamedArgumentOperation) _anaNode;
            return new RendNamedArgumentOperation(new ExecOperationContent(f_.getContent()), new ExecNamedContent(f_.getNamedContent()));
        }
        if (_anaNode instanceof FirstOptOperation) {
            FirstOptOperation f_ = (FirstOptOperation) _anaNode;
            return new RendFirstOptOperation(new ExecOperationContent(f_.getContent()), f_.getOffset());
        }
        if (_anaNode instanceof WrappOperation) {
            WrappOperation f_ = (WrappOperation) _anaNode;
            return new RendWrappOperation(new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof StaticAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof VarargOperation) {
            VarargOperation f_ = (VarargOperation) _anaNode;
            return new RendConstLeafOperation(true,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof IdFctOperation) {
            IdFctOperation f_ = (IdFctOperation) _anaNode;
            return new RendConstLeafOperation(true,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            return new RendLambdaOperation(new ExecOperationContent(f_.getContent()), new ExecLambdaCommonContent(f_.getLambdaCommonContent()), new ExecLambdaMethodContent(f_.getMethod(), f_.getLambdaMethodContent(), f_.getLambdaMemberNumberContentId(), _forwards), new ExecLambdaConstructorContent(f_.getRealId(), f_.getLambdaMemberNumberContentId(), _forwards), new ExecLambdaFieldContent(f_.getFieldId(), f_.getLambdaFieldContent(), f_.getLambdaMemberNumberContentId(), _forwards), f_.getStandardMethod(), f_.getStandardType());
        }
        if (_anaNode instanceof StaticInfoOperation) {
            StaticInfoOperation f_ = (StaticInfoOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(f_.getContent()));
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            return new RendDefaultOperation(new ExecOperationContent(f_.getContent()), f_.getOffset());
        }
        if (_anaNode instanceof ThisOperation) {
            ThisOperation f_ = (ThisOperation) _anaNode;
            return new RendThisOperation(new ExecOperationContent(f_.getContent()), f_.getThisContent().getOff());
        }
        if (_anaNode instanceof ParentInstanceOperation) {
            ParentInstanceOperation f_ = (ParentInstanceOperation) _anaNode;
            return new RendParentInstanceOperation(new ExecOperationContent(f_.getContent()), new ExecParentInstanceContent(f_.getParentInstanceContent()));
        }
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            if (s_.getFieldId() == null) {
                return new RendDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
            }
            return new RendSettableFieldOperation(new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()), new ExecSettableOperationContent(s_.getSettableFieldContent()), FetchMemberUtil.fetchType(s_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new RendArrayFieldOperation(new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()));
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation m_ = (MutableLoopVariableOperation) _anaNode;
            return new RendStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof RefVariableOperation) {
            RefVariableOperation m_ = (RefVariableOperation) _anaNode;
            return new RendStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()),m_.isDeclare());
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new RendStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof FinalVariableOperation) {
            FinalVariableOperation m_ = (FinalVariableOperation) _anaNode;
            if (m_.getType() == ConstType.LOOP_INDEX) {
                return new RendFinalVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
            }
            return new RendStdVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new RendDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            return new RendSafeDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new RendExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getFunction(),m_.getCallFctContent()), FetchMemberUtil.fetchFunctionOp(m_.getMemberId(), _forwards), FetchMemberUtil.fetchType(m_.getMemberId(), _forwards), m_.getOffsetOper(), new ExecArrContent(m_.getArrContent()));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new RendSemiAffectationOperation(new ExecOperationContent(m_.getContent()), new ExecStaticPostEltContent(m_.getFunction(),m_.getClassName(), m_.isPost()), new ExecOperatorContent(m_.getOperatorContent()), FetchMemberUtil.fetchFunctionOp(m_.getMemberId(), _forwards), FetchMemberUtil.fetchType(m_.getMemberId(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConverterFrom(), m_.getMemberIdFrom(), _forwards), FetchMemberUtil.fetchImplicits(m_.getConverterTo(), m_.getMemberIdTo(), _forwards));
        }
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new RendUnaryBooleanOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof UnaryBinOperation) {
            UnaryBinOperation m_ = (UnaryBinOperation) _anaNode;
            return new RendUnaryBinOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (!n_.isOkNum()) {
                return new RendDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
            }
            AnaTypeFct pair_ = n_.getFunction();
            if (pair_ != null) {
                return new RendCustNumericOperation(
                        FetchMemberUtil.fetchFunctionOp(n_.getMemberId(), _forwards),
                        FetchMemberUtil.fetchType(n_.getMemberId(), _forwards), new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), new ExecStaticEltContent(pair_,n_.getClassName()));
            }
        }
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new RendUnaryOperation(new ExecOperationContent(m_.getContent()), m_.getOper());
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new RendCastOperation(new ExecOperationContent(m_.getContent()), new ExecTypeCheckContent(m_.getTypeCheckContent()));
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return new RendExplicitOperation(
                    FetchMemberUtil.fetchTypeFunction(m_.getMemberId(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new RendImplicitOperation(
                    FetchMemberUtil.fetchTypeFunction(m_.getMemberId(), _forwards), new ExecOperationContent(m_.getContent()), new ExecExplicitContent(m_.getExplicitContent()));
        }
        if (_anaNode instanceof MultOperation) {
            MultOperation m_ = (MultOperation) _anaNode;
            return new RendMultOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof AddOperation) {
            AddOperation m_ = (AddOperation) _anaNode;
            return new RendAddOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp(), m_.isCatString());
        }
        if (_anaNode instanceof ShiftLeftOperation) {
            ShiftLeftOperation m_ = (ShiftLeftOperation) _anaNode;
            return new RendShiftLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof ShiftRightOperation) {
            ShiftRightOperation m_ = (ShiftRightOperation) _anaNode;
            return new RendShiftRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof BitShiftLeftOperation) {
            BitShiftLeftOperation m_ = (BitShiftLeftOperation) _anaNode;
            return new RendBitShiftLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof BitShiftRightOperation) {
            BitShiftRightOperation m_ = (BitShiftRightOperation) _anaNode;
            return new RendBitShiftRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof RotateLeftOperation) {
            RotateLeftOperation m_ = (RotateLeftOperation) _anaNode;
            return new RendRotateLeftOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof RotateRightOperation) {
            RotateRightOperation m_ = (RotateRightOperation) _anaNode;
            return new RendRotateRightOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset(), m_.getOp());
        }
        if (_anaNode instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _anaNode;
            return new RendAbstractCmpOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), c_.isStringCompare());
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new RendInstanceOfOperation(new ExecOperationContent(c_.getContent()), new ExecTypeCheckContent(c_.getTypeCheckContent()));
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new RendEqOperation(new ExecOperationContent(c_.getContent()), c_.getOper());
        }
        if (_anaNode instanceof BitAndOperation) {
            BitAndOperation c_ = (BitAndOperation) _anaNode;
            return new RendBitAndOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof BitOrOperation) {
            BitOrOperation c_ = (BitOrOperation) _anaNode;
            return new RendBitOrOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof BitXorOperation) {
            BitXorOperation c_ = (BitXorOperation) _anaNode;
            return new RendBitXorOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.getOp());
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation n_ = (NullSafeOperation) _anaNode;
            return new RendNullSafeOperation(new ExecOperationContent(n_.getContent()));
        }
        if (_anaNode instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _anaNode;
            if (!q_.isOkNum()) {
                return new RendDeclaringOperation(new ExecOperationContent(q_.getContent()));
            }
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new RendAndOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(c_.getMemberId(), _forwards), FetchMemberUtil.fetchType(c_.getMemberId(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberConverter(), _forwards));
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new RendOrOperation(new ExecOperationContent(c_.getContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(c_.getMemberId(), _forwards), FetchMemberUtil.fetchType(c_.getMemberId(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberConverter(), _forwards));
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            return new RendCompoundAffectationOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecStaticEltContent(c_.getFunction(),c_.getClassName()), FetchMemberUtil.fetchFunctionOp(c_.getMemberId(), _forwards), FetchMemberUtil.fetchType(c_.getMemberId(), _forwards), FetchMemberUtil.fetchImplicits(c_.getConverter(), c_.getMemberIdConv(), _forwards));
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new RendAffectationOperation(new ExecOperationContent(a_.getContent()));
        }
        return new RendDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }

    private static AnaArrContent generareArrContent() {
        AnaArrContent cont_ = new AnaArrContent();
        cont_.setVariable(true);
        return cont_;
    }

    private static CustList<RendDynOperationNode> buildWritePartArr(ResultInput _resultInput, ArrOperation _settable) {
        CustList<RendDynOperationNode> w_ = new CustList<RendDynOperationNode>();
        String cl_ = NumParsers.getSingleNameOrEmpty(_resultInput.getResult().getNames());
        ExecClassArgumentMatching pr_ = FetchMemberUtil.toExec(_resultInput.getPreviousResult());
        CustList<OperationNode> childrenNodes_ = _settable.getChildrenNodes();
        RendAffectationOperation rendAff_ = new RendAffectationOperation(new ExecOperationContent(0, pr_, 4+childrenNodes_.size()));
        ExecClassArgumentMatching clResField_ = new ExecClassArgumentMatching(cl_);
        RendDotOperation rendDot_ = new RendDotOperation(new ExecOperationContent(0, clResField_, 2+childrenNodes_.size()));
        RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(new ExecOperationContent(0, pr_, 0), new ExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        RendArrOperation arr_ = new RendArrOperation(true, new ExecOperationContent(1, pr_, childrenNodes_.size() + 1), new ExecArrContent(generareArrContent()));
        int i_ = 1;
        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
        for (OperationNode o: childrenNodes_) {
            String varParam_ = _resultInput.getVarNames().get(i_);
            RendStdVariableOperation rendVar_ = new RendStdVariableOperation(new ExecOperationContent(i_-1, FetchMemberUtil.toExec(o.getResultClass()), i_), new ExecVariableContent(generateVariable(varParam_)));
            arr_.appendChild(rendVar_);
            list_.add(rendVar_);
            i_++;
        }
        String varLoc_ = _resultInput.getVarNames().last();
        rendPrevVar_.setSiblingSet(arr_);
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(arr_);
        rendAff_.appendChild(rendDot_);
        RendStdVariableOperation rendVar_ = new RendStdVariableOperation(new ExecOperationContent(0, clResField_, childrenNodes_.size() + 3), new ExecVariableContent(generateVariable(varLoc_)));
        rendAff_.appendChild(rendVar_);
        rendAff_.setup();
        w_.add(rendPrevVar_);
        w_.addAllElts(list_);
        w_.add(arr_);
        w_.add(rendDot_);
        w_.add(rendVar_);
        w_.add(rendAff_);
        return w_;
    }

    private static CustList<RendDynOperationNode> buildWritePartCustArr(ResultInput _resultInput, ArrOperation _settable, Forwards _forwards) {
        CustList<RendDynOperationNode> w_ = new CustList<RendDynOperationNode>();
        ExecTypeFunction get_ = FetchMemberUtil.fetchTypeFunction(_settable.getMemberIdGet(), _forwards);
        ExecTypeFunction set_ = FetchMemberUtil.fetchTypeFunction(_settable.getMemberIdSet(), _forwards);
        String cl_ = NumParsers.getSingleNameOrEmpty(_resultInput.getResult().getNames());
        ExecClassArgumentMatching pr_ = FetchMemberUtil.toExec(_resultInput.getPreviousResult());
        ExecClassArgumentMatching clResField_ = new ExecClassArgumentMatching(cl_);
        RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(new ExecOperationContent(0, pr_, 0), new ExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        AnaCallFctContent callFctContent_ = new AnaCallFctContent("");
        callFctContent_.setClassName(_settable.getCallFctContent().getClassName());
        ExecInstFctContent instFctContent_ = new ExecInstFctContent(callFctContent_, _settable.getAnc(), _settable.isStaticChoiceMethod());
        MethodId constraints_ = _settable.getCallFctContent().getClassMethodId().getConstraints();
        int nbParam_ = constraints_.getParametersTypesLength();
        int refCount_ = 0;
        for (int i = 0; i < nbParam_; i++) {
            if (constraints_.getParametersRef(i)) {
                refCount_++;
            }
        }
        RendCustArrOperation arr_ = new RendCustArrOperation(true,get_,set_, new ExecOperationContent(1, pr_, refCount_+nbParam_ + 1), new ExecArrContent(generareArrContent()), instFctContent_);
        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
        InputInfo varNamesParams_ = _resultInput.getVarNamesParams();
        StringList varNames_ = varNamesParams_.getVarNames();
        int order_ = 1;
        for (int i = 0; i < nbParam_; i++) {
            String varParam_ = varNames_.get(i);
            if (constraints_.getParametersRef(i)) {
                RendStdRefVariableOperation rendVar_ = new RendStdRefVariableOperation(new ExecOperationContent(i, FetchMemberUtil.toExec(new AnaClassArgumentMatching("")), order_), new ExecVariableContent(generateVariable(varParam_)),false);
                order_++;
                RendWrappOperation wr_ = new RendWrappOperation(new ExecOperationContent(i, FetchMemberUtil.toExec(new AnaClassArgumentMatching("")), order_));
                wr_.appendChild(rendVar_);
                arr_.appendChild(wr_);
                list_.add(rendVar_);
                list_.add(wr_);
                order_++;
            } else {
                RendStdVariableOperation rendVar_ = new RendStdVariableOperation(new ExecOperationContent(i, FetchMemberUtil.toExec(new AnaClassArgumentMatching("")), order_), new ExecVariableContent(generateVariable(varParam_)));
                arr_.appendChild(rendVar_);
                list_.add(rendVar_);
                order_++;
            }
        }
        RendAffectationOperation rendAff_ = new RendAffectationOperation(new ExecOperationContent(0, pr_, 4+refCount_+nbParam_));
        String varLoc_ = _resultInput.getVarNames().last();
        rendPrevVar_.setSiblingSet(arr_);
        RendDotOperation rendDot_ = new RendDotOperation(new ExecOperationContent(0, clResField_, 2+refCount_+nbParam_));
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(arr_);
        rendAff_.appendChild(rendDot_);
        RendStdVariableOperation rendVar_ = new RendStdVariableOperation(new ExecOperationContent(0, clResField_, refCount_+nbParam_ + 3), new ExecVariableContent(generateVariable(varLoc_)));
        rendAff_.appendChild(rendVar_);
        rendAff_.setup();
        w_.add(rendPrevVar_);
        w_.addAllElts(list_);
        w_.add(arr_);
        w_.add(rendDot_);
        w_.add(rendVar_);
        w_.add(rendAff_);
        return w_;
    }

    private static AnaVariableContent generateVariable(String _varLoc) {
        AnaVariableContent cont_ = new AnaVariableContent(0);
        cont_.setDeep(-1);
        cont_.setVariableName(_varLoc);
        return cont_;
    }

    private static CustList<RendDynOperationNode> buildWritePartField(ResultInput _resultInput, SettableAbstractFieldOperation _settable, Forwards _forwards) {
        CustList<RendDynOperationNode> w_ = new CustList<RendDynOperationNode>();
        String cl_ = NumParsers.getSingleNameOrEmpty(_resultInput.getResult().getNames());
        ExecClassArgumentMatching pr_ = FetchMemberUtil.toExec(_resultInput.getPreviousResult());
        RendAffectationOperation rendAff_ = new RendAffectationOperation(new ExecOperationContent(0, pr_, 4));
        ExecClassArgumentMatching clResField_ = new ExecClassArgumentMatching(cl_);
        RendDotOperation rendDot_ = new RendDotOperation(new ExecOperationContent(0, clResField_, 2));
        RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(new ExecOperationContent(0, pr_, 0), new ExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(_settable.getMemberId(), _forwards);
        AnaFieldOperationContent cont_ = new AnaFieldOperationContent(0);
        cont_.setIntermediate(true);
        RendSettableFieldOperation rendField_ = new RendSettableFieldOperation(new ExecOperationContent(1,pr_,1),
                new ExecFieldOperationContent(cont_), new ExecSettableOperationContent(_settable.getSettableFieldContent()),rootBlock_);
        rendPrevVar_.setSiblingSet(rendField_);
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(rendField_);
        rendAff_.appendChild(rendDot_);
        RendStdVariableOperation rendVar_ = new RendStdVariableOperation(new ExecOperationContent(0, clResField_, 3), new ExecVariableContent(generateVariable(_resultInput.getVarNames().last())));
        rendAff_.appendChild(rendVar_);
        rendAff_.setup();

        w_.add(rendPrevVar_);
        w_.add(rendField_);
        w_.add(rendDot_);
        w_.add(rendVar_);
        w_.add(rendAff_);
        return w_;
    }

    private static void setImplicits(RendDynOperationNode _ex, OperationNode _ana, Forwards _forwards){
        AnaClassArgumentMatching ana_ = _ana.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        FetchMemberUtil.setImplicits(ana_,implicits_,implicitsTest_, _forwards);
    }

    private static void initValidatorsInstance(AnalyzingDoc _anaDoc, Forwards _forwards) {
        for (EntryCust<OperationNode, ValidatorInfo> e: _anaDoc.getLateValidators().entryList()) {
            ValidatorInfo v_ = e.getValue();
            OperationNode root_ = e.getKey();
            CustList<RendDynOperationNode> exps_ = getExecutableNodes(root_, _forwards);
            v_.setExps(exps_);
        }
    }

    private static void initBeansInstances(AnalyzingDoc _anaDoc, Forwards _forwards) {
        for (EntryCust<OperationNode, BeanInfo> e: _anaDoc.getBeansInfos().entryList()) {
            OperationNode root_ = e.getKey();
            CustList<RendDynOperationNode> exps_ = getExecutableNodes(root_, _forwards);
            e.getValue().setExps(exps_);
        }
    }

    public static void buildExec(AnalyzingDoc _analyzingDoc, StringMap<AnaRendDocumentBlock> _d, Forwards _forwards, Configuration _conf) {
        buildExec(_d, _forwards, _conf, _analyzingDoc);
        initBeansInstances(_analyzingDoc, _forwards);
        initValidatorsInstance(_analyzingDoc, _forwards);
    }

    private static void buildExec(StringMap<AnaRendDocumentBlock> _d, Forwards _forwards, Configuration _conf, AnalyzingDoc _anaDoc) {
        for (EntryCust<String,AnaRendDocumentBlock> v: _d.entryList()) {
            RendDocumentBlock rendDoc_ = build(v.getValue(), _forwards, _anaDoc);
            _conf.getRenders().put(v.getKey(), rendDoc_);
        }
        String currentUrl2_ = _conf.getFirstUrl();
        String realFilePath2_ = Configuration.getRealFilePath(_conf.getCurrentLanguage(), currentUrl2_);
        _conf.setRendDocumentBlock(_conf.getRenders().getVal(realFilePath2_));
    }
}
