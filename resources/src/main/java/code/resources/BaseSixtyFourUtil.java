package code.resources;

public final class BaseSixtyFourUtil {

    private static final short BYTE = 256;
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
    private static final byte PADDING = 127;
    private static final byte NB_LETTERS = 26;
    private static final byte NB_LETTERS_UPP_LOW = 52;
    private static final byte NB_DIGITS_LETTERS = 62;

    private BaseSixtyFourUtil(){
    }

    public static byte[] parseBaseSixtyFourBinary(String _text) {
        int buflen_ = guessLength(_text);
        byte[] out_ = new byte[buflen_];
        int o_=0;

        int len_ = _text.length();

        byte[] quadruplet_ = new byte[FOUR_BITS];
        int q_=0;

        // convert each quadruplet to three bytes.
        for(int i=0; i<len_; i++ ) {
            char ch_ = _text.charAt(i);
            
            byte v_;
            if (Character.isDigit(ch_)) {
                v_ = (byte) (NB_LETTERS_UPP_LOW + Byte.parseByte(String.valueOf(ch_)));
            } else if (Character.isLetter(ch_)) {
                if (Character.isLowerCase(ch_)) {
                    int diff_ = ch_ - 'a';
                    v_ = (byte) (NB_LETTERS+diff_);
                } else {
                    int diff_ = ch_ - 'A';
                    v_ = (byte) diff_;
                }
            } else if (ch_ == '+') {
                v_ = NB_DIGITS_LETTERS;
            } else if (ch_ == '/') {
                v_ = NB_DIGITS_LETTERS + 1;
            } else {
                v_ = PADDING;
            }

            //v!=-1
            quadruplet_[q_] = v_;
            q_++;

            if(q_==FOUR_BITS) {
                // quadruplet is now filled.
                int firstBytes_ = quadruplet_[0];
                int secondBytes_ = quadruplet_[1];
                int thirdBytes_ = quadruplet_[2];
                int fourthBytes_ = quadruplet_[THREE_COLORS_BYTES];
                out_[o_] = (byte) (FOUR_BITS * firstBytes_ + secondBytes_ / SIXTEEN_BITS);
                o_++;
                if( quadruplet_[2]!=PADDING ) {
                    out_[o_] = (byte)(secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
                    o_++;
                }
                if( quadruplet_[THREE_COLORS_BYTES]!=PADDING ) {
                    out_[o_] = (byte)(thirdBytes_ * SIXTY_FOUR_BITS +fourthBytes_);
                    o_++;
                }
                q_=0;
            }
        }

        if(buflen_==o_) {
            return out_;
        }

        byte[] nb_ = new byte[o_];
        System.arraycopy(out_,0,nb_,0,o_);
        return nb_;
    }
    private static int guessLength(String _text) {
        int len_ = _text.length();

        int size_ = len_/FOUR_BITS*THREE_COLORS_BYTES;
        int j_=len_-1;
        while (j_ >= 0) {
            if (_text.charAt(j_) == '=') {
                j_--;
                continue;
            }
            break;
        }

        j_++;
        int padSize_ = len_-j_;
        if(padSize_ >2) { 
            return size_;
        }

        return size_-padSize_;
    }
    public static String printBaseSixtyFourBinary(byte[] _input) {
        int len_ = _input.length;
        char[] buf_ = new char[((len_+2)/THREE_COLORS_BYTES)*FOUR_BITS];
        int ptr_ = 0;
        
        for( int i=0; i<len_; i+=THREE_COLORS_BYTES ) {
            int adj_ = _input[i];
            if (adj_ < 0) {
                adj_ += BYTE;
            }
            buf_[ptr_] = encode(adj_/FOUR_BITS);
            ptr_++;
            if (len_-i == 2) {
                int adjNext_ = _input[i+1];
                if (adjNext_ < 0) {
                    adjNext_ += BYTE;
                }
                buf_[ptr_] = encode(
                        ((adj_%FOUR_BITS)*SIXTEEN_BITS) +
                        ((adjNext_/SIXTEEN_BITS)%SIXTEEN_BITS));
                buf_[ptr_] = encode((adjNext_%SIXTEEN_BITS)*FOUR_BITS);
                ptr_++;
                buf_[ptr_] = '=';
                continue;
            }
            if (len_-i == 1) {
                buf_[ptr_] = encode((adj_%FOUR_BITS)*SIXTEEN_BITS);
                ptr_++;
                buf_[ptr_] = '=';
                ptr_++;
                buf_[ptr_] = '=';
                continue;
            }
            int adjNext_ = _input[i+1];
            if (adjNext_ < 0) {
                adjNext_ += BYTE;
            }
            int adjNextNext_ = _input[i+2];
            if (adjNextNext_ < 0) {
                adjNextNext_ += BYTE;
            }
            buf_[ptr_] = encode(
                    ((adj_%FOUR_BITS)*SIXTEEN_BITS) +
                    ((adjNext_/SIXTEEN_BITS)%SIXTEEN_BITS));
            ptr_++;
            buf_[ptr_] = encode(
                        ((adjNext_%SIXTEEN_BITS)*FOUR_BITS)+
                        ((adjNextNext_/SIXTY_FOUR_BITS)%FOUR_BITS));
            ptr_++;
            buf_[ptr_] = encode(adjNextNext_%SIXTY_FOUR_BITS);
            ptr_++;
        }
        int last_ = buf_.length - 1;
        while (buf_[last_] == 0) {
            if (last_ < 0) {
                break;
            }
            buf_[last_] = '=';
            last_--;
        }
        return new String(buf_);
    }
    public static char encode(int _i) {
        if (_i < NB_LETTERS) {
            return (char) ('A'+_i);
        }
        if (_i < NB_LETTERS_UPP_LOW) {
            return (char) ('a'-NB_LETTERS+_i);
        }
        if (_i < NB_DIGITS_LETTERS) {
            return (char) ('0'-NB_LETTERS_UPP_LOW+_i);
        }
        if (_i == NB_DIGITS_LETTERS) {
            return '+';
        }
        return '/';
    }
}
