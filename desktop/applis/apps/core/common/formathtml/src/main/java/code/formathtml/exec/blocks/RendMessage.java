package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
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
    public void processEl(Configuration _cont) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        StringList anchorArg_ = new StringList();
        for (int i = 0; i< l_; i++) {
            if (quoted.get(i)) {
                objects_.add(args.get(i));
                anchorArg_.add(args.get(i));
                continue;
            }
            Argument arg_ = RenderExpUtil.calculateReuse(opExp.get(i), _cont);
            if (_cont.getContext().callsOrException()) {
                return;
            }
            String res_;
            if (escaped.get(i)) {
                res_ = escapeParam(_cont,arg_);
            } else {
                res_ = _cont.getAdvStandards().processString(arg_,_cont);
            }
            if (_cont.getContext().callsOrException()) {
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
                Node write_ = rend_.getWrite();
                Document doc_ = write_.getOwnerDocument();
                Text t_ = doc_.createTextNode(EMPTY_STRING);
                ((MutableNode)write_).appendChild(t_);
                t_.appendData(preRend_);
                processBlock(_cont);
                return;
            }
        }
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Node oldWrite_ = rw_.getWrite();
        MutableNode root_ = docLoc_.getDocumentElement();
        MutableNode read_ = root_.getFirstChild();
        Document ownerDocument_ = rw_.getDocument();
        _cont.getCallsExps().addAllElts(callsExps.getVal(_cont.getCurrentLanguage()));
        while (true) {
            if (read_ instanceof Element) {
                Element eltRead_ = (Element) read_;
                appendChild(ownerDocument_, rw_.getWrite(), eltRead_);
                MutableNode nextWrite_ = rw_.getWrite().getLastChild();
                Element nextEltWrite_ = (Element) nextWrite_;
                processImportedNode(_cont,ip_, nextEltWrite_);
                if (StringList.quickEq(nextEltWrite_.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())){
                    _cont.getAnchorsArgs().add(anchorArg_);
                    _cont.getAnchorsVars().add(varNames);
                }
                incrAncNb(_cont, nextEltWrite_);
            } else {
                Text txt_ = (Text) read_;
                Text t_ = ownerDocument_.createTextNode(txt_.getTextContent());
                ((MutableNode)rw_.getWrite()).appendChild(t_);
            }
            MutableNode firstChild_ = read_.getFirstChild();
            if (firstChild_ != null) {
                MutableNode nextWrite_ = rw_.getWrite().getLastChild();
                rw_.setWrite(nextWrite_);
                read_ = firstChild_;
                continue;
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
                rw_.setWrite(rw_.getWrite().getParentNode());
                read_ = parentNode_;
            }
            if (stop_) {
                break;
            }
        }
        rw_.setWrite(oldWrite_);
        processBlock(_cont);
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
