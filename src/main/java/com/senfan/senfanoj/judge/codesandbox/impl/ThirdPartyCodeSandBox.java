package com.senfan.senfanoj.judge.codesandbox.impl;

import com.senfan.senfanoj.judge.codesandbox.CodeSandBox;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上线程的代码沙箱）
 */
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
