package code.stream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import code.util.StringList;
import code.util.StringMap;

public final class StreamZipFile {

    private StreamZipFile() {
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
                InputStream stream_ = zipFile_.getInputStream(entry_);
                byte[] bytes_ = new byte[(int) entry_.getSize()];
                stream_.read(bytes_);
                stream_.close();
                files_.put(entry_.getName(), bytes_);
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

    public static boolean zipFiles(String _zipFileName, StringMap<String> _files) {
        try{
            FileOutputStream fos_ = new FileOutputStream(_zipFileName);
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            for (String n: _files.getKeys()) {
                String file_ = _files.getVal( n);
                ZipEntry ze_ = new ZipEntry(n);
                zos_.putNextEntry(ze_);
                zos_.write(StringList.encode(file_));
            }
            zos_.closeEntry();
            //remember close it
            zos_.close();
            return true;
        }catch(Throwable _0){
        	return false;
        }
    }

    public static boolean zipBinFiles(String _zipFileName, StringMap<byte[]> _files) {
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
            return true;
        } catch (Throwable _0) {
        	return false;
        }
    }

}
