package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.sml.Element;
import code.sml.NavigationCore;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendImg extends NatRendElement {

    private final NatExecTextPart textPart;

    public NatRendImg(Element _read, StringMap<NatExecTextPart> _execAttributes, NatExecTextPart _textPart) {
        super(_read, _execAttributes);
        this.textPart = _textPart;
    }

    void img(NatConfigurationCore _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        String pageName_ = NatRenderingText.renderNat(textPart, _rendStack);
        prImg(_cont, (Element) _nextWrite, pageName_);
    }

    public static void prImg(NatConfigurationCore _cont, Element _nextWrite, String _pageName) {
        NavigationCore.prImg(_cont.getNat(),_cont.getRendKeyWords().getKeyWordsAttrs(), _nextWrite, _pageName);
    }
}
