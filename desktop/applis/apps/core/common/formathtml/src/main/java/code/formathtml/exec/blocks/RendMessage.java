package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.sml.FormParts;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class RendMessage extends RendParentBlock implements RendWithEl {

    private final CustList<CustList<RendDynOperationNode>> opExp;

    private final StringMap<String> preformatted;
    private final CustList<BoolVal> quoted;
    private final CustList<BoolVal> escaped;
    private final StringMap<CustList<CustList<RendDynOperationNode>>> callsExps;
    private final StringList args;
    private final StringMap<Document> locDoc;


    public RendMessage(CustList<CustList<RendDynOperationNode>> _opExp, StringMap<String> _preformatted, CustList<BoolVal> _quoted, CustList<BoolVal> _escaped,
                       StringMap<CustList<CustList<RendDynOperationNode>>> _callsExps, StringList _args, StringMap<Document> _locDoc) {
        this.opExp = _opExp;
        this.preformatted = _preformatted;
        this.quoted = _quoted;
        this.escaped = _escaped;
        this.callsExps = _callsExps;
        this.args = _args;
        this.locDoc = _locDoc;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            if (quoted.get(i) == BoolVal.TRUE) {
                objects_.add(args.get(i));
                anchorArg_.add(args.get(i));
                continue;
            }
            Struct arg_ = RenderExpUtil.getFinalArg(opExp.get(i), _ctx, _rendStack);
            if (arg_ == null) {
                return;
            }
            String res_;
            if (escaped.get(i) == BoolVal.TRUE) {
                res_ = escapeParam(arg_, _ctx, _rendStack);
            } else {
                res_ = BeanCustLgNames.processString(arg_, _ctx, _rendStack);
            }
            if (res_ == null) {
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
                NavigationCore.simpleAppendChild(doc_,rend_,t_);
                t_.appendData(preRend_);
                processBlock(_cont, _stds, _ctx, _rendStack);
                return;
            }
        }
        for (CustList<RendDynOperationNode> e: callsExps.getVal(_cont.getCurrentLanguage())) {
            _rendStack.getFormParts().getCallsExps().add(new AnchorCall(e,new CustList<AbstractWrapper>()));
        }
//        _rendStack.getFormParts().getCallsExps().addAllElts(callsExps.getVal(_cont.getCurrentLanguage()));
        injectDoc(_cont, docLoc_, _rendStack.getLastPage().getBeanName(), _rendStack.getLastPage().getRendReadWrite(), _rendStack.getFormParts());
        processBlock(_cont, _stds, _ctx, _rendStack);
    }

    public static void injectDoc(Configuration _cont, Document _docLoc, String _beanName, RendReadWrite _rendReadWrite, FormParts _formParts) {
        Element write_ = _rendReadWrite.getWrite();
        Node root_ = _docLoc.getDocumentElement();
        Node read_ = root_.getFirstChild();
        Document ownerDocument_ = _rendReadWrite.getDocument();
        while (read_ != null) {
            if (read_ instanceof Element) {
                Element eltRead_ = (Element) read_;
                Element created_ = appendedChild(ownerDocument_, write_, eltRead_);
                processImportedNode(_cont, created_, _beanName);
                incrAncNb(_cont.getRend(), created_, _formParts.getIndexes(), _cont.getRendKeyWords().group());
                Node firstChild_ = read_.getFirstChild();
                if (firstChild_ != null) {
                    write_ = created_;
                    read_ = firstChild_;
                    continue;
                }
            } else {
                Text txt_ = (Text) read_;
                Text t_ = ownerDocument_.createTextNode(txt_.getTextContent());
                NavigationCore.simpleAppendChild(ownerDocument_,write_,t_);
            }
            while (read_ != null) {
                Node nextSibling_ = read_.getNextSibling();
                if (nextSibling_ != null) {
                    read_ = nextSibling_;
                    break;
                }
                Element parentNode_ = read_.getParentNode();
                if (parentNode_ != root_) {
                    write_ = NavigationCore.getParentNode(write_);
                }
                read_ = getNode(root_, parentNode_);
            }
        }
    }

    private static Node getNode(Node _r, Element _par) {
        Node read_;
        if (_par == _r) {
            read_ = null;
        } else {
            read_ = _par;
        }
        return read_;
    }

    private static void processImportedNode(Configuration _conf,
                                            Element _tag, String _beanName) {
        if (StringUtil.quickEq(_tag.getTagName(),_conf.getRendKeyWords().getKeyWordAnchor()) && _tag.hasAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))) {
//            String href_ = _tag.getAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()));
//            if (_tag.hasAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))) {
                _tag.setAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()), StringUtil.concat(_beanName,DOT,_tag.getAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))));
//            }
//            if (_tag.hasAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))) {
//                _tag.setAttribute(_conf.getRendKeyWords().getAttrHref(), EMPTY_STRING);
//            }
        }
    }
}
