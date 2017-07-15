package code.stream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import code.stream.exceptions.RuntimeIOException;
import code.util.CustList;
import code.util.InsCaseStringMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;

public final class StreamZipFile {

    private static final int MAX_BYTES = 1000000000;

    private static final String EMPTY_STRING = "";
    private static final String CLASS_EXT = "class";
    private static final String RETURN_LINE = "\n";
//    private static final String BEGIN = "^";
    private static final String DOT = ".";
//    private static final String REL_PATH_REGEXP = "/[^/]+$";
    private static final StringList FILES_JAR = new StringList();
    private StreamZipFile() {
    }
    public static String getInsensitiveCaseFileInJar(String _file) {
        String file_ = StringList.replaceBackSlash(_file);
        for (String s: getFilesInJar()) {
            if (s.equalsIgnoreCase(file_)) {
                return s;
            }
        }
        return _file;
    }
    public static StringList getFilesInJar() {
        if (FILES_JAR.isEmpty()) {
            FILES_JAR.addAllElts(zippedFiles());
            FILES_JAR.replaceBackSlashesInStrings();
        }
        return new StringList(FILES_JAR);
    }
    public static StringMap<String> zippedTextFiles(String _zipFileName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            StringMap<String> files_ = new StringMap<String>();
            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                String str_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), str_);
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }
    public static InsCaseStringMap<String> zippedTextFilesIns(String _zipFileName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            InsCaseStringMap<String> files_ = new InsCaseStringMap<String>();
            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                String str_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), str_);
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }

    public static StringList classesFromCurrentJar() {
        return classesFromJar(ConstFiles.getJarPath());
    }

    public static StringList classesFromJar(String _jarFileName) {
        StringList classNames_ = new StringList();
        try {
            FileInputStream fis_ = new FileInputStream(_jarFileName);
            ZipInputStream zip_ = new ZipInputStream(fis_);
            ZipEntry entry_ = zip_.getNextEntry();
            while (entry_ != null) {
                if (!entry_.getName().endsWith(DOT+CLASS_EXT)) {
                    entry_ = zip_.getNextEntry();
                    continue;
                }
                if (entry_.isDirectory()) {
                    entry_ = zip_.getNextEntry();
                    continue;
                }
                // This ZipEntry represents a class. Now, what class does it represent?
                StringBuilder className_ = new StringBuilder();
                for (String part_ : StringList.splitStrings(entry_.getName(),StreamTextFile.SEPARATEUR)) {
                    if (className_.length() != 0) {
                        className_.append(DOT);
                    }
                    className_.append(part_);
                    if (part_.endsWith(DOT+CLASS_EXT)) {
                        className_.setLength(className_.length()-(DOT+CLASS_EXT).length());
                    }
                }
                classNames_.add(className_.toString());
                entry_=zip_.getNextEntry();
            }
            zip_.close();
            fis_.close();
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        } catch (IOException _0) {
            _0.printStackTrace();
        }
        return classNames_;
    }

