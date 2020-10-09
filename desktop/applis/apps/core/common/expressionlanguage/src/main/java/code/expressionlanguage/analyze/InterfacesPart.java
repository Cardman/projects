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
    private StringList staticInitInterfaces = new StringList();
    private Ints staticInitInterfacesOffset = new Ints();
    private boolean ok = true;
    private String part;
    private int locIndex;
    public InterfacesPart(String _part, int _locIndex) {
        part = _part;
        locIndex = _locIndex;
    }
    public void parse(KeyWords _keyWords, int _delta, int _offset) {
        String keyWordInterfaces_ = _keyWords.getKeyWordInterfaces();
        if (StringExpUtil.startsWithKeyWord(part,0, keyWordInterfaces_)) {
            int begin_ = part.indexOf(BEGIN_CALLING);
            if (begin_ < 0) {
                //ERROR
                ok = false;
            } else {
                int end_ = part.indexOf(END_CALLING, begin_);
                if (end_ < 0) {
                    //ERROR
                    ok = false;
                } else {
                    int interfaceOffest_ = _delta+begin_ + 1;
                    String interfacesInfo_ = part.substring(begin_ + 1, end_);
                    for (String p: StringUtil.splitChars(interfacesInfo_, SEP_CALLING)) {
                        staticInitInterfaces.add(p);
                        staticInitInterfacesOffset.add(interfaceOffest_+_offset);
                        interfaceOffest_ += p.length() + 1;
                    }
                    locIndex += end_ + 1;
                    part = part.substring(end_+1);
                    locIndex += StringUtil.getFirstPrintableCharIndex(part);
                    part = part.trim();
                }
            }
        } else {
            locIndex += StringExpUtil.getOffset(part);
            part = part.trim();
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

    public int getLocIndex() {
        return locIndex;
    }
}
