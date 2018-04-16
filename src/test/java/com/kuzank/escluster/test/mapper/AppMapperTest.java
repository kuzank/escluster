package com.kuzank.escluster.test.mapper;

import com.kuzank.escluster.mapper.AppMapper;
import com.kuzank.escluster.mapper.entity.AppEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppMapperTest {

    @Autowired
    private AppMapper appMapper;

    @Test
    @Rollback
    public void insert() throws Exception {

        AppEntity appEntity = new AppEntity();

        appEntity.setDeleted(Boolean.FALSE.toString());
        appEntity.setClusterName("app one");
        appEntity.setCreatedBy(0);
        appEntity.setDescription("app test");

        if (appEntity.getClusterName() != null && appEntity.getClusterName().indexOf(" ") <= 0) {
            if (appMapper.findByClusterName(appEntity.getClusterName()) != null) {
                int result = appMapper.insert(appEntity);
                System.out.println("result : " + result);
            } else {
                throw new Exception("ClusterName Exist");
            }
        } else {
            throw new Exception("ClusterName Did't Meet The Requirements");
        }
    }


    @Test
    @Rollback
    public void findByClusterName() {

        AppEntity appEntity = appMapper.findByClusterName("app one");
        System.out.println("result : " + appEntity);
    }

}
