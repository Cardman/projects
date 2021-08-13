package code.bean.help.exec.blocks;

import code.formathtml.exec.blocks.ExecTextPart;
import code.util.StringList;

public final class HelpRenderingText {
    private HelpRenderingText() {
    }

    public static String render(ExecTextPart _texts) {
        StringList texts_ = _texts.getTexts();
        return texts_.last();
    }

}
