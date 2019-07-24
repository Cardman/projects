package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class RendElement extends RendParentBlock implements RendWithEl, RendReducableOperations, RendBuildableElMethod {
    private static final char ESCAPED = '\\';
    private Element read;
    private StringMap<CustList<RendDynOperationNode>> attributes = new StringMap<CustList<RendDynOperationNode>>();
    private StringMap<CustList<CustList<RendDynOperationNode>>> attributesId = new StringMap<CustList<CustList<RendDynOperationNode>>>();
    private StringMap<String> attributesConst = new StringMap<String>();
    private StringMap<StringList> idTexts = new StringMap<StringList>();
    RendElement(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        String prefixWrite_ = _cont.getPrefix();
//        String beanName_ = _ip.getBeanName();
        StringList attributesNames_ = new StringList();
        StringList allAttributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        allAttributesNames_.addAllElts(attributesNames_);
        attributesNames_.removeAllString(ATTRIBUTE_ID);
        String id_ = read.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            attributesId.put(ATTRIBUTE_ID,new CustList<CustList<RendDynOperationNode>>());
            idTexts.put(ATTRIBUTE_ID,new StringList());
            setupIds(ATTRIBUTE_ID,id_,_cont,_doc);
        }
        String prefGr_ = StringList.concat(prefixWrite_, ATTRIBUTE_GROUP_ID);
        String groupId_ = read.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            attributesId.put(groupId_,new CustList<CustList<RendDynOperationNode>>());
            idTexts.put(groupId_,new StringList());
            setupIds(groupId_,id_,_cont,_doc);
        }
        processAttributes(_cont,_doc,read,allAttributesNames_,attributesNames_);
    }

    protected abstract void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list);

    public final Element getRead() {
        return read;
    }

    private void setupIds(String _key, String _text, Configuration _cont, RendDocumentBlock _doc) {
        CustList<CustList<RendDynOperationNode>> list_ = attributesId.getVal(_key);
        StringList txts_ = idTexts.getVal(_key);
        StringBuilder calculateVariables_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int len_ = _text.length();
        boolean escaped_ = false;
        while (i_ < len_) {
            char curChar_ = _text.charAt(i_);
            if (escaped_) {
                if (curChar_ == ESCAPED) {
                    escaped_ = false;
                    calculateVariables_.append(ESCAPED);
                    i_++;
                    continue;
                }
                if (curChar_ == LEFT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(LEFT_EL);
                    i_++;
                    continue;
                }
                if (curChar_ == RIGHT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(RIGHT_EL);
                    i_++;
                    continue;
                }
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getContext().getClasses().addError(badEl_);
                return;
            }
            if (curChar_ == ESCAPED) {
                escaped_ = true;
                i_++;
                continue;
            }
            if (curChar_ == LEFT_EL) {
                txts_.add(calculateVariables_.toString());
                calculateVariables_.delete(0,calculateVariables_.length());
                CustList<RendDynOperationNode> ops_ = ElRenderUtil.getAnalyzedOperations(_text, _cont, i_ + 1, LEFT_EL, RIGHT_EL, Calculation.staticCalculation(_doc.isStaticContext()));
                list_.add(ops_);
//                calculateVariables_.append(ExtractObject.valueOf(_cont, arg_.getStruct()));
//                if (_cont.getContext().getException() != null) {
//                    return;
//                }
                i_ = _cont.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
        txts_.add(calculateVariables_.toString());
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Node write_ = rw_.getWrite();
        if (ip_.hasBlock()) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeRendLastBlock();
                processBlock(_cont);
                return;
            }
        }
        Document ownerDocument_ = rw_.getDocument();
        FormatHtml.appendChild(ownerDocument_,_cont,write_,read);
        MutableNode nextWrite_ = write_.getLastChild();
        processExecAttr(_cont,nextWrite_,read);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        rw_.setRead(getFirstChild());
        rw_.setWrite(nextWrite_);
    }

    protected abstract void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read);

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(this);
    }

}
