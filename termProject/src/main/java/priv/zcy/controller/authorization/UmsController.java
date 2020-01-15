package priv.zcy.controller.authorization;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.zcy.biz.UmsBiz;
import priv.zcy.controller.returning_wrapper.PersonalResponseBodyWrapper;
import priv.zcy.pojo.data_mapping.Ums;
import priv.zcy.pojo.data_mapping.Ums_admin;
import priv.zcy.pojo.data_mapping.Ums_member;
import priv.zcy.pojo.data_wrapper_utils.TokenFormat;

import javax.servlet.http.HttpSession;


@RestController
@PersonalResponseBodyWrapper
public class UmsController {
    @Autowired
    private UmsBiz umsBiz;
    @Autowired
    private TokenFormat tokenFormat;

    @PostMapping(value = "/user/login",params = "auth_type")
    public TokenFormat login(@RequestParam("auth_type")String auth_type, @RequestBody Ums ums, HttpSession session){
        String token=RandomStringUtils.random(15,true,true);
        tokenFormat.setToken(token);
        if ("admin".equals(auth_type)){
            Ums_admin login = umsBiz.adminLogin(ums);
            session.setAttribute(token,login);
            return null!=login?tokenFormat:null;


        }else if("member".equals(auth_type)){
            Ums_member login = umsBiz.memberLogin(ums);
            session.setAttribute(token,login);
            return null!=login?tokenFormat:null;
        }
        return null;
    }

    @GetMapping(value = "/user/info",params = "token")
    public Ums_admin adminInfo(@RequestParam("token") String token, HttpSession session){
        Object sessionAttribute = session.getAttribute(token);
        Ums_admin ums_admin = (Ums_admin) sessionAttribute;
        return ums_admin;

    }

    @GetMapping(value = "/member/username/{username}",params = "access_token")
    public Ums_member memberInfo(@PathVariable("username")String username, @RequestParam("access_token")String access_token , HttpSession session){
        Object sessionAttribute = session.getAttribute(access_token);
        return (Ums_member)sessionAttribute;

    }
}
