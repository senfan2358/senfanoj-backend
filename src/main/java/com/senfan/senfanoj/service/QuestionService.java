package com.senfan.senfanoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.senfan.senfanoj.common.ErrorCode;
import com.senfan.senfanoj.constant.CommonConstant;
import com.senfan.senfanoj.exception.BusinessException;
import com.senfan.senfanoj.exception.ThrowUtils;
import com.senfan.senfanoj.mapper.QuestionMapper;
import com.senfan.senfanoj.model.dto.question.QuestionQueryRequest;
import com.senfan.senfanoj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.senfan.senfanoj.model.vo.QuestionVO;
import com.senfan.senfanoj.model.vo.UserVO;
import com.senfan.senfanoj.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
