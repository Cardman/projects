package code.stream;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.core.TechStreams;
import code.util.CustList;
import code.util.StringList;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class StreamTextFile {

    public static final String SEPARATEUR = "/";

    private StreamTextFile() {
    }

//    public static StringList getExcludedFolders(AbstractFileCoreStream _fileCoreStream, String _current, String _path) {
//        if (_fileCoreStream.newFile(_current+_path).exists()) {
//            return new StringList();
//        }
//        for (String p: StringUtil.splitChars(_path,';')) {
//            if (p.endsWith(".exe")) {
//                return new StringList("jre");
//            }
//        }
//        return new StringList();
//    }
    public static StringList allSortedFiles(String _folder,AbstractFileCoreStream _fact) {
        AbstractFile abstractFile_ = _fact.newFile(_folder);
        FileInfo f_ = new FileInfo(abstractFile_,_fact);
        StringList files_ = new StringList();
        for (FileInfo s: getSortedDescNodes(_fact,f_)) {
            files_.add(StringUtil.replaceBackSlash(s.getInfo().getAbsolutePath()));
        }
        return files_;
    }
    private static CustList<FileInfo> getSortedDescNodes(AbstractFileCoreStream _fact,FileInfo _root) {
        CustList<FileInfo> list_ = new CustList<FileInfo>();
        FileInfo c_ = _root;
        while (c_ != null) {
            list_.add(c_);
            c_ = getNext(_fact,c_, _root);
        }
        return list_;
    }

    private static FileInfo getNext(AbstractFileCoreStream _fact,FileInfo _current, FileInfo _root) {
        FileInfo n_ = _current.getFirstChild(_fact);
        if (n_ != null) {
            return n_;
        }
        FileInfo curr_ = _current;
        while (true) {
            FileInfo next_ = curr_.getNextSibling(_fact);
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
    public static StringList files(String _folder,AbstractFileCoreStream _fact) {
        StringList files_ = new StringList();
        StringList current_ = new StringList(_folder);
        String folder_ = StringUtil.replaceBackSlash(_fact.newFile(_folder).getAbsolutePath());
        while (true) {
            StringList new_ = new StringList();
            for (String c : current_) {
                FileListInfo filesFolder_ = PathsUtil.abs(_fact.newFile(c),_fact);
                for (AbstractFile f : filesFolder_.getNames()) {
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

    public static String contentsOfFile(String _nomFichier,AbstractFileCoreStream _fact, TechStreams _tech) {
        return contentsOfFile(_nomFichier,new DefaultUniformingString(),_fact,_tech);
    }

    public static String contentsOfFile(String _nomFichier, UniformingString _apply,AbstractFileCoreStream _fact, TechStreams _tech) {
        return readFile(_nomFichier,_apply,_fact,_tech);
    }

    private static String readFile(String _filePath, UniformingString _apply, AbstractFileCoreStream _fact, TechStreams _tech) {
        AbstractFile file_ = _fact.newFile(_filePath);
        return _tech.getTextFact().contentsOfFile(_filePath,_apply, file_.length());
    }

    public static Element contenuDocumentXmlExterne(String _nomFichier,AbstractFileCoreStream _fact, TechStreams _tech) {
        Document doc_ = DocumentBuilder.parseSax(contentsOfFile(_nomFichier,_fact,_tech));
        if (doc_ == null) {
            return null;
        }
        return doc_.getDocumentElement();
    }

    public static boolean saveTextFile(String _nomFichier, String _text, TechStreams _tech) {
        return write(StringUtil.nullToEmpty(_nomFichier), StringUtil.nullToEmpty(_text), false,_tech);
    }

    public static boolean logToFile(String _nomFichier, String _text, TechStreams _tech) {
        return write(StringUtil.nullToEmpty(_nomFichier), StringUtil.nullToEmpty(_text), true,_tech);
    }

    private static boolean write(String _nomFichier, String _text, boolean _append, TechStreams _tech) {
        return _tech.getTextFact().write(_nomFichier, _text, _append);
    }

}
