/*
 * Copyright (c) 2020 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yookue.springstarter.ipregion.config;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.BooleanUtils;
import org.nutz.plugins.ip2region.DbConfig;
import org.nutz.plugins.ip2region.DbSearcher;
import org.nutz.plugins.ip2region.impl.ByteArrayDBReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import com.yookue.commonplexus.springutil.util.ResourceUtilsWraps;
import com.yookue.springstarter.ipregion.composer.IpRegionResolver;
import com.yookue.springstarter.ipregion.composer.impl.DefaultIpRegionResolver;
import com.yookue.springstarter.ipregion.property.IpRegionProperties;


/**
 * Configuration for ip2region
 *
 * @author David Hsing
 * @reference "https://github.com/hiwepy/ip2region-spring-boot-starter"
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = IpRegionAutoConfiguration.PROPERTIES_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(value = IpRegionProperties.class)
@SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
public class IpRegionAutoConfiguration {
    public static final String PROPERTIES_PREFIX = "spring.ip-region";    // $NON-NLS-1$
    public static final String REGION_DB_SEARCHER = "ipRegionDbSearcher";    // $NON-NLS-1$
    public static final String IP_REGION_RESOLVER = "ipRegionResolver";    // $NON-NLS-1$

    @Bean(name = REGION_DB_SEARCHER)
    @ConditionalOnMissingBean(name = REGION_DB_SEARCHER)
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "region-db")
    public DbSearcher regionDbSearcher(@Nonnull IpRegionProperties properties) throws IOException {
        Resource resource = ResourceUtilsWraps.determineResource(properties.getRegionDb());
        if (resource == null || !resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("Region database is not exists or readable");
        }
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            FileCopyUtils.copy(resource.getInputStream(), output);
            return new DbSearcher(new DbConfig(), new ByteArrayDBReader(output.toByteArray()));
        } catch (Exception ignored) {
        }
        return null;
    }

    @Bean(name = IP_REGION_RESOLVER)
    @ConditionalOnMissingBean(name = IP_REGION_RESOLVER)
    @ConditionalOnBean(name = REGION_DB_SEARCHER)
    public IpRegionResolver regionResolver(@Qualifier(value = REGION_DB_SEARCHER) @Nonnull DbSearcher searcher, @Nonnull IpRegionProperties properties) {
        return new DefaultIpRegionResolver(searcher, properties.getSearchType(), BooleanUtils.isTrue(properties.getDiscardLan()));
    }
}