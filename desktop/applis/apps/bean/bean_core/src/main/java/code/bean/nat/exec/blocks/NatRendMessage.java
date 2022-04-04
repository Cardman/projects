package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.Element;
import code.sml.Text;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendMessage extends NatParentBlock {

    private final CustList<CustList<NatExecOperationNode>> opExp;

    private final StringMap<String> preformatted;
    private final StringList args;


    public NatRendMessage(CustList<CustList<NatExecOperationNode>> _opExp, StringMap<String> _preformatted,
                          StringList _args) {
        this.opExp = _opExp;
        this.preformatted = _preformatted;
        this.args = _args;
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
        String preRend_ = StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        injectDoc(_rendStack.getLastPage().getRendReadWrite(), preRend_);
        RendBlockHelp.processBlock(_rendStack, this);
    }

    public static void injectDoc(RendReadWrite _rendReadWrite, String _textContent) {
        Element write_ = _rendReadWrite.getWrite();
        Document ownerDocument_ = _rendReadWrite.getDocument();
        Text t_ = ownerDocument_.createTextNode(_textContent);
        RendBlock.simpleAppendChild(ownerDocument_,write_,t_);
    }

}
