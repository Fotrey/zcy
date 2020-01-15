package priv.zcy.pojo.data_mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ums_member extends Ums{
    private long id;
    private long member_level_id;

//    private String username;
//    private String password;

    private String nickname;
    private String phone;
    private int status;
    private Timestamp create_time;
    private String icon;
    private int gender;
    private Date birthday;
    private String city;
    private String job;
    private String personalized_signature;
    private int source_type;
    private int integration;
    private int growth;
    private int luckey_count;
    private int history_intergration;
}
