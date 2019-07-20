package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.RendReadWrite;
import code.sml.Document;
import code.sml.MutableNode;
import code.sml.Node;
import code.sml.Text;
import code.util.CustList;
import code.util.StringList;

public final class RendText extends RendLeaf implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private static final char SEP_TR = ',';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private static final char ESCAPED = '\\';
    private final String expression;

    private int expressionOffset;

    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();

    RendText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _conf,RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        boolean st_ = _doc.isStaticContext();
        StringBuilder str_ = new StringBuilder();
        int length_ = expression.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = expression.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (expression.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                StringBuilder tr_ = new StringBuilder();
                int indexSepTr_ = expression.indexOf(SEP_TR, i_ + 1);
                boolean processTr_ = false;
                if (i_ + 1 < length_ && indexSepTr_ != CustList.INDEX_NOT_FOUND_ELT) {
                    boolean allWord_ = true;
                    boolean existWord_ = false;
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            break;
                        }
                        if (j_ > i_+1 && !StringList.isWordChar(expression.charAt(j_))) {
                            allWord_ = false;
                            break;
                        }
                        if (StringList.isWordChar(expression.charAt(j_))) {
                            existWord_ = true;
                        }
                        j_++;
                    }
                    if (!existWord_) {
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().addError(badEl_);
                        return;
                    }
                    processTr_ = allWord_;
                }
                if (processTr_) {
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            j_++;
                            i_ = j_;
                            break;
                        }
                        j_++;
                        tr_.append(expression.charAt(j_));
                    }
                    tr_.deleteCharAt(tr_.length()-1);
                } else {
                    i_++;
                }
                if (i_ >= length_ || expression.charAt(i_) == RIGHT_EL) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(badEl_);
                    return;
                }
//                Struct trloc_ = null;
//                if (!tr_.toString().isEmpty()) {
//                    trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
//                    if (trloc_ == null) {
//                        _conf.getLastPage().setOffset(i_);
//                        _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
//                        return EMPTY_STRING;
//                    }
//                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = ElRenderUtil.getAnalyzedOperations(expression, _conf, i_, LEFT_EL, RIGHT_EL, Calculation.staticCalculation(st_));
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(badEl_);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    @Override
    public void reduce(Configuration _context) {
        int s_ = opExp.size();
        for (int i = 0; i < s_; i++) {
            opExp.set(i,ElRenderUtil.getReducedNodes(opExp.get(i).last()));
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage lastPage_ = _cont.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Node write_ = rend_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        ((MutableNode)write_).appendChild(t_);
        int s_ = opExp.size();
        BeanLgNames standards_ = _cont.getAdvStandards();
        for (int i = 0; i < s_; i++) {
            t_.appendData(texts.get(i));
            CustList<RendDynOperationNode> exp_ = opExp.get(i);
            Argument argument_ = ElRenderUtil.calculateReuse(exp_, _cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            t_.appendData(standards_.processString(argument_,_cont));
        }
        t_.appendData(texts.last());
        processBlock(_cont);
    }
}
