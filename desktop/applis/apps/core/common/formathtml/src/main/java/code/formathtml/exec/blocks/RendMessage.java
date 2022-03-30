package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public final class RendMessage extends RendParentBlock implements RendWithEl {

    private final CustList<CustList<RendDynOperationNode>> opExp;

    private final StringMap<String> preformatted;
    private CustList<Boolean> quoted = new CustList<Boolean>();
    private CustList<Boolean> escaped = new CustList<Boolean>();
    private StringMap<CustList<CustList<RendDynOperationNode>>> callsExps = new StringMap<CustList<CustList<RendDynOperationNode>>>();
    private StringList args = new StringList();
    private StringMap<Document> locDoc = new StringMap<Document>();
    private StringList varNames = new StringList();


    public RendMessage(CustList<CustList<RendDynOperationNode>> _opExp, StringMap<String> _preformatted, CustList<Boolean> _quoted, CustList<Boolean> _escaped,
                       StringMap<CustList<CustList<RendDynOperationNode>>> _callsExps, StringList _args, StringMap<Document> _locDoc, StringList _varNames) {
        this.opExp = _opExp;
        this.preformatted = _preformatted;
        this.quoted = _quoted;
        this.escaped = _escaped;
        this.callsExps = _callsExps;
        this.args = _args;
        this.locDoc = _locDoc;
        this.varNames = _varNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            if (quoted.get(i)) {
                objects_.add(args.get(i));
                anchorArg_.add(args.get(i));
                continue;
            }
            Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opExp.get(i), _ctx, _rendStack).lastValue().getArgument());
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            String res_;
            if (escaped.get(i)) {
                res_ = escapeParam(arg_, _ctx, _rendStack);
            } else {
                res_ = BeanCustLgNames.processStr(arg_, _ctx, _rendStack);
            }
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            objects_.add(res_);
            anchorArg_.add(res_);
        }
        String preRend_;
        preRend_= StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        CustList<Document> docs_ = new CustList<Document>();
        docs_.add(locDoc.getVal(_cont.getCurrentLanguage()));
        String lt_ = Character.toString(LT_BEGIN_TAG);
        String gt_ = Character.toString(GT_TAG);
        String concat_ = StringUtil.concat(lt_,TMP_BLOCK_TAG,gt_,preRend_,LT_END_TAG,TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        docs_.add(docLoc_);
        for (Document d: docs_) {
            if (d == null) {
                ImportingPage lastPage_ = _rendStack.getLastPage();
                RendReadWrite rend_ = lastPage_.getRendReadWrite();
                Document doc_ = rend_.getDocument();
                Text t_ = doc_.createTextNode(EMPTY_STRING);
                simpleAppendChild(doc_,rend_,t_);
                t_.appendData(preRend_);
                processBlock(_cont, _stds, _ctx, _rendStack);
                return;
            }
        }
        injectDoc(_cont, _rendStack, anchorArg_, docLoc_, callsExps, varNames);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }

    public static void injectDoc(Configuration _cont, RendStackCall _rendStack, StringList _anchorArg, Document _docLoc, StringMap<CustList<CustList<RendDynOperationNode>>> _callsExps, StringList _varNames) {
        _rendStack.getFormParts().getCallsExps().addAllElts(_callsExps.getVal(_cont.getCurrentLanguage()));
        injectDoc(_cont, _rendStack, _anchorArg, _docLoc, _varNames);
    }

    public static void injectDoc(Configuration _cont, RendStackCall _rendStack, StringList _anchorArg, Document _docLoc, StringList _varNames) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Element write_ = rw_.getWrite();
        Node root_ = _docLoc.getDocumentElement();
        Node read_ = root_.getFirstChild();
        Document ownerDocument_ = rw_.getDocument();
        while (true) {
            if (read_ instanceof Element) {
                Element eltRead_ = (Element) read_;
                Element created_ = appendChild(ownerDocument_, write_, eltRead_);
                processImportedNode(_cont,ip_, created_);
                if (StringUtil.quickEq(created_.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())){
                    _rendStack.getFormParts().getAnchorsArgs().add(_anchorArg);
                    _rendStack.getFormParts().getAnchorsVars().add(_varNames);
                }
                incrAncNb(_cont, created_, _rendStack);
                Node firstChild_ = read_.getFirstChild();
                if (firstChild_ != null) {
                    write_ = created_;
                    read_ = firstChild_;
                    continue;
                }
            } else {
                Text txt_ = (Text) read_;
                Text t_ = ownerDocument_.createTextNode(txt_.getTextContent());
                simpleAppendChild(ownerDocument_,write_,t_);
            }
            boolean stop_ = false;
            while (true) {
                Node nextSibling_ = read_.getNextSibling();
                if (nextSibling_ != null) {
                    read_ = nextSibling_;
                    break;
                }
                Element parentNode_ = read_.getParentNode();
                if (parentNode_ == root_) {
                    stop_ = true;
                    break;
                }
                write_ = getParentNode(write_);
                read_ = parentNode_;
            }
            if (stop_) {
                break;
            }
        }
    }

    private static void processImportedNode(Configuration _conf,
                                            ImportingPage _ip, Element _tag) {
        String beanName_ = _ip.getBeanName();
        if (StringUtil.quickEq(_tag.getTagName(),_conf.getRendKeyWords().getKeyWordAnchor())) {
            String href_ = _tag.getAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()));
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
            }
            if (_tag.hasAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))) {
                _tag.setAttribute(_conf.getRendKeyWords().getAttrHref(), EMPTY_STRING);
            }
        }
    }
}
