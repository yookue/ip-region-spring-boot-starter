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


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import com.yookue.springstarter.ipregion.structure.IpRegionOutcome;


/**
 * Composer interface for ip2region searcher
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public interface IpRegionResolver {
    @Nonnull
    IpRegionOutcome getRegionOutcome(@Nullable String ipAddress) throws Exception;

    IpRegionOutcome getRegionOutcomeQuietly(@Nullable String ipAddress);

    String getCompositeAddress(@Nullable String ipAddress) throws Exception;

    String getCompositeAddress(@Nullable String ipAddress, char delimiter) throws Exception;

    String getCompositeAddress(@Nullable String ipAddress, @Nullable String delimiter) throws Exception;

    String getCompositeAddressQuietly(@Nullable String ipAddress);

    String getCompositeAddressQuietly(@Nullable String ipAddress, char delimiter);

    String getCompositeAddressQuietly(@Nullable String ipAddress, @Nullable String delimiter);
}
