package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;

public final class RendMessage extends RendParentBlock implements RendWithEl, RendReducableOperations {

    private Element elt;
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringMap<String> preformatted;
    private BooleanList quoted = new BooleanList();
    private BooleanList escaped = new BooleanList();
    private StringMap<CustList<CustList<RendDynOperationNode>>> callsExps = new StringMap<CustList<CustList<RendDynOperationNode>>>();
    private StringList args = new StringList();
    private StringMap<Document> locDoc = new StringMap<Document>();
    private StringList varNames = new StringList();


    public RendMessage(int _offsetTrim, Element elt, CustList<CustList<RendDynOperationNode>> opExp, StringMap<String> preformatted, BooleanList quoted, BooleanList escaped, StringMap<CustList<CustList<RendDynOperationNode>>> callsExps, StringList args, StringMap<Document> locDoc, StringList varNames) {
        super(_offsetTrim);
        this.elt = elt;
        this.opExp = opExp;
        this.preformatted = preformatted;
        this.quoted = quoted;
        this.escaped = escaped;
        this.callsExps = callsExps;
        this.args = args;
        this.locDoc = locDoc;
        this.varNames = varNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            if (quoted.get(i)) {
                objects_.add(args.get(i));
                anchorArg_.add(args.get(i));
                continue;
            }
            Argument arg_ = RenderExpUtil.calculateReuse(opExp.get(i), _cont, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            String res_;
            if (escaped.get(i)) {
                res_ = escapeParam(arg_, _stds, _ctx);
            } else {
                res_ = _stds.processString(arg_, _ctx);
            }
            if (_ctx.callsOrException()) {
                return;
            }
            objects_.add(res_);
            anchorArg_.add(res_);
        }
        String preRend_;
        preRend_=StringList.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_);
        CustList<Document> docs_ = new CustList<Document>();
        docs_.add(locDoc.getVal(_cont.getCurrentLanguage()));
        String lt_ = String.valueOf(LT_BEGIN_TAG);
        String gt_ = String.valueOf(GT_TAG);
        String concat_ = StringList.concat(lt_,TMP_BLOCK_TAG,gt_,preRend_,LT_END_TAG,TMP_BLOCK_TAG,gt_);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
        Document docLoc_ = res_.getDocument();
        docs_.add(docLoc_);
        for (Document d: docs_) {
            if (d == null) {
                ImportingPage lastPage_ = _cont.getLastPage();
                RendReadWrite rend_ = lastPage_.getRendReadWrite();
                Document doc_ = rend_.getDocument();
                Text t_ = doc_.createTextNode(EMPTY_STRING);
                simpleAppendChild(doc_,rend_,t_);
                t_.appendData(preRend_);
                processBlock(_cont, _stds, _ctx);
                return;
            }
        }
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Element write_ = rw_.getWrite();
        MutableNode root_ = docLoc_.getDocumentElement();
        MutableNode read_ = root_.getFirstChild();
        Document ownerDocument_ = rw_.getDocument();
        _cont.getFormParts().getCallsExps().addAllElts(callsExps.getVal(_cont.getCurrentLanguage()));
        while (true) {
            if (read_ instanceof Element) {
                Element eltRead_ = (Element) read_;
                Element created_ = appendChild(ownerDocument_, write_, eltRead_);
                processImportedNode(_cont,ip_, created_);
                if (StringList.quickEq(created_.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())){
                    _cont.getFormParts().getAnchorsArgs().add(anchorArg_);
                    _cont.getFormParts().getAnchorsVars().add(varNames);
                }
                incrAncNb(_cont, created_);
                MutableNode firstChild_ = read_.getFirstChild();
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
                MutableNode nextSibling_ = read_.getNextSibling();
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
        processBlock(_cont, _stds, _ctx);
    }

    private void processImportedNode(Configuration _conf,
                                            ImportingPage _ip, Element _tag) {
        String beanName_ = _ip.getBeanName();
        if (StringList.quickEq(_tag.getTagName(),_conf.getRendKeyWords().getKeyWordAnchor())) {
            String href_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()));
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(StringList.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()), StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
            }
            if (_tag.hasAttribute(StringList.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrCommand()))) {
                _tag.setAttribute(_conf.getRendKeyWords().getAttrHref(), EMPTY_STRING);
            }
        }
    }
}
