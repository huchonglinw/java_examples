package local.qunar;

import local.contants.Constants;

import java.io.*;
import java.util.*;

/**
 * 从本题对应的附件中找到 sdxl_prop.txt 和 sdxl_template.txt。根据 sdxl_prop.txt 中内
 * 容替换掉 sdxl_template.txt 里$function(index)形式文字，将其还原成一本完整小说，写
 * 到文件 sdxl.txt 中，输出在 classpath 下。
 * 其中 function 有 4 种类型，替换规则如下：
 * 1) natureOrder 自然排序，即文本中排列顺序
 * 2) indexOrder 索引排序，文本中每行第一个数字为索引
 * 3) charOrder 文本排序，java 的字符排序
 * 4) charOrderDESC 文本倒序，java 的字符倒序
 * @author: hcl
 * @date: 2021/7/11 14:25
 */
public class QuestionThree {
    public static void main(String[] args) {
        //===================var===================
        File sdxl_template = Constants.SDXL_TEMPLATE;
        File sdxl_prop = Constants.SDXL_PROP;
        File sdxlOutput = Constants.SDXL_OUTPUT;
        Map<Integer, String> fileSortMap = new LinkedHashMap<>();
        List<String> javaSortList = new LinkedList<>();

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(sdxl_prop)));
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                String[] strArray = readLine.trim().split(" ");
                fileSortMap.put(Integer.parseInt(strArray[0]), strArray[1]);
            }

            javaSortList.addAll(fileSortMap.values());
            Collections.sort(javaSortList);

            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(sdxl_template)));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sdxlOutput)));
            while((readLine = bufferedReader.readLine()) != null) {
                if(readLine.contains("$")) {
                    String function = readLine.substring(readLine.indexOf("$") + 1, readLine.indexOf("("));
                    String number = readLine.substring(readLine.indexOf("(") + 1, readLine.indexOf(")"));
                    int index = Integer.parseInt(number);

                    if(function.equals("natureOrder")){
                        readLine = readLine.replaceAll("\\$\\w+\\(\\d+\\)", (String) fileSortMap.values().toArray()[index]);
                    }else if(function.equals("indexOrder")){
                        readLine = readLine.replaceAll("\\$\\w+\\(\\d+\\)", fileSortMap.get(index));
                    }else if(function.equals("charOrder")){
                        readLine = readLine.replaceAll("\\$\\w+\\(\\d+\\)", javaSortList.get(index));
                    }else if(function.equals("charOrderDESC")){
                        readLine = readLine.replaceAll("\\$\\w+\\(\\d+\\)", javaSortList.get(javaSortList.size()-index));
                    }
                }

                bufferedWriter.write(readLine);
                bufferedWriter.newLine();

            }
            bufferedReader.close();
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
