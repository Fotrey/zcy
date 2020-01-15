package priv.zcy.dao;

import priv.zcy.pojo.data_mapping.Ums;
import priv.zcy.pojo.data_mapping.Ums_admin;
import priv.zcy.pojo.data_mapping.Ums_member;


public interface UmsMapper {
    Ums_admin adminLogin(Ums ums);

    Ums_member memberLogin(Ums ums);
}
