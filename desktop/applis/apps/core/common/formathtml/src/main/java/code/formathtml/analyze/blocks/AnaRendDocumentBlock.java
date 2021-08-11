package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.structs.BeanInfo;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendDocumentBlock extends AnaRendParentBlock implements AccessedBlock,AccessingImportingBlock {

    private final Element elt;

    private final String file;
    private final String fileName;
    private String beanName;
    private final CustList<AnaRendBlock> bodies = new CustList<AnaRendBlock>();
    private StringList imports = new StringList();
    public AnaRendDocumentBlock(Element _elt, String _file, int _offset, String _fileName) {
        super(_offset);
        elt = _elt;
        file = _file;
        fileName = _fileName;
    }

    public void buildFctInstructions(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, StringMap<BeanInfo> _beansInfosBefore) {
        beanName = elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean()));
        imports = StringUtil.splitChar(elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrAlias())),';');
        _page.setGlobalOffset(getOffset());
        _page.zeroOffset();
        _page.setAccessStaticContext(MethodAccessKind.STATIC);
        _page.setCurrentPkg("");
        _page.setCurrentFile(null);
        if (_beansInfosBefore.contains(beanName)) {
            _page.setAccessStaticContext(MethodAccessKind.INSTANCE);
            String clName_ = _beansInfosBefore.getVal(beanName).getResolvedClassName();
            AnaFormattedRootBlock globalType_ = new AnaFormattedRootBlock(_page, clName_);
            _page.setGlobalType(globalType_);
            _page.setImporting(globalType_.getRootBlock());
        } else {
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
            _page.setImporting(null);
        }
        AnaRendBlock en_ = this;
        CustList<AnaRendParentBlock> parents_ = new CustList<AnaRendParentBlock>();
        CustList<AnaRendLocBreakableBlock> parentsBreakables_ = new CustList<AnaRendLocBreakableBlock>();
        CustList<AnaRendLoop> parentsContinuable_ = new CustList<AnaRendLoop>();
        CustList<AnaRendEval> parentsReturnable_ = new CustList<AnaRendEval>();
        StringList labels_ = new StringList();
        _anaDoc.setFileName(fileName);
        _anaDoc.setCurrentDoc(this);
        while (true) {
            _anaDoc.setCurrentBlock(en_);
            if (en_ instanceof ImportForEachLoop) {
                _page.setCurrentAnaBlockForEachLoop((ImportForEachLoop) en_);
            } else {
                _page.setCurrentAnaBlockForEachLoop(null);
            }
            if (en_ instanceof ImportForEachTable) {
                _page.setCurrentAnaBlockForEachTable((ImportForEachTable) en_);
            } else {
                _page.setCurrentAnaBlockForEachTable(null);
            }
            if (en_ instanceof AnaRendStdElement) {
                if (StringUtil.quickEq(((AnaRendStdElement)en_).getRead().getTagName(),_anaDoc.getRendKeyWords().getKeyWordBody())) {
                    bodies.add(en_);
                }
            }
            checkBreakable(en_, labels_, _anaDoc, _page);
            if (en_ instanceof AnaRendParentBlock) {
                if (en_ instanceof AnaRendLocBreakableBlock) {
                    parentsBreakables_.add((AnaRendLocBreakableBlock) en_);
                }
                if (en_ instanceof AnaRendLoop) {
                    parentsContinuable_.add((AnaRendLoop) en_);
                }
                if (en_ instanceof AnaRendEval && !(en_ instanceof AnaRendFinallyEval)) {
                    AnaRendBlock ne_ = en_;
                    boolean fin_ = false;
                    while (ne_ instanceof AnaRendEval) {
                        if (ne_ instanceof AnaRendFinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.add((AnaRendEval) en_);
                    }
                }
                parents_.add((AnaRendParentBlock) en_);
            }
            AnaRendBlock n_ = en_.getFirstChild();
            tryBuildExpressionLanguage(en_, this, _anaDoc, _page);
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
                AnaRendParentBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    par_.removeAllVars(_page);
                    return;
                }
                parents_.removeLast();
                if (par_ instanceof AnaRendLocBreakableBlock) {
                    parentsBreakables_.removeLast();
                }
                if (par_ instanceof AnaRendLoop) {
                    parentsContinuable_.removeLast();
                }
                if (par_ instanceof AnaRendEval && !(par_ instanceof AnaRendFinallyEval)) {
                    AnaRendBlock ne_ = par_;
                    boolean fin_ = false;
                    while (ne_ instanceof AnaRendEval) {
                        if (ne_ instanceof AnaRendFinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.removeLast();
                    }
                }
                par_.removeAllVars(_page);
                if (par_ instanceof AnaRendLocBreakableBlock && !((AnaRendLocBreakableBlock)par_).getRealLabel().isEmpty()) {
                    labels_.removeLast();
                }
                en_ = par_;
            }
        }
    }
    @Override
    public StringList getFileImports() {
        return getImports();
    }

    @Override
    public boolean isTypeHidden(Accessed _class, AnalyzedPageEl _analyzable) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

    @Override
    public StringList getImports() {
        return imports;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        //
    }

    private static void checkBreakable(AnaRendBlock _block, StringList _labels, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_block instanceof AnaRendLocBreakableBlock) {
            String label_ = ((AnaRendLocBreakableBlock)_block).getRealLabel();
            boolean wc_ = true;
            for (char c: label_.toCharArray()) {
                if (StringExpUtil.isDollarWordChar(c)) {
                    continue;
                }
                wc_ = false;
                break;
            }
            if (!wc_) {
                FoundErrorInterpret bad_ = new FoundErrorInterpret();
                bad_.setFileName(_anaDoc.getFileName());
                bad_.setIndexFile(((AnaRendLocBreakableBlock) _block).getRealLabelOffset());
                bad_.buildError(_page.getAnalysisMessages().getBadLabel());
                AnalyzingDoc.addError(bad_, _anaDoc, _page);
            } else if (!label_.isEmpty()){
                if (StringUtil.contains(_labels, label_)) {
                    FoundErrorInterpret dup_ = new FoundErrorInterpret();
                    dup_.setFileName(_anaDoc.getFileName());
                    dup_.setIndexFile(((AnaRendLocBreakableBlock) _block).getRealLabelOffset());
                    dup_.buildError(_page.getAnalysisMessages().getDuplicatedLabel());
                    AnalyzingDoc.addError(dup_, _anaDoc, _page);
                } else {
                    _labels.add(label_);
                }
            }
        }
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

}
