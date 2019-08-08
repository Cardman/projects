package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public final class RendMessage extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {

    private Element elt;
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringMap<String> preformatted;
    private BooleanList quoted = new BooleanList();
    private BooleanList escaped = new BooleanList();
    private StringMap<CustList<CustList<RendDynOperationNode>>> callsExps = new StringMap<CustList<CustList<RendDynOperationNode>>>();
    private StringList args = new StringList();
    private StringMap<Document> locDoc = new StringMap<Document>();
    private StringList varNames = new StringList();


    RendMessage(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        String value_ = elt.getAttribute(ATTRIBUTE_VALUE);
        preformatted = getPre(_cont,value_);
        if (preformatted.isEmpty()) {
            return;
        }
        boolean st_ = _doc.isStaticContext();
        for (Element n: elt.getChildElements()) {
            String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
            if (n.hasAttribute(ATTRIBUTE_QUOTED)) {
                quoted.add(true);
                if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                    args.add(escapeParam(attribute_));
                    escaped.add(true);
                } else {
                    args.add(attribute_);
                    escaped.add(false);
                }
                opExp.add(new CustList<RendDynOperationNode>());
                continue;
            }
            args.add(EMPTY_STRING);
            quoted.add(false);
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                escaped.add(true);
            } else {
                escaped.add(false);
            }
            opExp.add(RenderExpUtil.getAnalyzedOperations(attribute_,0,_cont,Calculation.staticCalculation(st_)));
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        if (elt.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
            String lt_ = String.valueOf(LT_BEGIN_TAG);
            String gt_ = String.valueOf(GT_TAG);
            int l_ = opExp.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
            }
            varNames = varNames_;
            for (String v:varNames_) {
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(_cont.getStandards().getAliasPrimInteger());
                _cont.getLocalVarsAna().last().addEntry(v,lv_);
                formArg_.add(StringList.concat("(", BeanCustLgNames.sufficLocal(_cont.getContext(),v),")"));
            }
            for (EntryCust<String,String> e: preformatted.entryList()) {
                String preRend_;
                String concat_ = StringList.concat(lt_,TMP_BLOCK_TAG,gt_,e.getValue(),LT_END_TAG,TMP_BLOCK_TAG,gt_);
                preRend_=StringList.simpleStringsFormat(concat_, formArg_);
                DocumentResult res2_ = DocumentBuilder.parseSaxNotNullRowCol(preRend_);
                Document docLoc2_ = res2_.getDocument();
                CustList<CustList<RendDynOperationNode>> callExpsLoc_ = new CustList<CustList<RendDynOperationNode>>();
                if (docLoc2_ != null) {
                    for (Element a: docLoc2_.getElementsByTagName(TAG_A)){
                        String href_ = a.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
                        if (href_.startsWith(CALL_METHOD)) {
                            if (href_.indexOf('(') == CustList.INDEX_NOT_FOUND_ELT) {
                                href_ = StringList.concat(href_,"()");
                            }
                            CustList<RendDynOperationNode> expsCall_ = RenderExpUtil.getAnalyzedOperations(href_, 1, _cont, Calculation.staticCalculation(st_));
                            callExpsLoc_.add(expsCall_);
                        } else {
                            callExpsLoc_.add(new CustList<RendDynOperationNode>());
                        }
                    }
                }
                DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
                Document docLoc_ = res_.getDocument();
                if (docLoc_ == null) {
                    BadElError badEl_ = new BadElError();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
                callsExps.addEntry(e.getKey(),callExpsLoc_);
                locDoc.addEntry(e.getKey(),docLoc_);
            }
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }

        }
    }

    @Override
    public void reduce(Configuration _context) {
        ResultText.reduce(opExp);
        for (EntryCust<String,CustList<CustList<RendDynOperationNode>>> e: callsExps.entryList()) {
            ResultText.reduce(e.getValue());
        }
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
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            String res_;
            if (escaped.get(i)) {
                res_ = escapeParam(_cont,arg_);
            } else {
                res_ = _cont.getAdvStandards().processString(arg_,_cont);
            }
            if (_cont.getContext().hasExceptionOrFailInit()) {
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
                if (StringList.quickEq(nextEltWrite_.getTagName(), TAG_A)){
                    _cont.getAnchorsArgs().add(anchorArg_);
                    _cont.getAnchorsVars().add(varNames);
                    _cont.getConstAnchors().add(false);
                    _cont.getAnchorsNames().add(EMPTY_STRING);
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
        if (StringList.quickEq(_tag.getTagName(),TAG_A)) {
            String href_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND));
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND), StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
            }
            if (_tag.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND))) {
                _tag.setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
            }
        }
    }
}
