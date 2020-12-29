package com.wsk.study.commonsclass;

/**
 * @description: 补全字符串中的占位
 * @author: wsk
 * @date: 2020/12/10 16:28
 * @version: 1.0
 */
public class StringTest {

    public static void main(String[] args) {
        String dbName = "xqy_portal_nifitest";
        String collectPartitionStat = "select tmp1.TBL_NAME,tmp1.numRows,tmp2.total_size,tmp3.transient_lastDdlTime from (select sum(a.PARAM_VALUE) as numRows,d.NAME,t.TBL_NAME,t.TBL_ID,p.PART_ID,p.PART_NAME,p.CREATE_TIME,a.PARAM_VALUE,a.PARAM_KEY from TBLS t left join DBS d on t.DB_ID = d.DB_ID left join PARTITIONS p on t.TBL_ID = p.TBL_ID left join PARTITION_PARAMS a on p.PART_ID=a.PART_ID where d.NAME='%s' and a.PARAM_KEY ='numRows' group by t.TBL_NAME)tmp1, (select sum(a.PARAM_VALUE)as total_size,d.NAME,t.TBL_NAME,t.TBL_ID,p.PART_ID,p.PART_NAME,p.CREATE_TIME,a.PARAM_VALUE,a.PARAM_KEY from TBLS t left join DBS d on t.DB_ID = d.DB_ID left join PARTITIONS p on t.TBL_ID = p.TBL_ID left join PARTITION_PARAMS a on p.PART_ID=a.PART_ID where d.NAME='%s' and a.PARAM_KEY ='totalSize ' group by t.TBL_NAME)tmp2, (select max(a.PARAM_VALUE) as transient_lastDdlTime,d.NAME,t.TBL_NAME,t.TBL_ID,p.PART_ID,p.PART_NAME,p.CREATE_TIME,a.PARAM_VALUE,a.PARAM_KEY from TBLS t left join DBS d on t.DB_ID = d.DB_ID left join PARTITIONS p on t.TBL_ID = p.TBL_ID left join PARTITION_PARAMS a on p.PART_ID=a.PART_ID where d.NAME='%s' and a.PARAM_KEY ='transient_lastDdlTime ' group by t.TBL_NAME)tmp3 where tmp1.tbl_id=tmp2.tbl_id and tmp2.tbl_id=tmp3.tbl_id";
        System.out.println(String.format(collectPartitionStat, dbName, dbName, dbName));
    }
}
