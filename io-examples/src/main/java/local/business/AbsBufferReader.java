package local.business;

import java.io.*;

public abstract class AbsBufferReader {
    /**
     * 使用BufferReader循环读取文件
     * br.readLine() 返回String
     */
    public void fileReader(File file) {
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str;
            while((str = br.readLine())!= null) {
                inRead(str);
            }
            beforeRead();
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void beforeRead() {

    }

    protected abstract void inRead(String str);
}
