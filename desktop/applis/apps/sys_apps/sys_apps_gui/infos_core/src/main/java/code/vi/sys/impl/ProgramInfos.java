package code.vi.sys.impl;

import aiki.db.DataBase;
import aiki.main.AikiFactory;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.GamesPk;
import cards.facade.Games;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.gui.labels.AbsMetaLabelCard;
import cards.main.CardFactories;
import cards.main.CardNatLgNamesNavigation;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.*;
import code.gui.files.*;
import code.gui.initialize.*;
import code.netw.NetWork;
import code.scripts.confs.HelpScriptConfPages;
import code.scripts.imgs.cards.CardsInit;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.TranslationsLg;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.stream.AbstractFileCoreStream;
import code.stream.core.DefBinFact;
import code.stream.core.DefTextFact;
import code.stream.core.DefZipFact;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;
import code.vi.maths.random.AdvancedGenerator;
import code.vi.prot.impl.*;
import code.vi.sys.impl.gui.DefFrameFactory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.ByteArrayInputStream;

public abstract class ProgramInfos extends ProgramInfosBase implements AbstractProgramInfos {
    public static final String EN = "en";
    public static final String FR = "fr";

    private static final String SEPARATEUR = "/";

//    private static final String C_PROGRAM_FILES_X86 = "C:/Program Files (x86)/";

//    private static final String C_PROGRAM_FILES = "C:/Program Files/";

    private static final String USER_HOME = "user.home";

//    private static final String TMP_FOLDER_KEY = "java.io.tmpdir";

    private static final String DOT = ".";

//    private static final String RELATIVE_VIRTUAL_STORE = "AppData/Local/VirtualStore/";

    private final AbstractFileCoreStream fileCoreStream;
    private final TechStreams streams;
    private final AbstractSocketFactory socketFactory;
    private final AbsFrameFactory frameFactory;
    private final AbsLightFrameFactory lightFrameFactory;
    private final StringList excludedFolders;

    protected ProgramInfos() {
        super(StringUtil.replaceBackSlashDot(System.getProperty(USER_HOME)),StringUtil.concat(initialize(),SEPARATEUR),new AdvancedGenerator(),
                new CompoundedInitParts(new DefaultThreadFactory(),new DefZipFact(new DefZipFactory()),new DefaultNameValidating(new StringList()),new DefCompoFactory(),new DefImageFactory()));
        fileCoreStream = new DefaultFileCoreStream();
        DefFrameFactory frameFactory_ = new DefFrameFactory();
        this.frameFactory = frameFactory_;
        this.lightFrameFactory = frameFactory_;
        streams = new TechStreams(new DefBinFact(new DefBinFactory(new DefaultInputStreamBuilder())),new DefTextFact(new DefTextFactory()),getZipFact());
        socketFactory = new DefSocketFactory();
        UpdateStyle updateStyle_ = new UpdateStyleImpl();
        updateStyle_.update();
        excludedFolders = new StringList();
        setLanguages(Constants.getAvailableLanguages());
        setDisplayLanguages(Constants.getDisplayLanguages());
        locales(this);
//        excludedFolders = StreamTextFile.getExcludedFolders(fileCoreStream,tmpUserFolder,StringUtil.replaceBackSlash(System.getProperty("java.class.path")));
    }

    public static void locales(ProgramInfosBase _pr) {
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsLg fr_ = _pr.lg(FR);
        en_.getMaxiCards().addAllEntries(CardsInit.en());
        fr_.getMaxiCards().addAllEntries(CardsInit.fr());
        en_.getMiniCardsDef().addAllEntries(AbsMetaLabelCard.enDef());
        fr_.getMiniCardsDef().addAllEntries(AbsMetaLabelCard.frDef());
        en_.getMiniCardsSel().addAllEntries(AbsMetaLabelCard.enSel());
        fr_.getMiniCardsSel().addAllEntries(AbsMetaLabelCard.frSel());
        en_.setTreeCards(HelpScriptConfPages.info(HelpScriptConfPages.en()));
        fr_.setTreeCards(HelpScriptConfPages.info(HelpScriptConfPages.fr()));
        FileInfos.enTr(FileInfos.initComments(en_));
        FileInfos.frTr(FileInfos.initComments(fr_));
        Games.enTr(Games.initAppliTr(en_));
        Games.frTr(Games.initAppliTr(fr_));
        GamesPk.enTr(GamesPk.initAppliTr(en_));
        GamesPk.frTr(GamesPk.initAppliTr(fr_));
        NetWork.enTr(NetWork.initAppliTr(en_));
        NetWork.frTr(NetWork.initAppliTr(fr_));
        FileFrame.enTr(FileFrame.initAppliTr(en_));
        FileFrame.frTr(FileFrame.initAppliTr(fr_));
        _pr.setCommon(MessGuiGr.ms());
    }

