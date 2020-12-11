package code.expressionlanguage.utilcompo;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.core.ContentTime;
import code.stream.core.StreamZipFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

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
            bytes_[i] = NumParsers.convertToNumber(((ArrayStruct)_byteArray).get(i)).byteStruct();
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

    public static CustList<EntryBinaryStruct> getEntryBinaryStructs(byte[] _bytes, RunnableContextEl _ctx) {
        StringMap<ContentTime> unzip_ = StreamZipFile.zippedBinaryFiles(_bytes);
        if (unzip_ == null) {
            return null;
        }
        String cont_ = _ctx.getStandards().getContent().getPrimTypes().getAliasPrimByte();
        cont_ = StringExpUtil.getPrettyArrayType(cont_);
        CustList<EntryBinaryStruct> filesMap_ = new CustList<EntryBinaryStruct>();
        for (EntryCust<String,ContentTime> e: unzip_.entryList()) {
            byte[] bytesFile_ = e.getValue().getContent();
            int lengthFile_ = bytesFile_.length;
            ArrayStruct bs_ = new ArrayStruct(lengthFile_,cont_);
            for (int j = 0; j < lengthFile_; j++) {
                bs_.set(j, new ByteStruct(bytesFile_[j]));
            }
            filesMap_.add(new EntryBinaryStruct(new StringStruct(e.getKey()),bs_));
        }
        return filesMap_;
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
        if (_files instanceof ArrayStruct) {
            StringMap<ContentTime> files_ = new StringMap<ContentTime>();
            int fileLength_ = ((ArrayStruct) _files).getLength();
            for (int j = 0; j < fileLength_; j++) {
                Struct s = ((ArrayStruct) _files).get(j);
                if (!(s instanceof EntryBinaryStruct)) {
                    continue;
                }
                EntryBinaryStruct e_ = (EntryBinaryStruct)s;
                int len_ = e_.getBinary().getLength();
                byte[] file_ = new byte[len_];
                for (int i = 0; i < len_; i++) {
                    Struct byte_ = e_.getBinary().get(i);
                    file_[i] = NumParsers.convertToNumber(byte_).byteStruct();
                }
                files_.addEntry(e_.getName().getInstance(),new ContentTime(file_,0));
            }
            return StreamZipFile.zipBinFiles(files_);
        }
        return StreamZipFile.zipBinFiles(new StringMap<ContentTime>());
    }
}
