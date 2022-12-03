package code.bean.help.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatParentBlock;
import code.bean.nat.exec.blocks.NatRendMessage;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpRendMessage extends NatParentBlock {

    private final StringMap<String> preformatted;


    public HelpRendMessage(StringMap<String> _preformatted) {
        this.preformatted = _preformatted;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        StringList objects_ = new StringList();
        String preRend_ = StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        NatRendMessage.injectDoc(_rendStack.getLastPage().getRendReadWrite(), preRend_);
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }

}
