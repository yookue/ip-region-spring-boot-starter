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

package com.yookue.springstarter.ipregion.property;


import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.yookue.springstarter.ipregion.config.IpRegionAutoConfiguration;
import com.yookue.springstarter.ipregion.enumeration.IpRegionSearchType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Properties for ip2region
 *
 * @author David Hsing
 */
@ConfigurationProperties(prefix = IpRegionAutoConfiguration.PROPERTIES_PREFIX)
@Getter
@Setter
@ToString
public class IpRegionProperties implements Serializable {
    /**
     * Indicates whether to enable this starter or not
     * <p>
     * Default is {@code true}
     */
    private Boolean enabled = true;

    /**
     * Indicates whether to discard lan address or not
     */
    private Boolean discardLan = true;

    /**
     * The region database resource url
     */
    private String regionDb;

    /**
     * Region search type
     * <p>
     * Default is {@code BTREE}
     */
    private IpRegionSearchType searchType = IpRegionSearchType.BTREE;
}
