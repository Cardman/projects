package code.bean.nat.fwd;

import code.bean.nat.*;
import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.blocks.*;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.opers.SettableFieldNatOperation;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.NatImportingPageAbs;
import code.bean.nat.exec.NatImportingPageForm;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.Element;
import code.sml.RendKeyWordsGroup;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AdvNatBlockBuilder implements AbstractNatBlockBuilder {

    @Override
    public NatAnaRendBlock defBlock(String _prefix, RendKeyWordsGroup _rendKeyWords, Element _elt) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordSelect()))) {
            return new NatAnaRendSelect(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordImg()))) {
            return new NatAnaRendEscImg(_elt,this);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordsTags().getKeyWordInput())) {
            return new NatAnaRendInput(_elt, StringUtil.quickEq(_elt.getAttribute(_rendKeyWords.getKeyWordsAttrs().getAttrType()), _rendKeyWords.getKeyWordsValues().getValueRadio()));
        }
//        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan()) && !_elt.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
//            return new NatAnaRendSpan(_elt);
//        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordAnchor()))) {
            return new NatAnaRendTitledAnchor(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordSubmit()))) {
            return new NatAnaRendSubmit(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordsTags().getKeyWordAnchor())) {
            String href_ = _elt.getAttribute(StringUtil.concat(_prefix,_rendKeyWords.getKeyWordsAttrs().getAttrCommand()));
            if (href_.isEmpty()) {
                return new NatAnaRendInactiveAnchor(_elt);
            }
            return new NatAnaRendAnchor(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordsTags().getKeyWordForm())) {
            return new NatAnaRendForm(_elt);
        }
        return new NatAnaRendStdElement(_elt, this);
    }

    @Override
    public NatBlock toExec(NatAnaRendBlock _from) {
        if (_from instanceof NatAnaRendSubmit){
            NatAnaRendSubmit f_ = (NatAnaRendSubmit) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            return new NatRendSubmit(f_.getRead(),part_,partText_, f_.getPreformatted());
        }
        if (_from instanceof NatAnaRendEscImg){
            NatAnaRendEscImg f_ = (NatAnaRendEscImg) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            return new NatRendEscImg(f_.getRead(),part_, NatRendForwardInfos.getExecutableNodes(f_.getRoot()));
        }
        if (_from instanceof NatAnaRendAnchor){
            NatAnaRendAnchor f_ = (NatAnaRendAnchor) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = NatRendForwardInfos.toExecPartExt(f_.getResultText().getOpExpRoot(), f_.getResultText().getTexts());
            CustList<NatExecOperationNode> op_ = NatRendForwardInfos.getExecutableNodes(f_.getResultText().getOpExpAnchorRoot());
            return new NatRendAnchor(f_.getRead(),part_,partText_,op_, f_.getResultText().getVarNames(),partSub_);
        }
        if (_from instanceof NatAnaRendInactiveAnchor){
            NatAnaRendInactiveAnchor f_ = (NatAnaRendInactiveAnchor) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            return new NatRendInactiveAnchor(f_.getRead(),part_,partText_);
        }
        if (_from instanceof NatAnaRendForm){
            NatAnaRendForm f_ = (NatAnaRendForm) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            CustList<NatExecOperationNode> opForm_ = NatRendForwardInfos.getExecutableNodes(f_.getRoot());
            return new NatRendForm(f_.getRead(),part_,partText_,opForm_,f_.getVarNames());
        }
        if (_from instanceof NatAnaRendSelect){
            NatAnaRendSelect f_ = (NatAnaRendSelect) _from;
            NatResultInput resultInput_ = f_.getResultInput();
            NatCaller opsWrite_ = buildWritePart(resultInput_);
            CustList<NatExecOperationNode> opRead_ = NatRendForwardInfos.getExecutableNodes(f_.getRootRead());
            CustList<NatExecOperationNode> opMap_ = NatRendForwardInfos.getExecutableNodes(f_.getRootMap());
            CustList<NatExecOperationNode> opValue_ = NatRendForwardInfos.getExecutableNodes(f_.getRootValue());
            return new NatRendSelect(opRead_,opValue_,opsWrite_,opMap_,
                    f_.getElt(),
                    initIn(f_.getClassNameNat(), false));
        }
        if (_from instanceof NatAnaRendInput){
            NatAnaRendInput f_ = (NatAnaRendInput) _from;
            if (f_.isRadio()) {
                NatResultInput resultInput_ = f_.getResultInput();
                NatCaller opsWrite_ = buildWritePart(resultInput_);
                CustList<NatExecOperationNode> opRead_ = NatRendForwardInfos.getExecutableNodes(f_.getRootRead());
                CustList<NatExecOperationNode> opValue_ = NatRendForwardInfos.getExecutableNodes(f_.getRootValue());
                StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
                StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
                return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                        initIn(f_.getClassName(), true));
            }
            NatResultInput resultInput_ = f_.getResultInput();
            NatCaller opsWrite_ = buildWritePart(resultInput_);
            CustList<NatExecOperationNode> opRead_ = NatRendForwardInfos.getExecutableNodes(f_.getRootRead());
            CustList<NatExecOperationNode> opValue_ = NatRendForwardInfos.getExecutableNodes(f_.getRootValue());
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            return new NatRendInput(f_.getRead(),part_,partText_,opRead_,opValue_,opsWrite_,
                    initIn(f_.getClassName(), false));
        }
//        if (_from instanceof NatAnaRendSpan){
//            NatAnaRendSpan f_ = (NatAnaRendSpan) _from;
//            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
//            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
//            NatExecTextPart partSub_ = NatRendForwardInfos.toExecPartExt(f_.getRoots(),f_.getTexts());
//            return new NatRendSpan(f_.getRead(),part_,partText_,partSub_,f_.getFormatted());
//        }
        if (_from instanceof NatAnaRendTitledAnchor){
            NatAnaRendTitledAnchor f_ = (NatAnaRendTitledAnchor) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            StringMap<NatExecTextPart> partText_ = NatRendForwardInfos.toExecPartExt(f_.getAttributesText());
            NatExecTextPart partSub_ = NatRendForwardInfos.toExecPartExt(f_.getResultText().getOpExpRoot(), f_.getResultText().getTexts());
            CustList<NatExecOperationNode> opAnc_ = NatRendForwardInfos.getExecutableNodes(f_.getResultText().getOpExpAnchorRoot());
            return new NatRendTitledAnchor(f_.getRead(),part_,partText_,opAnc_, f_.getResultText().getVarNames(), f_.getPreformatted(),partSub_);
        }
        if (_from instanceof NatAnaRendStdElement) {
            NatAnaRendStdElement f_ = (NatAnaRendStdElement) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            return new NatRendStdElement(f_.getRead(), part_);
        }
        return null;
    }

    static NatCaller buildWritePart(NatResultInput _resultInput) {
        NatOperationNode settable_ = _resultInput.getSettable();
        return ((SettableFieldNatOperation)settable_).getSettableFieldContent().getField().getCallerSet();
    }

    private static NatFieldUpdates initIn(String _className, boolean _rad) {
        NatFieldUpdates fieldUpdates_ = new NatFieldUpdates();
        fieldUpdates_.setClassName(_className);
        fieldUpdates_.setRad(_rad);
        return fieldUpdates_;
    }

    @Override
    public NatImportingPageAbs fwd() {
        return new NatImportingPageForm();
    }

    @Override
    public NatResultText newNatResultText() {
        return new NatResultTextForm();
    }

}
