package com.hlk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlk.mapper.UserMapper;
import com.hlk.pojo.User;
import com.hlk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringbootMybatisplusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testAutoFill(){
        User user = new User();
        user.setName("tom");
        user.setEmail("tom@163.com");
        user.setAge(300);
        if (userMapper.insert(user)==1) {
            userMapper.selectList(null).forEach(System.out::println);
        } else {
            System.out.println("添加数据失败");
        }
    }

    @Test
    public void testDelete() {
        if (userMapper.deleteById(1)==1) {
            System.out.println("删除数据成功");
            userMapper.selectList(null).forEach(System.out::println);
        } else {
            System.out.println("删除数据失败");
            userMapper.selectList(null).forEach(System.out::println);
        }
    }

    @Test
    public void testPage() {
        // Step1：创建一个 Page 对象
        Page<User> page = new Page<>();
        // Page<User> page = new Page<>(2, 5);
        // Step2：调用 mybatis-plus 提供的分页查询方法
        userService.page(page,null);
        // Step3：获取分页数据
        System.out.println(page.getCurrent()); // 获取当前页
        System.out.println(page.getTotal()); // 获取总记录数
        System.out.println(page.getSize()); // 获取每页的条数
        System.out.println(page.getRecords()); // 获取每页数据的集合
        System.out.println(page.getPages()); // 获取总页数
        System.out.println(page.hasNext()); // 是否存在下一页
        System.out.println(page.hasPrevious()); // 是否存在上一页
    }

    @Test
    public void testVersion() {
        User user = new User();
        user.setName("hlk");
        user.setEmail("hlk@qq.com");
        user.setAge(100);
        userService.save(user);
        userService.list().forEach(System.out::println);
        user.setName("jarry");
        userService.update(user, null);
        userService.list().forEach(System.out::println);
    }

    @Test
    public void testQueryWrapper() {
        // Step1：创建一个 QueryWrapper 对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // Step2： 构造查询条件
        queryWrapper
                .select("id", "name", "age")
                .eq("age", 100)
                .like("name", "jarry");

        // Step3：执行查询
        userService
                .list(queryWrapper)
                .forEach(System.out::println);
    }

}
