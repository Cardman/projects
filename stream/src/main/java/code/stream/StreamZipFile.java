package code.stream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import code.stream.exceptions.RuntimeIOException;
import code.util.InsCaseStringMap;
import code.util.StringList;
import code.util.StringMap;

public final class StreamZipFile {

    private static final long MAX_BYTES = Long.MAX_VALUE;

    private static final int BYTE_ARRAY_SIZE = 4096;
    private static final String EMPTY_STRING = "";
    private static final String CLASS_EXT = "class";

    private static final String DOT = ".";

    private StreamZipFile() {
    }
    public static StringMap<String> zippedTextFiles(String _zipFileName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            StringMap<String> files_ = new StringMap<String>();
            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                String str_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), str_);
            }
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }
    public static InsCaseStringMap<String> zippedTextFilesIns(String _zipFileName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            InsCaseStringMap<String> files_ = new InsCaseStringMap<String>();
            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                String str_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), str_);
            }
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }
    public static InsCaseStringMap<String> zippedTextFilesInsFromBytes(String _zipFileName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            InsCaseStringMap<String> files_ = new InsCaseStringMap<String>();
            FileInputStream fis_ = new FileInputStream(_zipFileName);
            ZipInputStream zip_ = new ZipInputStream(fis_);
            ZipEntry entry_ = zip_.getNextEntry();
            while (entry_ != null) {
                if (entry_.isDirectory()) {
                    entry_ = zip_.getNextEntry();
                    continue;
                }
                int index_ = 0;
                String fileName_ = entry_.getName();
                byte[] bytes_ = new byte[(int) entry_.getSize()];
                while (true) {
                    int res_ = zip_.read(bytes_, index_, Math.min(bytes_.length - index_, BYTE_ARRAY_SIZE));
                    if (res_ < 1) {
                        break;
                    }
                    index_+=res_;
                }
                files_.put(fileName_, new String(bytes_, StandardCharsets.UTF_8.getName()));
                entry_=zip_.getNextEntry();
            }
            zip_.close();
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }

    public static StringList classesFromJar(String _jarFileName) {
        StringList classNames_ = new StringList();
        try {
            FileInputStream fis_ = new FileInputStream(_jarFileName);
            ZipInputStream zip_ = new ZipInputStream(fis_);
            ZipEntry entry_ = zip_.getNextEntry();
            while (entry_ != null) {
                if (!entry_.getName().endsWith(StringList.concat(DOT,CLASS_EXT))) {
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
                    if (part_.endsWith(StringList.concat(DOT,CLASS_EXT))) {
                        className_.setLength(className_.length()-StringList.concat(DOT,CLASS_EXT).length());
                    }
                }
                classNames_.add(className_.toString());
                entry_=zip_.getNextEntry();
            }
            zip_.close();
            fis_.close();
        } catch (Throwable _0) {
        }
        return classNames_;
    }

    public static StringList zippedFiles(String _zipFileName) {
        StringList files_ = new StringList();
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                files_.add(entry_.getName());
            }
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }
    public static StringMap<byte[]> zippedBinaryFiles(String _zipFileName) {
        StringMap<byte[]> files_ = new StringMap<byte[]>();
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                int index_ = 0;
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
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }
    public static StringMap<String>
    contentsOfZippedFilesFromFolder(String _zipFileName, String _folderName) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            StringMap<String> files_ = new StringMap<String>();
            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                String fileName_ = entry_.getName();
                if (!fileName_.startsWith(StringList.concat(_folderName,StreamTextFile.SEPARATEUR))) {
                    continue;
                }
                if (StringList.quickEq(fileName_,StringList.concat(_folderName,StreamTextFile.SEPARATEUR))) {
                    continue;
                }
                String file_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), file_);
            }
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }

    public static StringMap<String>
    contentsOfZippedFiles(String _zipFileName, StringList _relativePathZippedFiles) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            StringMap<String> files_ = new StringMap<String>();
            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                if (!_relativePathZippedFiles.containsObj(entry_.getName())&&!_relativePathZippedFiles.isEmpty()) {
                    continue;
                }
                String file_ = getContentOfZippedFile(zipFile_, entry_);
                files_.put(entry_.getName(), file_);
            }
            return files_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }

    public static String contentsOfZippedFile(String _zipFileName, String _relativePathZippedFile) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);

            String file_ = EMPTY_STRING;
            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                if (!StringList.quickEq(entry_.getName(),_relativePathZippedFile)) {
                    continue;
                }
                file_ = getContentOfZippedFile(zipFile_, entry_);
                break;
            }
            return file_;
        } catch (Throwable _0) {
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (Throwable _0) {
                }
            }
        }
    }

    static String getContentOfZippedFile(ZipFile _zipFile, ZipEntry _entry) {
        long estimated_ = _entry.getSize();
        if (estimated_ < 0) {
            estimated_ = 0;
        }
        StringBuilder fileBuilder_ = new StringBuilder((int) estimated_);
        InputStream stream_ = null;
        try {
            stream_ = _zipFile.getInputStream(_entry);
        } catch (Throwable _0) {
            throw new RuntimeIOException(_0.getMessage());
        }
        BufferedReader br_ = null;
        InputStreamReader isr_ = null;
        try {
            isr_ = new InputStreamReader(stream_);
            br_ = new BufferedReader(isr_);
            while (true) {
                int char_ = br_.read();
                if (char_ < 0) {
                    break;
                }
                fileBuilder_.append((char)char_);
            }
        } catch (Throwable _0) {
            throw new RuntimeIOException(_0.getMessage());
        } finally {
            if (br_ != null) {
                try {
                    br_.close();
                } catch (Throwable _0) {
                }
            }
            if (isr_ != null) {
                try {
                    isr_.close();
                } catch (Throwable _0) {
                }
            }
            if (stream_ != null) {
                try {
                    stream_.close();
                } catch (Throwable _0) {
                }
            }
        }
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
        }catch(Throwable _0){
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
        } catch (Throwable _0) {
        }
    }

}
