package code.gui;
import java.io.File;

import code.util.StringList;
import code.util.core.StringUtil;

public final class ConstFiles {

    private static final String SEPARATEUR = "/";

    private static final String C_PROGRAM_FILES_X86 = "C:/Program Files (x86)/";

    private static final String C_PROGRAM_FILES = "C:/Program Files/";

    private static final String USER_HOME = "user.home";

    private static final String TMP_FOLDER_KEY = "java.io.tmpdir";

    private static final String USER_NAME = System.getProperty("user.name");

    /**Used only for init*/
    private static final String TMP_FOLDER = StringUtil.replaceBackSlashDot(System.getProperty(TMP_FOLDER_KEY));

    private static final String DOT = ".";

    private static final String INIT_FOLDER = StringUtil.replaceBackSlashDot(new File(DOT).getAbsolutePath());

    /**Used only for temp folders*/
    private static final String JAVA_PATH = StringUtil.replaceBackSlash(System.getProperty("java.class.path"));

    private static final String RELATIVE_VIRTUAL_STORE = "AppData/Local/VirtualStore/";

    private static final String HOME_PATH = StringUtil.replaceBackSlashDot(System.getProperty(USER_HOME));

    private static final String TMP_USER_FOLDER = ConstFiles.initialize();

    private ConstFiles() {
    }

    private static String initialize() {
        if (!isExecutedJar()) {
            //use TMP_FOLDER as INIT_FOLDER
            String tmpFolder_ = INIT_FOLDER;
            return tmpFolder_.substring(0, tmpFolder_.length() - 1);
        }
        if (!HOME_PATH.startsWith(SEPARATEUR)) {
            String virtualStore_ = StringUtil.concat(HOME_PATH,RELATIVE_VIRTUAL_STORE);
            //<home>/AppData/Local/VirtualStore/
            String jarFolder_ = getInitFolder();
            boolean isStandardProgramFilesExecuted_ = false;
            if (jarFolder_.startsWith(C_PROGRAM_FILES)) {
                isStandardProgramFilesExecuted_ = true;
            } else if (jarFolder_.startsWith(C_PROGRAM_FILES_X86)) {
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
                for (String n: StringUtil.splitStrings(jarFolder_, SEPARATEUR)) {
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
                if (new File(jarFolder_).canWrite()) {
                	return jarFolder_.substring(0, jarFolder_.length() - 1);
                }
            } else {
                return INIT_FOLDER.substring(0, INIT_FOLDER.length() - 1);
            }
            //use temp folder
            String tmpUserFolder_ = StringUtil.concat(TMP_FOLDER,USER_NAME);
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
        String f_ = getInitFolder();
        if (!f_.endsWith(SEPARATEUR)) {
            f_ = StringUtil.concat(f_, SEPARATEUR);
        }
        return f_.substring(0, f_.length() - 1);
    }

    private static boolean isExecutedJar() {
        return !new File(getPath()).isDirectory();
    }

    private static String getPath() {
        String init_ = getInitFolder();
        if (!JAVA_PATH.contains(SEPARATEUR)) {
            return StringUtil.concat(init_, JAVA_PATH);
        }
        return init_;
    }

    private static String getInitFolder() {
        return INIT_FOLDER;
    }

    public static String getTmpUserFolderSl() {
        return StringUtil.concat(TMP_USER_FOLDER,SEPARATEUR);
    }

    public static String getHomePath() {
        return HOME_PATH;
    }
}
