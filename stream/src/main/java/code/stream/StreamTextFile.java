package code.stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import code.resources.ResourceFiles;
import code.serialize.SerializeXmlObject;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.InsCaseStringMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class StreamTextFile {

    public static final String SEPARATEUR = "/";
    private static final String LINE_RETURN = "\n";
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}.properties";
    private static final String WEB_SEPARATOR = "/";
    private static final String SEPARATOR_HTTP_WEB_PAGE = WEB_SEPARATOR+WEB_SEPARATOR;
    private static final String XML_SCHEMA = "http:"+SEPARATOR_HTTP_WEB_PAGE+"www.w3.org/2001/XMLSchema";
    private static final String EMPTY_STRING = Constants.EMPTY_STRING;
    private static final String DOT = ".";
    private static final char INVALID_CHARACTER = 65533;

    private static boolean _showReadStackTrace_ = true;

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
            if (new File(_folder+f).isDirectory()) {
                continue;
            }
            map_.put(f, contentsOfFile(_folder+f));
        }
        return map_;
    }

    public static InsCaseStringMap<String> getTextFilesIns(String _folder) {
        InsCaseStringMap<String> map_ = new InsCaseStringMap<String>();
        for (String f: files(_folder)) {
            if (new File(_folder+f).isDirectory()) {
                continue;
            }
            map_.put(f, contentsOfFile(_folder+f));
        }
        return map_;
    }

    public static void purgeFolder(String _folder, boolean _deleteRoot) {
        String folder_ = StringList.replaceBackSlash(_folder);
        StringList files_ = files(folder_);
        StringList folders_ = new StringList();
        for (String f: files_) {
            if (new File(folder_+f).isDirectory()) {
                folders_.add(folder_+f);
                continue;
            }
            new File(folder_+f).delete();
        }
        while (!folders_.isEmpty()) {
            StringList newFolders_ = new StringList();
            for (String f: folders_) {
                if (!new File(f).delete()) {
                    newFolders_.add(f);
                }
            }
            folders_ = newFolders_;
        }
        if (_deleteRoot) {
            new File(folder_).delete();
        }
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
        if (folder_.endsWith(SEPARATEUR+_folder)) {
            String suffix_ = SEPARATEUR+_folder;
            folder_ = folder_.substring(CustList.FIRST_INDEX, folder_.length() - suffix_.length());
            folder_ += SEPARATEUR;
        }
//        folder_ = folder_.replaceAll(SEPARATEUR+_folder+END, SEPARATEUR);
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
//        files_.replaceRegExpInStrings(BEGIN+folder_, EMPTY_STRING);
        files_.removePrefixInStrings(folder_);
        return files_;
    }

    public static String getPropertiesPath(String _folder, String _language, String _file) {
        return StringList.simpleFormat(PROPERTIES_PATTERN, _folder, _language, StringList.replace(_file, DOT, SEPARATEUR).toLowerCase());
    }

    public static Element documentXmlInterne(String _dossier, String _fichier) {
        Document doc_ = DocumentBuilder.parseSax(ResourceFiles.ressourceFichier(_dossier +SEPARATEUR + _fichier));
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
            Reader reader_ = new InputStreamReader(inputStream_, Charset.forName(StandardCharsets.UTF_8.getName()));
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
            _0.printStackTrace();
            return 0;
        } catch (IOException _0) {
            _0.printStackTrace();
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
            if (_showReadStackTrace_) {
                _0.printStackTrace();
            }
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
            reader_ = new InputStreamReader(inputStream_, Charset.forName(_encoding));
            br_ = new BufferedReader(reader_);
            return readingFile(LINE_RETURN, br_, file_.length());
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
        } catch (RuntimeException _0) {
        } catch (IOException _0) {
        }
    }
    public static boolean validateXml(String _xmlFileName, String _xsdFileName) {
        SchemaFactory factory_ = SchemaFactory.newInstance(XML_SCHEMA);
//        FileInputStream fis_ = null;
        try {
//            fis_=new FileInputStream(new File(_xsdFileName));
//            InputSource sourceentree_ = new InputSource(fis_);
//            SAXSource sourceXsd_ = new SAXSource(sourceentree_);
            Schema schema_ = factory_.newSchema(new File(_xsdFileName));
            Validator validator_ = schema_.newValidator();
            validator_.validate(new StreamSource(new File(_xmlFileName)));
            return true;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return false;
        } catch (SAXException _0) {
            _0.printStackTrace();
            return false;
        } catch (IOException _0) {
            _0.printStackTrace();
            return false;
        }
        /*finally {
            if (fis_ != null) {
                fis_.close();
            }
        }*/
    }

    public static Element contenuDocumentXmlExterne(String _nomFichier) {
        Document doc_ = documentXmlExterne(_nomFichier);
        if (doc_ == null) {
            return null;
        }
        return doc_.getDocumentElement();
    }

    /**@param lignes_
    @param _saut
    @param in_
    @return
    @throws IOException*/

    private static String readingFile(String _saut, BufferedReader _br, long _capacity) {
        try {
            StringBuilder strBuilder_ = new StringBuilder((int) _capacity);
            while (true) {

                String ligne_ = _br.readLine();
                if (ligne_ == null) {
                    break;
                }
                strBuilder_.append(ligne_);
                strBuilder_.append(_saut);
            }
            return strBuilder_.toString();
        } catch (IOException _0) {
            return null;
        }
    }

    public static Object loadObject(String _nomFichier) {
        String content_ = contentsOfFile(_nomFichier);
        try {
            return SerializeXmlObject.fromXmlStringObject(content_);
        } catch (Throwable _0) {
            return null;
        }
    }

    public static Object loadResourceObject(String _nomFichier) {
        String content_ = ResourceFiles.ressourceFichier(_nomFichier);
        try {
            return SerializeXmlObject.fromXmlStringObject(content_);
        } catch (Throwable _0) {
            return null;
        }
    }

    public static void saveTextFile(String _nomFichier, String _text) {
        try {
            FileWriter fw_ = new FileWriter(new File(_nomFichier));
            BufferedWriter bw_ = new BufferedWriter(fw_);
            bw_.write(_text);
            bw_.close();
            fw_.close();
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        } catch (IOException _0) {
            _0.printStackTrace();
        }
    }
    public static void saveObject(String _fileName, Object _object) {
//        if (!SecurityManagerUtil.canUseReflection()) {
//            XMLEncoder encoder_ = null;
//            try {
//                encoder_ = new XMLEncoder(new FileOutputStream(_fileName));
//                encoder_.writeObject(_object);
//                encoder_.flush();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                if (encoder_ != null) {
//                    encoder_.close();
//                }
//            }
//            return;
//        }
        saveTextFile(_fileName, SerializeXmlObject.toXmlString(_object));
//        try {
//            DOMSource source_ = SerializeXmlObject.getSource(_object);
//            StreamResult result_ = new StreamResult(new File(_fileName));
//            DocumentBuilder.getTransformer(DocumentBuilder.isIndentXmlWhileWriting()).transform(source_, result_);
//        } catch (ParserConfigurationException ex) {
//            ex.printStackTrace();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        } catch (DOMException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static boolean isShowReadStackTrace() {
        return _showReadStackTrace_;
    }

    public static void setShowReadStackTrace(boolean _showReadStackTrace) {
        _showReadStackTrace_ = _showReadStackTrace;
    }
}
