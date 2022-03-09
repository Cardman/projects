package code.expressionlanguage.common;

import code.util.StringList;

final class StackType {
    private int count;
    void loopSepComma(StringList _types, StringBuilder _out, char _curChar) {
        if (count > 0) {
            changeStack(_curChar);
            _out.append(_curChar);
            return;
        }
        if (_curChar == StringExpUtil.LT) {
            _out.append(_curChar);
            if (_out.toString().trim().charAt(0) != StringExpUtil.LT) {
                count++;
            }
            return;
        }
        if (_curChar == StringExpUtil.COMMA) {
            _types.add(_out.toString());
            _out.delete(0, _out.length());
        } else {
            _out.append(_curChar);
        }
    }

    String loopAllTypes(StringBuilder _id, StringBuilder _out, char _curChar) {
        if (count > 0) {
            changeStack(_curChar);
            if (count == 1 && _curChar == StringExpUtil.COMMA || count == 0) {
                String res_ = _out.toString();
                _out.delete(0, _out.length());
                return res_;
            }
            _out.append(_curChar);
            return "";
        }
        if (_curChar == StringExpUtil.LT) {
            count++;
        }
        _id.append(_curChar);
        return "";
    }

    void loopAllTypes(StringList _types, StringBuilder _id, StringBuilder _out, char _curChar) {
        if (count > 0) {
            changeStack(_curChar);
            if (count == 1 && _curChar == StringExpUtil.COMMA || count == 0) {
                _types.add(_out.toString());
                _out.delete(0, _out.length());
                return;
            }
            _out.append(_curChar);
            return;
        }
        if (_curChar == StringExpUtil.LT) {
            count++;
            return;
        }
        _id.append(_curChar);
    }
    boolean loopAllPartInnerTypes(String _type, int _i,StringList _types, StringBuilder _out, char _curChar) {
        if (changedCount(_out, _curChar)) {
            return false;
        }
        if (_curChar == '-') {
            _types.add(_out.toString());
            _types.add("-");
            _out.delete(0, _out.length());
            return false;
        }
        if (_type.startsWith(StringExpUtil.INNER_TYPE,_i)) {
            _types.add(_out.toString());
            _types.add(StringExpUtil.INNER_TYPE);
            _out.delete(0, _out.length());
            return true;
        }
        _out.append(_curChar);
        return false;
    }
    boolean loopAllInnerTypes(String _type, int _i,StringList _types, StringBuilder _out, char _curChar) {
        if (changedCount(_out, _curChar)) {
            return false;
        }
        if (_curChar == '-') {
            _types.add(_out.toString());
            _out.delete(0, _out.length());
            return false;
        }
        if (_type.startsWith(StringExpUtil.INNER_TYPE,_i)) {
            _types.add(_out.toString());
            _out.delete(0, _out.length());
            return true;
        }
        _out.append(_curChar);
        return false;
    }

    private boolean changedCount(StringBuilder _out, char _curChar) {
        if (count > 0) {
            changeStack(_curChar);
            _out.append(_curChar);
            return true;
        }
        if (_curChar == StringExpUtil.LT) {
            _out.append(_curChar);
            count++;
            return true;
        }
        return false;
    }
    private void changeStack(char _curChar) {
        if (_curChar == StringExpUtil.LT) {
            count++;
        }
        if (_curChar == StringExpUtil.GT) {
            count--;
        }
    }
}
