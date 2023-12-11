package com.senfan.senfanoj.judge.strategy;

import com.senfan.senfanoj.judge.codesandbox.model.JudgeInfo;
import com.senfan.senfanoj.model.dto.question.JudgeCase;
import com.senfan.senfanoj.model.entity.Question;
import com.senfan.senfanoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}