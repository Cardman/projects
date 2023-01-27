package code.renders;

import code.expressionlanguage.analyze.instr.DefCharacterCaseConverter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.filenames.AbstractNameValidating;
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
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.OutputType;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;




public final class WindowRenders extends GroupFrame {
    private final AbsMenu menu;
    private final AbsMenuItem open;
    private final AbsTextField lgCode;
    private final AbsTextField path;
    private final RenderedPage session;
    private final CdmFactory interceptor;

    public WindowRenders(String _lg, CdmFactory _list, AbstractProgramInfos _programInfos) {
        super(_lg, _programInfos);
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
        session = new RenderedPage(getCompoFactory().newAbsScrollPane(), _programInfos,new DefCharacterCaseConverter());
        Navigation n_ = nav();
        session.initNav(n_.getCore(),n_.getSession().getRendKeyWords().group());
        session.setLanguage(_lg);
        session.setFrame(getCommonFrame());
        AbsTextField field_;
        AbsPlainButton search_ = getCompoFactory().newPlainButton("search");
        field_ = getCompoFactory().newTextField(20);
        session.setSearchText(search_);
        session.setField(field_);
        AbsTextArea t_ = getCompoFactory().newTextArea(8, 32);
        session.setArea(t_);
        session.addFinder();
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

    @Override
    public void dispose() {
        basicDispose();
    }

    public void load() {
        if (!path.getText().trim().isEmpty()) {
            loadRenderConf(path.getText().trim());
            return;
        }
        String fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, "", getFrames().getHomePath());
        if (fichier_ == null) {
            fichier_ = "";
        }
        if (fichier_.isEmpty()) {
            return;
        }
        loadRenderConf(fichier_);
    }

    public void loadRenderConf(String _fichier) {
        String content_ = StreamTextFile.contentsOfFile(_fichier,getFileCoreStream(),getStreams());
        if (content_ == null) {
            return;
        }
        StringList lines_ = StringUtil.splitStrings(content_, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
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
        ExecutingOptions exec_ = new ExecutingOptions(getThreadFactory().newAtomicBoolean());
        exec_.setListGenerator(interceptor);
        String lg_ = getLanguageKey();
        StringList lgs_ = Constants.getAvailableLanguages();
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
                    lg_ = linesFiles_.get(3);
                    StringList curr_ = new StringList();
                    if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
                        lg_ = getLanguageKey();
                        setupOptionals(3, exec_,linesFiles_,curr_);
                    } else {
                        setupOptionals(4, exec_, linesFiles_,curr_);
                    }
                    if (!curr_.isEmpty()) {
                        lgs_ = curr_;
                    }
                }
            }
        }
        AbstractNameValidating validator_ = getValidator();
        LgNamesRenderUtils lgNames_ = new LgNamesRenderUtils(new FileInfos(new DefaultLogger(new RenderIssuer(session),getFileCoreStream(),getStreams()),
                new DefaultFileSystem(app_, validator_,getFileCoreStream(),getStreams()), new DefaultReporter(interceptor.getProgramInfos(),validator_, app_, false,new TechInfos(getThreadFactory(),getStreams()),getFileCoreStream()), getGenerator(), getStreams().getZipFact(), getThreadFactory()),interceptor.getInterceptor());
        lgNames_.setExecutingOptions(exec_);
        Navigation n_ = nav();
        session.initNav(n_.getCore(),n_.getSession().getRendKeyWords().group());
        session.setLanguage(lg_,lgs_);
        session.setFiles(zipFiles_);
        String lgCode_ = lgCode.getText();
        if (!StringUtil.contains(Constants.getAvailableLanguages(),lgCode_)){
            lgCode_ = "";
        }
        DefaultInitialization ini_ = new DefaultInitialization(lgNames_, new AdvSymbolFactory(lgNames_), lgCode_, confRel_, zipFiles_, clName_, mName_);
        session.initializeOnlyConf(new CustRenderAction(ini_,n_,new CustContextCreator(),session,lgNames_), lgNames_, inst(ini_, session,n_));
    }
    private static Navigation nav() {
        Navigation nav_ = new Navigation();
        nav_.setSession(new Configuration());
        return nav_;
    }
    public static void setupOptionals(int _from, ExecutingOptions _exec, StringList _lines, StringList _lgs) {
        for (String l: _lines.mid(_from)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    _exec.setLogFolder(StringUtil.replaceBackSlash(output_.substring(0,lastSep_)));
                    _exec.setMainThread(StringUtil.replaceBackSlash(output_.substring(lastSep_+1)));
                }
            }
            if (l.startsWith("lgs=")) {
                String output_ = l.substring("lgs=".length());
                for (String s: StringUtil.splitChars(output_,',')) {
                    String tr_ = s.trim();
                    if (tr_.isEmpty()) {
                        continue;
                    }
                    _lgs.add(tr_);
                }
            }
            if (l.startsWith("res=")) {
                String output_ = l.substring("res=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setResources(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("files=")) {
                String output_ = l.substring("files=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setFiles(StringUtil.replaceBackSlash(output_));
                }
            }
        }
    }

    private static boolean endsWithSep(String _output) {
        return MemoryFileSystem.endsSep(_output);
    }

    @Override
    public void quit() {
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingRenders.getMainWindowClass();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
        //
    }
}
