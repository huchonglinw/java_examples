package commandbash.command;

import java.util.List;

/**
 * 描述一个命令的结构体
 * command [options] filename
 * command [options] filename | command [options]
 * command [options] pattern filename
 *
 * @author: hcl
 * @date: 2021/6/5 00:34
 */
public class Command {
    private String commandName;

    private String option;

    /**
     * 数据来源
     */
    private List<String> dataSources;

    /**
     * 数据来源格式
     */
    private Integer dataSourcesType;

    /**
     * 普通命令 0是1否
     */
    private Integer simple;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


    public List<String> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<String> dataSources) {
        this.dataSources = dataSources;
    }

    public Integer getSimple() {
        return simple;
    }

    public void setSimple(Integer simple) {
        this.simple = simple;
    }

    public Integer getDataSourcesType() {
        return dataSourcesType;
    }

    public void setDataSourcesType(Integer dataSourcesType) {
        this.dataSourcesType = dataSourcesType;
    }
}
