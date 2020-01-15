package priv.zcy.pojo.data_mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ums_admin extends Ums{
    @JsonProperty("name")
    private long id;

//    private String username;
//
//    private String password;
    @JsonProperty("avatar")
    private String icon;

    private String email;
    @JsonProperty("nickName")
    private String nick_name;

    private String note;

    private Timestamp create_time;

    private Timestamp login_time;

    private int status;

    private String[] roles={"admin"};
}
