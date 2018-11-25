package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.EnumStruct;
import code.expressionlanguage.structs.Struct;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;
    
    public Struct getPart() {
        return part;
    }

    public void setPart(Struct _part) {
        part = _part;
    }

    @Override
    Struct getInstance() {
        return part;
    }
    @Override
    InfoAnnotPart getFirst() {
        return null;
    }
    public String export() {
        if (part.isNull()) {
            return ";";
        }
        if (part instanceof EnumStruct) {
            return ((EnumStruct)part).getName();
        }
        if (part instanceof ClassMetaInfo) {
            return ((ClassMetaInfo)part).getName();
        }
        Object instance_ = part.getInstance();
        if (instance_ instanceof Character) {
            Character ch_ = (Character) instance_;
            int v_ = ch_.charValue();
            return Integer.toString(v_);
        }
        if (instance_ instanceof Number) {
            return PrimitiveTypeUtil.toNumberString((Number) instance_);
        }
        if (instance_ instanceof Boolean) {
            if ((Boolean)instance_) {
                return "1";
            }
            return "0";
        }
        StringBuilder out_ = new StringBuilder();
        out_.append("\"");
        for (char c: ((String)instance_).toCharArray()) {
            if (c == '"' || c == '\\') {
                out_.append("\\");
                out_.append(c);
                continue;
            }
            if (c == 0) {
                out_.append("\\u0000");
                continue;
            }
            if (c < 16) {
                out_.append("\\u000");
                out_.append(Integer.toHexString(c));
                continue;
            }
            if (c < 31) {
                out_.append("\\u00");
                out_.append(Integer.toHexString(c));
                continue;
            }
            out_.append(c);
        }
        out_.append("\"");
        return out_.toString();
    }
}
