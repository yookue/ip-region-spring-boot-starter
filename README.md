# IP Region Spring Boot Starter

Spring Boot application integrates `IP2Region` quickly, to provide abilities to resolve IP address to region.

## Quickstart

- Import dependencies

```xml
    <dependency>
        <groupId>com.yookue.springstarter</groupId>
        <artifactId>ip-region-spring-boot-starter</artifactId>
        <version>LATEST</version>
    </dependency>
```

> By default, this starter will auto take effect, you can turn it off by `spring.ip-region.enabled = false`

- Configure Spring Boot `application.yml` with prefix `spring.ip-region`

```yml
spring:
    ip-region:
        discard-lan: true
        region-db: 'classpath:/ipaddr/Ip2Region_20220622/ip2region.db'
```

- Configure your beans with a `IpRegionResolver` bean by constructor or `@Autowired`/`@Resource` annotation, then you can resolve locations with it as following:

| Method Return | Method Name         |
|---------------|---------------------|
| String        | getCompositeAddress |
| String        | getRegionOutcome    |

## Document

- Github: https://github.com/yookue/ip-region-spring-boot-starter
- Ip2region github: https://github.com/lionsoul2014/ip2region
- Ip2region database v1: https://github.com/lionsoul2014/ip2region/tree/master/v1.0/data/ip2region.db

## Requirement

- jdk 1.8+

## License

This project is under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

See the `NOTICE.txt` file for required notices and attributions.

## Donation

You like this package? Then [donate to Yookue](https://yookue.com/public/donate) to support the development.

## Website

- Yookue: https://yookue.com
