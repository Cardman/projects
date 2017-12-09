package code.resources;

public final class BaseSixtyFourUtil {

    private static final byte PADDING = 127;

    private BaseSixtyFourUtil(){
    }

    public static byte[] parseBaseSixtyFourBinary(String _text) {
        int buflen_ = guessLength(_text);
        byte[] out_ = new byte[buflen_];
        int o_=0;

        int len_ = _text.length();

        byte[] quadruplet_ = new byte[4];
        int q_=0;

        // convert each quadruplet to three bytes.
        for(int i=0; i<len_; i++ ) {
            char ch_ = _text.charAt(i);
            
            byte v_;
            if (Character.isDigit(ch_)) {
                v_ = (byte) (52 + Byte.parseByte(String.valueOf(ch_)));
            } else if (Character.isLetter(ch_)) {
                if (Character.isLowerCase(ch_)) {
                    int diff_ = (ch_) - ('a');
                    v_ = (byte) (26+diff_);
                } else {
                    int diff_ = (ch_) - ('A');
                    v_ = (byte) diff_;
                }
            } else if (ch_ == '+') {
                v_ = 62;
            } else if (ch_ == '/') {
                v_ = 63;
            } else {
                v_ = PADDING;
            }

            //v!=-1
            quadruplet_[q_] = v_;
            q_++;

            if(q_==4) {
                // quadruplet is now filled.
                int firstBytes_ = quadruplet_[0];
                int secondBytes_ = quadruplet_[1];
                int thirdBytes_ = quadruplet_[2];
                int fourthBytes_ = quadruplet_[3];
                out_[o_] = (byte) (4 * firstBytes_ + secondBytes_ / 16);
                o_++;
                if( quadruplet_[2]!=PADDING ) {
                    out_[o_] = (byte)(secondBytes_ * 16 + thirdBytes_ / 4);
                    o_++;
                }
                if( quadruplet_[3]!=PADDING ) {
                    out_[o_] = (byte)(thirdBytes_ * 64 +fourthBytes_);
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

        int size_ = len_/4*3;
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
        char[] buf_ = new char[((len_+2)/3)*4];
        int ptr_ = 0;
        
        for( int i=0; i<len_; i+=3 ) {
            int adj_ = _input[i];
            if (adj_ < 0) {
                adj_ += 256;
            }
            buf_[ptr_] = encode(adj_/4);
            ptr_++;
            if (len_-i == 2) {
                int adjNext_ = _input[i+1];
                if (adjNext_ < 0) {
                    adjNext_ += 256;
                }
                buf_[ptr_] = encode(
                        ((adj_%4)*16) +
                        ((adjNext_/16)%16));
                buf_[ptr_] = encode((adjNext_%16)*4);
                ptr_++;
                buf_[ptr_] = '=';
                continue;
            }
            if (len_-i == 1) {
                buf_[ptr_] = encode((adj_%4)*16);
                ptr_++;
                buf_[ptr_] = '=';
                ptr_++;
                buf_[ptr_] = '=';
                continue;
            }
            int adjNext_ = _input[i+1];
            if (adjNext_ < 0) {
                adjNext_ += 256;
            }
            int adjNextNext_ = _input[i+2];
            if (adjNextNext_ < 0) {
                adjNextNext_ += 256;
            }
            buf_[ptr_] = encode(
                    ((adj_%4)*16) +
                    ((adjNext_/16)%16));
            ptr_++;
            buf_[ptr_] = encode(
                        ((adjNext_%16)*4)+
                        ((adjNextNext_/64)%4));
            ptr_++;
            buf_[ptr_] = encode(adjNextNext_%64);
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
        if (_i < 26) {
            return (char) ('A'+_i);
        }
        if (_i < 52) {
            return (char) ('a'-26+_i);
        }
        if (_i < 62) {
            return (char) ('0'-52+_i);
        }
        if (_i == 62) {
            return '+';
        }
        return '/';
    }
}
