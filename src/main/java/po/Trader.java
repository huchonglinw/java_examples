package po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huchonglin
 * @date 2020/11/13 15:10
 */
@Data
@NoArgsConstructor
public  class Trader{
 
    private String name;
    private String city;
 
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
 
    public String getName(){
        return this.name;
    }
 
    public String getCity(){
        return this.city;
    }
 
    public void setCity(String newCity){
        this.city = newCity;
    }

}