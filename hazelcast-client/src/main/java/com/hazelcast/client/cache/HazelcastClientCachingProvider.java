/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.cache;

import com.hazelcast.cache.impl.HazelcastAbstractCachingProvider;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;

import javax.cache.CacheManager;
import java.net.URI;
import java.util.Properties;

public final class HazelcastClientCachingProvider
        extends HazelcastAbstractCachingProvider {

    public HazelcastClientCachingProvider() {
        super();
    }

    @Override
    protected HazelcastInstance initHazelcast() {
        ClientConfig config = new XmlClientConfigBuilder().build();
        return HazelcastClient.newHazelcastClient(config);
    }

    @Override
    protected CacheManager getHazelcastCacheManager(URI uri, ClassLoader classLoader, Properties managerProperties) {
        return new HazelcastClientCacheManager(this, getHazelcastInstance(), uri, classLoader, managerProperties);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HazelcastClientCachingProvider{");
        sb.append("hazelcastInstance=").append(getHazelcastInstance());
        sb.append('}');
        return sb.toString();
    }
}
