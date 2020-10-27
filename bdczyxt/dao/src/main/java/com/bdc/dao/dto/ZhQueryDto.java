package com.bdc.dao.dto;

import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年11月05日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：ZhQueryDto
 * @Description 功能说明：综合查询
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年11月05日
 */
@Data
public class ZhQueryDto {
    /**
     * 标识码
     */
    private String bsm;
    /**
     * 查询ID
     */
    private String queryid;
    /**
     * 业务号
     */
    private String ywh;
    /**
     * 登记原因
     */
    private String djyy;
    /**
     * 登记类型
     */
    private String djlx;
    /**
     * 登记机构
     */
    private String djjg;
    /**
     * 登簿时间
     */
    private String dbsj;
    /**
     * 注销时间
     */
    private String zxsj;
    /**
     * 注销原因
     */
    private String zxyy;
    /**
     * 权利人
     */
    private String qlr;
    /**
     * 权利人证件号
     */
    private String qlrzjh;
    /**
     * 权利人证件种类
     */
    private String qlrzjzl;
    /**
     * 义务人
     */
    private String ywr;
    /**
     * 义务人证件号
     */
    private String ywrzjh;
    /**
     * 义务人证件种类
     */
    private String ywrzjzl;
    /**
     * 权力类型
     */
    private String qllx;
    /**
     * 权利性质
     */
    private String qlxz;
    /**
     * 共有情况
     */
    private String gyqk;
    /**
     * 证书状态
     */
    private String zszt;
    /**
     * 不动产权证号
     */
    private String bdcqzh;
    /**
     * 原不动产权证号
     */
    private String ybdcqzh;
    /**
     * 附记
     */
    private String fj;
    /**
     * 不动产单元号
     */
    private String bdcdyh;
    /**
     * 不动产类型
     */
    private String bdclx;
    /**
     * 坐落
     */
    private String zl;
    /**
     * 户号
     */
    private String hh;
    /**
     * 自然幢号
     */
    private String zrzh;
    /**
     * 层号
     */
    private String ch;
    /**
     * 面积单位
     */
    private String mjdw;
    /**
     * 面积
     */
    private String mj;
    /**
     * 实测建筑面积
     */
    private String scjzmj;
    /**
     * 预测建筑面积
     */
    private String ycjzmj;
    /**
     * 土地面积
     */
    private String tdmj;
    /**
     * 土地使用权面积
     */
    private String tdsyqmj;
    /**
     * 用途
     */
    private String yt;
    /**
     * 房屋用途
     */
    private String fwyt;
    /**
     * 房屋性质
     */
    private String fwxz;
    /**
     * 房屋结构
     */
    private String fwjg;
    /**
     * 土地用途
     */
    private String tdyt;
    /**
     * 土地权利性质
     */
    private String tdqlxz;
    /**
     * 四至
     */
    private String sz;
    /**
     * 小地名
     */
    private String xdm;
    /**
     * 林班
     */
    private String lb;
    /**
     * 小班
     */
    private String xb;
    /**
     * 林种
     */
    private String lz;
    /**
     * 主要树种
     */
    private String zysz;
    /**
     * 建成年份
     */
    private String jznf;
    /**
     * 单元标识码
     */
    private String dybsm;
    /**
     * 单元表名
     */
    private String dybm;
    /**
     * 权利标识码
     */
    private String qlbsm;
    /**
     * 权利表名
     */
    private String qlbm;
    /**
     * 区县代码
     */
    private String qxdm;
    /**
     * 预告登记种类
     */
    private String ygdjzl;
    /**
     * 抵押类型
     */
    private String dylx;
    /**
     * 债券数额
     */
    private String zqse;
    /**
     * 查封机关
     */
    private String cfjg;
    /**
     * 查封文号
     */
    private String cfwh;
    /**
     * 查封文件
     */
    private String cfwj;
    /**
     * 查封类型
     */
    private String cflx;
    /**
     * 权利起始时间
     */
    private String qlqssj;
    /**
     * 权利结束时间
     */
    private String qljssj;

    /**
     * 注销业务号
     */
    private String zxywh;
    /**
     * 解封文号
     */
    private String jfwh;
    /**
     * 解封文件
     */
    private String jfwj;

}
