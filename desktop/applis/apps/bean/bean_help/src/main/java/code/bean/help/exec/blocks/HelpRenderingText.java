package code.bean.help.exec.blocks;

import code.bean.nat.exec.blocks.NatExecTextPart;
import code.util.StringList;

public final class HelpRenderingText {
    private HelpRenderingText() {
    }

    public static String render(NatExecTextPart _texts) {
        StringList texts_ = _texts.getTexts();
        return texts_.last();
    }

}
