package com.senfan.senfanoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senfan.senfanoj.exception.ThrowUtils;
import com.senfan.senfanoj.model.dto.question.QuestionQueryRequest;
import com.senfan.senfanoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.senfan.senfanoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.senfan.senfanoj.model.entity.Question;
import com.senfan.senfanoj.model.entity.QuestionSubmit;
import com.senfan.senfanoj.model.vo.QuestionSubmitVO;
import com.senfan.senfanoj.model.vo.QuestionVO;
import com.senfan.senfanoj.service.QuestionSubmitService;
import com.senfan.senfanoj.common.BaseResponse;
import com.senfan.senfanoj.common.ErrorCode;
import com.senfan.senfanoj.common.ResultUtils;
import com.senfan.senfanoj.exception.BusinessException;
import com.senfan.senfanoj.model.entity.User;
import com.senfan.senfanoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取当题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionQueryRequest,
                                                                         HttpServletRequest request) {
        if (questionQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = questionQueryRequest.getCurrent();
        long pageSize = questionQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, pageSize),
                questionSubmitService.getQueryWrapper(questionQueryRequest));
        // 返回脱敏信息
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }

}
