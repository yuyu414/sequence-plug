package com.github.sequence.segment.dao;

import com.github.sequence.segment.model.Sequence;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

public class SequenceDaoImpl implements SequenceDao {

    SqlSessionFactory sqlSessionFactory;

    public SequenceDaoImpl(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(SequenceMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Override
    public List<Sequence> getAllSequence() {
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            return sqlSession.getMapper(SequenceMapper.class).getAllSequence();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Sequence updateMaxIdAndGetSequence(String tag) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SequenceMapper mapper = sqlSession.getMapper(SequenceMapper.class);
            mapper.updateMaxId(tag);
            Sequence result = mapper.getSequence(tag);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Sequence updateMaxIdByCustomStepAndGetSequence(Sequence sequence) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SequenceMapper mapper = sqlSession.getMapper(SequenceMapper.class);
            mapper.updateMaxIdByCustomStep(sequence);
            Sequence result = mapper.getSequence(sequence.getKey());
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<String> getAllTags() {
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            return sqlSession.getMapper(SequenceMapper.class).getAllTags();
        } finally {
            sqlSession.close();
        }
    }
}
