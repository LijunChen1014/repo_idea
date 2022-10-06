package com.chen.controller;

import com.chen.domain.ResponseResult;
import com.chen.domain.Role;
import com.chen.domain.User;
import com.chen.domain.UserVo;
import com.chen.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.JstlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo userByPage = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页多条件查询成功",userByPage);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserUpdate(id,status);
        return new ResponseResult(true,200,"用户状态修改成功",status);
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1!=null){
            //保存ID及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id", user1.getId());

            //将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            return new  ResponseResult(true,200,"登录成功",map);
        }else {

            return new  ResponseResult(true,400,"用户名或密码错误",null);

        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"查询用户关联的角色信息成功",userRelationRoleById);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //获取session
        Object session_token = (String)request.getSession().getAttribute("access_token");

        //判断token是否一致
        if (header_token.equals(session_token)){
            Object user_id = (Integer)request.getSession().getAttribute("user_id");

            return userService.getUserPermissions((Integer) user_id);
        }else {
            return new ResponseResult(false,400,"获取用户权限信息失败",null);
        }


    }

}


