package code.bean.help.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.exec.blocks.NatRendImg;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class HelpRendImg extends HelpRendElement {

    private final NatExecTextPart textPart;

    public HelpRendImg(Element _read, StringMap<NatExecTextPart> _execAttributes, NatExecTextPart _textPart) {
        super(_read, _execAttributes);
        this.textPart = _textPart;
    }

    void processExecAttr(NatConfigurationCore _cont, Node _nextWrite) {
        String pageName_ = HelpRenderingText.render(textPart);
        NatRendImg.prImg(_cont,(Element)_nextWrite,pageName_);
    }
}
