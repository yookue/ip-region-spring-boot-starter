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

package com.yookue.springstarter.ipregion.structure;


import javax.annotation.Nullable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import com.yookue.commonplexus.javaseutil.constant.CharVariantConst;
import com.yookue.springstarter.ipregion.enumeration.IpRegionSegmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Structure for outcome of ip2region
 *
 * @author David Hsing
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@SuppressWarnings("unused")
public class IpRegionOutcome {
    private String country;
    private String area;
    private String province;
    private String city;
    private String isp;

    public IpRegionOutcome(@Nullable String composite) {
        if (StringUtils.isBlank(composite)) {
            return;
        }
        String[] outcomes = StringUtils.split(StringUtils.trimToNull(composite), CharVariantConst.VERTICAL);
        if (ArrayUtils.isEmpty(outcomes)) {
            return;
        }
        country = ArrayUtils.get(outcomes, IpRegionSegmentType.COUNTRY.getValue());
        area = ArrayUtils.get(outcomes, IpRegionSegmentType.AREA.getValue());
        province = ArrayUtils.get(outcomes, IpRegionSegmentType.PROVINCE.getValue());
        city = ArrayUtils.get(outcomes, IpRegionSegmentType.CITY.getValue());
        isp = ArrayUtils.get(outcomes, IpRegionSegmentType.ISP.getValue());
    }

    public String getCompositeAddress() {
        return getCompositeAddress(CharVariantConst.SLASH);
    }

    public String getCompositeAddress(char delimiter) {
        return getCompositeAddress(CharUtils.toString(delimiter));
    }

    public String getCompositeAddress(@Nullable String delimiter) {
        StringBuilder builder = new StringBuilder();
        if (isValidField(country)) {
            builder.append(country);
        }
        if (isValidField(province)) {
            if (builder.length() > 0) {
                builder.append(StringUtils.defaultString(delimiter));
            }
            builder.append(province);
        }
        if (isValidField(city)) {
            if (builder.length() > 0) {
                builder.append(StringUtils.defaultString(delimiter));
            }
            builder.append(city);
        }
        return (builder.length() > 0) ? builder.toString() : null;
    }

    private boolean isValidField(@Nullable String field) {
        return StringUtils.isNotBlank(field) && !StringUtils.equals(field, "0");    // $NON-NLS-1$
    }
}
