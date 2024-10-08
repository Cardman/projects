package code.bean.nat.exec.blocks;

import aiki.beans.NaImgSt;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NaSt;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatImportingPageAbs;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.Element;
import code.sml.Node;
import code.sml.RendKeyWordsGroup;
import code.util.CustList;
import code.util.StringMap;

public final class NatRendEscImg extends NatRendElement {

    private final CustList<NatExecOperationNode> imgOps;
    public NatRendEscImg(Element _read, StringMap<NatExecTextPart> _execAttributes, CustList<NatExecOperationNode> _i) {
        super(_read, _execAttributes);
        this.imgOps = _i;
    }

    @Override
    protected void tag(NatConfigurationCore _cont, NatRendStackCall _rendStack, NatImportingPageAbs _ip, NatRendReadWrite _rw) {
        Element created_ = RendBlockHelp.appendChild(_rw.getDocument(), _rw, getRead());
//        for (EntryCust<String, NatExecTextPart> e: natAttributesText.entryList()) {
//            NatExecTextPart res_ = e.getValue();
//            String txt_ = NatRenderingText.renderNat(res_, _rendStack);
//            created_.setAttribute(e.getKey(),txt_);
//        }
        escImg(_cont, created_);
        NaSt argument_ = BeanNatCommonLgNames.getAllArgs(imgOps, _rendStack).lastValue().getArgument();
        endElement(_rendStack, _ip, _rw, created_);
        buildAttr(_cont.getRendKeyWords(),created_,NaImgSt.tryGet(argument_));
    }

    void escImg(NatConfigurationCore _cont, Node _nextWrite) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordsTags().getKeyWordImg());
    }
    public static void buildAttr(RendKeyWordsGroup _attrs, Element _nextWrite, int[][] _content) {
//        String base_;
//        int sep_ = _content.indexOf("==");
//        int from_;
//        base_ = _attrs.getKeyWordsDefs().getDefBaseSixtyFour();
//        from_ = 0;
//        CustList<String> ls_ = StringUtil.splitChars(_content.substring(sep_ + 2), '=').mid(from_);
        NatImgAttr img_ = new NatImgAttr(_attrs.getKeyWordsAttrs().getAttrSrc());
//        CustList<int[][]> anim_ = new CustList<int[][]>();
//        for (String p: ls_) {
//            anim_.add(BaseSixtyFourUtil.getImageByString(_content,base_));
//        }
        img_.setAnim(new CustList<int[][]>(_content));
        _nextWrite.removeAttribute(_attrs.getKeyWordsAttrs().getAttrSrc());
        _nextWrite.getAttributes().add(img_);

//        RendImgAttr img_ = new RendImgAttr(_attrs.getKeyWordsAttrs().getAttrSrc());
//        int sep_ = _content.indexOf("==");
//        int from_;
//        if (sep_ > -1) {
//            String extract_ = _content.substring(0, sep_);
//            String checked_ = BaseSixtyFourUtil.checkBase(extract_, _attrs.getKeyWordsDefs().getDefBaseSixtyFour());
//            if (StringUtil.quickEq(checked_, extract_)) {
//                from_ = 1;
//            } else {
//                from_ = 0;
//            }
//        } else {
//            from_ = 0;
//        }
//        StringUtil.splitChars(_content.substring(sep_+2),'=').mid(from_);
//        img_.setImg(BaseSixtyFourUtil.getImageByString(_content,_attrs.getKeyWordsDefs().getDefBaseSixtyFour()));
//        _nextWrite.getAttributes().add(img_);
    }
}
