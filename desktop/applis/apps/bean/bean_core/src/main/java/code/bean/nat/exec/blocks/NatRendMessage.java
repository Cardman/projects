package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendMessage;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.blocks.RendWithEl;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public final class NatRendMessage extends RendParentBlock implements RendWithEl {

    private final CustList<CustList<RendDynOperationNode>> opExp;

    private final StringMap<String> preformatted;
    private StringMap<CustList<CustList<RendDynOperationNode>>> callsExps = new StringMap<CustList<CustList<RendDynOperationNode>>>();
    private StringList args = new StringList();
    private StringList varNames = new StringList();


    public NatRendMessage(CustList<CustList<RendDynOperationNode>> _opExp, StringMap<String> _preformatted,
                          StringMap<CustList<CustList<RendDynOperationNode>>> _callsExps, StringList _args, StringList _varNames) {
        this.opExp = _opExp;
        this.preformatted = _preformatted;
        this.callsExps = _callsExps;
        this.args = _args;
        this.varNames = _varNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            Argument arg_ = RenderExpUtil.calculateReuse(opExp.get(i), _stds, _ctx, _rendStack);
            String res_;
            res_ = BeanNatLgNames.processString(arg_);
            objects_.add(res_);
            anchorArg_.add(res_);
        }
        String preRend_;
        preRend_= StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        String lt_ = Character.toString(LT_BEGIN_TAG);
        String gt_ = Character.toString(GT_TAG);
        String concat_ = StringUtil.concat(lt_,TMP_BLOCK_TAG,gt_,preRend_,LT_END_TAG,TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        RendMessage.injectDoc(_cont,_rendStack,anchorArg_,docLoc_,callsExps,varNames);
        RendBlockHelp.processBlock(_ctx, _rendStack, this);
    }

}
