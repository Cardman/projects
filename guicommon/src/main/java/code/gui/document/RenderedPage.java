package code.gui.document;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import code.formathtml.Navigation;
import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanLgNames;
import code.gui.ChangeableTitle;
import code.gui.LabelButton;
import code.gui.LoadingWeb;
import code.gui.ProgressingWebDialog;
import code.sml.Document;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class RenderedPage implements ProcessingSession {

    private DualPanel page;
    private final JScrollPane scroll;
    private final Navigation navigation;
    private IdMap<MetaComponent,DualComponent> refs = new IdMap<MetaComponent,DualComponent>();
    private FindEvent finding;

    private AtomicBoolean processing = new AtomicBoolean();

    private ThreadActions threadAction;

    private CustList<BufferedImage> process = new CustList<BufferedImage>();

    private JTextArea area;

    private ProgressingWebDialog dialog;

    private ChangeableTitle frame;

    private BeanLgNames standards;

    private CustList<DualAnimatedImage> anims = new CustList<DualAnimatedImage>();

    private LabelButton find;
    private JTextField field;

    public RenderedPage(JScrollPane _frame) {
        scroll = _frame;
        navigation = new Navigation();
    }

    public RenderedPage(JScrollPane _frame, LabelButton _find, JTextField _field) {
        scroll = _frame;
        navigation = new Navigation();
        finding = new FindEvent(_field, this);
        _find.addMouseListener(finding);
    }

    public void setFiles(String _url) {
        setFiles(new StringMap<String>(), _url);
    }

    public void setFiles(StringMap<String> _web) {
        navigation.setFiles(_web);
    }

    public void setFiles(StringMap<String> _web, String _url) {
        navigation.setFiles(_web);
        navigation.setResourcesFolder(_url);
    }

    public void setLanguage(String _language) {
        navigation.setLanguage(_language);
    }

    public void setDataBase(Object _dataBase) {
        navigation.setDataBase(_dataBase);
    }

    public void setProcess(CustList<BufferedImage> _process) {
        process = _process;
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public void initialize(String _conf, BeanLgNames _stds) {
        start();
        standards = _stds;
        navigation.loadConfiguration(_conf, _stds);
        navigation.initializeSession();
        setupText(true);
        directScroll();
    }

    public void initializeOnlyConf(String _conf, BeanLgNames _stds) {
        if (processing.get()) {
            return;
        }
        standards = _stds;
        threadAction = new ThreadActions(this, _stds, "", _conf, false, false, true);
        threadAction.start();
        animateProcess();
    }
    public void animateProcess() {
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            load_.start();
        }
    }
    public void initializeHtml(String _conf, BeanLgNames _lgNames) {
        start();
        standards = _lgNames;
        navigation.loadConfiguration(_conf, _lgNames);
        navigation.initializeSession();
        setupText(true);
        finish(false);
    }
    public void initSession(String _conf, BeanLgNames _lgNames) {
        start();
        standards = _lgNames;
        navigation.loadConfiguration(_conf, _lgNames);
        navigation.initializeSession();
        setupText(true);
        directScroll();
    }
    public void refresh() {
        if (processing.get()) {
            return;
        }
        threadAction = new ThreadActions(this, standards, "", "", false, true, false);
        threadAction.start();
        animateProcess();
    }

    public void reset(BeanLgNames _lgNames) {
        if (processing.get()) {
            return;
        }
        standards = _lgNames;
        threadAction = new ThreadActions(this, _lgNames, "", navigation.getSession().getFirstUrl(), false, false, true);
        threadAction.start();
        animateProcess();
    }

    public void reInitSession(String _conf, BeanLgNames _lgNames) {
        start();
        standards = _lgNames;
        navigation.loadConfiguration(_conf, _lgNames);
        navigation.initializeSession();
        setupText(true);
        directScroll();
    }

    public void start() {
        processing.set(true);
    }

    public void interrupt() {
        navigation.getSession().setInterrupt(true);
    }

    public void finish(boolean _wait) {
        //boolean _stop
        processing.set(false);
        if (!_wait) {
            return;
        }
        while (threadAction.isAlive()) {
            continue;
        }
    }

    void setupText(boolean _setText) {
        if (!_setText) {
            return;
        }
        if (!processing.get()) {
            return;
        }
        Document doc_ = navigation.getDocument();
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_);
        SwingUtilities.invokeLater(new WindowPage(metadoc_, scroll, this));
    }
    void directScroll() {
        if (frame != null) {
            if (!navigation.getTitle().isEmpty()) {
                frame.setTitle(navigation.getTitle());
            }
        }
        String ref_ = navigation.getReferenceScroll();
        if (!ref_.isEmpty()) {
            Document doc_ = navigation.getDocument();
            MetaDocument metadoc_ = MetaDocument.newInstance(doc_);
            MetaAnchorLabel lab_ = metadoc_.getAnchorsRef().getVal(ref_);
            DualComponent c_ = getRefs().getVal(lab_);
            DualComponent r_ = page;
            int x_ = 0;
            int y_ = 0;
            while (c_ != r_) {
                Point loc_ = c_.getGraphic().getLocation();
                x_ += loc_.x;
                y_ += loc_.y;
                c_ = c_.getContainer();
            }
            scroll.getHorizontalScrollBar().setValue(x_);
            scroll.getVerticalScrollBar().setValue(y_);
        }
        if (frame != null) {
            frame.pack();
        }
        finish(false);
    }
    public JTextArea getArea() {
        return area;
    }
    @Override
    public boolean isProcessing() {
        return processing.get();
    }
    public void setFinding(MetaDocument _document) {
        finding.setFinding(_document);
    }
    public FindEvent getFinding() {
        return finding;
    }
    public void setPage(DualPanel _page) {
        page = _page;
    }
    public IdMap<MetaComponent, DualComponent> getRefs() {
        return refs;
    }
    public Navigation getNavigation() {
        return navigation;
    }
    public JScrollPane getScroll() {
        return scroll;
    }
    public DualPanel getPage() {
        return page;
    }

    public ThreadActions getThreadAction() {
        return threadAction;
    }

    public void setThreadAction(ThreadActions _threadAction) {
        threadAction = _threadAction;
    }

    public ProgressingWebDialog getDialog() {
        return dialog;
    }

    public void setDialog(ProgressingWebDialog _dialog) {
        dialog = _dialog;
    }

    public ChangeableTitle getFrame() {
        return frame;
    }

    public void setFrame(ChangeableTitle _frame) {
        frame = _frame;
    }

    public CustList<BufferedImage> getProcess() {
        return process;
    }

    public void setRefs(IdMap<MetaComponent, DualComponent> _refs) {
        refs = _refs;
    }

    public void setFinding(FindEvent _finding) {
        finding = _finding;
    }

    public void setProcessing(boolean _processing) {
        processing.set(_processing);
    }
    public void setArea(JTextArea _area) {
        area = _area;
    }

    public BeanLgNames getStandards() {
        return standards;
    }
    public void setStandards(BeanLgNames _standards) {
        standards = _standards;
    }
    public void setAnims(CustList<DualAnimatedImage> _anims) {
        anims = _anims;
    }
    public CustList<DualAnimatedImage> getAnims() {
        return anims;
    }

    public void setSearchText(LabelButton _search) {
        find = _search;
    }

    public void setField(JTextField _field) {
        field = _field;
    }

    public void addFinder() {
        if (find.getMouseListeners().length > 0) {
            return;
        }
        finding = new FindEvent(field, this);
        find.addMouseListener(finding);
    }
}
