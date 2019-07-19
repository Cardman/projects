package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendSubmit extends RendElement {
    private StringMap<CustList<RendDynOperationNode>> args = new StringMap<CustList<RendDynOperationNode>>();
    private StringMap<String> parts = new StringMap<String>();
    private String preformatted;
    RendSubmit(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        _list.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
        String value_ = _read.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
        StringList elts_ = StringList.splitStrings(value_, COMMA);
        String var_ = elts_.first();
        String fileName_ = ExtractObject.getProperty(_cont, var_);
        if (fileName_ == null) {
            fileName_ = var_;
        }
        AnalyzingDoc a_ = _cont.getAnalyzingDoc();
        String language_ = a_.getLanguage();
        StringMap<String> files_ = a_.getFiles();
        String[] resourcesFolder_ = a_.getResourcesFolder();
        String content_ = ExtractFromResources.tryGetContent(_cont, language_, fileName_, files_, resourcesFolder_);
        if (content_ == null) {
            return;
        }
        int index_ = ExtractFromResources.indexCorrectMessages(content_);
        if (index_ >= 0) {
            return;
        }
        StringMap<String> messages_ = ExtractFromResources.getMessages(content_);
        String key_ = elts_.last();
        preformatted = ExtractFromResources.getFormat(messages_, key_, _cont, language_, fileName_);
        if (preformatted == null) {
            return;
        }
        preformatted = DocumentBuilder.transformSpecialChars(preformatted, _read.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
            String attribute_ = _read.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
            if (attribute_.startsWith(CALL_METHOD)) {
                args.put(StringList.concat(TAG_PARAM,Long.toString(i_)),ElRenderUtil.getAnalyzedOperations(attribute_,1,_cont,Calculation.staticCalculation(_doc.isStaticContext())));
            } else {
                parts.put(StringList.concat(TAG_PARAM,Long.toString(i_)),attribute_);
            }
            i_++;
        }
        _list.removeAllString(ATTRIBUTE_VALUE);
        _list.removeAllString(ATTRIBUTE_TYPE);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        if (preformatted == null) {
            return;
        }
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
        curWr_.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
        StringList objects_ = new StringList();
        BeanLgNames stds_ = _cont.getAdvStandards();

        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
            if (args.contains(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
                ip_.setProcessingAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                ip_.setLookForAttrValue(true);
                ip_.setOffset(1);
                CustList<RendDynOperationNode> o_ = args.getVal(StringList.concat(TAG_PARAM, Long.toString(i_)));
                Argument argument_ = ElRenderUtil.calculateReuse(o_, _cont);
                if (_cont.getContext().hasExceptionOrFailInit()) {
                    return;
                }
                objects_.add(stds_.processString(argument_,_cont));
                if (_cont.getContext().hasExceptionOrFailInit()) {
                    return;
                }
            } else {
                objects_.add(parts.getVal(StringList.concat(TAG_PARAM, Long.toString(i_))));
            }
            curWr_.removeAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
            i_++;
        }
        curWr_.setAttribute(ATTRIBUTE_VALUE, StringList.simpleStringsFormat(preformatted, objects_));
        curWr_.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
        ownerDocument_.renameNode(curWr_, INPUT_TAG);
    }
}
