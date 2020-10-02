package code.gui.document;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.ReportedMessages;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.gui.*;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class RenderedPage implements ProcessingSession {

    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private DualPanel page;
    private final ScrollPane scroll;
    private Navigation navigation;
    private IdMap<MetaComponent,DualComponent> refs = new IdMap<MetaComponent,DualComponent>();
    private FindEvent finding;

    private AtomicBoolean processing = new AtomicBoolean();

    private Thread threadAction;

    private CustList<BufferedImage> process = new CustList<BufferedImage>();

    private TextArea area;

    private ProgressingWebDialog dialog;

    private ChangeableTitle frame;

    private BeanLgNames standards;

    private CustList<DualAnimatedImage> anims = new CustList<DualAnimatedImage>();

    private LabelButton find;
    private TextField field;

    public RenderedPage(ScrollPane _frame) {
        scroll = _frame;
        initNav();
    }

    public RenderedPage(ScrollPane _frame, LabelButton _find, TextField _field) {
        scroll = _frame;
        initNav();
        finding = new FindEvent(_field, this);
        _find.addMouseListener(finding);
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

    public void setProcess(CustList<BufferedImage> _process) {
        process = _process;
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public void initialize(String _conf, Object _db,BeanNatLgNames _stds) {
        start();
        _stds.setDataBase(_db);
        standards = _stds;
        String content_ = ResourceFiles.ressourceFichier(_conf);
        RendAnalysisMessages rend_ = new RendAnalysisMessages();
        AnalyzedPageEl page_ = navigation.loadConfiguration(content_, "", _stds, rend_, DefaultFileBuilder.newInstance(_stds.getContent()));
        if (navigation.isError()) {
            setupText();
            return;
        }
        setFiles();
        ReportedMessages reportedMessages_ = navigation.setupRendClassesInit(page_, _stds, rend_);
        if (!reportedMessages_.isAllEmptyErrors()) {
            return;
        }
        navigation.initializeRendSession();
        setupText();
    }

    public void initializeOnlyConf(Object _dataBase, boolean _ok, BeanNatLgNames _stds,Navigation _navigation, String _lg) {
        if (processing.get()) {
            return;
        }
        navigation = _navigation;
        _stds.setDataBase(_dataBase);
        navigation.setLanguage(_lg);
        standards = _navigation.getSession().getAdvStandards();
        threadAction = CustComponent.newThread(new ThreadActions(this,_ok));
        threadAction.start();
        animateProcess();
    }

    public void initializeOnlyConf(BeanCustLgNames _stds, Runnable _inst) {
        if (processing.get()) {
            return;
        }
        standards = _stds;
        threadAction = CustComponent.newThread(_inst);
        threadAction.start();
        animateProcess();
    }

    void animateProcess() {
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            CustComponent.newThread(load_).start();
        }
    }

    private static String getRealFilePath(String _lg, String _link) {
        return StringList.replace(_link, IMPLICIT_LANGUAGE, StringList.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }
    public void refresh() {
        if (processing.get()) {
            return;
        }
        if (!(standards instanceof BeanNatLgNames)) {
            return;
        }
        threadAction = CustComponent.newThread(new ThreadRefresh(this, (BeanNatLgNames) standards));
        threadAction.start();
        animateProcess();
    }

    public void reset(BeanNatLgNames _lgNames) {
        if (processing.get()) {
            return;
        }
        standards = _lgNames;
        threadAction = CustComponent.newThread(ThreadActions.inst(this, _lgNames, navigation.getSession().getFirstUrl()));
        threadAction.start();
        animateProcess();
    }

    void setFiles() {
        String lg_ = navigation.getLanguage();
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a: session_.getAddedFiles()) {
            files_.put(a,ResourceFiles.ressourceFichier(a));
        }
        for (String l: navigation.getLanguages()) {
            for (String a: session_.getProperties().values()) {
                String folder_ = session_.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,ResourceFiles.ressourceFichier(fileName_));
            }
        }
        String realFilePath_ = getRealFilePath(lg_, session_.getFirstUrl());
        files_.put(realFilePath_,ResourceFiles.ressourceFichier(realFilePath_));
        navigation.setFiles(files_);
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
        CustComponent.invokeLater(new WindowPage(metadoc_, scroll, this));
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
    public TextArea getArea() {
        return area;
    }
    @Override
    public boolean isProcessing() {
        return processing.get();
    }
    void setFinding(MetaDocument _document) {
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
    public ScrollPane getScroll() {
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

    public void setArea(TextArea _area) {
        area = _area;
    }

    BeanLgNames getStandards() {
        return standards;
    }

    CustList<DualAnimatedImage> getAnims() {
        return anims;
    }

    public void setSearchText(LabelButton _search) {
        find = _search;
    }

    public void setField(TextField _field) {
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
