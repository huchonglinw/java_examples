package local.business;

public class ValidLine extends AbsBufferReader{
    private Integer count = 0;

    @Override
    protected void inRead(String str) {
        if(str == null || str.length() == 0) {
            return;
        }
        str = str.trim();
        if(str.startsWith("/*") || str.startsWith("//") || str.startsWith("*")) {
            count++;
        }
    }
}
