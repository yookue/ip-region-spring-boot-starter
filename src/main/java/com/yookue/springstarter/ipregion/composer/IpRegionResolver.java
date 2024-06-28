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

package com.yookue.springstarter.ipregion.composer;


import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.yookue.springstarter.ipregion.enumeration.IpRegionSearchType;
import com.yookue.springstarter.ipregion.structure.IpRegionOutcome;


/**
 * Composer interface for ip2region searcher
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public interface IpRegionResolver {
    @Nonnull
    IpRegionOutcome getRegionOutcome(@Nullable String ipAddress) throws IOException;

    @Nonnull
    IpRegionOutcome getRegionOutcome(@Nullable String ipAddress, @Nullable IpRegionSearchType type) throws IOException;

    IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress);

    IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type);

    String getCompositeAddress(@Nullable String ipAddress) throws IOException;

    String getCompositeAddress(@Nullable String ipAddress, char delimiter) throws IOException;

    String getCompositeAddress(@Nullable String ipAddress, @Nullable String delimiter) throws IOException;

    String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type) throws IOException;

    String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type, char delimiter) throws IOException;

    String getCompositeAddress(@Nullable String ipAddress, @Nullable IpRegionSearchType type, @Nullable String delimiter) throws IOException;

    String getCompositeAddressQuietly(@Nullable String ipAddress);

    String getCompositeAddressQuietly(@Nullable String ipAddress, char delimiter);

    String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable String delimiter);

    String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type);

    String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type, char delimiter);

    String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable IpRegionSearchType type, @Nullable String delimiter);
}
