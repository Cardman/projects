package code.mock;

import code.expressionlanguage.filenames.DefaultNameValidating;
import code.stream.FileListInfo;
import code.stream.core.AbstractZipFact;
import code.stream.core.ContentTime;
import code.threads.FileStruct;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MockZipFact implements AbstractZipFact {

    @Override
    public StringMap<ContentTime> zippedBinaryFiles(byte[] _bytes) {
        if (_bytes == null) {
            return null;
        }
        if (!FileListInfo.isZip(_bytes)) {
            return new StringMap<ContentTime>();
        }
        if (_bytes.length < 5 || _bytes[4] != '\r') {
            return new StringMap<ContentTime>();
        }
        Bytes ls_ = Bytes.newList(_bytes);
        int unFileCount_ = ls_.indexOfNb('/', 4);
        String res_ = StringUtil.nullToEmpty(StringUtil.decode(new Bytes(ls_.sub(5, unFileCount_)).toArrByte()));
        if (res_.isEmpty()) {
            return new StringMap<ContentTime>();
        }
        if (ko(res_)) {
            return new StringMap<ContentTime>();
        }
        return headComplete(_bytes, ls_, unFileCount_, res_);
    }

    private StringMap<ContentTime> headComplete(byte[] _bytes, Bytes _ls, int _unFileCount, String _res) {
        int count_ = NumberUtil.parseInt(_res);
        int un_ = _unFileCount;
        StringMap<FileStruct> files_ = new StringMap<FileStruct>();
        for (int i = 0; i < count_; i++) {

            int next_ = _ls.indexOfNb('\r', un_ + 1);
            if (next_ < 0) {
                return new StringMap<ContentTime>();
            }
            int next2_ = _ls.indexOfNb('\r', next_ + 1);
            if (next2_ < 0) {
                return new StringMap<ContentTime>();
            }
            String modif_ = StringUtil.nullToEmpty(StringUtil.decode(new Bytes(_ls.sub(next_ + 1, next2_)).toArrByte()));
            if (modif_.isEmpty()||ko(modif_)) {
                return new StringMap<ContentTime>();
            }
            long last_ = NumberUtil.parseLongZero(modif_);
            int curr_ = incr(_ls, next2_);
            if (curr_ >= _ls.size()|| _ls.get(curr_) !='/') {
                return new StringMap<ContentTime>();
            }
            String resLen_ = StringUtil.nullToEmpty(StringUtil.decode(new Bytes(_ls.sub(next2_ + 1, curr_)).toArrByte()));
            String resKey_ = StringUtil.nullToEmpty(StringUtil.decode(new Bytes(_ls.sub(un_ + 1, next_)).toArrByte()));
            if (koPath(resKey_)) {
                return new StringMap<ContentTime>();
            }
            put(files_, resKey_, resLen_,last_);
            un_ = curr_;
        }
        return complete(_bytes, un_, files_);
    }

    private boolean koPath(String _key) {
        if (_key.endsWith("/")) {
            return !new DefaultNameValidating(new StringList()).okPath(_key.substring(0,_key.length()-1), '/');
        }
        return !new DefaultNameValidating(new StringList()).okPath(_key, '/');
    }

    private boolean ko(String _nb) {
        for (char c: _nb.toCharArray()) {
            if (c < '0' || c > '9') {
                return true;
            }
        }
        return false;
    }

    private void put(StringMap<FileStruct> _files, String _resKey, String _resLen, long _last) {
        if (_resLen.isEmpty()) {
            _files.put(_resKey,new FileStruct(null,_last));
        } else {
            _files.put(_resKey,new FileStruct(new byte[NumberUtil.parseInt(_resLen)],_last));
        }
    }

    private StringMap<ContentTime> complete(byte[] _bytes, int _un, StringMap<FileStruct> _files) {
        int offset_ = _un +1;
        for (EntryCust<String,FileStruct> n: _files.entryList()) {
            byte[] content_ = n.getValue().getContent();
            if (content_ != null) {

                int coLen_ = content_.length;
                for (int i = 0; i < coLen_; i++) {
                    if (offset_+i >= _bytes.length) {
                        return new StringMap<ContentTime>();
                    }
                    MockBinStreamInImpl.set(content_,i, _bytes,offset_+i);
                }
                offset_+= coLen_;
            }
        }
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        for (EntryCust<String,FileStruct> n: _files.entryList()) {
            out_.addEntry(n.getKey(),new ContentTime(n.getValue().getContent(), n.getValue().getLastDate()));
        }
        return out_;
    }

    private int incr(Bytes _ls, int _next) {
        int curr_ = _next + 1;
        while (curr_ < _ls.size()) {
            if (_ls.get(curr_) < '0' || _ls.get(curr_) > '9') {
                break;
            }
            curr_++;
        }
        return curr_;
    }

    public static byte[] wrapInts(int[] _map) {
        int len_ = _map.length;
        byte[] bytes_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            bytes_[i] = (byte) _map[i];
        }
        return bytes_;
    }

    public static StringMap<ContentTime> wrapText(MockNameFile[] _map) {
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        for (MockNameFile e: _map) {
            out_.addEntry(e.getName(),new ContentTime(e.getContent(),e.getMillis()));
        }
        return out_;
    }
    @Override
    public byte[] zipBinFiles(StringMap<ContentTime> _files) {
        StringList keys_ = new StringList();
        for (EntryCust<String,ContentTime> n: _files.entryList()) {
            ContentTime file_ = n.getValue();
            byte[] content_ = file_.getContent();
            if (content_ != null) {
                if (!n.getKey().endsWith("/")) {
                    keys_.add(n.getKey()+'\r'+file_.getLastModifTime()+'\r'+content_.length);
                } else {
                    keys_.add(n.getKey()+'\r'+file_.getLastModifTime()+'\r');
                }
            } else {
                if (n.getKey().endsWith("/")) {
                    keys_.add(n.getKey()+'\r'+file_.getLastModifTime()+'\r');
                } else {
                    keys_.add(n.getKey()+'/'+'\r'+file_.getLastModifTime()+'\r');
                }
            }
        }
        return feed(_files, keys_);
    }

    private byte[] feed(StringMap<ContentTime> _cont, StringList _keys) {
        String header_ = "\r" +
                _cont.size() +
                '/' +
                StringUtil.join(_keys, '/') +
                '/';
        byte[] he_ = StringUtil.encode(header_);
        int sum_ = sumCont(_cont);
        int heLen_ = he_.length;
        byte[] union_ = new byte[4+heLen_ +sum_];
        union_[0] = (byte) 0x50;
        union_[1] = (byte) 0x4b;
        union_[2] = (byte) 0x03;
        union_[3] = (byte) 0x04;
        int offset_ = 4;
        for (int i = 0; i < heLen_; i++) {
            MockBinStreamInImpl.set(union_,i+offset_,he_,i);
        }
        offset_ += heLen_;
        for (EntryCust<String,ContentTime> n: _cont.entryList()) {
            byte[] content_ = n.getValue().getContent();
            if (content_ != null) {
                int coLen_ = content_.length;
                for (int i = 0; i < coLen_; i++) {
                    MockBinStreamInImpl.set(union_,i+offset_,content_,i);
                }
                offset_ += coLen_;
            }
        }
        return union_;
    }

    private int sumCont(StringMap<ContentTime> _map) {
        int sum_ = 0;
        for (EntryCust<String,ContentTime> n: _map.entryList()) {
            byte[] content_ = n.getValue().getContent();
            if (content_ != null) {
                sum_ += content_.length;
            }
        }
        return sum_;
    }

}
