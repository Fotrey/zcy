package pers.zcy.myblogbusiness.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zcy.myblogboot.entity.Blog;
import pers.zcy.myblogboot.entity.Type;
import pers.zcy.myblogboot.exception.NoSuchDataException;
import pers.zcy.myblogboot.persist.BlogRepository;
import pers.zcy.myblogboot.utils.BlogSearchConditionMatchBuilder;
import pers.zcy.myblogboot.utils.BeanFieldJudgeUtils;
import pers.zcy.myblogboot.vo.BlogQuery;
import pers.zcy.myblogbusiness.service.BlogService;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;


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
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery)
    {

        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new BlogSearchConditionMatchBuilder.Builder()
                    .titleFuzzyMatch(!"".equals(blogQuery.getTitle()) && null != blogQuery.getTitle(), criteriaBuilder.like(root.<String>get("title"), "%" + blogQuery.getTitle() + "%"))
                    .typeMatch(null != blogQuery.getId(), criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getId()))
                    .isRecommendMatch(blogQuery.isRecommend(), criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()))
                    .conditionsDone();
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
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
}
