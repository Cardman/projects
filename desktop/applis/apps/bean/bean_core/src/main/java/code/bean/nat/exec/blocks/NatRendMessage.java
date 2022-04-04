package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendMessage;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendMessage extends NatParentBlock implements NatRendWithEl {

    private final CustList<CustList<NatExecOperationNode>> opExp;

    private final StringMap<String> preformatted;
    private StringMap<CustList<CustList<NatExecOperationNode>>> callsExps = new StringMap<CustList<CustList<NatExecOperationNode>>>();
    private StringList args = new StringList();
    private StringList varNames = new StringList();


    public NatRendMessage(CustList<CustList<NatExecOperationNode>> _opExp, StringMap<String> _preformatted,
                          StringMap<CustList<CustList<NatExecOperationNode>>> _callsExps, StringList _args, StringList _varNames) {
        this.opExp = _opExp;
        this.preformatted = _preformatted;
        this.callsExps = _callsExps;
        this.args = _args;
        this.varNames = _varNames;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            Argument arg_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(opExp.get(i), _rendStack).lastValue().getArgument());
            String res_;
            res_ = BeanNatCommonLgNames.processString(arg_);
            objects_.add(res_);
            anchorArg_.add(res_);
        }
        String preRend_;
        preRend_= StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        String lt_ = Character.toString(RendBlock.LT_BEGIN_TAG);
        String gt_ = Character.toString(RendBlock.GT_TAG);
        String concat_ = StringUtil.concat(lt_,RendBlock.TMP_BLOCK_TAG,gt_,preRend_,RendBlock.LT_END_TAG,RendBlock.TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        _rendStack.getFormParts().getCallsExps().addAllElts(callsExps.getVal(_cont.getCurrentLanguage()));
        RendMessage.injectDoc(_cont, anchorArg_, docLoc_, varNames, _rendStack.getLastPage().getBeanName(), _rendStack.getLastPage().getRendReadWrite(), _rendStack.getFormParts());
        RendBlockHelp.processBlock(_rendStack, this);
    }

}
