package com.yxc.controller;

import java.io.OutputStream;
import java.io.IOException;

import com.yxc.model.AppTestClass;
import com.yxc.model.UserEntity;
import com.yxc.repository.UserRepository;
//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Created by tm on 16/9/6.
 */
@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/index2",method = RequestMethod.GET)
    public String index() {
        return "index2";
    }

    @RequestMapping(value = "/root/admin/users",method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap){

        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList",userList);
        return "admin/users";
    }

    @RequestMapping(value = "/admin/users",method = RequestMethod.POST)
    public void appGetUsers(HttpServletResponse response, @ModelAttribute("user") UserEntity userEntity)  throws ParseException, IOException {

//        System.out.println(app.sdf);

        List<UserEntity> userList = userRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userList);
//        System.out.println(json);
        userRepository.saveAndFlush(userEntity);
//        String json = "{ok:ok}";
        OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
        response.setHeader("content-type", "text/html;charset=UTF-8");
        byte[] dataByteArr = json.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        outputStream.write(dataByteArr);
    }

    @RequestMapping(value = "/root/admin/users/add",method = RequestMethod.GET)
    public String addUser() {
        return "admin/addUser";
    }

    @RequestMapping(value = "/root/admin/users/addP",method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        userRepository.saveAndFlush(userEntity);
        return "redirect:/root/admin/users";
    }

    @RequestMapping(value = "/root/admin/users/show/{id}",method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId,ModelMap modelMap) {
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/userDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/root/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/root/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {

        // 更新用户信息
        userRepository.updateUser(user.getNickname(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/root/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/root/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/root/admin/users";
    }

}
