package pers.zcy.myblogboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "zcy_user")
public class User implements Serializable {
    private static final long serialVersionUID = 17746722667L;

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private Set<Blog> blogs = new HashSet<>();


}
