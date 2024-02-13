package code.gui.document;

import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaDocument;
import code.formathtml.render.MetaSearchableLabel;
import code.sml.WithPageInfos;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.sml.NavigationCore;
import code.sml.RendKeyWordsGroup;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.*;
import code.util.ints.CharacterCaseConverter;

public final class RenderedPage implements ProcessingSession {

    private static final int DELTA = 100;
    private final AbsCompoFactory compoFactory;
    private DualPanel page;
    private final AbsScrollPane scroll;
    private RendKeyWordsGroup keys;
    private NavigationCore navCore;
    private final IdMap<MetaComponent,DualComponent> refs = new IdMap<MetaComponent,DualComponent>();
    private FindEvent finding;

    private final AbstractAtomicBoolean processing;

    private CustList<AbstractImage> process = new CustList<AbstractImage>();

    private AbsTextArea area;

    private ProgressDialogAdv dialog;

    private ChangeableTitle frame;

    private AbstractRenderAction renderAction;
    private WithPageInfos standards;

    private final CustList<DualAnimatedImage> anims = new CustList<DualAnimatedImage>();

    private AbsButton find;
    private AbsTextField field;
    private final AbstractProgramInfos gene;
    private AbstractScheduledExecutorService timer;
    private AbstractFuture taskTimer;
    private String keyWordDigit = "ABCDEF";
    private final CharacterCaseConverter converter;

    public RenderedPage(AbsScrollPane _frame, AbstractProgramInfos _gene, CharacterCaseConverter _ccc) {
        scroll = _frame;
        gene = _gene;
        compoFactory = _gene.getCompoFactory();
        processing = _gene.getThreadFactory().newAtomicBoolean();
        converter = _ccc;
    }

    public void initNav(NavigationCore _core, RendKeyWordsGroup _k) {
        navCore = _core;
        keys = _k;
    }

    public void setFiles(StringMap<String> _web) {
        navCore.setFiles(_web);
    }

    public void setLanguage(String _language, StringList _languages) {
        navCore.setLanguage(_language);
        navCore.setLanguages(_languages);
    }

    public void setProcess(CustList<AbstractImage> _process) {
        process = _process;
    }

    public boolean initializeOnlyConf(AbstractRenderAction _action, WithPageInfos _stds, Runnable _inst) {
        if (processing.get()) {
            return false;
        }
        start();
        standards = _stds;
        renderAction = _action;
        gene.getThreadFactory().newStartedThread(_inst);
        return true;
//        animateProcess();
    }

//    void animateProcess() {
//        if (!process.isEmpty()) {
//            LoadingWeb load_ = new LoadingWeb(gene.getThreadFactory(),this, process, frame, dialog);
//            gene.getThreadFactory().newStartedThread(load_);
//        }
//    }

//    public void refresh() {
////        if (processing.get()) {
////            return;
////        }
////        if (!(standards instanceof BeanNatLgNames)) {
////            return;
////        }
////        threadAction = gene.getThreadFactory().newThread(new ThreadRefresh(this, (BeanNatLgNames) standards));
////        threadAction.start();
////        animateProcess();
//    }

    public void start() {
//        if (!(getStandards() instanceof BeanCustLgNames)) {
//            return;
//        }
        if (dialog == null) {
            timer = gene.getThreadFactory().newScheduledExecutorService();
            processing.set(true);
            return;
        }
        dialog.init(gene.getThreadFactory(),this,frame, process);
        timer = gene.getThreadFactory().newScheduledExecutorService();
        taskTimer = timer.scheduleAtFixedRate(new TaskPaintingLabel(dialog),0,DELTA);
        dialog.startAnimation(gene.getThreadFactory());
        processing.set(true);
    }

    void finish() {
        if (taskTimer == null || dialog == null) {
            return;
        }
        dialog.stopAnimation();
        taskTimer.cancel(true);
        timer.shutdown();
        dialog.getAbsDialog().setVisible(false);
        dialog.getAbsDialog().getPane().removeAll();
        //boolean _stop
        processing.set(false);
        taskTimer = null;
    }

    public void setNavCore(NavigationCore _n) {
        this.navCore = _n;
    }

    public void setKeys(RendKeyWordsGroup _k) {
        this.keys = _k;
    }

    public void setStandards(WithPageInfos _s) {
        this.standards = _s;
    }

