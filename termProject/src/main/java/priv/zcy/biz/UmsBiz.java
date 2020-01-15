package priv.zcy.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.zcy.dao.UmsMapper;
import priv.zcy.pojo.data_mapping.Ums;
import priv.zcy.pojo.data_mapping.Ums_admin;
import priv.zcy.pojo.data_mapping.Ums_member;

@Service
public class UmsBiz {
    @Autowired
    private UmsMapper umsMapper;

    public Ums_admin adminLogin(Ums ums) {
        return umsMapper.adminLogin(ums);
    }

    public Ums_member memberLogin(Ums ums){
        return umsMapper.memberLogin(ums);
    }
}
