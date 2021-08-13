package code.bean.help.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendMessage;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.blocks.RendWithEl;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpRendMessage extends RendParentBlock implements RendWithEl {

    private final StringMap<String> preformatted;
    private StringList varNames = new StringList();


    public HelpRendMessage(StringMap<String> _preformatted,
                           StringList _varNames) {
        this.preformatted = _preformatted;
        this.varNames = _varNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        String preRend_;
        preRend_= StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        String lt_ = Character.toString(LT_BEGIN_TAG);
        String gt_ = Character.toString(GT_TAG);
        String concat_ = StringUtil.concat(lt_,TMP_BLOCK_TAG,gt_,preRend_,LT_END_TAG,TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        RendMessage.injectDoc(_cont,_rendStack,anchorArg_,docLoc_,varNames);
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }

}
