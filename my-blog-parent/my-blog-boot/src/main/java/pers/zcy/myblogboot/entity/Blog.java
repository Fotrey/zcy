package pers.zcy.myblogboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.zcy.myblogboot.utils.StringIds2CollectionConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "zcy_blog")
public class Blog implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String description;

    @Transient
    private String tagIds;

    @ManyToOne
    private Type type;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private Set<Comment>  comments = new HashSet<>();

    public void initTagIds(){
        this.tagIds= StringIds2CollectionConverter.recycle(this.tags);
    }

}
