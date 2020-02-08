package pers.zcy.myblogboot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.zcy.myblogboot.entity.Type;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
