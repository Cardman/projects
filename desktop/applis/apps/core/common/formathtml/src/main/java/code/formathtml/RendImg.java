package code.formathtml;

import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RenderingText;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringMap;

public final class RendImg extends RendElement {

    private ExecTextPart textPart;

    public RendImg(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, ExecTextPart textPart) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.textPart = textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String pageName_ = RenderingText.render(textPart,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        String lg_ = _cont.getCurrentLanguage();
        String link_ = RendExtractFromResources.getRealFilePath(lg_,pageName_);
        String file_ = _cont.getFiles().getVal(link_);
        if (file_ == null) {
            return;
        }
        ((Element)_nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),file_);
    }
}
