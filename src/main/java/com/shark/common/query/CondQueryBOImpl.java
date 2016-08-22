package com.shark.common.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Date: 11-9-3
 * Time: 下午5:07
 */
@Service
@Transactional(readOnly = true)
public class CondQueryBOImpl implements CondQueryBO {

    @Autowired
    private BaseSearchDao dao;

    @Override
    public ReportPage searchPage(SearchConditions csc) {
        return dao.searchPage(csc);
    }
}
