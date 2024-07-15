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


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.DisposableBean;
import com.yookue.commonplexus.javaseutil.util.InetAddressWraps;
import com.yookue.springstarter.ipregion.composer.IpRegionResolver;
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
    private Searcher searcher;
    private boolean discardLan;

    @Nonnull
    @Override
    public IpRegionOutcome getRegionOutcome(@Nullable String ipAddress) throws Exception {
        if (searcher == null || StringUtils.isBlank(ipAddress) || (discardLan && InetAddressWraps.isLanAddress(ipAddress))) {
            return new IpRegionOutcome();
        }
        return IpRegionOutcome.of(searcher.search(ipAddress));
    }

    @Override
    public IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress) {
        try {
            return getRegionOutcome(ipAddress);
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress) throws Exception {
        return getRegionOutcome(ipAddress).getCompositeAddress();
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, char delimiter) throws Exception {
        return getRegionOutcome(ipAddress).getCompositeAddress(delimiter);
    }

    @Override
    public String getCompositeAddress(@Nullable String ipAddress, @Nullable String delimiter) throws Exception {
        return getRegionOutcome(ipAddress).getCompositeAddress(delimiter);
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
    public void destroy() throws Exception {
        searcher.close();
    }
}
