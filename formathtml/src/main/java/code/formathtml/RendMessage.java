package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.formathtml.util.BadElRender;
import code.formathtml.util.RendReadWrite;
import code.formathtml.util.ResultText;
import code.sml.*;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendMessage extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {

    private Element elt;
    private CustList<CustList<RendDynOperationNode>> opExp;

    private String preformatted;
    private BooleanList quoted = new BooleanList();
    private BooleanList escaped = new BooleanList();
    private StringList args = new StringList();
    private Document locDoc;
    RendMessage(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        String value_ = elt.getAttribute(ATTRIBUTE_VALUE);
        StringList elts_ = StringList.splitStrings(value_, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(_cont, var_);
        if (fileName_ == null) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
            return;
        }
        AnalyzingDoc a_ = _cont.getAnalyzingDoc();
        String language_ = a_.getLanguage();
        StringMap<String> files_ = a_.getFiles();
        String[] resourcesFolder_ = a_.getResourcesFolder();
        String content_ = ExtractFromResources.tryGetContent(_cont, language_, fileName_, files_, resourcesFolder_);
        int index_ = ExtractFromResources.indexCorrectMessages(content_);
        if (index_ >= 0) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
            return;
        }
        StringMap<String> messages_ = ExtractFromResources.getMessages(content_);
        String key_ = elts_.last();
        preformatted = ExtractFromResources.getQuickFormat(messages_, key_);
        if (preformatted == null) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
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
            quoted.add(false);
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                escaped.add(true);
            } else {
                escaped.add(false);
            }
            opExp.add(ElRenderUtil.getAnalyzedOperations(attribute_,0,_cont,Calculation.staticCalculation(st_)));
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        if (elt.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
            String lt_ = String.valueOf(LT_BEGIN_TAG);
            String gt_ = String.valueOf(GT_TAG);
            String concat_ = StringList.concat(lt_,TMP_BLOCK_TAG,gt_,preformatted,LT_END_TAG,TMP_BLOCK_TAG,gt_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
            Document docLoc_ = res_.getDocument();
            if (docLoc_ == null) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
            locDoc = docLoc_;
        }
    }

    @Override
    public void reduce(Configuration _context) {
        ResultText.reduce(opExp);
    }

    @Override
    public void processEl(Configuration _cont) {
        int l_ = args.size();
        StringList objects_ = new StringList();
        for (int i = 0; i< l_; i++) {
            if (quoted.get(i)) {
                objects_.add(args.get(i));
                continue;
            }
            Argument arg_ = ElRenderUtil.calculateReuse(opExp.get(i), _cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            String res_;
            if (escaped.get(i)) {
                res_ = escapeParam(_cont,arg_);
            } else {
                res_ = _cont.getAdvStandards().processString(arg_,_cont);
            }
            objects_.add(res_);
        }
        String preRend_;
        preRend_=StringList.simpleStringsFormat(preformatted, objects_);
        if (locDoc == null) {
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
        processBlock(_cont);
    }
}
