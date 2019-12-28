package code.expressionlanguage.utilcompo;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class ZipStructUtil {
    private ZipStructUtil(){
    }
    public static Struct zippedBinaryFiles(Struct _zipFileName, RunnableContextEl _ctx) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(getValue(_zipFileName));
            int len_ = zipFile_.size();
            String arr_ = ((LgNamesUtils)_ctx.getStandards()).getAliasEntryBinary();
            arr_ = PrimitiveTypeUtil.getPrettyArrayType(arr_);
            String cont_ = _ctx.getStandards().getAliasPrimByte();
            cont_ = PrimitiveTypeUtil.getPrettyArrayType(cont_);
            ArrayStruct files_ = new ArrayStruct(new Struct[len_],arr_);
            int i_ = 0;

            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                InputStream stream_ = zipFile_.getInputStream(entry_);
                byte[] bytes_ = new byte[(int) entry_.getSize()];
                int i = 0;
                while (i < bytes_.length) {
                    i += stream_.read(bytes_, i, bytes_.length - i);
                }
                stream_.close();
                int bLen_ = bytes_.length;
                ArrayStruct bs_ = new ArrayStruct(new Struct[bLen_],cont_);
                for (int j = 0; j < bLen_; j++) {
                    bs_.getInstance()[j] = new ByteStruct(bytes_[j]);
                }
                files_.getInstance()[i_] = new EntryBinaryStruct(new StringStruct(entry_.getName()),bs_,cont_);
                i_++;
            }
            return files_;
        } catch (Throwable _0) {
            return NullStruct.NULL_VALUE;
        } finally {
            tryClose(zipFile_);
        }
    }
    public static Struct zippedTextFiles(Struct _zipFileName, RunnableContextEl _ctx) {
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(getValue(_zipFileName));
            int len_ = zipFile_.size();
            String arr_ = ((LgNamesUtils)_ctx.getStandards()).getAliasEntryText();
            arr_ = PrimitiveTypeUtil.getPrettyArrayType(arr_);
            ArrayStruct files_ = new ArrayStruct(new Struct[len_],arr_);
            int i_ = 0;

            for (ZipEntry entry_ :Collections.list(zipFile_.entries())) {
                InputStream stream_ = zipFile_.getInputStream(entry_);
                byte[] bytes_ = new byte[(int) entry_.getSize()];
                int i = 0;
                while (i < bytes_.length) {
                    i += stream_.read(bytes_, i, bytes_.length - i);
                }
                stream_.close();
                String dec_ = StringList.decode(bytes_);
                if (dec_ == null) {
                    files_.getInstance()[i_] = NullStruct.NULL_VALUE;
                    i_++;
                    continue;
                }
                files_.getInstance()[i_] = new EntryTextStruct(new StringStruct(entry_.getName()),new StringStruct(dec_));
                i_++;
            }
            return files_;
        } catch (Throwable _0) {
            return NullStruct.NULL_VALUE;
        } finally {
            tryClose(zipFile_);
        }
    }
    public static Struct zippedBinaryFilesByteArray(Struct _byteArray, RunnableContextEl _ctx) {
        if (!(_byteArray instanceof ArrayStruct)) {
            return NullStruct.NULL_VALUE;
        }
        try {
            Struct[] data_ = ((ArrayStruct)_byteArray).getInstance();
            int len_ = data_.length;
            byte[] bytes_ = new byte[len_];
            for (int i = 0; i < len_; i++) {
                bytes_[i] = ((ByteStruct)data_[i]).byteStruct();
            }

            String arr_ = ((LgNamesUtils)_ctx.getStandards()).getAliasEntryBinary();
            arr_ = PrimitiveTypeUtil.getPrettyArrayType(arr_);
            String cont_ = _ctx.getStandards().getAliasPrimByte();
            cont_ = PrimitiveTypeUtil.getPrettyArrayType(cont_);
            ByteArrayInputStream bais_ = new ByteArrayInputStream(bytes_);
            ZipInputStream zis_ = new ZipInputStream(bais_);
            CustList<EntryBinaryStruct> filesMap_ = new CustList<EntryBinaryStruct>();
            while (true) {
                ZipEntry e_ = zis_.getNextEntry();
                if (e_ == null) {
                    break;
                }
                if (e_.isDirectory()) {
                    ArrayStruct bs_ = new ArrayStruct(new Struct[0],cont_);
                    filesMap_.add(new EntryBinaryStruct(new StringStruct(e_.getName()),bs_,cont_));
                    zis_.closeEntry();
                    continue;
                }
                byte[] bytesFile_ = new byte[(int) e_.getSize()];
                int i = 0;
                int lengthFile_ = bytesFile_.length;
                while (i < lengthFile_) {
                    i += zis_.read(bytesFile_, i, lengthFile_ - i);
                }
                ArrayStruct bs_ = new ArrayStruct(new Struct[lengthFile_],cont_);
                for (int j = 0; j < lengthFile_; j++) {
                    bs_.getInstance()[j] = new ByteStruct(bytesFile_[j]);
                }
                filesMap_.add(new EntryBinaryStruct(new StringStruct(e_.getName()),bs_,cont_));
                zis_.closeEntry();
            }
            zis_.close();

            ArrayStruct files_ = new ArrayStruct(new Struct[filesMap_.size()],arr_);
            int i_ = 0;
            for (EntryBinaryStruct entry_ :filesMap_) {
                files_.getInstance()[i_] = entry_;
                i_++;
            }
            return files_;
        } catch (Throwable _0) {
            return NullStruct.NULL_VALUE;
        }
    }
    static void tryClose(ZipFile _zipFile) {
        if (_zipFile != null) {
            try {
                _zipFile.close();
            } catch (Throwable _0) {
            }
        }
    }

    public static BooleanStruct zipBinFiles(Struct _zipFileName, Struct _files) {
        try {
            FileOutputStream fos_ = new FileOutputStream(getValue(_zipFileName));
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            if (_files instanceof ArrayStruct) {
                for (Struct s: ((ArrayStruct)_files).getInstance()) {
                    if (!(s instanceof EntryBinaryStruct)) {
                        continue;
                    }
                    EntryBinaryStruct e_ = (EntryBinaryStruct)s;
                    ZipEntry ze_ = new ZipEntry(e_.getName().getInstance());
                    zos_.putNextEntry(ze_);
                    Struct[] as_ = e_.getBinary().getInstance();
                    int len_ = as_.length;
                    byte[] file_ = new byte[len_];
                    for (int i = 0; i < len_; i++) {
                        Struct byte_ = as_[i];
                        if (!(byte_ instanceof ByteStruct)) {
                            continue;
                        }
                        file_[i] = ((ByteStruct)byte_).byteStruct();
                    }
                    zos_.write(file_);
                }
            }
            zos_.closeEntry();
            // remember close it
            zos_.close();
            return new BooleanStruct(true);
        } catch (Throwable _0) {
            return new BooleanStruct(false);
        }
    }

    public static BooleanStruct zipTextFiles(Struct _zipFileName, Struct _files) {
        try{
            FileOutputStream fos_ = new FileOutputStream(getValue(_zipFileName));
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            if (_files instanceof ArrayStruct) {
                for (Struct s: ((ArrayStruct)_files).getInstance()) {
                    if (!(s instanceof EntryTextStruct)) {
                        continue;
                    }
                    EntryTextStruct e_ = (EntryTextStruct)s;
                    ZipEntry ze_ = new ZipEntry(e_.getName().getInstance());
                    zos_.putNextEntry(ze_);
                    byte[] file_ = StringList.encode(e_.getText().getInstance());
                    zos_.write(file_);
                }
            }
            zos_.closeEntry();
            //remember close it
            zos_.close();
            return new BooleanStruct(true);
        }catch(Throwable _0){
            return new BooleanStruct(false);
        }
    }

    public static Struct zipBinFiles(Struct _files, RunnableContextEl _ctx) {
        try {
            String cont_ = _ctx.getStandards().getAliasPrimByte();
            cont_ = PrimitiveTypeUtil.getPrettyArrayType(cont_);
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ZipOutputStream zos_ = new ZipOutputStream(baos_);
            if (_files instanceof ArrayStruct) {
                for (Struct s: ((ArrayStruct)_files).getInstance()) {
                    if (!(s instanceof EntryBinaryStruct)) {
                        continue;
                    }
                    EntryBinaryStruct e_ = (EntryBinaryStruct)s;
                    ZipEntry ze_ = new ZipEntry(e_.getName().getInstance());
                    zos_.putNextEntry(ze_);
                    Struct[] as_ = e_.getBinary().getInstance();
                    int len_ = as_.length;
                    byte[] file_ = new byte[len_];
                    for (int i = 0; i < len_; i++) {
                        Struct byte_ = as_[i];
                        if (!(byte_ instanceof ByteStruct)) {
                            continue;
                        }
                        file_[i] = ((ByteStruct)byte_).byteStruct();
                    }
                    zos_.write(file_);
                }
            }
            zos_.closeEntry();
            // remember close it
            zos_.close();
            //baos_
            byte[] exp_ = baos_.toByteArray();
            int lengthFile_ = exp_.length;
            ArrayStruct bs_ = new ArrayStruct(new Struct[lengthFile_],cont_);
            for (int j = 0; j < lengthFile_; j++) {
                bs_.getInstance()[j] = new ByteStruct(exp_[j]);
            }
            return bs_;
        } catch (Throwable _0) {
            return NullStruct.NULL_VALUE;
        }
    }
    private static String getValue(Struct _str) {
        if (_str instanceof StringStruct) {
            return ((StringStruct)_str).getInstance();
        }
        return "";
    }
}
