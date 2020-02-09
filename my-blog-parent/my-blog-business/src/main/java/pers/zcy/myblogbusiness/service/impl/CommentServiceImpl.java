package pers.zcy.myblogbusiness.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zcy.myblogboot.entity.Comment;
import pers.zcy.myblogboot.persist.CommentRepository;
import pers.zcy.myblogbusiness.service.CommentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return rootComments(comments);
    }

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        comment.setParentComment(-1 != parentCommentId?commentRepository.getOne(parentCommentId):null);
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    //先定义一个存放迭代找出的所有子节点的容器
    private List<Comment> allChildren = new ArrayList<>();

    //将查出的数据拷贝 ,为了防止对数据库数据的修改
    private List<Comment> rootComments(List<Comment> comments) {

        List<Comment> roots = comments.stream().map(comment -> {
            Comment copy = new Comment();
            BeanUtils.copyProperties(comment, copy);
            return copy;
        }).collect(Collectors.toList());
        directChildren(roots);
        return roots;
    }

    //找出顶级节点下第一层并加入容器
    private void directChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //迭代
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(allChildren);
            //刷新临时存放区
            allChildren = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        allChildren.add(comment);//根节点添加到容器
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                allChildren.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
