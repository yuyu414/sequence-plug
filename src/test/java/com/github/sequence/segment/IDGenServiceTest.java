package com.github.sequence.segment;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.sequence.IDGen;
import com.github.sequence.common.PropertyFactory;
import com.github.sequence.common.Result;
import com.github.sequence.segment.dao.SequenceDao;
import com.github.sequence.segment.dao.SequenceDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class IDGenServiceTest {
    IDGen idGen;
    DruidDataSource dataSource;
    @Before
    public void before() throws IOException, SQLException {
        // Load Db Config
        Properties properties = PropertyFactory.getProperties();

        // Config dataSource
        dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.init();

        // Config Dao
        SequenceDao dao = new SequenceDaoImpl(dataSource);

        // Config ID Gen
        idGen = new SegmentIDGenImpl();
        ((SegmentIDGenImpl) idGen).setDao(dao);
        idGen.init();
    }
    @Test
    public void testGetId() {
        for (int i = 0; i < 1000; ++i) {
            Result r = idGen.get("segment-test");
            System.out.println(r);
        }
    }
    @After
    public void after() {
       dataSource.close();
    }

}
