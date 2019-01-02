package code.gui;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import code.util.StringList;

public final class ConstFiles {

    private static final String SEPARATEUR = "/";

    private static final String C_PROGRAM_FILES_X86 = "C:/Program Files (x86)/";

    private static final String C_PROGRAM_FILES = "C:/Program Files/";

    private static final String USER_HOME = "user.home";

    private static final String TMP_FOLDER_KEY = "java.io.tmpdir";

    private static final String USER_NAME = System.getProperty("user.name");

    /**Used only for init*/
    private static final String TMP_FOLDER = StringList.replaceBackSlashDot(System.getProperty(TMP_FOLDER_KEY));

    private static final String DOT = ".";

    private static final String INIT_FOLDER = StringList.replaceBackSlashDot(new File(DOT).getAbsolutePath());

    /**Used only for temp folders*/
    private static final String JAVA_PATH = StringList.replaceBackSlash(System.getProperty("java.class.path"));

    private static final String RELATIVE_VIRTUAL_STORE = "AppData/Local/VirtualStore/";

    private static final String HOME_PATH = StringList.replaceBackSlashDot(System.getProperty(USER_HOME));

    private static final String TMP_USER_FOLDER = ConstFiles.initialize(INIT_FOLDER,HOME_PATH);

    private ConstFiles() {
    }

    private static String initialize(String _initFolder, String _homePath) {
        if (!isExecutedJar()) {
            //use TMP_FOLDER as INIT_FOLDER
            String tmpFolder_ = _initFolder;
            return tmpFolder_.substring(0, tmpFolder_.length() - 1);
        }
        if (!_homePath.startsWith(SEPARATEUR)) {
            String virtualStore_ = StringList.concat(_homePath,RELATIVE_VIRTUAL_STORE);
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
                for (String n: StringList.splitStrings(jarFolder_, SEPARATEUR)) {
                    if (n.isEmpty()) {
                        continue;
                    }
                    folders_.add(n);
                }
                StringList lastFolders_ = new StringList();
                lastFolders_.add(folders_.get(1));
                lastFolders_.add(folders_.last());
                virtualStore_ = StringList.concat(virtualStore_, lastFolders_.join(SEPARATEUR));
                new File(StringList.concat(virtualStore_, SEPARATEUR)).mkdirs();
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
            	String tmpFolder_ = _initFolder;
                return tmpFolder_.substring(0, tmpFolder_.length() - 1);
            }
            //use temp folder
            String tmpUserFolder_ = StringList.concat(TMP_FOLDER,USER_NAME);
            String realTmpUserFolder_;
            if (!new File(tmpUserFolder_).exists()) {
                realTmpUserFolder_ = tmpUserFolder_;
            } else if (new File(tmpUserFolder_).isDirectory()) {
                realTmpUserFolder_ = tmpUserFolder_;
            } else {
                int nb_ = 0;
                while (true) {
                    File f_ = new File(StringList.concatNbs(tmpUserFolder_,nb_));
                    if (!f_.exists()) {
                        break;
                    }
                    if (f_.isDirectory()) {
                        break;
                    }
                    nb_++;
                }
                realTmpUserFolder_ = StringList.concatNbs(tmpUserFolder_,nb_);
            }
            new File(realTmpUserFolder_).mkdirs();
            return realTmpUserFolder_;
        }
        //unix os => jar folder path
        String f_ = getInitFolder();
        if (!f_.endsWith(SEPARATEUR)) {
            f_ = StringList.concat(f_, SEPARATEUR);
        }
        return f_.substring(0, f_.length() - 1);
    }

    private static boolean isExecutedJar() {
        return !new File(getPath()).isDirectory();
    }

    private static String getPath() {
        String init_ = ConstFiles.getInitFolder();
        if (!JAVA_PATH.contains(SEPARATEUR)) {
            return StringList.concat(init_, JAVA_PATH);
        }
        return init_;
    }

    public static String getInitFolder() {
        return INIT_FOLDER;
    }

    public static boolean isZippedFile(String _path) {
        try {
            ZipFile zipFile_ = new ZipFile(_path);
            zipFile_.close();
            return true;
        } catch (ZipException _0) {
            return false;
        } catch (IOException _0) {
            return false;
        }
    }

    public static String getTmpUserFolderSl() {
        return StringList.concat(TMP_USER_FOLDER,SEPARATEUR);
    }

    public static String getTmpUserFolder() {
        return TMP_USER_FOLDER;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static String getHomePath() {
        return HOME_PATH;
    }
}
