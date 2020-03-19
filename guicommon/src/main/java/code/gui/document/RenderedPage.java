package code.gui.document;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.gui.*;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.ThreadUtil;
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
    private String resourcesFolder;
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

    public void setFiles(String _url) {
        setFiles(new StringMap<String>(), _url);
    }

    public void setFiles(StringMap<String> _web) {
        navigation.setFiles(_web);
    }

    public void setFiles(StringMap<String> _web, String _url) {
        navigation.setFiles(_web);
        resourcesFolder = _url;
    }

    public void setLanguage(String _language) {
        navigation.setLanguage(_language);
        navigation.setLanguages(Constants.getAvailableLanguages());
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
        String content_ = ResourceFiles.ressourceFichier(_conf);
        navigation.loadConfiguration(content_,"", _stds);
        if (navigation.isError()) {
            setupText();
            return;
        }
        updateFiles();
        navigation.initializeRendSession();
        setupText();
    }


    public void initializeOnlyConf(Object _dataBase,Navigation _navigation, String _lg) {
        if (processing.get()) {
            return;
        }
        navigation = _navigation;
        navigation.setDataBase(_dataBase);
        navigation.setLanguage(_lg);
        standards = _navigation.getSession().getAdvStandards();
        threadAction = CustComponent.newThread(new ThreadActions(this));
        threadAction.start();
        animateProcess();
    }

    public void initializeOnlyConf(String _conf, String _lgCode,BeanCustLgNames _stds, StringMap<String> _files, String _clName, String _methodName) {
        if (processing.get()) {
            return;
        }
        standards = _stds;
        ThreadActions actions_ = new ThreadActions(this, _stds, _lgCode,"", _conf, _files, false, false, true);
        actions_.setClassDbName(_clName);
        actions_.setMethodName(_methodName);
        threadAction = CustComponent.newThread(actions_);
        threadAction.start();
        animateProcess();
    }
    public void animateProcess() {
        if (!process.isEmpty()) {
            LoadingWeb load_ = new LoadingWeb(this, process, frame, dialog);
            CustComponent.newThread(load_).start();
        }
    }
    public void initializeHtml(String _conf, BeanLgNames _lgNames) {
        start();
        standards = _lgNames;
        String content_ = ResourceFiles.ressourceFichier(_conf);
        navigation.loadConfiguration(content_,"", _lgNames);
        if (navigation.isError()) {
            setupText();
            finish(false);
            return;
        }
        updateFiles();
        navigation.initializeRendSession();
        setupText();
        finish(false);
    }


    static String getRealFilePath(String _lg, String _link) {
        return StringList.replace(_link, IMPLICIT_LANGUAGE, StringList.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }
    public void refresh() {
        if (processing.get()) {
            return;
        }
        threadAction = CustComponent.newThread(new ThreadActions(this, standards, "", "", false, true, false));
        threadAction.start();
        animateProcess();
    }

    public void reset(BeanLgNames _lgNames) {
        if (processing.get()) {
            return;
        }
        standards = _lgNames;
        threadAction = CustComponent.newThread(new ThreadActions(this, _lgNames, "", navigation.getSession().getFirstUrl(), false, false, true));
        threadAction.start();
        animateProcess();
    }

    public void reInitSession(String _conf, BeanLgNames _lgNames) {
        start();
        standards = _lgNames;
        String content_ = ResourceFiles.ressourceFichier(_conf);
        navigation.loadConfiguration(content_,"", _lgNames);
        if (navigation.isError()) {
            setupText();
            return;
        }
        updateFiles();
        navigation.initializeRendSession();
        setupText();
    }

    void updateFiles() {
        String lg_ = navigation.getLanguage();
        StringMap<String> files_ = new StringMap<String>();
        Configuration session_ = navigation.getSession();
        for (String a: session_.getAddedFiles()) {
            String name_ = StringList.concat(resourcesFolder, a);
            files_.put(a,ResourceFiles.ressourceFichier(name_));
        }
        for (String l: navigation.getLanguages()) {
            for (String a: session_.getProperties().values()) {
                String folder_ = session_.getMessagesFolder();
                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,l,a);
                files_.put(fileName_,ResourceFiles.ressourceFichier(StringList.concat(resourcesFolder,fileName_)));
            }
        }
        String realFilePath_ = getRealFilePath(lg_, session_.getFirstUrl());
        String rel_ = StringList.concat(resourcesFolder,realFilePath_);
        files_.put(realFilePath_,ResourceFiles.ressourceFichier(rel_));
        navigation.setFiles(files_);
        navigation.setupRendClassesInit();
    }
    public void start() {
        processing.set(true);
    }

    public void finish(boolean _wait) {
        //boolean _stop
        processing.set(false);
        if (!_wait) {
            return;
        }
        while (threadAction.isAlive()) {
            ThreadUtil.sleep(0);
        }
    }

    void setupText() {
        if (!processing.get()) {
            return;
        }
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
        finish(false);
    }
    public TextArea getArea() {
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
    public ScrollPane getScroll() {
        return scroll;
    }
    public DualPanel getPage() {
        return page;
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
    public void setArea(TextArea _area) {
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
