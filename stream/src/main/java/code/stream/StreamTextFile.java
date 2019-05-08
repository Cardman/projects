package code.stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StreamTextFile {

    public static final String SEPARATEUR = "/";
    private static final String EMPTY_STRING = "";
    private static final char INVALID_CHARACTER = 65533;

    private StreamTextFile() {
    }

    public static StringMap<byte[]> getFilesInBin(String _folder) {
        StringMap<byte[]> map_ = new StringMap<byte[]>();
        for (String f: files(_folder)) {
            if (new File(f).isDirectory()) {
                continue;
            }
            map_.put(f, StreamBinaryFile.loadFile(f));
        }
        return map_;
    }

    public static StringMap<String> getTextFiles(String _folder) {
        StringMap<String> map_ = new StringMap<String>();
        for (String f: files(_folder)) {
            if (new File(StringList.concat(_folder,f)).isDirectory()) {
                continue;
            }
            map_.put(f, contentsOfFile(StringList.concat(_folder,f)));
        }
        return map_;
    }

    public static StringList allSortedFiles(String _folder) {
        FileInfo f_ = new FileInfo(new File(_folder));
        StringList files_ = new StringList();
        for (FileInfo s: getSortedDescNodes(f_)) {
            FileInfo c_ = s;
            files_.add(StringList.replaceBackSlash(c_.getInfo().getAbsolutePath()));
        }
        return files_;
    }
    static CustList<FileInfo> getSortedDescNodes(FileInfo _root) {
        CustList<FileInfo> list_ = new CustList<FileInfo>();
        if (_root == null) {
            return list_;
        }
        FileInfo c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    static FileInfo getNext(FileInfo _current, FileInfo _root) {
        FileInfo n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            FileInfo next_ = n_.getNextSibling();
            while (next_ == null) {
                FileInfo par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
    }
    public static StringList files(String _folder) {
        StringList files_ = new StringList();
        StringList current_ = new StringList(_folder);
        String folder_ = _folder;
        folder_ = new File(folder_).getAbsolutePath();
        folder_ = StringList.replaceBackSlash(folder_);
        if (folder_.endsWith(StringList.concat(SEPARATEUR,_folder))) {
            String suffix_ = StringList.concat(SEPARATEUR,_folder);
            folder_ = folder_.substring(CustList.FIRST_INDEX, folder_.length() - suffix_.length());
            folder_ = StringList.concat(folder_,SEPARATEUR);
        }
        StringList new_ = new StringList();
        while (true) {
            new_ = new StringList();
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
        files_.removePrefixInStrings(folder_);
        return files_;
    }

    public static Element documentXmlInterne(String _dossier, String _fichier) {
        Document doc_ = DocumentBuilder.parseSax(ResourceFiles.ressourceFichier(StringList.concat(_dossier,SEPARATEUR, _fichier)));
        Element element_ = doc_.getDocumentElement();
        return element_;
    }

    public static Document documentXmlExterne(String _nomFichier) {
        return DocumentBuilder.parseSax(contentsOfFile(_nomFichier));
    }

    public static int nbLines(String _nomFichier) {
        try {
            File file_ = new File(_nomFichier);
            InputStream inputStream_ = new FileInputStream(file_);
            Reader reader_ = new InputStreamReader(inputStream_, StandardCharsets.UTF_8.getName());
//            Reader reader_ = new InputStreamReader(inputStream_, StandardCharsets.ISO_8859_1);
            BufferedReader br_ = new BufferedReader(reader_);
            int i_ = 0;
            while (true) {

                String ligne_ = br_.readLine();
                if (ligne_ == null) {
                    break;
                }
                i_ ++;
            }
            br_.close();
            reader_.close();
            return i_;
        } catch (RuntimeException _0) {
            return 0;
        } catch (IOException _0) {
            return 0;
        }
    }

    public static String contentsOfFile(String _nomFichier) {
        String file_ = EMPTY_STRING;
        try {
            file_ = readFile(_nomFichier, StandardCharsets.UTF_8.getName());
            int ind_ = file_.indexOf(INVALID_CHARACTER);
            if (ind_ >= 0) {
                file_ = readFile(_nomFichier, StandardCharsets.ISO_8859_1.getName());
            }
            return file_;
        } catch (RuntimeException _0) {
            return null;
        }
    }

    private static String readFile(String _filePath, String _encoding) {
        InputStream inputStream_ = null;
        InputStreamReader reader_ = null;
        BufferedReader br_ = null;
        try {
            File file_ = new File(_filePath);
            inputStream_ = new FileInputStream(file_);
            reader_ = new InputStreamReader(inputStream_, _encoding);
            br_ = new BufferedReader(reader_);
            return readingFile(br_, file_.length());
        } catch (IOException _0) {
            return null;
        } finally {
            close(inputStream_, reader_, br_);
        }
    }

    private static void close(InputStream _inputStream,
            InputStreamReader _reader, BufferedReader _br) {
        try {
            if (_br != null) {
                _br.close();
            }
            if (_reader != null) {
                _reader.close();
            }
            if (_inputStream != null) {
                _inputStream.close();
            }
        } catch (Exception _0) {
        }
    }
    public static Element contenuDocumentXmlExterne(String _nomFichier) {
        Document doc_ = documentXmlExterne(_nomFichier);
        if (doc_ == null) {
            return null;
        }
        return doc_.getDocumentElement();
    }

    private static String readingFile(BufferedReader _br, long _capacity) {
        try {
            StringBuilder strBuilder_ = new StringBuilder((int) _capacity);
            while (true) {

                int char_ = _br.read();
                if (char_ < 0) {
                    break;
                }
                if (char_ != '\r') {
                    strBuilder_.append((char) char_);
                }
            }
            return strBuilder_.toString();
        } catch (IOException _0) {
            return null;
        }
    }

    public static boolean saveTextFile(String _nomFichier, String _text) {
        try {
            FileWriter fw_ = new FileWriter(new File(_nomFichier));
            BufferedWriter bw_ = new BufferedWriter(fw_);
            bw_.write(_text);
            bw_.close();
            fw_.close();
            return true;
        } catch (Exception _0) {
            return false;
        }
    }
    public static boolean logToFile(String _nomFichier, String _text) {
        try {
            FileWriter fw_ = new FileWriter(_nomFichier, true);
            BufferedWriter bw_ = new BufferedWriter(fw_);
            PrintWriter out_ = new PrintWriter(bw_);
            out_.println(_text);
            out_.close();
            bw_.close();
            fw_.close();
            return true;
        } catch (Exception _0) {
            return false;
        }
    }
}
