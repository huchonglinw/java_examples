package local.contants;

import java.io.File;

/**
 * @author: hcl
 * @date: 2021/6/2 17:21
 */
public interface Constants {
    File FILE1 = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\access.log");
    File FILE2 = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\StringUtils.java");

    File SDXL_PROP = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\sdxl_prop.txt");
    File SDXL_TEMPLATE = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\sdxl_template.txt");
    File SDXL_OUTPUT = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\sdxl_output.txt");

    File OUT_PUT_FILE = new File("F:\\workspace_idea\\java_examples\\io-examples\\src\\main\\resources\\validLineCount.txt");

    Integer PORT = 8089;

    String HOST = "localhost";

    String GET = "GET";

    String POST = "POST";

}
