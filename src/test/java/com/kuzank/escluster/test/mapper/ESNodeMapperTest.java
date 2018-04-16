package com.kuzank.escluster.test.mapper;

import com.kuzank.escluster.mapper.ESNodeMapper;
import com.kuzank.escluster.mapper.entity.ESNodeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author kuzan
 * @since 2018-01-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESNodeMapperTest {

    @Autowired
    private ESNodeMapper esNodeMapper;

    @Test
    @Rollback
    public void insert() {

        ESNodeEntity node = new ESNodeEntity();

        node.setBeloneAppId("0");
        node.setCreatedBy(1);
        node.setDeleted(Boolean.FALSE.toString());

        node.setClusterName("elasticsearch");
        node.setNodeName("cluster");
        node.setMaster(String.valueOf(true));
        node.setData(String.valueOf(true));
        node.setDataDir(String.valueOf(true));
        node.setHost("119.23.224.26");
        node.setHttpPort("9999");
        node.setTcpPort("9900");
        node.setHttpEnabled("yes");
        node.setUnicastHost("[]");

        int result = esNodeMapper.insert(node);

        System.out.println("result : " + result);
    }

    @Test
    @Rollback
    public void findByBeloneAppIdAndNodeName() {

        ESNodeEntity esNodeEntity = esNodeMapper.findByBeloneAppIdAndNodeName("0", "cluster");
        System.out.println(esNodeEntity);
    }


    public void display(List<ESNodeEntity> esNodes) {

        System.out.println("size : " + String.valueOf(esNodes == null ? 0 : esNodes.size()));

        if (esNodes != null && esNodes.size() > 0) {
            for (ESNodeEntity node : esNodes)
                System.out.println(node);
        }
    }

}