    public static WithAppFactories build(AbstractProgramInfos _p) {
        return new WithAppFactories(_p,new AppFactories(new AikiFactory(_p,new DefaultExecutorServiceParam<AikiNatLgNamesNavigation>(), new DefaultExecutorServiceParam<DataBase>()),
                new CardFactories(_p,new DefaultExecutorServiceParam<CardNatLgNamesNavigation>(),new DefaultExecutorServiceParam<StringMap<HelpIndexesTree>>()),new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator()))));
    }

    public StringList getExcludedFolders() {
        return excludedFolders;
    }

    private static String initialize() {
        String init_ = StringUtil.replaceBackSlashDot(DefaultFile.newFile(DOT).getAbsolutePath());
        return init_.substring(0, init_.length() - 1);

//        String javaPath_ = StringUtil.replaceBackSlash(System.getProperty("java.class.path"));
//        String result_;
//        if (!javaPath_.contains(SEPARATEUR)) {
//            result_ = StringUtil.concat(init_, javaPath_);
//        } else {
//            result_ = init_;
//        }
//        if (new File(result_).isDirectory()) {
//            //use tmpFolder as initFolder
//            return init_.substring(0, init_.length() - 1);
//        }
//        if (_homePath.startsWith(SEPARATEUR)) {
//            //unix os => jar folder path
//            String f_ = init_;
//            if (!f_.endsWith(SEPARATEUR)) {
//                f_ = StringUtil.concat(f_, SEPARATEUR);
//            }
//            return f_.substring(0, f_.length() - 1);
//        }
//        String virtualStore_ = StringUtil.concat(_homePath, RELATIVE_VIRTUAL_STORE);
//        //<home>/AppData/Local/VirtualStore/
//        boolean isStandardProgramFilesExecuted_ = false;
//        if (init_.startsWith(C_PROGRAM_FILES)) {
//            isStandardProgramFilesExecuted_ = true;
//        } else if (init_.startsWith(C_PROGRAM_FILES_X86)) {
//            isStandardProgramFilesExecuted_ = true;
//        }
//        //is it c:/program files (x86)/folder/... app writable?
//        if (!isStandardProgramFilesExecuted_) {
//            return init_.substring(0, init_.length() - 1);
//        }
//        if (new File(virtualStore_).exists()) {
//            //c:/program files/folder app
//            //c:/program files (x86)/folder app
//            StringList folders_ = new StringList();
//            for (String n : StringUtil.splitStrings(init_, SEPARATEUR)) {
//                if (n.isEmpty()) {
//                    continue;
//                }
//                folders_.add(n);
//            }
//            StringList lastFolders_ = new StringList();
//            lastFolders_.add(folders_.get(1));
//            lastFolders_.add(folders_.last());
//            virtualStore_ = StringUtil.concat(virtualStore_, StringUtil.join(lastFolders_, SEPARATEUR));
//            StreamFolderFile.makeParent(StringUtil.concat(virtualStore_, SEPARATEUR));
//            return virtualStore_;
//        }
//        //jarFolder_.startsWith("C:/Program Files/")
//        //jarFolder_.startsWith("C:/Program Files (x86)/")
//        //jarFolder_ == C:/<pf>/<appfolder>
//        if (new File(init_).canWrite()) {
//            return init_.substring(0, init_.length() - 1);
//        }
//
//        String userName_ = System.getProperty("user.name");
//
//        String tmpFolder_ = StringUtil.replaceBackSlashDot(System.getProperty(TMP_FOLDER_KEY));
//
//        //use temp folder
//        String tmpUserFolder_ = StringUtil.concat(tmpFolder_, userName_);
//        String realTmpUserFolder_;
//        if (!new File(tmpUserFolder_).exists()) {
//            realTmpUserFolder_ = tmpUserFolder_;
//        } else if (new File(tmpUserFolder_).isDirectory()) {
//            realTmpUserFolder_ = tmpUserFolder_;
//        } else {
//            int nb_ = 0;
//            while (true) {
//                File f_ = new File(StringUtil.concatNbs(tmpUserFolder_, nb_));
//                if (!f_.exists()) {
//                    break;
//                }
//                if (f_.isDirectory()) {
//                    break;
//                }
//                nb_++;
//            }
//            realTmpUserFolder_ = StringUtil.concatNbs(tmpUserFolder_, nb_);
//        }
//        StreamFolderFile.makeParent(realTmpUserFolder_);
//        return realTmpUserFolder_;
    }

    @Override
    public AbsFrameFactory getFrameFactory() {
        return frameFactory;
    }

    @Override
    public AbsLightFrameFactory getLightFrameFactory() {
        return lightFrameFactory;
    }

    @Override
    public AbstractFileCoreStream getFileCoreStream() {
        return fileCoreStream;
    }

    public TechStreams getStreams() {
        return streams;
    }

    @Override
    public AbsClipStream openClip(byte[] _file) {
        ByteArrayInputStream bis_ = new ByteArrayInputStream(_file);
        try {
            AudioInputStream audioIn_ = AudioSystem.getAudioInputStream(bis_);
            Clip clip_ = AudioSystem.getClip();
            clip_.open(audioIn_);
            return new ClipStream(clip_,audioIn_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbsSoundRecord newSoundPattern() {
        return new SoundRecord();
    }

    @Override
    public AbstractSocketFactory getSocketFactory() {
        return socketFactory;
    }

    @Override
    public int getScreenHeight() {
        GraphicsDevice gd_ = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd_.getDisplayMode().getHeight();
    }

    @Override
    public int getScreenWidth() {
        GraphicsDevice gd_ = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd_.getDisplayMode().getWidth();
    }

}
