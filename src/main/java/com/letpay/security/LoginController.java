package com.letpay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLoginPage() {
    return new ModelAndView("login");
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView login(@RequestParam String username, @RequestParam String password) {
    System.out.println(username + " " +password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      return new ModelAndView("login", "error", "Invalid username or password");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    String token = jwtTokenUtil.generateToken(userDetails);

    return new ModelAndView("redirect:/").addObject("token", token);
  }
}
