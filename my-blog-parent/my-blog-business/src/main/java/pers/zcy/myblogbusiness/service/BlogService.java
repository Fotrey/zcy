package pers.zcy.myblogbusiness.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.zcy.myblogboot.entity.Blog;
import pers.zcy.myblogboot.vo.BlogQuery;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(int size);

    Blog getBlogAndConvert(Long id);

    Page<Blog> listBlog(String query, Pageable pageable);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
