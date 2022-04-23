package code.formathtml.fwd;

import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.InternGlobalOperation;
import code.formathtml.analyze.blocks.*;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendForwardInfos {
    private RendForwardInfos() {
    }
    private static RendDocumentBlock build(AnaRendDocumentBlock _ana, Forwards _forwards, AnalyzingDoc _anaDoc) {
        RendDocumentBlock rendDoc_ = new RendDocumentBlock(_ana.getFileName(),_ana.getEsc(),_ana.getFile().getMetricsCore(), _ana.getElt(), _ana.getBeanName(), fwdType(_ana, _forwards));
        RendAnaExec pair_ = new RendAnaExec(_ana, rendDoc_);
        while (pair_.getRead() != null) {
            RendBlock loc_ = newRendBlock(pair_.getRead(), _forwards);
            pair_.setWrite(complete(_anaDoc, rendDoc_, pair_.getWrite(), loc_));
            nextPair(pair_);
        }
        ForwardInfos.feedFct(_ana,rendDoc_,_forwards);
        return rendDoc_;
    }

    private static ExecFormattedRootBlock fwdType(AnaRendDocumentBlock _ana, Forwards _forwards) {
        AnaFormattedRootBlock declClass_ = _ana.getDeclClass();
        if (declClass_.getRootBlock() == null) {
            return ExecFormattedRootBlock.defValue();
        }
        return FetchMemberUtil.fwdFormatType(declClass_, _forwards);
    }

    public static void nextPair(RendAnaExec _pair) {
        AnaRendBlock n_ = _pair.getRead().getFirstChild();
        if (n_ != null) {
            _pair.setRead(n_);
            return;
        }
        while (_pair.getRead() != null) {
            n_ = _pair.getRead().getNextSibling();
            if (n_ != null) {
                _pair.setRead(n_);
                break;
            }
            AnaRendParentBlock parent_ = _pair.getRead().getParent();
            _pair.setWrite(_pair.getWrite().getParent());
            if (_pair.getWrite() == null) {
                _pair.setRead(null);
            } else {
                _pair.setRead(parent_);
            }
        }
    }

    private static RendParentBlockInt complete(AnalyzingDoc _anaDoc, RendDocumentBlock _rendDoc, RendParentBlockInt _curPar, RendBlock _loc) {
        if (_loc != null) {
            if (_loc instanceof RendStdElement && StringUtil.quickEq(((RendStdElement) _loc).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                _rendDoc.getBodies().add(_loc);
            }
//            _loc.setEscapedChars(_en.getEscapedChars());
            _curPar.appendChild(_loc);
        }
        if (_loc instanceof RendParentBlockInt) {
            return (RendParentBlockInt) _loc;
        }
        return _curPar;
    }

    private static RendBlock newRendBlock(AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendEmptyText){
            return new RendEmptyText(((AnaRendEmptyText)_current).getExpression(),((AnaRendEmptyText)_current).isAdd());
        }
        if (_current instanceof AnaRendText){
            AnaRendText t_ = (AnaRendText) _current;
            DefExecTextPart part_ = toExecPartExt(t_.getRoots(),t_.getTexts(), _forwards);
            return new RendText(part_,t_.getExpressionOffset());
        }
        if (_current instanceof AnaRendForEachLoop){
            AnaRendForEachLoop f_ = (AnaRendForEachLoop) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            if (f_.getRoot().getResultClass().isArray()) {
                if (f_.isRefVar()) {
                    return new RendForEachRefArray(f_.getImportedClassName(),f_.getVariableName(),
                            f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(), op_);
                }
                return new RendForEachArray(f_.getImportedClassName(),f_.getVariableName(),
                        f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(), op_);
            }
            return new RendForEachIterable(f_.getImportedClassName(),f_.getVariableName(),
                    f_.getExpressionOffset(),f_.getImportedClassIndexName(),f_.getRealLabel(), op_);
        }
        return block2(_current, _forwards);
    }

    private static RendBlock block2(AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendForEachTable){
            AnaRendForEachTable f_ = (AnaRendForEachTable) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendForEachTable(f_.getImportedClassNameFirst(),f_.getVariableNameFirst(),
                    f_.getImportedClassNameSecond(),f_.getVariableNameSecond(),
                    f_.getImportedClassIndexName(),f_.getRealLabel(), new RendOperationNodeListOff(op_, f_.getExpressionOffset()));
        }
        if (_current instanceof AnaRendForIterativeLoop){
            AnaRendForIterativeLoop f_ = (AnaRendForIterativeLoop) _current;
            CustList<RendDynOperationNode> opInit_ = getExecutableNodes(f_.getRootInit(), _forwards);
            CustList<RendDynOperationNode> opExp_ = getExecutableNodes(f_.getRootExp(), _forwards);
            CustList<RendDynOperationNode> opStep_ = getExecutableNodes(f_.getRootStep(), _forwards);
            return buildItFor(f_, opInit_, opExp_, opStep_);
        }
        if (_current instanceof AnaRendForMutableIterativeLoop){
            AnaRendForMutableIterativeLoop f_ = (AnaRendForMutableIterativeLoop) _current;
            CustList<RendDynOperationNode> opInit_ = getExecutableNodes(f_.getRootInit(), _forwards);
            CustList<RendDynOperationNode> opExp_ = getExecutableNodes(f_.getRootExp(), _forwards);
            CustList<RendDynOperationNode> opStep_ = getExecutableNodes(f_.getRootStep(), _forwards);
            return new RendForMutableIterativeLoop(f_.getImportedClassName(),
                    f_.getVariableNames(),
                    f_.getImportedClassIndexName(),f_.getRealLabel(),
                    new RendOperationNodeListOff(opInit_, f_.getInitOffset()), new RendOperationNodeListOff(opExp_, f_.getExpressionOffset()), new RendOperationNodeListOff(opStep_, f_.getStepOffset()));
        }
        if (_current instanceof AnaRendWhileCondition){
            AnaRendWhileCondition f_ = (AnaRendWhileCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendWhileCondition(op_,f_.getConditionOffset(),f_.getRealLabel());
        }
        if (_current instanceof AnaRendDoBlock){
            AnaRendDoBlock f_ = (AnaRendDoBlock) _current;
            return new RendDoBlock(f_.getRealLabel());
        }
        if (_current instanceof AnaRendDoWhileCondition){
            AnaRendDoWhileCondition f_ = (AnaRendDoWhileCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendDoWhileCondition(op_,f_.getConditionOffset());
        }
        if (_current instanceof AnaRendReturnMehod){
            return new RendReturnMehod();
        }
        if (_current instanceof AnaRendBreakBlock){
            AnaRendBreakBlock f_ = (AnaRendBreakBlock) _current;
            return new RendBreakBlock(f_.getLabel());
        }
        if (_current instanceof AnaRendContinueBlock){
            AnaRendContinueBlock f_ = (AnaRendContinueBlock) _current;
            return new RendContinueBlock(f_.getLabel());
        }
        if (_current instanceof AnaRendThrowing){
            AnaRendThrowing f_ = (AnaRendThrowing) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendThrowing(op_,f_.getExpressionOffset());
        }
        if (_current instanceof AnaRendDeclareVariable){
            AnaRendDeclareVariable f_ = (AnaRendDeclareVariable) _current;
            return new RendDeclareVariable(f_.getImportedClassName(), f_.getVariableNames());
        }
        if (_current instanceof AnaRendLine){
            AnaRendLine f_ = (AnaRendLine) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendLine(op_,f_.getExpressionOffset());
        }
        return block(_current, _forwards);
    }

    private static RendBlock block(AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendIfCondition){
            AnaRendIfCondition f_ = (AnaRendIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendIfCondition(op_,f_.getConditionOffset(),f_.getRealLabel());
        }
        if (_current instanceof AnaRendElseIfCondition){
            AnaRendElseIfCondition f_ = (AnaRendElseIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendElseIfCondition(op_,f_.getConditionOffset());
        }
        if (_current instanceof AnaRendElseCondition){
            return new RendElseCondition();
        }
        if (_current instanceof AnaRendTryEval){
            AnaRendTryEval f_ = (AnaRendTryEval) _current;
            return new RendTryEval(f_.getRealLabel());
        }
        if (_current instanceof AnaRendCatchEval){
            AnaRendCatchEval f_ = (AnaRendCatchEval) _current;
            return new RendCatchEval(f_.getImportedClassName(),f_.getVariableName());
        }
        if (_current instanceof AnaRendNullCatchEval){
            return new RendNullCatchEval();
        }
        if (_current instanceof AnaRendFinallyEval){
            return new RendFinallyEval();
        }
        if (_current instanceof AnaRendSwitchBlock){
            return buildSwitch((AnaRendSwitchBlock) _current, _forwards);
        }
        if (_current instanceof AnaRendCaseCondition){
            return buildCaseCondition((AnaRendCaseCondition) _current, _forwards);
//            return new RendCaseCondition(f_.isBuiltEnum(),f_.getImportedClassName(),f_.getVariableName(),f_.getValue(),f_.getArgument());
        }
        if (_current instanceof AnaRendDefaultCondition){
            return buildDefaultCondition((AnaRendDefaultCondition) _current);
        }
        if (_current instanceof AnaRendImport){
            AnaRendImport f_ = (AnaRendImport) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRootPage(), _forwards);
            return new RendImport(f_.getElt(), new RendOperationNodeListOff(op_,f_.getPageOffset()));
        }
        return element(_current, _forwards);
    }

    private static RendBlock element(AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendSubmit){
            AnaRendSubmit f_ = (AnaRendSubmit) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getOpExp(), _forwards);
            return new RendSubmit(f_.getRead(),part_,partText_,partSub_,f_.getPreformatted());
        }
        if (_current instanceof AnaRendAnchor){
            AnaRendAnchor f_ = (AnaRendAnchor) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            DefExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendAnchor(f_.getRead(),part_,partText_,op_, f_.getVarNames(),partSub_);
        }
        if (_current instanceof AnaRendImg){
            AnaRendImg f_ = (AnaRendImg) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRootSrc(), _forwards);
            return new RendImg(f_.getRead(), part_,partText_, new RendOperationNodeListOff(op_, f_.getOffSrc()));
        }
        if (_current instanceof AnaRendLink){
            AnaRendLink f_ = (AnaRendLink) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getOpExpTitle(), _forwards);
            return new RendLink(f_.getRead(),part_,partText_,f_.getContent(),partSub_);
        }
        if (_current instanceof AnaRendStyle){
            AnaRendStyle f_ = (AnaRendStyle) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStyle(f_.getRead(),part_,partText_);
        }
        if (_current instanceof AnaRendEscImg){
            AnaRendEscImg f_ = (AnaRendEscImg) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendEscImg(f_.getRead(),part_,partText_);
        }
        if (_current instanceof AnaRendPackage){
            return new RendClass("");
        }
        if (_current instanceof AnaRendForm){
            AnaRendForm f_ = (AnaRendForm) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> opForm_ = getExecutableNodes(f_.getRoot(), _forwards);
            DefExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            return new RendForm(f_.getRead(),part_,partText_,opForm_,f_.getVarNames(),partSub_);
        }
        if (_current instanceof AnaRendImportForm){
            AnaRendImportForm f_ = (AnaRendImportForm) _current;
            return new RendImportForm(f_.getName());
        }
        if (_current instanceof AnaRendClass){
            AnaRendClass f_ = (AnaRendClass) _current;
            return new RendClass(f_.getFullName());
        }
        if (_current instanceof AnaRendField){
            AnaRendField f_ = (AnaRendField) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendField(op_,f_.getPrepareOffset());
        }
        if (_current instanceof AnaRendMessage){
            AnaRendMessage f_ = (AnaRendMessage) _current;
            CustList<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getRoots(), _forwards);
            StringMap<CustList<CustList<RendDynOperationNode>>> map_ = toExecPartMapExt(f_.getCallsRoots(), _forwards);
            return new RendMessage(partSub_,
                    f_.getPreformatted(),f_.getQuoted(),f_.getEscaped(),map_,
                    f_.getArgs(),f_.getLocDoc(),
                    f_.getVarNames());
        }
        return input(_current, _forwards);
    }

    private static RendBlock input(AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendSelect){
            AnaRendSelect f_ = (AnaRendSelect) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opConverterFieldValue_ = getExecutableNodes(f_.getRootConverterFieldValue(), _forwards);
            CustList<RendDynOperationNode> opDefault_ = getExecutableNodes(f_.getRootDefault(), _forwards);
            CustList<RendDynOperationNode> opMap_ = getExecutableNodes(f_.getRootMap(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendSelect(opRead_,opValue_, opMap_,opDefault_,opConverter_,opConverterField_,opConverterFieldValue_
            ,partText_,part_, f_.getIdClass(),f_.getIdName(),f_.getElt(),f_.isMultiple(),f_.getVarNameConverter(),f_.getVarNameConverterField(),
                    f_.getVarNameConverterFieldValue(),f_.getClassName(),f_.isArrayConverter());
        }
        if (_current instanceof AnaRendRadio){
            AnaRendRadio f_ = (AnaRendRadio) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opConverterFieldValue_ = getExecutableNodes(f_.getRootConverterFieldValue(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> nbRad_ = getExecutableNodes(f_.getRootRadio(), _forwards);
            return new RendRadio(f_.getRead(),part_,partText_, opValue_, opConverterField_
                    , f_.getVarNameConverterField(), opConverterFieldValue_,
                    f_.getVarNameConverterFieldValue(), RendInput.initUpdates(f_.getIdClass(), f_.getIdName(), opRead_, f_.getVarNameConverter(), opConverter_, f_.getClassName(), nbRad_));
        }
        if (_current instanceof AnaRendStdInput){
            AnaRendStdInput f_ = (AnaRendStdInput) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdInput(f_.getRead(),part_,partText_, opValue_, opConverterField_
                    , f_.getVarNameConverterField(), RendInput.initUpdates(f_.getIdClass(), f_.getIdName(), opRead_, f_.getVarNameConverter(), opConverter_, f_.getClassName(), new CustList<RendDynOperationNode>()));
        }
        if (_current instanceof AnaRendTextArea){
            AnaRendTextArea f_ = (AnaRendTextArea) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = getExecutableNodes(f_.getRootConverter(), _forwards);
            CustList<RendDynOperationNode> opConverterField_ = getExecutableNodes(f_.getRootConverterField(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendTextArea(opRead_,opValue_, opConverter_,opConverterField_
                    ,partText_,part_,f_.getVarNameConverter(),f_.getVarNameConverterField(), f_.getIdClass(),f_.getIdName(),f_.getClassName(),f_.getElt());
        }
        if (_current instanceof AnaRendSpan){
            AnaRendSpan f_ = (AnaRendSpan) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRootFor(), _forwards);
            return new RendSpan(f_.getRead(),part_,partText_, f_.getFormatted(), new RendOperationNodeListOff(op_,f_.getOffFor()));
        }
        if (_current instanceof AnaRendTitledAnchor){
            AnaRendTitledAnchor f_ = (AnaRendTitledAnchor) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            DefExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts(), _forwards);
            StringMap<CustList<RendDynOperationNode>> title_ = toExecPartExt(f_.getOpExpTitle(), _forwards);
            CustList<RendDynOperationNode> opAnc_ = getExecutableNodes(f_.getRoot(), _forwards);
            return new RendTitledAnchor(f_.getRead(),part_,partText_,opAnc_,f_.getVarNames(),title_,f_.getPreformatted(),partSub_);
        }
        if (_current instanceof AnaRendEmptyInstruction){
            return new RendEmptyInstruction();
        }
        if (_current instanceof AnaRendStdElement) {
            AnaRendStdElement f_ = (AnaRendStdElement) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdElement(f_.getRead(), part_, partText_);
        }
        return null;
    }

    private static RendParentBlock buildDefaultCondition(AnaRendDefaultCondition _current) {
        String instanceTest_ = _current.getImportedClassName();
        if (!instanceTest_.isEmpty()) {
            return new RendAbstractInstanceCaseCondition(_current.getVariableName(), _current.getImportedClassName(), false, new CustList<RendDynOperationNode>(), 0);
        }
        return new RendDefaultCondition();
    }

    private static RendBlock buildCaseCondition(AnaRendCaseCondition _current, Forwards _fwd) {
        RendBlock exec_;
        if (!_current.getImportedClassName().isEmpty()) {
            exec_ = new RendAbstractInstanceCaseCondition(_current.getVariableName(), _current.getImportedClassName(), true, getExecutableNodes(_current.getRoot(),_fwd), _current.getValueOffset());
        } else {
            exec_ = new RendSwitchValuesCondition(_current.getStdValues(), _current.getEnumValues());
        }
        return exec_;
    }

    private static RendAbsSwitchBlock buildSwitch(AnaRendSwitchBlock _current, Forwards _forwards) {
        CustList<RendDynOperationNode> op_ = getExecutableNodes(_current.getRoot(), _forwards);
        if (_current.isInstance()) {
            return new RendSwitchInstBlock(_current.getRealLabel(), _current.getValueOffset(),op_, _current.getInstanceTest());
        }
        return new RendSwitchBlock(_current.getRealLabel(), _current.getValueOffset(),op_, _current.getInstanceTest());
    }

    private static RendForIterativeLoop buildItFor(AnaRendForIterativeLoop _f, CustList<RendDynOperationNode> _opInit, CustList<RendDynOperationNode> _opExp, CustList<RendDynOperationNode> _opStep) {
        if (_f.isEq()) {
            return new RendForIterativeLoopEq(_f.getImportedClassName(), _f.getVariableName(),
                    _f.getImportedClassIndexName(), _f.getRealLabel(),
                    new RendOperationNodeListOff(_opInit, _f.getInitOffset()), new RendOperationNodeListOff(_opExp, _f.getExpressionOffset()), new RendOperationNodeListOff(_opStep, _f.getStepOffset()));
        }
        return new RendForIterativeLoopStrict(_f.getImportedClassName(), _f.getVariableName(),
                _f.getImportedClassIndexName(), _f.getRealLabel(),
                new RendOperationNodeListOff(_opInit, _f.getInitOffset()), new RendOperationNodeListOff(_opExp, _f.getExpressionOffset()), new RendOperationNodeListOff(_opStep, _f.getStepOffset()));
    }

    private static StringMap<CustList<RendDynOperationNode>> toExecPartExt(StringMap<ResultExpression> _texts, Forwards _forwards) {
        StringMap<CustList<RendDynOperationNode>> m_ = new StringMap<CustList<RendDynOperationNode>>();
        for (EntryCust<String,ResultExpression> e: _texts.entryList()) {
            ResultExpression value_ = e.getValue();
            m_.addEntry(e.getKey(),getExecutableNodes(value_.getRoot(),_forwards));
        }
        return m_;
    }
    private static DefExecTextPart toExecPartExt(CustList<OperationNode> _roots, StringList _texts, Forwards _forwards) {
        CustList<CustList<RendDynOperationNode>> parts_ = toExecPartExt(_roots, _forwards);
        DefExecTextPart part_ = new DefExecTextPart();
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
        if (_root == null){
            return new CustList<RendDynOperationNode>();
        }
        return getExecutableNodesStd(_root, _forwards);
    }

    private static CustList<RendDynOperationNode> getExecutableNodesStd(OperationNode _root, Forwards _forwards) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
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
            while (current_ != null) {
                trySetup(exp_);
                out_.add(exp_);
                op_ = current_.getNextSibling();
                RendMethodOperation par_ = exp_.getParent();
                if (op_ != null) {
                    RendDynOperationNode loc_ = createExecOperationNode(op_, _forwards);
                    setImplicits(loc_, op_, _forwards);
                    par_.appendChild(loc_);
                    setSiblingSet(exp_, op_, loc_);
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                } else if (op_ == _root) {
                    trySetup(par_);
                    out_.add(par_);
                    current_ = null;
                } else {
                    current_ = op_;
                    exp_ = par_;
                }
            }
        }
        return out_;
    }

    private static void setSiblingSet(RendDynOperationNode _exp, OperationNode _op, RendDynOperationNode _loc) {
        if (_op.getParent() instanceof AbstractDotOperation && _loc instanceof RendPossibleIntermediateDotted) {
            _exp.setSiblingSet((RendPossibleIntermediateDotted) _loc);
        }
    }

    private static void trySetup(RendDynOperationNode _exp) {
        if (_exp instanceof RendAbstractAffectOperation) {
            ((RendAbstractAffectOperation) _exp).setup();
        }
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
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isClonedMethod()) {
                return new RendCloneOperation(new ExecOperationContent(f_.getContent()), f_.isIntermediateDottedOperation(), f_.getCallFctContent().getMethodName());
            }
        }
        return procOperand6(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperand6(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof InvokingOperation && _anaNode instanceof AbstractCallFctOperation) {
            InvokingOperation i_ = (InvokingOperation) _anaNode;
            AbstractCallFctOperation a_ = (AbstractCallFctOperation) _anaNode;
            if (a_.getStandardMethod() != null) {
                return new RendStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(a_.getCallFctContent(), a_.isStaticMethod()), new ExecArrContent(a_.getArrContent()));
            }
            ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(a_.getCallFctContent().getMemberId(), _forwards);
            if (rootBlock_ instanceof ExecAnnotationBlock) {
                return new RendAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(a_.getCallFctContent()));
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(a_.getCallFctContent().getMemberId(), _forwards);
            if (a_.isTrueFalse()) {
                return new RendExplicitOperation(pair_,
                        new ExecOperationContent(i_.getContent()), new ExecExplicitContent(a_.getCallFctContent(), _forwards));
            }
            if (a_.isStaticMethod()) {
                if (pair_ == null) {
                    return new RendEnumMethOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctCommonContent(a_.getCallFctContent()), new ExecArrContent(a_.getArrContent()),rootBlock_);
                }
                return new RendStaticFctOperation(pair_,
                        new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), ExecStaticFctContent.initByNotNull(a_.getCallFctContent(), _forwards), new ExecArrContent(a_.getArrContent()));
            }
        }
        return procOperands5(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperands5(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new RendCallDynMethodOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), c_.getFctName(), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof ArgumentListInstancing) {
            ArgumentListInstancing i_ = (ArgumentListInstancing) _anaNode;
            return new RendArgumentListInstancing(new ExecOperationContent(i_.getContent()));
        }
        if (_anaNode instanceof WithArrayElementInstancing) {
            WithArrayElementInstancing e_ = (WithArrayElementInstancing) _anaNode;
            return new RendArrayElementOperation(new ExecOperationContent(_anaNode.getContent()), e_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(e_.getArrayInstancingContent()));
        }
        if (_anaNode instanceof DimensionArrayInstancing) {
            DimensionArrayInstancing d_ = (DimensionArrayInstancing) _anaNode;
            return new RendDimensionArrayInstancing(new ExecOperationContent(d_.getContent()), d_.isIntermediateDottedOperation(), new ExecArrayInstancingContent(d_.getArrayInstancingContent()), d_.getCountArrayDims());
        }
        return procOperands4(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperands4(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchPossibleTypeCtor(s_.getMemberId(), _forwards);
            if (typeCtor_ == null) {
                return new RendDirectStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingDirContent(s_.getInstancingCommonContent()));
            }
            return new RendStandardInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_, _forwards), new ExecInstancingStdContent(s_.getInstancingStdContent(), FetchMemberUtil.namedFieldsContent(s_.getInstancingStdContent().getNamedFields(),_forwards), FetchMemberUtil.fwdFormatTypes(s_.getInstancingStdContent().getSups(), _forwards)));
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards);
            return new RendAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_, _forwards));
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor s_ = (InterfaceFctConstructor) _anaNode;
            return new RendInterfaceFctConstructor(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(s_.getInvokingConstructorContent(), _forwards), s_.getClassName(), FetchMemberUtil.fetchTypeCtor(s_.getMemberId(), _forwards));
        }
        if (_anaNode instanceof ArrOperation) {
            return arrOp((ArrOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof SwitchOperation) {
            SwitchOperation s_ = (SwitchOperation) _anaNode;
            SwitchMethodBlock switchMethod_ = s_.getSwitchMethod();
            ExecAbstractSwitchMethod r_ = _forwards.getSwitchMethod(switchMethod_);
            return new RendSwitchOperation(new ExecOperationContent(s_.getContent()), new ExecArrContent(s_.getArrContent()), r_);
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new RendIdOperation(new ExecOperationContent(d_.getContent()));
            }
            return new RendMultIdOperation(new ExecOperationContent(d_.getContent()));
        }
        return procOperands3(_anaNode, _forwards);
    }

    private static RendInvokingOperation arrOp(ArrOperation _anaNode, Forwards _forwards) {
        ExecTypeFunction get_ = FetchMemberUtil.fetchOvTypeFunction(_anaNode.getMemberIdGet(), _forwards);
        ExecTypeFunction set_ = FetchMemberUtil.fetchOvTypeFunction(_anaNode.getMemberIdSet(), _forwards);
        if (get_ == null && set_ == null) {
            return new RendArrOperation(_anaNode.isIntermediateDottedOperation(), new ExecOperationContent(_anaNode.getContent()), new ExecArrContent(_anaNode.getArrContent()));
        }
        if (get_ == null) {
            return new RendCustArrWriteOperation(_anaNode.isIntermediateDottedOperation(), set_, new ExecOperationContent(_anaNode.getContent()), new ExecInstFctContent(_anaNode.getCallFctContentSet(), _anaNode.getAncSet(), _anaNode.isStaticChoiceMethod(), _forwards));
        }
        if (set_ == null) {
            return new RendCustArrReadOperation(_anaNode.isIntermediateDottedOperation(), get_, new ExecOperationContent(_anaNode.getContent()), new ExecInstFctContent(_anaNode.getCallFctContent(), _anaNode.getAnc(), _anaNode.isStaticChoiceMethod(), _forwards));
        }
        return new RendCustArrOperation(_anaNode.isIntermediateDottedOperation(), get_, set_, new ExecOperationContent(_anaNode.getContent()), new ExecInstFctContent(_anaNode.getCallFctContent(), _anaNode.getAnc(), _anaNode.isStaticChoiceMethod(), _forwards), new ExecInstFctContent(_anaNode.getCallFctContentSet(), _anaNode.getAncSet(), _anaNode.isStaticChoiceMethod(), _forwards));
    }

    private static RendDynOperationNode procOperands3(OperationNode _anaNode, Forwards _forwards) {
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
            return new RendRefTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal(),new ExecArrContent(false));
        }
        if (_anaNode instanceof AbstractRefTernaryOperation) {
            AbstractRefTernaryOperation t_ = (AbstractRefTernaryOperation) _anaNode;
            return new RendRefTernaryOperation(new ExecOperationContent(t_.getContent()), t_.getOffsetLocal(),new ExecArrContent(t_.getArrContent()));
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            ExecTypeFunction ex_ = FetchMemberUtil.fetchOvTypeFunction(c_.getCallFctContent().getMemberId(), _forwards);
            return new RendChoiceFctOperation(ex_, new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecInstFctContent(c_.getCallFctContent(), c_.getAnc(), true, _forwards), new ExecArrContent(c_.getArrContent()));
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            ExecTypeFunction ex_ = FetchMemberUtil.fetchOvTypeFunction(s_.getCallFctContent().getMemberId(), _forwards);
            return new RendChoiceFctOperation(ex_, new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstFctContent(s_.getCallFctContent(), s_.getAnc(), true, _forwards), new ExecArrContent(s_.getArrContent()));
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(f_.getCallFctContent().getMemberId(), _forwards);
            return new RendFctOperation(p_, new ExecOperationContent(f_.getContent()), new ExecInstFctContent(f_.getCallFctContent(), f_.getAnc(), f_.isStaticChoiceMethod(), _forwards), f_.isIntermediateDottedOperation(), new ExecArrContent(f_.getArrContent()));
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
        return procOperands2(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperands2(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof ConstantOperation|| _anaNode instanceof StaticAccessOperation|| _anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new RendConstLeafOperation(false,new ExecOperationContent(f_.getContent()));
        }
        if (InvokingOperation.getDeltaCount(_anaNode) != 0) {
            return new RendConstLeafOperation(true,new ExecOperationContent(_anaNode.getContent()));
        }
        if (_anaNode instanceof AnonymousLambdaOperation) {
            AnonymousLambdaOperation s_ = (AnonymousLambdaOperation) _anaNode;
            ExecTypeFunction pair_ = ForwardInfos.buildAnonFctPair(_forwards, s_);
            return new RendAnonymousLambdaOperation(new ExecOperationContent(s_.getContent()), new ExecLambdaCommonContent(s_.getLambdaCommonContent(), _forwards), new ExecLambdaMethodContent(s_.getMethod(), pair_));
        }
        if (_anaNode instanceof LambdaOperation) {
            return lambda((LambdaOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof StaticInfoOperation) {
            StaticInfoOperation f_ = (StaticInfoOperation) _anaNode;
            return new RendStaticInfoOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new RendDefaultValueOperation(new ExecOperationContent(f_.getContent()), f_.getClassName());
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendDefaultOperation(new ExecOperationContent(f_.getContent()), f_.getOffset(), names_);
        }
        if (_anaNode instanceof ThisOperation) {
            ThisOperation f_ = (ThisOperation) _anaNode;
            return new RendThisOperation(new ExecOperationContent(f_.getContent()), f_.getThisContent().getOff());
        }
        if (_anaNode instanceof ParentInstanceOperation) {
            ParentInstanceOperation f_ = (ParentInstanceOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendParentInstanceOperation(new ExecOperationContent(f_.getContent()), new ExecParentInstanceContent(f_.getParentInstanceContent()), names_);
        }
        return procOperands(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperands(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            return procUseField((SettableAbstractFieldOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new RendArrayFieldOperation(new ExecOperationContent(s_.getContent()), new ExecFieldOperationContent(s_.getFieldContent()));
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new RendStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()), m_.getType() == ConstType.REF_LOC_VAR);
        }
        if (_anaNode instanceof VariableOperationUse) {
            VariableOperationUse m_ = (VariableOperationUse) _anaNode;
            return new RendStdRefVariableOperation(new ExecOperationContent(m_.getContent()), new ExecVariableContent(m_.getVariableContent()));
        }
        if (_anaNode instanceof FinalVariableOperation) {
            return finalVariable((FinalVariableOperation) _anaNode);
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new RendDotOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendSafeDotOperation(new ExecOperationContent(m_.getContent()), names_);
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            if (m_.isAffect()) {
                StringList names_ = _anaNode.getResultClass().getNames();
                AnaCallFctContent callFctContent_ = m_.getCallFctContent();
                ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(callFctContent_.getMemberId(), _forwards);
                AnaOperatorContent cont_ = new AnaOperatorContent();
                cont_.setOper(m_.getMethodFound()+'=');
                cont_.setOpOffset(m_.getOffsetOper());
                return new RendCompoundAffectationExplicitCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(cont_), new ExecStaticFctContent(callFctContent_, _forwards), pair_, FetchMemberUtil.fetchImplicits(m_.getConv(), _forwards), names_, m_.isPost());
            }
            return new RendExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent(), _forwards), FetchMemberUtil.fetchFunctionOpPair(m_.getCallFctContent().getMemberId(), _forwards), m_.getOffsetOper(), new ExecArrContent(m_.getArrContent()));
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            return semi((SemiAffectationOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = n_.getFct();
            AnaTypeFct pair_ = fct_.getFunction();
            if (pair_ != null) {
                return new RendCustNumericOperation(
                        FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards),
                        new ExecOperationContent(_anaNode.getContent()), n_.getOpOffset(), new ExecStaticEltContent(fct_, _forwards));
            }
        }
        return procGeneOperators(_anaNode, _forwards);
    }

    private static RendSettableFieldOperation procUseField(SettableAbstractFieldOperation _anaNode, Forwards _forwards) {
        AnaSettableOperationContent settableFieldContent_ = _anaNode.getSettableFieldContent();
        if (settableFieldContent_.isStaticField()) {
            return new RendSettableFieldStatOperation(new ExecOperationContent(_anaNode.getContent()), new ExecFieldOperationContent(_anaNode.getFieldContent()), new ExecSettableOperationContent(settableFieldContent_), FetchMemberUtil.fetchType(_anaNode.getFieldType(), _forwards));
        }
        return new RendSettableFieldInstOperation(new ExecOperationContent(_anaNode.getContent()), new ExecFieldOperationContent(_anaNode.getFieldContent()), new ExecSettableOperationContent(settableFieldContent_), FetchMemberUtil.fetchType(_anaNode.getFieldType(), _forwards));
    }

    private static RendLeafOperation finalVariable(FinalVariableOperation _anaNode) {
        return new RendFinalVariableOperation(new ExecOperationContent(_anaNode.getContent()), new ExecVariableContent(_anaNode.getVariableContent()));
    }

    private static RendDynOperationNode procGeneOperators(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new RendUnaryBooleanOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof UnaryBinOperation) {
            UnaryBinOperation m_ = (UnaryBinOperation) _anaNode;
            return new RendUnaryBinOperation(new ExecOperationContent(m_.getContent()));
        }
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new RendUnaryOperation(new ExecOperationContent(m_.getContent()), m_.getOper());
        }
        if (_anaNode instanceof RandCodeOperation) {
            RandCodeOperation m_ = (RandCodeOperation) _anaNode;
            return new RendRandCodeOperation(new ExecOperationContent(m_.getContent()), m_.getOpOffset());
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new RendCastOperation(new ExecOperationContent(m_.getContent()), new ExecTypeCheckContent(m_.getTypeCheckContent()));
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return cast(_forwards, m_.getExplicitContent(), m_.getContent());
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return cast(_forwards, m_.getExplicitContent(), m_.getContent());
        }
        return procOperators(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperators(OperationNode _anaNode, Forwards _forwards) {
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
        if (_anaNode instanceof RangeOperation) {
            RangeOperation c_ = (RangeOperation) _anaNode;
            return new RendRangeOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.isImplicitMiddle());
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
        return procOper(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOper(OperationNode _anaNode, Forwards _forwards) {
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
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendNullSafeOperation(new ExecOperationContent(n_.getContent()),n_.getOpOffset(), names_);
        }
        if (_anaNode instanceof AndOperation) {
            return quickOperation((QuickOperation) _anaNode, _forwards, false);
        }
        if (_anaNode instanceof OrOperation) {
            return quickOperation((QuickOperation) _anaNode, _forwards, true);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            return compound((CompoundAffectationOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendAffectationOperation(new ExecOperationContent(a_.getContent()), names_);
        }
        return new RendDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }

    private static RendSemiAffectationOperation semi(SemiAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getFct(), _forwards);
        if (pair_.getFct() == null) {
            return new RendSemiAffectationNatOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConvTo(), _forwards), _anaNode.isPost(), names_);
        }
        return new RendSemiAffectationCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecStaticPostEltContent(_anaNode.getFct(), _anaNode.isPost(), _forwards), new ExecOperatorContent(_anaNode.getOperatorContent()), pair_, FetchMemberUtil.fetchImplicits(_anaNode.getConvTo(), _forwards), names_);
    }

    private static RendCompoundAffectationOperation compound(CompoundAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        if (_anaNode.isConcat()) {
            return new RendCompoundAffectationStringOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), names_);
        }
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        if (pair_.getFct() != null) {
            return new RendCompoundAffectationCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), new ExecStaticEltContent(fct_, _forwards), pair_, FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_, false);
        }
        String oper_ = _anaNode.getOperatorContent().getOper();
        String op_ = oper_.substring(0, oper_.length() - 1);
        if (StringUtil.quickEq(op_, "??") || StringUtil.quickEq(op_, "???")) {
            return new RendCompoundAffectationNatSafeOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_);
        }
        return new RendCompoundAffectationNatOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), names_);
    }

    private static RendDynOperationNode cast(Forwards _forwards, AnaExplicitContent _explicitContent, AnaOperationContent _content) {
        ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(_explicitContent.getMemberId(), _forwards);
        if (ExecExplicitOperation.direct(pair_, _explicitContent.getClassName())) {
            return new RendImplicitOperation(
                    new ExecOperationContent(_content), new ExecExplicitCommonContent(_explicitContent));
        }
        return new RendExplicitOperation(
                pair_, new ExecOperationContent(_content), new ExecExplicitContent(_explicitContent, _forwards));
    }

    private static RendAbstractLambdaOperation lambda(LambdaOperation _anaNode, Forwards _forwards) {
        ExecLambdaCommonContent lamCont_ = new ExecLambdaCommonContent(_anaNode.getLambdaCommonContent(), _forwards);
        if (_anaNode.getStandardMethod() != null) {
            return new RendStdMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, _anaNode.getMethod(), _anaNode.getStandardMethod());
        }
        if (_anaNode.getStandardType() != null) {
            return new RendStdConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, _anaNode.getRealId(), _anaNode.getStandardType(),_anaNode.getStandardConstructor());
        }
        int recordType_ = _anaNode.getRecordType();
        ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(recordType_, _forwards);
        if (rootBlock_ != null) {
            return new RendRecordConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, rootBlock_, FetchMemberUtil.namedFieldsContent(_anaNode.getNamedFields(), _forwards), FetchMemberUtil.fwdFormatTypes(_anaNode.getSups(), _forwards));
        }
        if (_anaNode.getMethod() == null && _anaNode.getRealId() == null) {
            return new RendFieldLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, new ExecLambdaFieldContent(_anaNode.getFieldId(), _anaNode.getLambdaFieldContent(), _anaNode.getLambdaMemberNumberContentId(), _forwards));
        }
        if (_anaNode.getMethod() == null) {
            ExecLambdaConstructorContent lambdaConstructorContent_ = new ExecLambdaConstructorContent(_anaNode.getRealId(), _anaNode.getLambdaMemberNumberContentId(), _forwards);
            ExecTypeFunction pair_ = lambdaConstructorContent_.getPair();
            if (pair_ != null) {
                return new RendTypeConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaConstructorContent_);
            }
            return new RendConstructorLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_);
        }
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getLambdaMemberNumberContentId(), _forwards);
        ExecRootBlock declaring_ = pair_.getType();
        ExecNamedFunctionBlock named_ = pair_.getFct();
        ExecLambdaMethodContent lambdaMethodContent_ = new ExecLambdaMethodContent(_anaNode.getMethod().getConstraints(), _anaNode.getLambdaMethodContent(), pair_);
        if (declaring_ != null || named_ != null) {
            return new RendCustMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
        }
        if (lambdaMethodContent_.isDirectCast() || lambdaMethodContent_.isClonedMethod()) {
            return new RendSimpleMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
        }
        return new RendMethodLambdaOperation(new ExecOperationContent(_anaNode.getContent()), lamCont_, lambdaMethodContent_);
    }

    private static RendDynOperationNode quickOperation(QuickOperation _anaNode, Forwards _forwards, boolean _value) {
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        if (pair_.getFct() != null) {
            return new RendQuickCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecStaticEltContent(fct_, _forwards), pair_, FetchMemberUtil.fetchImplicits(_anaNode.getConv(), _forwards), _value);
        }
        return new RendQuickNatOperation(new ExecOperationContent(_anaNode.getContent()), _anaNode.getOpOffset(), _value);
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

    public static RendDocumentBlock buildExec(AnalyzingDoc _analyzingDoc, StringMap<AnaRendDocumentBlock> _d, Forwards _forwards, Configuration _conf, StringMap<RendDocumentBlock> _renders) {
        RendDocumentBlock rendDocumentBlock_ = buildExec(_d, _forwards, _conf, _analyzingDoc, _renders);
        initBeansInstances(_analyzingDoc, _forwards);
        initValidatorsInstance(_analyzingDoc, _forwards);
        return rendDocumentBlock_;
    }

    private static RendDocumentBlock buildExec(StringMap<AnaRendDocumentBlock> _d, Forwards _forwards, Configuration _conf, AnalyzingDoc _anaDoc, StringMap<RendDocumentBlock> _renders) {
        for (EntryCust<String,AnaRendDocumentBlock> v: _d.entryList()) {
            AnaRendDocumentBlock value_ = v.getValue();
            RendDocumentBlock rendDoc_ = build(value_, _forwards, _anaDoc);
            _renders.put(v.getKey(), rendDoc_);
        }
        String currentUrl2_ = _conf.getFirstUrl();
        return _renders.getVal(currentUrl2_);
    }
}
