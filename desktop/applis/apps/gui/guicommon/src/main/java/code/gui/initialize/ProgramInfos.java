package code.gui.initialize;

import code.gui.GroupFrame;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.random.AdvancedGenerator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public final class ProgramInfos implements AbstractProgramInfos {

    private static final String SEPARATEUR = "/";

    private static final String C_PROGRAM_FILES_X86 = "C:/Program Files (x86)/";

    private static final String C_PROGRAM_FILES = "C:/Program Files/";

    private static final String USER_HOME = "user.home";

    private static final String TMP_FOLDER_KEY = "java.io.tmpdir";

    private static final String DOT = ".";

    private static final String RELATIVE_VIRTUAL_STORE = "AppData/Local/VirtualStore/";

    private final CustList<GroupFrame> frames = new CustList<GroupFrame>();
    private final StringMap<AtomicInteger> counts = new StringMap<AtomicInteger>();
    private final AbstractGenerator generator;
    private final String tmpUserFolder;
    private final String homePath;
    public ProgramInfos() {
        homePath = StringUtil.replaceBackSlashDot(System.getProperty(USER_HOME));
        tmpUserFolder = initialize(homePath);
        generator = new AdvancedGenerator();
        UpdateStyle updateStyle_ = new UpdateStyleImpl();
        updateStyle_.update();
    }
    private static String initialize(String _homePath) {

        String userName = System.getProperty("user.name");

        String tmpFolder = StringUtil.replaceBackSlashDot(System.getProperty(TMP_FOLDER_KEY));

        String init_ = StringUtil.replaceBackSlashDot(new File(DOT).getAbsolutePath());

        String javaPath_ = StringUtil.replaceBackSlash(System.getProperty("java.class.path"));
        String result_;
        if (!javaPath_.contains(SEPARATEUR)) {
            result_ = StringUtil.concat(init_, javaPath_);
        } else {
            result_ = init_;
        }
        if (new File(result_).isDirectory()) {
            //use tmpFolder as initFolder
            return init_.substring(0, init_.length() - 1);
        }
        if (!_homePath.startsWith(SEPARATEUR)) {
            String virtualStore_ = StringUtil.concat(_homePath,RELATIVE_VIRTUAL_STORE);
            //<home>/AppData/Local/VirtualStore/
            boolean isStandardProgramFilesExecuted_ = false;
            if (init_.startsWith(C_PROGRAM_FILES)) {
                isStandardProgramFilesExecuted_ = true;
            } else if (init_.startsWith(C_PROGRAM_FILES_X86)) {
                isStandardProgramFilesExecuted_ = true;
            }
            boolean storeVirtual_ = false;
            if (new File(virtualStore_).exists()){
                if (isStandardProgramFilesExecuted_) {
                    storeVirtual_ = true;
                }
            }
            if (storeVirtual_) {
                //c:/program files/folder app
                //c:/program files (x86)/folder app
                StringList folders_ = new StringList();
                for (String n: StringUtil.splitStrings(init_, SEPARATEUR)) {
                    if (n.isEmpty()) {
                        continue;
                    }
                    folders_.add(n);
                }
                StringList lastFolders_ = new StringList();
                lastFolders_.add(folders_.get(1));
                lastFolders_.add(folders_.last());
                virtualStore_ = StringUtil.concat(virtualStore_, StringUtil.join(lastFolders_, SEPARATEUR));
                new File(StringUtil.concat(virtualStore_, SEPARATEUR)).mkdirs();
                return virtualStore_;
            }
            //is it c:/program files (x86)/folder/... app writable?
            if (isStandardProgramFilesExecuted_) {
                //jarFolder_.startsWith("C:/Program Files/")
                //jarFolder_.startsWith("C:/Program Files (x86)/")
                //jarFolder_ == C:/<pf>/<appfolder>
                if (new File(init_).canWrite()) {
                    return init_.substring(0, init_.length() - 1);
                }
            } else {
                return init_.substring(0, init_.length() - 1);
            }
            //use temp folder
            String tmpUserFolder_ = StringUtil.concat(tmpFolder, userName);
            String realTmpUserFolder_;
            if (!new File(tmpUserFolder_).exists()) {
                realTmpUserFolder_ = tmpUserFolder_;
            } else if (new File(tmpUserFolder_).isDirectory()) {
                realTmpUserFolder_ = tmpUserFolder_;
            } else {
                int nb_ = 0;
                while (true) {
                    File f_ = new File(StringUtil.concatNbs(tmpUserFolder_,nb_));
                    if (!f_.exists()) {
                        break;
                    }
                    if (f_.isDirectory()) {
                        break;
                    }
                    nb_++;
                }
                realTmpUserFolder_ = StringUtil.concatNbs(tmpUserFolder_,nb_);
            }
            new File(realTmpUserFolder_).mkdirs();
            return realTmpUserFolder_;
        }
        //unix os => jar folder path
        String f_ = init_;
        if (!f_.endsWith(SEPARATEUR)) {
            f_ = StringUtil.concat(f_, SEPARATEUR);
        }
        return f_.substring(0, f_.length() - 1);
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

    public StringMap<AtomicInteger> getCounts() {
        return counts;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }
}
