package code.maths.litteral;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.exceptions.BadNumberException;
import code.maths.exceptions.FormatException;
import code.maths.exceptions.MathStringFormatException;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Displayable;
import code.util.ints.NumericableString;

final class NumericString implements NumericableString<Rate>, Displayable {

    static final char SEPARATOR_SET_CHAR = ';';

    static final String EMPTY_STRING = "";

    static final String LEFT_BRACE_SET=String.valueOf(StringList.LEFT_BRACE);

    static final String RIGHT_BRACE_SET= String.valueOf(StringList.RIGHT_BRACE);

    static final String SEPARATOR_SET = String.valueOf(SEPARATOR_SET_CHAR);

    static final char LEFT_BRACE_SET_CHAR = StringList.LEFT_BRACE;

    static final String LEFT_PAR= String.valueOf(StringList.LEFT_PAR);

    static final char RIGHT_BRACE_SET_CHAR = StringList.RIGHT_BRACE;

    static final String RIGHT_PAR = String.valueOf(StringList.RIGHT_PAR);

    static final String EMPTY_SET = "vide";

    static final String ONE = "1";

    static final String ZERO = "0";

    static final String MINUS_ONE = "-1";

    static final String MINUS_ZERO = "-0";

    private static final char SEPARATOR_ARGS = ',';

    private static final char FOIS = '*';

    private static final char PLUS = '+';

    private static final char MOINS = '-';

    private static final char DIVISION = ':';

    private static final char POINT = '.';

    private static final String PUIS = "puis";

    private static final String QUOT = "quot";

    private static final String MOD = "mod";

    private static final String MODTAUX = "modtaux";

    private static final String ABS = "abs";

    private static final String ENT = "ent";

    private static final String TRONC = "troncature";

    private static final String NUM = "num";

    private static final String DEN = "den";

    private static final String MIN = "min";

    private static final String MAX = "max";

    private static final String MOY = "moy";

    private static final String VAR = "var";

    private static final String CARAC_FERME = "caracferme";

    private static final String CARAC_OUVERT = "caracouvert";

    private static final String CARAC_SEMI_OUVERT_G = "caracsemiouvertg";

    private static final String CARAC_SEMI_OUVERT_D = "caracsemiouvertd";

    private static final String CARAC_DROITE_OUVERT = "caracdroiteouvert";

    private static final String CARAC_DROITE_FERME = "caracdroiteferme";

    private static final String CARAC_GAUCHE_OUVERT = "caracgaucheouvert";

    private static final String CARAC_GAUCHE_FERME = "caracgaucheferme";

    private static final String SGN = "sgn";

    private static final String CARD = "cardinal";

    private static final String INTER = "inter";

    private static final String UNION = "union";

    private static final String COMPL = "complementaire";

    private static final String INCL = "inclusnum";

    private static final String NON_INCL = "noninclusnum";

    private static final String EQ_NUM = "egalnum";

    private static final String NON_EQ_NUM = "differentnum";

    private static final String DIV_FCT = "div";

    private static final int ERROR_CODE = -1;

    private static final int TRUE_CODE = 1;

    private static final int FALSE_CODE = 0;

    private transient boolean checkSyntax;

    private int code = FALSE_CODE;

    private StringBuilder numericString;

    NumericString(String _chaineNumerique) {
        numericString = new StringBuilder(_chaineNumerique);
    }

    NumericString(String _chaineNumerique, StringMap<String> _vars) {
        if (_vars.isEmpty()) {
            numericString = new StringBuilder(_chaineNumerique);
        } else {
            numericString = new StringBuilder(StringList.replaceWordsJoin(_chaineNumerique, _vars));
        }
    }

    boolean isError() {
        return code == ERROR_CODE;
    }

    @Override
    public void evaluateExp(boolean _checkSyntax) {
        checkSyntax = _checkSyntax;
        if (numericString.length() == 0) {
            return;
        }
        replaceEmptySets();
        while (true) {
            replaceNumbers();
            if (code == FALSE_CODE) {
                break;
            }
//            if (code == ERROR_CODE) {
//                return;
//            }
        }
        while (true) {
            insertMult();
            if (code == FALSE_CODE) {
                break;
            }
        }
        boolean modif_ = true;
        while (modif_) {
            modif_ = false;
            replaceMinus();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            calculateArgs();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            if (code == ERROR_CODE) {
                return;
            }
            calculateFunctions();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            if (code == ERROR_CODE) {
                return;
            }
            removeParenthesesAroundOps();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            removeDoubleParentheses();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            removeSeqMinus();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            calculateGreatPriority();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            if (code == ERROR_CODE) {
                return;
            }
            calculateLowPriority();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
        }
        int index_ = CustList.FIRST_INDEX;
        while (index_ < numericString.length()) {
            if (numericString.charAt(index_) != StringList.LEFT_PAR) {
                CaughtStringIndex c_;
                c_ = tryGetRateNumber(index_);
                if (!c_.isValid()) {
                    return;
                }
                String token_ = c_.getToken();
                String res_ = deleteZeroDivider(_checkSyntax, token_).toNumberString();
                numericString.replace(index_, c_.getIndex(), res_);
                return;
            }
            index_ ++;
        }
    }

