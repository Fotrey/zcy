package pers.zcy.myblogboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "zcy_type")
public class Type implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "分类名称不允许为空！")
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Blog> blogs = new HashSet<>();
}
