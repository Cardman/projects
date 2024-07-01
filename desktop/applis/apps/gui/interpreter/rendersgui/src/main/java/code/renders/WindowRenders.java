package code.renders;

import code.expressionlanguage.analyze.instr.DefCharacterCaseConverter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.util.DefaultInitialization;
import code.gui.*;
import code.gui.document.CustRenderAction;
import code.gui.document.RenderedPage;
import code.gui.events.*;
import code.gui.files.DefButtonsOpenPanelAct;
import code.gui.files.FileOpenFrame;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.OutputType;
import code.stream.core.ReadFiles;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;




public final class WindowRenders extends GroupFrame implements AbsOpenQuit {
    public static final String APPS_RENDERS_SITES = "renders_sites";
    private final EnabledMenu menu;
    private final EnabledMenu open;
    private final AbsTextField lgCode;
    private final AbsTextField path;
    private final RenderedPage session;
    private final CdmFactory interceptor;
    private final AbsActionListenerAct guardRender;
    private final AbstractAtomicBoolean atomicBoolean;
    private final FileOpenFrame fileOpenFrame;

    public WindowRenders(String _lg, CdmFactory _list, AbstractProgramInfos _programInfos) {
        super(_programInfos);
        guardRender = new AlwaysActionListenerAct();
        atomicBoolean = _programInfos.getThreadFactory().newAtomicBoolean();
        fileOpenFrame = new FileOpenFrame(_programInfos,atomicBoolean);
        GuiBaseUtil.choose(_lg, this, _programInfos.getCommon());
        interceptor = _list;
        setJMenuBar(getCompoFactory().newMenuBar());
        menu = getCompoFactory().newMenu("file");
        open = getCompoFactory().newMenuItem("open");
        open.addActionListener(new OpenArchive(this));
        open.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        menu.addMenuItem(open);
        getJMenuBar().add(menu);
        setTitle("Local sites");
        AbsPanel pane_ = getCompoFactory().newPageBox();
        lgCode = getCompoFactory().newTextField(20);
        pane_.add(lgCode);
        path = getCompoFactory().newTextField(20);
        pane_.add(path);
        session = new RenderedPage(getCompoFactory().newAbsScrollPane(), _programInfos,new DefCharacterCaseConverter(), getGuardRender());
        Navigation n_ = nav();
        session.initNav(n_.getCore(),n_.getSession().getRendKeyWords().group());
        session.setLanguage(_lg,_programInfos.getLanguages());
        session.setFrame(getCommonFrame());
        AbsTextField field_;
        AbsButton search_ = getCompoFactory().newPlainButton("search");
        field_ = getCompoFactory().newTextField(20);
        AbsTextArea t_ = getCompoFactory().newTextArea(8, 32);
        session.setArea(t_);
        session.addFinder(field_,search_);
        AbsScrollPane scrollSession_ = session.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
        pane_.add(scrollSession_);
        pane_.add(field_);
        pane_.add(search_);
        pane_.add(getCompoFactory().newAbsScrollPane(t_));
        setContentPane(pane_);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    public static CustThreadActions inst(DefaultInitialization _init, RenderedPage _page, Navigation _nav) {
        return CustThreadActions.inst(_page, _init,_nav);
    }

//    @Override
//    public void dispose() {
//        GuiBaseUtil.trEx(this);
//    }

    public void load() {
        if (!path.getText().trim().isEmpty()) {
            loadRenderConf(path.getText().trim());
            return;
        }
        getAtomicBoolean().set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,getFrames().getHomePath(),getFileOpenFrame(),new DefButtonsOpenPanelAct(new RenderAppContinueFile(this)));
//        String fichier_=StringUtil.nullToEmpty(getFileOpenDialogInt().input(getCommonFrame(), true, "", getFrames().getHomePath()));
//        if (fichier_.isEmpty()) {
//            return;
//        }
//        loadRenderConf(fichier_);
    }

    public AbstractAtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }
    public AbsActionListenerAct getGuardRender() {
        return guardRender;
    }

    public void loadRenderConf(String _fichier) {
        String content_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams()));
