

/**
 * @author huchonglin
 * @date 2021/1/22 20:29
 */
public class HashMapDemoObject {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "HashMapDemoObject{" +
                "id=" + id +
                '}';
    }
}
