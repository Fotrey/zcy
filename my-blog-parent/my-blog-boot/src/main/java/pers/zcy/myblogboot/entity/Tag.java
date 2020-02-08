package pers.zcy.myblogboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "zcy_tag")
public class Tag implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Blog> blogs = new HashSet<>();
}
