package code.expressionlanguage.utilcompo;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class ZipBinStructUtil {
    private ZipBinStructUtil() {
    }

    public static Struct zippedBinaryFilesByteArray(Struct _byteArray, RunnableContextEl _ctx) {
        if (!(_byteArray instanceof ArrayStruct)) {
            return NullStruct.NULL_VALUE;
        }
        int len_ = ((ArrayStruct)_byteArray).getLength();
        byte[] bytes_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            bytes_[i] = ((ByteStruct)((ArrayStruct)_byteArray).get(i)).byteStruct();
        }

        CustList<EntryBinaryStruct> filesMap_ = getEntryBinaryStructs(bytes_, _ctx);
        if (filesMap_ == null) {
            return NullStruct.NULL_VALUE;
        }

        String arr_ = ((LgNamesWithNewAliases)_ctx.getStandards()).getCustAliases().getAliasEntryBinary();
        arr_ = StringExpUtil.getPrettyArrayType(arr_);
        ArrayStruct files_ = new ArrayStruct(filesMap_.size(),arr_);
        int i_ = 0;
        for (EntryBinaryStruct entry_ :filesMap_) {
            files_.set(i_, entry_);
            i_++;
        }
        return files_;
    }

    public static CustList<EntryBinaryStruct> getEntryBinaryStructs(byte[] bytes_, RunnableContextEl _ctx) {
        try {
            String cont_ = _ctx.getStandards().getContent().getPrimTypes().getAliasPrimByte();
            cont_ = StringExpUtil.getPrettyArrayType(cont_);
            ByteArrayInputStream bais_ = new ByteArrayInputStream(bytes_);
            ZipInputStream zis_ = new ZipInputStream(bais_);
            CustList<EntryBinaryStruct> filesMap_ = new CustList<EntryBinaryStruct>();
            while (true) {
                ZipEntry e_ = zis_.getNextEntry();
                if (e_ == null) {
                    break;
                }
                if (e_.isDirectory()) {
                    ArrayStruct bs_ = new ArrayStruct(0,cont_);
                    filesMap_.add(new EntryBinaryStruct(new StringStruct(e_.getName()),bs_));
                    zis_.closeEntry();
                    continue;
                }
                byte[] bytesFile_ = new byte[(int) e_.getSize()];
                int i = 0;
                int lengthFile_ = bytesFile_.length;
                while (i < lengthFile_) {
                    i += zis_.read(bytesFile_, i, lengthFile_ - i);
                }
                ArrayStruct bs_ = new ArrayStruct(lengthFile_,cont_);
                for (int j = 0; j < lengthFile_; j++) {
                    bs_.set(j, new ByteStruct(bytesFile_[j]));
                }
                filesMap_.add(new EntryBinaryStruct(new StringStruct(e_.getName()),bs_));
                zis_.closeEntry();
            }
            zis_.close();
            return filesMap_;
        } catch (IOException _0) {
            return null;
        }
    }

    public static Struct zipBinFiles(Struct _files, RunnableContextEl _ctx) {
        byte[] exp_ = getZipBinFileAsArray(_files);
        if (exp_ == null) {
            return NullStruct.NULL_VALUE;
        }
        int lengthFile_ = exp_.length;
        String cont_ = _ctx.getStandards().getContent().getPrimTypes().getAliasPrimByte();
        cont_ = StringExpUtil.getPrettyArrayType(cont_);
        ArrayStruct bs_ = new ArrayStruct(lengthFile_,cont_);
        for (int j = 0; j < lengthFile_; j++) {
            bs_.set(j, new ByteStruct(exp_[j]));
        }
        return bs_;
    }

    public static byte[] getZipBinFileAsArray(Struct _files) {
        try {
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
                    int len_ = e_.getBinary().getLength();
                    byte[] file_ = new byte[len_];
                    for (int i = 0; i < len_; i++) {
                        Struct byte_ = e_.getBinary().get(i);
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
            return baos_.toByteArray();
        } catch (IOException _0) {
            return null;
        }
    }
}