    public void setRenderAction(AbstractRenderAction _r) {
        this.renderAction = _r;
    }

    public void setupText() {
        Document doc_ = navCore.getDocument();
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_,keys,keyWordDigit,converter);
        getGene().getCompoFactory().invokeNow(new WindowPage(metadoc_, scroll, this));
    }
    void directScroll(MetaDocument _meta) {
        if (frame != null && !navCore.getTitle().isEmpty()) {
            frame.setTitle(navCore.getTitle());
        }
        String ref_ = navCore.getReferenceScroll();
        if (!ref_.isEmpty()) {
            MetaSearchableLabel lab_ = _meta.getAnchorsRef().getVal(ref_);
            scroll(lab_, this);
        }
        if (frame != null) {
            frame.pack();
        }
        finish();
    }

    static void scroll(MetaSearchableLabel _lab, RenderedPage _rend) {
        DualComponent c_ = _rend.getRefs().getVal(_lab);
        AbsScrollPane sc_ = _rend.getScroll();
        DualComponent r_ = _rend.getPage();
        int x_ = 0;
        int y_ = 0;
        while (c_ != r_ && c_ != null) {
            x_ += c_.getGraphic().getXcoords();
            y_ += c_.getGraphic().getYcoords();
            c_ = c_.getContainer();
        }
        sc_.setHorizontalValue(x_);
        sc_.setVerticalValue(y_);
    }
    public CustList<DualComponent> allMainComponents() {
        CustList<DualComponent> all_ = new CustList<DualComponent>();
        DualComponent root_ = page;
        DualComponent current_ = root_;
        while (current_ != null) {
            filter(all_, current_);
            DualComponent child_ = child(current_,0);
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                DualComponent next_ = next(current_);
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                DualComponent par_ = current_.getContainer();
                if (par_ == root_ || par_ == null) {
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        return all_;
    }

    private static void filter(CustList<DualComponent> _all, DualComponent _current) {
        if (_current instanceof DualLeaf && !(_current instanceof DualSeparator) && !(_current instanceof DualIndentNbLabel) && !(_current instanceof DualIndentLabel)) {
            _all.add(_current);
        }
    }

    private static DualComponent next(DualComponent _compo) {
        DualContainer cont_ = _compo.getContainer();
        if (cont_ == null) {
            return null;
        }
        return child(cont_,index(cont_.getChildren(),_compo)+1);
    }
    private static int index(CustList<DualComponent> _chs, DualComponent _compo) {
        return new IdList<DualComponent>(_chs).indexOfObj(_compo);
    }
    private static DualComponent child(DualComponent _par, int _index) {
        if (_par.getChildren().isValidIndex(_index)) {
            return _par.getChildren().get(_index);
        }
        return null;
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

    public String getKeyWordDigit() {
        return keyWordDigit;
    }

    public void setKeyWordDigit(String _k) {
        this.keyWordDigit = _k;
    }

    void setPage(DualPanel _page) {
        page = _page;
    }
    IdMap<MetaComponent, DualComponent> getRefs() {
        return refs;
    }

    public RendKeyWordsGroup getKeys() {
        return keys;
    }

    public NavigationCore getNavCore() {
        return navCore;
    }

    public AbsScrollPane getScroll() {
        return scroll;
    }
    DualPanel getPage() {
        return page;
    }

    public void setDialog(ProgressDialogAdv _dialog) {
        dialog = _dialog;
    }

    public void setFrame(ChangeableTitle _frame) {
        frame = _frame;
    }

    public void setArea(AbsTextArea _area) {
        area = _area;
    }

    public WithPageInfos getStandards() {
        return standards;
    }

    CustList<DualAnimatedImage> getAnims() {
        return anims;
    }

    public void addFinder(AbsTextField _f, AbsButton _s) {
        field = _f;
        find = _s;
        finding = new FindEvent(_f, this);
        _s.addActionListener(finding);
    }

    public FindEvent getFinding() {
        return finding;
    }

    public AbsButton getFind() {
        return find;
    }

    public AbsTextField getField() {
        return field;
    }

    public AbstractRenderAction getRenderAction() {
        return renderAction;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbstractProgramInfos getGene() {
        return gene;
    }

    public CharacterCaseConverter getConverter() {
        return converter;
    }
}
