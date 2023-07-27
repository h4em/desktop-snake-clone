import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {
    public int readHighscore() {
        int highscore = 0;
        try {
            FileInputStream fis = new FileInputStream(new File("highscore.bin"));
            highscore = readIntFromBinaryFile(fis);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return highscore;
    }
    private void saveHighscore(int highscore) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("highscore.bin"));
            saveIntToBinaryFile(highscore, fos);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void saveScoreIfNewHighscore(int score) {
        int highscore = readHighscore();
        if(score > highscore) {
            saveHighscore(highscore);
        }
    }

    private int readIntFromBinaryFile(FileInputStream fis) {
        int n = 0;
        try {
            n |= fis.read() << 24;
            n |= fis.read() << 16;
            n |= fis.read() << 8;
            n |= fis.read();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return n;
    }

    private void saveIntToBinaryFile(int n, FileOutputStream fos) {
        try {
            fos.write(n >> 24);
            fos.write(n >> 16);
            fos.write(n >> 8);
            fos.write(n);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}