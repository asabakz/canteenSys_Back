package com.item.web.Comment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.Comment.entity.Comment;
import com.item.web.Comment.entity.CommentParm;
import com.item.web.Comment.service.CommentService;
import com.item.web.user_order_detail.entity.UserOrderDetail;
import com.item.web.user_order_detail.service.UserOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wxapi/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Resource
    private UserOrderDetailService userOrderDetailService;

    // 新增评论
    @PostMapping("/addComment")
    public ResultVo addComment(@RequestBody Comment goodsComment) {
        //根据单号查询商品
        QueryWrapper<UserOrderDetail> query = new QueryWrapper<>();
        query.lambda().eq(UserOrderDetail::getOrderId, goodsComment.getOrderId());
        List<UserOrderDetail> list = userOrderDetailService.list(query);
        List<Comment> CommentList = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Comment temp = new Comment();
                BeanUtils.copyProperties(goodsComment, temp);
                temp.setGoodsId(list.get(i).getGoodsId());
                CommentList.add(temp);
            }
        }
        //批量插入
        commentService.saveBatch(CommentList);
        return ResultUtils.success("评论成功");
    }

    //管理端列表查询
    @GetMapping("/manageCommentList")
    public ResultVo pcCommentList(CommentParm parm){
        IPage<Comment> list = commentService.getList(parm);
        return ResultUtils.success("查询成功", list);
    }

    // 删除
    @DeleteMapping("/{commentId}")
    public ResultVo delete(@PathVariable("commentId") Long commentId){
        commentService.removeById(commentId);
        return ResultUtils.success("删除成功！");
    }



}

