package com.github.dragon.controller;

import com.github.dragon.bean.PageInfo;
import com.github.dragon.bean.Role;
import com.github.dragon.bean.User;
import com.github.dragon.service.RoleService;
import com.github.dragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")//接受用户客户端请求
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/login.do")
    public ModelAndView login(User user,HttpSession session){ //session.setAttribute()
        int id=userService.login(user.getUsername(),user.getPassword());

        ModelAndView mv=new ModelAndView();
        //页面跳转
        if (id!=-1){
            List<Integer> roleIds=roleService.findRoleId(id);
            //存储角色信息
            session.setAttribute("roleIds",roleIds);
            session.setAttribute("user",user);
            mv.setViewName("main");
        }else{
            mv.setViewName("../failer");
        }
        return mv;
    }

/*    @RequestMapping("/findAll.do")
    public ModelAndView findAll()
    {
        List<User> users=userService.findAll();
        ModelAndView mv=new ModelAndView();
        //键值对
        mv.addObject("users",users);
        //跳转页面
        mv.setViewName("user-list");
        return  mv;
    }*/

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1")int currentPage, String username,
                                @RequestParam(defaultValue = "0")int type, HttpSession session){
        //实现查询时点击翻页不被被主页覆盖
        if (type==1)//对应user-list第123行
        {
            //赋值
            session.setAttribute("searchName",username);
        }else {
            //取值
            username=(String) session.getAttribute("searchName");
        }
        //显示所有人
        PageInfo<User> pageInfo=userService.findAll(currentPage,username);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/add.do")
    public String add(User user)
    {
        userService.add(user);
        //重新获取页面数据并刷新
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String delete (int id){
        userService.deleteById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/toUpdate.do")
    public ModelAndView toUpdate(int id){
        User user=userService.selectById(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user-update");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/update.do")
    public String update(User user){
        userService.update(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("deleteAll.do")
    public String deleteAll(String userList){
        String[] str=userList.split(",");
        List<Integer> ids=new ArrayList<>();
        for (String s:str){
            ids.add(Integer.parseInt(s));
        }
        userService.deleteAll(ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("toAddRole.do")
    public ModelAndView toAddRole(int id){
        List<Role> roleList=roleService.findRoleByUserId(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("roles",roleList);
        mv.addObject("id",id);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRole.do")
    @ResponseBody
    public String add(String roleList,String userId){
        String[] strs =roleList.split(",");
        List<Integer> ids=new ArrayList<>();
        for (String s:strs) {
            ids.add(Integer.parseInt(s));
        }
        roleService.add(ids,userId);
        return "";
    }
}
