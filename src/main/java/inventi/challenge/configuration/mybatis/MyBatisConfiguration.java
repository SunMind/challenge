package inventi.challenge.configuration.mybatis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@MapperScan({"inventi.challenge.persistence.repository"})
public class MyBatisConfiguration {

    MybatisAutoConfiguration mybatisAutoConfiguration;

    @Bean
    @Primary
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactory target = mybatisAutoConfiguration.sqlSessionFactory(dataSource);
        return new MyBatisSqlSessionFactory(target);
    }
}
