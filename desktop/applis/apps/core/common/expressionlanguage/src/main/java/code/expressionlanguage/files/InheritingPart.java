package code.expressionlanguage.files;

import code.util.IntMap;

public final class InheritingPart {
    private static final char INHERIT = ':';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private IntMap<String> superTypes = new IntMap< String>();
    private String tempDef = "";
    private String typeName = "";
    private int beginDefinition;
    private String part;
    InheritingPart(int _beginDefinition,String _part) {
        beginDefinition = _beginDefinition;
        part = _part;
    }
    void parse(int _offset) {
        int locIndex_ = 0;
        StringBuilder str_ = new StringBuilder();
        StringBuilder typeNamePref_ = new StringBuilder();
        StringBuilder templateDef_ = new StringBuilder();
        int nbOpened_ = 0;
        boolean foundInherit_ = false;
        int inheritIndex_ = -1;
        while (locIndex_ < part.length()) {
            char locChar_ = part.charAt(locIndex_);
            if (locChar_ == BEGIN_TEMPLATE) {
                nbOpened_++;
            }
            if (nbOpened_ > 0) {
                if (!foundInherit_) {
                    templateDef_.append(locChar_);
                }
            } else {
                if (templateDef_.length() == 0
                        && !foundInherit_ && locChar_ != INHERIT) {
                    if (typeNamePref_.length() == 0) {
                        beginDefinition = locIndex_+_offset;
                    }
                    typeNamePref_.append(locChar_);
                }
            }
            if (locChar_ == END_TEMPLATE) {
                nbOpened_--;
            }
            if (locChar_ == INHERIT && nbOpened_ == 0) {
                if (foundInherit_) {
                    superTypes.put(inheritIndex_, str_.toString());
                }
                str_.delete(0, str_.length());
                foundInherit_ = true;

                locIndex_ = locIndex_ + 1;
                inheritIndex_ = locIndex_+_offset;
                continue;
            }
            if (foundInherit_) {
                str_.append(locChar_);
            }

            locIndex_ = locIndex_ + 1;
        }
        if (foundInherit_) {
            superTypes.put(inheritIndex_, str_.toString());
        }

        tempDef = templateDef_.toString();
        typeName = typeNamePref_.toString();

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
