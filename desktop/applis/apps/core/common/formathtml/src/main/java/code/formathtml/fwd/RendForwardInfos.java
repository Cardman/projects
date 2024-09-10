package code.formathtml.fwd;

import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.symbol.CommonOperNullSafe;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.exec.symbols.ExecOperDir;
import code.expressionlanguage.exec.symbols.ExecOperNull;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.InternGlobalOperation;
import code.formathtml.analyze.blocks.*;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.RendSelectOperators;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendForwardInfos {
    private RendForwardInfos() {
    }
    private static RendDocumentBlock build(Configuration _cont, AnaRendDocumentBlock _ana, ResultContext _forwards, AnalyzingDoc _anaDoc) {
        RendDocumentBlock rendDoc_ = new RendDocumentBlock(_ana.getFileName(),_ana.getEsc(),_ana.getFile().getMetricsCore(), _ana.getElt(), _ana.getBeanName(), fwdType(_ana, _forwards.getForwards()));
        RendAnaExec pair_ = new RendAnaExec(_ana, rendDoc_);
        while (pair_.getRead() != null) {
            RendBlock loc_ = newRendBlock(_cont,pair_.getRead(), _forwards.getForwards());
            pair_.setWrite(complete(_anaDoc, rendDoc_, pair_.getWrite(), loc_));
            nextPair(pair_);
        }
        ForwardInfos.feedFct(_forwards.getPageEl(),_ana,rendDoc_,_forwards.getForwards());
        return rendDoc_;
    }

    private static ExecFormattedRootBlock fwdType(AnaRendDocumentBlock _ana, Forwards _forwards) {
        AnaFormattedRootBlock declClass_ = _ana.getDeclClass();
        if (declClass_.getRootBlock() == null) {
            return ExecFormattedRootBlock.defValue();
        }
        return ExecStaticEltContent.build(declClass_, _forwards);
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

    private static RendBlock newRendBlock(Configuration _cont, AnaRendBlock _current, Forwards _forwards) {
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
        return block2(_cont,_current, _forwards);
    }

    private static RendBlock block2(Configuration _cont, AnaRendBlock _current, Forwards _forwards) {
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
        return block(_cont,_current, _forwards);
    }

    private static RendBlock block(Configuration _cont, AnaRendBlock _current, Forwards _forwards) {
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
            return buildCatchEval(f_,_forwards);
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
        return element(_cont,_current, _forwards);
    }

    private static RendAbstractCatchEval buildCatchEval(AnaRendCatchEval _f, Forwards _forwards) {
        OperationNode r_ = _f.getFilterContent().getResCondition().getRoot();
        if (!_f.getFilterContent().getImportedClassName().isEmpty()) {
            return new RendAbstractCatchEval(getExecutableNodes(r_,_forwards), _f.getFilterContent().getConditionOffset(), new ExecFilterContent(_f.getOffset(), _f.getFilterContent().getImportedClassName(), _f.getFilterContent().getVariableName(),new CustList<Struct>(),new CustList<ClassField>()), _f.isThrowIfGuardError(), _f.isCatchAll());
        }
        return new RendAbstractCatchEval(getExecutableNodes(r_,_forwards), _f.getFilterContent().getConditionOffset(),new ExecFilterContent(_f.getOffset(),"","",_f.getFilterContent().getStdValues(), _f.getFilterContent().getEnumValues()), _f.isThrowIfGuardError(), _f.isCatchAll());
    }

    private static RendBlock element(Configuration _cont, AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendSubmit){
            AnaRendSubmit f_ = (AnaRendSubmit) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getOpExp(), _forwards);
            return new RendSubmit(f_.getRead(),part_,partText_,partSub_,f_.getPreformatted());
        }
        if (_current instanceof AnaRendAnchor){
            return acnhor(_cont, _forwards, (AnaRendAnchor) _current);
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
            return form(_cont, _forwards, (AnaRendForm) _current);
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
            CustList<RendMessageOperationNode> partSub_ = toExecPartExtMessage(f_.getRes(), _forwards);
            StringMap<CustList<CustList<RendDynOperationNode>>> map_ = toExecPartMapExt(f_.getCallsRoots(), _forwards);
            return new RendMessage(partSub_,
                    f_.getPreformatted(), map_,
                    f_.getLocDoc()
            );
        }
        return input(_cont,_current, _forwards);
    }

    private static RendElement form(Configuration _cont, Forwards _forwards, AnaRendForm _f) {
        StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(_f.getAttributes(), _forwards);
        StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(_f.getAttributesText(), _forwards);
        CustList<RendDynOperationNode> op_ = geneLink(_f.getRes().getResultAnc(), _forwards, _f.getResults().size());
        RendGeneLinkTypes g_ = new RendGeneLinkTypes(op_, _f.getRes().getArgTypes());
        if (!_f.getRead().hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
            return new RendCstForm(_f.getRead(), part_, partText_, g_);
        }
        StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(_f.getResults(), _forwards);
        return new RendForm(_f.getRead(), part_, partText_, g_, partSub_);
    }

    private static RendElement acnhor(Configuration _cont, Forwards _forwards, AnaRendAnchor _a) {
        StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(_a.getAttributes(), _forwards);
        StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(_a.getAttributesText(), _forwards);
        CustList<RendDynOperationNode> op_ = geneLink(_a.getResultAnc(), _forwards, _a.getResults().size());
        RendGeneLinkTypes g_ = new RendGeneLinkTypes(op_, _a.getRes().getArgTypes());
        if (!_a.getRead().hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
            return new RendCstAnchor(_a.getRead(), part_, partText_, g_);
        }
        StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(_a.getResults(), _forwards);
        return new RendAnchor(_a.getRead(), part_, partText_, g_, partSub_);
    }

    private static RendBlock input(Configuration _cont, AnaRendBlock _current, Forwards _forwards) {
        if (_current instanceof AnaRendSelect){
            AnaRendSelect f_ = (AnaRendSelect) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = geneLink(f_.getRootConverter(), _forwards,1);
            CustList<RendDynOperationNode> opConverterField_ = geneLink(f_.getRootConverterField(), _forwards,1);
            CustList<RendDynOperationNode> opConverterFieldValue_ = geneLink(f_.getRootConverterFieldValue(), _forwards,1);
            CustList<RendDynOperationNode> opDefault_ = getExecutableNodes(f_.getRootDefault(), _forwards);
            CustList<RendDynOperationNode> opMap_ = getExecutableNodes(f_.getRootMap(), _forwards);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendSelect(
                    partText_,part_, f_.getElt(),f_.isMultiple(),
                    RendSelect.initElts(opRead_, opConverter_, f_.getIdClass(), f_.getIdName(), f_.getClassName(), f_.isArrayConverter()), new RendSelectOperators(opValue_, opMap_, opDefault_, opConverterField_, opConverterFieldValue_));
        }
        if (_current instanceof AnaRendRadio){
            AnaRendRadio f_ = (AnaRendRadio) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = geneLink(f_.getRootConverter(), _forwards, 1);
            CustList<RendDynOperationNode> opConverterField_ = geneLink(f_.getRootConverterField(), _forwards, 1);
            CustList<RendDynOperationNode> opConverterFieldValue_ = geneLink(f_.getRootConverterFieldValue(), _forwards,1);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> nbRad_ = getExecutableNodes(f_.getRootRadio(), _forwards);
            return new RendRadio(f_.getRead(),part_,partText_, opValue_, opConverterField_
                    , opConverterFieldValue_,
                    RendInput.initUpdates(f_.getIdClass(), f_.getIdName(), opRead_, opConverter_, f_.getClassName(), nbRad_));
        }
        if (_current instanceof AnaRendStdInput){
            AnaRendStdInput f_ = (AnaRendStdInput) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = geneLink(f_.getRootConverter(), _forwards, 1);
            CustList<RendDynOperationNode> opConverterField_ = geneLink(f_.getRootConverterField(), _forwards, 1);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdInput(f_.getRead(),part_,partText_, opValue_, opConverterField_
                    , RendInput.initUpdates(f_.getIdClass(), f_.getIdName(), opRead_, opConverter_, f_.getClassName(), new CustList<RendDynOperationNode>()));
        }
        if (_current instanceof AnaRendTextArea){
            AnaRendTextArea f_ = (AnaRendTextArea) _current;
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead(), _forwards);
            CustList<RendDynOperationNode> opConverter_ = geneLink(f_.getRootConverter(), _forwards, 1);
            CustList<RendDynOperationNode> opConverterField_ = geneLink(f_.getRootConverterField(), _forwards,1);
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue(), _forwards);
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendTextArea(opValue_, opConverterField_
                    ,partText_,part_, f_.getElt(), RendTextArea.initElts(opRead_, opConverter_, f_.getIdClass(), f_.getIdName(), f_.getClassName()));
        }
        if (_current instanceof AnaRendSpan){
            AnaRendSpan f_ = (AnaRendSpan) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRootFor(), _forwards);
            return new RendSpan(f_.getRead(),part_,partText_, f_.getFormatted(), new RendOperationNodeListOff(op_,f_.getOffFor()));
        }
        if (_current instanceof AnaRendTitledAnchor){
            return titledAnchor(_cont, _forwards, (AnaRendTitledAnchor) _current);
        }
        if (_current instanceof AnaRendEmptyInstruction){
            return new RendEmptyText("",false);
        }
        if (_current instanceof AnaRendStdElement) {
            AnaRendStdElement f_ = (AnaRendStdElement) _current;
            StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(f_.getAttributes(), _forwards);
            StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(f_.getAttributesText(), _forwards);
            return new RendStdElement(f_.getRead(), part_, partText_);
        }
        return null;
    }

    private static RendElement titledAnchor(Configuration _cont, Forwards _forwards, AnaRendTitledAnchor _f) {
        StringMap<CustList<RendDynOperationNode>> part_ = toExecPartExt(_f.getAttributes(), _forwards);
        StringMap<CustList<RendDynOperationNode>> partText_ = toExecPartExt(_f.getAttributesText(), _forwards);
        StringMap<CustList<RendDynOperationNode>> title_ = toExecPartExt(_f.getOpExpTitle(), _forwards);
        CustList<RendDynOperationNode> opAnc_ = geneLink(_f.getRes().getResultAnc(), _forwards, _f.getResults().size());
        RendGeneLinkTypes g_ = new RendGeneLinkTypes(opAnc_, _f.getRes().getArgTypes());
        if (!_f.getRead().hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
            return new RendCstTitledAnchor(_f.getRead(), part_, partText_, g_, title_, _f.getPreformatted());
        }
        StringMap<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(_f.getResults(), _forwards);
        return new RendTitledAnchor(_f.getRead(), part_, partText_, g_, title_, _f.getPreformatted(), partSub_);
    }

    private static RendParentBlock buildDefaultCondition(AnaRendDefaultCondition _current) {
        String instanceTest_ = _current.getImportedClassName();
        if (!instanceTest_.isEmpty()) {
            return new RendDefaultCondition(_current.getVariableName());
        }
        return new RendDefaultCondition();
    }

    private static RendBlock buildCaseCondition(AnaRendCaseCondition _current, Forwards _fwd) {
        OperationNode r_ = _current.getFilterContent().getResCondition().getRoot();
        RendBlock exec_;
        if (!_current.getFilterContent().getImportedClassName().isEmpty()) {
            exec_ = new RendAbstractCaseCondition(_current.getOffset(), getExecutableNodes(r_,_fwd), _current.getFilterContent().getConditionOffset(), _current.getFilterContent().getImportedClassName(), _current.getVariableName(),new CustList<Struct>(),new CustList<ClassField>());
        } else {
            exec_ = new RendAbstractCaseCondition(_current.getOffset(), getExecutableNodes(r_,_fwd), _current.getFilterContent().getConditionOffset(),"","",_current.getFilterContent().getStdValues(), _current.getFilterContent().getEnumValues());
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

    private static CustList<RendDynOperationNode> geneLink(ClassMethodIdReturn _id, Forwards _forwards, int _size) {
        CustList<RendDynOperationNode> lk_ = new CustList<RendDynOperationNode>();
        if (_id == null) {
            return lk_;
        }
        AnaCallFctContent ana_ = new AnaCallFctContent("");
        ana_.update(_id);
        ExecTypeFunction p_ = FetchMemberUtil.fetchOvTypeFunction(ana_.getMemberId(), _forwards);
        RendFctOperation rend_ = new RendFctOperation(p_,new ExecOperationContent(0,new ExecClassArgumentMatching(""), _size),new ExecInstFctContent(0,false,0,"",-1,ExecFormattedRootBlock.defValue()),false,new ExecArrContent(false));
        for (int i = 0; i < _size; i++) {
            AnaVariableContent cont_ = new AnaVariableContent(0);
            cont_.setVariableName(Long.toString(i));
            cont_.setDeep(-1);
            RendStdRefVariableOperation v_ = new RendStdRefVariableOperation(new ExecOperationContent(i,new ExecClassArgumentMatching(""),i),new ExecVariableContent(cont_));
            rend_.appendChild(v_);
            lk_.add(v_);
        }
        lk_.add(rend_);
        return lk_;
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
    private static CustList<RendMessageOperationNode> toExecPartExtMessage(CustList<AnaMessageOperationNode> _roots, Forwards _forwards) {
        CustList<RendMessageOperationNode> parts_;
        parts_ = new CustList<RendMessageOperationNode>();
        for (AnaMessageOperationNode r: _roots) {
            parts_.add(new RendMessageOperationNode(getExecutableNodes(r.getRoot(), _forwards),r.isQuotted(),r.isEscaped(),r.getArg()));
        }
        return parts_;
    }
    private static StringMap<CustList<CustList<RendDynOperationNode>>> toExecPartMapExt(StringMap<Integer> _roots, Forwards _forwards) {
        StringMap<CustList<CustList<RendDynOperationNode>>> m_ = new StringMap<CustList<CustList<RendDynOperationNode>>>();
        for (EntryCust<String, Integer> e:_roots.entryList()) {
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
    private static CustList<CustList<RendDynOperationNode>> toExecPartExt(int _roots, Forwards _forwards) {
        CustList<CustList<RendDynOperationNode>> parts_;
        parts_ = new CustList<CustList<RendDynOperationNode>>();
        for (int i = 0; i < _roots; i++) {
            parts_.add(getExecutableNodes(null, _forwards));
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
        return procOperand6(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperand6(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof AbsFctOperation) {
            AbsFctOperation i_ = (AbsFctOperation) _anaNode;
            if (i_.isClonedMethod()) {
                return new RendCloneOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), i_.getCallFctContent().getMethodName());
            }
            if (i_.getStandardMethod() != null) {
                return new RendStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStdFctContent(i_.getCallFctContent(), i_.isStaticMethod()), new ExecArrContent(i_.getArrContent()));
            }
            ExecRootBlock rootBlock_ = FetchMemberUtil.fetchType(i_.getCallFctContent().getMemberId(), _forwards);
            if (rootBlock_ instanceof ExecAnnotationBlock) {
                return new RendAnnotationMethodOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecCallFctAnnotContent(i_.getCallFctContent()));
            }
            ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(i_.getCallFctContent().getMemberId(), _forwards);
            if (pair_ == null) {
                return new RendEnumMethOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new ExecStaticFctCommonContent(i_.getCallFctContent()), new ExecArrContent(i_.getArrContent()),rootBlock_);
            }
            if (i_.isTrueFalse()) {
                return new RendExplicitOperation(pair_,
                        new ExecOperationContent(i_.getContent()), new ExecExplicitContent(i_.getCallFctContent(), _forwards));
            }
            if (i_.isStaticMethod()) {
                return new RendStaticFctOperation(pair_,
                        new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), ExecStaticFctContent.initByNotNull(i_.getCallFctContent(), _forwards), new ExecArrContent(i_.getArrContent()));
            }
            return new RendFctOperation(pair_, new ExecOperationContent(i_.getContent()), new ExecInstFctContent(i_.getCallFctContent(), i_.getAnc(), i_.isStaticChoiceMethod(), _forwards), i_.isIntermediateDottedOperation(), new ExecArrContent(i_.getArrContent()));
        }
        return procOperands5(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOperands5(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new RendCallDynMethodOperation(new ExecOperationContent(c_.getContent()), c_.isIntermediateDottedOperation(), new ExecArrContent(c_.getArrContent()), c_.getStdMethod());
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
            return instance((StandardInstancingOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation s_ = (AnonymousInstancingOperation) _anaNode;
            ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchPossibleTypeCtor(s_.getMemberId(), _forwards);
            return new RendAnonymousInstancingOperation(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInstancingCustContent(s_.getInstancingCommonContent(),typeCtor_, _forwards));
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor s_ = (InterfaceFctConstructor) _anaNode;
            return new RendInterfaceFctConstructor(new ExecOperationContent(s_.getContent()), s_.isIntermediateDottedOperation(), new ExecInvokingConstructorContent(s_.getInvokingConstructorContent(), _forwards), s_.getClassName(), FetchMemberUtil.fetchPossibleTypeCtor(s_.getMemberId(), _forwards));
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

    private static RendInvokingOperation instance(StandardInstancingOperation _anaNode, Forwards _forwards) {
        ExecTypeFunction typeCtor_ = FetchMemberUtil.fetchPossibleTypeCtor(_anaNode.getMemberId(), _forwards);
        if (typeCtor_ == null) {
            return new RendDirectStandardInstancingOperation(new ExecOperationContent(_anaNode.getContent()), _anaNode.isIntermediateDottedOperation(), new ExecInstancingDirContent(_anaNode.getInstancingCommonContent()));
        }
        return new RendStandardInstancingOperation(new ExecOperationContent(_anaNode.getContent()), _anaNode.isIntermediateDottedOperation(), new ExecInstancingCustContent(_anaNode.getInstancingCommonContent(), typeCtor_, _forwards), new ExecInstancingStdContent(_anaNode.getInstancingStdContent(), FetchMemberUtil.namedFieldsContent(_anaNode.getInstancingStdContent().getNamedFields(), _forwards), FetchMemberUtil.fwdFormatTypes(_anaNode.getInstancingStdContent().getSups(), _forwards)));
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
                RendCompoundAffectationExplicitCustOperation c_ = new RendCompoundAffectationExplicitCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(ForwardInfos.syntheticOperator(m_.getSyntheticOperator()), m_.getOffsetOper()), new ExecStaticFctContent(callFctContent_, _forwards), pair_, names_, m_.isPost());
                return updateConv(_forwards, c_, m_);
            }
            RendExplicitOperatorOperation c_ = new RendExplicitOperatorOperation(new ExecOperationContent(m_.getContent()), m_.isIntermediateDottedOperation(), new ExecStaticFctContent(m_.getCallFctContent(), _forwards), FetchMemberUtil.fetchFunctionOpPair(m_.getCallFctContent().getMemberId(), _forwards), new ExecOperatorContent(ForwardInfos.syntheticOperator(m_.getSyntheticOperator()), m_.getOffsetOper()), new ExecArrContent(m_.getArrContent()));
            return updateConv(_forwards, c_, m_);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            RendCompoundAffectationOperation c_ = semi((SemiAffectationOperation) _anaNode, _forwards);
            return updateConv(_forwards, c_, (SemiAffectationOperation) _anaNode);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            ClassMethodIdMemberIdTypeFct fct_ = n_.getFct();
            if (AnaTypeFct.fct(fct_.getFunction()) != null) {
                return new RendExplicitOperatorOperation(
                        new ExecOperationContent(_anaNode.getContent()),
                        false,
                        new ExecStaticFctContent(new ExecStaticFctCommonContent("","",-1), new ExecStaticEltContent(fct_, _forwards)),
                        FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards),
                        new ExecOperatorContent(n_.getOperatorContent()), new ExecArrContent(false));
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
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new RendQuickOperation(new ExecOperationContent(m_.getContent()),new ExecOperatorContent(m_.getOperatorContent()), new ExecOperDir(m_.getSymbol()));
        }
        if (_anaNode instanceof RandCodeOperation) {
            RandCodeOperation m_ = (RandCodeOperation) _anaNode;
            return new RendRandCodeOperation(new ExecOperationContent(m_.getContent()), m_.getOperatorContent().getOpOffset());
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
        if (_anaNode instanceof RangeOperation) {
            RangeOperation c_ = (RangeOperation) _anaNode;
            return new RendRangeOperation(new ExecOperationContent(c_.getContent()), c_.getOpOffset(), c_.isImplicitMiddle());
        }
        if (_anaNode instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _anaNode;
            return new RendQuickOperation(new ExecOperationContent(c_.getContent()), new ExecOperatorContent(c_.getOperatorContent()), new ExecOperDir(c_.getSymbol()));
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new RendInstanceOfOperation(new ExecOperationContent(c_.getContent()), new ExecTypeCheckContent(c_.getTypeCheckContent()));
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new RendQuickOperation(new ExecOperationContent(c_.getContent()),new ExecOperatorContent(c_.getOperatorContent()), new ExecOperDir(c_.getSymbol()));
        }
        return procOper(_anaNode, _forwards);
    }

    private static RendDynOperationNode procOper(OperationNode _anaNode, Forwards _forwards) {
        if (_anaNode instanceof NumericOperation) {
            NumericOperation c_ = (NumericOperation) _anaNode;
            return new RendQuickOperation(new ExecOperationContent(c_.getContent()),new ExecOperatorContent(c_.getOperatorContent()), ForwardInfos.oper(c_));
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation n_ = (NullSafeOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            AnaOperatorContent cont_ = new AnaOperatorContent();
            cont_.setOpOffset(n_.getOpOffset());
            cont_.setOper("??");
            return new RendQuickOperation(new ExecOperationContent(n_.getContent()),new ExecOperatorContent(cont_), new ExecOperNull(new CommonOperNullSafe(),names_));
//            return new RendNullSafeOperation(new ExecOperationContent(n_.getContent()),n_.getOpOffset(), names_);
        }
        if (_anaNode instanceof QuickOperation) {
            return quickOperation((QuickOperation) _anaNode, _forwards);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            RendCompoundAffectationOperation c_ = compound((CompoundAffectationOperation) _anaNode, _forwards);
            return updateConv(_forwards, c_, (CompoundAffectationOperation) _anaNode);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            StringList names_ = _anaNode.getResultClass().getNames();
            return new RendAffectationOperation(new ExecOperationContent(a_.getContent()), names_);
        }
        return new RendDeclaringOperation(new ExecOperationContent(_anaNode.getContent()));
    }

    private static RendCompoundAffectationOperation semi(SemiAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getFct(), _forwards);
        if (pair_.getFct() == null) {
            return new RendCompoundAffectationStringOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), names_, new ExecOperDir(_anaNode.getSymbol()), _anaNode.isPost());
        }
        return new RendCompoundAffectationExplicitCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), new ExecStaticFctContent(new ExecStaticFctCommonContent("", "", -1), new ExecStaticEltContent(_anaNode.getFct(), _forwards)), pair_, names_, _anaNode.isPost());
    }

    private static RendCompoundAffectationOperation compound(CompoundAffectationOperation _anaNode, Forwards _forwards) {
        StringList names_ = _anaNode.getResultClass().getNames();
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        if (pair_.getFct() != null) {
            return new RendCompoundAffectationExplicitCustOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), new ExecStaticFctContent(new ExecStaticFctCommonContent("","",-1),new ExecStaticEltContent(fct_, _forwards)), pair_, names_, false);
        }
        String oper_ = _anaNode.getOperatorContent().getOper();
        String op_ = oper_.substring(0, oper_.length() - 1);
        if (StringExpUtil.isNullSafe(op_)) {
            return new RendCompoundAffectationStringOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()),names_,new ExecOperNull(_anaNode.getSymbol(), names_), false);
        }
        return new RendCompoundAffectationStringOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(_anaNode.getOperatorContent()), names_,ForwardInfos.symbol(_anaNode), false);
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
        if (_anaNode.getVirtualCall() != null) {
            ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(_anaNode.getLambdaMemberNumberContentId(), _forwards);
            ExecLambdaMethodContent lambdaMethodContent_ = new ExecLambdaMethodContent(_anaNode.getMethod().getConstraints(), _anaNode.getLambdaMethodContent(), pair_);
            return new RendNativeOperatorLambdaOperation(new ExecOperationContent(_anaNode.getContent()),lamCont_,lambdaMethodContent_,_anaNode.getVirtualCall());
        }
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

    private static RendDynOperationNode quickOperation(QuickOperation _anaNode, Forwards _forwards) {
        ClassMethodIdMemberIdTypeFct fct_ = _anaNode.getFct();
        ExecTypeFunction pair_ = FetchMemberUtil.fetchFunctionOpPair(fct_, _forwards);
        AnaOperatorContent cont_ = new AnaOperatorContent();
        cont_.setOper(ForwardInfos.quickOperator(_anaNode));
        cont_.setOpOffset(_anaNode.getOpOffset());
        if (pair_.getFct() != null) {
            RendExplicitOperatorOperation c_ = new RendExplicitOperatorOperation(new ExecOperationContent(_anaNode.getContent()), false, new ExecStaticFctContent(new ExecStaticFctCommonContent("", "", -1), new ExecStaticEltContent(fct_, _forwards)), pair_, new ExecOperatorContent(cont_), new ExecArrContent(false));
            return updateConv(_forwards, c_, _anaNode);
        }
        RendQuickOperation c_ = new RendQuickOperation(new ExecOperationContent(_anaNode.getContent()), new ExecOperatorContent(cont_), new ExecOperDir(_anaNode.getSymbol()));
        return updateConv(_forwards, c_, _anaNode);
    }

    private static RendCompoundAffectationOperation updateConv(Forwards _forwards, RendCompoundAffectationOperation _c, CompoundAffectationOperation _m) {
        FetchMemberUtil.impls(_m.getConv(), _c.getConverter(), _forwards);
        return _c;
    }

    private static RendCompoundAffectationOperation updateConv(Forwards _forwards, RendCompoundAffectationOperation _c, SemiAffectationOperation _m) {
        FetchMemberUtil.impls(_m.getConvTo(), _c.getConverter(), _forwards);
        return _c;
    }

    private static RendCompoundAffectationExplicitCustOperation updateConv(Forwards _forwards, RendCompoundAffectationExplicitCustOperation _c, ExplicitOperatorOperation _m) {
        FetchMemberUtil.impls(_m.getConv(), _c.getConverter(), _forwards);
        return _c;
    }

    private static RendQuickOperation updateConv(Forwards _forwards, RendQuickOperation _c, QuickOperation _m) {
        FetchMemberUtil.impls(_m.getConv(), _c.getConv(), _forwards);
        return _c;
    }

    private static RendExplicitOperatorOperation updateConv(Forwards _forwards, RendExplicitOperatorOperation _c, QuickOperation _m) {
        FetchMemberUtil.impls(_m.getConv(), _c.getConverter(), _forwards);
        return _c;
    }

    private static RendExplicitOperatorOperation updateConv(Forwards _forwards, RendExplicitOperatorOperation _c, ExplicitOperatorOperation _m) {
        FetchMemberUtil.impls(_m.getConv(), _c.getConverter(), _forwards);
        return _c;
    }

    private static void setImplicits(RendDynOperationNode _ex, OperationNode _ana, Forwards _forwards){
        AnaClassArgumentMatching ana_ = _ana.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        FetchMemberUtil.setImplicits(ana_,implicits_,implicitsTest_, _forwards);
    }

    private static void initValidatorsInstance(AnalyzingDoc _anaDoc, Forwards _forwards) {
        for (EntryCust<OperationNode, ValidatorInfo> e: _anaDoc.getLateValidators().entryList()) {
            OperationNode root_ = e.getKey();
            StandardInstancingOperation s_ = (StandardInstancingOperation) root_;
            RendInvokingOperation rootExp_ = instance(s_, _forwards);
            e.getValue().setExps(new CustList<RendDynOperationNode>(rootExp_));
        }
    }

    private static void initReinitsInstance(AnalyzingDoc _anaDoc, Forwards _forwards) {
        for (EntryCust<OperationNode, ValidatorInfo> e: _anaDoc.getLateReinits().entryList()) {
            OperationNode root_ = e.getKey();
            StandardInstancingOperation s_ = (StandardInstancingOperation) root_;
            RendInvokingOperation rootExp_ = instance(s_, _forwards);
            e.getValue().setExps(new CustList<RendDynOperationNode>(rootExp_));
        }
    }

    private static void initBeansInstances(AnalyzingDoc _anaDoc, Forwards _forwards) {
        for (EntryCust<OperationNode, BeanInfo> e: _anaDoc.getBeansInfos().entryList()) {
            OperationNode root_ = e.getKey();
            StandardInstancingOperation s_ = (StandardInstancingOperation) root_;
            RendInvokingOperation rootExp_ = instance(s_, _forwards);
            e.getValue().setExps(new CustList<RendDynOperationNode>(rootExp_));
        }
    }

    public static RendDocumentBlock buildExec(AnalyzingDoc _analyzingDoc, StringMap<AnaRendDocumentBlock> _d, ResultContext _forwards, Configuration _conf, StringMap<RendDocumentBlock> _renders) {
        RendDocumentBlock rendDocumentBlock_ = buildExec(_d, _forwards, _conf, _analyzingDoc, _renders);
        initBeansInstances(_analyzingDoc, _forwards.getForwards());
        initValidatorsInstance(_analyzingDoc, _forwards.getForwards());
        initReinitsInstance(_analyzingDoc, _forwards.getForwards());
        return rendDocumentBlock_;
    }

    private static RendDocumentBlock buildExec(StringMap<AnaRendDocumentBlock> _d, ResultContext _forwards, Configuration _conf, AnalyzingDoc _anaDoc, StringMap<RendDocumentBlock> _renders) {
        for (EntryCust<String,AnaRendDocumentBlock> v: _d.entryList()) {
            AnaRendDocumentBlock value_ = v.getValue();
            RendDocumentBlock rendDoc_ = build(_conf,value_, _forwards, _anaDoc);
            _renders.put(v.getKey(), rendDoc_);
        }
        String currentUrl2_ = _conf.getFirstUrl();
        return _renders.getVal(currentUrl2_);
    }
}
