/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.platform.security;
import org.platform.context.AppContext;
/**
 * 负责权限系统的认证工作，认证模块在系统中可以存在多份。这些模块会按照顺序排成一个链（认证模块链）
 * @version : 2013-3-12
 * @author 赵永春 (zyc@byshell.org)
 */
public interface SecurityAuth {
    /**初始化授权模块。*/
    public void initAuth(AppContext appContext);
    /**通过userCode登陆系统*/
    public UserInfo getUserInfo(String userCode);
    /**通过帐号密码登陆系统*/
    public UserInfo getUserInfo(String account, String password);
    /**销毁授权模块。*/
    public void destroyAuth(AppContext appContext);
}