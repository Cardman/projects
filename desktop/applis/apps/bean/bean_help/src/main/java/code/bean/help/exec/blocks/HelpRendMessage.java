package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatRendWithEl;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendMessage;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpRendMessage extends RendParentBlock implements NatRendWithEl {

    private final StringMap<String> preformatted;
    private StringList varNames = new StringList();


    public HelpRendMessage(StringMap<String> _preformatted,
                           StringList _varNames) {
        this.preformatted = _preformatted;
        this.varNames = _varNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack) {
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        String preRend_;
        preRend_= StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        String lt_ = Character.toString(LT_BEGIN_TAG);
        String gt_ = Character.toString(GT_TAG);
        String concat_ = StringUtil.concat(lt_,TMP_BLOCK_TAG,gt_,preRend_,LT_END_TAG,TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        RendMessage.injectDoc(_cont, anchorArg_,docLoc_,varNames, _rendStack.getLastPage().getBeanName(), _rendStack.getLastPage().getRendReadWrite(), _rendStack.getFormParts());
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }

}
