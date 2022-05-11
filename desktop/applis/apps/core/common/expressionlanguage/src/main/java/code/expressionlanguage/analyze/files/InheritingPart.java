package code.expressionlanguage.analyze.files;

import code.util.IntMap;

public final class InheritingPart {
    private static final char INHERIT = ':';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private final IntMap<String> superTypes = new IntMap< String>();
    private String tempDef = "";
    private String typeName = "";
    private int beginDefinition;
    private final int offset;
    private final String part;
    InheritingPart(int _offset,String _part) {
        beginDefinition = _offset;
        offset = _offset;
        part = _part;
    }
    void parse(int _globalOffset) {
        int locIndex_ = 0;
        StringBuilder str_ = new StringBuilder();
        StringBuilder typeNamePref_ = new StringBuilder();
        StringBuilder templateDef_ = new StringBuilder();
        int nbOpened_ = 0;
        boolean foundInherit_ = false;
        int inheritIndex_ = -1;
        while (locIndex_ < part.length()) {
            char locChar_ = part.charAt(locIndex_);
            nbOpened_ = openOrClose(_globalOffset,nbOpened_,foundInherit_,locIndex_,typeNamePref_,templateDef_);
            if (locChar_ == INHERIT && nbOpened_ == 0) {
                foundInh(foundInherit_, inheritIndex_, _globalOffset, str_);
                str_.delete(0, str_.length());
                foundInherit_ = true;

                locIndex_ = locIndex_ + 1;
                inheritIndex_ = locIndex_+offset;
                continue;
            }
            if (foundInherit_) {
                str_.append(locChar_);
            }

            locIndex_ = locIndex_ + 1;
        }
        foundInh(foundInherit_, inheritIndex_, _globalOffset, str_);

        tempDef = templateDef_.toString();
        typeName = typeNamePref_.toString();

    }
    private int openOrClose(int _globalOffset,int _nb, boolean _foundInherit, int _current, StringBuilder _typeNamePref, StringBuilder _templateDef) {
        int nbOpened_ = _nb;
        char locChar_ = part.charAt(_current);
        if (locChar_ == BEGIN_TEMPLATE) {
            nbOpened_++;
        }
        if (nbOpened_ > 0) {
            if (!_foundInherit) {
                _templateDef.append(locChar_);
            }
        } else {
            if (_templateDef.length() == 0
                    && !_foundInherit && locChar_ != INHERIT) {
                if (_typeNamePref.length() == 0) {
                    beginDefinition = _current+offset+_globalOffset;
                }
                _typeNamePref.append(locChar_);
            }
        }
        if (locChar_ == END_TEMPLATE) {
            nbOpened_--;
        }
        return nbOpened_;
    }

    private void foundInh(boolean _foundInherit, int _inheritIndex, int _globalOffset, StringBuilder _str) {
        if (_foundInherit) {
            this.superTypes.put(_inheritIndex + _globalOffset, _str.toString());
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTempDef() {
        return tempDef;
    }

    public int getBeginDefinition() {
        return beginDefinition;
    }

    public IntMap<String> getSuperTypes() {
        return superTypes;
    }
}
