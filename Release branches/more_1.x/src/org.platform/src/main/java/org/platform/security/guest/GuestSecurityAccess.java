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
package org.platform.security.guest;
import java.util.ArrayList;
import java.util.List;
import org.more.util.StringUtil;
import org.platform.context.AppContext;
import org.platform.security.ISecurityAccess;
import org.platform.security.Permission;
import org.platform.security.SecurityAccess;
import org.platform.security.SecuritySettings;
import org.platform.security.UserInfo;
/**
 * 
 * @version : 2013-4-28
 * @author ������ (zyc@byshell.org)
 */
@SecurityAccess(authSystem = "GuestAuthSystem")
public class GuestSecurityAccess implements ISecurityAccess {
    private SecuritySettings settings = null;
    //
    @Override
    public void initAccess(AppContext appContext) {
        this.settings = appContext.getBean(SecuritySettings.class);
    }
    @Override
    public List<Permission> loadPermission(UserInfo userInfo) {
        String[] perList = this.settings.getGuestPermissions();
        List<Permission> perArray = new ArrayList<Permission>();
        if (perList != null)
            for (String per : perList)
                if (StringUtil.isBlank(per) == false)
                    perArray.add(new Permission(per));
        return perArray;
    }
    @Override
    public void destroyAccess(AppContext appContext) {}
}