package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendImg;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendImg extends NatRendElement {

    private final NatExecTextPart textPart;

    public NatRendImg(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText, NatExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.textPart = _textPart;
    }

    void img(Configuration _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        String pageName_ = NatRenderingText.renderNat(textPart, _rendStack);
        prImg(_cont, (Element) _nextWrite, pageName_);
    }

    public static void prImg(Configuration _cont, Element _nextWrite, String _pageName) {
        RendImg.prImg(_cont, _nextWrite, _pageName);
    }
}
