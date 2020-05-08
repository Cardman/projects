package code.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ResourceFiles {
    public static final String SEPARATEUR = "/";
    private static final String EMPTY_STRING = "";

    private ResourceFiles() {
    }

    public static String ressourceFichier(String _filePath) {
        return resourceTextFile(_filePath);
    }

    private static String resourceTextFile(String _filePath) {
        String lignes_ = readFile(_filePath);
        if (lignes_ == null) {
            return EMPTY_STRING;
        }
        return lignes_;
    }

    private static String readFile(String _file) {
        InputStream inputStream_ = ClassLoader.getSystemResourceAsStream(_file);
        if (inputStream_ == null) {
            return null;
        }
        return readingFile(new BufferedReader(new InputStreamReader(inputStream_)));
    }

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

}