//    public static Map<String, byte[]> zippedBinaryFilesFromCurrentJar(Class<?> _mainClass) {
//        String path_ = _mainClass.getProtectionDomain().getCodeSource().getLocation().getPath();
//        try {
//            return zippedBinaryFiles(path_);
//        } catch (Exception e) {
//            Map<String, byte[]> map_ = new Map<>();
//            for (String f: StreamTextFile.files(path_)) {
//                map_.put(f, StreamBinaryFile.loadFile(path_+f));
//            }
//            return map_;
//        }
//    }
    public static StringMap<byte[]> zippedBinaryFilesFromCurrentJar() {
//        String path_ = Constants.getMainClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String path_ = ConstFiles.getJarPath();
        try {
            return zippedBinaryFiles(path_);
        } catch (RuntimeException _0) {
            StringMap<byte[]> map_ = new StringMap<byte[]>();
            for (String f: StreamTextFile.files(path_)) {
                map_.put(f, StreamBinaryFile.loadFile(path_+f));
            }
            return map_;
        }
    }

    public static StringList zippedFiles() {
        StringList files_ = new StringList();
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(ConstFiles.getJarPath());
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                files_.add(entry_.getName());
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }

    public static StringList zippedFiles(String _zipFileName) {
        StringList files_ = new StringList();
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                files_.add(entry_.getName());
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }
    public static StringMap<byte[]> zippedBinaryFiles(String _zipFileName) {
        //byte[] arr_ = new byte[1024];
        StringMap<byte[]> files_ = new StringMap<byte[]>();
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            while(entries_.hasMoreElements()){
                int index_ = 0;
                ZipEntry entry_ = entries_.nextElement();
                if (entry_.getSize() < MAX_BYTES) {
                    InputStream stream_ = zipFile_.getInputStream(entry_);
                    byte[] bytes_ = new byte[(int) entry_.getSize()];
                    while (true) {
                        int read_ = stream_.read(bytes_, index_, (int) (entry_.getSize() - index_ ));
                        if (read_ == -1) {
                            break;
                        }
                        if (index_ == entry_.getSize()) {
                            break;
                        }
                        index_ += read_;
                    }
                    stream_.close();
                    files_.put(entry_.getName(), bytes_);
                }
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (IOException _0) {
                } catch (RuntimeException _0) {
                }
            }
        }
    }
    public static StringMap<String>
    contentsOfZippedFilesFromFolder(String _zipFileName, String _folderName) {
//        Pattern patt_ = Pattern.compile(BEGIN+Pattern.quote(_folderName)+REL_PATH_REGEXP);
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            StringMap<String> files_ = new StringMap<String>();
            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                String fileName_ = entry_.getName();
                if (!fileName_.startsWith(_folderName+StreamTextFile.SEPARATEUR)) {
                    continue;
                }
                if (StringList.quickEq(fileName_,_folderName+StreamTextFile.SEPARATEUR)) {
                    continue;
                }
//                if (!patt_.matcher(fileName_).matches()) {
//                    continue;
//                }
                String file_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), file_);
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }

    public static StringMap<String>
    contentsOfZippedFiles(String _zipFileName, StringList _relativePathZippedFiles) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            StringMap<String> files_ = new StringMap<String>();
            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                if (!_relativePathZippedFiles.containsObj(entry_.getName())&&!_relativePathZippedFiles.isEmpty()) {
                    continue;
                }
                String file_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), file_);
            }
            return files_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (IOException _0) {
                } catch (RuntimeException _0) {
                }
            }
        }
    }

    public static String contentsOfZippedFile(String _zipFileName, String _relativePathZippedFile) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            String file_ = EMPTY_STRING;
            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                if (!StringList.quickEq(entry_.getName(),_relativePathZippedFile)) {
                    continue;
                }
                file_ = getContentOfZippedFile(zipFile_, entry_);
                break;
            }
            return file_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }

    static String getContentOfZippedFile(ZipFile _zipFile, ZipEntry _entry) {
        StringBuilder fileBuilder_ = new StringBuilder();
        InputStream stream_ = null;
        try {
            stream_ = _zipFile.getInputStream(_entry);
        } catch (IOException _0) {
            throw new RuntimeIOException(_0);
        }
        BufferedReader br_ = null;
        InputStreamReader isr_ = null;
        try {
            isr_ = new InputStreamReader(stream_);
            br_ = new BufferedReader(isr_);
            while (true) {
                String line_ = br_.readLine();
                if (line_ == null) {
                    break;
                }
                fileBuilder_.append(line_);
                fileBuilder_.append(RETURN_LINE);
            }
        } catch (IOException _0) {
            throw new RuntimeIOException(_0);
        } finally {
            if (br_ != null) {
                try {
                    br_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
            if (isr_ != null) {
                try {
                    isr_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
            if (stream_ != null) {
                try {
                    stream_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
        if (fileBuilder_.length() == CustList.SIZE_EMPTY) {
            return fileBuilder_.toString();
        }
        fileBuilder_.deleteCharAt(fileBuilder_.length() - 1);
        return fileBuilder_.toString();
    }

    public static void zipFiles(String _zipFileName, StringMap<String> _files) {
        try{
            FileOutputStream fos_ = new FileOutputStream(_zipFileName);
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            for (String n: _files.getKeys()) {
                String file_ = _files.getVal( n);
                ZipEntry ze_ = new ZipEntry(n);
                zos_.putNextEntry(ze_);
                zos_.write(file_.getBytes());
            }
            zos_.closeEntry();
            //remember close it
            zos_.close();
        }catch(IOException _0){
            _0.printStackTrace();
        }
    }

    public static void zipBinFiles(String _zipFileName, StringMap<byte[]> _files) {
        try {
            FileOutputStream fos_ = new FileOutputStream(_zipFileName);
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            for (String n : _files.getKeys()) {
                byte[] file_ = _files.getVal(n);
                ZipEntry ze_ = new ZipEntry(n);
                zos_.putNextEntry(ze_);
                zos_.write(file_);
            }
            zos_.closeEntry();
            // remember close it
            zos_.close();
        } catch (IOException _0) {
            _0.printStackTrace();
        }
    }

}
