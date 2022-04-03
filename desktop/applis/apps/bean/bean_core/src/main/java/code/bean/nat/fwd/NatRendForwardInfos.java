package code.bean.nat.fwd;

import code.bean.nat.NatCaller;
import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.blocks.*;
import code.bean.nat.analyze.opers.*;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.*;
import code.bean.nat.fwd.opers.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.exec.opers.RendMethodOperation;
import code.formathtml.exec.opers.RendPossibleIntermediateDotted;
import code.formathtml.fwd.RendAnaExec;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.util.FieldUpdates;
import code.formathtml.util.InputInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendForwardInfos {
    private NatRendForwardInfos() {
    }
    private static RendDocumentBlock build(AnaRendDocumentBlock _ana, AnalyzingDoc _anaDoc) {
        RendDocumentBlock rendDoc_ = new RendDocumentBlock("",null,null,_ana.getElt(), _ana.getBeanName(), ExecFormattedRootBlock.defValue());
        RendAnaExec pair_ = new RendAnaExec(_ana, rendDoc_);
        while (pair_.getRead() != null) {
            RendBlock loc_ = newRendBlock(pair_.getRead());
            pair_.setWrite(complete(_anaDoc, rendDoc_, pair_.getWrite(), loc_));
            RendForwardInfos.nextPair(pair_);
        }
        return rendDoc_;
    }

    private static RendParentBlockInt complete(AnalyzingDoc _anaDoc, RendDocumentBlock _rendDoc, RendParentBlockInt _curPar, RendBlock _loc) {
        if (_loc != null) {
            if (_loc instanceof NatRendStdElement && StringUtil.quickEq(((NatRendStdElement) _loc).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                _rendDoc.getBodies().add(_loc);
            }
//            _loc.setEscapedChars(_en.getEscapedChars());
            _curPar.appendChild(_loc);
        }
        if (_loc instanceof RendParentBlock) {
            return (RendParentBlock) _loc;
        }
        return _curPar;
    }

    private static RendBlock newRendBlock(AnaRendBlock _current) {
        if (_current instanceof NatAnaRendText){
            NatAnaRendText t_ = (NatAnaRendText) _current;
            ExecTextPart part_ = toExecPartExt(t_.getRoots(),t_.getTexts());
            return new NatRendText(part_);
        }
        if (_current instanceof NatAnaRendForEachLoop){
            NatAnaRendForEachLoop f_ = (NatAnaRendForEachLoop) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendForEachIterable(f_.getVariableName(),
                    f_.getExpressionOffset(), f_.getRealLabel(), op_);
        }
        return block2(_current);
    }

    private static RendBlock block2(AnaRendBlock _current) {
        if (_current instanceof NatAnaRendForEachTable){
            NatAnaRendForEachTable f_ = (NatAnaRendForEachTable) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendForEachTable(f_.getVariableNameFirst(),
                    f_.getVariableNameSecond(),
                    f_.getRealLabel(), new RendOperationNodeListOff(op_, f_.getExpressionOffset()));
        }
        return block(_current);
    }

    private static RendBlock block(AnaRendBlock _current) {
        if (_current instanceof NatAnaRendIfCondition){
            NatAnaRendIfCondition f_ = (NatAnaRendIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendIfCondition(op_,f_.getConditionOffset(),f_.getRealLabel());
        }
        if (_current instanceof NatAnaRendElseIfCondition){
            NatAnaRendElseIfCondition f_ = (NatAnaRendElseIfCondition) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendElseIfCondition(op_,f_.getConditionOffset());
        }
        if (_current instanceof NatAnaRendElseCondition){
            return new NatRendElseCondition();
        }
        if (_current instanceof NatAnaRendImport){
            NatAnaRendImport f_ = (NatAnaRendImport) _current;
            ExecTextPart part_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendImport(part_, f_.getNatImpLgNames());
        }
        return element(_current);
    }

    private static RendBlock element(AnaRendBlock _current) {
        if (_current instanceof NatAnaRendSubmit){
            NatAnaRendSubmit f_ = (NatAnaRendSubmit) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendSubmit(f_.getRead(),part_,partText_, f_.getPreformatted());
        }
        if (_current instanceof NatAnaRendAnchor){
            NatAnaRendAnchor f_ = (NatAnaRendAnchor) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendAnchor(f_.getRead(),part_,partText_,op_, f_.getVarNames(),partSub_);
        }
        if (_current instanceof NatAnaRendImg){
            NatAnaRendImg f_ = (NatAnaRendImg) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendImg(f_.getRead(),part_,partText_, partSub_);
        }
        if (_current instanceof NatAnaRendLink){
            NatAnaRendLink f_ = (NatAnaRendLink) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendLink(f_.getRead(),part_,partText_,f_.getContent());
        }
        if (_current instanceof NatAnaRendEscImg){
            NatAnaRendEscImg f_ = (NatAnaRendEscImg) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendEscImg(f_.getRead(),part_,partText_);
        }
        if (_current instanceof NatAnaRendPackage){
            return new NatRendClass();
        }
        if (_current instanceof NatAnaRendForm){
            NatAnaRendForm f_ = (NatAnaRendForm) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            CustList<RendDynOperationNode> opForm_ = getExecutableNodes(f_.getRoot());
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendForm(f_.getRead(),part_,partText_,opForm_,f_.getVarNames(),partSub_);
        }
        if (_current instanceof NatAnaRendClass){
            return new NatRendClass();
        }
        if (_current instanceof NatAnaRendField){
            NatAnaRendField f_ = (NatAnaRendField) _current;
            CustList<RendDynOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendField(op_);
        }
        if (_current instanceof NatAnaRendMessage){
            NatAnaRendMessage f_ = (NatAnaRendMessage) _current;
            CustList<CustList<RendDynOperationNode>> partSub_ = toExecPartExt(f_.getRoots());
            StringMap<CustList<CustList<RendDynOperationNode>>> map_ = toExecPartMapExt(f_.getCallsRoots());
            return new NatRendMessage(partSub_,
                    f_.getPreformatted(), map_,
                    f_.getArgs(),
                    f_.getVarNames());
        }
        return input(_current);
    }

    private static RendBlock input(AnaRendBlock _current) {
        if (_current instanceof NatAnaRendSelect){
            NatAnaRendSelect f_ = (NatAnaRendSelect) _current;
            NatResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
            CustList<RendDynOperationNode> opMap_ = getExecutableNodes(f_.getRootMap());
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
            return new NatRendSelect(opRead_,opValue_,opsWrite_,opMap_,
                    f_.getElt(),
                    initIn(f_.getCallerGet(), f_.getIdClass(), f_.getIdName(), f_.getClassNameNat(), f_.getVarName(), f_.getVarNames()));
        }
        if (_current instanceof NatAnaRendInput){
            NatAnaRendInput f_ = (NatAnaRendInput) _current;
            if (f_.isRadio()) {
                NatResultInput resultInput_ = f_.getResultInput();
                CustList<RendDynOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
                CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
                CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
                StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
                StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
                return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                        initIn(f_.getCallerGet(), f_.getIdClass(), f_.getIdName(), f_.getClassName(), f_.getVarName(), f_.getVarNames()));
            }
            NatResultInput resultInput_ = f_.getResultInput();
            CustList<RendDynOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
            CustList<RendDynOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
            CustList<RendDynOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                    initIn(f_.getCallerGet(), f_.getIdClass(), f_.getIdName(), f_.getClassName(), f_.getVarName(), f_.getVarNames()));
        }
        if (_current instanceof NatAnaRendSpan){
            NatAnaRendSpan f_ = (NatAnaRendSpan) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendSpan(f_.getRead(),part_,partText_,partSub_,f_.getFormatted());
        }
        if (_current instanceof NatAnaRendTitledAnchor){
            NatAnaRendTitledAnchor f_ = (NatAnaRendTitledAnchor) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            ExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            CustList<RendDynOperationNode> opAnc_ = getExecutableNodes(f_.getRoot());
            return new NatRendTitledAnchor(f_.getRead(),part_,partText_,opAnc_,f_.getVarNames(), f_.getPreformatted(),partSub_);
        }
        if (_current instanceof NatAnaRendEmptyInstruction){
            return new NatRendEmptyInstruction();
        }
        if (_current instanceof NatAnaRendStdElement) {
            NatAnaRendStdElement f_ = (NatAnaRendStdElement) _current;
            StringMap<ExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<ExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendStdElement(f_.getRead(), part_, partText_);
        }
        return null;
    }

    static CustList<RendDynOperationNode> buildWritePart(NatResultInput _resultInput) {
        NatOperationNode settable_ = _resultInput.getSettable();
        CustList<RendDynOperationNode> l_ = new CustList<RendDynOperationNode>();
        if (settable_ instanceof SettableFieldNatOperation) {
            l_ = buildWritePartField(_resultInput, (SettableFieldNatOperation) settable_);
        }
        return l_;
    }

    private static StringMap<ExecTextPart> toExecPartExt(StringMap<NatResultText> _texts) {
        StringMap<ExecTextPart> m_ = new StringMap<ExecTextPart>();
        for (EntryCust<String, NatResultText> e: _texts.entryList()) {
            NatResultText value_ = e.getValue();
            m_.addEntry(e.getKey(),toExecPartExt(value_.getOpExpRoot(),value_.getTexts()));
        }
        return m_;
    }
    private static ExecTextPart toExecPartExt(CustList<NatOperationNode> _roots, StringList _texts) {
        CustList<CustList<RendDynOperationNode>> parts_ = toExecPartExt(_roots);
        ExecTextPart part_ = new ExecTextPart();
        part_.getTexts().addAllElts(_texts);
        part_.setOpExp(parts_);
        return part_;
    }
    private static StringMap<CustList<CustList<RendDynOperationNode>>> toExecPartMapExt(StringMap<CustList<NatOperationNode>> _roots) {
        StringMap<CustList<CustList<RendDynOperationNode>>> m_ = new StringMap<CustList<CustList<RendDynOperationNode>>>();
        for (EntryCust<String, CustList<NatOperationNode>> e:_roots.entryList()) {
            CustList<CustList<RendDynOperationNode>> parts_ = toExecPartExt(e.getValue());
            m_.addEntry(e.getKey(),parts_);
        }

        return m_;
    }
    private static CustList<CustList<RendDynOperationNode>> toExecPartExt(CustList<NatOperationNode> _roots) {
        CustList<CustList<RendDynOperationNode>> parts_;
        parts_ = new CustList<CustList<RendDynOperationNode>>();
        for (NatOperationNode r: _roots) {
            parts_.add(getExecutableNodes(r));
        }
        return parts_;
    }

    private static CustList<RendDynOperationNode> getExecutableNodes(NatOperationNode _root) {
        if (_root == null){
            return new CustList<RendDynOperationNode>();
        }
        return getExecutableNodesStd(_root);
    }

    private static CustList<RendDynOperationNode> getExecutableNodesStd(NatOperationNode _rootNat) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        NatOperationNode currentNat_ = _rootNat;
        RendDynOperationNode exp_ = createNatOperationNode(currentNat_);
        while (currentNat_ != null) {
            NatOperationNode opNat_ = currentNat_.getFirstChild();
            if (exp_ instanceof RendMethodOperation &&opNat_ != null) {
                RendDynOperationNode loc_ = createNatOperationNode(opNat_);
                ((RendMethodOperation) exp_).appendChild(loc_);
                exp_ = loc_;
                currentNat_ = opNat_;
                continue;
            }
            while (currentNat_ != null) {
                trySetup(exp_);
                out_.add(exp_);
                opNat_ = currentNat_.getNextSibling();
                RendMethodOperation par_ = exp_.getParent();
                if (opNat_ != null) {
                    RendDynOperationNode loc_ = createNatOperationNode(opNat_);
                    par_.appendChild(loc_);
                    setSiblingSet(exp_, opNat_, loc_);
                    exp_ = loc_;
                    currentNat_ = opNat_;
                    break;
                }
                opNat_ = currentNat_.getParent();
                if (opNat_ == null) {
                    currentNat_ = null;
                } else if (opNat_ == _rootNat) {
                    trySetup(par_);
                    out_.add(par_);
                    currentNat_ = null;
                } else {
                    currentNat_ = opNat_;
                    exp_ = par_;
                }
            }
        }
        return out_;
    }

    private static void setSiblingSet(RendDynOperationNode _exp, NatOperationNode _op, RendDynOperationNode _loc) {
        if (_op.getParent() instanceof AbstractDotNatOperation) {
            _exp.setSiblingSet((RendPossibleIntermediateDotted) _loc);
        }
    }

    private static void trySetup(RendDynOperationNode _exp) {
        if (_exp instanceof NatAbstractAffectOperation) {
            ((NatAbstractAffectOperation) _exp).setup();
        }
    }

    private static RendDynOperationNode createNatOperationNode(NatOperationNode _anaNode) {
        if (_anaNode instanceof InternGlobalNatOperation) {
            InternGlobalNatOperation m_ = (InternGlobalNatOperation) _anaNode;
            return new NatInternGlobalOperation(new ExecOperationContent(m_.getContent()));
        }
        return procOperand6(_anaNode);
    }

    private static RendDynOperationNode procOperand6(NatOperationNode _anaNode) {
        if (_anaNode instanceof FctNatOperation) {
            FctNatOperation i_ = (FctNatOperation) _anaNode;
            return new NatStdFctOperation(new ExecOperationContent(i_.getContent()), i_.isIntermediateDottedOperation(), new NatExecStdFctContent(i_.getCallFctContent()));
        }
        return procOperands5(_anaNode);
    }

    private static RendDynOperationNode procOperands5(NatOperationNode _anaNode) {
        return procOperands4(_anaNode);
    }

    private static RendDynOperationNode procOperands4(NatOperationNode _anaNode) {
        if (_anaNode instanceof IdNatOperation) {
            IdNatOperation d_ = (IdNatOperation) _anaNode;
            return new NatIdOperation(new ExecOperationContent(d_.getContent()));
        }
        return procOperands3(_anaNode);
    }

    private static RendDynOperationNode procOperands3(NatOperationNode _anaNode) {
        return procOperands2(_anaNode);
    }

    private static RendDynOperationNode procOperands2(NatOperationNode _anaNode) {
        return procOperands(_anaNode);
    }

    private static RendDynOperationNode procOperands(NatOperationNode _anaNode) {
        if (_anaNode instanceof SettableAbstractFieldNatOperation) {
            SettableAbstractFieldNatOperation s_ = (SettableAbstractFieldNatOperation) _anaNode;
            return new NatSettableFieldOperation(new ExecOperationContent(s_.getContent()), new NatExecFieldOperationContent(s_.getFieldContent()), new NatExecSettableOperationContent(s_.getSettableFieldContent()));
        }
        if (_anaNode instanceof FinalVariableNatOperation) {
            return finalVariable((FinalVariableNatOperation) _anaNode);
        }
        if (_anaNode instanceof DotNatOperation) {
            DotNatOperation m_ = (DotNatOperation) _anaNode;
            return new NatDotOperation(new ExecOperationContent(m_.getContent()));
        }
        return procGeneOperators(_anaNode);
    }

    private static RendLeafOperation finalVariable(FinalVariableNatOperation _anaNode) {
        if (_anaNode.isVarIndex()) {
            return new NatFinalVariableOperation(new ExecOperationContent(_anaNode.getContent()), new NatExecVariableContent(_anaNode.getVariableContent()));
        }
        return new NatStdRefVariableOperation(new ExecOperationContent(_anaNode.getContent()), new NatExecVariableContent(_anaNode.getVariableContent()));
    }

    private static RendDynOperationNode procGeneOperators(NatOperationNode _anaNode) {
        if (_anaNode instanceof UnaryBooleanNatOperation) {
            UnaryBooleanNatOperation m_ = (UnaryBooleanNatOperation) _anaNode;
            return new NatUnaryBooleanOperation(new ExecOperationContent(m_.getContent()));
        }
        AffectationNatOperation a_ = (AffectationNatOperation) _anaNode;
        return new NatAffectationOperation(new ExecOperationContent(a_.getContent()));
    }

    private static NatAnaVariableContent generateVariable(String _varLoc) {
        NatAnaVariableContent cont_ = new NatAnaVariableContent();
        cont_.setVariableName(_varLoc);
        return cont_;
    }

    public static ExecClassArgumentMatching toExec(String _cl) {
        return new ExecClassArgumentMatching(new StringList(_cl), (byte) -1, false, false);
    }
    private static CustList<RendDynOperationNode> buildWritePartField(NatResultInput _resultInput, SettableFieldNatOperation _settable) {
        CustList<RendDynOperationNode> w_ = new CustList<RendDynOperationNode>();
        String cl_ = _resultInput.getResult();
        ExecClassArgumentMatching pr_ = toExec(_resultInput.getPreviousResult());
        NatAffectationOperation rendAff_ = new NatAffectationOperation(new ExecOperationContent(0, pr_, 4));
        ExecClassArgumentMatching clResField_ = new ExecClassArgumentMatching(cl_);
        NatDotOperation rendDot_ = new NatDotOperation(new ExecOperationContent(0, clResField_, 2));
        NatStdRefVariableOperation rendPrevVar_ = new NatStdRefVariableOperation(new ExecOperationContent(0, pr_, 0), new NatExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        NatAnaFieldOperationContent cont_ = new NatAnaFieldOperationContent();
        cont_.setIntermediate(true);
        NatSettableFieldOperation rendField_ = new NatSettableFieldOperation(new ExecOperationContent(1,pr_,1),
                new NatExecFieldOperationContent(cont_), new NatExecSettableOperationContent(_settable.getSettableFieldContent()));
        rendPrevVar_.setSiblingSet(rendField_);
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(rendField_);
        rendAff_.appendChild(rendDot_);
        NatStdRefVariableOperation rendVar_ = new NatStdRefVariableOperation(new ExecOperationContent(0, clResField_, 3), new NatExecVariableContent(generateVariable(_resultInput.getVarNames().last())));
        rendAff_.appendChild(rendVar_);
        rendAff_.setup();

        w_.add(rendPrevVar_);
        w_.add(rendField_);
        w_.add(rendDot_);
        w_.add(rendVar_);
        w_.add(rendAff_);
        return w_;
    }

    private static void initValidatorsInstance() {
//        for (EntryCust<NatOperationNode, ValidatorInfo> e: _lateValidators.entryList()) {
//            ValidatorInfo v_ = e.getValue();
//            NatOperationNode root_ = e.getKey();
//            CustList<RendDynOperationNode> exps_ = getExecutableNodes(root_);
//            v_.setExps(exps_);
//        }
    }

    public static void buildExec(AnalyzingDoc _analyzingDoc, StringMap<AnaRendDocumentBlock> _d, StringMap<RendDocumentBlock> _renders) {
        buildExec(_d, _analyzingDoc, _renders);

        initValidatorsInstance();
    }

    private static void buildExec(StringMap<AnaRendDocumentBlock> _d, AnalyzingDoc _anaDoc, StringMap<RendDocumentBlock> _renders) {
        for (EntryCust<String,AnaRendDocumentBlock> v: _d.entryList()) {
            RendDocumentBlock rendDoc_ = build(v.getValue(), _anaDoc);
            _renders.put(v.getKey(), rendDoc_);
        }
    }

    private static FieldUpdates initIn(NatCaller _caller, String _idClass, String _idName, String _className, String _varName, InputInfo _varNames) {
        NatFieldUpdates fieldUpdates_ = new NatFieldUpdates(_caller);
        fieldUpdates_.setIdClass(_idClass);
        fieldUpdates_.setIdName(_idName);
        fieldUpdates_.setClassName(_className);
        fieldUpdates_.setVarName(_varName);
        fieldUpdates_.setVarNames(_varNames);
        fieldUpdates_.setVarNameConverter("");
        fieldUpdates_.setOpsConverter(new CustList<RendDynOperationNode>());
        return fieldUpdates_;
    }
}
