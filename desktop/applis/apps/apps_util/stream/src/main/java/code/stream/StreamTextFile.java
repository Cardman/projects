package code.stream;
import java.io.*;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.core.StreamCoreUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class StreamTextFile {

    public static final String SEPARATEUR = "/";

    private StreamTextFile() {
    }

    public static StringList allSortedFiles(String _folder) {
        FileInfo f_ = new FileInfo(new File(_folder));
        StringList files_ = new StringList();
        for (FileInfo s: getSortedDescNodes(f_)) {
            files_.add(StringUtil.replaceBackSlash(s.getInfo().getAbsolutePath()));
        }
        return files_;
    }
    private static CustList<FileInfo> getSortedDescNodes(FileInfo _root) {
        CustList<FileInfo> list_ = new CustList<FileInfo>();
        FileInfo c_ = _root;
        while (c_ != null) {
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    private static FileInfo getNext(FileInfo _current, FileInfo _root) {
        FileInfo n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        FileInfo curr_ = _current;
        while (true) {
            FileInfo next_ = curr_.getNextSibling();
            if (next_ != null) {
                return next_;
            }
            FileInfo par_ = curr_.getParent();
            if (par_ == null || par_ == _root) {
                return null;
            }
            curr_ = par_;
        }
    }
    public static StringList files(String _folder) {
        StringList files_ = new StringList();
        StringList current_ = new StringList(_folder);
        String folder_ = _folder;
        folder_ = new File(folder_).getAbsolutePath();
        folder_ = StringUtil.replaceBackSlash(folder_);
        if (folder_.endsWith(StringUtil.concat(SEPARATEUR,_folder))) {
            String suffix_ = StringUtil.concat(SEPARATEUR,_folder);
            folder_ = folder_.substring(IndexConstants.FIRST_INDEX, folder_.length() - suffix_.length());
            folder_ = StringUtil.concat(folder_,SEPARATEUR);
        }
        while (true) {
            StringList new_ = new StringList();
            for (String c : current_) {
                File[] filesFolder_ = new File(c).listFiles();
                if (filesFolder_ == null) {
                    continue;
                }
                for (File f : filesFolder_) {
                    new_.add(f.getAbsolutePath());
                    files_.add(f.getAbsolutePath());
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            current_ = new StringList(new_);
        }
        files_.replaceBackSlashesInStrings();
        StringUtil.removePrefixInStrings(files_,folder_);
        return files_;
    }

    private static Document documentXmlExterne(String _nomFichier) {
        return DocumentBuilder.parseSax(contentsOfFile(_nomFichier));
    }

    public static String contentsOfFile(String _nomFichier) {
        return readFile(_nomFichier);
    }

    private static String readFile(String _filePath) {
        File file_ = new File(_filePath);
        return readingFile(tryCreateBufferedReader(StreamFileCore.tryCreateFileInputStream(file_)), file_.length());
    }
    private static BufferedReader tryCreateBufferedReader(FileInputStream _file) {
        if (_file == null) {
            return null;
        }
        return new BufferedReader(new InputStreamReader(_file));
    }

    public static Element contenuDocumentXmlExterne(String _nomFichier) {
        Document doc_ = documentXmlExterne(_nomFichier);
        if (doc_ == null) {
            return null;
        }
        return doc_.getDocumentElement();
    }

    private static String readingFile(BufferedReader _br, long _capacity) {
        if (_br == null) {
            return null;
        }
        StringBuilder strBuilder_ = new StringBuilder((int) _capacity);
        while (true) {

            int char_ = StreamCoreUtil.read(_br);
            if (char_ == -2) {
                return null;
            }
            if (char_ < 0) {
                break;
            }
            if (char_ != '\r') {
                strBuilder_.append((char) char_);
            }
        }
        return strBuilder_.toString();
    }

    public static boolean saveTextFile(String _nomFichier, String _text) {
        if (_nomFichier == null) {
            return false;
        }
        return write(tryCreateWriter(_nomFichier,false),_text);
    }
    private static boolean write(FileWriter _bw,String _text) {
        if (_bw == null) {
            return false;
        }
        boolean w_ = write(new BufferedWriter(_bw), _text);
        return w_&&StreamCoreUtil.close(_bw);
    }
    private static boolean write(BufferedWriter _bw,String _text) {
        try {
            _bw.write(_text);
            return StreamCoreUtil.close(_bw);
        } catch (IOException e) {
            return false;
        }
    }
    public static boolean logToFile(String _nomFichier, String _text) {
        if (_nomFichier == null) {
            return false;
        }
        return println(tryCreateWriter(_nomFichier, true),_text);
    }

    private static FileWriter tryCreateWriter(String _nomFichier, boolean _append) {
        try {
            return new FileWriter(_nomFichier,_append);
        } catch (IOException e) {
            return null;
        }
    }
    private static boolean println(FileWriter _bw,String _text) {
        if (_bw == null) {
            return false;
        }
        PrintWriter _bw1 = new PrintWriter(_bw);
        println(_bw1, _text);
        return StreamCoreUtil.close(_bw);
    }

    private static void println(PrintWriter _bw, String _text) {
        _bw.println(_text);
        _bw.close();
    }
}
