package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatImportingPageAbs;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
import code.images.BaseSixtyFourUtil;
import code.sml.Element;
import code.sml.Node;
import code.sml.RendKeyWordsGroup;
import code.sml.util.MessagesTranslations;
import code.util.CustList;
import code.util.StringMap;

public final class NatRendEscImg extends NatRendElement {

    public NatRendEscImg(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        super(_read, _execAttributes);
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
        endElement(_rendStack, _ip, _rw, created_);
        buildAttr(_cont.getRendKeyWords(),created_,created_.getAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrSrc()));
    }

    void escImg(NatConfigurationCore _cont, Node _nextWrite) {
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordsTags().getKeyWordImg());
    }
    public static void buildAttr(RendKeyWordsGroup _attrs, Element _nextWrite, String _content) {
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
        img_.setAnim(new CustList<int[][]>(BaseSixtyFourUtil.getImageByString(_content, MessagesTranslations.BASE)));
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
