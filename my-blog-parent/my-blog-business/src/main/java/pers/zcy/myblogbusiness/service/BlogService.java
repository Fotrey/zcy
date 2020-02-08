package pers.zcy.myblogbusiness.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.zcy.myblogboot.entity.Blog;
import pers.zcy.myblogboot.vo.BlogQuery;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
