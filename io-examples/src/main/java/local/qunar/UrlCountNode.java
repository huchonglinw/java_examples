package local.qunar;

/**
 * url和count次数的结构体
 * @author: hcl
 * @date: 2021/7/9 14:58
 */
public class UrlCountNode {
    private String url;
    private Integer score;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
