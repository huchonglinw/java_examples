package lambda;

import com.sun.deploy.net.proxy.UserDefinedProxyConfig;
import org.junit.Test;
import po.Trader;
import po.UserGroup;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 项目业务：获取共享产品组和未共享产品组
 *
 * @author huchonglin
 * @date 2020/11/14 20:08
 */
public class SharedUserGroupList {


    public static void main(String[] args) {
        UserGroup userGroup1 = new UserGroup("产品组名字1", "产品组编码1");
        UserGroup userGroup2 = new UserGroup("产品组名字2", "产品组编码2");
        UserGroup userGroup3 = new UserGroup("产品组名字3", "产品组编码3");
        UserGroup userGroup4 = new UserGroup("产品组名字4", "产品组编码4");
        UserGroup userGroup5 = new UserGroup("产品组名字5", "产品组编码5");
        UserGroup userGroup6 = new UserGroup("产品组名字6", "产品组编码6");
        UserGroup userGroup7 = new UserGroup("产品组名字7", "产品组编码7");
        UserGroup userGroup8 = new UserGroup("产品组名字8", "产品组编码8");
        UserGroup userGroup9 = new UserGroup("产品组名字9", "产品组编码9");

        List<UserGroup> allUserGroupList = Arrays.asList(userGroup1,
                userGroup2,
                userGroup3,
                userGroup4,
                userGroup5,
                userGroup6,
                userGroup7,
                userGroup8,
                userGroup9);
        List<UserGroup> sharedUserGroupList = Arrays.asList(userGroup1, userGroup2);
        List<UserGroup> currentUserGroupList = Arrays.asList(userGroup9, userGroup8);
        List<UserGroup> notSharedUserGroupList = new LinkedList<>();
        test1(allUserGroupList, currentUserGroupList, sharedUserGroupList);
    }

    public static void test1(List<UserGroup> allUserGroupList, List<UserGroup> currentUserGroupList, List<UserGroup> sharedUserGroupList) {

        // 根据共享用户组，当前用户组，所有用户组，过滤并筛选出未共享用户组
        // 所有用户组 对共享用户组和当前用户组作去重操作
        Optional<List<UserGroup>> sharedUserGroupList1 = Optional.ofNullable(sharedUserGroupList);

        allUserGroupList.stream()
                .filter(all -> {
                    boolean b = true;
                    // 如果初始化
                    if (sharedUserGroupList1.isPresent()) {
                        // 如果没有，则会返回false
                        b = sharedUserGroupList.stream()
                                .noneMatch(s -> Objects.equals(s.getUserGroupCode(), all.getUserGroupCode()));
                    }
                    return currentUserGroupList.stream()
                            .noneMatch(c -> Objects.equals(c.getUserGroupCode(), all.getUserGroupCode())) &&
                            b;
                })
                .map(m ->
                        new UserGroup(m.getUserGroupCode() + "新增的", m.getUserGroupName()))
                .forEach(System.out::println);


//                                    .stream()
//                                    .noneMatch(s -> Objects.equals(s.getUserGroupCode(), all.getUserGroupCode()));
//


//                .map(m -> {
//                    Trader trader = new Trader();
//                    return trader;
//                });
//        allUserGroupList.forEach(all ->{
//            sharedUserGroupList.stream()
//                    .filter(s -> Objects.equals(s.getUserGroupCode(), all.getUserGroupCode()))
//                    .findFirst()
//                    .map()
////            first.orElse()
//            if(first.isPresent()) {
//                currentUserGroupList.stream().filter(s -> )
//            }
//            currentUserGroupList.stream().filter()
//
//        });

    }
}
