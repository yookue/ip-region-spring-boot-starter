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

package com.yookue.springstarter.ipregion.composer.impl;


import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.plugins.ip2region.DataBlock;
import org.nutz.plugins.ip2region.DbSearcher;
import org.springframework.beans.factory.DisposableBean;
import com.yookue.commonplexus.javaseutil.util.InetAddressWraps;
import com.yookue.springstarter.ipregion.composer.IpRegionResolver;
import com.yookue.springstarter.ipregion.enumeration.IpRegionSearchType;
import com.yookue.springstarter.ipregion.structure.IpRegionOutcome;
import lombok.AllArgsConstructor;


/**
 * Composer implementation for ip2region searcher
 *
 * @author David Hsing
 * @see "org.lionsoul.ip2region.Ip2regionSearcher"
 */
@AllArgsConstructor
@SuppressWarnings("unused")
public class DefaultIpRegionResolver implements IpRegionResolver, DisposableBean {
    private DbSearcher searcher;
    private IpRegionSearchType searchType;
    private boolean discardLan;

    @Nonnull
    @Override
    public IpRegionOutcome getRegionOutcome(@Nullable String ipAddress) throws IOException {
        return getRegionOutcome(ipAddress, searchType);
    }

    @Nonnull
    @Override
    public IpRegionOutcome getRegionOutcome(@Nullable String ipAddress, @Nullable IpRegionSearchType type) throws IOException {
        if (StringUtils.isBlank(ipAddress) || (discardLan && InetAddressWraps.isLanAddress(ipAddress))) {
            return new IpRegionOutcome();
        }
        type = ObjectUtils.defaultIfNull(type, IpRegionSearchType.BTREE);
        DataBlock dataBlock;
        switch (type) {
            case BINARY:
                dataBlock = searcher.binarySearch(ipAddress);
                break;
            case MEMORY:
                dataBlock = searcher.memorySearch(ipAddress);
                break;
            default:
                dataBlock = searcher.btreeSearch(ipAddress);
                break;
        }
        return (dataBlock == null) ? new IpRegionOutcome() : new IpRegionOutcome(dataBlock.getRegion());
    }

    @Override
    public IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress) {
        return getRegionOutcomeQuietly(ipAddress, searchType);
    }

    @Override
    public IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type) {
        try {
            return getRegionOutcome(ipAddress, type);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress) throws IOException {
        return getRegionOutcome(ipAddress).getCompositeAddress();
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, char delimiter) throws IOException {
        return getRegionOutcome(ipAddress).getCompositeAddress(delimiter);
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, @Nullable String delimiter) throws IOException {
        return getRegionOutcome(ipAddress).getCompositeAddress(delimiter);
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type) throws IOException {
        return getRegionOutcome(ipAddress, type).getCompositeAddress();
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type, char delimiter) throws IOException {
        return getRegionOutcome(ipAddress, type).getCompositeAddress(delimiter);
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type, @Nullable String delimiter) throws IOException {
        return getRegionOutcome(ipAddress, type).getCompositeAddress(delimiter);
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress) {
        try {
            return getCompositeAddress(ipAddress);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress, char delimiter) {
        try {
            return getCompositeAddress(ipAddress, delimiter);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable String delimiter) {
        try {
            return getCompositeAddress(ipAddress, delimiter);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type) {
        try {
            return getCompositeAddress(ipAddress, type);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type, char delimiter) {
        try {
            return getCompositeAddress(ipAddress, type, delimiter);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type, @Nullable String delimiter) {
        try {
            return getCompositeAddress(ipAddress, type, delimiter);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public void destroy() throws Exception {
        searcher.close();
    }
}
