package com.ruoyi.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.web.domain.TEnterpriseStockholder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITEnterpriseStockholderMapper extends BaseMapper<TEnterpriseStockholder> {

    List<Map<String, Object>> getSameShareholder(@Param("entnames") String[] entnames);

    List<Map<String, Object>> getSameInvestment(@Param("entnames") String[] entnames);

}
