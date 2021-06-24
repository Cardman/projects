package code.sys.impl;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.GroupFrame;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.*;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.random.AdvancedGenerator;
import code.stream.AbsClipStream;
import code.stream.AbstractFileCoreStream;
import code.stream.core.*;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.RenderedImage;
import java.io.*;

public final class ProgramInfos implements AbstractProgramInfos {

    private static final String SEPARATEUR = "/";

//    private static final String C_PROGRAM_FILES_X86 = "C:/Program Files (x86)/";

//    private static final String C_PROGRAM_FILES = "C:/Program Files/";

    private static final String USER_HOME = "user.home";

//    private static final String TMP_FOLDER_KEY = "java.io.tmpdir";

    private static final String DOT = ".";

//    private static final String RELATIVE_VIRTUAL_STORE = "AppData/Local/VirtualStore/";

    private final CustList<GroupFrame> frames = new CustList<GroupFrame>();
    private final StringMap<AbstractAtomicInteger> counts = new StringMap<AbstractAtomicInteger>();
    private final AbstractGenerator generator;
    private final String tmpUserFolder;
    private final String homePath;
    private final DefaultNameValidating validator;
    private final AbstractGraphicStringListGenerator graphicStringListGenerator;
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private final AbstractGraphicComboBoxGenerator graphicComboBoxGenerator;
    private final AbstractThreadFactory threadFactory;
    private final AbstractFileCoreStream fileCoreStream;
    private final TechStreams streams;
    private final AbstractInterceptor interceptor;
    private final AbstractSocketFactory socketFactory;

    public ProgramInfos(AbstractGraphicStringListGenerator _graphicStringListGenerator, AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator) {
        threadFactory = new DefaultThreadFactory();
        fileCoreStream = new DefaultFileCoreStream();
        streams = new TechStreams(new DefBinFact(new DefBinFactory()),new DefTextFact(new DefTextFactory()),new DefZipFact(new DefZipFactory()));
        interceptor = new DefInterceptor();
        socketFactory = new DefSocketFactory();
        compoFactory = new DefCompoFactory();
        imageFactory = new DefImageFactory();
        graphicStringListGenerator = _graphicStringListGenerator;
        graphicComboBoxGenerator = _graphicComboBoxGenerator;
        homePath = StringUtil.replaceBackSlashDot(System.getProperty(USER_HOME));
        tmpUserFolder = StringUtil.concat(initialize(),SEPARATEUR);
        generator = new AdvancedGenerator();
        validator = new DefaultNameValidating(new StringList());
        UpdateStyle updateStyle_ = new UpdateStyleImpl();
        updateStyle_.update();
    }
    private String initialize() {
        String init_ = StringUtil.replaceBackSlashDot(fileCoreStream.newFile(DOT).getAbsolutePath());
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

    public String getHomePath() {
        return homePath;
    }

    public String getTmpUserFolder() {
        return tmpUserFolder;
    }

    public CustList<GroupFrame> getFrames() {
        return frames;
    }

    public StringMap<AbstractAtomicInteger> getCounts() {
        return counts;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }

    @Override
    public AbstractFileCoreStream getFileCoreStream() {
        return fileCoreStream;
    }

    public TechStreams getStreams() {
        return streams;
    }

    @Override
    public AbstractNameValidating getValidator() {
        return validator;
    }

    @Override
    public AbstractGraphicStringListGenerator getGeneGraphicList() {
        return graphicStringListGenerator;
    }

    @Override
    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return imageFactory;
    }

    @Override
    public AbstractGraphicComboBoxGenerator getGeneComboBox() {
        return graphicComboBoxGenerator;
    }

    @Override
    public AbsClipStream openClip(byte[] _file) {
        ByteArrayInputStream bis_ = new ByteArrayInputStream(_file);
        try {
            AudioInputStream audioIn_ = AudioSystem.getAudioInputStream(bis_);
            Clip clip_ = AudioSystem.getClip();
            clip_.open(audioIn_);
            StreamCoreUtil.close(bis_);
            return new ClipStream(clip_,audioIn_);
        } catch (Exception e) {
            StreamCoreUtil.close(bis_);
            return null;
        }
    }

    @Override
    public AbstractImage readImg(String _file) {
        try {
            return new DefImage(ImageIO.read(new FileInputStream(StringUtil.nullToEmpty(_file))));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean writeImg(String _format, String _file, AbstractImage _img) {
        try {
            ImageIO.write((RenderedImage) ((DefImage)_img).data(),StringUtil.nullToEmpty(_format),new FileOutputStream(StringUtil.nullToEmpty(_file)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    @Override
    public AbstractSocketFactory getSocketFactory() {
        return socketFactory;
    }
}
