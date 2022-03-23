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
import code.formathtml.common.AdvFileEscapedCalc;
import code.formathtml.structs.BeanInfo;
import code.sml.Element;
import code.sml.EncodedChar;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendDocumentBlock extends AnaRendParentBlock implements AccessedBlock,AccessingImportingBlock,WithContext {

    private final int nb;
    private final Element elt;

    private final String file;
    private final String fileName;
    private final FileBlock fileBlock;
    private String beanName;
    private StringList imports = new StringList();
    private final IntTreeMap<Integer> escapedChar;
    private MethodAccessKind accessKind;
    private AnaFormattedRootBlock declClass = AnaFormattedRootBlock.defValue();
    public AnaRendDocumentBlock(int _n, Element _elt, String _file, int _offset, String _fileName, CustList<EncodedChar> _chars) {
        super(_offset);
        this.nb = _n;
        IntTreeMap<Integer> escaped_ = getIndexesSpecChars(_file, _chars);
        fileBlock = new FileBlock(_offset, false, _fileName, new AdvFileEscapedCalc(escaped_));
        elt = _elt;
        file = _file;
        fileName = _fileName;
        escapedChar = escaped_;
    }

    public int getNb() {
        return nb;
    }

    public IntTreeMap<Integer> getEscapedChar() {
        return escapedChar;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return accessKind;
    }

    public void initMetrics(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String alias_ = StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrAlias());
        imports = StringUtil.splitChar(elt.getAttribute(alias_),';');
        fileBlock.setNumberFile(_page.getFilesBodies().size());
        _page.putFileBlock(fileName, fileBlock);
        fileBlock.processLinesTabsWithError(file, _page);
        int o_ = getAttributeDelimiter(alias_);
        for (String o: imports) {
            fileBlock.getImports().add(o);
            fileBlock.getImportsOffset().add(o_);
            o_ += o.length() +1;
        }
    }

    public void buildFctInstructions(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, StringMap<BeanInfo> _beansInfosBefore) {
        setBeanName(elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        _page.setGlobalOffset(getOffset());
        _page.zeroOffset();
        _page.setAccessStaticContext(MethodAccessKind.STATIC);
        _page.setCurrentPkg("");
        _page.setCurrentFile(fileBlock);
        _page.setCurrentFct(null);
        _page.setCurrentCtx(this);
        if (_beansInfosBefore.contains(beanName)) {
            accessKind = MethodAccessKind.INSTANCE;
            _page.setAccessStaticContext(MethodAccessKind.INSTANCE);
            String clName_ = _beansInfosBefore.getVal(beanName).getResolvedClassName();
            AnaFormattedRootBlock globalType_ = new AnaFormattedRootBlock(_page, clName_);
            _page.setGlobalType(globalType_);
            _page.setImporting(globalType_.getRootBlock());
            declClass = globalType_;
        } else {
            accessKind = MethodAccessKind.STATIC;
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
            _page.setImporting(null);
        }
        AnaRendBlock en_ = this;
        StringList labels_ = new StringList();
        _page.setImportingAcces(this);
        _page.getImportingTypes().clear();
        _page.getImportingTypes().add(getImports());
        _page.getImportingTypes().add(getFileImports());
        while (true) {
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
            checkBreakable(en_, labels_, _anaDoc, _page);
            AnaRendBlock n_ = en_.getFirstChild();
            if (en_ instanceof AnaRendBuildEl) {
                ((AnaRendBuildEl)en_).buildExpressionLanguage(this, _anaDoc, _page);
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
                AnaRendParentBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    par_.removeAllVars(_page);
                    return;
                }
                par_.removeAllVars(_page);
                if (par_ instanceof AnaRendLocBreakableBlock && !((AnaRendLocBreakableBlock)par_).getRealLabel().isEmpty()) {
                    labels_.removeLast();
                }
                en_ = par_;
            }
        }
    }

    public FileBlock getFileBlock() {
        return fileBlock;
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
                bad_.setFile(_page.getCurrentFile());
                bad_.setIndexFile(((AnaRendLocBreakableBlock) _block).getRealLabelOffset());
                bad_.buildError(_page.getAnalysisMessages().getBadLabel());
                AnalyzingDoc.addError(bad_, _page);
            } else if (!label_.isEmpty()){
                if (StringUtil.contains(_labels, label_)) {
                    FoundErrorInterpret dup_ = new FoundErrorInterpret();
                    dup_.setFile(_page.getCurrentFile());
                    dup_.setIndexFile(((AnaRendLocBreakableBlock) _block).getRealLabelOffset());
                    dup_.buildError(_page.getAnalysisMessages().getDuplicatedLabel());
                    AnalyzingDoc.addError(dup_, _page);
                } else {
                    _labels.add(label_);
                }
            }
        }
    }

    public AnaFormattedRootBlock getDeclClass() {
        return declClass;
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        this.beanName = _beanName;
    }

}
