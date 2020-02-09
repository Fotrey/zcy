package pers.zcy.myblogbusiness.service;

import pers.zcy.myblogboot.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
