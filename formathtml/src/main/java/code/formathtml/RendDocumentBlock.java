package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.BadLabelName;
import code.expressionlanguage.errors.custom.DuplicateLabel;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.methods.FunctionBlock;
import code.formathtml.util.BeanCustLgNames;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.CustList;

public final class RendDocumentBlock extends RendParentBlock implements FunctionBlock {
    private boolean staticContext;
    private Element elt;
    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();
    RendDocumentBlock(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    public void buildFctInstructions(Configuration _cont) {
        beanName = elt.getAttribute(StringList.concat(_cont.getPrefix(),BEAN_ATTRIBUTE));
        setupStaticInfo();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        _cont.setStaticContext(true);
        if (_cont.getBeansInfos().contains(beanName)) {
            _cont.setStaticContext(false);
            page_.setGlobalClass(_cont.getBeansInfos().getVal(beanName).getClassName());
        }
        StringMap<StringList> vars_ = _cont.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        RendBlock root_ = this;
        RendBlock en_ = this;
        CustList<RendParentBlock> parents_ = new CustList<RendParentBlock>();
        CustList<RendBreakableBlock> parentsBreakables_ = new CustList<RendBreakableBlock>();
        CustList<RendLoop> parentsContinuable_ = new CustList<RendLoop>();
        CustList<RendEval> parentsReturnable_ = new CustList<RendEval>();
        StringList labels_ = new StringList();
        while (true) {
            _cont.getAnalyzingDoc().setCurrentBlock(en_);
            if (en_ instanceof RendStdElement) {
                if (StringList.quickEq(((RendStdElement)en_).getRead().getTagName(),BODY_TAG)) {
                    bodies.add(en_);
                }
            }
            checkBreakable(en_,_cont,labels_);
            if (en_ instanceof RendParentBlock) {
                RendBlock first_ = en_.getFirstChild();
                if (first_ == null) {
                    OffsetsBlock off_ = en_.getOffset();
                    RendEmptyInstruction empty_ = new RendEmptyInstruction(off_);
                    ((RendParentBlock)en_).appendChild(empty_);
                }
                _cont.getAnalyzing().initLocalVars();
                _cont.getAnalyzing().initVars();
                _cont.getAnalyzing().initMutableLoopVars();
                _cont.getAnalyzing().initCatchVars();
                if (en_ instanceof RendBreakableBlock) {
                    parentsBreakables_.add((RendBreakableBlock) en_);
                }
                if (en_ instanceof RendLoop) {
                    parentsContinuable_.add((RendLoop) en_);
                }
                if (en_ instanceof RendEval && !(en_ instanceof RendFinallyEval)) {
                    RendBlock ne_ = en_;
                    boolean fin_ = false;
                    while (ne_ instanceof RendEval) {
                        if (ne_ instanceof RendFinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.add((RendEval) en_);
                    }
                }
                parents_.add((RendParentBlock) en_);
            }
            RendBlock n_ = en_.getFirstChild();
            if (en_ != root_) {
                tryBuildExpressionLanguage(en_, _cont,this);
                if (!(en_ instanceof RendForMutableIterativeLoop)) {
                    reduce(en_,_cont);
                }
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                RendParentBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    return;
                }
                if (par_ instanceof RendForMutableIterativeLoop) {
                    ((RendForMutableIterativeLoop)par_).buildIncrementPart(_cont,this);
                    reduce(par_,_cont);
                }
                parents_.removeLast();
                if (par_ instanceof RendBreakableBlock) {
                    parentsBreakables_.removeLast();
                }
                if (par_ instanceof RendLoop) {
                    parentsContinuable_.removeLast();
                }
                if (par_ instanceof RendEval && !(par_ instanceof RendFinallyEval)) {
                    RendBlock ne_ = par_;
                    boolean fin_ = false;
                    while (ne_ instanceof RendEval) {
                        if (ne_ instanceof RendFinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.removeLast();
                    }
                }
                page_.removeLocalVars();
                page_.removeVars();
                page_.removeMutableLoopVars();
                page_.removeCatchVars();
                if (par_ instanceof RendBreakableBlock && !((RendBreakableBlock)par_).getRealLabel().isEmpty()) {
                    labels_.removeLast();
                }
                en_ = par_;
            }
        }
    }

    private void setupStaticInfo() {
        staticContext = beanName.isEmpty();
    }

    static void reduce(RendBlock _block,Configuration _cont) {
        if (_cont.getAdvStandards() instanceof BeanCustLgNames && _block instanceof RendReducableOperations) {
            ((RendReducableOperations)_block).reduce(_cont);
        }
    }
    static void checkBreakable(RendBlock _block,Configuration _conf,StringList _labels) {
        if (_block instanceof RendBreakableBlock) {
            String label_ = ((RendBreakableBlock)_block).getRealLabel();
            boolean wc_ = true;
            for (char c: label_.toCharArray()) {
                if (StringList.isDollarWordChar(c)) {
                    continue;
                }
                wc_ = false;
                break;
            }
            if (!wc_) {
                BadLabelName bad_ = new BadLabelName();
                bad_.setName(label_);
                bad_.setFileName(_conf.getCurrentFileName());
                bad_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(bad_);
            } else if (!label_.isEmpty()){
                if (StringList.contains(_labels, label_)) {
                    DuplicateLabel dup_ = new DuplicateLabel();
                    dup_.setId(label_);
                    dup_.setFileName(_conf.getCurrentFileName());
                    dup_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(dup_);
                } else {
                    _labels.add(label_);
                }
            }
        }
    }
    @Override
    public boolean isStaticContext() {
        return staticContext;
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public CustList<RendBlock> getBodies() {
        return bodies;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
    }
}
