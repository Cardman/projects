package code.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public final class ResourceFiles {
    public static final String SEPARATEUR = "/";
    private static final String EMPTY_STRING = "";
    private static final char INVALID_CHARACTER = 65533;

    private ResourceFiles() {
    }

    public static String ressourceFichier(String _filePath) {
        return resourceTextFile(_filePath);
    }

    private static String resourceTextFile(String _filePath) {
        String lignes_ = readFile(_filePath, StandardCharsets.UTF_8.getName());
        if (lignes_ == null) {
            return EMPTY_STRING;
        }
        int ind_ = lignes_.indexOf(INVALID_CHARACTER);
        if (ind_ >= 0) {
            lignes_ = readFile(_filePath, StandardCharsets.ISO_8859_1.getName());
        }
        return lignes_;
    }

    private static String readFile(String _file, String _encoding) {
        InputStream inputStream_ = null;
        InputStreamReader reader_ = null;
        BufferedReader br_ = null;
        try {
            inputStream_ = ClassLoader.getSystemResourceAsStream(_file);
            reader_ = new InputStreamReader(inputStream_, _encoding);
            br_ = new BufferedReader(reader_);
            return readingFile(br_);
        } catch (UnsupportedEncodingException _0) {
            return null;
        } finally {
            close(inputStream_, reader_, br_);
        }
    }

    /**
     @param _br reader
    @return
    @throws IOException
    */
    private static String readingFile(BufferedReader _br) {
        try {
            StringBuilder strBuilder_ = new StringBuilder();
            while (true) {

                int char_ = _br.read();
                if (char_ < 0) {
                    break;
                }
                if (char_ != '\r') {
                    strBuilder_.append((char) char_);
                }
            }
            return strBuilder_.toString();
        } catch (IOException _0) {
            return null;
        }
    }

    private static void close(InputStream _inputStream,
            InputStreamReader _reader, BufferedReader _br) {
        try {
            if (_br != null) {
                _br.close();
            }
            if (_reader != null) {
                _reader.close();
            }
            if (_inputStream != null) {
                _inputStream.close();
            }
        } catch (RuntimeException _0) {
        } catch (IOException _0) {
        }
    }
}
