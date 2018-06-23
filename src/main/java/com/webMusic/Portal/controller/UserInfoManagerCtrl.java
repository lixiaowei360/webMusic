package com.webMusic.Portal.controller;

import com.webMusic.Portal.service.SongClassifyManagerServiceImpl;
import com.webMusic.common.model.SongClassify;
import com.webMusic.common.model.UUser;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Scope(value="prototype")
@RequestMapping("userInfoManagerCtrl")
public class UserInfoManagerCtrl {
    @Autowired
    UUserService userService;

    @RequestMapping("saveUserInfoManagerCtrl")
    public ModelAndView saveUserInfoManagerCtrl(Model model,UUser user) throws IOException {
        ResultMessage infoRecordNumber = userService.updateUserInfo(user);
        model.addAttribute("user",user);
        return new ModelAndView("frontDesk/userInfo/userInfo");
    }
}
