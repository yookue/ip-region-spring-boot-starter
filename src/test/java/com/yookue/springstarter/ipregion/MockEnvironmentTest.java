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

package com.yookue.springstarter.ipregion;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import com.yookue.commonplexus.javaseutil.util.StackTraceWraps;
import com.yookue.springstarter.ipregion.composer.IpRegionResolver;
import com.yookue.springstarter.ipregion.config.IpRegionAutoConfiguration;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest(classes = TestApplicationRunner.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
class MockEnvironmentTest {
    @Autowired
    @Qualifier(value = IpRegionAutoConfiguration.REGION_RESOLVER)
    private IpRegionResolver regionSearcher;

    private static final String IP_ADDRESS = "61.158.208.225";    // $NON-NLS-1$

    @Test
    void compositeAddress() throws Exception {
        String methodName = StackTraceWraps.getExecutingMethodName();
        Assertions.assertNotNull(regionSearcher, "Region searcher can not be null");
        log.info("{}: IP {} address is {}", methodName, IP_ADDRESS, regionSearcher.getCompositeAddress(IP_ADDRESS));
    }
}
