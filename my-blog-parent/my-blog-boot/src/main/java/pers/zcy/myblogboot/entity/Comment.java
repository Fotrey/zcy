package pers.zcy.myblogboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "zcy_comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
}
