package kr.kopo.ctc.spring.board2.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *	SqlSessionFactory 설정
 *		- 모든 마이바티스 애플리케이션은 SqlSessionFactory 인스턴스를 사용
 *		- SqlSessionFactory은 sqlSession을 만드는 역할
 *		- 설정 방법은 1) xml를 통한 설정, 2) config 클래스를 이용한 설정 , 2가지 존재. 
 */

@Configuration
@MapperScan(basePackages="kr.kopo.ctc.spring.board2.repository.mapper")
@EnableTransactionManagement
public class MybatisConfig {
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("mapper/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSesisonTemplate(SqlSessionFactory sessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory);
		return sqlSessionTemplate;
	}
}
