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
package org.platform.icache;
import java.util.ArrayList;
import org.platform.Platform;
import org.platform.context.AppContext;
import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
/**
 * 缓存使用入口，缓存的实现由系统自行提供。
 * @version : 2013-4-20
 * @author 赵永春 (zyc@byshell.org)
 */
class ManagedKeyBuilderManager {
    private KeyBuilderDefinition[] keyBuilderDefinitionSet = null;
    //
    public void initManager(AppContext appContext) {
        Platform.info("init ManagedKeyBuilderManager...");
        this.keyBuilderDefinitionSet = collectKeyBuilderDefinitionSet(appContext.getGuice());
        for (KeyBuilderDefinition keyBuilderDefinition : keyBuilderDefinitionSet) {
            keyBuilderDefinition.initKeyBuilder(appContext);
        }
    }
    private KeyBuilderDefinition[] collectKeyBuilderDefinitionSet(Injector injector) {
        ArrayList<KeyBuilderDefinition> keyBuilderDefinitionList = new ArrayList<KeyBuilderDefinition>();
        TypeLiteral<KeyBuilderDefinition> KEYBUILDER_DEFS = TypeLiteral.get(KeyBuilderDefinition.class);
        for (Binding<KeyBuilderDefinition> entry : injector.findBindingsByType(KEYBUILDER_DEFS)) {
            KeyBuilderDefinition define = entry.getProvider().get();
            keyBuilderDefinitionList.add(define);
        }
        // Convert to a fixed size array for speed.
        return keyBuilderDefinitionList.toArray(new KeyBuilderDefinition[keyBuilderDefinitionList.size()]);
    }
    public void destroyManager(AppContext appContext) {
        Platform.info("destroy ManagedKeyBuilderManager...");
        for (KeyBuilderDefinition cacheDefinition : keyBuilderDefinitionSet) {
            cacheDefinition.destroy(appContext);
        }
    }
    public IKeyBuilder getKeyBuilder(Class<?> sampleType, AppContext appContext) {
        for (KeyBuilderDefinition def : this.keyBuilderDefinitionSet) {
            if (def.canSupport(sampleType) == true) {
                return appContext.getGuice().getInstance(def.getKeyBuilderKey());
            }
        }
        return null;
    }
}