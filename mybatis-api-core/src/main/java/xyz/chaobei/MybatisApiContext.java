package xyz.chaobei;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class MybatisApiContext {

    private final Logger logger = LoggerFactory.getLogger(MybatisApiContext.class);

    private final SqlSession sqlSession;
    private final Configuration configuration;
    private final Collection<MappedStatement> mappedStatements;

    public MybatisApiContext(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.configuration = sqlSession.getConfiguration();
        this.mappedStatements = this.configuration.getMappedStatements();
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Collection<MappedStatement> getMappedStatements() {
        return mappedStatements;
    }



}
