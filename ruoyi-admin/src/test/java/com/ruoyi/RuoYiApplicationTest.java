package com.ruoyi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.web.domain.TBiddingIndex;
import com.ruoyi.web.domain.TEnterpriseBasicDto;
import com.ruoyi.web.domain.TEvaluatingIndex;
import com.ruoyi.web.mapper.ITBiddingIndexMapper;
import com.ruoyi.web.mapper.ITBiddingsAllNewMapper;
import com.ruoyi.web.mapper.ITEnterpriseBasicMapper;
import com.ruoyi.web.mapper.ITEvaluatingIndexMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RuoYiApplicationTest {

    @Autowired
    private ITBiddingsAllNewMapper itBiddingsAllNewMapper;

    @Autowired
    private ITEnterpriseBasicMapper itEnterpriseBasicMapper;

    @Autowired
    private ITBiddingIndexMapper itBiddingIndexMapper;

    @Autowired
    private ITEvaluatingIndexMapper itEvaluatingIndexMapper;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void updateScore() {
        itEnterpriseBasicMapper.selectList(new QueryWrapper<TEnterpriseBasicDto>().select("distinct uniscid,entname")).forEach(tEnterpriseBasicDto -> {
            TEvaluatingIndex tEvaluatingIndex = itEvaluatingIndexMapper
                    .selectOne(new QueryWrapper<TEvaluatingIndex>().eq("uniscid", tEnterpriseBasicDto.getUniscid()));
            if (tEvaluatingIndex != null) {
                TEnterpriseBasicDto tEnterpriseBasicDto1 = new TEnterpriseBasicDto();
                tEnterpriseBasicDto1.setUniscid(tEnterpriseBasicDto.getUniscid());
                tEnterpriseBasicDto1.setEntname(tEnterpriseBasicDto.getEntname());
                tEnterpriseBasicDto1.setOverallEnt(Float.valueOf(tEvaluatingIndex.getOverall()));
                itEnterpriseBasicMapper.updateById(tEnterpriseBasicDto1);
            }
        });

    }


    @Test
    public void testOne() {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct uniscid,entname");
        for (TEnterpriseBasicDto tEnterpriseBasicDto : itEnterpriseBasicMapper.selectList(queryWrapper)) {
            try {
                // 检查是否已经存在
                if (itBiddingIndexMapper.selectById(tEnterpriseBasicDto.getUniscid()) != null) {
                    log.info("entname: {} 已存在", tEnterpriseBasicDto.getEntname());
                    continue;
                }
                BigDecimal totalPurchaseAmount = BigDecimal.ZERO;
                BigDecimal topFiveSupplierAmount = BigDecimal.ZERO;
                List<Map> biddingIndexList = itBiddingsAllNewMapper.getBiddingIndex(tEnterpriseBasicDto.getEntname());
                if (biddingIndexList.size() == 0) {
                    log.info("entname: {} 无数据", tEnterpriseBasicDto.getEntname());
                    continue;
                }
                int five = 5;
                String maxSupplierName = "";
                String maxSupplierId = "";
                String dataId = (String) biddingIndexList.get(0).get("data_id");
                List<Map> maxSupply = itBiddingsAllNewMapper.getMaxSupply(dataId);
                if (maxSupply.size() > 0) {
                    maxSupplierName = (String) maxSupply.get(0).get("eid");
                    maxSupplierId = (String) maxSupply.get(0).get("ename");
                }
                for (Map map : biddingIndexList) {
                    Double bid_amount_single = (Double) map.get("bid_amount_single");
                    totalPurchaseAmount = totalPurchaseAmount.add(BigDecimal.valueOf(bid_amount_single));
                    if (five > 0) {
                        topFiveSupplierAmount = topFiveSupplierAmount.add(BigDecimal.valueOf(bid_amount_single));
                        five--;
                    }
                }
                TBiddingIndex tBiddingIndex = new TBiddingIndex();
                tBiddingIndex.setUniscid(tEnterpriseBasicDto.getUniscid());
                tBiddingIndex.setEntname(tEnterpriseBasicDto.getEntname());
                if (totalPurchaseAmount.compareTo(BigDecimal.ZERO) == 0) {
                    tBiddingIndex.setTotalPurchaseAmount(BigDecimal.ZERO);
                    tBiddingIndex.setTopFiveSupplierAmountRatio(BigDecimal.ZERO);
                    tBiddingIndex.setMaxSupplierName(maxSupplierName);
                    tBiddingIndex.setMaxSupplierId(maxSupplierId);
                    itBiddingIndexMapper.insert(tBiddingIndex);
                    continue;
                }
                tBiddingIndex.setTotalPurchaseAmount(totalPurchaseAmount);
                tBiddingIndex.setTopFiveSupplierAmountRatio(topFiveSupplierAmount.divide(totalPurchaseAmount, 4, BigDecimal.ROUND_HALF_UP));
                tBiddingIndex.setMaxSupplierName(maxSupplierName);
                tBiddingIndex.setMaxSupplierId(maxSupplierId);
                itBiddingIndexMapper.insert(tBiddingIndex);
            } catch (Exception e) {
                log.error("entname: {} error ,{} ", tEnterpriseBasicDto.getEntname(), e.getMessage());
            }
        }

    }
}