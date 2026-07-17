package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAuthController {

    @Autowired
   private UserAuthService userAuthService;



}
