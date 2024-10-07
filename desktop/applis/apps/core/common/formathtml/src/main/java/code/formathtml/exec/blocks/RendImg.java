package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.images.BaseSixtyFourUtil;
import code.sml.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendImg extends RendElement {

    private final RendOperationNodeListOff rendExp;

    public RendImg(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, RendOperationNodeListOff _r) {
        super(_read, _execAttributes, _execAttributesText);
        rendExp = _r;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage last_ = _rendStack.getLastPage();
        last_.setOffset(rendExp.getOffset());
        String pageName_ = RendInput.idRad(rendExp.getList(),_ctx,_rendStack);
        if (pageName_ == null) {
            return null;
        }
        if (!prImg(_cont.getRend(), _cont.getRendKeyWords().group(), (Element) _nextWrite, pageName_)){
            ((Element) _nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),"");
        }
        return NullStruct.NULL_VALUE;
    }

    public static boolean prImg(ConfigurationCore _cont, RendKeyWordsGroup _attrs, Element _nextWrite, String _link) {
        String file_ = StringUtil.nullToEmpty(_cont.getFiles().getVal(_link));
        if (file_.isEmpty()) {
            return false;
        }
        buildAttr(_attrs,_nextWrite,file_);
//        _nextWrite.setAttribute(_attrs.getAttrSrc(),file_);
        return true;
    }
    public static void buildAttr(RendKeyWordsGroup _attrs, Element _nextWrite, String _content) {
        String base_;
        String after_;
        int sep_ = _content.indexOf("==");
        if (sep_ > -1) {
            String extract_ = _content.substring(0, sep_);
            String checked_ = BaseSixtyFourUtil.checkBase(extract_, _attrs.getKeyWordsDefs().getDefBaseSixtyFour());
            base_ = checked_;
            if (StringUtil.quickEq(checked_, extract_)) {
                after_ = _content.substring(sep_ + 2);
            } else {
                after_ = _content;
            }
        } else {
            base_ = _attrs.getKeyWordsDefs().getDefBaseSixtyFour();
            after_ = _content;
        }
        CustList<String> ls_ = StringUtil.splitChars(after_, '=');
        RendImgAnimAttr img_ = new RendImgAnimAttr(_attrs.getKeyWordsAttrs().getAttrSrc());
        img_.setAnim(BaseSixtyFourUtil.getImagesByString(ls_,base_));
        _nextWrite.removeAttribute(_attrs.getKeyWordsAttrs().getAttrSrc());
        _nextWrite.getAttributes().add(img_);
    }
}
