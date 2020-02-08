package pers.zcy.myblogboot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.zcy.myblogboot.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
