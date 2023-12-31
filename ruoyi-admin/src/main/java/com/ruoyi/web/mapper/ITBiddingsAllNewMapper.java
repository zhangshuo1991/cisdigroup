package com.ruoyi.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.web.domain.TBiddingsAllNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITBiddingsAllNewMapper extends BaseMapper<TBiddingsAllNew> {

    List<Map> getBiddingIndex(@Param("entname") String entname);

    List<Map> getMaxSupply(@Param("data_id") String dataId);

}