    void calculateArgs() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                String nbOne_;
                String nbTwo_;
                CaughtStringIndex caught_;
                caught_ = tryGetRateNumber(index_);
                if (!caught_.isValid()) {
                    index_ = caught_.getIndex() - 1;
                    foundBeginChar_ = false;
                    continue;
                }
                nbOne_ = caught_.getToken();
                int next_ = caught_.getIndex();
                if (next_ >= numericString.length()) {
                    return;
                }
                char opChar_ = numericString.charAt(next_);
                if(opChar_ != PLUS){
                    if(opChar_ != FOIS){
                        if(opChar_ != MOINS){
                            if(opChar_ != DIVISION){
                                index_ = next_;
                                //index_ = next_ + 1;
                                foundBeginChar_ = false;
                                continue;
                            }
                        }
                    }
                }
                next_ ++;
                caught_ = tryGetRateNumber(next_);
                if (!caught_.isValid()) {
                    index_ = caught_.getIndex() - 1;
                    foundBeginChar_ = false;
                    continue;
                }
                nbTwo_ = caught_.getToken();
                next_ = caught_.getIndex();
                if (next_ >= numericString.length()) {
                    String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                    if (res_.isEmpty()) {
                        code=ERROR_CODE;
                        return;
                    }
                    numericString.replace(index_, numericString.length(), res_);
                    code=TRUE_CODE;
                    return;
                }
                if (numericString.charAt(next_) != StringList.RIGHT_PAR) {
                    if (numericString.charAt(next_) != SEPARATOR_ARGS) {
                        index_ = next_;
                        foundBeginChar_ = false;
                        continue;
                    }
                }
                String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                if (res_.isEmpty()) {
                    code=ERROR_CODE;
                    return;
                }
                numericString.replace(index_, next_, res_);
                foundBeginChar_ = false;
                index_ += res_.length();
                code=TRUE_CODE;
                continue;
            }
            if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
            } else if (numericString.charAt(index_) == SEPARATOR_ARGS) {
                foundBeginChar_ = true;
            }
            index_++;
        }
    }

    void removeParenthesesAroundOps() {
        code=FALSE_CODE;
        int index_ = CustList.FIRST_INDEX;
        int len_ = numericString.length();
//        if (index_ >= len_) {
//            return;
//        }
        if (numericString.charAt(index_) == StringList.LEFT_PAR) {
            CaughtStringIndex c_;
            c_ = tryGetRateNumber(index_ + 1);
            if (c_.isValid()) {
                int next_ = c_.getIndex();
                if (next_ >= len_) {
                    return;
                }
                if (numericString.charAt(next_) == StringList.RIGHT_PAR) {
                    code=TRUE_CODE;
                    numericString.deleteCharAt(next_);
                    numericString.deleteCharAt(CustList.FIRST_INDEX);
                    index_ = next_ - 2;
                }
            }
        }
        boolean foundBeginChar_ = false;
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                    CaughtStringIndex c_;
                    c_ = tryGetRateNumber(index_ + 1);
                    if (!c_.isValid()) {
                        index_ = c_.getIndex();
                        foundBeginChar_ = false;
                        continue;
                    }
                    int next_ = c_.getIndex();
                    if (next_ >= len_) {
                        return;
                    }
                    if (numericString.charAt(next_) == StringList.RIGHT_PAR) {
                        code=TRUE_CODE;
                        numericString.deleteCharAt(next_);
                        numericString.deleteCharAt(index_);
                        index_ += c_.getToken().length();
                        foundBeginChar_ = false;
                        continue;
                    }
                }
                index_ ++;
                foundBeginChar_ = false;
                continue;
            }
            char opChar_ = numericString.charAt(index_);
            if(opChar_ == PLUS){
                foundBeginChar_ = true;
            }
            if(opChar_ == FOIS){
                foundBeginChar_ = true;
            }
            if(opChar_ == MOINS){
                foundBeginChar_ = true;
            }
            if(opChar_ == SEPARATOR_ARGS){
                foundBeginChar_ = true;
            }
            if(opChar_ == DIVISION){
                foundBeginChar_ = true;
            }
            index_ ++;
        }
    }

    void calculateFunctions() {
        code=FALSE_CODE;
        int index_ = CustList.FIRST_INDEX;
        int len_ = numericString.length();
//        if (index_ >= len_) {
//            return;
//        }
        StringBuilder fctName_ = new StringBuilder();
        while (index_ < len_) {
            if (!Character.isLowerCase(numericString.charAt(index_))) {
                if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                    fctName_.append(numericString.charAt(index_));
                }
                break;
            }
            fctName_.append(numericString.charAt(index_));
            index_ ++;
        }
        int lengthFct_ = fctName_.length();
        StringList args_ = new StringList();
        int begin_ = index_;
        if (lengthFct_ > CustList.ONE_ELEMENT) {
            if (fctName_.charAt(lengthFct_ - 1) == StringList.LEFT_PAR) {
                while (true) {
                    //index_ < len_
                    CaughtStringIndex c_;
                    c_ = tryGetRateNumber(index_ + 1);
                    if (!c_.isValid()) {
                        c_ = tryGetSet(index_ + 1);
                    }
                    if (!c_.isValid()) {
                        index_ = begin_;
                        break;
                    }
                    args_.add(c_.getToken());
                    int next_ = c_.getIndex();
                    if (next_ >= len_) {
                        return;
                    }
                    if (numericString.charAt(next_) == StringList.RIGHT_PAR) {
                        fctName_.deleteCharAt(lengthFct_ - 1);
                        String res_ = functions(checkSyntax, fctName_.toString(), args_);
                        if (res_.isEmpty()) {
                            code = ERROR_CODE;
                            return;
                        }
                        numericString.replace(CustList.FIRST_INDEX, next_+1, res_);
                        code=TRUE_CODE;
                        index_ = CustList.FIRST_INDEX;
                        break;
                    }
                    if (numericString.charAt(next_) != SEPARATOR_ARGS) {
                        index_ = begin_;
                        break;
                    }
                    index_ = next_;
                }
            }
        } else {
            begin_ = CustList.FIRST_INDEX;
        }
        args_.clear();
        fctName_ = new StringBuilder();
        boolean foundBeginChar_ = false;
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                while (true) {
                    //index_ < numericString.length()
                    CaughtStringIndex c_;
                    c_ = tryGetRateNumber(index_ + 1);
                    if (!c_.isValid()) {
                        c_ = tryGetSet(index_ + 1);
                    }
                    if (!c_.isValid()) {
                        index_ = begin_ + fctName_.length() - 1;
                        foundBeginChar_ = false;
                        fctName_ = new StringBuilder();
                        args_.clear();
                        break;
                    }
                    args_.add(c_.getToken());
                    int next_ = c_.getIndex();
                    if (next_ >= numericString.length()) {
                        return;
                    }
                    if (numericString.charAt(next_) == StringList.RIGHT_PAR) {
                        fctName_.deleteCharAt(lengthFct_ - 1);
                        String res_ = functions(checkSyntax, fctName_.toString(), args_);
                        if (res_.isEmpty()) {
                            code = ERROR_CODE;
                            return;
                        }
                        args_.clear();
                        fctName_ = new StringBuilder();
                        numericString.replace(begin_, next_+1, res_);
                        code=TRUE_CODE;
                        index_ = begin_+res_.length();
                        foundBeginChar_ = false;
                        break;
                    }
                    if (numericString.charAt(next_) != SEPARATOR_ARGS) {
                        index_ = begin_ + fctName_.length() - 1;
                        foundBeginChar_ = false;
                        fctName_ = new StringBuilder();
                        args_.clear();
                        break;
                    }
                    index_ = next_;
                }
                continue;
            }
            if (!StringList.isWordChar(numericString.charAt(index_))) {
                fctName_ = new StringBuilder();
                args_.clear();
                boolean fct_ = false;
                begin_ = index_ + 1;
                index_ ++;
                boolean lower_ = false;
                while (index_ < numericString.length()) {
                    if (!Character.isLowerCase(numericString.charAt(index_))) {
                        if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                            fctName_.append(numericString.charAt(index_));
                            fct_ = true;
                        }
                        break;
                    }
                    lower_ = true;
                    fctName_.append(numericString.charAt(index_));
                    index_ ++;
                }
                if (fct_ && lower_) {
                    lengthFct_ = fctName_.length();
                    foundBeginChar_ = true;
                } else {
                    index_ = begin_;
                }
                continue;
            }
            index_ ++;
        }
    }

    void removeDoubleParentheses() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                CaughtStringIndex c_;
                c_ = tryGetRateNumber(index_);
                if (!c_.isValid()) {
                    c_ = tryGetSet(index_);
                }
                if (!c_.isValid()) {
                    foundBeginChar_ = false;
                    continue;
                }
                int next_ = c_.getIndex();
                if (next_ >= numericString.length()) {
                    return;
                }
                if (numericString.charAt(next_) != StringList.RIGHT_PAR) {
                    index_ = next_;
                    foundBeginChar_ = false;
                    continue;
                }
                numericString.deleteCharAt(next_);
                numericString.deleteCharAt(index_ - 1);
                code=TRUE_CODE;
                if (next_ + 1>= numericString.length()) {
                    return;
                }
                index_ = next_ - 1;
                continue;
            }
            if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                if (index_ + 1 < numericString.length()) {
                    if (numericString.charAt(index_ + 1) == StringList.LEFT_PAR) {
                        foundBeginChar_ = true;
                        index_ ++;
                        index_ ++;
                        continue;
                    }
                }
            }
            index_++;
        }
    }

    void removeSeqMinus() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        boolean foundDigit_ = false;
        code=FALSE_CODE;
        while (index_ < numericString.length()) {
            if (numericString.charAt(index_) != Rate.MINUS_CHAR) {
                break;
            }
            index_ ++;
        }
        if (index_ > CustList.SECOND_INDEX) {
            int begin_;
            if (index_ % 2 == 1) {
                index_ --;
                begin_ = CustList.SECOND_INDEX;
            } else {
                begin_ = CustList.FIRST_INDEX;
            }
            code=TRUE_CODE;
            numericString.delete(CustList.FIRST_INDEX, index_);
            index_ = begin_;
        }
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                int begin_ = index_;
                while (index_ < numericString.length()) {
                    if (numericString.charAt(index_) != Rate.MINUS_CHAR) {
                        break;
                    }
                    index_ ++;
                }
                int nbMinus_ = index_ - begin_;
                if (nbMinus_ < 2) {
                    foundBeginChar_ = false;
                    foundDigit_ = false;
                    continue;
                }
                code = TRUE_CODE;
                if (foundDigit_) {
                    if (nbMinus_ % 2 == 0) {
                        numericString.replace(begin_, index_, String.valueOf(PLUS));
                    } else {
                        //index_ --;
                        begin_ ++;
                        numericString.delete(begin_, index_);
                    }
                } else {
                    if (nbMinus_ % 2 == 0) {
                        numericString.delete(begin_, index_);
                    } else {
//                        index_ --;
                        begin_ ++;
                        numericString.delete(begin_, index_);
                    }
                }
                index_ = begin_;
                foundBeginChar_ = false;
                foundDigit_ = false;
                continue;
            }
            if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == SEPARATOR_ARGS) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == FOIS) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == DIVISION) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == PLUS) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (Character.isDigit(numericString.charAt(index_))) {
                foundBeginChar_ = true;
                foundDigit_ = true;
            }
            index_ ++;
        }
    }

    void calculateLowPriority() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while (true) {
            CaughtStringIndex c_;
            c_ = tryGetRateNumber(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex() - 1;
                break;
            }
            String nbOne_ = c_.getToken();
            int next_ = c_.getIndex();
            if (next_ >= numericString.length()) {
                return;
            }
            char opChar_ = numericString.charAt(next_);
            if (opChar_ != PLUS) {
                if (opChar_ != MOINS) {
                    index_ = c_.getIndex() - 1;
                    break;
                }
            }
            c_ = tryGetRateNumber(next_ + 1);
            if (!c_.isValid()) {
                index_ = c_.getIndex() - 1;
                break;
            }
            String nbTwo_ = c_.getToken();
            next_ = c_.getIndex();
            if (next_ >= numericString.length()) {
                String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                numericString.replace(CustList.FIRST_INDEX, numericString.length(), res_);
                code = TRUE_CODE;
                return;
            }
            if (numericString.charAt(next_) != PLUS) {
                if (numericString.charAt(next_) != MOINS) {
                    if (numericString.charAt(next_) != StringList.RIGHT_PAR) {
//                        index_ = c_.getIndex() - 1;
                        index_ = c_.getIndex();
                        break;
                    }
                }
            }
            String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
            numericString.replace(CustList.FIRST_INDEX, next_, res_);
            code = TRUE_CODE;
        }
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                CaughtStringIndex c_;
                c_ = tryGetRateNumber(index_);
                if (!c_.isValid()) {
                    foundBeginChar_ = false;
                    index_ = c_.getIndex() - 1;
                    continue;
                }
                String nbOne_ = c_.getToken();
                int next_ = c_.getIndex();
                if (next_ >= numericString.length()) {
                    return;
                }
                char opChar_ = numericString.charAt(next_);
                if (opChar_ != PLUS) {
                    if (opChar_ != MOINS) {
//                        index_ = c_.getIndex() - 1;
                        index_ = c_.getIndex();
                        foundBeginChar_ = false;
                        continue;
                    }
                }
                c_ = tryGetRateNumber(next_ + 1);
                if (!c_.isValid()) {
                    index_ = c_.getIndex() - 1;
                    foundBeginChar_ = false;
                    continue;
                }
                String nbTwo_ = c_.getToken();
                next_ = c_.getIndex();
                if (next_ >= numericString.length()) {
                    String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                    numericString.replace(index_, numericString.length(), res_);
                    code = TRUE_CODE;
                    return;
                }
                if (numericString.charAt(next_) != PLUS) {
                    if (numericString.charAt(next_) != MOINS) {
//                        index_ = c_.getIndex() - 1;
                        index_ = c_.getIndex();
                        foundBeginChar_ = false;
                        continue;
                    }
                }
                String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                numericString.replace(index_, next_, res_);
                code = TRUE_CODE;
                continue;
            }
            if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
            } else if (numericString.charAt(index_) == SEPARATOR_ARGS) {
                foundBeginChar_ = true;
            }
            index_++;
        }
    }

    void calculateGreatPriority() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        boolean foundDigit_ = false;
        code=FALSE_CODE;
        while (true) {
            CaughtStringIndex c_;
            c_ = tryGetRateNumber(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex() - 1;
                break;
            }
            String nbOne_ = c_.getToken();
            int next_ = c_.getIndex();
            if (next_ >= numericString.length()) {
                return;
            }
            char opChar_ = numericString.charAt(next_);
            if (opChar_ != FOIS) {
                if (opChar_ != DIVISION) {
                    index_ = c_.getIndex() - 1;
                    //index_ = c_.getIndex();
                    break;
                }
            }
            c_ = tryGetRateNumber(next_ + 1);
            if (!c_.isValid()) {
                index_ = c_.getIndex() - 1;
                break;
            }
            String nbTwo_ = c_.getToken();
            next_ = c_.getIndex();
            if (next_ >= numericString.length()) {
                String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                if (res_.isEmpty()) {
                    code = ERROR_CODE;
                    return;
                }
                numericString.replace(CustList.FIRST_INDEX, numericString.length(), res_);
                code = TRUE_CODE;
                return;
            }
            String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
            if (res_.isEmpty()) {
                code = ERROR_CODE;
                return;
            }
            numericString.replace(CustList.FIRST_INDEX, next_, res_);
            code = TRUE_CODE;
        }
        while(index_ < numericString.length()){
            if (foundBeginChar_) {
                if (foundDigit_) {
//                    if (index_ + 1 >= numericString.length()) {
//                        return;
//                    }
//                    if (numericString.charAt(index_) != MOINS) {
//                        foundBeginChar_ = false;
//                        foundDigit_ = false;
//                        index_++;
//                        continue;
//                    }
                    //numericString.charAt(index_) == MOINS
                    index_++;
                }
                CaughtStringIndex c_;
                c_ = tryGetRateNumber(index_);
                if (!c_.isValid()) {
                    index_ = c_.getIndex() - 1;
                    foundBeginChar_ = false;
                    foundDigit_ = false;
                    continue;
                }
                String nbOne_ = c_.getToken();
                int next_ = c_.getIndex();
                if (next_ >= numericString.length()) {
                    return;
                }
                char opChar_ = numericString.charAt(next_);
                if (opChar_ != FOIS) {
                    if (opChar_ != DIVISION) {
                        index_ = c_.getIndex() - 1;
//                        index_ = c_.getIndex();
                        foundBeginChar_ = false;
                        foundDigit_ = false;
                        continue;
                    }
                }
                c_ = tryGetRateNumber(next_ + 1);
                if (!c_.isValid()) {
                    index_ = c_.getIndex() - 1;
                    foundBeginChar_ = false;
                    foundDigit_ = false;
                    continue;
                }
                String nbTwo_ = c_.getToken();
                next_ = c_.getIndex();
                if (next_ >= numericString.length()) {
                    String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                    if (res_.isEmpty()) {
                        code = ERROR_CODE;
                        return;
                    }
                    numericString.replace(index_, numericString.length(), res_);
                    code = TRUE_CODE;
                    return;
                }
                String res_ = operators(checkSyntax, opChar_, nbOne_, nbTwo_);
                if (res_.isEmpty()) {
                    code = ERROR_CODE;
                    return;
                }
                numericString.replace(index_, next_, res_);
                foundDigit_ = false;
                code = TRUE_CODE;
                continue;
            }
            if (index_ < CustList.FIRST_INDEX || index_ >= numericString.length()) {
                throw new MathStringFormatException(numericString.toString(),index_);
            }
            if (numericString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == SEPARATOR_ARGS) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (numericString.charAt(index_) == PLUS) {
                foundBeginChar_ = true;
                foundDigit_ = false;
            } else if (Character.isDigit(numericString.charAt(index_))) {
                if (index_ + 1 < numericString.length()) {
                    if (numericString.charAt(index_ + 1) == MOINS) {
                        foundBeginChar_ = true;
                        foundDigit_ = true;
                    }
                }
            }
            index_++;
        }
    }

    void replaceMinus() {
        int index_ = CustList.FIRST_INDEX;
        code = FALSE_CODE;
        while (index_ < numericString.length()) {
            String num_;
            CaughtStringIndex c_;
            c_ = tryGetPositiveInteger(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            num_ = c_.getToken();
            int next_= c_.getIndex();
            if (next_ + 1 >= numericString.length()) {
                return;
            }
            if (numericString.charAt(next_) != Rate.SEP_NUM_DEN_CHAR) {
                index_ = c_.getIndex();
                continue;
            }
            if (numericString.charAt(next_+1) != Rate.MINUS_CHAR) {
                index_ = c_.getIndex() + 1;
                continue;
            }
            String den_;
            c_ = tryGetPositiveInteger(next_ + 2);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            den_ = c_.getToken();
            numericString.replace(index_, c_.getIndex(), StringList.concat(Rate.MINUS,num_,Rate.SEP_NUM_DEN,den_));
            index_ = c_.getIndex();
            code = TRUE_CODE;
        }
    }

    void replaceEmptySets() {
        int index_ = CustList.FIRST_INDEX;
        while (true) {
            //index_ < numericString.length()
            if (index_ + 1 >= numericString.length()) {
                return;
            }
            if (numericString.charAt(index_) == LEFT_BRACE_SET_CHAR) {
                if (numericString.charAt(index_ + 1) == RIGHT_BRACE_SET_CHAR) {
                    numericString.replace(index_, index_ + 2, EMPTY_SET);
                    index_ += EMPTY_SET.length();
                    continue;
                }
            }
            index_++;
        }
    }

    void replaceNumbers() {
        int index_ = CustList.FIRST_INDEX;
        code = FALSE_CODE;
        while (index_ < numericString.length()) {
            String int_;
            CaughtStringIndex c_;
            c_ = tryGetPositiveInteger(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            int_ = c_.getToken();
            int next_= c_.getIndex();
            if (next_ + 1 >= numericString.length()) {
                break;
            }
            if (numericString.charAt(next_) != POINT) {
                index_ = next_;
                continue;
            }
            String dec_;
            c_ = tryGetPositiveInteger(next_ + 1);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            dec_ = c_.getToken();
            numericString.replace(index_, c_.getIndex(), deleteZeroDivider(checkSyntax, StringList.concat(int_,String.valueOf(POINT),dec_)).toNumberString());
            code = TRUE_CODE;
            index_ = c_.getIndex();
        }
        index_ = CustList.FIRST_INDEX;
        while (index_ < numericString.length()) {
            String int_;
            CaughtStringIndex c_;
            c_ = tryGetPositiveInteger(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            int_ = c_.getToken();
            int next_= c_.getIndex();
            if (next_ >= numericString.length()) {
                break;
            }
            if (numericString.charAt(next_) != POINT) {
                index_ = next_;
                continue;
            }
            numericString.replace(index_, next_ + 1, deleteZeroDivider(checkSyntax, int_).toNumberString());
            code = TRUE_CODE;
            index_ = next_ + 1;
        }
        boolean foundOpenChar_ = false;
        index_ = CustList.FIRST_INDEX;
        while (index_ < numericString.length()) {
            if (foundOpenChar_) {
                String int_;
                CaughtStringIndex c_;
                c_ = tryGetPositiveInteger(index_);
                if (!c_.isValid()) {
                    index_ = c_.getIndex();
                    foundOpenChar_ = false;
                    continue;
                }
                int_ = c_.getToken();
                int next_= c_.getIndex();
                numericString.replace(index_ - 1, next_, deleteZeroDivider(checkSyntax, StringList.concat(String.valueOf(POINT),int_)).toNumberString());
                code = TRUE_CODE;
                index_ = next_ + 1;
                foundOpenChar_ = false;
                continue;
            }
            if (numericString.charAt(index_) == POINT) {
                foundOpenChar_ = true;
            }
            index_ ++;
        }
    }

    void insertMult() {
        int index_ = CustList.FIRST_INDEX;
        code = FALSE_CODE;
        while (true) {
            //index_ < numericString.length()
            if (index_ + 1 >= numericString.length()) {
                break;
            }
            if (numericString.charAt(index_) == StringList.RIGHT_PAR) {
                if (numericString.charAt(index_ + 1) == StringList.LEFT_PAR) {
                    numericString.insert(index_ + 1, FOIS);
                    code = TRUE_CODE;
                    index_ ++;
                }
            }
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        while (true) {
            //index_ < numericString.length()
            if (index_ + 1 >= numericString.length()) {
                break;
            }
            if (Character.isDigit(numericString.charAt(index_))) {
                if (numericString.charAt(index_ + 1) == StringList.LEFT_PAR) {
                    numericString.insert(index_ + 1, FOIS);
                    code = TRUE_CODE;
                    index_ ++;
                }
            }
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        while (true) {
            //index_ < numericString.length()
            if (index_ + 1 >= numericString.length()) {
                break;
            }
            if (numericString.charAt(index_) == StringList.RIGHT_PAR) {
                if (Character.isLowerCase(numericString.charAt(index_ + 1))) {
                    numericString.insert(index_ + 1, FOIS);
                    code = TRUE_CODE;
                    index_ ++;
                }
            }
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        while (true) {
            //index_ < numericString.length()
            if (index_ + 1 >= numericString.length()) {
                break;
            }
            if (Character.isDigit(numericString.charAt(index_))) {
                if (!Character.isLowerCase(numericString.charAt(index_ + 1))) {
                    index_++;
                    continue;
                }
                int begin_ = index_ + 1;
                while (true) {
                    if (index_ + 1 >= numericString.length()) {
                        break;
                    }
                    if (!Character.isLowerCase(numericString.charAt(index_ + 1))) {
                        break;
                    }
                    index_ ++;
                }
                if (index_ + 1 < numericString.length()) {
                    if (numericString.charAt(index_ + 1) == StringList.LEFT_PAR) {
                        code = TRUE_CODE;
                        numericString.insert(begin_, FOIS);
                    }
                }
                index_ = begin_;
                continue;
            }
            index_++;
        }
    }

    private CaughtStringIndex tryGetPositiveInteger(int _index) {
        StringBuilder str_ = new StringBuilder();
        int len_ = numericString.length();
        int index_ = _index;
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (!Character.isDigit(numericString.charAt(index_))) {
            str_.append(numericString.charAt(index_));
            index_ ++;
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        //_input.charAt(index_) is digit
        while (true) {
            if (index_ >= len_) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            if (!Character.isDigit(numericString.charAt(index_))) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            str_.append(numericString.charAt(index_));
            index_++;
        }
    }

    private CaughtStringIndex tryGetRateNumber(int _index) {
        StringBuilder str_ = new StringBuilder();
        int len_ = numericString.length();
        int index_ = _index;
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (numericString.charAt(index_) == Rate.MINUS_CHAR) {
            str_.append(numericString.charAt(index_));
            index_ ++;
        }
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (!Character.isDigit(numericString.charAt(index_))) {
            str_.append(numericString.charAt(index_));
            index_ ++;
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        //_input.charAt(index_) is digit
        while (true) {
            if (index_ >= len_) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            if (!Character.isDigit(numericString.charAt(index_))) {
                if (numericString.charAt(index_) != Rate.SEP_NUM_DEN_CHAR) {
                    return new CaughtStringIndex(str_.toString(), index_, true);
                }
                break;
            }
            str_.append(numericString.charAt(index_));
            index_++;
        }
        //_input.charAt(index_) is rate separator
        if (index_ + 1 >= len_) {
            return new CaughtStringIndex(str_.toString(), index_ + 1, false);
        }
        str_.append(numericString.charAt(index_));
        index_ ++;
        if (!Character.isDigit(numericString.charAt(index_))) {
            str_.append(numericString.charAt(index_));
            index_ ++;
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        while (true) {
            if (index_ >= len_) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            if (!Character.isDigit(numericString.charAt(index_))) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            str_.append(numericString.charAt(index_));
            index_++;
        }
    }

    private CaughtStringIndex tryGetSet(int _index) {
        StringBuilder str_ = new StringBuilder();
        int len_ = numericString.length();
        int index_ = _index;
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (numericString.charAt(index_) != LEFT_BRACE_SET_CHAR) {
            if (startsWith(EMPTY_SET, index_)) {
                //if (index_ + EMPTY_SET.length() >= len_) {
                //    return new CaughtStringIndex(EMPTY_SET, index_ + EMPTY_SET.length(), true);
                //}
                //if (!StringList.isWordChar(numericString.charAt(index_ + EMPTY_SET.length()))) {
                return new CaughtStringIndex(EMPTY_SET, index_ + EMPTY_SET.length(), true);
                //}
            }
            str_.append(numericString.charAt(index_));
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        int indexRightBrace_ = numericString.indexOf(RIGHT_BRACE_SET, index_);
        if (indexRightBrace_ == CustList.INDEX_NOT_FOUND_ELT) {
            str_.append(numericString.charAt(index_));
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        //All sets are formatted by words joined by ; and wrapped by braces
        while (index_ <= indexRightBrace_) {
            str_.append(numericString.charAt(index_));
            index_ ++;
        }
        return new CaughtStringIndex(str_.toString(), index_, true);
    }

    private boolean startsWith(String _subString, int _index) {
        int len_ = numericString.length();
        int lenSub_ = _subString.length();
        if (_index + lenSub_ > len_) {
            return false;
        }
        //i < lenSub_ => _index + i < _index + lenSub_
        //_index + i < _index + lenSub_ && _index + lenSub_ <= len_
        //=> _index + i < len_ => _index + i is always a valid index
        for (int i = 0; i < lenSub_; i++) {
            if (numericString.charAt(_index + i) != _subString.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String operators(boolean _checkSyntax, char _op, String _nbOne, String _nbTwo) {
        String res_ = EMPTY_STRING;
        if(_op == PLUS){
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, _nbTwo);
            res_=Rate.plus(rateOne_, rateTwo_).toNumberString();
        }
        if(_op == FOIS){
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, _nbTwo);
            res_=Rate.multiply(rateOne_, rateTwo_).toNumberString();
        }
        if(_op == MOINS){
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, _nbTwo);
            res_=Rate.minus(rateOne_, rateTwo_).toNumberString();
        }
        if(_op == DIVISION){
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_;
            if (StringList.quickEq(_nbTwo, MINUS_ZERO)) {
                rateTwo_ = deleteZeroDivider(_checkSyntax, ZERO);
            } else {
                rateTwo_ = deleteZeroDivider(_checkSyntax, _nbTwo);
            }
            if (rateTwo_.isZero()) {
                if (_checkSyntax) {
                    return Rate.one().toNumberString();
                }
                return res_;
            }
            res_=Rate.divide(rateOne_, rateTwo_).toNumberString();
        }
        return res_;
    }

    private static String functions(boolean _checkSyntax, String _name, StringList _args) {
        if(StringList.quickEq(_name, CARD)){
            String texteArg_=_args.first();
            if(StringList.quickEq(texteArg_, EMPTY_SET)){
                return ZERO;
            }
            StringList elements_ = new StringList();
            for (String e: StringList.splitChars(texteArg_, SEPARATOR_SET_CHAR)) {
//                String e_ = e.replace(LEFT_BRACE_SET, EMPTY_STRING);
//                e_ = e_.replace(RIGHT_BRACE_SET, EMPTY_STRING);
                String e_ = StringList.removeStrings(e, LEFT_BRACE_SET, RIGHT_BRACE_SET);
                if (elements_.containsStr(e_)) {
                    continue;
                }
                elements_.add(e_);
            }
            return String.valueOf(elements_.size());
        }
        if(StringList.quickEq(_name, INTER)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgOne_,EMPTY_SET)||StringList.quickEq(textArgTwo_,EMPTY_SET)){
                return EMPTY_SET;
            }
            StringList ls_ = toElements(textArgOne_);
            ls_.addAllElts(toElements(textArgTwo_));
            ls_.sort();
            StringBuilder retour_ = new StringBuilder();
            boolean entreeBoucle_=true;
            int nbElts_=ls_.size();
            for (int i = CustList.FIRST_INDEX;i<nbElts_;i++){
                if(!entreeBoucle_){
                    if(StringList.quickEq(ls_.get(i),ls_.get(i-1))){
                        retour_.append(ls_.get(i));
                        retour_.append(SEPARATOR_SET_CHAR);
                    }
                }
                entreeBoucle_=false;
            }
            //String ret_ = retour_.toString();
            if (retour_.length() == CustList.SIZE_EMPTY) {
                return EMPTY_SET;
            }
            retour_.deleteCharAt(retour_.length() - 1);
            return StringList.concat(LEFT_BRACE_SET,retour_,RIGHT_BRACE_SET);
        }
        if(StringList.quickEq(_name,UNION)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                if(StringList.quickEq(textArgTwo_,EMPTY_SET)){
                    return EMPTY_SET;
                }
                return textArgTwo_;
            }
            if(StringList.quickEq(textArgTwo_,EMPTY_SET)){
                return textArgOne_;
            }
            StringList ls_ = toElements(textArgOne_);
            ls_.addAllElts(toElements(textArgTwo_));
            ls_.sort();
            ls_.removeDuplicates();
            String retour_ = ls_.join(SEPARATOR_SET_CHAR);
            return StringList.concat(LEFT_BRACE_SET,retour_,RIGHT_BRACE_SET);
        }
        if(StringList.quickEq(_name,COMPL)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,EMPTY_SET)){
                return EMPTY_SET;
            }
            if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                return textArgTwo_;
            }
            StringList ls_ = toElements(textArgOne_);
            StringList elements_ = new StringList();
            for (String s: toElements(textArgTwo_)) {
                if (ls_.containsStr(s)) {
                    continue;
                }
                elements_.add(s);
            }
            if (elements_.isEmpty()) {
                return EMPTY_SET;
            }
            return StringList.concat(LEFT_BRACE_SET,elements_.join(SEPARATOR_SET_CHAR),RIGHT_BRACE_SET);
        }
        if(StringList.quickEq(_name,INCL)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,EMPTY_SET)){
                if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                    return ONE;
                }
                return ZERO;
            }
            if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                return ONE;
            }
            StringList ls_ = toElements(textArgOne_);
            StringList sortedListOne_=new StringList(ls_);
            sortedListOne_.sort();
            ls_.addAllElts(toElements(textArgTwo_));
            ls_.sort();
            StringBuilder inter_ = new StringBuilder();
            boolean entreeBoucle_=true;
            int nbElts_=ls_.size();
            for (int i = CustList.FIRST_INDEX;i<nbElts_;i++){
                if(!entreeBoucle_){
                    if(StringList.quickEq(ls_.get(i),ls_.get(i-1))){
                        inter_.append(ls_.get(i));
                        inter_.append(SEPARATOR_SET_CHAR);
                    }
                }
                entreeBoucle_=false;
            }
            if(inter_.length() == 0){
                return ZERO;
            }
            inter_.deleteCharAt(inter_.length() - 1);
            if(StringList.quickEq(sortedListOne_.join(SEPARATOR_SET_CHAR),inter_.toString())){
                return ONE;
            }
            return ZERO;
        }
        if(StringList.quickEq(_name,NON_INCL)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,EMPTY_SET)){
                if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                    return ZERO;
                }
                return ONE;
            }
            if(StringList.quickEq(textArgOne_,EMPTY_SET)){
                return ZERO;
            }
            StringList ls_ = toElements(textArgOne_);
            StringList sortedListOne_=new StringList(ls_);
            sortedListOne_.sort();
            ls_.addAllElts(toElements(textArgTwo_));
            ls_.sort();
            StringBuilder inter_ = new StringBuilder();
            boolean entreeBoucle_=true;
            int nbElts_=ls_.size();
            for (int i = CustList.FIRST_INDEX;i<nbElts_;i++){
                if(!entreeBoucle_){
                    if(StringList.quickEq(ls_.get(i),ls_.get(i-1))){
                        inter_.append(ls_.get(i));
                        inter_.append(SEPARATOR_SET_CHAR);
                    }
                }
                entreeBoucle_=false;
            }
            if (inter_.length() == CustList.SIZE_EMPTY) {
                return ONE;
            }
            inter_.deleteCharAt(inter_.length() - 1);
            if(StringList.quickEq(sortedListOne_.join(SEPARATOR_SET_CHAR),inter_.toString())){
                return ZERO;
            }
            return ONE;
        }
        if(StringList.quickEq(_name,EQ_NUM)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgOne_,textArgTwo_)){
                return ONE;
            }
            //texteArg1_!=texteArg2_ et pour tour arg commencant par une accolade ouvrante arg est different de vide
            if(StringList.quickEq(textArgOne_,EMPTY_SET)||StringList.quickEq(textArgTwo_,EMPTY_SET)){
                return ZERO;
            }
            StringList listOne_ = toElements(textArgOne_);
            listOne_.sort();
            StringList listTwo_ = toElements(textArgTwo_);
            listTwo_.sort();
            if(StringList.eqStrings(listOne_,listTwo_)){
                return ONE;
            }
            return ZERO;
        }
        if(StringList.quickEq(_name,NON_EQ_NUM)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgOne_,textArgTwo_)){
                return ZERO;
            }
            //texteArg1_!=texteArg2_ et pour tour arg commencant par une accolade ouvrante arg est different de vide
            if(StringList.quickEq(textArgOne_,EMPTY_SET)||StringList.quickEq(textArgTwo_,EMPTY_SET)){
                return ONE;
            }
            StringList listOne_ = toElements(textArgOne_);
            listOne_.sort();
            StringList listTwo_ = toElements(textArgTwo_);
            listTwo_.sort();
            if(StringList.eqStrings(listOne_,listTwo_)){
                return ZERO;
            }
            return ONE;
        }
        String res_=EMPTY_STRING;
        if(StringList.quickEq(_name,DIV_FCT)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,ZERO)||StringList.quickEq(textArgTwo_,MINUS_ZERO)){
                if (_checkSyntax) {
                    res_ = Rate.one().toNumberString();
                }
                return res_;
            }
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            res_=Rate.divide(rateOne_, rateTwo_).toNumberString();
        }
        //valeur absolue
        if(StringList.quickEq(_name,ABS)){
            String texteArg_=_args.first();
            res_=deleteZeroDivider(_checkSyntax, texteArg_).absNb().toNumberString();
        }
        //partie entiere
        if(StringList.quickEq(_name,ENT)){
            String texteArg_=_args.first();
            res_=deleteZeroDivider(_checkSyntax, texteArg_).intPart().toNumberString();
        }
        //troncature vers l'entier en valeur absolue le plus proche
        //exemples:
        //troncature(32/10)=3,troncature(35/10)=3,troncature(37/10)=3,troncature(4)=4
        //troncature(-2)=-2,troncature(-21/10)=-2,troncature(-25/10)=-2,troncature(-26/10)=-2
        if(StringList.quickEq(_name,TRONC)){
            String texteArg_=_args.first();
            res_=deleteZeroDivider(_checkSyntax, texteArg_).toLgInt().toNumberString();
        }
        //numerateur
        if(StringList.quickEq(_name,NUM)){
            String texteArg_=_args.first();
            res_=deleteZeroDivider(_checkSyntax, texteArg_).getNumeratorString();
        }
        //denominateur
        if(StringList.quickEq(_name,DEN)){
            String texteArg_=_args.first();
            res_=deleteZeroDivider(_checkSyntax, texteArg_).getDenominatorString();
        }
        //puissance
        if(StringList.quickEq(_name,PUIS)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            Rate base_=deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate exposant_=deleteZeroDivider(_checkSyntax, textArgTwo_);
            try {
                res_=Rate.powNb(base_, exposant_).toNumberString();
            } catch (RuntimeException _0) {
                if (_checkSyntax) {
                    res_= Rate.one().toNumberString();
                } else {
                    return res_;
                }
            }
        }
        //division euclidienne
        if(StringList.quickEq(_name,QUOT)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,ZERO)||StringList.quickEq(textArgTwo_,MINUS_ZERO)){
                if (_checkSyntax) {
                    res_ = Rate.one().toNumberString();
                }
                return res_;
            }
            res_=LgInt.divide(new LgInt(textArgOne_),new LgInt(textArgTwo_)).toNumberString();
        }
        //reste entier
        if(StringList.quickEq(_name,MOD)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,ZERO)||StringList.quickEq(textArgTwo_,MINUS_ZERO)){
                if (_checkSyntax) {
                    res_ = Rate.one().toNumberString();
                }
                return res_;
            }
            res_=LgInt.remain(new LgInt(textArgOne_),new LgInt(textArgTwo_)).toNumberString();
        }
        //reste rationnel
        if(StringList.quickEq(_name,MODTAUX)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            if(StringList.quickEq(textArgTwo_,ZERO)||StringList.quickEq(textArgTwo_,MINUS_ZERO)){
                if (_checkSyntax) {
                    res_ = Rate.one().toNumberString();
                }
                return res_;
            }
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            res_=Rate.minus(rateOne_,Rate.multiply(new Rate(Rate.divide(rateOne_,rateTwo_).intPart()),rateTwo_)).toNumberString();
        }
        //minimum
        if(StringList.quickEq(_name,MIN)){
            Rate min_=deleteZeroDivider(_checkSyntax, _args.first());
            for(String a:_args.mid(CustList.SECOND_INDEX, _args.size())){
                Rate arg_=deleteZeroDivider(_checkSyntax, a);
                if(Rate.strGreater(min_, arg_)){
                    min_=arg_;
                }
            }
            res_=min_.toNumberString();
        }
        //maximum
        if(StringList.quickEq(_name,MAX)){
            Rate max_=deleteZeroDivider(_checkSyntax, _args.first());
            for(String a:_args.mid(CustList.SECOND_INDEX, _args.size())){
                Rate arg_=deleteZeroDivider(_checkSyntax, a);
                if(Rate.strLower(max_, arg_)){
                    max_=arg_;
                }
            }
            res_=max_.toNumberString();
        }
        //moyenne
        if(StringList.quickEq(_name,MOY)){
            Rate moy_=Rate.zero();
            for(String a:_args){
                moy_.addNb(deleteZeroDivider(_checkSyntax, a));
            }
            moy_.divideBy(new Rate(_args.size()));
            res_=moy_.toNumberString();
        }
        //variance
        if(StringList.quickEq(_name,VAR)){
            Rate var_=Rate.zero();
            Rate moy_=Rate.zero();
            for(String a:_args){
                Rate a_ = deleteZeroDivider(_checkSyntax, a);
                moy_.addNb(a_);
                var_.addNb(Rate.multiply(a_, a_));
            }
            moy_.divideBy(new Rate(_args.size()));
            var_.divideBy(new Rate(_args.size()));
            var_.removeNb(Rate.multiply(moy_, moy_));
            res_=var_.toNumberString();
        }
        //segment
        if(StringList.quickEq(_name,CARAC_FERME)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.get(CustList.SECOND_INDEX);
            String textArgThree_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            Rate rateThree_ = deleteZeroDivider(_checkSyntax, textArgThree_);
            if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_,rateThree_)){
                res_=ONE;
            }
        }
        //ouvert borne
        if(StringList.quickEq(_name,CARAC_OUVERT)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.get(CustList.SECOND_INDEX);
            String textArgThree_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            Rate rateThree_ = deleteZeroDivider(_checkSyntax, textArgThree_);
            if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
                res_=ONE;
            }
        }
        //semi ouvert gauche borne
        if(StringList.quickEq(_name,CARAC_SEMI_OUVERT_G)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.get(CustList.SECOND_INDEX);
            String textArgThree_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            Rate rateThree_ = deleteZeroDivider(_checkSyntax, textArgThree_);
            if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_, rateThree_)){
                res_=ONE;
            }
        }
        //semi ouvert droite borne
        if(StringList.quickEq(_name,CARAC_SEMI_OUVERT_D)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.get(CustList.SECOND_INDEX);
            String textArgThree_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            Rate rateThree_ = deleteZeroDivider(_checkSyntax, textArgThree_);
            if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
                res_=ONE;
            }
        }
        //ouvert droite minore
        if(StringList.quickEq(_name,CARAC_DROITE_OUVERT)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            if(Rate.strGreater(rateOne_,rateTwo_)){
                res_=ONE;
            }
        }
        //ferme droite minore
        if(StringList.quickEq(_name,CARAC_DROITE_FERME)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            if(Rate.greaterEq(rateOne_,rateTwo_)){
                res_=ONE;
            }
        }
        //ouvert droite majore
        if(StringList.quickEq(_name,CARAC_GAUCHE_OUVERT)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            if(Rate.strLower(rateOne_,rateTwo_)){
                res_=ONE;
            }
        }
        //ferme gauche majore
        if(StringList.quickEq(_name,CARAC_GAUCHE_FERME)){
            String textArgOne_=_args.first();
            String textArgTwo_=_args.last();
            res_=ZERO;
            Rate rateOne_ = deleteZeroDivider(_checkSyntax, textArgOne_);
            Rate rateTwo_ = deleteZeroDivider(_checkSyntax, textArgTwo_);
            if(Rate.lowerEq(rateOne_,rateTwo_)){
                res_=ONE;
            }
        }
        //signe
        if(StringList.quickEq(_name,SGN)){
            res_=ZERO;
            String texteArg_=_args.first();
            Rate tx_ = deleteZeroDivider(_checkSyntax, texteArg_);
            if (!tx_.isZero()) {
                if (tx_.isZeroOrGt()) {
                    res_ = ONE;
                } else {
                    res_ = MINUS_ONE;
                }
            }
        }
        return res_;
    }

    @Override
    public Rate getResult() {
        return deleteZeroDivider(checkSyntax, numericString.toString());
    }

    static Rate evaluate(String _numericString, StringMap<String> _variables, Rate _default) {
        return evaluate(_numericString, _variables, false, _default);
    }

    static Rate evaluatePositiveOrZeroExp(String _numericString, StringMap<String> _variables, Rate _default) {
        return evaluatePositiveOrZeroExp(_numericString, _variables, false, _default);
    }

    static Rate evaluatePositiveExp(String _numericString, StringMap<String> _variables, Rate _default) {
        return evaluatePositiveExp(_numericString, _variables, false, _default);
    }

    static Rate evaluateCheckinSyntax(String _numericString, StringMap<String> _variables) {
        NumericString num_ = new NumericString(_numericString, _variables);
        num_.evaluateExp(true);
        return num_.getResult();
    }

    static Rate evaluate(String _numericString, StringMap<String> _variables, boolean _checkSyntax, Rate _default) {
        try {
            NumericString num_ = new NumericString(_numericString, _variables);
            num_.evaluateExp(_checkSyntax);
            return num_.getResult();
        } catch (RuntimeException _0) {
            return new Rate(_default);
        }
    }

    static Rate evaluatePositiveOrZeroExp(String _numericString, StringMap<String> _variables, boolean _checkSyntax, Rate _default) {
        try {
            NumericString num_ = new NumericString(_numericString, _variables);
            num_.evaluateExp(_checkSyntax);
            Rate result_ = num_.getResult();
            if (!result_.isZeroOrGt()) {
                throw new BadNumberException();
            }
            return result_;
        } catch (RuntimeException _0) {
            return _default.absNb();
        }
    }

    static Rate evaluatePositiveExp(String _numericString, StringMap<String> _variables, boolean _checkSyntax, Rate _default) {
        try {
            NumericString num_ = new NumericString(_numericString, _variables);
            num_.evaluateExp(_checkSyntax);
            Rate result_ = num_.getResult();
            if (result_.isZero()) {
                throw new BadNumberException();
            }
            if (!result_.isZeroOrGt()) {
                throw new BadNumberException();
            }
            return result_;
        } catch (RuntimeException _0) {
            return _default.absNb();
        }
    }

    @Override
    public String display() {
        return numericString.toString();
    }

    static Rate deleteZeroDivider(boolean _checkSyntax, String _string) {
        if (!_checkSyntax) {
            return new Rate(_string);
        }
        if (Rate.isValid(_string)) {
            return new Rate(_string);
        }
        StringList numDen_ = StringList.splitChars(_string, Rate.SEP_NUM_DEN_CHAR);
        if (numDen_.size() == CustList.ONE_ELEMENT + CustList.ONE_ELEMENT) {
            for (String n: numDen_) {
                if (!StringList.isNumber(n)) {
                    throw new FormatException(_string);
                }
            }
            return Rate.one();
        }
        throw new FormatException(_string);
//        try {
//            return new Rate(_string);
//        } catch (FormatException _0) {
//            if (isCheckSyntax()) {
//                StringList numDen_ = StringList.splitChars(_string, Rate.SEP_NUM_DEN_CHAR);
//                if (numDen_.size() > CustList.ONE_ELEMENT) {
//                    String str_ = numDen_.last();
//                    if (str_.startsWith(String.valueOf(MOINS))) {
//                        str_ = str_.substring(CustList.SECOND_INDEX);
//                    }
//                    for (char c: str_.toCharArray()) {
//                        if (!Character.isDigit(c)) {
//                            throw _0;
//                        }
//                    }
//                    return Rate.one();
//                }
//            }
//            throw _0;
//        }
    }

    private static StringList toElements(String _string) {
        StringList list_ = new StringList();
        for (String s: StringList.splitChars(_string, SEPARATOR_SET_CHAR, LEFT_BRACE_SET_CHAR, RIGHT_BRACE_SET_CHAR)) {
            if (s.isEmpty()) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    @Override
    public String beforeEvaluated() {
        return numericString.toString();
    }

    @Override
    public boolean isValid() {
        return Rate.isValid(numericString.toString());
    }

//    public static boolean isCheckSyntax() {
//        return _checkSyntax_;
//    }

//    public static void setCheckSyntax(boolean _checkSyntax) {
//        NumericString._checkSyntax_ = _checkSyntax;
//    }
}
