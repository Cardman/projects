package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.common.AbstractFileEscapedCalc;
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

public final class AnaRendDocumentBlock extends AnaRendParentBlock implements AccessedFct,AccessedBlockMembers,WithContext {
    private final StringList allReservedInners = new StringList();
    private final CustList<RootBlock> localTypes = new CustList<RootBlock>();
    private final CustList<AnonymousTypeBlock> anonymousTypes = new CustList<AnonymousTypeBlock>();
    private int countsAnonFct;

    private final Element elt;

    private final String file;
    private final String fileName;
    private final AbstractFileEscapedCalc esc;
    private final FileBlock fileBlock;
    private String beanName;
    private StringList imports = new StringList();
    private final IntTreeMap<Integer> escapedChar;
    private MethodAccessKind accessKind;
    private AnaFormattedRootBlock declClass = AnaFormattedRootBlock.defValue();
    private final CustList<RootBlock> reserved = new CustList<RootBlock>();
    private final CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> anonymousFct = new CustList<NamedCalledFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    public AnaRendDocumentBlock(Element _elt, String _file, int _offset, String _fileName, CustList<EncodedChar> _chars) {
        super(_offset);
        IntTreeMap<Integer> escaped_ = getIndexesSpecChars(_file, _chars);
        esc = new AdvFileEscapedCalc(escaped_);
        fileBlock = new FileBlock(_offset, false, _fileName, esc);
        elt = _elt;
        file = _file;
        fileName = _fileName;
        escapedChar = escaped_;
    }

    public IntTreeMap<Integer> getEscapedChar() {
        return escapedChar;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return accessKind;
    }

    public void initMetrics(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, StringMap<BeanInfo> _beansInfosBefore) {
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
        setBeanName(elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        String clName_ = getResolvedClassName(_beansInfosBefore);
        AnaFormattedRootBlock globalType_ = new AnaFormattedRootBlock(_page, clName_);
        RootBlock rootBlock_ = globalType_.getRootBlock();
        if (rootBlock_ != null) {
            accessKind = MethodAccessKind.INSTANCE;
            declClass = globalType_;
        } else {
            accessKind = MethodAccessKind.STATIC;
        }
    }

    public void buildFctInstructions(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(getOffset());
        _page.zeroOffset();
        _page.setAccessStaticContext(MethodAccessKind.STATIC);
        _page.setCurrentPkg("");
        _page.setCurrentFile(fileBlock);
        _page.setCurrentFct(null);
        _page.setCurrentCtx(this);
        RootBlock rootBlock_ = declClass.getRootBlock();
        if (rootBlock_ != null) {
            _page.setAccessStaticContext(MethodAccessKind.INSTANCE);
            _page.setGlobalType(declClass);
            _page.setImporting(rootBlock_);
            _page.setImportingTypes(this);
            _page.setImportingAcces(new TypeAccessor(rootBlock_.getFullName()));
        } else {
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
            _page.setImporting(this);
            _page.setImportingTypes(this);
            _page.setImportingAcces(new OperatorAccessor());
        }
        _page.setAccessedFct(this);
        AnaRendBlock en_ = this;
        StringList labels_ = new StringList();
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

    private String getResolvedClassName(StringMap<BeanInfo> _beansInfosBefore) {
        BeanInfo val_ = _beansInfosBefore.getVal(beanName);
        if (val_ == null) {
            return "";
        }
        return StringUtil.nullToEmpty(val_.getResolvedClassName());
    }

    @Override
    public FileBlock getFile() {
        return fileBlock;
    }

    @Override
    public StringList getFileImports() {
        return getImports();
    }

    @Override
    public StringList getImports() {
        return imports;
    }

    @Override
    public CustList<AnonymousTypeBlock> getAnonymousTypes() {
        return anonymousTypes;
    }

    @Override
    public CustList<RootBlock> getLocalTypes() {
        return localTypes;
    }

    @Override
    public CustList<AnonymousTypeBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<RootBlock> getReserved() {
        return reserved;
    }

    @Override
    public CustList<NamedCalledFunctionBlock> getAnonymousFct() {
        return anonymousFct;
    }

    @Override
    public CustList<SwitchMethodBlock> getSwitchMethods() {
        return switchMethods;
    }

    @Override
    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    @Override
    public StringMap<MappingLocalType> getRefMappings() {
        return new StringMap<MappingLocalType>();
    }

    @Override
    public int getCountsAnonFct() {
        return countsAnonFct;
    }

    @Override
    public void setCountsAnonFct(int _v) {
        this.countsAnonFct = _v;
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

    public String getFileName() {
        return fileName;
    }

    public AbstractFileEscapedCalc getEsc() {
        return esc;
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
