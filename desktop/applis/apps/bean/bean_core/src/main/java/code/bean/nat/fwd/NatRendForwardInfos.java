package code.bean.nat.fwd;

import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.blocks.*;
import code.bean.nat.analyze.opers.*;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.*;
import code.bean.nat.fwd.opers.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendForwardInfos {
    private NatRendForwardInfos() {
    }
    private static NatDocumentBlock build(NatAnaRendDocumentBlock _ana, AnalyzingDoc _anaDoc) {
        NatDocumentBlock rendDoc_ = new NatDocumentBlock(_ana.getElt(), _ana.getBeanName());
        NatAnaExec pair_ = new NatAnaExec(_ana, rendDoc_);
        while (pair_.getReadNat() != null) {
            NatBlock loc_ = newRendBlock(pair_.getReadNat());
            pair_.setWriteNat(complete(_anaDoc, rendDoc_, pair_.getWriteNat(), loc_));
            nextPair(pair_);
        }
        return rendDoc_;
    }

    public static void nextPair(NatAnaExec _nat) {
        NatAnaRendBlock n_ = _nat.getReadNat().getFirstChild();
        if (n_ != null) {
            _nat.setReadNat(n_);
            return;
        }
        while (_nat.getReadNat() != null) {
            n_ = _nat.getReadNat().getNextSibling();
            if (n_ != null) {
                _nat.setReadNat(n_);
                break;
            }
            NatAnaRendParentBlock parent_ = _nat.getReadNat().getParent();
            _nat.setWriteNat(_nat.getWriteNat().getParent());
            if (_nat.getWriteNat() == null) {
                _nat.setReadNat(null);
            } else {
                _nat.setReadNat(parent_);
            }
        }
    }

    private static NatParentBlock complete(AnalyzingDoc _anaDoc, NatDocumentBlock _rendDoc, NatParentBlock _curPar, NatBlock _loc) {
        if (_loc != null) {
            if (_loc instanceof NatRendStdElement && StringUtil.quickEq(((NatRendStdElement) _loc).getRead().getTagName(), _anaDoc.getRendKeyWords().getKeyWordBody())) {
                _rendDoc.setBody((NatRendStdElement)_loc);
            }
//            _loc.setEscapedChars(_en.getEscapedChars());
            _curPar.appendChild(_loc);
        }
        if (_loc instanceof NatParentBlock) {
            return (NatParentBlock) _loc;
        }
        return _curPar;
    }

    private static NatBlock newRendBlock(NatAnaRendBlock _current) {
        if (_current instanceof NatAnaRendText){
            NatAnaRendText t_ = (NatAnaRendText) _current;
            NatExecTextPart part_ = toExecPartExt(t_.getRoots(),t_.getTexts());
            return new NatRendText(part_);
        }
        if (_current instanceof NatAnaRendForEachLoop){
            NatAnaRendForEachLoop f_ = (NatAnaRendForEachLoop) _current;
            CustList<NatExecOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendForEachIterable(f_.getVariableName(),
                    op_);
        }
        return block2(_current);
    }

    private static NatBlock block2(NatAnaRendBlock _current) {
        if (_current instanceof NatAnaRendForEachTable){
            NatAnaRendForEachTable f_ = (NatAnaRendForEachTable) _current;
            CustList<NatExecOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendForEachTable(f_.getVariableNameFirst(),
                    f_.getVariableNameSecond(),
                    new NatRendOperationNodeListOff(op_));
        }
        return block(_current);
    }

    private static NatBlock block(NatAnaRendBlock _current) {
        if (_current instanceof NatAnaRendIfCondition){
            NatAnaRendIfCondition f_ = (NatAnaRendIfCondition) _current;
            CustList<NatExecOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendIfCondition(op_);
        }
        if (_current instanceof NatAnaRendElseIfCondition){
            NatAnaRendElseIfCondition f_ = (NatAnaRendElseIfCondition) _current;
            CustList<NatExecOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendElseIfCondition(op_);
        }
        if (_current instanceof NatAnaRendElseCondition){
            return new NatRendElseCondition();
        }
        if (_current instanceof NatAnaRendImport){
            NatAnaRendImport f_ = (NatAnaRendImport) _current;
            NatExecTextPart part_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            CustList<CustList<NatExecOperationNode>> fs_ = toExecPartExt(f_.getFields());
            return new NatRendImport(part_, f_.getNatImpLgNames(), fs_);
        }
        return element(_current);
    }

    private static NatBlock element(NatAnaRendBlock _current) {
        if (_current instanceof NatAnaRendSubmit){
            NatAnaRendSubmit f_ = (NatAnaRendSubmit) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendSubmit(f_.getRead(),part_,partText_, f_.getPreformatted());
        }
        if (_current instanceof NatAnaRendAnchor){
            NatAnaRendAnchor f_ = (NatAnaRendAnchor) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            CustList<NatExecOperationNode> op_ = getExecutableNodes(f_.getRoot());
            return new NatRendAnchor(f_.getRead(),part_,partText_,op_, f_.getVarNames(),partSub_);
        }
        if (_current instanceof NatAnaRendImg){
            NatAnaRendImg f_ = (NatAnaRendImg) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendImg(f_.getRead(),part_,partText_, partSub_);
        }
        if (_current instanceof NatAnaRendLink){
            NatAnaRendLink f_ = (NatAnaRendLink) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendLink(f_.getRead(),part_,partText_,f_.getContent());
        }
        if (_current instanceof NatAnaRendEscImg){
            NatAnaRendEscImg f_ = (NatAnaRendEscImg) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendEscImg(f_.getRead(),part_,partText_);
        }
        if (_current instanceof NatAnaRendForm){
            NatAnaRendForm f_ = (NatAnaRendForm) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            CustList<NatExecOperationNode> opForm_ = getExecutableNodes(f_.getRoot());
            NatExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendForm(f_.getRead(),part_,partText_,opForm_,f_.getVarNames(),partSub_);
        }
        if (_current instanceof NatAnaRendMessage){
            NatAnaRendMessage f_ = (NatAnaRendMessage) _current;
            CustList<CustList<NatExecOperationNode>> partSub_ = toExecPartExt(f_.getRoots());
            return new NatRendMessage(partSub_,
                    f_.getPreformatted(),
                    f_.getArgs()
            );
        }
        return input(_current);
    }

    private static NatBlock input(NatAnaRendBlock _current) {
        if (_current instanceof NatAnaRendSelect){
            NatAnaRendSelect f_ = (NatAnaRendSelect) _current;
            NatResultInput resultInput_ = f_.getResultInput();
            CustList<NatExecOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
            CustList<NatExecOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
            CustList<NatExecOperationNode> opMap_ = getExecutableNodes(f_.getRootMap());
            CustList<NatExecOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
            return new NatRendSelect(opRead_,opValue_,opsWrite_,opMap_,
                    f_.getElt(),
                    initIn(f_.getClassNameNat(), f_.getVarName()));
        }
        if (_current instanceof NatAnaRendInput){
            NatAnaRendInput f_ = (NatAnaRendInput) _current;
            if (f_.isRadio()) {
                NatResultInput resultInput_ = f_.getResultInput();
                CustList<NatExecOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
                CustList<NatExecOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
                CustList<NatExecOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
                StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
                StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
                return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                        initIn(f_.getClassName(), f_.getVarName()));
            }
            NatResultInput resultInput_ = f_.getResultInput();
            CustList<NatExecOperationNode> opsWrite_ = NatRendForwardInfos.buildWritePart(resultInput_);
            CustList<NatExecOperationNode> opRead_ = getExecutableNodes(f_.getRootRead());
            CustList<NatExecOperationNode> opValue_ = getExecutableNodes(f_.getRootValue());
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                    initIn(f_.getClassName(), f_.getVarName()));
        }
        if (_current instanceof NatAnaRendSpan){
            NatAnaRendSpan f_ = (NatAnaRendSpan) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            return new NatRendSpan(f_.getRead(),part_,partText_,partSub_,f_.getFormatted());
        }
        if (_current instanceof NatAnaRendTitledAnchor){
            NatAnaRendTitledAnchor f_ = (NatAnaRendTitledAnchor) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = toExecPartExt(f_.getRoots(),f_.getTexts());
            CustList<NatExecOperationNode> opAnc_ = getExecutableNodes(f_.getRoot());
            return new NatRendTitledAnchor(f_.getRead(),part_,partText_,opAnc_,f_.getVarNames(), f_.getPreformatted(),partSub_);
        }
        if (_current instanceof NatAnaRendEmptyInstruction){
            return new NatRendEmptyInstruction();
        }
        if (_current instanceof NatAnaRendStdElement) {
            NatAnaRendStdElement f_ = (NatAnaRendStdElement) _current;
            StringMap<NatExecTextPart> part_ = toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = toExecPartExt(f_.getAttributesText());
            return new NatRendStdElement(f_.getRead(), part_, partText_);
        }
        return null;
    }

    static CustList<NatExecOperationNode> buildWritePart(NatResultInput _resultInput) {
        NatOperationNode settable_ = _resultInput.getSettable();
        CustList<NatExecOperationNode> l_ = new CustList<NatExecOperationNode>();
        if (settable_ instanceof SettableFieldNatOperation) {
            l_ = buildWritePartField(_resultInput, (SettableFieldNatOperation) settable_);
        }
        return l_;
    }

    private static StringMap<NatExecTextPart> toExecPartExt(StringMap<NatResultText> _texts) {
        StringMap<NatExecTextPart> m_ = new StringMap<NatExecTextPart>();
        for (EntryCust<String, NatResultText> e: _texts.entryList()) {
            NatResultText value_ = e.getValue();
            m_.addEntry(e.getKey(),toExecPartExt(value_.getOpExpRoot(),value_.getTexts()));
        }
        return m_;
    }
    private static NatExecTextPart toExecPartExt(CustList<NatOperationNode> _roots, StringList _texts) {
        CustList<CustList<NatExecOperationNode>> parts_ = toExecPartExt(_roots);
        NatExecTextPart part_ = new NatExecTextPart();
        part_.getTexts().addAllElts(_texts);
        part_.setOpExp(parts_);
        return part_;
    }

    private static CustList<CustList<NatExecOperationNode>> toExecPartExt(CustList<NatOperationNode> _roots) {
        CustList<CustList<NatExecOperationNode>> parts_;
        parts_ = new CustList<CustList<NatExecOperationNode>>();
        for (NatOperationNode r: _roots) {
            parts_.add(getExecutableNodes(r));
        }
        return parts_;
    }

    private static CustList<NatExecOperationNode> getExecutableNodes(NatOperationNode _root) {
        if (_root == null){
            return new CustList<NatExecOperationNode>();
        }
        return getExecutableNodesStd(_root);
    }

    private static CustList<NatExecOperationNode> getExecutableNodesStd(NatOperationNode _rootNat) {
        CustList<NatExecOperationNode> out_ = new CustList<NatExecOperationNode>();
        NatOperationNode currentNat_ = _rootNat;
        NatExecOperationNode exp_ = createNatOperationNode(currentNat_);
        while (currentNat_ != null) {
            NatOperationNode opNat_ = currentNat_.getFirstChild();
            if (exp_ instanceof NatExecMethodOperation &&opNat_ != null) {
                NatExecOperationNode loc_ = createNatOperationNode(opNat_);
                ((NatExecMethodOperation) exp_).appendChild(loc_);
                exp_ = loc_;
                currentNat_ = opNat_;
                continue;
            }
            while (currentNat_ != null) {
                trySetup(exp_);
                out_.add(exp_);
                opNat_ = currentNat_.getNextSibling();
                NatExecMethodOperation par_ = exp_.getParent();
                if (opNat_ != null) {
                    NatExecOperationNode loc_ = createNatOperationNode(opNat_);
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

    private static void setSiblingSet(NatExecOperationNode _exp, NatOperationNode _op, NatExecOperationNode _loc) {
        if (_op.getParent() instanceof AbstractDotNatOperation) {
            _exp.setSiblingSet((NatExecPossibleIntermediateDotted) _loc);
        }
    }

    private static void trySetup(NatExecOperationNode _exp) {
        if (_exp instanceof NatAbstractAffectOperation) {
            ((NatAbstractAffectOperation) _exp).setup();
        }
    }

    private static NatExecOperationNode createNatOperationNode(NatOperationNode _anaNode) {
        if (_anaNode instanceof InternGlobalNatOperation) {
            InternGlobalNatOperation m_ = (InternGlobalNatOperation) _anaNode;
            return new NatInternGlobalOperation(m_.getOrder());
        }
        return procOperand6(_anaNode);
    }

    private static NatExecOperationNode procOperand6(NatOperationNode _anaNode) {
        if (_anaNode instanceof FctNatOperation) {
            FctNatOperation i_ = (FctNatOperation) _anaNode;
            return new NatStdFctOperation(i_.getOrder(), i_.isIntermediateDottedOperation(), new NatExecStdFctContent(i_.getCallFctContent()));
        }
        return procOperands5(_anaNode);
    }

    private static NatExecOperationNode procOperands5(NatOperationNode _anaNode) {
        return procOperands4(_anaNode);
    }

    private static NatExecOperationNode procOperands4(NatOperationNode _anaNode) {
        if (_anaNode instanceof IdNatOperation) {
            IdNatOperation d_ = (IdNatOperation) _anaNode;
            return new NatIdOperation(d_.getOrder());
        }
        return procOperands3(_anaNode);
    }

    private static NatExecOperationNode procOperands3(NatOperationNode _anaNode) {
        return procOperands2(_anaNode);
    }

    private static NatExecOperationNode procOperands2(NatOperationNode _anaNode) {
        return procOperands(_anaNode);
    }

    private static NatExecOperationNode procOperands(NatOperationNode _anaNode) {
        if (_anaNode instanceof SettableAbstractFieldNatOperation) {
            SettableAbstractFieldNatOperation s_ = (SettableAbstractFieldNatOperation) _anaNode;
            return new NatSettableFieldOperation(s_.getOrder(), new NatExecFieldOperationContent(s_.getFieldContent()), new NatExecSettableOperationContent(s_.getSettableFieldContent()));
        }
        if (_anaNode instanceof FinalVariableNatOperation) {
            return finalVariable((FinalVariableNatOperation) _anaNode);
        }
        if (_anaNode instanceof DotNatOperation) {
            DotNatOperation m_ = (DotNatOperation) _anaNode;
            return new NatDotOperation(m_.getOrder());
        }
        return procGeneOperators(_anaNode);
    }

    private static NatExecOperationNode finalVariable(FinalVariableNatOperation _anaNode) {
        if (_anaNode.isVarIndex()) {
            return new NatFinalVariableOperation(_anaNode.getOrder(), new NatExecVariableContent(_anaNode.getVariableContent()));
        }
        return new NatStdRefVariableOperation(_anaNode.getOrder(), new NatExecVariableContent(_anaNode.getVariableContent()));
    }

    private static NatExecOperationNode procGeneOperators(NatOperationNode _anaNode) {
        if (_anaNode instanceof UnaryBooleanNatOperation) {
            UnaryBooleanNatOperation m_ = (UnaryBooleanNatOperation) _anaNode;
            return new NatUnaryBooleanOperation(m_.getOrder());
        }
        AffectationNatOperation a_ = (AffectationNatOperation) _anaNode;
        return new NatAffectationOperation(a_.getOrder());
    }

    private static NatAnaVariableContent generateVariable(String _varLoc) {
        NatAnaVariableContent cont_ = new NatAnaVariableContent();
        cont_.setVariableName(_varLoc);
        return cont_;
    }

    private static CustList<NatExecOperationNode> buildWritePartField(NatResultInput _resultInput, SettableFieldNatOperation _settable) {
        CustList<NatExecOperationNode> w_ = new CustList<NatExecOperationNode>();
        NatAffectationOperation rendAff_ = new NatAffectationOperation(4);
        NatDotOperation rendDot_ = new NatDotOperation(2);
        NatStdRefVariableOperation rendPrevVar_ = new NatStdRefVariableOperation(0, new NatExecVariableContent(generateVariable(_resultInput.getVarNames().first())));
        NatAnaFieldOperationContent cont_ = new NatAnaFieldOperationContent();
        cont_.setIntermediate(true);
        NatSettableFieldOperation rendField_ = new NatSettableFieldOperation(1,
                new NatExecFieldOperationContent(cont_), new NatExecSettableOperationContent(_settable.getSettableFieldContent()));
        rendPrevVar_.setSiblingSet(rendField_);
        rendDot_.appendChild(rendPrevVar_);
        rendDot_.appendChild(rendField_);
        rendAff_.appendChild(rendDot_);
        NatStdRefVariableOperation rendVar_ = new NatStdRefVariableOperation(3, new NatExecVariableContent(generateVariable(_resultInput.getVarNames().last())));
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

    public static void buildExec(AnalyzingDoc _analyzingDoc, StringMap<NatAnaRendDocumentBlock> _d, StringMap<NatDocumentBlock> _renders) {
        buildExec(_d, _analyzingDoc, _renders);

        initValidatorsInstance();
    }

    private static void buildExec(StringMap<NatAnaRendDocumentBlock> _d, AnalyzingDoc _anaDoc, StringMap<NatDocumentBlock> _renders) {
        for (EntryCust<String,NatAnaRendDocumentBlock> v: _d.entryList()) {
            NatDocumentBlock rendDoc_ = build(v.getValue(), _anaDoc);
            _renders.put(v.getKey(), rendDoc_);
        }
    }

    private static NatFieldUpdates initIn(String _className, String _varName) {
        NatFieldUpdates fieldUpdates_ = new NatFieldUpdates();
        fieldUpdates_.setClassName(_className);
        fieldUpdates_.setVarName(_varName);
        return fieldUpdates_;
    }
}
