package pers.zcy.myblogbusiness.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zcy.myblogboot.entity.Blog;
import pers.zcy.myblogboot.entity.Type;
import pers.zcy.myblogboot.exception.NoSuchDataException;
import pers.zcy.myblogboot.persist.BlogRepository;
import pers.zcy.myblogboot.utils.BlogSearchConditionMatchBuilder;
import pers.zcy.myblogboot.utils.BeanFieldJudgeUtils;
import pers.zcy.myblogboot.utils.MarkDownUtils;
import pers.zcy.myblogboot.vo.BlogQuery;
import pers.zcy.myblogbusiness.service.BlogService;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    @Override
    @Transactional
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery)
    {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new BlogSearchConditionMatchBuilder.Builder()
                    .match(!"".equals(blogQuery.getTitle()) && null != blogQuery.getTitle(), criteriaBuilder.like(root.<String>get("title"), "%" + blogQuery.getTitle() + "%"))
                    .match(null != blogQuery.getTypeId(), criteriaBuilder.equal(root.<Type>get("type").<Long>get("id"), blogQuery.getTypeId()))
                    .match(blogQuery.isRecommend(), criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()))
                    .conditionsDone();
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    @Override
    @Transactional
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            Join tags = root.join("tags");
            return criteriaBuilder.equal(tags.<Long>get("id"),tagId);
        }, pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        if (null == blog.getId()){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog ToBeModified = getBlog(id);
        if (null == ToBeModified){
            throw new NoSuchDataException("没有此博客");
        }
        BeanUtils.copyProperties(blog,ToBeModified, BeanFieldJudgeUtils.getNullPropertyNames(blog));
        ToBeModified.setUpdateTime(new Date());
        return saveBlog(ToBeModified);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Blog> listRecommendBlogTop(int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Blog getBlogAndConvert(Long id) {
        Blog blog = getBlog(id);
        if (null == blog){
            throw new NoSuchDataException("该博客不存在");
        }
        Blog afterCommentConverted = new Blog();
        BeanUtils.copyProperties(blog,afterCommentConverted);
        String content = afterCommentConverted.getContent();
        afterCommentConverted.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return afterCommentConverted;
    }

    @Override
    @Transactional
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.fingByQuery(query, pageable);
    }

    @Override
    @Transactional
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.groupByYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    @Transactional
    public Long countBlog() {
        return blogRepository.count();
    }
}
