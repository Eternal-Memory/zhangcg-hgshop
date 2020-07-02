package com.zcg.hgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcg.hgshop.common.MsgData;
import com.zcg.hgshop.domain.Cart;
import com.zcg.hgshop.domain.User;
import com.zcg.hgshop.service.CartService;
import com.zcg.hgshop.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Reference
	UserService userService;
	@Reference
	CartService cartService;
	
	
	/**
	 * 去登录
	 * @return
	 */
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	@PostMapping("login")
	public String login(HttpSession session,User user) {
		User u = userService.login(user);
		if(u==null) {
			return "user/login";
		}
		session.setAttribute("user", u);
		return "redirect:home";
	}
	

	/**
	 * 去注册
	 * @return
	 */
	@GetMapping("register")
	public String register() {
		return "user/register";
	}
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@RequestMapping("insert")
	public String insert(User user) {
		User u = userService.register(user);
		if(u!=null) {
			return "redirect:login";
		}
		return "user/register";
	}
	
	/**
	 * 去主页面
	 * @return
	 */
	@RequestMapping("home")
	public String home() {
		return "user/home";
	}
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping("checkExist")
	@ResponseBody
	public boolean checkExist(String username) {
		return null==userService.getUserByName(username);	
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param cart
	 * @return
	 */
	@RequestMapping("insertCart")
	@ResponseBody
	public MsgData insertCart(HttpServletRequest request,Cart cart) {
		User loginUser = (User) request.getSession().getAttribute("user");
		if(loginUser==null) {
			return new MsgData(1, "对不起，你尚未登录");
		}
		cart.setUid(loginUser.getUid());
		int i = cartService.insert(cart);
		return i>0?new MsgData("保存成功"):new MsgData(2,"加入失败，请稍后再试");
	}
	
	/**
	 * 进入购物车列表页面
	 * @return
	 */
	@RequestMapping("cartList")
	public String carList(HttpServletRequest request ) {
		User loginUser = (User) request.getSession().getAttribute("user");
		//购物车列表
		List<Cart> list = cartService.selects(loginUser.getUid());
		request.setAttribute("list", list);
		return "user/cartlist";
	}
	/**
	 * 删除购物车
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteCart")
	@ResponseBody
	public int deleteCart(int[] ids) {
		int i = cartService.delete(ids);
		return i;
	}
}
