package code.gui.document;

import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class RenderedPage implements ProcessingSession {

    private final AbsCompoFactory compoFactory;
    private DualPanel page;
    private final AbsScrollPane scroll;
    private Navigation navigation;
    private final IdMap<MetaComponent,DualComponent> refs = new IdMap<MetaComponent,DualComponent>();
    private FindEvent finding;

    private final AbstractAtomicBoolean processing;

    private AbstractThread threadAction;

    private CustList<AbstractImage> process = new CustList<AbstractImage>();

    private AbsTextArea area;

    private ProgressingWebDialog dialog;

    private ChangeableTitle frame;

    private ContextEl context;

    private AbstractContextCreator contextCreator;
    private BeanLgNames standards;

    private final CustList<DualAnimatedImage> anims = new CustList<DualAnimatedImage>();

    private AbsPlainButton find;
    private AbsTextField field;
    private final AbstractProgramInfos gene;

    public RenderedPage(AbsScrollPane _frame, AbstractProgramInfos _gene) {
        scroll = _frame;
        gene = _gene;
        compoFactory = _gene.getCompoFactory();
        processing = _gene.getThreadFactory().newAtomicBoolean();
    }

    public void initNav() {
        navigation = new Navigation();
        navigation.setSession(new Configuration());
    }

    public void setFiles(StringMap<String> _web) {
        navigation.setFiles(_web);
    }

    public void setLanguage(String _language) {
        navigation.setLanguage(_language);
        navigation.setLanguages(Constants.getAvailableLanguages());
    }

    public void setLanguage(String _language, StringList _languages) {
        navigation.setLanguage(_language);
        navigation.setLanguages(_languages);
    }

    public void setProcess(CustList<AbstractImage> _process) {
        process = _process;
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public void initialize(Navigation _nav,MetaDocument _metaDoc) {
        navigation = _nav;
        FrameUtil.invokeLater(new WindowPage(_metaDoc, scroll, this), getGene());
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public void initialize(PreparedAnalyzed _stds) {
        standards = _stds.getBeanNatLgNames();
        start();
        navigation = _stds.getNavigation();
        contextCreator = new NativeContextCreator();
        NatRendStackCall _rendStackCall = new NatRendStackCall();
        String textToBeChanged_ = _stds.getBeanNatLgNames().initializeRendSessionDoc(navigation, _rendStackCall);
        navigation.setupText(textToBeChanged_, standards, _rendStackCall.getDocument(), _rendStackCall.getHtmlPage());
        setupText();
    }

    public void initializeOnlyConf(PreparedAnalyzed _prepared, String _lg) {
        navigation = _prepared.getNavigation();
        navigation.setLanguage(_lg);
        standards = _prepared.getBeanNatLgNames();
        contextCreator = new NativeContextCreator();
        ThreadDirectActions th_ = new ThreadDirectActions(this);
        threadAction = gene.getThreadFactory().newThread(th_);
        threadAction.start();
        animateProcess();
    }

    public void initializeOnlyConf(AbstractContextCreator _creator,BeanCustLgNames _stds, Runnable _inst) {
        if (processing.get()) {
            return;
        }
        start();
        standards = _stds;
        contextCreator = _creator;
        threadAction = gene.getThreadFactory().newThread(_inst);
        threadAction.start();
        animateProcess();
    }

    void animateProcess() {
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(gene.getThreadFactory(),this, process, frame, dialog);
            gene.getThreadFactory().newStartedThread(load_);
        }
    }

    public void refresh() {
//        if (processing.get()) {
//            return;
//        }
//        if (!(standards instanceof BeanNatLgNames)) {
//            return;
//        }
//        threadAction = gene.getThreadFactory().newThread(new ThreadRefresh(this, (BeanNatLgNames) standards));
//        threadAction.start();
//        animateProcess();
    }

    public void start() {
        processing.set(true);
    }

    void finish() {
        //boolean _stop
        processing.set(false);
    }

    private void setupText() {
        if (!processing.get()) {
            return;
        }
        processing.set(false);
        Document doc_ = navigation.getDocument();
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_,navigation.getSession().getRendKeyWords());
        FrameUtil.invokeLater(new WindowPage(metadoc_, scroll, this), getGene());
    }
    void directScroll(MetaDocument _meta) {
        if (frame != null) {
            if (!navigation.getTitle().isEmpty()) {
                frame.setTitle(navigation.getTitle());
            }
        }
        String ref_ = navigation.getReferenceScroll();
        if (!ref_.isEmpty()) {
            MetaAnchorLabel lab_ = _meta.getAnchorsRef().getVal(ref_);
            DualComponent c_ = getRefs().getVal(lab_);
            DualComponent r_ = page;
            int x_ = 0;
            int y_ = 0;
            while (c_ != r_) {
                x_ += c_.getGraphic().getXcoords();
                y_ += c_.getGraphic().getYcoords();
                c_ = c_.getContainer();
            }
            scroll.setHorizontalValue(x_);
            scroll.setVerticalValue(y_);
        }
        if (frame != null) {
            frame.pack();
        }
        finish();
    }
    public AbsTextArea getArea() {
        return area;
    }
    @Override
    public boolean isProcessing() {
        return processing.get();
    }
    void setFinding(MetaDocument _document) {
        if (finding == null) {
            return;
        }
        finding.setFinding(_document);
    }

    void setPage(DualPanel _page) {
        page = _page;
    }
    IdMap<MetaComponent, DualComponent> getRefs() {
        return refs;
    }
    public Navigation getNavigation() {
        return navigation;
    }
    public AbsScrollPane getScroll() {
        return scroll;
    }
    DualPanel getPage() {
        return page;
    }

    public void setDialog(ProgressingWebDialog _dialog) {
        dialog = _dialog;
    }

    public void setFrame(ChangeableTitle _frame) {
        frame = _frame;
    }

    public void setArea(AbsTextArea _area) {
        area = _area;
    }

    public BeanLgNames getStandards() {
        return standards;
    }

    CustList<DualAnimatedImage> getAnims() {
        return anims;
    }

    public void setSearchText(AbsPlainButton _search) {
        find = _search;
    }

    public void setField(AbsTextField _field) {
        field = _field;
    }

    public void addFinder() {
        finding = new FindEvent(field, this);
        find.addActionListener(finding);
    }

    public ContextEl getContext() {
        return context;
    }

    public void setContext(ContextEl _context) {
        this.context = _context;
    }

    public AbstractContextCreator getContextCreator() {
        return contextCreator;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbstractProgramInfos getGene() {
        return gene;
    }
}
