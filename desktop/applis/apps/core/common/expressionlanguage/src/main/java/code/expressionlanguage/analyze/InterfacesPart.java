package code.expressionlanguage.analyze;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class InterfacesPart {
    private static final char BEGIN_CALLING = '(';
    private static final char SEP_CALLING = ',';
    private static final char END_CALLING = ')';
    private final StringList staticInitInterfaces = new StringList();
    private final StringList instInitInterfaces = new StringList();
    private final Ints staticInitInterfacesOffset = new Ints();
    private final Ints instInitInterfacesOffset = new Ints();
    private boolean ok = true;
    private String part;
    private int locIndex;
    private int interfaceOffest;
    public InterfacesPart(String _part, int _locIndex) {
        part = _part;
        locIndex = _locIndex;
    }
    public void parse(KeyWords _keyWords, String _type, int _delta, int _offset) {
        String keyWordInterfaces_ = _keyWords.getKeyWordInterfaces();
        if (!StringExpUtil.startsWithKeyWord(part, 0, keyWordInterfaces_)) {
            locIndex += StringExpUtil.getOffset(part);
            part = part.trim();
            return;
        }
        int begin_ = part.indexOf(BEGIN_CALLING);
        if (begin_ < 0) {
            //ERROR
            ok = false;
            return;
        }
        int end_ = part.indexOf(END_CALLING, begin_);
        if (end_ < 0) {
            //ERROR
            ok = false;
            return;
        }
        String keyWordClass_ = _keyWords.getKeyWordClass();
        String keyWordInterface_ = _keyWords.getKeyWordInterface();
        interfaceOffest = _delta+begin_ + 1;
        String interfacesInfo_ = part.substring(begin_ + 1, end_);
        if (!StringUtil.quickEq("@" + keyWordClass_, _type) && !StringUtil.quickEq("@" + keyWordInterface_, _type)) {
            loopStatic(_offset,interfacesInfo_);
            locIndex += end_ + 1;
            part = part.substring(end_+1);
            locIndex += StringUtil.getFirstPrintableCharIndex(part);
            part = part.trim();
            return;
        }
        int sepInst_ = interfacesInfo_.indexOf(";");
        if (sepInst_ < 0) {
            loopInst(_offset, interfacesInfo_);
            locIndex += end_ + 1;
            part = part.substring(end_+1);
            locIndex += StringUtil.getFirstPrintableCharIndex(part);
            part = part.trim();
            return;
        }
        String before_ = interfacesInfo_.substring(0, sepInst_);
        String after_ = interfacesInfo_.substring(sepInst_ + 1);
        if (before_.trim().isEmpty()) {
            interfaceOffest+=sepInst_+1;
            loopInst(_offset, after_);
            locIndex += end_ + 1;
            part = part.substring(end_+1);
            locIndex += StringUtil.getFirstPrintableCharIndex(part);
            part = part.trim();
            return;
        }
        loopStatic(_offset,before_);
        if (!after_.trim().isEmpty()) {
            loopInst(_offset,after_);
        }
        locIndex += end_ + 1;
        part = part.substring(end_+1);
        locIndex += StringUtil.getFirstPrintableCharIndex(part);
        part = part.trim();
    }

    private void loopStatic(int _offset, String _info) {
        for (String p : StringUtil.splitChars(_info, SEP_CALLING)) {
            staticInitInterfaces.add(p.trim());
            staticInitInterfacesOffset.add(interfaceOffest+StringExpUtil.getOffset(p)+_offset);
            interfaceOffest += p.length() + 1;
        }
    }

    private void loopInst(int _offset, String _info) {
        for (String p : StringUtil.splitChars(_info, SEP_CALLING)) {
            instInitInterfaces.add(p.trim());
            instInitInterfacesOffset.add(interfaceOffest+StringExpUtil.getOffset(p) + _offset);
            interfaceOffest += p.length() + 1;
        }
    }

    public boolean isOk() {
        return ok;
    }

    public String getPart() {
        return part;
    }

    public Ints getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public Ints getInstInitInterfacesOffset() {
        return instInitInterfacesOffset;
    }

    public StringList getInstInitInterfaces() {
        return instInitInterfaces;
    }

    public int getLocIndex() {
        return locIndex;
    }
}
