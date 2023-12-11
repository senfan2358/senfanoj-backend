package com.senfan.senfanoj.judge;

import com.senfan.senfanoj.judge.strategy.DefaultJudgeStrategy;
import com.senfan.senfanoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.senfan.senfanoj.judge.strategy.JudgeContext;
import com.senfan.senfanoj.judge.strategy.JudgeStrategy;
import com.senfan.senfanoj.judge.codesandbox.model.JudgeInfo;
import com.senfan.senfanoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