//        String content_ = StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams());
//        if (content_ == null) {
//            return;
//        }
        StringList linesFiles_ = ExecutingOptions.lines(content_);
        if (linesFiles_.size() < 2) {
            return;
        }
        String archive_ = linesFiles_.first();
        String confRel_ = linesFiles_.get(1);
        UniformingString app_ = new DefaultUniformingString();
        ReadFiles result_ = StreamFolderFile.getFiles(archive_, app_,getFileCoreStream(),getStreams());
        if (result_.getType() == OutputType.NOTHING) {
            return;
        }
        StringMap<String> zipFiles_ = result_.getZipFiles();
        String clName_ = "";
        String mName_ = "";
        ExecutingOptions exec_ = new ExecutingOptions();
        exec_.setAccess(archive_);
        exec_.setListGenerator(interceptor);
        String lg_ = getFrames().getLanguage();
        StringList lgs_ = getFrames().getLanguages();
        Options opt_ = new Options();
        if (linesFiles_.size() > 2) {
            String line_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
            if (line_.startsWith("initDb=")) {
                String subLine_ = line_.substring("initDb=".length());
                int last_ = subLine_.lastIndexOf('.');
                if (last_ > -1) {
                    clName_ = subLine_.substring(0,last_);
                    mName_ = subLine_.substring(last_+1);
                }
                if (linesFiles_.size() > 3) {
                    lg_ = updateLg(linesFiles_, exec_, opt_);
                    exec_.setLg(lg_);
                    StringList curr_ = exec_.getLgs();
                    if (!curr_.isEmpty()) {
                        lgs_ = curr_;
                    }
                }
            }
        }
        AbstractNameValidating validator_ = getValidator();
        LgNamesRenderUtils lgNames_ = new LgNamesRenderUtils(new FileInfos(new DefaultLogger(new RenderIssuer(session),getFileCoreStream(),getStreams()),
                new DefaultFileSystem(app_, validator_,getFileCoreStream(),getStreams()), new DefaultReporter(interceptor.getProgramInfos(),validator_, app_, false,new TechInfos(getThreadFactory(),getStreams()),getFileCoreStream()), getGenerator(), getStreams().getZipFact(), getThreadFactory()),interceptor.getInterceptor());
        lgNames_.getExecContent().setExecutingOptions(exec_);
        String lgCode_ = lgCode();
        lgNames_.getExecContent().updateTranslations(getFrames().getTranslations(),getFrames().getLanguage(),lgCode_);
        Navigation n_ = nav();
        session.initNav(n_.getCore(),n_.getSession().getRendKeyWords().group());
        session.setLanguage(lg_,lgs_);
        session.setFiles(zipFiles_);
        DefaultInitialization ini_ = new DefaultInitialization(lgNames_, new AdvSymbolFactory(lgNames_.getExecContent().getCustAliases().getMathAdvAliases()), lgCode_, confRel_, zipFiles_, clName_, mName_);
        ini_.setLog(lgNames_.getExecContent());
        session.initializeOnlyConf(new CustRenderAction(ini_,n_,new CustContextCreator(),session,lgNames_), lgNames_, inst(ini_, session,n_));
    }

    private String lgCode() {
        String lgCode_ = lgCode.getText();
        if (!StringUtil.contains(getFrames().getLanguages(),lgCode_)){
            lgCode_ = "";
        }
        return lgCode_;
    }

    private String updateLg(StringList _linesFiles, ExecutingOptions _exec, Options _opt) {
        String lg_;
        lg_ = _linesFiles.get(3);
        if (!StringUtil.contains(getFrames().getLanguages(),lg_)){
            lg_ = getFrames().getLanguage();
            ExecutingOptions.setupOptionals(3, _opt, _exec, _linesFiles);
        } else {
            ExecutingOptions.setupOptionals(4, _opt, _exec, _linesFiles);
        }
        return lg_;
    }

    private static Navigation nav() {
        Navigation nav_ = new Navigation();
        nav_.setSession(new Configuration());
        return nav_;
    }

    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        GuiBaseUtil.trEx(this);
    }

    @Override
    public String getApplicationName() {
        return APPS_RENDERS_SITES;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
    }

    public RenderedPage getSession() {
        return session;
    }

    public EnabledMenu getOpen() {
        return open;
    }

    public AbsTextField getLgCode() {
        return lgCode;
    }

    public AbsTextField getPath() {
        return path;
    }
}
